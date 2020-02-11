package com.vitalynec.phonebook.commandline;

/**
 * Перечисление для списка комманд и их текстового представления
 */
public enum CommandsText {
    ADD("add"),
    ADD_HELP_TEXT(" [user]|[user phone]"),
    REMOVE("remove"),
    REMOVE_HELP_TEXT(" [user]|[user phone]"),
    ALL("all"),
    USER("user"),
    USER_HELP_TEXT(" [id]"),
    EXPORT("export"),
    HELP("help"),
    EXIT("exit");

    CommandsText(String text) {
        this.text = text;
    }

    private final String text;

    protected String getText() {
        return text;
    }
}
