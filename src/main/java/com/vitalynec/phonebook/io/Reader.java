package com.vitalynec.phonebook.io;

/**
 * Интерфейс сущности, получающей информацию от пользователя
 */
public interface Reader {
    /**
     * Метод получения информации от пользователя
     *
     * @return String[] массив ввода
     */
    String[] read();
}
