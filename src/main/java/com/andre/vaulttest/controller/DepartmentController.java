package com.andre.vaulttest.controller;

import com.andre.vaulttest.dto.DepartmentDTO;
import com.andre.vaulttest.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {
    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Object> insertDepartment(HttpServletRequest req, @RequestBody DepartmentDTO departmentDTO) {
        Long id = departmentService.insertDepartment(departmentDTO);
        return validateInsertAndRespond(req, id);
    }
}
