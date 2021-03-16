package com.rutinim.exam.management.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "exam_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamType {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "exam_duration")
    private Integer examDuration;

    @Column(name = "is_one_piece")
    private Boolean isOnePiece;

    @Column(name = "is_preparatory_exam")
    private Boolean isPreparatoryExam;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp lastModifitedDate;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "examType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamField> examFields = new ArrayList<>();

    public void addExamField(ExamField examField){
        examField.setExamType(this);
        this.examFields.add(examField);
    }

}
