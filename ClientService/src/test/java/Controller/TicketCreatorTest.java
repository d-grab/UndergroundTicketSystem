/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Controller;

import Models.Ticket;
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
public class TicketCreatorTest {
    Ticket new_ticket = null;
    
    public TicketCreatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    
        Date fromDate = new Date();
        Date toDate = new Date();
        
        
        String departure_station = "London Bridge";
        String end_station = "Hamsted";
        
        int departure_zone = 1;
        int end_zone = 2;
        boolean enteredStation=false;
        boolean exitStation=false;
        
        
        // test if ticket created
        // add 24 hours 1000 ms * 60 secs * 60 mins * 24 hrs to current time
        long validTo = fromDate.getTime() + 1000 * 60 * 60 * 24;
        Date expired = new Date(validTo);
        
        new_ticket = new Ticket(departure_station,end_station,departure_zone,
                end_zone,false,false,fromDate,expired);
        assertNotNull(new_ticket);
    }
     
    @After
    public void tearDown() {
    }

    /**
     * Test of GenerateTicket method, of class TicketCreator.
     */
    @Test
    public void testGenerateTicket() {
        try {
            System.out.println("GenerateTicket");
            // Ticket ticket = null;
            
            String result = TicketCreator.GenerateTicket(new_ticket);
            JAXBContext context = JAXBContext.newInstance(Ticket.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader xml_reader = new StringReader (result);
            Ticket ticket = (Ticket) unmarshaller.unmarshal(xml_reader);
            assertEquals(new_ticket.getDeparture_station(), ticket.getDeparture_station());
            // TODO review the generated test code and remove the default call to fail.
            
        } catch (JAXBException ex) {
            Logger.getLogger(TicketCreatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}
