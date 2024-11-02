package dev.misha.school.exception;

import org.jetbrains.annotations.NotNull;

public class JournalException extends RuntimeException {
    public JournalException(String meesage, Throwable cause){
        super(meesage, cause);
    }

    public JournalException(@NotNull String message, @NotNull Object... args){
        super(message.formatted(args));
    }
}
