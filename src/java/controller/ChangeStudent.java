/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.StudentDao;
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
import javax.servlet.http.HttpSession;
import models.Student;

/**
 *
 * @author Dell
 */
@WebServlet(name = "ChangeStudent", urlPatterns = {"/changeStudent"})
public class ChangeStudent extends HttpServlet {

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
            out.println("<title>Servlet ChangeStudent</title>");            
            out.println("</head>");
            out.println("<body>");
            String opass=request.getParameter("old");
            String pass=request.getParameter("password");
            String cpass=request.getParameter("cpassword");
            if(!pass.equals(cpass))
            {
                request.setAttribute("nme","Password and confirm password doesn't match");
            }
            else
            {
               ServletContext sc=request.getServletContext();
               Connection con=(Connection)sc.getAttribute("con");
               StudentDao sd=new StudentDao(con);
               HttpSession session=request.getSession(false);
               Student s=(Student)session.getAttribute("user");
               if(s.getPassword().equals(opass))
               {
                   sd.changePassword(s.getRoll(),pass);
                   request.setAttribute("success","Password Changed!!!");
                   session.setAttribute("user",sd.getStudent(s.getsId()));
               }
               else
               {
                   request.setAttribute("success","Old Password Doesn't Match!!!");
               }
            }
            RequestDispatcher rd = request.getRequestDispatcher("facultyViews/changePasswordStudent.jsp");
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
