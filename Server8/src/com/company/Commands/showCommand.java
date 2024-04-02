package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class showCommand extends AbstractCommand {
    private static final long serialVersionUID = 13;

    @Override
    public void execute() {
        int sizeOfCollection = Main.collection.size();
        if (sizeOfCollection == 0) {
            Main.answer = "В коллекции нет объектов.";
        } else {
            for (int b = 1; b <= sizeOfCollection; b++) {
                StudyGroup example = Main.collection.poll();
                Main.twoColl.add(example);
                if (example != null) Main.answer = Main.answer + "\n" + "Объект коллекции № " + b +"\n " + example;
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();
            String nameHistory = "show";
            Main.saveHistory.add(nameHistory);
        }
    }
}
