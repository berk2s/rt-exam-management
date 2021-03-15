package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.ExamPublisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamPublisherRepository extends JpaRepository<ExamPublisher, UUID> {
}
