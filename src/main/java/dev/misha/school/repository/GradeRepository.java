package dev.misha.school.repository;

import dev.misha.school.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    @Query("SELECT AVG(g.grade) FROM Grade g WHERE g.student.id = :studentId")
    Double findAverageGradeByStudentId(@Param("studentId") Long studentId);
}

