package com.company.Commands;

import com.company.Main;

import java.time.LocalDate;

public class infoCommand extends AbstractCommand {
    private static final long serialVersionUID = 9;
    @Override
    public void execute() {
        Main.answer = " Информация о коллекции:\n" +
                "Тип коллекции - PriorityQueue\n" +
                "Дата инициализации - " + LocalDate.now() + "\n" +
                "Количество эллементов - " + Main.collection.size();
        String nameHistory = "info";
        Main.saveHistory.add(nameHistory);
    }
}
