package vti.com.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vti.com.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long>,
    JpaSpecificationExecutor<Account> {
Optional<Account> findByLastNameLike(String lastName);
}
