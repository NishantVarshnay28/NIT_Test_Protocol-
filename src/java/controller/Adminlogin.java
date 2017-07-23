package controller;

import dao.AdminDao;
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
import models.Admin;

@WebServlet(name = "Adminlogin", urlPatterns = {"/Adminlogin"})
public class Adminlogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Adminlogin</title>");            
            out.println("</head>");
            out.println("<body>");
            String id=request.getParameter("id");
            String password=request.getParameter("password");
            Admin ad=new Admin(id,password);
            ServletContext application=request.getServletContext();
            Connection con=(Connection)application.getAttribute("con");
            AdminDao a=new AdminDao(con);
            Admin adminDetail=a.VerifyDetails(ad);
            if(adminDetail!=null)
            {
                System.out.println(adminDetail.getName());
                HttpSession session = request.getSession();
                session.setAttribute("user",adminDetail);
                RequestDispatcher rd = request.getRequestDispatcher("views/adminProfile.jsp");
                rd.forward(request,response);
            }
            else
            {
                request.setAttribute("msg","Wrong Id or Password!!!!!");
                RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
                rd.forward(request,response);
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
