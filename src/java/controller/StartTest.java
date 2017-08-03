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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Question;
import models.Student;
import models.Test;

/**
 *
 * @author Dell
 */
@WebServlet(name = "StartTest", urlPatterns = {"/startTest"})
public class StartTest extends HttpServlet {

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
            out.println("<title>Servlet StartTest</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StartTest at " + request.getContextPath() + "</h1>");
            int testId=Integer.parseInt(request.getParameter("testId"));
            Connection con = (Connection)getServletContext().getAttribute("con");
            TestDao tdao = new TestDao(con);
            ArrayList<Question> questionList= tdao.getQuestions(testId);
            HttpSession session=request.getSession(false);
            session.setAttribute("questionList",questionList);
            Test testDetail=tdao.getActiveTestDetail(testId);
            int answerList[]= new int[questionList.size()];
            session.setAttribute("answerList", answerList);
            session.setAttribute("testDetail", testDetail);
            
            //Converting endDate into string for counter
 
       
           
           String eTime = String.valueOf(testDetail.getSchDate());
           eTime=eTime.concat(" ");
           eTime=eTime.concat(String.valueOf(testDetail.getEndTime()));
           try{
           DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
           eTime=String.valueOf(outputFormat.format(inputFormat.parse(eTime)));
           session.setAttribute("eTime",eTime);
           }
           catch(Exception e)
           {
               System.out.println("exception in counter");
           }
            Student s=(Student)session.getAttribute("user");
            int valid=tdao.checkValidity(s.getsId(),testId);
            if(valid>0){
                RequestDispatcher rd=request.getRequestDispatcher("endTest");
                rd.forward(request, response);
            }
            else
            {    
            RequestDispatcher rd=request.getRequestDispatcher("facultyViews/question.jsp?quesInd=0");
            rd.forward(request, response);
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
