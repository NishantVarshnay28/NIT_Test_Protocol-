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
import models.Student;

/**
 *
 * @author Dell
 */
@WebServlet(name = "AddStudent", urlPatterns = {"/addStudent"})
public class AddStudent extends HttpServlet {

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
            out.println("<title>Servlet AddStudent</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
            String name=request.getParameter("name");
            int roll=Integer.parseInt(request.getParameter("roll"));
            String branch=request.getParameter("branch");
            int batch=Integer.parseInt(request.getParameter("batch"));
            String emailId=request.getParameter("emailId");
            String password=request.getParameter("password");
            String cpassword=request.getParameter("cpassword");
            if(password.equals(cpassword))
            {
                Student s=new Student(name,roll,emailId,batch,branch,password);
                ServletContext sc=request.getServletContext();
                Connection con=(Connection)sc.getAttribute("con");
                StudentDao sd=new StudentDao(con);
               if( sd.addStudent(s))
               {
                   request.setAttribute("msg","You are added successfully!!!");
               }
               else
                   request.setAttribute("msg","Fields can't be null");
            }
            else
            {
                request.setAttribute("msg","Password And Confirm password does not Match");
            }
            RequestDispatcher rd = request.getRequestDispatcher("studentSignIn.jsp");
            rd.forward(request,response);
            }
            catch(Exception e)
            {
                out.println("Field can't be null");
            }
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
