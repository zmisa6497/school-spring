package dev.misha.school.model;

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

    @Column(name = "studentname", nullable = false)
    String name;

    @ElementCollection
    @CollectionTable(name = "grades", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "grade", nullable = false)
    List<Integer> grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void addGrade(int grade){
        grades.add(grade);
    }
}
