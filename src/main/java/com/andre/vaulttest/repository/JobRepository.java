package com.andre.vaulttest.repository;

import com.andre.vaulttest.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
