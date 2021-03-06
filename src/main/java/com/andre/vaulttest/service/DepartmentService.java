package com.andre.vaulttest.service;

import com.andre.vaulttest.dto.DepartmentDTO;
import com.andre.vaulttest.mapper.DepartmentMapper;
import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Location;
import com.andre.vaulttest.repository.DepartmentRepository;
import com.andre.vaulttest.repository.LocationRepository;
import com.andre.vaulttest.util.DateUtil;
import com.andre.vaulttest.validation.DepartmentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Consumer;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentMapper departmentMapper;


    public Long insertDepartment(DepartmentDTO departmentDTO) throws ConstraintViolationException {
        if (departmentDTO.getLocationId() == null) {
            return null;
        }
        Optional optDepartmentLocation = locationRepository.findById(departmentDTO.getLocationId());
        OptionalDouble salaryAverage = OptionalDouble.empty();

        if (optDepartmentLocation.isPresent()) {
            Location departmentLocation = (Location) optDepartmentLocation.get();
            List<Department>  departments = departmentRepository.findByLocation(departmentLocation);
            salaryAverage = employeeService.getSalaryAveragePerDepartments(departments);
        }

        if (salaryAverage.isPresent()) {
            Department departmentToInsert = departmentMapper.DTOToBusiness(departmentDTO);
            if (isValidDepartment(salaryAverage.getAsDouble())) {
                departmentRepository.save(departmentToInsert);
                return departmentToInsert.getId();
            }
        }

        return -1L;
    }

    public boolean isValidDepartment(Double salaryAvg) {
        Date dateNow = DateUtil.getNow();
        return isValidDepartment(salaryAvg, dateNow);
    }

    public boolean isValidDepartment(Double salaryAvg, Date dateNow) {
        return (DepartmentValidator.firstFortnightDate.test(dateNow).isValid()
                    && DepartmentValidator.firstFortnightSalary.test(salaryAvg).isValid())
                ||
                (!DepartmentValidator.firstFortnightDate.test(dateNow).isValid()
                    && DepartmentValidator.secondFortnightSalary.test(salaryAvg).isValid());
    }
}
