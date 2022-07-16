package vti.com.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Account;

import java.util.List;
import java.util.Optional;
import vti.com.entity.dto.AccountDto;

public interface IAccountService {

  Page<AccountDto> findAll(Pageable pageable);

  Optional<Account> findOne(Long id);

  AccountDto createAccount(Account account);


  AccountDto updateAccount(Long id, Account account) throws NotFoundException;

  AccountDto deleteAccount(Long id) throws NotFoundException;



}
