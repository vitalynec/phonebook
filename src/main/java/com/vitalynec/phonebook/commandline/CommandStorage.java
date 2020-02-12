package com.vitalynec.phonebook.commandline;

import com.vitalynec.phonebook.PhonebookApplication;
import com.vitalynec.phonebook.controller.CommonController;
import com.vitalynec.phonebook.exception.IncorrectArgumentCountException;
import com.vitalynec.phonebook.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Хранилище комманд для {@link CommandLineHandler}
 */
@Component
class CommandStorage {

    private static CommonController controller;

    @Autowired
    public void setController(CommonController controller) {
        CommandStorage.controller = controller;
    }

    /**
     * Удаление записи
     */
    static Command removeCommand(String[] commands) {
        return () -> {
            try {
                checkCommandsLength(commands, 2);
            } catch (IncorrectArgumentCountException e) {
                System.out.println(e.getMessage());

            }

            // TODO удаление пользователя

            if ("phone".equalsIgnoreCase(commands[1])) {
                if (commands[2] == null || StringUtils.isEmpty(commands[2])) {
                    System.out.println("Недостаточное количество аргументов");
                } else {
                    //TODO удаление телефона
                }

            }
        };
    }

    /**
     * Добавление записи
     */
    static Command addCommand(String[] commands) {
        return () -> {
            try {
                checkCommandsLength(commands, 3);
            } catch (IncorrectArgumentCountException e) {
                System.out.println(e.getMessage());
            }

            if ("user".equalsIgnoreCase(commands[1])) {
                controller.addUser(commands[2]);
                // TODO добавить телефон, если пользователь не существует ???
            }

            if ("phone".equalsIgnoreCase(commands[1])) {
                if (commands[2] == null || StringUtils.isEmpty(commands[2])) {
                    System.out.println("Недостаточное количество аргументов");
                } else {
                    try {
                        controller.addPhoneToUser(commands[2], Integer.valueOf(commands[3]));
                    } catch (NotFoundException e) {
                        System.out.println("Пользователь не найден!");
                    }
                }
            }
        };
    }

    /**
     * Экспорт в XML
     */
    static Command exportCommand() {
        return () -> {
            System.out.println("Данный функционал находится в разработке...");
        };
    }

    /**
     * Получение записи </br>
     *
     * @return Текстовое представление пользователя со списком телефонов
     */
    static Command userCommand(String[] commands) {
        return () -> {
            try {
                checkCommandsLength(commands, 2);
            } catch (IncorrectArgumentCountException e) {
                System.out.println(e.getMessage());
            }

            try {
                System.out.println(controller.getUserById(Integer.valueOf(commands[1])));
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Некорректно указан ID пользователя!");
            }
        };
    }

    /**
     * Получение всех записей </br>
     *
     * @return Текстовое представление пользователей со списком телефонов
     */
    static Command allCommand() {
        return () -> {
            System.out.println(controller.getAllUsers());
        };
    }

    /**
     * Выход
     */
    static Command exitCommand() {
        return () -> {
            System.out.println("Выход...");
            PhonebookApplication.isProcess = false;
        };
    }

    private static void checkCommandsLength(String[] commands, int minLength)
            throws IncorrectArgumentCountException {
        if (commands.length < minLength) {
            throw new IncorrectArgumentCountException("Недостаточное количество аргументов");
        }
    }
}
