package com.glassdoor.test.intern.first.repository;

import com.glassdoor.test.intern.first.entity.ProcessedPayments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedPaymentsRepository extends JpaRepository<ProcessedPayments, Long> {
}
