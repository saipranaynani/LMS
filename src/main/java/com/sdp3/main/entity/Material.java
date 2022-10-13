package com.sdp3.main.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Material {
    @Id
	@GeneratedValue
    private int id;
    
    private long courseCode;
    private String courseName;

    private Date uploadDate;
    private String fileName;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getCourseCode() {
        return courseCode;
    }
    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Date getUploadDate() {
        return uploadDate;
    }
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    

}
