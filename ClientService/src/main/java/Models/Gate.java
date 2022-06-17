/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author danlo
 * Validating the ticket when ticket inserted to the Gate
 */
public class Gate {
   
    private int zone;
    private int entry_zone, exit_zone;
/**
 * 
 * @param zone Passing zone from the station
 */
    public Gate(int zone) {
        this.zone = zone;
    }
 /**
  * 
  * @param zone Setting up the entry zone for the gate from the station
  */
    public void setEntryZone(int zone){
        this.entry_zone = zone;
    }
   /**
    * 
    * @param zone Setting up the exit zone for the gate from the station
    */ 
    public void setExitZone(int zone){
        this.exit_zone = zone;
    }
    /**
     * 
     * @param station_entry_exit_zone Entry or exit zone for the Station selected from dropdown menu
     * @param departure_zone departure zone for the gate
     * @param entry_zone entry zone for the gate
     * @return 
     */
    private boolean validZone(int station_entry_exit_zone, int departure_zone, int entry_zone){
        return ((station_entry_exit_zone >= departure_zone && station_entry_exit_zone <= entry_zone) || 
                (station_entry_exit_zone <= departure_zone && station_entry_exit_zone >= entry_zone)) && 
                entry_zone <= 6 && entry_zone > 0 && departure_zone <= 6 && departure_zone > 0;
    }
    /**
     * 
     * @param xml Passing marshalled ticket in xml 
     * @return Returning validate ticket with values = true if passed validation
     */
    public ValidationTicket entered(String xml) 
    {
        JAXBContext context;
        ValidationTicket validationTicket = new ValidationTicket();
        // Validating the ticket
        try{
            context= JAXBContext.newInstance(Ticket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader reader = new StringReader(xml);
            Ticket ticket =(Ticket) unmarshaller.unmarshal(reader);
            // If not entered and not a past date
            if(ticket.isEnteredStation() == false && new Date().before(ticket.getToDate() )&& 
                    validZone(entry_zone, ticket.getDeparture_zone(), ticket.getEnd_zone())
                    )
            {
                ticket.setEnteredStation(true);
                validationTicket.setTicket_valid(true);

            }
            //Marshalling ticket back to XML to  return validated ticket
           try{
                context = JAXBContext.newInstance(Ticket.class); 
             //Marshaller object
                Marshaller marshall = context.createMarshaller();
             //Writter object
                StringWriter writer = new StringWriter();
             //Marshall the object to XML
                marshall.marshal(ticket,writer);
                String second_marshall= writer.toString();
                validationTicket.setXml(second_marshall);
                return validationTicket;
            }
             catch(Exception e){
                   System.out.println(e);
                     }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * 
     * @param xml Passing marshalled ticket in xml format
     * @return  Returning boolean value true or false depending from valid ticket 
     */
    public boolean exited(String xml)
    {
        JAXBContext context;
        ValidationTicket ValidationTicket = new ValidationTicket();
        try{
        context= JAXBContext.newInstance(Ticket.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(xml);
        Ticket ticket =(Ticket) unmarshaller.unmarshal(reader);
        if(ticket.isExitStation() == false && new Date().before(ticket.getToDate()) &&
                validZone(exit_zone, ticket.getDeparture_zone(), ticket.getEnd_zone())  )
        {
            ticket.setExitStation(true); 
            return true;
        }
        }
        catch(Exception e)
        {
                   System.out.println(e);
        }
        return false;
    }
    /*
     * 
     * @return Returning zone for the gate
     */
    public int getZone()
    {
        return zone;
    }
} 
