/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import models.Question;

/**
 *
 * @author Dell
 */
public class TestDao {
     private Connection con;
     public TestDao(Connection con){
         this.con=con;
     }
     
    public ArrayList<String[]> getSubjects(String branch,int batch){
        ArrayList<String[]> subjectList= new ArrayList<String[]>();
        
        try{
            PreparedStatement stmt = con.prepareStatement(" select subCode,sub from bbSubject where bbId= (select bbId from branchbatch where branch=? and year=?)");
            stmt.setString(1,branch);
            stmt.setInt(2,batch);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                String s[]= new String[2];
                s[0]=rs.getString(1);
                s[1]= rs.getString(2);
                subjectList.add(s);
                System.out.println("Code: "+s[0]);
                System.out.println("Subject : "+s[1]);
                
            }
        }
        catch(Exception ex){
            System.out.println("Exception found in getSubject() method: "+ ex);
        }
    return subjectList;
    }
   public String addTestDetails(int batch,String branch,String subject,java.sql.Date d,Time t,Time duration,int facultyId)
   {
      Calendar cal=Calendar.getInstance();
      Date now=cal.getTime();
      Timestamp ts=new Timestamp(now.getTime());
      String testCode=branch;
      testCode=testCode.concat(subject);
      try
      {
          PreparedStatement ps=con.prepareStatement("select count(*) from test where subject=?");
          ps.setString(1,subject);
          int r=0;
          ResultSet rs=ps.executeQuery();
          if(rs.next())
              r=rs.getInt(1);
          r++;
          testCode=testCode.concat(Integer.toString(r));
          System.out.println("TestCode : "+ testCode);
          ps=con.prepareStatement("insert into test(subject,testCode,creationTime,schDate,schTime,endTime) values(?,?,?,?,?,?)");
          ps.setString(1,subject);
          ps.setString(2,testCode);
          ps.setTimestamp(3,ts);
          ps.setDate(4,d);
          ps.setTime(5,t);
          ps.setTime(6,duration);
          int success=ps.executeUpdate();
          System.out.println("Before Success");
          if(success>0)
          {
              ps=con.prepareStatement("select testId from test where testCode=?");
              ps.setString(1,testCode);
              rs=ps.executeQuery();
              if(rs.next())
              {
                 int tId=rs.getInt(1);
                 System.out.println("Faculty Id: "+facultyId);
                 System.out.println("Test Id: "+tId);
                 ps=con.prepareStatement("insert into facultytest values(?,?)");
                 ps.setInt(1,facultyId);
                 ps.setInt(2,tId);
                 ps.executeUpdate();
                 
                 ps=con.prepareStatement("select bbId from branchbatch where branch=? and year=?");
                 ps.setString(1,branch);
                 ps.setInt(2,batch);
                 rs = ps.executeQuery();
                 int bbId=0;
                 if(rs.next()){
                     bbId=rs.getInt(1);
                     System.out.println("BranchBatchId: "+bbId);
                 }
                 ps=con.prepareStatement("insert into bbtest values(?,?)");
                 ps.setInt(1,bbId);
                 ps.setInt(2,tId);
                 int re= ps.executeUpdate();
                  System.out.println("All commands have been run "+ re);
               }
          }
      }
      catch(Exception e)
      {
          System.out.println("Error in adding test Detail"+e);
      }
      return testCode;
   } 
   
 
   /*
   
   Test -> no of questions,totalMarks
   TestQuestion->testid,qid
   Question->
   facultyTest -> tid,testId;
   */
   
   
   public boolean saveQuestions(ArrayList<Question> qList, String testCode,int teacherId){
       System.out.println("Test Code"+testCode);
       int nq= qList.size();
       System.out.println("No of Questions: "+nq);
       int totalMarks=0;
       for(Question q: qList)
        totalMarks+= q.getWeightage();
       System.out.println("Total marks: "+totalMarks);
       int testId;
       try{
           PreparedStatement stmt = con.prepareStatement("select testId from test where testCode =?");
           stmt.setString(1,testCode);
           ResultSet rs= stmt.executeQuery();
           
           if(rs.next()){
              testId=rs.getInt(1); 
               System.out.println("Test Id: "+ testId);
               
               //Adding data to test table
               stmt = con.prepareStatement("update  test set totalMarks=? ,noOfQue=?  where testId=? ");
               stmt.setInt(1,totalMarks);
               stmt.setInt(2, nq);
               stmt.setInt(3, testId);
               int success= stmt.executeUpdate();
               
               if(success>0){
                   
                   for(Question ques: qList){
                       stmt = con.prepareStatement("insert into questions values(null,?,?,?,?,?,?,?) ");
                       stmt.setString(1,ques.getQuestion());
                       stmt.setString(2,ques.getOption1());
                       stmt.setString(3,ques.getOption2());
                       stmt.setString(4,ques.getOption3());
                       stmt.setString(5,ques.getOption4());
                       stmt.setInt(6,ques.getAnswer());
                       stmt.setInt(7,ques.getWeightage());
                       
                        success= stmt.executeUpdate();
                         System.out.println("Questiton Add:  "+ success);
                       
                       stmt = con.prepareStatement("insert into questiontest values(last_insert_id(),?) ");
                       stmt.setInt(1, testId);
                       success= stmt.executeUpdate();
                       System.out.println("QuestionTest Add :"+ success);
                       
                   }
               }
               
               
               
           }
         return true;  
       }
       catch(Exception ex){
           System.out.println("Exception found in saveQuestions  : "+ ex);
       }
       return false; 
   }
   
   public ArrayList<Question> getQuestions(int testId){
       ArrayList<Question> questionList = new ArrayList<Question>();
       try{
           PreparedStatement stmt = con.prepareStatement(" select * from questions where qId in (select qId from questiontest where testId=?)");
            stmt.setInt(1,testId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Question q = new Question(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8));
                questionList.add(q);
                }
            if(questionList.size()!=0){
                return questionList;
            }
            
                   }
       catch(Exception ex){
           System.out.println("Exception found while getting Questions : "+ ex);
       }
       return null;
   }
  }
