/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ServletEditarCrearCustomer", urlPatterns = {"/ServletEditarCrearCustomer"})
public class ServletEditarCrearCustomer extends HttpServlet {

    @EJB
    private CustomerFacade customerFacade;

    @EJB
    private DiscountCodeFacade discountCodeFacade;

    @EJB
    private MicroMarketFacade microMarketFacade;

    
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
        
        List<DiscountCode> discountsCodes = this.discountCodeFacade.findAll();
        List<MicroMarket> microMarkets = this.microMarketFacade.findAll();
        
        request.setAttribute("discountsCodes", discountsCodes);
        request.setAttribute("microMarkets", microMarkets);
        
        String customerId = request.getParameter("customerId");
        
        if (customerId != null){ //Editar usuario
            Customer customer = this.customerFacade.find(Integer.parseInt(customerId));
            request.setAttribute("customer", customer);
        }
        
        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/crearEditarCustomer.jsp");
        rd.forward(request, response);
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
