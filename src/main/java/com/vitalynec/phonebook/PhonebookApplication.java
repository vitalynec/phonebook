package com.vitalynec.phonebook;

import com.vitalynec.phonebook.controller.CLIController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PhonebookApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(PhonebookApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(PhonebookApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        LOG.info("EXECUTING : command line runner");
        CLIController controller = new CLIController();
        controller.getAllUsers();
    }
}
