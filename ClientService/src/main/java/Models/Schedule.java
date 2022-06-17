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
 * Creating Schedules objects
 */
@XmlRootElement
public class Schedule {
    private int scheduleid;
    private Date arrivaltime;
    private int stationid;

    /**
     * 
     * @param scheduleid Schedule 
     * @param arrivaltime Time the Tube arrive to the station
     * @param stationid Id for the station train is going from
     */
    public Schedule(int scheduleid, Date arrivaltime, int stationid) {
        this.scheduleid = scheduleid;
        this.arrivaltime = arrivaltime;
        this.stationid = stationid;
    }
    public Schedule() {
    }

    /**
     * 
     * @return Returning schedule id
     */
    public int getScheduleid() {
        return scheduleid;
    }
    /**
     * 
     * @param scheduleid Setting new schedule ID
     */
    public void setScheduleid(int scheduleid) {
        this.scheduleid = scheduleid;
    }

    /**
     * 
     * @return Returning arrival time to the station for tube
     */
    public Date getArrivaltime() {
        return arrivaltime;
    }
    /**
     * 
     * @param arrivaltime Setting up new Arrival time 
     */
    public void setArrivaltime(Date arrivaltime) {
        this.arrivaltime = arrivaltime;
    }
    /**
     * 
     * @return Retrieving Station id
     */
    public int getStationid() {
        return stationid;
    }
    /**
     * 
     * @param stationid Setting Station id for new schedule 
     */
    public void setStationid(int stationid) {
        this.stationid = stationid;
    }

}
