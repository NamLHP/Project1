package vti.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vti.com.entity.Department;
import vti.com.repository.IDepartmentRepository;

import java.util.List;
import java.util.Optional;
@Service
public class DepartmentServiceImp implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findOne(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
