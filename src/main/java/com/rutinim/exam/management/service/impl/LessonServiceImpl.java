package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.Lesson;
import com.rutinim.exam.management.repository.LessonRepository;
import com.rutinim.exam.management.service.LessonService;
import com.rutinim.exam.management.web.exception.LessonNotFoundException;
import com.rutinim.exam.management.web.mappers.LessonMapper;
import com.rutinim.exam.management.web.model.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    @Override
    public LessonDto getLesson(UUID lessonId) {
        Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);

        if(optionalLesson.isEmpty())
            throw new LessonNotFoundException("Lesson doesn't exists");

        return lessonMapper.lessonToLessonDto(optionalLesson.get());
    }

    @Override
    public void updateLesson(UUID lessonId, LessonDto lessonDto) {
        Lesson lesson = lessonRepository.getOne(lessonId);

        lesson.setLessonName(lessonDto.getLessonName());

        lessonRepository.save(lesson);
    }
}
