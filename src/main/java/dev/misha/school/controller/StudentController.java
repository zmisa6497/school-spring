package dev.misha.school.controller;


import dev.misha.school.model.Student;
import dev.misha.school.service.StudentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestParam String name) {
        studentService.addStudent(name);
        return ResponseEntity.ok("Student added successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeStudent(@RequestParam String name) {
        studentService.removeStudent(name);
        return ResponseEntity.ok("Student removed successfully");
    }

    @PostMapping("/{name}/grades")
    public ResponseEntity<String> addGrades(@PathVariable String name, @RequestParam String gradesInput) {
        studentService.addGrades(name, gradesInput);
        return ResponseEntity.ok("Grades added successfully");
    }

    @GetMapping("/{name}")
    public ResponseEntity<Student> getStudentByName(@PathVariable String name) {
        Student student = studentService.getStudentByName(name);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }

    @GetMapping("/{name}/average")
    public ResponseEntity<Double> calculateAverageGrade(@PathVariable String name) {
        double average = studentService.calculateAverageGrade(name);
        return ResponseEntity.ok(average);
    }
}
