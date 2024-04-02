package com.company.Commands;

import com.company.Main;
import com.company.classes.StudyGroup;

import static com.company.Main.l;

public class remove_by_idCommand extends AbstractCommand {
    public Long ID;
    private static final long serialVersionUID = 11;
    @Override
    public void execute() {
        long number = ID;
        int sizeOfCollection = Main.collection.size();

        if(sizeOfCollection==0){
            Main.answer = "В коллекции нет объектов.";
        }else {
            for (StudyGroup p:Main.collection)
                if(p.getUser().equals(l) && p.getId() == number) {
                    Main.pgManager.removeElementFromDatabase(p);
                }

            for (int b = 1; b <= sizeOfCollection; b++) {
                StudyGroup example = Main.collection.poll();
                if (example != null) {
                    System.out.println(example.getUser());
                    System.out.println(l);
                    System.out.println(number);
                    if (example.getUser().equals(l) && number == example.getId()) {
                        Main.answer = "Данные элемента с таким iD был найден и удалён. ";
                        break;
                    }else {
                        Main.twoColl.add(example);
                        Main.answer = "Данные с таким iD не были удалены.";
                    }
                }
            }
            Main.collection.addAll(Main.twoColl);
            Main.twoColl.clear();

            String nameHistory = "remove_by_id";
            Main.saveHistory.add(nameHistory);
        }
    }
    public remove_by_idCommand(Long ID) {
        this.ID = ID;
    }
}
