package com.vitalynec.phonebook.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.vitalynec.phonebook.commandline.CommandStorage.*;

/**
 * Класс для работы с командной строкой, обеспечивающий взаимодействие с пользователем
 */
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
        commands.forEach(System.out::print);
    }

    public void readFromConsole() {
        String[] commands = scanner.nextLine().split(" ");
        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put("add", addCommand(commands));
//        commandMap.put("remove", removeCommand(commands));
        commandMap.put("all", allCommand());
        commandMap.put("user", userCommand(commands));
        commandMap.put("export", exportCommand());
        commandMap.put("help", this::printMenu);
        commandMap.put("exit", exitCommand());

        commandMap.getOrDefault(commands[0].toLowerCase(), () -> {
            System.out.println("Команда не распознана, попробуйте еще раз");
            printMenu();
        }).execute();
    }

    private Command exportCommand() {
        return () -> {
            System.out.println("Данный функционал находится в разработке...");
        };
    }

    private Command exitCommand() {
        return () -> {
            System.out.println("Выход...");
            PhonebookApplication.isProcess = false;
        };
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

    private Command addCommand(String[] commands) {
        return () -> {
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
        };
    }

    private Command userCommand(String[] commands) {
        return () -> {
            if (commands.length < 2) {
                System.out.println("Недостаточное количество аргументов");
                return;
            }

            try {
                System.out.println(controller.getUserById(Integer.valueOf(commands[1])));
            } catch (NotFoundException e) {
                System.out.println("Пользователь не найден!");
            }
        };
    }

    private Command allCommand() {
        return () -> {
            System.out.println(controller.getAllUsers());
        };
    }
}
