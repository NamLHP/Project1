package vti.com.service;

import java.util.List;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Account;

import java.util.Optional;
import vti.com.entity.dto.AccountDTO;
import vti.com.service.specification.Expression;

public interface IAccountService {

  Page<AccountDTO> findAll(Pageable pageable);

  Optional<Account> findOne(Long id);

  AccountDTO createAccount(Account account);

  AccountDTO updateAccount(Long id, Account account) throws NotFoundException;

  AccountDTO deleteAccount(Long id) throws NotFoundException;

  Optional<AccountDTO> findOneToDTO(Long id);

  List<AccountDTO> search(Expression expression);

}
