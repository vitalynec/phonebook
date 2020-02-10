package com.vitalynec.phonebook;

import com.vitalynec.phonebook.commandline.CommandLineParser;
import com.vitalynec.phonebook.controller.CommonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(PhonebookApplication.class);
    public static boolean isProcess = true;

    protected CommonController controller;
    protected CommandLineParser parser;

    @Autowired
    public void setController(CommonController controller) {
        this.controller = controller;
    }

    @Autowired
    public void setParser(CommandLineParser parser) {
        this.parser = parser;
    }

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(PhonebookApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : command line runner");
        parser.printMenu();
        do {
            parser.readFromConsole();
        } while (isProcess);
    }
}
