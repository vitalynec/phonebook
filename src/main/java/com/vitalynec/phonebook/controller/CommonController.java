package com.vitalynec.phonebook.controller;

import com.vitalynec.phonebook.PhonebookApplication;
import com.vitalynec.phonebook.controller.commandline.Command;
import com.vitalynec.phonebook.entity.dto.PhoneDto;
import com.vitalynec.phonebook.entity.dto.UserDto;
import com.vitalynec.phonebook.service.exception.NotFoundException;
import com.vitalynec.phonebook.service.PhoneService;
import com.vitalynec.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CommonController {

    protected UserService userService;
    protected PhoneService phoneService;

    public UserDto getUserById(Integer id) throws NotFoundException {
        return userService.findById(id).orElseThrow(NotFoundException::new);
    }

    /**
     * Добавление записи
     */
    public Command addUser(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 2, () -> "Недостаточное количество аргументов");

                if ("user".equalsIgnoreCase(commands[1])) {
                    UserDto user = new UserDto();
                    user.setName(commands[2]);
                    System.out.println(
                            userService.save(user).get()
                    );
                    // TODO добавить телефон, если пользователь не существует ???
                }

                if ("phone".equalsIgnoreCase(commands[1])) {
                    Assert.isTrue(commands.length > 3, () -> "Недостаточное количество аргументов");
                    try {
                        PhoneDto phone = new PhoneDto();
                        phone.setNumber(commands[2]);
                        phone.setUser(getUserById(Integer.valueOf(commands[3])));
                        phoneService.save(phone);
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
     * Удаление записи
     */
    public Command remove(String[] commands) {
        return () -> {
            try {
                Assert.isTrue(commands.length > 2, () -> "Недостаточное количество аргументов");

                if ("user".equalsIgnoreCase(commands[1])) {
                    Integer id = Integer.valueOf(commands[2]);
                    userService.deleteById(id);
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
     * Экспорт в XML
     */
    public Command export() {
        return () -> {
            System.out.println("Данный функционал находится в разработке...");
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
                Assert.isTrue(commands.length > 1, () -> "Недостаточное количество аргументов");
                Integer id = Integer.valueOf(commands[2]);

                System.out.println(
                        userService.findById(id).orElseThrow(NotFoundException::new)
                );
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

    @Autowired
    public void setPhoneService(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
