package vti.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import vti.com.entity.Department;
import vti.com.service.DepartmentServiceImp;

import java.util.List;

public class DepartmentController {
    @Autowired
    private DepartmentServiceImp departmentServiceImp;

    ResponseEntity<List<Department>> findAll() {
        return ResponseEntity.ok(departmentServiceImp.findAll());
    }
}
