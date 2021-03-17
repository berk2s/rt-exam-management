package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
}
