package com.company.PG;

import com.company.Enums.*;
import com.company.Loginner.Login;
import com.company.Main;
import com.company.classes.Coordinates;
import com.company.classes.Person;
import com.company.classes.StudyGroup;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.PriorityQueue;

import static com.company.Main.AllId;


public class PGManager {

    Connection connection;
    Statement stmt;
    //private boolean connectionEstablished = false;

    public void connectToDatabase(){
        try {
            String url = "jdbc:postgresql://localhost:50002/studs";
            String login = "s336960";
            String password = "ikw322";
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            connection = DriverManager.getConnection(url,login,password);
            System.out.println("База данных подключена.");
            stmt = connection.createStatement();

        } catch (ClassNotFoundException | InstantiationException | SQLException | IllegalAccessException e){
            System.out.println("База данных не подключена..");
            e.printStackTrace();
            //connectionEstablished = false;
        }
    }


    public void createNewUserBase() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE NEWBASEOFUSERS " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL, " +
                    " USER1          TEXT    NOT NULL, " +
                    " NAME1          TEXT    NOT NULL, " +
                    " COORDINATEX    FLOAT  NOT NULL, " +
                    " COORDINATEY    INT     NOT NULL, " +
                    " CREATIONDATE   TEXT    NOT NULL, " +
                    " STUDENTSCOUNT  INT     NOT NULL, " +
                    " FORMOFEDUCATION         TEXT    NOT NULL, " +
                    " SEMESTER       TEXT    NOT NULL, " +
                    " ADMINNAME      TEXT    NOT NULL," +
                    " passportiD     TEXT    NOT NULL," +
                    " EYECOLOR       TEXT    NOT NULL, " +
                    " HAIRCOLOR      TEXT    NOT NULL, " +
                    " NATIONALITY    TEXT    NOT NULL);";
            stmt.executeUpdate(sql);
            //stmt.close();
        } catch (SQLException e){
            System.out.println("Unable to create database");
        }
    }

    public void createNewPasswordBase(){
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE NEWBASEOFPASSWORDS " +
                    "(ID SERIAL PRIMARY KEY     NOT NULL, " +
                    " USERNAME           TEXT    NOT NULL, " +
                    " PASSWORD           TEXT    NOT NULL," +
                    " R                  INT     NOT NULL," +
                    " G                  INT     NOT NULL," +
                    " B                  INT     NOT NULL);";
            stmt.executeUpdate(sql);

        } catch (SQLException e){
            System.out.println("Unable to create database");
        }
    }

    public boolean userExists(String loginController){
        boolean res = false;
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM NEWBASEOFPASSWORDS");
            while (rs.next()){
                if(rs.getString("USERNAME").equals(loginController)){
                    res = true;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public boolean passwordMatches(String password ,String login){
        boolean res = false;
        try{
            ResultSet rs = stmt.executeQuery("SELECT PASSWORD FROM NEWBASEOFPASSWORDS WHERE USERNAME = '"+login+"';");
            String result = null;
               if (rs.next()) result = rs.getString("PASSWORD");
               if (password.equals(result)) res = true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return res;
    }

    public void addNewUser(Login loginController){
        try{
            String sql = "INSERT INTO NEWBASEOFPASSWORDS (USERNAME, PASSWORD, R, G, B) VALUES ('" + loginController.getLogin()+"', '" + loginController.getPassword()+ "', '" + loginController.getR()+ "' , '" + loginController.getG()+ "', '" + loginController.getB()+ "'); ";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void readCollectionFromDatabase(PriorityQueue<StudyGroup> collection){
        try{
            collection.clear();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NEWBASEOFUSERS");
            while (rs.next()) {
                StudyGroup studyGroup = new StudyGroup(rs.getLong("id"),
                        rs.getString("user1"), rs.getString("name1"), new Coordinates(rs.getDouble("coordinatex"),
                        rs.getInt("coordinatey")), new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(rs.getString("creationdate")),
                        rs.getLong("studentscount"), FormOfEducation.valueOf(rs.getString("formofeducation")), Semester.valueOf(rs.getString("semester")),
                                new Person(rs.getString("adminname"), rs.getString("passportid"), ColorEye.valueOf(rs.getString("eyecolor")),
                                        ColorHair.valueOf(rs.getString("haircolor")), Country.valueOf(rs.getString("nationality"))));
                collection.add(studyGroup);
            }
    } catch (SQLException | ParseException e){
            e.printStackTrace();
        }
    }

    public void gId(){
        try {
            AllId.clear();
            ResultSet rs = stmt.executeQuery("SELECT * FROM NEWBASEOFUSERS");
            while (rs.next()) {
                AllId.add(rs.getLong("id"));
            }
            System.out.println(AllId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addElementToDatabase(StudyGroup studyGroup){
        try {

            String sql = "INSERT INTO NEWBASEOFUSERS (USER1, NAME1, COORDINATEX,COORDINATEY, CREATIONDATE, studentscount, " +
                    "formofeducation, semester, adminname, passportid ,eyecolor, haircolor, nationality ) VALUES ('" +
                    studyGroup.getUser()  + "','" + studyGroup.getNameG() + "','" + studyGroup.getCoordinates().getX() + "','" + studyGroup.getCoordinates().getY() + "','" + studyGroup.getCreationDate().toString()+ "','"+
                    studyGroup.getStudentsCount() + "','" + studyGroup.getFormOfEducation() + "','" + studyGroup.getSemesterEnum() + "','" + studyGroup.getGroupAdmin().getName() + "','" +
                    studyGroup.getGroupAdmin().getPassportID() + "','" + studyGroup.getGroupAdmin().getEyeColor() + "','" + studyGroup.getGroupAdmin().getHairColor() + "','" + studyGroup.getGroupAdmin().getNationality() + "');";
            stmt.executeUpdate(sql);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeElementFromDatabase(StudyGroup studyGroup){
        try{
            String sql = "DELETE FROM NEWBASEOFUSERS WHERE ID = " + studyGroup.getId() + ";";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

