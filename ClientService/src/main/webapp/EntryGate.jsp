<%-- 
    Document   : EntryGate
    Created on : 2 Jun 2022, 12:15:50
    Author     : danlo
--%>

<%@page import="java.util.Date"%>
<%@page import="Controller.RestClient"%>
<%@page import="java.util.List"%>
<%@page import="Controller.Travel"%>
<%@page import="Models.Stations"%>
<%@page import="Controller.TicketCreator"%>
<%@page import="java.io.StringReader"%>
<%@page import="Models.Ticket"%>
<%@page import="javax.xml.bind.Unmarshaller"%>
<%@page import="javax.xml.bind.JAXBContext"%>
<%@page import="Models.ValidationTicket"%>
<%@page import="Models.Gate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
  h2  {
    color: green;
  }
    h3  {
    color: red;
  }
</style>
    </head>
    
        <h1>Entry gate - Paste your ticket to enter the station</h1>
        <br>
        <form action="EntryGate.jsp" method="POST">
            <label for="station">Choose the station You would like enter from:</label>
            <select name="NewEntryStation" id="station" required>
        <option value="">Please select</option>
        <br>
            <% // Displaying entry station in dropdown menu
                List<Stations> dropdownlist = RestClient.getStations();

                for (int element = 0; element < dropdownlist.size(); element++)
                    {
                        out.print("<option value="+ element + ">" + dropdownlist.get(element).getStationname() +" - zone: "+dropdownlist.get(element).getZone()+ "</option>");
                    }
            %>
            </select>
            <br> <br>
            <textarea rows="14" cols="100" name="xml"></textarea>
            <input type="submit" value="Enter the Station"  >
        </form>
        <%  String xml_ticket = null;
            
            // Checking if customer pasted a ticket
            try {
            if(request.getParameter("xml") != null){
                String xml = request.getParameter("xml");
                
                int entry_station= Integer.parseInt(request.getParameter("NewEntryStation"));
                int new_entry_zone= dropdownlist.get(entry_station).getZone();
                
                //Unmarshalling the ticket
                JAXBContext context = JAXBContext.newInstance(Ticket.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StringReader xml_reader = new StringReader (xml);
                Ticket ticket = (Ticket) unmarshaller.unmarshal(xml_reader);

                // Station.getGate
                
                
                // Retrieving entry station for validation
                Stations departure = Travel.Journey.departure;
                
                //Checking if customer entered the station and if the date is correct
                ValidationTicket vt = departure.entered(xml, new_entry_zone);
  
                // checking if the ticket is valid
                if (vt.isTicket_valid() )
                    {
                    
                    ticket.setEnteredStation(vt.isTicket_valid());
                    out.print("<h2> Gate Open </h2> " + "<h4>Welcome to the" + " "+ (dropdownlist.get(entry_station).getStationname() )+" "+"station</h3>" );
                    }
                else{
                    out.print("<h3>Gate not Open !! Your ticket is not valid</h3>");
                    }
                //Marshalling the ticket to display new boolean value changed from false to true
                xml_ticket = TicketCreator.GenerateTicket(ticket);
            
        %>
       
<!-- Show to button and XML ticket only if ticket is valid -->
        <%  if (vt.isTicket_valid() ) { %>
         <hr>  
        <h4>Validation result  -  Copy that ticket to the EXIT GATE </h4>
         <hr>  
         <br>
<!-- Displaying ticket for the customer to use it to exit the station-->
        <textarea rows="15" cols="100" name="xml"><% out.print(xml_ticket); %></textarea>
        <a href="ExitGate.jsp" ><input type="submit" value="Go to Exit GATE >>>">  </a>
<!-- Exception if user pasted different ticket or added some letters not valid for the ticket  -->       
        <% }} }catch(Exception e){out.print("Pasted ticket is not aloud, check the format of XML");}%>
    
</html>
