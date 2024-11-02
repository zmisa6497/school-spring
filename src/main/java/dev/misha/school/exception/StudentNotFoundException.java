package dev.misha.school.exception;

import org.jetbrains.annotations.NotNull;

public class StudentNotFoundException extends JournalException {
    public StudentNotFoundException(@NotNull String message, @NotNull Object... args){
        super(message, args);
    }
}
