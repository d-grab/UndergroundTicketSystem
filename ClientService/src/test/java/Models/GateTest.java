/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Models;

import Controller.TicketCreator;
import java.io.StringReader;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author danlo
 */
public class GateTest {
    Ticket new_ticket = null;
    Date fromDate = null;
    Date toDate = null;
    public GateTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        fromDate = new Date();
        toDate = new Date();
        
        
        String departure_station = "London Bridge";
        String end_station = "Hamsted";
        
        int departure_zone = 1;
        int end_zone = 2;
        boolean enteredStation=false;
        boolean exitStation=false;
        
        
        // test if ticket created
        // Simulating that user will choose the ticket valid untill next day
        long validTo = fromDate.getTime() + 1000 * 60 * 60 * 24;
        Date expired = new Date(validTo);
        
        new_ticket = new Ticket(departure_station,end_station,departure_zone,
                end_zone,enteredStation,exitStation,fromDate,expired);
        assertNotNull(new_ticket);
    
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of entered method, of classGate.
     * The entry Gate from Entry station opened if  ticket valid for correct Entry zone
     * zone 1 to zone 2 (ticket valid)
     * Customer enters the Gate from Station in zone 1 (ticket valid)
     */
    @Test
    public void testEnteredGate() {
          
         // Assigning User will Enter the Gate in Station zone 1
            Gate entry_gate = new Gate(1);
            entry_gate.setEntryZone(1);
         //Marshalling Ticket   
            String result = TicketCreator.GenerateTicket(new_ticket);
         // Validation for the ticket
            ValidationTicket valid_ticket = entry_gate.entered(result);
         // Checking if user entered station   ---  if isTicket_valid == true 
            assertTrue(valid_ticket.isTicket_valid());
            System.out.println("Gate open");         
    }
    
     /**
     * Test of entered method, of classGate.
     * The Gate from the entry station not opened if ticket already have been used
     * zone 1 to zone 2 (ticket valid)
     * Customer entered the Gate from Station in zone 1 (ticket valid)
     */
    
    @Test
    public void testEnteredGateTwice() {

         // Assigning User will Enter the Gate in Station zone 1
            Gate entry_gate = new Gate(1);
            entry_gate.setEntryZone(1);
            
         //Marshalling Ticket   
            String entered_once_ticket = TicketCreator.GenerateTicket(new_ticket);
         // Validation for the ticket
            ValidationTicket valid_ticket = entry_gate.entered(entered_once_ticket);
         // Checking if ticket is valid 
            boolean is_entered = valid_ticket.isTicket_valid();
         // If ticket valid, assign true to EnteredStation (customer entered station)
            new_ticket.setEnteredStation(is_entered);
            
         // Marshalling  Validated Ticket with new EnteredStation value
            String entered_again_ticket = TicketCreator.GenerateTicket(new_ticket);
         // Entering station again with the same ticket
            ValidationTicket invalid_ticket = entry_gate.entered(entered_again_ticket);
         // Checking if user not entered station again  ---  if isTicket_valid == false
            assertFalse(invalid_ticket.isTicket_valid());
            System.out.println("Gate closed, ticket has been used before");      
    }
    
    /**
     * Test of entered method, of classGate.
     * The entry Gate from station not opened if entry Station in not valid zone range
     * zone 1 to zone 2 (ticket valid)
     * Customer enters the Gate from Station in zone 3 (ticket not valid)
     */
        @Test
    public void testEnteredGateInNotValidZone() {
        
    // Assigning User will Enter Station at zone 3
            Gate Station_gate = new Gate(3);
            Station_gate.setEntryZone(3);
    //Marshalling Ticket   
            String result = TicketCreator.GenerateTicket(new_ticket);
            
    // Validation for the ticket
            ValidationTicket valid_ticket = Station_gate.entered(result);
    // Checking if user not entered station   ---  if isTicket_valid == false 
            assertFalse(valid_ticket.isTicket_valid());
            System.out.println("Gate closed, invalid entry station");
    }

       /**
     * Test of entered method, of class Gate.
     * The entry Gate station not opened if Valid to time is after current data
     * Simulating current date is 2 days after ticket validTo
     * Customer enters the Gate from Station in zone 2 (valid zone)
     */
         @Test
    public void testNotEnteredGateTicketExpired() {
        // Checking Enter Gate not open if From date is past toDate
     
         // Assigning User will Enter Station at zone 5, where ticket valid from zone 1 to zone 2 
            Gate Station_gate = new Gate(2);
            Station_gate.setEntryZone(2);
        // Assigning new ToDate to the ticket(2 days before) that simulates past current Date.   
            long new_validTo_date = fromDate.getTime() - 1000 * 60 * 60 * 24 * 2;
            Date validTo = new Date(new_validTo_date);
            new_ticket.setToDate(validTo);
         //Marshalling Ticket   with new  toDate
            String result = TicketCreator.GenerateTicket(new_ticket);
            
         // Validation for the ticket
            ValidationTicket valid_ticket = Station_gate.entered(result);
         // Checking if user not entered station   ---  if isTicket_valid == false 
            assertFalse(valid_ticket.isTicket_valid());
            System.out.println("Ticket expired");
    }
    
    /**
     * Test of exited method, of class Gate.
     * The exit Gate from station opened if Exit Station in valid zone range
     * zone 1 to zone 2 (ticket valid)
     * Customer exits the Gate from Station in zone 2 (ticket valid)
     */
    @Test
    public void testExited() {
    // Testing if Exit Gates Open when ticket is valid
        
        Gate exit_gate = new Gate(2);
     // Assigning User will exit in zone 2
        exit_gate.setExitZone(2);
         
     //Marshalling Ticket
        String result = TicketCreator.GenerateTicket(new_ticket);
     // Checking if User Exit station
        boolean valid_ticket = exit_gate.exited(result);
        
     // Checking if isExited == true 
        assertTrue(valid_ticket); 
        System.out.println("Gate open, exited from the station");
    }

    /**
     * Test of exited method, of classGate.
     * The exit Gate from station not opened if exit station in not valid zone range
     * zone 1 to zone 2 (ticket valid)
     * Customer exits the Gate from Station in zone 4 (ticket not valid)
     */

      @Test
    public void testNotExitedInTheValidZone() {
        // Checking if Enter Gate open if ticket valid for correct Entry zone
            
            
         // Assigning User will Exits the Gate in Station zone 4
            Gate exit_gate = new Gate(4);
            exit_gate.setExitZone(4);
            
         //Marshalling Ticket   
            String result = TicketCreator.GenerateTicket(new_ticket);
         // Validation for the ticket
            boolean valid_ticket = exit_gate.exited(result);
         // Checking if user exited station   ---  valid_ticket == false
            assertFalse(valid_ticket);
            System.out.println("Gate closed, wrong exit station");
    }

    /**
     * Test of Exited method, of classGate.
     * The Gate from the exit station not opened if customer exited station
     * zone 1 to zone 2 (ticket valid)
     * Customer entered the Gate from Station in zone 1 (ticket valid)
     */
    
       @Test
    public void testExitedTwice() {
        // Checking if Enter Gate open if ticket valid for correct Entry zone
        
        // Assigning User will Exits the Gate in Station zone 1
            Gate exit_gate = new Gate(1);
            exit_gate.setEntryZone(1);        //Marshalling Ticket   
            String exit_once_ticket = TicketCreator.GenerateTicket(new_ticket);
        // Validation for the ticket
            boolean valid_ticket = exit_gate.exited(exit_once_ticket);
        // Checking if exited Station
            boolean exits = valid_ticket;
        // Assigning Exit Station to true
            new_ticket.setExitStation(exits);
        // Marshalling  ticket with new boolean value ExitStation = true
            String exited_again = TicketCreator.GenerateTicket(new_ticket);
        // Checking if customer exits with same ticket again
            boolean exits_again = exit_gate.exited(exited_again);
        
        // Checking if user not entered station again  ---  if isTicket_valid == false
            assertFalse(exits_again);
            System.out.println("Gate closed, Exited with this ticket already");        
    }
}
