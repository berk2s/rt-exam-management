package com.rutinim.exam.management.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity(name = "publisher_series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherSeries {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(insertable = false, updatable = false)
    private UUID id;

    @Column(name = "sequence_name")
    private String sequenceName;

    @Column(name = "number_of_sequence")
    private Integer numberOfSequence;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp lastModifitedDate;

    @ManyToOne()
    @JoinColumn(name = "exam_publisher_id", referencedColumnName = "id")
    private ExamPublisher examPublisher;

}
