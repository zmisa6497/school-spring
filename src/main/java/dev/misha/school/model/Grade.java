package dev.misha.school.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.misha.school.repository.GradeRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    Student student;

    public Grade(Integer grade, Student student){
        this.grade = grade;
        this.student = student;
    }

}
