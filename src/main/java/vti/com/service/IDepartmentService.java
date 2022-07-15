package vti.com.service;
import vti.com.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    List<Department> findAll();

    Optional<Department> findOne(Long id);

    Department createDepartment(Department department);

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);
}
