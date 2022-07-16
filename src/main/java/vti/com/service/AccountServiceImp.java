package vti.com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.entity.Account;
import vti.com.entity.dto.AccountDto;
import vti.com.repository.IAccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements IAccountService {

    private final IAccountRepository accountRepository;

    public AccountServiceImp(IAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Page<AccountDto> findAll(Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(pageable);
        return new PageImpl<>(
            accountRepository.findAll(pageable).getContent().stream().map(this::toDTO)
                .collect(Collectors.toList()), pageable, accountPage.getTotalElements());
    }


    @Override
    public Optional<Account> findOne(Long id) {
        if (accountRepository.existsById(id) == false) {
            new Exception().getMessage();
        } else {
            return accountRepository.findById(id);
        }

        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public AccountDto createAccount(Account account) {

        return toDTO(accountRepository.save(account));
    }


    @Override
    @Transactional
    public AccountDto updateAccount(Long id, Account account) throws NotFoundException {
        return findOne(id).map(acc -> {
            account.setId(id);
            return toDTO(accountRepository.save(account));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public AccountDto deleteAccount(Long id) throws NotFoundException {
        return findOne(id).map(acc -> {
            accountRepository.delete(acc);
            return toDTO(acc);
        }).orElseThrow(NotFoundException::new);
    }


    private AccountDto toDTO(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setFirstName(account.getFirstName());
        accountDto.setLastName(account.getLastName());
        accountDto.setRole(account.getRole());
        accountDto.setUserName(account.getUserName());
//        accountDto.setDepartmentName(account.getDepartment().getName());
        return accountDto;
    }
}
