package vti.com.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
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
  public List<AccountDto> findAll() {
    List<Account> accountList = accountRepository.findAll();
    List<AccountDto> accountDtoList = new ArrayList<>();
    for (Account account : accountList
    ) {
      AccountDto accountDto = toDTO(account);
      accountDtoList.add(accountDto);
    }
    return accountDtoList;
  }

  @Override
  public Page<AccountDto> findAllWithPage(Pageable pageable) {
//    Page<Account> accountPage = accountRepository.findAll(pageable);
//    List<AccountDto> accountDtoList = new ArrayList<>();
//    accountPage.getContent().forEach(account -> {accountDtoList.add(toDTO(account));});
//    return new PageImpl<>(accountDtoList,pageable, accountPage.getTotalElements());

    Page<Account> accountPage = accountRepository.findAll(pageable);
    return new PageImpl<>(
        accountRepository.findAll(pageable).getContent().stream().map(this::toDTO)
            .collect(Collectors.toList()), pageable, accountPage.getTotalElements());
  }

  private AccountDto toDTO(Account account) {
    AccountDto accountDto = new AccountDto();
    accountDto.setId(account.getId());
    accountDto.setFirstName(account.getFirstName());
    accountDto.setLastName(account.getLastName());
    accountDto.setRole(account.getRole());
    accountDto.setUserName(account.getUserName());
    accountDto.setDepartmentName(account.getDepartment().getName());
    return accountDto;
  }

  @Override
  public Optional<Account> findOne(Long id) {
    if (accountRepository.existsById(id) == false) {
      String message = "Id not exist";
      new Exception().getMessage();
    } else {
      return accountRepository.findById(id);
    }

    return accountRepository.findById(id);
  }

  @Override
  public Account createAccount(Account account) {

    return accountRepository.save(account);
  }


  @Override
  public Account updateAccount(Long id, Account account) throws NotFoundException {
    return findOne(id).map(acc -> {
      account.setId(id);
      return accountRepository.save(account);
    }).orElseThrow(NotFoundException::new);
  }

  @Override
  public Account deleteAccount(Long id) throws NotFoundException {
    return findOne(id).map(acc -> {
      accountRepository.delete(acc);
      return acc;
    }).orElseThrow(NotFoundException::new);
  }

  @Override
  public Optional<Account> findAccountLikeLastName(String lastName) {
    return accountRepository.findByLastNameLike("%" + lastName + "%");
  }

  public List<AccountDto> findAccountLikeLastNameToDTO(String lastName) throws NotFoundException {
    return findAccountLikeLastName(lastName).stream().map(this::toDTO).collect(Collectors.toList());
  }
}
