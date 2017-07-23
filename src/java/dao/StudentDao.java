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
import models.Student;
import models.Test;

/**
 *
 * @author Dell
 */
public class StudentDao {
    private Connection con;
    public StudentDao(Connection con)
    {
        this.con=con;
    }
    public boolean addStudent(Student s)
    {
        Calendar cal=Calendar.getInstance();
        Date now=cal.getTime();
        Timestamp ts=new Timestamp(now.getTime());
        if(con!=null){
        try{
        PreparedStatement ps=con.prepareStatement("insert into student(name,rollNo,branchName,batch,emailId,creationTime,password,year) VALUES(?,?,?,?,?,?,?,?)");
        ps.setString(1, s.getName());
        ps.setInt(2, s.getRoll());
        ps.setString(3, s.getBranch());
        ps.setInt(4, s.getBatch());
        ps.setString(5, s.getEmail());
        ps.setTimestamp(6, ts);
        ps.setString(7, s.getPassword());
        ps.setInt(8,(2018-s.getBatch()) );
        int r=ps.executeUpdate();
        System.out.println("error1");
        if(r>0)
            return true;
        }
        catch(Exception e)
        {
            System.out.println("Error in page"+e);
        }
        }
        return false;
    }
    public Student verify(int id,String Password)
    {
      Student s=null;
      try{
      PreparedStatement ps=con.prepareStatement("select * from student where rollNo=?");
      ps.setInt(1,id);
      ResultSet rs=ps.executeQuery();
      if(rs.next()&&rs.getString(7).equals(Password)){
          return new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(11));
        
      }
      }
      catch(Exception ex)
      {
          System.out.println("Error in sql "+ex); 
      }
        return s;
    }
    
    /*
    upcoming
    subject date time
    
    
    */
    
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
     public int changePassword(int id,String password)
     {
       try
      {
          PreparedStatement ps=con.prepareStatement("update student set password=? where rollNo=?");
          ps.setString(1,password);
          ps.setInt(2,id);
          int a=ps.executeUpdate();
             return a;
       }
      catch(Exception e)
      {
          System.out.println("Exception is "+e);
      }
      return 0;
    }
    public boolean update(int sId,String name,int rollNo,String branch,int batch,String emailId)
    {
        try
        {
            System.out.println(sId);
            PreparedStatement ps=con.prepareStatement("update student set name=?,rollNo=?,branchName=?,batch=?,emailId=?,year=? where sId=?");
            ps.setString(1, name);
            ps.setInt(2, rollNo);
            ps.setString(3,branch);
            ps.setInt(4, batch);
            ps.setString(5,emailId);
            ps.setInt(6, (2018-batch));
            ps.setInt(7, sId);
            int r=ps.executeUpdate();
            if(r>0)
                return true;
        }
        catch(Exception e)
        {
            System.out.println("Error in updating"+e);
        }
        return false;
    }
    public Student getStudent(int sId)
    {
        Student s=null;
        try{
      PreparedStatement ps=con.prepareStatement("select * from student where sId=?");
      ps.setInt(1,sId);
      ResultSet rs=ps.executeQuery();
      if(rs.next()){
          return new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getInt(8),rs.getInt(11));
        
      }
      }
      catch(Exception ex)
      {
          System.out.println("Error in sql "+ex); 
      } 
        return s;
    }
   
    
    public ArrayList<Test> getUpcomingTest(ArrayList<String> testCodes){
       
        
        System.out.println(testCodes);
        ArrayList<Test> upcomingTest = new ArrayList<Test>();
        
        try{
            for(String tc: testCodes){
            PreparedStatement  stmt= con.prepareStatement("select * from test where testCode = ? and (schDate>curdate() or (schDate= curdate() and endTime>=curtime()))");
            stmt.setString(1,tc);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
            upcomingTest.add(new Test(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getDate(7),rs.getTime(8),rs.getTime(9)));
           }
            
            }
            if(upcomingTest.size()==0){
                System.out.println("Nothing is there .....");
                return null;
            }
            else{
                System.out.println("Everything is there .....");
                System.out.println(upcomingTest.size());
                return upcomingTest;
            }
        }
        catch(Exception ex){
            System.out.println("Exception found while getting Upcoming Test: "+ex);
        }
        return null;
    }
}
