package vti.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vti.com.entity.Account;
import vti.com.entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long>,
    JpaSpecificationExecutor<Department> {

}
