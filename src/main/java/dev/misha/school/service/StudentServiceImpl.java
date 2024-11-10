package dev.misha.school.service;

import dev.misha.school.exception.GradeSaveException;
import dev.misha.school.exception.JournalException;
import dev.misha.school.exception.StudentNotFoundException;
import dev.misha.school.model.Grade;
import dev.misha.school.model.Student;
import dev.misha.school.repository.GradeRepository;
import dev.misha.school.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    GradeRepository gradeRepository;


    @Override
    public void addStudent(@NotNull String name) throws GradeSaveException {
        studentRepository.save(new Student(name));
    }


    @Override
    public void removeStudent(@NotNull String name) throws JournalException {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new StudentNotFoundException("Student with name %s not found" , name));
        studentRepository.delete(student);
    }

    @Override
    public void addGrades(@NotNull String name,@NotNull String gradesInput) throws JournalException {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new StudentNotFoundException("Student with name %s not found", name));

        String[] gradesStr = gradesInput.split("[,\\s]+");  // парсимо рядки вводу оцінок у зручний спосіб, а саме через кому або пробел

        for (String gradeStr : gradesStr){
            try {
                int grade = Integer.parseInt(gradeStr);
                if(grade >= 0 && grade <= 12){
                    Grade newGrade = new Grade(grade, student);
                    student.addGrade(newGrade);

                }
            }catch (NumberFormatException ignored){
            }
        }
        studentRepository.save(student);
    }

    @Override
    @Cacheable(value = "students", key = "#name")
    public @NotNull Student getStudentByName(@NotNull String name) throws JournalException {
        return studentRepository.findByName(name)
                .orElseThrow(() -> new StudentNotFoundException("Student with name %s not found", name));
    }

    @Override
    @Cacheable("students")
    public List<Student> getAll() {
        return studentRepository.findAll();
    }


    @Override
    public Double calculateAverageGrade(@NotNull String name) throws JournalException {
        Student student = studentRepository.findByName(name)
                .orElseThrow(() -> new StudentNotFoundException("Student with name " + name + " not found"));

        Double averageGrade = gradeRepository.findAverageGradeByStudentId(student.getId());
        if (averageGrade == null) {
            throw new JournalException("У студента немає оцінок для обчислення середнього бала.");
        }
        return averageGrade;
    }
}


