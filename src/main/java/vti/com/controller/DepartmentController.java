package vti.com.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vti.com.entity.Department;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.entity.form.DepartmentForm;
import vti.com.service.DepartmentServiceImp;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImp departmentServiceImp;

    @GetMapping("")
    ResponseEntity<Page<DepartmentDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(departmentServiceImp.findAll(pageable));
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<Department>> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(departmentServiceImp.findOne(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DepartmentDTO> deleteDepartmentByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(departmentServiceImp.deleteDepartment(id));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("")
//    public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentForm departmentForm) {
//        try {
//            return ResponseEntity.ok().body(departmentServiceImp.createDepartment(departmentForm));
//        } catch (NullPointerException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("")
    public ResponseEntity<DepartmentDTO> create(@RequestBody Department department) {
        try {
            return ResponseEntity.ok().body(departmentServiceImp.createDepartment(department));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@RequestBody Department department,
        @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(departmentServiceImp.updateDepartment(id,department));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
