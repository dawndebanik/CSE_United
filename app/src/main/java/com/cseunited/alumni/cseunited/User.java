package com.cseunited.alumni.cseunited;

import java.net.UnknownServiceException;

/**
 * User base class for a generic user on the site.
 * Created by Debanik on 15-09-2017.
 */

abstract class User {
    String name;        //name of the user
    String password;    //the password
    int userID;         //userID used in login
    int position;       //1 indicates an alumnus, 2 a teacher and 3, a student (see Constants.java)

    /**
     * Constructor for the User base class.
     *
     * @param name     Name of the user
     * @param password Password of the user
     * @param userID   user ID of the user
     * @param position position of the user as defined in Constants.java
     */
    User(String name, String password, int userID, int position) {
        this.name = name;
        this.password = password;
        this.userID = userID;
        this.position = position;
    }
}

class Teacher extends User {

    /**
     * Constructor for teacher subclass.
     *
     * @param name     Name of the Teacher
     * @param password Password of the teacher
     * @param userID   User ID of the teacher
     */
    Teacher(String name, String password, int userID) {
        super(name, password, userID, Constants.ID_TEACHER);
    }
}

class Student extends User {

    /**
     * Constructor for student subclass.
     *
     * @param name     Name of the student
     * @param password Password of the student
     * @param userID   User ID of the student
     */
    Student(String name, String password, int userID) {
        super(name, password, userID, Constants.ID_STUDENT);
    }
}

class Alumnus extends User {

    /**
     * Constructor for Alumnus subclass.
     *
     * @param name     Name of the alumnus
     * @param password Password of the Alumnus
     * @param userID   User ID of the alumnus
     */
    Alumnus(String name, String password, int userID) {
        super(name, password, userID, Constants.ID_ALUMNUS);
    }
}