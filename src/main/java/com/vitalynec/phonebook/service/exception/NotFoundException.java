package com.vitalynec.phonebook.service.exception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("Пользователь не найден!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
