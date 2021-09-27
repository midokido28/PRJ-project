<%-- 
    Document   : create
    Created on : Jul 12, 2021, 11:45:12 AM
    Author     : Suki
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="sample.food.FoodError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Food</title>
    </head>
    <body>
        <%
            FoodError userError = (FoodError) request.getAttribute("FOOD_ERROR");
            if (userError == null) {
                userError = new FoodError();
            }
        %>
        
        <%
            Date dNow = new Date();
            SimpleDateFormat ft
                    = new SimpleDateFormat("YYYY/MM/dd hh:mm:ss ");          
        %>
        <h1>Add Food</h1>
        <a href="food.jsp"><button>Back</button></a>

        <form action="MainController">
            ID: <input type="text" name="id" required=""/>
            <%= userError.getIDError()%></br>

            Name: <input type="text" name="name" required=""/>            
            <%= userError.getNameError()%></br>

            Description: <input type="text" name="description" required=""/></br>                 

            Price: <input type="num" name="price" required=""/></br>        

            Cooking Time: <input type="num" name="cookingtime" required=""/></br>          
            <input type="hidden" name="isdelete" value="0" readonly=""/></br>         
            <input type="hidden" name="createdate" value="<%= ft.format(dNow) %>" readonly=""/></br>         
            <input type="submit" name="action" value="Create"/>
            <input type="reset" value="Reset"/>            
        </form>  
    </body>
</html>
