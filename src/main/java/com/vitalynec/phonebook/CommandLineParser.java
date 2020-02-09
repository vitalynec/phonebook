package com.vitalynec.phonebook;

import com.vitalynec.phonebook.controller.CommonController;
import com.vitalynec.phonebook.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineParser {
    protected Scanner scanner = new Scanner(System.in);

    protected CommonController controller;

    @Autowired
    public void setController(CommonController controller) {
        this.controller = controller;
    }

    public void printMenu() {
        List<String> commands = new ArrayList<>();
        commands.add("add [user]|[user phone]\n");
        commands.add("remove [user]|[user phone]\n");
        commands.add("all\n");
        commands.add("user [id]\n");
        commands.add("export\n");
        commands.add("help\n");
        commands.add("exit\n");
        System.out.println("Доступные команды:\n");
        for (int i = 0; i < commands.size(); i++) {
            System.out.print((i + 1) + ". " + commands.get(i));
        }
    }

    public void readFromConsole() {
        String[] commands = scanner.nextLine().split(" ");

        switch (commands[0].toLowerCase()) {
            case ("add"): {
                addCommand(commands);
                break;
            }
            case ("remove"): {
                removeCommand(commands);
                break;
            }
            case ("all"): {
                allCommand();
                break;
            }
            case ("user"): {
                userCommand(commands);
                break;
            }
            case ("export"): {
                System.out.println("Данный функционал находится в разработке...");
                break;
            }
            case ("help"): {
                printMenu();
                break;
            }
            case ("exit"): {
                System.out.println("Выход...");
                PhonebookApplication.isProcess = false;
                break;
            }
            default:
                System.out.println("Команда не распознана, попробуйте еще раз");
                printMenu();
        }
    }

    private void removeCommand(String[] commands) {
        if (commands.length < 2) {
            System.out.println("Недостаточное количество аргументов");
            return;
        }

        if ("phone".equalsIgnoreCase(commands[1])) {
            if (commands[2] == null || StringUtils.isEmpty(commands[2])) {
                System.out.println("Недостаточное количество аргументов");
            } else {
            //TODO удаление телефона
            }

        }
    }

    private void addCommand(String[] commands) {
        if (commands.length < 3) {
            System.out.println("Недостаточное количество аргументов");
            return;
        }
        if ("user".equalsIgnoreCase(commands[1])) {
            controller.addUser(commands[2]);
        }

        if ("phone".equalsIgnoreCase(commands[1])) {
            if (commands[2] == null || StringUtils.isEmpty(commands[2])) {
                System.out.println("Недостаточное количество аргументов");
            } else {
                try {
                    controller.addPhoneToUser(commands[2], Long.valueOf(commands[3]));
                } catch (NotFoundException e) {
                    System.out.println("Пользователь не найден!");
                }
            }

        }
    }

    private void userCommand(String[] commands) {
        if (commands.length < 2) {
            System.out.println("Недостаточное количество аргументов");
            return;
        }

        try {
            System.out.println(controller.getUserByName(commands[1]));
        } catch (NotFoundException e) {
            System.out.println("Пользователь не найден!");
        }

    }

    private void allCommand() {
        System.out.println(controller.getAllUsers());
    }
}
