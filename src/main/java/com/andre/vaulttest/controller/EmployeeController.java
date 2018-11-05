package com.andre.vaulttest.controller;

import com.andre.vaulttest.dto.EmployeeDTO;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BaseController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id) {
        EmployeeDTO employeeDTO = employeeService.getOneEmployee(id);
        if (employeeDTO == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.accepted().body(employeeDTO);
    }

    @PostMapping
    public ResponseEntity<Object> insertEmployee(HttpServletRequest req,
                                               @RequestBody @Valid EmployeeDTO employeeDTO) {
        Long id = employeeService.insertEmployee(employeeDTO);

        return validateInsertAndRespond(req, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEmployee(HttpServletRequest request,
                                               @PathVariable Long id,
                                               @RequestBody EmployeeDTO employeeDTO) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDTO);

        if (updatedEmployee != null) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(HttpServletRequest request,
                                               @PathVariable Long id) {

        boolean deleted = employeeService.deleteEmployee(id);

        if (deleted) {
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(value = "jobId") Optional<Long> jobId,
                                          @RequestParam(value = "managerId") Optional<Long> managerId,
                                          @RequestParam(value = "lastName") Optional<String> employeeLastName,
                                          @RequestParam(value = "startAt") Optional<Integer> startAt,
                                          @RequestParam(value = "maxResults") Optional<Integer> maxResults) {

        List<Employee> employees = employeeService.getEmployees(jobId, managerId, employeeLastName, startAt, maxResults);
        return employees;
    }
}
