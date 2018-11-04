package com.andre.vaulttest.repository;

import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.JobHistory;
import com.andre.vaulttest.model.pk.JobHistoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobHistoryRepository extends JpaRepository<JobHistory, JobHistoryPK> {
    @Query("SELECT j FROM JobHistory j where j.id.employee = :empId")
    List<JobHistory> findJobHistoryOfEmployee(@Param("empId") Employee employeeId);
}
