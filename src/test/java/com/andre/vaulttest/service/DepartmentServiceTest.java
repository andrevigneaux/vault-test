package com.andre.vaulttest.service;

import com.andre.vaulttest.dto.DepartmentDTO;
import com.andre.vaulttest.mapper.DepartmentMapper;
import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.Location;
import com.andre.vaulttest.repository.DepartmentRepository;
import com.andre.vaulttest.repository.EmployeeRepository;
import com.andre.vaulttest.repository.LocationRepository;
import com.andre.vaulttest.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    public void should_validate_department_rules(){
        //Assert
        assertTrue(departmentService.isValidDepartment(1200D, DateUtil.getDate("17/02/2018")));
        assertTrue(departmentService.isValidDepartment(800D, DateUtil.getDate("01/01/2016")));

        assertFalse(departmentService.isValidDepartment(1200D, DateUtil.getDate("12/05/2002")));
        assertFalse(departmentService.isValidDepartment(1700D, DateUtil.getDate("31/01/2009")));
    }

    @Test
    public void should_insert_department() {
        //Assign
        Location location = createLocation("Juramento 5000");
        Location location2 = createLocation("Balbin 2040");

        Department dep1 = createDepartment("IT", location);
        Department dep2 = createDepartment("Mkt", location);
        Department dep3 = createDepartment("Finances", location2);

        createEmployee("Francis", dep1, 800D);
        createEmployee("Camille", dep2, 1100D);
        createEmployee("Nikolai", dep3 , 2000D);
        createEmployee("Pierre", dep3 , 1800D);

        DepartmentDTO depDto = new DepartmentDTO();
        depDto.setLocationId(location.getId());
        depDto.setName("Operations");

        DepartmentDTO depDto2 = new DepartmentDTO();
        depDto2.setLocationId(location2.getId());
        depDto2.setName("R&D");
        //Act
        Long id = departmentService.insertDepartment(depDto);
        Long id2 = departmentService.insertDepartment(depDto2);
        assertNotNull(id);
        assertNull(id2);
    }

    private Department createDepartment(String name, Location location) {
        Department department = new Department();
        department.setName(name);
        department.setLocation(location);
        departmentRepository.save(department);
        return department;
    }

    private Location createLocation(String name) {
        Location location = new Location();
        location.setStreetAddress(name);
        locationRepository.save(location);
        return location;
    }

    private Employee createEmployee(String name, Department department, Double salary) {
        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setDepartment(department);
        employee.setSalary(salary);
        employeeRepository.save(employee);
        return employee;
    }
}
