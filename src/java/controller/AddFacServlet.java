package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.FacultyDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Faculty;

/**
 *
 * @author Dell
 */
@WebServlet(urlPatterns = {"/AddFacServlet"})
public class AddFacServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddFacServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            String name=request.getParameter("name");
            int id=Integer.parseInt(request.getParameter("roll"));
            String department=request.getParameter("department");
            String emailId=request.getParameter("emailId");
            String password=request.getParameter("password");
            String cpassword=request.getParameter("cpassword");
            if(password.equals(cpassword))
            {
                ServletContext application=request.getServletContext();
                Connection con=(Connection)application.getAttribute("con");
                Faculty fac=new Faculty(name,id,department,emailId,password);
                FacultyDao fd=new FacultyDao(con);
                if(fd.addFaculty(fac))
                    request.setAttribute("success","Added Successfully!!!!!!");
            }
            else
            {
                request.setAttribute("nme","Password and confirm password doesn't match");
            }
            RequestDispatcher rd = request.getRequestDispatcher("views/addFaculty.jsp");
            rd.forward(request,response);
            out.println("</body>");
            out.println("</html>");
        }
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
