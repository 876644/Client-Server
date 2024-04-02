package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class remove_headCommand extends AbstractCommand {
    private static final long serialVersionUID = 12;
    @Override
    public void execute() {
        if (Main.collection.size()!=0) {
            StudyGroup example = Main.collection.poll();


            if (example != null) {
                Main.answer = "Первый элемент коллекции: " + "\n" +
                        "Номер iD: " + example.getId() +"\n " +
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
                        "Национальность: " + example.getGroupAdmin().getNationality() + "\n" +
                        "Этот объект был удалён";
                Main.pgManager.removeElementFromDatabase(example);
            }

            String nameHistory = "remove_head";
            Main.saveHistory.add(nameHistory);
        } else System.out.println("В коллекции нет эллементов.");
    }
}
