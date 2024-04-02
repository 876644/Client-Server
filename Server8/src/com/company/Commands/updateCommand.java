package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

public class updateCommand extends AbstractCommand {
    public Long ID;
    private static final long serialVersionUID = 14;
    @Override
    public void execute() {
        long number = ID;
        int sizeOfCollection = Main.collection.size();

        if(sizeOfCollection==0){
            Main.answer = "В коллекции нет объектов.";
        }else {

            for (int b = 1; b <= sizeOfCollection; b++) {
                StudyGroup example = Main.collection.poll();
                Main.pgManager.removeElementFromDatabase(example);
                if (example != null) {
                    if (number == example.getId()) {
                        Main.answer = "Данные элемента с таким iD был найден и удалён. Введите новые двнные элемента";
                        String[] argss = new String[0];
                        
                    } else Main.twoColl.add(example);
                }
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();

            String nameHistory = "update";
            Main.saveHistory.add(nameHistory);
        }
    }
    public updateCommand(Long ID) {
        this.ID = ID;
    }
}
