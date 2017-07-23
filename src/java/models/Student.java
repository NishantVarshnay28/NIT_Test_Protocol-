package models;
public class Student {
    private String name;
    private int roll;
    private String email;
    private int batch;
    private String branch;
    private int rank;
    private String password;
    private int sId;
    private int year;
    public Student(String name, int roll, String email, int batch, String branch, String password) {
        this.name = name;
        this.roll = roll;
        this.email = email;
        this.batch = batch;
        this.branch = branch;
        this.rank = rank;
        this.password = password;
    }
   public Student(int sId,String name,int roll,String branch,int batch,String email,String password,int rank,int year)
   {
       this.sId=sId;
       this.name=name;
       this.roll=roll;
       this.branch=branch;
       this.batch=batch;
       this.email=email;
       this.password=password;
       this.rank=rank;
       this.year=year;
   }
    public String getName() {
        return name;
    }

    public int getRoll() {
        return roll;
    }

    public String getEmail() {
        return email;
    }

    public int getBatch() {
        return batch;
    }

    public String getBranch() {
        return branch;
    }

    public int getRank() {
        return rank;
    }

    public int getsId() {
        return sId;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student() {
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
