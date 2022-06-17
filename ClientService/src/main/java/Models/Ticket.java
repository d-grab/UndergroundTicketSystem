/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danlo
 * Creating Ticket objects 
 */

@XmlRootElement
public class Ticket {
    
    private String departure_station,end_station;
    
    private int departure_zone, end_zone;
    
    private boolean enteredStation, exitStation;
    
    private Date fromDate, toDate;

    /**
     * 
     * @param departure_station Name of departure station
     * @param end_station   Name of destination station
     * @param departure_zone Zone for departure station
     * @param end_zone Zone for the destination station
     * @param enteredStation Boolean if customer entered station (default = false)
     * @param exitStation Boolean if customers exited station (default = false)
     * @param fromDate Date the ticket is valid from ( = new Date() )
     * @param toDate  Date the ticket expires (from the calendar)
     */
    public Ticket(String departure_station, String end_station, int departure_zone,
            int end_zone, boolean enteredStation, boolean exitStation,Date fromDate,Date toDate) {
        this.departure_station = departure_station;
        this.departure_zone = departure_zone;
        this.end_station = end_station;
        
        this.end_zone = end_zone;
        this.enteredStation = enteredStation;
        this.exitStation = exitStation;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    public Ticket(){
    
    }
    /**
     * 
     * @return Returning departure station name
     */
    public String getDeparture_station() {
        return departure_station;
    }
    /**
     * 
     * @param departure_station Setting new departure station Name
     */
    public void setDeparture_station(String departure_station) {
        this.departure_station = departure_station;
    }
    /**
     * 
     * @return Returning station destination Name
     */
    public String getEnd_station() {
        return end_station;
    }
    /**
     * 
     * @param end_station Setting new destination station Name
     */
    public void setEnd_station(String end_station) {
        this.end_station = end_station;
    }
    /**
     * 
     * @return  Returning departure station zone
     */
    public int getDeparture_zone() {
        return departure_zone;
    }
    /**
     * 
     * @param departure_zone Setting a new Departure station zone
     */
    public void setDeparture_zone(int departure_zone) {
        this.departure_zone = departure_zone;
    }
    /**
     * 
     * @return  Returning destination Station zone
     */
    public int getEnd_zone() {
        return end_zone;
    }
    /**
     * 
     * @param end_zone Setting new end zone for the ticket
     */
    public void setEnd_zone(int end_zone) {
        this.end_zone = end_zone;
    }
    /**
     * 
     * @return Returning if ticket has been used to enter a gate
     */
    public boolean isEnteredStation() {
        return enteredStation;
    }
    /**
     * 
     * @param enteredStation  Setting the ticket has been used to enter station
     */
    public void setEnteredStation(boolean enteredStation) {
        this.enteredStation = enteredStation;
    }
    /**
     * 
     * @return Returning Ticket has been used to exit the station
     */
    public boolean isExitStation() {
        return exitStation;
    }
    /**
     * 
     * @param exitStation Setting the ticket has been used to exit station
     */
    public void setExitStation(boolean exitStation) {
        this.exitStation = exitStation;
    }
    /**
     * 
     * @return Returning the date ticket is valid from
     */
    public Date getFromDate() {
        return fromDate;
    }
    /**
     * 
     * @param fromDate Setting the date , the ticket is valid from
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    /**
     * 
     * @return Returning the date , ticket is valid to 
     */
    public Date getToDate() {
        return toDate;
    }
    /**
     * 
     * @param toDate Setting the date ticket is valid to
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
    
    
   

  
    

    


  
  
    
}
