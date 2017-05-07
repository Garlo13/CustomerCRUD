<%@page import="repeticioncustomer.entity.Customer"%>
<%@page import="repeticioncustomer.entity.MicroMarket"%>
<%@page import="repeticioncustomer.entity.DiscountCode"%>
<%@page import="java.util.List"%>
<%-- 
    Document   : crearEditarCustomer
    Created on : 07-may-2017, 12:05:24
    Author     : Adrián
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<DiscountCode> discountsCodes = (List<DiscountCode>) request.getAttribute("discountsCodes");
    List<MicroMarket> microMarkets = (List<MicroMarket>) request.getAttribute("microMarkets");
    Customer customer = (Customer) request.getAttribute("customer");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2> <%= (customer != null) ? "Datos del Cliente con id " + customer.getCustomerId() : "Rellene datos de nuevo usuario"%></h2>
        <form action="ServletActualizarCrearCustomer" method="post">
            <%
                if (customer != null) {
            %>     
            <input type="hidden" name="customerId" value="<%=customer.getCustomerId()%>">
            <%
                }
            %>
            Nombre: <input type="text" name="nombre" value="<%=(customer != null) ? customer.getName() : ""%>">
            <br/>
            Domicilio: <input type="text" name="domicilio" value="<%=(customer != null) ? customer.getAddressline1() : ""%>">
            <br/>
            Ciudad: <input type="text" name="ciudad" value="<%=(customer != null) ? customer.getCity() : ""%>">
            <br/>
            Estado <input type="text" name="estado" value="<%=(customer != null) ? customer.getState() : ""%>">
            <br/>
            Email <input type="text" name="email" value="<%=(customer != null) ? customer.getEmail() : ""%>">
            <br/> 
            Supermercado: <select name="supermercadoZip">
                <%
                    if (customer != null) {
                        for (MicroMarket microMarket : microMarkets) {

                %>
                <option <%=(microMarket.getZipCode().equals(customer.getZip().getZipCode())) ? "selected" : ""%> value="<%=microMarket.getZipCode()%>"><%=microMarket.getZipCode()%></option>
                <%
                    }
                } else {
                    for (MicroMarket microMarket : microMarkets) {
                %>
                <option value="<%=microMarket.getZipCode()%>"><%=microMarket.getZipCode()%></option>
                <%
                        }
                    }
                %>
            </select>
            <br/>
            Descuento: <select name="descuento">
                <%
                    if (customer != null) {
                        for (DiscountCode discountCode : discountsCodes) {

                %>
                <option <%=(discountCode.getDiscountCode().equals(customer.getDiscountCode().getDiscountCode())) ? "selected" : ""%> value="<%=discountCode.getDiscountCode()%>"><%=discountCode.getRate()%></option>
                <%
                    }
                } else {
                    for (DiscountCode discountCode : discountsCodes) {
                %>
                <option value="<%=discountCode.getDiscountCode()%>"><%=discountCode.getRate()%></option>
                <%
                        }
                    }
                %>
            </select>
            <br/>
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
