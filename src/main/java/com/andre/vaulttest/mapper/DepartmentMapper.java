package com.andre.vaulttest.mapper;

import com.andre.vaulttest.dto.DepartmentDTO;
import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.Location;
import com.andre.vaulttest.repository.EmployeeRepository;
import com.andre.vaulttest.repository.LocationRepository;
import com.andre.vaulttest.util.CoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DepartmentMapper implements CustomMapper<Department, DepartmentDTO> {
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Department DTOToBusiness(DepartmentDTO dto) {
        Department business = new Department();
        Optional optLocation = locationRepository.findById(dto.getLocationId());
        Optional optManager = employeeRepository.findById(dto.getManagerId());

        CoreUtil.setIfNotNull(business::setName, dto.getName());
        if (optLocation.isPresent())
            business.setLocation((Location) optLocation.get());
        if (optManager.isPresent())
            business.setManager((Employee) optManager.get());

        return business;
    }

    @Override
    public DepartmentDTO BusinessToDTO(Department business) {
        return null;
    }

    @Override
    public void DTOToBusiness(DepartmentDTO dto, Department business) {

    }
}
