package vti.com.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vti.com.entity.Account;

import java.util.List;
import java.util.Optional;
import vti.com.entity.dto.AccountDto;

public interface IAccountService {
    List<AccountDto> findAll();

    Page<AccountDto> findAllWithPage(Pageable pageable);

    Optional<Account> findOne(Long id);

    Account createAccount(Account account);


    Account updateAccount(Long id, Account account) throws NotFoundException;

    Account deleteAccount(Long id) throws NotFoundException;

    Optional<Account> findAccountLikeLastName(String lastName );

    List<AccountDto>  findAccountLikeLastNameToDTO(String lastName) throws NotFoundException;
}
