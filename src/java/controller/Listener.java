package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Dell
 */
public class  Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent ev)
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sms","root","8881092588");
          ServletContext sc=ev.getServletContext();
          sc.setAttribute("con",con);
        }
        catch(Exception e)
        {
           System.out.println("Excetion in listener"+e);
        }
   }
    @Override
    public void contextDestroyed(ServletContextEvent ev)
    {
        
    }
}
