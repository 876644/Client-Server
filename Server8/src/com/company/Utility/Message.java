package com.company.Utility;

import com.company.Commands.AbstractCommand;
import com.company.Loginner.Login;
import com.company.classes.StudyGroup;

import java.io.Serializable;
import java.util.PriorityQueue;

public class Message implements Serializable {
    public String message;
    public boolean newMes = true;
    public AbstractCommand object;
    //public Login login;
    public  String login;
    public String password2;
    public Boolean QQQ;
    public int r;
    public int g;
    public int b;
    public PriorityQueue<StudyGroup> objectForTable;
    private static final long serialVersionUID = 55;

    public Message(String message, PriorityQueue<StudyGroup> objectForTable,  String password2,String login, Boolean QQQ,  int r, int g, int b) {
        this.message = message;
        this.objectForTable = objectForTable;
        this.password2 = password2;
        this.login = login;
        this.QQQ = QQQ;
        this.r = r;
        this.g = g;
        this.b = b;
        if(message.equals("Вы успешно вошли в систему"))
            newMes=false;
    }
}
