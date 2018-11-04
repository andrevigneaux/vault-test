package com.andre.vaulttest.repository;

import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByJob(Job job);
    List<Employee> findByDepartment(Department department);
}
