package vti.com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.Constants.DEPARTMENT;
import vti.com.entity.Department;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.entity.form.DepartmentForm;
import vti.com.repository.IDepartmentRepository;
import vti.com.service.criteria.DepartmentCriteria;
import vti.com.service.specification.DepartmentSpecification;
import vti.com.service.specification.Expression;
import vti.com.service.specification.QueryService;

@Service
public class DepartmentServiceImp implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;

    private final ModelMapper modelMapper;

    private final QueryService<Department> queryService;

    public DepartmentServiceImp(IDepartmentRepository departmentRepository,
        ModelMapper modelMapper,
        QueryService<Department> queryService) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.queryService = queryService;
    }

    public Page<DepartmentDTO> findAll(DepartmentCriteria departmentCriteria, Pageable pageable) {
        Specification<Department> specification = buildSpecification(departmentCriteria);
        Page<Department> page = departmentRepository.findAll(specification, pageable);
        List<DepartmentDTO> dtoList = page.getContent().stream()
            .map(department -> modelMapper.map(department, DepartmentDTO.class)).collect(
                Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }

    @Override
    public Optional<Department> findOne(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public DepartmentDTO createDepartment(DepartmentForm departmentForm) {
        Department department = modelMapper.map(departmentForm, Department.class);
        return modelMapper.map(departmentRepository.save(department), DepartmentDTO.class);
    }


    @Override
    @Transactional
    public DepartmentDTO updateDepartment(Long id, DepartmentForm departmentForm)
        throws NotFoundException {
        return findOne(id).map(dep -> {
            Department department = modelMapper.map(departmentForm, Department.class);
            department.setId(departmentForm.getId());
            return modelMapper.map(departmentRepository.save(department), DepartmentDTO.class);
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


    Specification<Department> buildWhere(Expression expression) {
        DepartmentSpecification departmentSpecification = new DepartmentSpecification(expression);
        return Specification.where(departmentSpecification);
    }


    Specification<Department> buildSpecification(DepartmentCriteria departmentCriteria) {
        Specification<Department> specification = Specification.where(null);

        if (departmentCriteria.getId() != null) {
            specification = specification
                .and(queryService.buildLongFilter(DEPARTMENT.ID, departmentCriteria.getId()));
        }

        if (departmentCriteria.getName() != null) {
            specification = specification
                .and(queryService
                    .buildStringFilter(DEPARTMENT.NAME, departmentCriteria.getName()));
        }

        if (departmentCriteria.getTotalMember() != null) {
            specification = specification
                .and(queryService
                    .buildIntegerFilter(DEPARTMENT.TOTAL_MEMBER,
                        departmentCriteria.getTotalMember()));
        }

        if (departmentCriteria.getCreateDate() != null) {
            specification = specification
                .and(queryService
                    .buildDateFilter(DEPARTMENT.CREATED_DATE, departmentCriteria.getCreateDate()));
        }

        return specification;
    }


    private DepartmentDTO toDTO(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setName(department.getName());
        departmentDTO.setTotalMember(department.getTotalMember());
        departmentDTO.setType(department.getType());
        departmentDTO.setCreateDate(department.getCreateDate());
        return departmentDTO;
    }
}
