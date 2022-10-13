package com.sdp3.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id;
    private String fullname;
    private String email;
    private String universityname;
    private String address;
    private long contactnumber;
    private int yearsofexpe;
    private String designation;
    private Date requesteddate;
    private boolean verified;
    
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFullName() {
        return fullname;
    }
    public void setFullName(String fullName) {
        this.fullname = fullName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUniversityName() {
        return universityname;
    }
    public void setUniversityName(String universityname) {
        this.universityname = universityname;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public long getContactNumber() {
        return contactnumber;
    }
    public void setContactNumber(long contactnumber) {
        this.contactnumber = contactnumber;
    }
    public int getYearsOfExpe() {
        return yearsofexpe;
    }
    public void setYearsOfExpe(int yearsofexpe) {
        this.yearsofexpe = yearsofexpe;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public Date getRequestedDate() {
        return requesteddate;
    }
    public void setRequestedDate(Date requesteddate) {
        this.requesteddate = requesteddate;
    }
    public Request(int id, String fullname, String email, String universityname, String address, long contactnumber,
            int yearsofexpe, String designation,Date requesteddate,boolean verified) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.universityname = universityname;
        this.address = address;
        this.contactnumber = contactnumber;
        this.yearsofexpe = yearsofexpe;
        this.designation = designation;
        this.requesteddate=requesteddate;
        this.verified=verified;
    }
    public Request() {
        super();
    }

    
    
}
