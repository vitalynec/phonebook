package com.vitalynec.phonebook;

import com.vitalynec.phonebook.commandline.CommandLineHandler;
import com.vitalynec.phonebook.controller.CommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

    public static boolean isProcess = true;

    protected CommonController controller;
    protected CommandLineHandler handler;

    @Autowired
    public void setController(CommonController controller) {
        this.controller = controller;
    }

    @Autowired
    public void setHandler(CommandLineHandler handler) {
        this.handler = handler;
    }

    public static void main(String[] args) {
        SpringApplication.run(PhonebookApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        handler.printMenu();
        do {
            handler.handle();
        } while (isProcess);
    }
}
