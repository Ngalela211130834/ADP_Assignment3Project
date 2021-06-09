/**
 *ADP2-Assignment3
 * @author Gixelle Ngalela 211130834
 * 09/06/2021
 * 
 */

package za.ac.cput.assignment3projectngalela;


public class RunClass {
    
    public static void main (String[] args){
        
        TestFiles read = new TestFiles();
        read.openFile();
        read.readFile();
        read.closeFile();
        
    }
    
}
