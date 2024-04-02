package com.company.Commands;

import com.company.Main;

import java.io.Serializable;

public class Collection extends AbstractCommand implements Serializable {

    private static final long serialVersionUID = 111;
    @Override
    public void execute() {
        Main.collection = Main.message.objectForTable;
        Main.answer = "Объект заменён.";
    }
}
