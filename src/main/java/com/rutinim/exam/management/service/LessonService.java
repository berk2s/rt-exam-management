package com.rutinim.exam.management.service;

import com.rutinim.exam.management.web.model.LessonDto;

import java.util.UUID;


public interface LessonService {

    LessonDto getLesson(UUID lessonId);

    void updateLesson(UUID lessonId, LessonDto lessonDto);

}
