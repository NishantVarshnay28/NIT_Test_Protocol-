/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Dell
 */
public class Test {
    int testId;
    String subject;
    String testcode;
    int totalMarks;
    int noOfQue;
    java.sql.Date schDate;
    java.sql.Time schTime;
    java.sql.Time endTime;

    public Test(int testId, String subject, String testcode, int totalMarks, int noOfQue, Date schDate, Time schTime, Time endTime) {
        this.testId = testId;
        this.subject = subject;
        this.testcode = testcode;
        this.totalMarks = totalMarks;
        this.noOfQue = noOfQue;
        this.schDate = schDate;
        this.schTime = schTime;
        this.endTime = endTime;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTestcode() {
        return testcode;
    }

    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getNoOfQue() {
        return noOfQue;
    }

    public void setNoOfQue(int noOfQue) {
        this.noOfQue = noOfQue;
    }

    public Date getSchDate() {
        return schDate;
    }

    public void setSchDate(Date schDate) {
        this.schDate = schDate;
    }

    public Time getSchTime() {
        return schTime;
    }

    public void setSchTime(Time schTime) {
        this.schTime = schTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
    
}
