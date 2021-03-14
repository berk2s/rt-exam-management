package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {
}
