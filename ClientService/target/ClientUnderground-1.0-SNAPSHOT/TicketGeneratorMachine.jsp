<%-- 
    Document   : UnMarshaller
    Created on : 24 Mar 2022, 15:07:27
    Author     : danlo
--%>



<%@page import="Controller.StoredTicket"%>
<%@page import="java.util.concurrent.TimeUnit"%>
<%@page import="java.time.Duration"%>
<%@page import="java.lang.Math"%>
<%@page import="Controller.Travel"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Controller.TicketCreator"%>
<%@page import="Models.Ticket"%>
<%@page import="java.util.List"%>
<%@page import="Models.Stations"%>
<%@page import="Controller.RestClient"%>
<%@page import="java.io.StringReader"%>
<%@page import="javax.xml.bind.Unmarshaller"%>
<%@page import="javax.xml.bind.JAXBContext"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>Ticket Machine </h1>
    <form action="TicketGeneratorMachine.jsp" id="usrform" method="post">
        <label for="station">Choose a station You would like to go from :</label>
    <select name="ChooseFromStations" id="station" required>
        <option value="">Please select</option>
    <% 
        List<Stations> dropdownlist = RestClient.getStations();
        
        for (int element = 0; element < dropdownlist.size(); element++)
            {
                out.print("<option value="+ element + ">" + dropdownlist.get(element).getStationname() + " - zone: "+dropdownlist.get(element).getZone()+"</option>");
            }
    %>
    </select>
    <br>
    <label for="station">Choose station You go to :</label>
       <select name="ChooseToStations" id="station" required>
        <option value="">Please select</option>
        
    <% 
        
        for (int element = 0; element < dropdownlist.size(); element++)
             {
                out.print("<option value="+ element+ ">" + dropdownlist.get(element).getStationname() + " - zone: "+dropdownlist.get(element).getZone()+ "</option>");
             }
    %>
       </select><br>
    
        Ticket Expire date<input type="date" name="endDate" required><br><br>
  <input type="hidden" value="no" name="showticket">
        <input type="Submit" value="Buy Ticket">
        </form>
       
       <br>
        <% 
           // Declaring prices for the ticket locally
           
            int[][] prices = {{1,1,10},{1,2,11},{1,3,12},{1,4,13},{1,5,14},{1,6,15},
                             {2,2,7},{2,3,8},{2,4,9},{2,5,10},{2,6,11},
                             {3,3,5},{3,4,6},{3,5,7},{3,6,8},
                             {4,4,4},{4,5,5},{4,6,6},
                             {5,5,3}, {5,6,4},
                             {6,6,2}};
            int price = 0;
            String xml_ticket = null;
            int departure_zone = 0;
            int end_zone = 0;
            Date fromDate = new Date();
            List saved_ticket= null;
            Date convertedToDate = new Date();
            //checkinf if user selected all required fields (for example Starting station, destination station , date etc.)
            if(request.getParameter("ChooseFromStations") != null && 
               request.getParameter("ChooseToStations")!= null &&
               request.getParameter("endDate") != null)
               {
               // Retrieving the date from calendar
               String toDate = request.getParameter("endDate");
               SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                
             // converting the date from cale
                convertedToDate = dateFormat.parse(toDate);

            //out.print("<h1>" + request.getParameter("ChooseFromStations") + "</h1>");
               departure_zone = Integer.parseInt(request.getParameter("ChooseFromStations"));
               end_zone = Integer.parseInt(request.getParameter("ChooseToStations"));
               
            // Looping through the array list of prices to find correct price
               for (int i = 0; i < prices.length; i++){
                    if ((prices[i][0] == dropdownlist.get(departure_zone).getZone() && 
                        prices[i][1] == dropdownlist.get(end_zone).getZone()) || 
                        (prices[i][0] == dropdownlist.get(end_zone).getZone() && 
                        prices[i][1] == dropdownlist.get(departure_zone).getZone())){
                        price = prices[i][2];
                        break;
                    }
                }
             //Creating stations object for entry station and exit station
               Stations entry_station= new Stations(dropdownlist.get(departure_zone).getStationid(),dropdownlist.get(departure_zone).getZone(),dropdownlist.get(departure_zone).getStationname());
               Stations exit_station= new Stations(dropdownlist.get(end_zone).getStationid(),dropdownlist.get(end_zone).getZone(),dropdownlist.get(end_zone).getStationname());
              
               
              //Storing departure station , and destination Station objects in class Travel
               Travel.Journey.departure = entry_station;
               Travel.Journey.destination = exit_station;
              
              
              //Creating ticket object and passing required parameters
               Ticket ticket = new Ticket(dropdownlist.get(departure_zone).getStationname(),dropdownlist.get(end_zone).getStationname(),
               dropdownlist.get(departure_zone).getZone(), dropdownlist.get(end_zone).getZone(),false,false,fromDate,convertedToDate);
              // Marshalling the ticket to XML format for the customer
               xml_ticket = TicketCreator.GenerateTicket(ticket);
               
             //Storing ticket locally in ArrayList 
               StoredTicket.StoringTicket.ticketlist.add(ticket);
               saved_ticket = StoredTicket.StoringTicket.ticketlist;
               
            
        %>
        
<!--  Displaying ticket information -->
        <hr>
        <h1>Ticket Information</h1>
        <p>Departure Station: <% out.print(dropdownlist.get(departure_zone).getStationname()); %> </p>
        <p>End Station: <% out.print(dropdownlist.get(end_zone).getStationname()); %> </p>
        <p>Ticket valid from zone <%out.print(dropdownlist.get(departure_zone).getZone() + " " + "to zone " + " " + dropdownlist.get(end_zone).getZone());  %>
        <p>Valid for: <% out.print(TimeUnit.DAYS.convert(Math.abs(convertedToDate.getTime() - fromDate.getTime()), TimeUnit.MILLISECONDS) + 1); %> days </p>
        <p>Price: £<% out.print(price); %>
        
<!--  Creating a form to check if user clicked a button to Buy ticket and then display XML ticket -->
        <form action="TicketGeneratorMachine.jsp" method="post">
            <input type="hidden" value="<% out.print(request.getParameter("ChooseFromStations")); %>" name="ChooseFromStations">
            <input type="hidden" value="<% out.print(request.getParameter("ChooseToStations")); %>" name="ChooseToStations">
            <input type="hidden" value="<% out.print(request.getParameter("endDate")); %>" name="endDate">
            <input type="hidden" value="yes" name="showticket">
            <br>
            <p>Would You like to buy this ticket ?</p>
<!-- Button simulating payment transaction -->
            <p><input type="submit" value="Make a Payment"></p>
            
        </form>
            <hr><br>
            <% if (request.getParameter("showticket").equals("yes")){ %>
        <h1>Your ticket !</h1>
<!--  Displaying Ticket XML for the customer to enter the station -->        
        <textarea rows="14" cols="100" name="xml"><% out.print(xml_ticket); %></textarea><br>
<!--  Button with link to go to the Entry gate -->   
        <a href="EntryGate.jsp"><input type="submit" value="Go to ENTRY GATE  >>>"></a>
        <% } } %>
    </body>3
    <br><br>
    
</html>
