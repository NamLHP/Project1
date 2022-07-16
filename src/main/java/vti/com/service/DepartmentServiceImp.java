package vti.com.service;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.entity.Account;
import vti.com.entity.Department;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.entity.form.DepartmentForm;
import vti.com.repository.IDepartmentRepository;

import java.util.Optional;

@Service
public class DepartmentServiceImp implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    public Page<DepartmentDTO> findAll(Pageable pageable) {
        Page<Department> departments = departmentRepository.findAll(pageable);
        return new PageImpl(
            departmentRepository.findAll(pageable).getContent().stream().map(this::toDTO)
                .collect(Collectors.toList()), pageable, departments.getTotalElements());
    }

    @Override
    public Optional<Department> findOne(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public DepartmentDTO createDepartment(Department department) {
        return toDTO(departmentRepository.save(department));
    }

//    @Override
//    public DepartmentDTO createDepartment(DepartmentForm departmentForm) {
////        Account account = new Account();
////        account = departmentRepository.findById(departmentForm.get);
//        Department department = new Department();
//        department.setName(departmentForm.getName());
//        department.setTotal_number(department.getTotal_number());
////        department.setAccountList(account);
//        return toDTO(departmentRepository.save(department));
//    }

    @Override
    @Transactional
    public DepartmentDTO updateDepartment(Long id, Department department) throws NotFoundException {
        return findOne(id).map(dep -> {
            department.setId(id);
            departmentRepository.save(department);
            return toDTO(department);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public DepartmentDTO deleteDepartment(Long id) throws NotFoundException {
        return findOne(id).map(dep -> {
            departmentRepository.deleteById(id);
            return toDTO(dep);
        }).orElseThrow(NotFoundException::new);
    }


    private DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setTotal_member(department.getTotal_number());
        departmentDTO.setType(department.getType());
        departmentDTO.setCreateDate(department.getCreateDate());
        return departmentDTO;
    }
}
