package com.andre.vaulttest.mapper;

import com.andre.vaulttest.dto.EmployeeDTO;
import com.andre.vaulttest.model.Employee;
import com.andre.vaulttest.util.CoreUtil;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements CustomMapper<Employee, EmployeeDTO> {

    @Override
    public Employee DTOToBusiness(EmployeeDTO dto) {
        Employee employee = new Employee();
        map(employee, dto);
        return employee;
    }

    @Override
    public EmployeeDTO BusinessToDTO(Employee business) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        CoreUtil.setIfNotNull(employeeDTO::setFirstName, business.getFirstName());
        CoreUtil.setIfNotNull(employeeDTO::setLastName, business.getLastName());
        CoreUtil.setIfNotNull(employeeDTO::setEmail, business.getEmail());
        CoreUtil.setIfNotNull(employeeDTO::setPhoneNumber, business.getPhoneNumber());
        CoreUtil.setIfNotNull(employeeDTO::setHireDate, business.getHireDate());
        CoreUtil.setIfNotNull(employeeDTO::setSalary, business.getSalary());
        CoreUtil.setIfNotNull(employeeDTO::setCommissionPercentage, business.getCommissionPercentage());
        return employeeDTO;
    }

    @Override
    public void DTOToBusiness(EmployeeDTO dto, Employee business) {
        map(business, dto);
    }

    private void map(Employee employee, EmployeeDTO dto) {
        CoreUtil.setIfNotNull(employee::setFirstName, dto.getFirstName());
        CoreUtil.setIfNotNull(employee::setLastName, dto.getLastName());
        CoreUtil.setIfNotNull(employee::setEmail, dto.getEmail());
        CoreUtil.setIfNotNull(employee::setPhoneNumber, dto.getPhoneNumber());
        CoreUtil.setIfNotNull(employee::setHireDate, dto.getHireDate());
        CoreUtil.setIfNotNull(employee::setSalary, dto.getSalary());
        CoreUtil.setIfNotNull(employee::setCommissionPercentage, dto.getSalary());
    }


}
