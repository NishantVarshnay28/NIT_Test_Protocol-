/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Test;

/**
 *
 * @author Dell
 */
@WebServlet(name = "SaveAnswer", urlPatterns = {"/saveAnswer"})
public class SaveAnswer extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session =request.getSession(false);
            Test t=(Test)session.getAttribute("testDetail");
            String endTime=String.valueOf(t.getEndTime());
            java.util.Date d = new java.util.Date();
            java.sql.Time sqlTime = new java.sql.Time(d.getTime());
            String pTime=String.valueOf(sqlTime);
            int cmp=endTime.compareTo(pTime);
            System.out.println("comparision::"+cmp);
            if(cmp>=0){
            int answerList[]= (int[])session.getAttribute("answerList");
            int qInd= (int)session.getAttribute("qInd");
            for(int a: answerList)
              System.out.println("Answer: "+ a );
            try
            {
                int ans=Integer.parseInt(request.getParameter("ans"));
                answerList[qInd]=ans;
            }
            catch(Exception e)
            {
                System.out.println("No response Founded");
                answerList[qInd]=0;
            }
            System.out.println("ho gya kam");
            RequestDispatcher rd=request.getRequestDispatcher("/facultyViews/question.jsp?quesInd="+qInd);
            rd.forward(request,response);
            }
            else
            {
                   RequestDispatcher rd=request.getRequestDispatcher("/endTest");
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
