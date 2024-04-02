package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class clearCommand extends AbstractCommand {

    private static final long serialVersionUID = 1;

    @Override
    public void execute() {

        for (StudyGroup pr:Main.collection) {

            if (pr.getUser().equals(Main.l)) {
                Main.pgManager.removeElementFromDatabase(pr);
            }
        }
        Main.collection.removeIf(p -> p.getUser().equals(Main.login.getLogin()));
        Main.collection.clear();
        Main.answer = "Коллекция была очищена";
        String nameHistory = "clear";
        Main.saveHistory.add(nameHistory);
    }

}
