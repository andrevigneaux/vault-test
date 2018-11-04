package com.andre.vaulttest.service;

import com.andre.vaulttest.model.Department;
import com.andre.vaulttest.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DepartmentServiceTest {
    @Autowired
    DepartmentService departmentService;

    @Test
    public void should_validate_department_rules(){
        assertTrue(departmentService.isValidDepartment(1200D, DateUtil.getDate("17/02/2018")));
        assertTrue(departmentService.isValidDepartment(800D, DateUtil.getDate("01/01/2016")));

        assertFalse(departmentService.isValidDepartment(1200D, DateUtil.getDate("12/05/2002")));
        assertFalse(departmentService.isValidDepartment(1700D, DateUtil.getDate("31/01/2009")));
    }
}
