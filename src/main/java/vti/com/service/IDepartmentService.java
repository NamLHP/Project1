package vti.com.service;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Department;

import java.util.List;
import java.util.Optional;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.service.specification.Expression;


public interface IDepartmentService {

    Page<DepartmentDTO> findAll(Pageable pageable);

    Optional<Department> findOne(Long id);

    DepartmentDTO createDepartment(Department department);

    DepartmentDTO updateDepartment(Long id, Department department) throws NotFoundException;

    DepartmentDTO deleteDepartment(Long id) throws NotFoundException;

    Optional<DepartmentDTO> findOneToDTO(Long id);

    List<DepartmentDTO> search(Expression expression);
}
