package com.company.Commands;

import com.company.Enums.*;
import com.company.Main;
import com.company.PG.PGManager;
import com.company.classes.Coordinates;
import com.company.classes.Person;
import com.company.classes.StudyGroup;

import java.util.Date;
import java.util.Random;

import static com.company.Main.AllId;
import static com.company.Main.p;

public class addCommand extends AbstractCommand {
    public String nameOfGroup;
    public Double x;
    public Integer y;
    public Long studentsCount;
    public FormOfEducation formOfEducation;
    public Semester semesterEnum;
    public String namePerson;
    public String passportID;
    public ColorEye eyeColor;
    public ColorHair hairColor;
    public Country nationality;
    private static final long serialVersionUID = 3;

    public addCommand(String nameOfGroup, Double x, Integer y, Long studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, String namePerson, String passportID, ColorEye eyeColor, ColorHair hairColor, Country nationality) {
        this.nameOfGroup = nameOfGroup;
        this.x = x;
        this.y = y;
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.namePerson = namePerson;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    @Override
    public void execute() {
        System.out.println("ggg");
        int size = AllId.size();
        long idh;
        if(size == 0)  idh = 1;
        else idh = AllId.get(size-1) + 1;
        System.out.println(idh);
        Date date = new Date();

        Coordinates coordinates = new Coordinates(x, y);
        Person person = new Person(namePerson, passportID, eyeColor, hairColor, nationality);
        StudyGroup studyGroup = new StudyGroup(idh+1, Main.l,nameOfGroup, coordinates, date, studentsCount, formOfEducation, semesterEnum, person);
        Main.pgManager.addElementToDatabase(studyGroup);
        Main.collection.add(studyGroup);
        Main.answer = "Объект был успешно добавлен в коллекцию.";
    }
}
