package vti.com.controller;

import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import vti.com.service.IDepartmentService;
import vti.com.service.criteria.DepartmentCriteria;

@RestController
@RequestMapping("api/v1/departments")
@Validated
public class DepartmentController {

    private final IDepartmentService departmentService;

    public DepartmentController(
        IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("")
    ResponseEntity<Page<DepartmentDTO>> findAll(DepartmentCriteria departmentCriteria,
        Pageable pageable) {
        return ResponseEntity.ok(departmentService.findAll(departmentCriteria, pageable));
    }

    @GetMapping("{id}")
    ResponseEntity<Optional<DepartmentDTO>> findOneToDTO(@PathVariable Long id) {
        return ResponseEntity.ok().body(departmentService.findOneToDTO(id));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<DepartmentDTO> deleteDepartmentByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(departmentService.deleteDepartment(id));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<DepartmentDTO> create(@RequestBody @Valid DepartmentForm departmentForm) {
        return ResponseEntity.ok().body(departmentService.createDepartment(departmentForm));
    }


    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> update(@RequestBody @Valid DepartmentForm departmentForm,
        @PathVariable Long id) {
        try {
            return ResponseEntity.ok()
                .body(departmentService.updateDepartment(id, departmentForm));
        } catch (NullPointerException | NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
