package com.andre.vaulttest.repository;

import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByLocation(Location location);
}
