/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javax.servlet.http.HttpSession;
import models.Faculty;
import static org.apache.coyote.http11.Constants.a;

/**
 *
 * @author Dell
 */
@WebServlet(name = "changeFaculty", urlPatterns = {"/changeFaculty"})
public class changeFaculty extends HttpServlet {

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
            out.println("<title>Servlet changeFaculty</title>");            
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
               System.out.println("nulluikl");
               ServletContext sc=request.getServletContext();
               Connection con=(Connection)sc.getAttribute("con");
               FacultyDao fd=new FacultyDao(con);
               HttpSession session=request.getSession(false);
               Faculty f=(Faculty)session.getAttribute("user");
                if(f==null)
                    System.out.println("null");
                System.out.println(f);
                System.out.println(f.getPassword());
               if(f.getPassword().equals(opass))
               {
                   fd.changePassword(f.getId(),pass);
                   request.setAttribute("success","Password Changed!!!");
               }
               else
               {
                   request.setAttribute("success","Old Password Doesn't Match!!!");
               }
            }
            RequestDispatcher rd = request.getRequestDispatcher("facultyViews/changePasswordFaculty.jsp");
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
