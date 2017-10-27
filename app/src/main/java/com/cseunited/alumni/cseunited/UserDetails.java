package com.cseunited.alumni.cseunited;

import android.util.Log;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Sachin on 10/8/2017.
 * Modified by Debanik on 27/10/2017.
 * To store the details of an User.
 */

class UserDetails {
    private int id = 0;
    private String name = null;
    private int position = 0;
    private String username = null;
    private String email = null;
    private String password = null;

    private Date dateOfBirth = null;
    private int gradYear = 0;
    private int mobileNumber = 0;
    private byte gender = 0;
    private String qualification = null;
    private String presentEmployer = null;
    private String currentPosition = null;

    /**
     * Getters and Setters.
     */
    public int getId(){return id;}
    public String getName(){return name;}
    public int getPosition(){return position;}
    public String getUsername(){return username;}
    public String getEmail(){return email;}
    public Date getDateOfBirth(){return dateOfBirth;}
    public String getPassword(){
        return new HashMaker("SHA-256").getHash(password);
    }
    public int getMobileNumber(){return mobileNumber;}
    public byte getGender(){return gender;}
    public  int getGradYear(){return gradYear;}
    public String getQualification(){return qualification;}
    public String getPresentEmployer(){return presentEmployer;}
    public String getCurrentPosition(){return currentPosition;}

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPosition(int position){this.position = position;}
    public void setUsername(String username){this.username = username;}
    public void setEmail(String email){this.email = email;}
    public void setDateOfBirth(Date dateOfBirth){this.dateOfBirth = dateOfBirth;}
    public void setPassword(String password){this.password = password;}
    public void setMobileNumber(int mobileNumber){this.mobileNumber = mobileNumber;}
    public void setGender(byte gender){this.gender = gender;}
    public void setGradYear(int gradYear){this.gradYear = gradYear;}
    public void setQualification(String qualification){this.qualification = qualification;}
    public void setPresentEmployer(String presentEmployer){this.presentEmployer = presentEmployer;}
    public void setCurrentPosition(String currentPosition){this.currentPosition = currentPosition;}
}