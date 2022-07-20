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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vti.com.entity.Department;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.service.DepartmentServiceImp;

import java.util.List;
import vti.com.service.IDepartmentService;
import vti.com.service.specification.Expression;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService IDepartmentServiceImp;

    @GetMapping("")
    ResponseEntity<Page<DepartmentDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(IDepartmentServiceImp.findAll(pageable));
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<DepartmentDTO>> findOneToDTO(@PathVariable Long id) {
        return ResponseEntity.ok().body(IDepartmentServiceImp.findOneToDTO(id));
    }

    @GetMapping("search")
    ResponseEntity<List<DepartmentDTO>> search(
        @RequestParam String field,
        @RequestParam String operator,
        @RequestParam String value) {
        return ResponseEntity.ok()
            .body(IDepartmentServiceImp.search(new Expression(field, operator, value)));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<DepartmentDTO> deleteDepartmentByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(IDepartmentServiceImp.deleteDepartment(id));
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
            return ResponseEntity.ok().body(IDepartmentServiceImp.createDepartment(department));
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@RequestBody Department department,
        @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(IDepartmentServiceImp.updateDepartment(id,department));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
