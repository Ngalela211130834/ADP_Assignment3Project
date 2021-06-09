

/**
 *ADP2-Assignment3
 * @author Gixelle Ngalela 211130834
 * 09/06/2021
 * 
 * 
 */
package za.ac.cput.assignment3projectngalela;

import java.io.Serializable;


public class Stakeholder implements Serializable{
   private String stHolderId;

    public Stakeholder() {
    }
    
    public Stakeholder(String stHolderId) {
        this.stHolderId = stHolderId;
    }
    
    public String getStHolderId() {
        return stHolderId;
    }

    public void setStHolderId(String stHolderId) {
        this.stHolderId = stHolderId;
    }

    @Override
    public String toString() {
       return stHolderId;
    }
}
