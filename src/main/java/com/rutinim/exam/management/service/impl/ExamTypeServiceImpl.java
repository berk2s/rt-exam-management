package com.rutinim.exam.management.service.impl;

import com.rutinim.exam.management.domain.ExamType;
import com.rutinim.exam.management.repository.ExamTypeRepository;
import com.rutinim.exam.management.service.ExamTypeService;
import com.rutinim.exam.management.web.exception.ExamTypeNotFoundException;
import com.rutinim.exam.management.web.mappers.ExamTypeMapper;
import com.rutinim.exam.management.web.model.ExamTypeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamTypeServiceImpl implements ExamTypeService {

    private final ExamTypeRepository examTypeRepository;
    private final ExamTypeMapper examTypeMapper;

    @Override
    public List<ExamTypeDto> listExamTypes() {
        return examTypeMapper.examTypeToExamTypeDto(examTypeRepository.findAll());
    }

    @Override
    public ExamTypeDto getExamType(UUID examTypeId) {
        Optional<ExamType> optionalExamType = examTypeRepository.findById(examTypeId);

        if(optionalExamType.isEmpty())
            throw new ExamTypeNotFoundException("Exam Type doesn't exists");

        return examTypeMapper.examTypeToExamTypeDto(optionalExamType.get());
    }

    @Override
    public void saveExamType(ExamTypeDto examTypeDto) {
        examTypeRepository.save(examTypeMapper.examTypeDtoToExamType(examTypeDto));
    }

    @Override
    public void addExamField(UUID examTypeId, ExamTypeDto examTypeDto) {
        ExamType tempExamType = examTypeMapper.examTypeDtoToExamType(examTypeDto);
        ExamType examType = examTypeRepository.getOne(examTypeId);

        tempExamType.getExamFields().forEach(examType::addExamField);

        examTypeRepository.save(examType);
    }

    @Override
    public void updateExamType(UUID examTypeId, ExamTypeDto examTypeDto) {
        ExamType examType = examTypeRepository.getOne(examTypeId);

        examType.setTypeName(examTypeDto.getTypeName());
        examType.setExamDuration(examTypeDto.getExamDuration());
        examType.setIsPreparatoryExam(examTypeDto.getIsPreparatoryExam());
        examType.setIsOnePiece(examTypeDto.getIsOnePiece());

        if(examTypeDto.getExamFieldsDto().size() > 0) {
            examType.getExamFields().removeIf(f -> examTypeDto.getExamFieldsDto().stream().noneMatch(c -> c.getExamFieldId().equals(f.getId().toString())));
        }else {
            examType.getExamFields().clear();
        }

        examTypeRepository.save(examType);
    }

    @Override
    public void deleteExamType(UUID examTypeId) {
        examTypeRepository.delete(examTypeRepository.getOne(examTypeId));
    }

}
