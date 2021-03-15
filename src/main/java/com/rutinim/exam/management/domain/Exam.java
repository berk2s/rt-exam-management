package com.rutinim.exam.management.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "exam_name")
    private String examName;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp lastModifitedDate;

    @ManyToOne
    @JoinColumn(name = "exam_type_id", referencedColumnName = "id")
    private ExamType examType;

}
