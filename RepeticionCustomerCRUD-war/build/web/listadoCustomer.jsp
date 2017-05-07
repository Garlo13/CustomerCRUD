<%-- 
    Document   : listadoCustomer
    Created on : 07-may-2017, 11:38:26
    Author     : Adrián
--%>

<%@page import="repeticioncustomer.entity.MicroMarket"%>
<%@page import="repeticioncustomer.entity.Customer"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
    List<MicroMarket> microMarkets = (List<MicroMarket>) request.getAttribute("microMarkets");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ServletListarCustomer" method="post">
            Buscar: <input type="text" name="buscador"> <input type="submit" value="Buscar">
            <br/>
            Zip: 
            <br/>
                <%
                    for (MicroMarket market : microMarkets) {
                %>
                    <input type="checkbox" name="zipCode" value="<%= market.getZipCode()%>"><%= market.getZipCode()%>
                <%
                    }
                %>
        </form>
        <br/>
        <table border="1">
            <tr>
                <th>CUSTOMER ID</th>
                <th>NAME</th>
            </tr>
            <%
                for (Customer customer : customers) {
            %>
            <tr>
                <td><%=customer.getCustomerId()%></td>
                <td><%=customer.getName()%></td>
                <td><a href="ServletEditarCrearCustomer?customerId=<%=customer.getCustomerId()%>">Editar</a></td>
                <td><a href="ServletBorrarCustomer?customerId=<%=customer.getCustomerId()%>">Borrar</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <br/>
        <a href="ServletEditarCrearCustomer">Crear Customer</a>
    </body>
</html>
