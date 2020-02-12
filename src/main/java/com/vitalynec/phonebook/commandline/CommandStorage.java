package com.vitalynec.phonebook.commandline;

import com.vitalynec.phonebook.PhonebookApplication;
import com.vitalynec.phonebook.controller.CommonController;
import com.vitalynec.phonebook.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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
                Assert.isTrue(commands.length > 2, () -> "Недостаточное количество аргументов");

                if ("user".equalsIgnoreCase(commands[1])) {
                    controller.removeUser(Integer.valueOf(commands[2]));
                }

                if ("phone".equalsIgnoreCase(commands[1])) {
                    //TODO удаление телефона
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректно указан ID пользователя!");
            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    /**
     * Добавление записи
     */
    static Command addCommand(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 2, () -> "Недостаточное количество аргументов");

                if ("user".equalsIgnoreCase(commands[1])) {
                    controller.addUser(commands[2]);
                    // TODO добавить телефон, если пользователь не существует ???
                }

                if ("phone".equalsIgnoreCase(commands[1])) {
                    Assert.isTrue(commands.length > 3, () -> "Недостаточное количество аргументов");
                    try {
                        controller.addPhoneToUser(commands[2], Integer.valueOf(commands[3]));
                    } catch (NotFoundException e) {
                        System.out.println("Пользователь не найден!");
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
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
                Assert.isTrue(commands.length > 1, () -> "Недостаточное количество аргументов");

                System.out.println(controller.getUserById(Integer.valueOf(commands[1])));
            } catch (NumberFormatException e) {
                System.out.println("Некорректно указан ID пользователя!");
            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    /**
     * Получение всех записей
     *
     * @return Текстовое представление пользователей со списком телефонов
     */
    static Command allCommand() {
        return () -> {
            System.out.println(controller.getAllUsers());
        };
    }

    /**
     * Завершение работы приложения
     */
    static Command exitCommand() {
        return () -> {
            System.out.println("Выход...");
            PhonebookApplication.isProcess = false;
        };
    }
}