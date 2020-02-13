package com.vitalynec.phonebook.handler;

import com.vitalynec.phonebook.controller.commandline.Command;
import com.vitalynec.phonebook.controller.commandline.CommandsText;
import com.vitalynec.phonebook.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Класс для работы с командной строкой, обеспечивающий взаимодействие с пользователем
 */
@Component
public class CommandLineHandler implements Handler {
    protected CommonController controller;

    private final List<String> commands = Arrays.asList(
            CommandsText.ADD.getText() + CommandsText.ADD_HELP_TEXT.getText(),
            CommandsText.REMOVE.getText() + CommandsText.REMOVE_HELP_TEXT.getText(),
            CommandsText.ALL.getText(),
            CommandsText.USER.getText() + CommandsText.USER_HELP_TEXT.getText(),
            CommandsText.EXPORT.getText(),
            CommandsText.HELP.getText(),
            CommandsText.EXIT.getText()
    );

    @Override
    public void handle(String[] commands) {
        printMenu();
        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put(CommandsText.ADD.getText(), controller.addUser(commands));
        commandMap.put(CommandsText.REMOVE.getText(), controller.remove(commands));
        commandMap.put(CommandsText.ALL.getText(), controller.getAll());
        commandMap.put(CommandsText.USER.getText(), controller.getUser(commands));
        commandMap.put(CommandsText.EXPORT.getText(), controller.export());
        commandMap.put(CommandsText.HELP.getText(), this::printMenu);
        commandMap.put(CommandsText.EXIT.getText(), controller.exit());

        commandMap.getOrDefault(commands[0].toLowerCase(), () -> {
            System.out.println("Команда не распознана, попробуйте еще раз");
            printMenu();
        }).execute();
    }

    private void printMenu() {
        System.out.println("Доступные команды:\n");
        commands.forEach(System.out::println);
    }

    @Autowired
    public void setController(CommonController controller) {
        this.controller = controller;
    }

}