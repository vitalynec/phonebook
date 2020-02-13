package com.vitalynec.phonebook.handler;

/**
 * Интерфейс сущности, отвечающей за обработку пользовательского ввода и передачу его в {@link com.vitalynec.phonebook.controller.CommonController}
 */
public interface Handler {
    /**
     * Метод обработки пользовательского ввода
     * @param commands String[] массив ввода
     */
    void handle(String[] commands);
}
