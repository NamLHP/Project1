package vti.com.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vti.com.entity.Account;
import vti.com.entity.dto.AccountDTO;
import vti.com.entity.form.AccountForm;
import vti.com.exception.NotFoundException;
import vti.com.service.AccountServiceImp;
import vti.com.service.IAccountService;
import vti.com.service.criteria.AccountCriteria;
import vti.com.service.specification.Expression;

@RestController
@RequestMapping("api/v1/accounts")
@Validated
public class AccountController {

    private final IAccountService IAccountService;

    public AccountController(AccountServiceImp accountServiceImp) {
        this.IAccountService = accountServiceImp;
    }

    @GetMapping("")
    ResponseEntity<Page<AccountDTO>> findAll(AccountCriteria accountCriteria, Pageable pageable) {
        return ResponseEntity.ok(IAccountService.findAll(accountCriteria, pageable));
    }


    @GetMapping("{id}")
    ResponseEntity<Optional<AccountDTO>> findOneToDTO(@PathVariable Long id) {
        return ResponseEntity.ok().body(IAccountService.findOneToDTO(id));
    }

    @GetMapping("search")
    ResponseEntity<List<AccountDTO>> search(
        @RequestParam String field,
        @RequestParam String operator,
        @RequestParam String value) {
        return ResponseEntity.ok()
            .body(IAccountService.search(new Expression(field, operator, value)));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<AccountDTO> deleteAccountByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(IAccountService.deleteAccount(id));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Optional<AccountDTO>> create(
        @RequestBody @Valid AccountForm accountForm) throws MethodArgumentNotValidException {
        return ResponseEntity.ok().body(IAccountService.createAccount(accountForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<AccountDTO>> update(@RequestBody @Valid AccountForm accountForm,
        @PathVariable Long id) throws NotFoundException {
        IAccountService.updateAccount(id, accountForm);
        return ResponseEntity.ok().body(IAccountService.findOneToDTO(id));
    }
}
