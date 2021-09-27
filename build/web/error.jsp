<%-- 
    Document   : error.jsp
    Created on : Jul 12, 2021, 11:27:18 AM
    Author     : Suki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR...</title>
    </head>
    <body>
        <h1>Error: <%= session.getAttribute("ERROR_MESSAGE") %></h1>
        
    </body>
</html>
