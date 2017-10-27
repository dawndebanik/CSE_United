package com.cseunited.alumni.cseunited;


import java.util.Date;

/**
 * User base class for a generic user on the site.
 * Created by Debanik on 15-09-2017.
 */

abstract class User {
    UserDetails details;
    /**
     * Constructor for the User base class.
     *
     * @param name     Name of the user
     * @param password Password of the user
     * @param userName Username of the user
     * @param email Email id of the user
     * @param userID   user ID of the user
     * @param position position of the user as defined in Constants.java
     */
    User(String name, String userName, String email, String password, int userID, int position){
        details.setName(name);
        details.setUsername(userName);
        details.setEmail(email);
        details.setPassword(password);
        details.setId(userID);
        details.setPosition(position);
    }

    /**
     * Getters and Setters.
     */

    String getName() {
        return details.getName();
    }
    String getUserName(){return details.getUsername();}
    String getEmail(){return details.getEmail();}
    Date getDateofBirth(){return details.getDateOfBirth();}
    String getPassword(){return details.getPassword();}
    int getUserId(){return details.getId();}
    int getPosition(){return details.getPosition();}
    int getMobileNumber(){return details.getMobileNumber();}
    int getGender(){return details.getGender();}

    void setName(String name){this.details.setName(name);}
    void setEmail(String email){this.details.setEmail(email);}
    void setDateOfBirth(Date dateOfBirth){this.details.setDateOfBirth(dateOfBirth);}
    void setPassword(String password) {this.details.setPassword(password);}
    void setMobileNumber(int mobileNumber){this.details.setMobileNumber(mobileNumber);}
    void setGender(byte gender){this.details.setGender(gender);}
}

class Teacher extends User {

    /**
     * Constructor for teacher subclass.
     *
     * @param name     Name of the teacher
     * @param userName Username of the teacher
     * @param email Email ID of the teacher
     * @param password Password of the teacher
     * @param userID   User ID of the teacher
     */
    Teacher(String name, String userName, String email,  String password, int userID) {
        super(name, userName, email, password, userID, Constants.ID_TEACHER);
    }
}

class Student extends User {

    /**
     * Constructor for student subclass.
     *
     * @param name     Name of the student
     * @param userName Username of the student
     * @param email Email ID of the student
     * @param password Password of the student
     * @param userID   User ID of the student
     */
    Student(String name, String userName, String email, String password, int userID) {
        super(name, userName, email, password, userID, Constants.ID_STUDENT);
    }

    int getGradYear(){return details.getGradYear();}

    void setGradYear(int gradYear){details.setGradYear(gradYear);}

}

class Alumnus extends User {

    /**
     * Constructor for Alumnus subclass.
     *
     * @param name     Name of the alumnus
     * @param userName Username of the alumnus
     * @param email Email ID of the alumns
     * @param password Password of the Alumnus
     * @param userID   User ID of the alumnus
     */
    Alumnus(String name, String userName, String email, String password, int userID) {
        super(name, userName, email, password, userID, Constants.ID_ALUMNUS);
    }
    String getQualification(){return details.getQualification();}
    String getPresentEmployer(){return details.getPresentEmployer();}
    String getCurrentPosition(){return details.getCurrentPosition();}

    void setQualification(String qualification){details.setQualification(qualification);}
    void setPresentEmployer(String presentEmployer){details.setPresentEmployer(presentEmployer);}
    void setCurrentPosition(String currentPosition){details.setCurrentPosition(currentPosition);}
}