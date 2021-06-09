/**
 *ADP2-Assignment3
 * @author Gixelle Ngalela 211130834
 * 09/06/2021
 * 
 */

package za.ac.cput.assignment3projectngalela;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;



public class TestFiles {
    
    private ArrayList<Customer> CustArray = new ArrayList<Customer>();
    private ArrayList<Supplier> SuppArray = new ArrayList<Supplier>();
    LinkedList<Integer> CustQ = new LinkedList<Integer>();
    int[] IdObject = new int[6];
    Customer[] customers = new Customer[6];

    ObjectInputStream objectInputStream;

    public void openFile() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream("stakeholder.ser"));
            System.out.println("The file opened correctly");
            
        } catch (IOException e) {
            System.out.println("Ther is an error to open the specific file: " + e.getMessage());
            System.exit(1);
        }
    }

    public void readFile() {
        try {
            for (int i = 0; i < 11; ++i) {
                Object object = objectInputStream.readObject();
                if (isCustomer(object)) {
                    addToCustomerList((Customer) object);
                } else {
                    addToSupplierList((Supplier) object);
                }
            }
            System.out.println("Customer's Array not yet Sorted ");
            for (Customer customer : CustArray ) {
                System.out.println(customer );
            }
           
            sortCustomersVersionThree();
            System.out.println("Customer's Array Sorted: ");
            
            for (Customer customer : CustArray) {
                System.out.println(customer);
            }
            System.out.println("Customer: " + CustArray.get(3).getFirstName() + " Age is " + getCustomerAge(CustArray.get(3)));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ther is an error reading stakeholder.ser file: " + e.getMessage());
            System.exit(1);

        } finally {
            closeFile();
        }
    }

    public void closeFile() {
        try {
            objectInputStream.close();
        } catch (IOException e) {
            System.out.println("There is error closing stakeholder.ser file: " + e.getMessage());
            System.exit(1);
        }

    }
// sorting the customer arraylist
    public void sortCustomers() {
        String ID = " ";
        int idNo = 0;
        int indexer = 0;
        char[] IDNumb = new char[3];
        String dummyID = "";
        char character = '0';

        for (int i = 0; i < CustArray.size(); i++) {
            ID = CustArray.get(i).getStHolderId();
            
            for (int j = 1; j < 4; ++j) {
                character = ID.charAt(j);
                IDNumb[indexer] = character;
                ++indexer;
            }
            indexer = 0;
            dummyID = new String(IDNumb);
            
            IdObject[i] = Integer.parseInt(dummyID);
            customers[i] = CustArray.get(i);
        }

        int indexForSecondObject = 1;
        for (int i = 0; i < customers.length; ++i) {
            if (IdObject[i] < IdObject[indexForSecondObject]) {
                if (CustQ.size() == 0) {
                    CustQ.add(IdObject[i]);
                } else {
                    if (CustQ.peek() > IdObject[i]) {
                        CustQ.add(IdObject[i]);
                    }
                }
            } else {
                CustQ.add(IdObject[indexForSecondObject]);
            }
            indexForSecondObject++;
        }

    }

    public void sortCustomersVersionTwo() {
        String id = "";
        int idNo = 0;
        int counter = 0;
        char[] IDNumb = new char[3];
        String csID = "";
        char c = '0';

        for (int i = 0; i < CustArray.size(); i++) {
            id = CustArray.get(i).getStHolderId();
            for (int m = 1; m < 4; ++m) {
                c = id.charAt(m);
                 IDNumb[counter] = c;
                ++counter;
            }
             counter = 0;
            csID = new String( IDNumb);
            IdObject[i] = Integer.parseInt(csID);
            customers[i] = CustArray.get(i);
        }

        for (int ID :IdObject) {
           CustQ.add(ID);
        }
        Collections.sort(CustQ);
        ArrayList<Customer> sortedCustomerArray = new ArrayList<Customer>();
        String newlySortedIds;
        for (int i = 0; i < CustQ.size(); i++) {
            newlySortedIds = "C" + CustQ.get(i).toString();
            for (int j = 0; j < customers.length; j++) {
                if ((newlySortedIds.equalsIgnoreCase(customers[j].getStHolderId()))) {
                    sortedCustomerArray.add(customers[j]);
                    break;
                }
            }
        }
        CustArray.clear();
        CustArray.addAll(sortedCustomerArray);
    }

    public void sortCustomersVersionThree() {
        Customer temporaryCustomer;
        Customer[] customers = createTempArray();
        for (int i = 0; i < customers.length - 1; i++) {
            for (int j = i + 1; j < customers.length; j++) {
                if (customers[i].getStHolderId().
                        compareTo(customers[j].getStHolderId()) > 0) {
                    temporaryCustomer = customers[i];
                    customers[i] = customers[j];
                    customers[j] = temporaryCustomer;
                }
            }
        }
        CustArray.clear();
        CustArray.addAll(Arrays.asList(customers));
    }
   
 //  the array to store customers's age 
    
    public int getCustomerAge(Customer customer) {
        LocalDate dateOfBirth = LocalDate.parse(customer.getDateOfBirth());
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

    public void print() {

    }

    private Customer[] createTempArray() {
        customers = new Customer[CustArray.size()];
        for (int i = 0; i < CustArray.size(); ++i) {
            customers[i] = CustArray.get(i);
        }
        return customers;
    }

    private LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private boolean isCustomer(Object object) {
        return (object.getClass().equals(Customer.class));
    }

    private boolean isSupplier(Object object) {
        return (object.getClass().equals(Supplier.class));
    }

    private void addToCustomerList(Customer object) {
        CustArray.add(object);
    }

    private void addToSupplierList(Supplier object) {
        SuppArray.add(object);
    }

    
}
