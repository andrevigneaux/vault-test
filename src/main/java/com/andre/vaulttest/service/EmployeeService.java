package com.andre.vaulttest.service;

import com.andre.vaulttest.dto.EmployeeDTO;
import com.andre.vaulttest.mapper.EmployeeMapper;
import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.Job;
import com.andre.vaulttest.repository.EmployeeRepository;
import com.andre.vaulttest.repository.JobHistoryRepository;
import com.andre.vaulttest.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    JobHistoryRepository jobHistoryRepository;

    private final Integer defaultStartAtPagination = 0;
    private final Integer defaultMaxResultsPagination = 10;

    public Long insertEmployee(EmployeeDTO employee) {
        Employee newEmployee = employeeMapper.DTOToBusiness(employee);
        employeeRepository.save(newEmployee);
        return newEmployee.getId();
    }

    public Employee updateEmployee(Long id, EmployeeDTO employeeDTO) {
        if (employeeRepository.existsById(id)) {
            Employee existingEmployee = employeeRepository.findById(id).get();
            employeeMapper.DTOToBusiness(employeeDTO, existingEmployee);
            return employeeRepository.save(existingEmployee);
        } else {
            return null;
        }
    }

    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else return false;
    }

    public EmployeeDTO getOneEmployee(Long id) {
        Optional optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()){
            return employeeMapper.BusinessToDTO((Employee) optEmployee.get());
        } else return null;
    }

    /**
     * Method that returns a list of employees with all its relations
     * @param jobId: optional filter
     * @param managerId: optional filter
     * @param lastName: optional filter
     * @param optStartAt: Pagination start (optional)
     * @param optMaxResults: : Maximum results from pagination start (optional)
     * @return List of Employee with all its relations.
     */
    public List<Employee> getEmployees(Optional jobId, Optional managerId, Optional lastName, Optional optStartAt, Optional optMaxResults) {
        Employee employee = new Employee();
        Integer startAt = defaultStartAtPagination;
        Integer maxResults = defaultMaxResultsPagination;
        if (optStartAt.isPresent()) startAt = (Integer) optStartAt.get();
        if (optMaxResults.isPresent()) maxResults = (Integer) optMaxResults.get();

        if (jobId.isPresent()) {
            Optional optJob = jobRepository.findById((Long) jobId.get());
            if (optJob.isPresent()) {
                Job job = (Job) optJob.get();
                employee.setJob(job);
            }
        }

        if (managerId.isPresent()) {
            Optional optManager = employeeRepository.findById((Long) managerId.get());
            if (optManager.isPresent()) {
                Employee manager = (Employee) optManager.get();
                employee.setManager(manager);
            }
        }

        if (lastName.isPresent()) employee.setLastName((String) lastName.get());

        Page<Employee> employees = employeeRepository
                .findAll(Example.of(employee),
                        PageRequest.of(startAt, maxResults));

        employees.forEach(e -> setEmployeeRelations(e));

        return employees
                .stream()
                .sorted(Comparator.comparing(Employee::getHireDate))
                .collect(Collectors.toList());
    }

    public OptionalDouble getSalaryAveragePerDepartments(List<Department> departments) {
        List<Employee> employees = new ArrayList<>();

        departments
                .stream()
                .map(department ->
                   employees.addAll(employeeRepository.findByDepartment(department))
                );

        return employees
                .stream()
                .mapToDouble(Employee::getSalary)
                .average();
    }

    private void setEmployeeRelations(Employee employee) {
        employee.setJobsHistory(jobHistoryRepository.findJobHistoryOfEmployee(employee));
    }
}
