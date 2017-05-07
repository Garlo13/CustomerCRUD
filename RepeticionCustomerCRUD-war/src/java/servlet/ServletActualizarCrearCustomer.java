/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repeticioncustomer.ejb.CustomerFacade;
import repeticioncustomer.ejb.DiscountCodeFacade;
import repeticioncustomer.ejb.MicroMarketFacade;
import repeticioncustomer.entity.Customer;
import repeticioncustomer.entity.DiscountCode;
import repeticioncustomer.entity.MicroMarket;

/**
 *
 * @author Adrián
 */
@WebServlet(name = "ServletActualizarCrearCustomer", urlPatterns = {"/ServletActualizarCrearCustomer"})
public class ServletActualizarCrearCustomer extends HttpServlet {

    @EJB
    private MicroMarketFacade microMarketFacade;

    @EJB
    private DiscountCodeFacade discountCodeFacade;

    @EJB
    private CustomerFacade customerFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String customerId = request.getParameter("customerId");

        String nombre = request.getParameter("nombre");
        String domicilio = request.getParameter("domicilio");
        String ciudad = request.getParameter("ciudad");
        String estado = request.getParameter("estado");
        String email = request.getParameter("email");
        String supermercadoZip = request.getParameter("supermercadoZip");
        String codigoDescuento = request.getParameter("descuento");

        MicroMarket microMarket = this.microMarketFacade.find(supermercadoZip);
        DiscountCode dicountCode = this.discountCodeFacade.find(codigoDescuento);
        Customer customer = null;
        if (customerId != null) { //editar Customer
            customer = this.customerFacade.find(Integer.parseInt(customerId));
        } else {
            int nextId = this.customerFacade.nextId();
            customer = new Customer(nextId);
        }
        customer.setName(nombre);
        customer.setAddressline1(domicilio);
        customer.setCity(ciudad);
        customer.setState(estado);
        customer.setEmail(email);
        customer.setZip(microMarket);
        customer.setDiscountCode(dicountCode);
        
        if (customerId != null){
            this.customerFacade.edit(customer);
        } else {
            this.customerFacade.create(customer);
        }
        
        response.sendRedirect("ServletListarCustomer");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
