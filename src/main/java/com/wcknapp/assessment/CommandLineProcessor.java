package com.wcknapp.assessment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class CommandLineProcessor implements CommandLineRunner {
    private final FileProcessor processor;

    public CommandLineProcessor(FileProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("No file provided");
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("Too many arguments provided");
        }

        processor.processFile(args[0])
                .forEach(System.out::println);
    }
}
