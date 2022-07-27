package vti.com.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Department;

import java.util.List;
import java.util.Optional;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.entity.form.DepartmentForm;
import vti.com.service.criteria.DepartmentCriteria;
import vti.com.service.specification.Expression;


public interface IDepartmentService {

    Page<DepartmentDTO> findAll(DepartmentCriteria departmentCriteria, Pageable pageable);

    Optional<Department> findOne(Long id);

    DepartmentDTO createDepartment(DepartmentForm departmentForm);

    DepartmentDTO updateDepartment(Long id, DepartmentForm departmentForm) throws NotFoundException;


    DepartmentDTO deleteDepartment(Long id) throws NotFoundException;

    Optional<DepartmentDTO> findOneToDTO(Long id);


}
