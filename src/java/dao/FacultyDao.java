/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import models.Faculty;

/**
 *
 * @author Dell
 */
public class FacultyDao {
    private Connection con;
    public FacultyDao(Connection con)
    {
        this.con=con;
    }
    public boolean addFaculty(Faculty fac)
    {
        Calendar cal=Calendar.getInstance();
        Date now=cal.getTime();
        Timestamp ts=new Timestamp(now.getTime());
        try
        {
           PreparedStatement ps=con.prepareStatement("insert into teacher(name,empId,department,emailId,creationTime,password) values(?,?,?,?,?,?)");
           ps.setString(1,fac.getName());
           ps.setInt(2,fac.getId());
           ps.setString(3,fac.getDepartment());
           ps.setString(4,fac.getEmailId());
           ps.setTimestamp(5, ts);
           ps.setString(6,fac.getPassword());
           int r=ps.executeUpdate();
           if(r>0)
               return true;
        }
        catch(Exception e)
        {
            System.out.println("Exception in FacultyDao "+e);
        }
        return false;
    }
    public Faculty verify(int id,String Password)
    {
        Faculty f=null;
        try{
      PreparedStatement ps=con.prepareStatement("Select * from teacher where empId=?");
      ps.setInt(1,id);
      ResultSet rs=ps.executeQuery();
      if(rs.next()&&rs.getString(7).equals(Password))
        return new Faculty(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(7));
      }
      catch(Exception ex)
      {
          System.out.println("Error in sql "+ex); 
      }
        return f;
    }
     public int changePassword(int empId,String password)
     {
       try
      {
          PreparedStatement ps=con.prepareStatement("update teacher set password=? where empId=?");
          ps.setString(1,password);
          ps.setInt(2,empId);
          int a=ps.executeUpdate();
             return a;
       }
      catch(Exception e)
      {
          System.out.println("Exception is "+e);
      }
      return 0;
  }
   
     
      public ArrayList<String> getTestCode(String branch,int batch){
      ArrayList<String> codes=new ArrayList();
      
      try{
          PreparedStatement stmt= con.prepareStatement("select testCode from test where testId in (select testId from bbTest where bbId=(select bbId from branchbatch where branch=? and year=?))");
          stmt.setString(1,branch);
          stmt.setInt(2, batch);
          ResultSet rs=stmt.executeQuery();
          
          while(rs.next())
          {
              System.out.println(rs.getString(1));
              codes.add(rs.getString(1));
          }
      }
      catch(Exception ex){
          System.out.println("Exception found in getting testcodes "+ ex);
      }
      return codes;
}
}