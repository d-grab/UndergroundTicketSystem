/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.Ticket;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author danlo
 */
public class TicketCreator {
    
    /**
     * 
     * @param ticket Passing Created object Ticket for marshalling
     * @return Returning marshalled ticket object in XML
     */
   public static String GenerateTicket(Ticket ticket)
           {
               try{
                    JAXBContext context = JAXBContext.newInstance(Ticket.class); 
                    Marshaller marshall = context.createMarshaller();
                    marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                    StringWriter writer = new StringWriter();
                    marshall.marshal(ticket,writer);
                    return writer.toString();
        }
             catch(Exception e)
             {
                 System.out.println(e);
             }
                return null;
           }
}