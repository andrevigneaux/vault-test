package com.andre.vaulttest.service;

import com.andre.vaulttest.dto.EmployeeDTO;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.model.Job;
import com.andre.vaulttest.model.JobHistory;
import com.andre.vaulttest.model.pk.JobHistoryPK;
import com.andre.vaulttest.repository.EmployeeRepository;
import com.andre.vaulttest.repository.JobHistoryRepository;
import com.andre.vaulttest.repository.JobRepository;
import com.andre.vaulttest.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobHistoryRepository jobHistoryRepository;

    @Test
    public void should_insert_new_employee() {
        Long insertedId = employeeService.insertEmployee(getNewEmployeeDTO());
        assertTrue(insertedId >= 1);
    }

    @Test
    public void should_update_existing_employee() {
        Long id = employeeService.insertEmployee(getNewEmployeeDTO());
        EmployeeDTO employeeToUpdate = new EmployeeDTO();
        employeeToUpdate.setLastName("VII");
        Employee updatedEmployee= employeeService.updateEmployee(id, employeeToUpdate);
        assertTrue(updatedEmployee.getFirstName().equals("George")
                && updatedEmployee.getLastName().equals("VII"));
    }

    @Test
    public void should_get_all_employees_by_job() {
        //Assign
        Job jobDev = jobRepository.save(getNewJob("dev"));
        Job jobQA = jobRepository.save(getNewJob("qa"));
        employeeRepository.save(getNewEmployeeWithJob("Peter", jobDev));
        employeeRepository.save(getNewEmployeeWithJob("Claudia", jobQA));

        List<Employee> employees = employeeService.getEmployees(Optional.of(jobDev.getId()),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty());

        assertTrue(employees.size() == 1 && employees.get(0).getFirstName().equals("Peter"));
    }

    @Test
    public void should_get_all_employees_with_all_filters_and_pagination() {
        //Assign
        Job jobMan = jobRepository.save(getNewJob("manager"));
        Job jobSales = jobRepository.save(getNewJob("sales"));
        Job jobMkt = jobRepository.save(getNewJob("marketing"));

        Employee managerEmp = getNewEmployeeWithJob("George", "Smith", jobMan);
        Employee mktEmp = getNewEmployeeWithJob("John", "Doe", jobMkt);
        Employee mktEmp2 = getNewEmployeeWithJob("Jack", "Doe", jobMkt);
        Employee mktEmp3 = getNewEmployeeWithJob("Jerry", "Doe", jobMkt);

        mktEmp.setManager(managerEmp);
        mktEmp2.setManager(managerEmp);
        mktEmp3.setManager(managerEmp);

        mktEmp.setHireDate(DateUtil.getDate("20/02/2017"));
        mktEmp2.setHireDate(DateUtil.getDate("16/12/2015"));
        mktEmp3.setHireDate(DateUtil.getDate("01/01/2012"));

        employeeRepository.save(managerEmp);
        employeeRepository.save(mktEmp);
        employeeRepository.save(mktEmp2);
        employeeRepository.save(mktEmp3);
        employeeRepository.save(getNewEmployeeWithJob("Susane", jobSales));

        List<Employee> employeesPag1 = employeeService.getEmployees(Optional.of(jobMkt.getId()),
                Optional.of(managerEmp.getId()),
                Optional.of(mktEmp.getLastName()),
                Optional.of(0),
                Optional.of(2));

        assertTrue(employeesPag1.size() == 2 && employeesPag1.get(0).getFirstName().equals("Jack"));

        List<Employee> employeesPag2 = employeeService.getEmployees(Optional.of(jobMkt.getId()),
                Optional.of(managerEmp.getId()),
                Optional.of(mktEmp.getLastName()),
                Optional.of(1),
                Optional.of(2));

        assertTrue(employeesPag2.size() == 1);
    }

    @Test
    public void should_get_all_employees_full_fetched() {
    }


    private EmployeeDTO getNewEmployeeDTO() {
        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName("George");
        employee.setLastName("VI");
        return employee;
    }

    private Employee getNewEmployeeWithJob(String name, Job job) {
        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setJob(job);
        return employee;
    }

    private Employee getNewEmployeeWithJob(String name, String lastName, Job job) {
        Employee employee = new Employee();
        employee.setFirstName(name);
        employee.setLastName(lastName);
        employee.setJob(job);
        return employee;
    }

    private Job getNewJob(String name) {
        Job job = new Job();
        job.setTitle(name);
        return job;
    }

    private void createJobHistoryForEmployee(Employee employee) {
        JobHistory jobHistory = new JobHistory();
        JobHistoryPK jobHistoryPK = new JobHistoryPK();
        jobHistoryPK.setEmployee(employee);
        jobHistoryPK.setStartDate(new Date());
        jobHistory.setId(jobHistoryPK);
        jobHistoryRepository.save(jobHistory);
    }

    private void assignManagerToEmployee(Employee employee, Employee manager) {
        employee.setManager(manager);
    }
}
