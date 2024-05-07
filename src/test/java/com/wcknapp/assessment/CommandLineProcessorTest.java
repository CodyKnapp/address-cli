package com.wcknapp.assessment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandLineProcessorTest {
    @Test
    public void runShouldThrowIllegalArgumentExceptionWhenNoFileProvided() {
        var processor = new CommandLineProcessor(null);
        assertThrows(IllegalArgumentException.class, () -> processor.run());
    }

    @Test
    public void runShouldThrowIllegalArgumentExceptionWhenTooManyArgumentsProvided() {
        var processor = new CommandLineProcessor(null);
        assertThrows(IllegalArgumentException.class, () -> processor.run("file1", "file2"));
    }

    @Test
    public void runShouldCallProcessFileWithFileNameForASingleFile() {
        var mockProcessor = mock(FileProcessor.class);
        var subject = new CommandLineProcessor(mockProcessor);
        var file = "file.csv";
        try {
            subject.run(file);
        } catch (Exception e) {
            fail("Should not throw exception");
        }

        verify(mockProcessor).processFile(file);
    }
}