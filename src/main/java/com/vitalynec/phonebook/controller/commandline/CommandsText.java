package com.vitalynec.phonebook.controller.commandline;

/**
 * Перечисление для списка комманд и их текстового представления
 */
public enum CommandsText {
    ADD("add"),
    ADD_HELP("[user \"name\"]|[phone \"number\"]"),
    REMOVE("remove"),
    REMOVE_HELP("[user \"id\"]|[phone \"id\"]"),
    ALL("all"),
    USER("user"),
    USER_HELP("[id]"),
    EXPORT("export"),
    HELP("help"),
    EXIT("exit");

    CommandsText(String text) {
        this.text = text;
    }

    private final String text;

    public String getText() {
        return text;
    }
}
