<%-- 
    Document   : schedule
    Created on : 11 Jun 2022, 18:46:28
    Author     : danlo
--%>

<%@page import="Models.Stations"%>
<%@page import="Controller.RestClient"%>
<%@page import="Models.Schedule"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Ticket Machine </h1>
    <form action="schedule.jsp" id="usrform" method="post">
        <label for="station">What schedule You would like to choose :</label>
    <select name="ChooseFromStations" id="station" required>
        <option value="">Please select</option>
    <% 
        List<Schedule> dropdownlist = RestClient.getSchedule();
        List<Stations> dropdownlist3 = RestClient.getStations();
        
      
       
        for (int element = 0; element < dropdownlist.size(); element++)
            {
                int arrival = dropdownlist.get(element).getStationid();
                out.print("<option value="+ element + ">" + dropdownlist.get(element).getStationid());
                 out.print(dropdownlist3.get(dropdownlist.get(element).getScheduleid()).getStationname());
                
            }
                
                
            
    %>
    </select>
    </form>
    </body>
</html>
