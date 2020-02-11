package com.vitalynec.phonebook.commandline;

/**
 * Комманда <br/>
 * Интерфейс для описания функционала пунктов пользовательского меню
 */
public interface Command {
    /**
     * Точка входа в комманду
     */
    void execute();
}
