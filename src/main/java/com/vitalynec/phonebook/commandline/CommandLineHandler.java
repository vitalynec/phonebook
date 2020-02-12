package com.vitalynec.phonebook.commandline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.vitalynec.phonebook.commandline.CommandStorage.*;

/**
 * Класс для работы с командной строкой, обеспечивающий взаимодействие с пользователем
 */
@Component
public class CommandLineHandler {

    private final List<String> commands = Arrays.asList(
            CommandsText.ADD.getText() + CommandsText.ADD_HELP_TEXT.getText(),
            CommandsText.REMOVE.getText() + CommandsText.REMOVE_HELP_TEXT.getText(),
            CommandsText.ALL.getText(),
            CommandsText.USER.getText() + CommandsText.USER_HELP_TEXT.getText(),
            CommandsText.EXPORT.getText(),
            CommandsText.HELP.getText(),
            CommandsText.EXIT.getText()
    );

    protected Scanner scanner;

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printMenu() {
        System.out.println("Доступные команды:\n");
        commands.forEach(System.out::println);
    }

    private String[] readFromConsole() {
        return scanner.nextLine().split(" ");
    }

    public void handle() {
        String[] commands = readFromConsole();
        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put(CommandsText.ADD.getText(), addCommand(commands));
        commandMap.put(CommandsText.REMOVE.getText(), removeCommand(commands));
        commandMap.put(CommandsText.ALL.getText(), allCommand());
        commandMap.put(CommandsText.USER.getText(), userCommand(commands));
        commandMap.put(CommandsText.EXPORT.getText(), exportCommand());
        commandMap.put(CommandsText.HELP.getText(), this::printMenu);
        commandMap.put(CommandsText.EXIT.getText(), exitCommand());

        commandMap.getOrDefault(commands[0].toLowerCase(), () -> {
            System.out.println("Команда не распознана, попробуйте еще раз");
            printMenu();
        }).execute();
    }
}

