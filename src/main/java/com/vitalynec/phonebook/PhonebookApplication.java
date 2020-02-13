package com.vitalynec.phonebook;

import com.vitalynec.phonebook.handler.CommandLineHandler;
import com.vitalynec.phonebook.handler.Handler;
import com.vitalynec.phonebook.controller.CommonController;
import com.vitalynec.phonebook.io.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

    public static boolean isProcess = true;

    protected CommonController controller;
    protected Handler handler;
    protected Reader reader;

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
        do {
            handler.handle(
                    reader.read()
            );
        } while (isProcess);
    }
}
