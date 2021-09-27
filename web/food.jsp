<%-- 
    Document   : food
    Created on : Jul 12, 2021, 11:03:45 AM
    Author     : Suki
--%>

<%@page import="sample.food.FoodDAO"%>
<%@page import="java.util.List"%>
<%@page import="sample.food.FoodDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Management</title>
    </head>
    <body>
        <h1>Food Management</h1>
        <%
            String error_message = (String) request.getAttribute("ERROR_MESSAGE");
            if (error_message != null) {
        %>
        <h1>
            <%=error_message%>
        </h1>
        <%
            }
        %>  

        <%String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %> 
        <a href="create.jsp"><button>Add New Food</button></a>


        <form action="MainController">
            Search<input type="text" name="search" value="<%=search%>"/>
            <input type="submit" name="action" value="Search"/>
            </form>
            <!--bonus search-->
            <form action="MainController">
            Search<input type="text" name="search" value="<%=search%>"/>  
            <select name="status">
                <option value="all">All</option>
                <option value="active">Active</option>
                <option value="deleted">Deleted</option>
            </select>
                <input type="submit" name="action" value="Search by status"/>
            </form>
            <%
                List<FoodDTO> list = (List<FoodDTO>) request.getAttribute("LIST_FOOD");
                if (list == null) {
                    FoodDAO dao = new FoodDAO();
                    list = dao.getList("");
                }
            %>  
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Cooking Time</th>    
                        <th>Create Date</th>                       
                        <th>Update</th>
                        <th>Delete</th>

                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 1;
                        for (FoodDTO food : list) {
                    %>
                    <tr>
                <form action="MainController">
                    <td><%= count++%></td>
                    <td><input type="text" name="ID"  value="<%= food.getID()%>" readonly=""/></td>
                    <td><input type="text" name="Name"  value="<%= food.getName()%>" required=""/></td>
                    <td><input type="text" name="Description"  value="<%= food.getDescription()%>" required=""/></td>
                    <td><input type="num" name="Price"  value="<%= food.getPrice()%>" required=""/></td>
                    <td><input type="num" name="CookingTime"  value="<%= food.getCookingTime()%>" required=""/></td>                   
                    <td><input type="text" name="CreateDate"  value="<%= food.getCreateDate()%>" required=""/></td>
                    <input type="hidden" name="isDelete" value="<%= food.getIsDelete()%>" readonly=""/>
                    <td>
                        <form action="MainController">
                            <input type="submit" name="action" value="Update"/>
                            <input type="hidden" name="ID" value="<%= food.getID()%>"/>
                            <input type="hidden" name="search" value="<%=search%>"/>
                        </form>

                    </td>
                    <td>
                        <form action="MainController">
                            <input type="submit" name="action" value="Delete"/>
                            <input type="hidden" name="search" value="<%=search%>"/>  
                        </form>
                    </td>
                    
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
        
    </body>
</html>
