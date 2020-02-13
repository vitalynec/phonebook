package com.vitalynec.phonebook.controller;

import com.vitalynec.phonebook.PhonebookApplication;
import com.vitalynec.phonebook.controller.commandline.Command;
import com.vitalynec.phonebook.controller.commandline.CommandsText;
import com.vitalynec.phonebook.entity.dto.Users;
import com.vitalynec.phonebook.entity.dto.PhoneDto;
import com.vitalynec.phonebook.entity.dto.UserDto;
import com.vitalynec.phonebook.service.PhoneService;
import com.vitalynec.phonebook.service.UserService;
import com.vitalynec.phonebook.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Component
public class CommonController {
    private final List<String> commands = Arrays.asList(
            CommandsText.ADD.getText() + " " + CommandsText.ADD_HELP.getText(),
            CommandsText.REMOVE.getText() + " " + CommandsText.REMOVE_HELP.getText(),
            CommandsText.ALL.getText(),
            CommandsText.USER.getText() + " " + CommandsText.USER_HELP.getText(),
            CommandsText.EXPORT.getText(),
            CommandsText.HELP.getText(),
            CommandsText.EXIT.getText()
    );

    public static final String ILLEGAL_ARGUMENT_COUNT = "Недостаточное количество аргументов";
    public static final String USER_NOT_FOUND = "Пользователь не найден";
    public static final String COMMAND_NOT_IDENTIFIED = "Команда не определена";
    public static final String UNCORRECT_ID = "Некорректно указан ID записи";
    public static final String USER_TEXT = "user";
    public static final String PHONE_TEXT = "phone";

    protected UserService userService;
    protected PhoneService phoneService;

    /**
     * Добавление записи
     */
    public Command addUser(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 2, () -> ILLEGAL_ARGUMENT_COUNT);

                if (USER_TEXT.equalsIgnoreCase(commands[1])) {
                    UserDto user = new UserDto();
                    user.setName(commands[2]);
                    System.out.println(
                            userService.save(user).get()
                    );
                }

                if (PHONE_TEXT.equalsIgnoreCase(commands[1])) {
                    Assert.isTrue(commands.length > 3, () -> ILLEGAL_ARGUMENT_COUNT);
                    try {
                        PhoneDto phone = new PhoneDto();
                        phone.setNumber(commands[2]);
                        UserDto user = userService.findById(Integer.valueOf(commands[3]))
                                .orElseThrow(NotFoundException::new);
                        phone.setUser(user);
                        phoneService.save(phone);
                    } catch (NotFoundException e) {
                        System.out.println(USER_NOT_FOUND);
                    }
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    /**
     * Удаление записи
     */
    public Command remove(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 2, () -> ILLEGAL_ARGUMENT_COUNT);
                Integer id = Integer.valueOf(commands[2]);

                switch (commands[1].toLowerCase()) {
                    case USER_TEXT: {
                        userService.deleteById(id);
                        break;
                    }
                    case PHONE_TEXT: {
                        phoneService.deleteById(id);
                        break;
                    }
                    default: {
                        System.out.println(COMMAND_NOT_IDENTIFIED);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println(UNCORRECT_ID);
            } catch (NotFoundException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    /**
     * Экспорт в XML
     */
    public Command export() {
        return () -> {
            try {
                JAXBContext context = JAXBContext.newInstance(Users.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                Users users = new Users();
                users.setUsers(userService.findAll());
                File defaultFile = new File("./users.xml");
                marshaller.marshal(users, defaultFile);
            } catch (JAXBException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    /**
     * Получение записи </br>
     *
     * @return Текстовое представление пользователя со списком телефонов
     */
    public Command getUser(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 1, () -> ILLEGAL_ARGUMENT_COUNT);
                Integer id = Integer.valueOf(commands[1]);

                System.out.println(
                        userService.findById(id).orElseThrow(NotFoundException::new)
                );
            } catch (NumberFormatException e) {
                System.out.println(UNCORRECT_ID);
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
    public Command getAll() {
        return () -> {
            System.out.println(userService.findAll());
        };
    }

    /**
     * Завершение работы приложения
     */
    public Command exit() {
        return () -> {
            System.out.println("Выход...");
            PhonebookApplication.isProcess = false;
        };
    }

    /**
     * Список доступных команд
     */
    public Command printMenu() {
        return () -> {
            System.out.println("Доступные команды:\n");
            commands.forEach(System.out::println);
        };
    }

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
