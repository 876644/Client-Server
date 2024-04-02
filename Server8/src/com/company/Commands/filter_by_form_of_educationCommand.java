package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class filter_by_form_of_educationCommand extends AbstractCommand {
    public String formOfEducation;

    public filter_by_form_of_educationCommand(String formOfEducation) {
        this.formOfEducation = formOfEducation;
    }

    private static final long serialVersionUID = 6;
    @Override
    public void execute() {
        int sizeOfCollection = Main.collection.size();

        if(sizeOfCollection==0){
            Main.answer = "В коллекции нет объектов.";
        }
        for(int b=1; b<=sizeOfCollection;b++){
            StudyGroup example = Main.collection.poll();
            Main.twoColl.add(example);

            if(example != null ){
                if(formOfEducation.equals(String.valueOf(example.getFormOfEducation()))) {
                    Main.answer = "Номер iD: " + example.getId() +"\n " +
                            "Имя группы: " + example.getNameG() + "\n" +
                            "Координата x: " + example.getCoordinates().getX()+ "\n" +
                            "Координата y: " + example.getCoordinates().getY()+ "\n" +
                            "Дата: " + example.getCreationDate() +"\n" +
                            "Колличество студентов: " + example.getStudentsCount() + "\n" +
                            "Форма обучения: " + example.getFormOfEducation() + "\n" +
                            "Сместр: " + example.getSemesterEnum() + "\n" +
                            "Имя студента: " + example.getGroupAdmin().getName() + "\n" +
                            "iD паспорта: " + example.getGroupAdmin().getPassportID() + "\n" +
                            "Цвет глаз: " + example.getGroupAdmin().getEyeColor() + "\n" +
                            "Цвет волос: " + example.getGroupAdmin().getHairColor() + "\n" +
                            "Национальность: " + example.getGroupAdmin().getNationality();
                }
            }
        }
        Main.collection.addAll(Main.twoColl);
        Main.twoColl.clear();

        String nameHistory = "filter_by_form_of_education";
        Main.saveHistory.add(nameHistory);
    }
}

