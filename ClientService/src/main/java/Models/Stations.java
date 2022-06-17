/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author danlo
 * Creating Stations objects
 */
@XmlRootElement

public class Stations {
    private int stationid, zone;
    private String stationname;
    Gate gate;

    /**
     * 
     * @param stationid Id for each station generated in SQL
     * @param zone Identifying in which zone (entry or exit) the customer is 
     * @param stationname Name for the entry or exit station
     */
    public Stations(int stationid, int zone, String stationname) {
        this.stationid = stationid;
        this.zone = zone;
        this.stationname = stationname;
        
        //Creating gate object inside of the station ( passing zone of the station)
        gate= new Gate(this.zone);
    }
    
    /**
     * 
     * @return  returning Gate zone (from the station)
     */
    public int getGateZone() {
        
        return gate.getZone();
    }

    public Stations() {
    }

    /**
     * 
     * @return Retrieving Id for station generated in the database
     */
    public int getStationid() {
        return stationid;
    }
    /**
     *
     * @param stationid Setting new id for the station
     */
    public void setStationid(int stationid) {
        this.stationid = stationid;
    }
    /**
     * 
     * @return Returning zone for the station
     */
    public int getZone() {
        return zone;
    }
    /**
     * 
     * @param zone  Setting new zone for the station (entry or exit station)
     */
    public void setZone(int zone) {
        this.zone = zone;
    }
    /**
     * 
     * @return Returning the name of the station
     */
    public String getStationname() {
        return stationname;
    }
    /**
     * 
     * @param stationname Setting new station name , the customer choose from dropdown list
     */
    public void setStationname(String stationname) {
        this.stationname = stationname;
    }
    /**
     * 
     * @param xml Passing marshalled ticket for validation
     * @param entry_zone Entry zone from the station used for the Gate
     * @return Returning xml ticket with new zone for the Gate validation
     */
    @Transient
    public ValidationTicket entered(String xml, int entry_zone)
    {
        gate.setEntryZone(entry_zone);
        return gate.entered(xml);
    }
    /**
     * 
     * @param xml Passing xml ticket
     * @param exit_zone Passing exit zone from the station for the Gate
     * @return returning xml ticket for the Gate validation
     */
    @Transient
    public boolean exited(String xml, int exit_zone)
    {
        gate.setExitZone(exit_zone);
        return gate.exited(xml);
    }
}
