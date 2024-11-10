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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {


    @Autowired
    StudentService studentService;


    @PostMapping("/add")
    public void addStudent(@RequestParam String name) {
        studentService.addStudent(name);
    }

    @DeleteMapping("/remove")
    public void removeStudent(@RequestParam String name) {
        studentService.removeStudent(name);
    }

    @PostMapping("/{name}/grades")
    public void addGrades(@PathVariable String name, @RequestParam String gradesInput) {
        studentService.addGrades(name,gradesInput);
    }

    @GetMapping("/{name}")
    public void getStudentByName(@PathVariable String name) {
        studentService.getStudentByName(name);
    }

    @NotNull
    @GetMapping("/all")
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{name}/average")
    public void calculateAverageGrade(@PathVariable String name) {
        studentService.calculateAverageGrade(name);
    }

}
