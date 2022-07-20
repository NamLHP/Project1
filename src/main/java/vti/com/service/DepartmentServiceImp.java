package vti.com.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vti.com.entity.Department;

import vti.com.entity.dto.DepartmentDTO;
import vti.com.repository.IDepartmentRepository;

import java.util.Optional;

import vti.com.service.specification.DepartmentSpecification;
import vti.com.service.specification.Expression;

@Service
public class DepartmentServiceImp implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    public DepartmentServiceImp(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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


    @Override
    public Optional<DepartmentDTO> findOneToDTO(Long id) {
        // Optional<Account> accountOptional = findOne(id);
//        if (accountOptional.get() == null) { // accountOptional.get() bởi vì là optional nên cần dùng get() để láy ra object
//            return Optional.empty();
//        } else {
//            AccountDTO accountDTO = modelMapper.map(accountOptional.get(), AccountDTO.class);
//            return Optional.of(accountDTO);
//        }

        return findOne(id).map(department -> modelMapper.map(department, DepartmentDTO.class));
    }

    @Override
    public List<DepartmentDTO> search(Expression expression) {
        Specification<Department> spec = buildWhere(expression);
        List<Department> list = departmentRepository.findAll(spec);
        return modelMapper.map(list, new TypeToken<List<DepartmentDTO>>() {
        }.getType());
    }

    Specification<Department> buildWhere(Expression expression) {
        DepartmentSpecification departmentSpecification = new DepartmentSpecification(expression);
        return Specification.where(departmentSpecification);
    }


    private DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setTotal_member(department.getTotalNumber());
        departmentDTO.setType(department.getType());
        departmentDTO.setCreateDate(department.getCreateDate());
        return departmentDTO;
    }
}
