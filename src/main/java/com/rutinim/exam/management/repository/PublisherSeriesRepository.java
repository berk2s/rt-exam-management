package com.rutinim.exam.management.repository;

import com.rutinim.exam.management.domain.PublisherSeries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherSeriesRepository extends JpaRepository<PublisherSeries, UUID> {
}
