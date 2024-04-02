package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class remove_any_by_students_countCommand extends AbstractCommand {
    public Long countOfStudents;
    private static final long serialVersionUID = 10;
    @Override
    public void execute() {
        long study = countOfStudents;
        int count = 0;

        int sizeOfCollection = Main.collection.size();

        if(sizeOfCollection==0){
            Main.answer = "В коллекции нет объектов.";
        }
        for (StudyGroup p:Main.collection)
            if(p.getUser().equals(Main.login.getLogin()) && p.getStudentsCount() == study && count == 0) {
                Main.pgManager.removeElementFromDatabase(p);
                count+=1;
            }
        count = 0;
        //Main.collection.removeIf(p -> p.getUser().equals(Main.login.getLogin()) && p.getStudentsCount() == study);


        for(int b=1; b<=sizeOfCollection;b++){
            StudyGroup example = Main.collection.poll();

            if(example != null ){
                if(study == example.getStudentsCount() && count == 0 && example.getUser().equals(Main.login.getLogin())) {
                    Main.answer = "Один элемент с данным колличеством студентов был удален.";
                    count+=1;
                }else Main.twoColl.add(example);
            }
        }
        Main.collection.addAll(Main.twoColl);
        Main.twoColl.clear();

        String nameHistory = "remove_any_by_students_count";
        Main.saveHistory.add(nameHistory);
    }
    public remove_any_by_students_countCommand(Long countOfStudents) {
        this.countOfStudents = countOfStudents;
    }
}
