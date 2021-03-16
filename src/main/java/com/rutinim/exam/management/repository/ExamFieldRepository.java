package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamField;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamFieldRepository extends JpaRepository<ExamField, UUID> {
}
