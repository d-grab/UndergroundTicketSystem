/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author danlo
 */
public class ValidationTicket {
    private String Xml;
    private boolean ticket_valid;

    public ValidationTicket() {
        
    }

    
    /**
     * 
     * @return Returning ticket in XML format
     */
    public String getXml() {
        return Xml;
    }
    /**
     * 
     * @param Xml Setting new Ticket in XML
     */
    public void setXml(String Xml) {
        this.Xml = Xml;
    }
    /**
     * 
     * @return Returning if validation was successful
     */
    public boolean isTicket_valid() {
        return ticket_valid;
    }
    /**
     * 
     * @param ticket_valid  Setting if ticket was validated through the gate
     */
    public void setTicket_valid(boolean ticket_valid) {
        this.ticket_valid = ticket_valid;
    }
    
    
}
