package com.vitalynec.phonebook.handler;

import com.vitalynec.phonebook.controller.CommonController;
import com.vitalynec.phonebook.controller.commandline.Command;
import com.vitalynec.phonebook.controller.commandline.CommandsText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для работы с командной строкой, обеспечивающий взаимодействие с пользователем
 */
@Component
public class CommandLineHandler implements Handler {
    protected CommonController controller;

    @Override
    public void handle(String[] commands) {
        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put(CommandsText.ADD.getText(), controller.addUser(commands));
        commandMap.put(CommandsText.REMOVE.getText(), controller.remove(commands));
        commandMap.put(CommandsText.ALL.getText(), controller.getAll());
        commandMap.put(CommandsText.USER.getText(), controller.getUser(commands));
        commandMap.put(CommandsText.EXPORT.getText(), controller.export());
        commandMap.put(CommandsText.HELP.getText(), controller.printMenu());
        commandMap.put(CommandsText.EXIT.getText(), controller.exit());

        commandMap.getOrDefault(commands[0].toLowerCase(), () -> {
            System.out.println("Команда не распознана, попробуйте еще раз");
        }).execute();
    }

    @Autowired
    public void setController(CommonController controller) {
        this.controller = controller;
    }

}
