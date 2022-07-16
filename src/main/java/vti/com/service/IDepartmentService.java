package vti.com.service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Department;

import java.util.List;
import java.util.Optional;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.entity.form.DepartmentForm;

public interface IDepartmentService {

    Page<DepartmentDTO> findAll(Pageable pageable);

    Optional<Department> findOne(Long id);

//    DepartmentDTO createDepartment(DepartmentForm departmentForm);

    DepartmentDTO createDepartment(Department department);

    DepartmentDTO updateDepartment(Long id, Department department) throws NotFoundException;

    DepartmentDTO deleteDepartment(Long id) throws NotFoundException;
}
