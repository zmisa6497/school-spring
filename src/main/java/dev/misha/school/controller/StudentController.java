package dev.misha.school.controller;


import dev.misha.school.exception.GradeSaveException;
import dev.misha.school.exception.JournalException;
import dev.misha.school.model.Student;
import dev.misha.school.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {


    @Autowired
    @Qualifier("studentServiceImpl")
    StudentService studentService;


    @NotNull
    @PostMapping("/add")
    public void addStudent(@RequestParam String name) throws GradeSaveException {
        studentService.addStudent(name);
    }

    @NotNull
    @PostMapping("/remove")
    public void removeStudent(@RequestParam String name) throws JournalException {
        studentService.removeStudent(name);
    }

    @NotNull
    @PostMapping("/{name}/grades")
    public void addGrades(@PathVariable String name, @RequestParam String gradesInput) throws JournalException {
        studentService.addGrades(name,gradesInput);
    }

    @NotNull
    @GetMapping("/{name}")
    public void getStudentByName(@PathVariable String name) throws JournalException {
        studentService.getStudentByName(name);
    }

    @NotNull
    @GetMapping()
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @NotNull
    @GetMapping("/{name}/average")
    public void calculateAverageGrade(@PathVariable String name) throws JournalException {
        studentService.calculateAverageGrade(name);
    }

}
