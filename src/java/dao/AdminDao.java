/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.Admin;

/**
 *
 * @author Dell
 */
public class AdminDao {
    private Connection con;
  public AdminDao(Connection con)
  {
      this.con=con;
  }
  public Admin VerifyDetails(Admin ad)
  {
      Admin a=null;
      try{
      PreparedStatement ps=con.prepareStatement("Select * from admin where adminId=?");
      ps.setString(1,ad.getAdminId());
      ResultSet rs=ps.executeQuery();
      if(rs.next()&&rs.getString(3).equals(ad.getPassword()))
        return new Admin(rs.getString(2),rs.getString(5),rs.getString(3));
      }
      catch(Exception ex)
      {
          System.out.println("Error in sql "+ex); 
      }
      return a;
  }
  public boolean addAdmin(Admin ad)
  {
      Calendar cal=Calendar.getInstance();
        Date now=cal.getTime();
        Timestamp ts=new Timestamp(now.getTime());
        try
        {
           PreparedStatement ps=con.prepareStatement("insert into admin(name,adminId,creationTime,password) values(?,?,?,?)");
           ps.setString(1,ad.getName());
           ps.setString(2,ad.getAdminId());
           ps.setTimestamp(3, ts);
           ps.setString(4,ad.getPassword());
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
  public int changePassword(String adminId,String password)
  {
       try
      {
          PreparedStatement ps=con.prepareStatement("update admin set password=? where adminId=?");
          ps.setString(1,password);
          ps.setString(2,adminId);
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
      ArrayList<String> codes=new ArrayList<String>();
      
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
  public ArrayList<String []> getRecord(String testCode)
  {
    ArrayList<String []> record=new ArrayList<>(); 
      try{
          PreparedStatement stmt= con.prepareStatement("select rollNo,name,obtainedMarks,totalMarks,schDate,subject from student join studenttest on student.sId=studenttest.sId join test on studenttest.testId= test.testId where testCode=?");
          stmt.setString(1,testCode);
          ResultSet rs=stmt.executeQuery();
          if(rs.next()){
          String[] s=new String[3];
          s[0]=String.valueOf(rs.getInt(4));
          s[1]=String.valueOf(rs.getDate(5));
          s[2]=rs.getString(6);
          record.add(s);
          s=new String[3];
          s[0]=String.valueOf(rs.getInt(1));
          s[1]=rs.getString(2);
          s[2]=String.valueOf(rs.getInt(3));
          record.add(s);
          while(rs.next())
          {
              s=new String[3];
              s[0]=String.valueOf(rs.getInt(1));
              s[1]=rs.getString(2);
              s[2]=String.valueOf(rs.getInt(3));
              record.add(s);
          }
      }
      else
          record=null;    
      }
      catch(Exception ex){
          System.out.println("Exception found in getting testcodes "+ ex);
      }
    return record;
  }
  
}
