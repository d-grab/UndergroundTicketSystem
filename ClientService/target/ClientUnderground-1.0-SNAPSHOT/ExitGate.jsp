<%-- 
    Document   : EntryGate
    Created on : 2 Jun 2022, 12:15:50
    Author     : danlo
--%>

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
    <body>
        <h1>Exit gate Page</h1>
        <form action="ExitGate.jsp" method="POST">
            
            <label for="station">Choose a station You arriving at:</label>
            <select name="NewExitStation" id="station" required>
        <option value="">Please select</option>
        
            <% 
                //Dropdown list menu to choose the station customer would like to exit at
                List<Stations> dropdownlist = RestClient.getStations();

                for (int element = 0; element < dropdownlist.size(); element++)
                    {
                        out.print("<option value="+ element + ">" + dropdownlist.get(element).getStationname() +" - "+dropdownlist.get(element).getZone()+ "</option>");
                    }
            %>
            </select>
             <br> <br>
            <textarea rows="14" cols="100" name="xml"></textarea>
            <input type="submit" value="Exit from the station" >
        </form>
        <%
            try {
            if(request.getParameter("xml") != null){
                String xml = request.getParameter("xml");
                String xml_ticket = null;
                
                // Retrieving the index for the station customer choosed
                int exit_station= Integer.parseInt(request.getParameter("NewExitStation"));
                // Assigning new zone based on the index to the variable for exited validation
                int new_exit_zone= dropdownlist.get(exit_station).getZone();
                
                //Unmarshalling the ticket
                JAXBContext context = JAXBContext.newInstance(Ticket.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                StringReader xml_reader = new StringReader (xml);
                Ticket ticket = (Ticket)unmarshaller.unmarshal(xml_reader);

             

                // Calling Journey method from Travel class to get destination station details
                 Stations exit= Travel.Journey.destination;
                // Validating the ticket if customer can exit the gate
                 boolean exitedStation = exit.exited(xml,new_exit_zone);
                 
                 // if Exited == true assign new value to the ticket and print message
                if (exitedStation ){
                
                //Setting new boolean value exited = true to the ticket to display validated ticket
                    ticket.setExitStation(exitedStation);
                    out.print("<h2> Gate Open </h2> " + "<h4>Welcome to the" + " "+ (dropdownlist.get(exit_station).getStationname() )+" "+"station  <br><br> You exited station</h3>"  );
                    
               }
                else{
                    out.print("<h3>Gate not Open</h3>");
                }
                
                //Marshalling the ticket back to see new boolean value on the ticket Exited = true
                xml_ticket = TicketCreator.GenerateTicket(ticket);
            
        %>
<!-- If validation went successfully print the ticket, if not print the message -->
        <textarea rows="8" cols="50" name="xml">

        <%  if (exitedStation ){out.print(xml_ticket);} else {out.print(("Your ticket is invalid")); }%></textarea>
<!-- Checking if customer pasted correct ticket, or if there is no any additional text -->        
        <% }}catch(Exception e){out.print("Pasted ticket is not aloud, check the format of XML");} %>
    </body>
</html>
