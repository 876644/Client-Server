package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class average_of_students_countCommand extends AbstractCommand {

    private static final long serialVersionUID = 4;

    @Override
    public void execute() {
        int sizeOfCollection = Main.collection.size();
        if(sizeOfCollection==0){
            Main.answer = "В коллекции нет объектов.";
        }else {
            int summ = 0;
            int count = 0;
            for (int b = 1; b <= sizeOfCollection; b++) {

                StudyGroup example = Main.collection.poll();
                Main.twoColl.add(example);

                if (example != null) {
                    summ += example.getStudentsCount();
                    count+=1;
                }
            }

            Main.answer = "Среднее значение колличества студентов = " + summ/count;
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();

            String nameHistory = "average_of_students_count";
            Main.saveHistory.add(nameHistory);
        }
    }
}
