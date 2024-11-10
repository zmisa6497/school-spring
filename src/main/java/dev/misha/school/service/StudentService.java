package dev.misha.school.service;

import dev.misha.school.exception.JournalException;
import dev.misha.school.model.Student;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface StudentService {
    void addStudent(@NotNull String name) throws JournalException;


    void removeStudent(@NotNull String name) throws JournalException;

    void addGrades(@NotNull String name,@NotNull String gradesInput) throws JournalException;

    @NotNull Student getStudentByName(@NotNull String name) throws  JournalException;

    List<Student> getAll() throws  JournalException;

    Double calculateAverageGrade(@NotNull String name) throws JournalException;


}
