package com.rutinim.exam.management.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity(name = "exam_publisher")
@Getter
@Setter
@NoArgsConstructor
public class ExamPublisher {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(updatable = false, insertable = false)
    private UUID id;

    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(name = "publisher_id")
    private UUID publisherId;

    @Column(name = "publisher_name")
    private String publisherName;

    @Column(name = "number_of_series")
    private Integer numberOfSeries;

    @Column(name = "exam_image")
    private String examImage;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp lastModifitedDate;

    @ManyToOne
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @OneToMany(mappedBy = "examPublisher", cascade = CascadeType.ALL)
    private List<PublisherSeries> publisherSeries;

}
