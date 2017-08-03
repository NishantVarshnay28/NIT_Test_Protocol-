package controller;

import dao.TestDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Student;
import models.Test;

@WebServlet(name = "EndTest", urlPatterns = {"/endTest"})
public class EndTest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<meta http-equiv=\"refresh\" content=\"3;url=http://localhost:8084/SMS/facultyViews/StudentProfile.jsp\" />");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EndTest</title>");            
            out.println("</head>");
            out.println("<body>");
            HttpSession session =request.getSession(false);
            int answerList[]= (int[])session.getAttribute("answerList");
            Connection con=(Connection)getServletContext().getAttribute("con");
            Test t=(Test)session.getAttribute("testDetail");
            Student s=(Student)session.getAttribute("user");
            TestDao td=new TestDao(con);
            int result=td.getMarks(answerList,t.getTestId(),s.getsId());
            if(result>=0)
            out.println("<h1>You scored "+result+"</h1>");
            else
                out.println("<h1>You have already given the test</h1>");
            out.println("redirecting in 3 sec.....");
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
