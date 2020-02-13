package com.vitalynec.phonebook.io.impl;

import com.vitalynec.phonebook.io.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Реализация {@link Reader} для работы с консольным вводом
 */
@Component
public class ReaderFromConsole implements Reader {

    protected Scanner scanner;

    @Autowired
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String[] read() {
        return scanner.nextLine().split(" ");
    }
}
