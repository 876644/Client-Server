package com.company.Commands;

import com.company.Main;

public class helpCommand extends AbstractCommand {
    private static final long serialVersionUID = 8;
    @Override
    public void execute() {
        System.out.println(Thread.currentThread());
        Main.answer = """
                help : вывести справку по доступным командам
                info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                add {element} : добавить новый элемент в коллекцию
                update id {element} : обновить значение элемента коллекции, id которого равен заданному
                remove_by_id id : удалить элемент из коллекции по его id
                clear : очистить коллекцию
                save : сохранить коллекцию в файл
                execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                exit : завершить программу (без сохранения в файл)
                head : вывести первый элемент коллекции
                remove_head : вывести первый элемент коллекции и удалить его
                history : вывести последние 15 команд (без их аргументов)
                remove_any_by_students_count studentsCount : удалить из коллекции один элемент, значение поля studentsCount которого эквивалентно заданному
                average_of_students_count : вывести среднее значение поля studentsCount для всех элементов коллекции
                filter_by_form_of_education formOfEducation : вывести элементы, значение поля formOfEducation которых равно заданному""";
        String nameHistory = "help";
        Main.saveHistory.add(nameHistory);
    }
}
