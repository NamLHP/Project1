package vti.com.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.entity.Account;
import vti.com.entity.dto.AccountDTO;
import vti.com.repository.IAccountRepository;

import java.util.Optional;
import vti.com.service.specification.AccountSpecification;
import vti.com.service.specification.Expression;

@Service
public class AccountServiceImp implements IAccountService {

    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImp(IAccountRepository accountRepository,
        ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<AccountDTO> findAll(Pageable pageable) {
        Page<Account> accountPage = accountRepository.findAll(pageable);
        return new PageImpl<>(
            accountRepository.findAll(pageable).getContent().stream().map(this::toDTO)
                .collect(Collectors.toList()), pageable, accountPage.getTotalElements());
    }


    @Override
    public Optional<Account> findOne(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public AccountDTO createAccount(Account account) {

        return toDTO(accountRepository.save(account));
    }


    @Override
    @Transactional
    public AccountDTO updateAccount(Long id, Account account) throws NotFoundException {
        return findOne(id).map(acc -> {
            account.setId(id);
            return toDTO(accountRepository.save(account));
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public AccountDTO deleteAccount(Long id) throws NotFoundException {
        return findOne(id).map(acc -> {
            accountRepository.delete(acc);
            return toDTO(acc);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public Optional<AccountDTO> findOneToDTO(Long id) {
        // Optional<Account> accountOptional = findOne(id);
//        if (accountOptional.get() == null) { // accountOptional.get() bởi vì là optional nên cần dùng get() để láy ra object
//            return Optional.empty();
//        } else {
//            AccountDTO accountDTO = modelMapper.map(accountOptional.get(), AccountDTO.class);
//            return Optional.of(accountDTO);
//        }

        return findOne(id).map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public List<AccountDTO> search(Expression expression) {
        Specification<Account> spec = buildWhere(expression);
        List<Account> list = accountRepository.findAll(spec);
        return modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
        }.getType());
    }

    Specification<Account> buildWhere(Expression expression) {
        AccountSpecification accountSpecification = new AccountSpecification(expression);
        return Specification.where(accountSpecification);
    }


    private AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setRole(account.getRole());
        accountDTO.setUserName(account.getUserName());
        accountDTO.setDepartmentName(account.getDepartment().getName());
        return accountDTO;
    }
}
