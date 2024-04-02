package com.company.Commands;

import com.company.Main;

public class historyCommand extends AbstractCommand {
    private static final long serialVersionUID = 20;
    @Override
    public void execute() {
        int size = Main.saveHistory.size();
        if(size !=0) {
            if (size <15){

                Main.answer = "Вы ввели меньше 15 команд до этого. Часть команд, которая была введена: " + "\n" +
                        Main.saveHistory.subList(0,size);

            }else Main.answer = "Последние введённые 15 команд:" + "\n" +
                    Main.saveHistory.subList(size-14,size);

        }else Main.answer = "Ранее не было введено ни одной команды. ";
        String nameHistory = "history";
        Main.saveHistory.add(nameHistory);
    }
}
