package dev.misha.school.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.misha.school.repository.GradeRepository;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    Long id;

    @Column(name = "student_name", nullable = false)
    String name;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Grade> grades;

    public Student(String name) {
        this.name = name;
    }

    public void addGrade(Grade grade){
        grades.add(grade);
    }
}
