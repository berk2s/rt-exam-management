package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamTypeRepository extends JpaRepository<ExamType, UUID> {
}
