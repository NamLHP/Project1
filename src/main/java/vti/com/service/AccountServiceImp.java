package vti.com.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vti.com.Constants.ACCOUNT;
import vti.com.entity.Account;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.form.AccountForm;
import vti.com.exception.BusinessError;
import vti.com.exception.BusinessErrorException;
import vti.com.exception.NotFoundException;
import vti.com.repository.IAccountRepository;

import java.util.Optional;
import vti.com.service.criteria.AccountCriteria;
import vti.com.service.specification.AccountSpecification;
import vti.com.service.specification.Expression;
import vti.com.service.specification.QueryService;

@Service
public class AccountServiceImp implements IAccountService {

    private final IAccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final QueryService<Account> queryService;
    private final IDepartmentService iDepartmentService;

    public AccountServiceImp(IAccountRepository accountRepository,
        ModelMapper modelMapper, QueryService<Account> queryService,
        IDepartmentService iDepartmentService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.queryService = queryService;
        this.iDepartmentService = iDepartmentService;
    }

    @Override
    public Page<AccountDTO> findAll(AccountCriteria accountCriteria, Pageable pageable) {
        Specification<Account> specification = buildSpecification(accountCriteria);
        Page<Account> page = accountRepository.findAll(specification, pageable);
        List<AccountDTO> dtoList = page.getContent().stream()
            .map(account -> modelMapper.map(account, AccountDTO.class)).collect(
                Collectors.toList());
        return new PageImpl<>(dtoList, pageable, page.getTotalElements());
    }


    @Override
    public Optional<Account> findOne(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<AccountDTO> createAccount(AccountForm accountForm) {
      return  iDepartmentService.findOneToDTO(accountForm.getDepartmentId()).map(departmentDTO -> {
            Account account = modelMapper.map(accountForm,Account.class);
            account.getDepartment(); //?
            account.setId(null);
            accountRepository.save(account);
            return modelMapper.map(account,AccountDTO.class);
        });
    }

    @Override
    public Optional<AccountDTO> createAccount(Account account) {
        return  iDepartmentService.findOneToDTO(account.getDepartment().getId()).map(departmentDTO -> {
            account.getDepartment(); //?
            account.setId(null);
            accountRepository.save(account);
            return modelMapper.map(account,AccountDTO.class);
        });
    }


    @Override
    @Transactional
    public Optional<AccountDTO> updateAccount(Long id, AccountForm accountForm) throws NotFoundException {
        Account account = modelMapper.map(accountForm,Account.class);
        return findOne(id).map(acc -> {
            account.setId(id);
            return Optional.ofNullable(modelMapper.map(accountRepository.save(account),AccountDTO.class));
        }).orElseThrow(
            () -> new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode("error.account.idIsNotExists")
                        .params(Collections.singletonList(id))));
    }

    @Override
    @Transactional
    public AccountDTO deleteAccount(Long id) throws NotFoundException {
        return findOne(id).map(acc -> {
            accountRepository.delete(acc);
            return toDTO(acc);
        }).orElseThrow();
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

    Specification<Account> buildSpecification(AccountCriteria accountCriteria) {
        Specification<Account> specification = Specification.where(null);

        if (accountCriteria.getId() != null) {
            specification = specification
                .and(queryService.buildLongFilter(ACCOUNT.ID, accountCriteria.getId()));
        }

        if (accountCriteria.getFirstName() != null) {
            specification = specification
                .and(queryService
                    .buildStringFilter(ACCOUNT.FIRST_NAME, accountCriteria.getFirstName()));
        }

        if (accountCriteria.getUserName() != null) {
            specification = specification
                .and(queryService
                    .buildStringFilter(ACCOUNT.USERNAME, accountCriteria.getUserName()));
        }

        return specification;
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
