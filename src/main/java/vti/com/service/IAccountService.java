package vti.com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Account;

import java.util.Optional;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.form.AccountForm;
import vti.com.exception.NotFoundException;
import vti.com.service.criteria.AccountCriteria;
import vti.com.service.specification.Expression;

public interface IAccountService {

  Page<AccountDTO> findAll(AccountCriteria accountCriteria ,Pageable pageable);

  Optional<Account> findOne(Long id);

  Optional<AccountDTO> createAccount(AccountForm accountForm);

  Optional<AccountDTO> createAccount(Account account);

  Optional<AccountDTO> updateAccount(Long id, AccountForm accountForm) throws NotFoundException;

  AccountDTO deleteAccount(Long id) throws NotFoundException;

  Optional<AccountDTO> findOneToDTO(Long id);

  List<AccountDTO> search(Expression expression);

}
