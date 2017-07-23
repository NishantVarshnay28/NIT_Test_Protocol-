/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.TestDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Faculty;
import models.Question;

/**
 *
 * @author Dell
 */
@WebServlet(name = "SubmitServlet", urlPatterns = {"/submitServlet"})
public class SubmitServlet extends HttpServlet {

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
            out.println("<title>Servlet SubmitServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            HttpSession session=request.getSession(false);
            String s[]=new String[7];
            s[0]=request.getParameter("question");
            s[1]=request.getParameter("option1");
            s[2]=request.getParameter("option2");
            s[3]=request.getParameter("option3");
            s[4]=request.getParameter("option4");
            s[5]=request.getParameter("answer");
            s[6]=request.getParameter("weightage");
            out.println(s[0]);
            Question q=new Question(s[0],s[1],s[2],s[3],s[4],Integer.parseInt(s[5]),Integer.parseInt(s[6]));
            ArrayList<Question> a=( ArrayList<Question>)session.getAttribute("qlist");
            a.add(q);
            
            String testCode= (String)session.getAttribute("testCode"); 
            int teacherId= ((Faculty)session.getAttribute("user")).gettId(); 
  
            Connection con= (Connection)getServletContext().getAttribute("con");
            TestDao dao = new TestDao(con);
            boolean outcome= dao.saveQuestions(a,testCode,teacherId);
            if(outcome){
                System.out.println("Successfully saved all questions");
            }
            else{
                System.out.println("Problem Problem...");
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
