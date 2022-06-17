/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Schedule;
import Models.Stations;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author danlo
 */
public class RestClient {
    
    /**
     * 
     * @return Returning list of stations from the Rest Service
     * (UndegroundTicketWebService)
     */
    public static List<Stations> getStations()
    {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8080/WebApplicationTry1/webresources/pkg.stations");
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_XML);
        Response res = invocation.get();
        List<Stations> list= res.readEntity(new GenericType<List<Stations>>(){});
        return list;
    }
    /**
     * 
     * @return Returning list of underground schedule from the Rest Service
     * (UndegroundTicketWebService)
     */
    public static List<Schedule> getSchedule()
    {
        Client client = ClientBuilder.newBuilder().build();
        WebTarget target = client.target("http://localhost:8080/WebApplicationTry1/webresources/pkg.schedule");
        Invocation.Builder invocation = target.request(MediaType.APPLICATION_XML);
        Response res = invocation.get();
        List<Schedule> list= res.readEntity(new GenericType<List<Schedule>>(){});
        return list;
    }
}
