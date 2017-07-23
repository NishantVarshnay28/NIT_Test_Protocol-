/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.FacultyDao;
import dao.StudentDao;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Faculty;
import models.Student;
import models.Test;

/**
 *
 * @author Dell
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
           
            String  role=request.getParameter("role");
            int id=Integer.parseInt(request.getParameter("id"));
            String password=request.getParameter("password");
            ServletContext application=request.getServletContext();
            Connection con=(Connection)application.getAttribute("con");
            
            
            
            if(role.equals("student"))
            {
                
                StudentDao sd=new StudentDao(con);
                Student studentDetail=sd.verify(id,password);
                if(studentDetail!=null)
                {
                
                HttpSession session = request.getSession();
                session.setAttribute("user",studentDetail);
                ArrayList<String> codes= sd.getTestCode(studentDetail.getBranch(),studentDetail.getYear());
                if(codes!=null)
                 session.setAttribute("testCode",codes);
                ArrayList<Test> upcomingTest=sd.getUpcomingTest(codes);
                session.setAttribute("upcomingTest",upcomingTest);
                RequestDispatcher rd = request.getRequestDispatcher("facultyViews/StudentProfile.jsp");
                rd.forward(request,response);
            }
                 else{
                request.setAttribute("msg","Invalid Details");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
            }
            }
            else if(role.equals("faculty"))
            {
                System.out.println("faculty");
                FacultyDao fd=new FacultyDao(con);
                Faculty facultyDetail=fd.verify(id,password);
                if(facultyDetail!=null)
                {
                HttpSession session = request.getSession();
                session.setAttribute("user",facultyDetail);
                RequestDispatcher rd = request.getRequestDispatcher("facultyViews/facultyProfile.jsp");
                rd.forward(request,response);
                }
                 else{
                request.setAttribute("msg","Invalid Details");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
            }
            }
            else{
                request.setAttribute("msg","Invalid Details");
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
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
