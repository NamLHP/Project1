package vti.com.controller;

import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import vti.com.entity.dto.AccountDto;
import vti.com.entity.dto.DepartmentDTO;
import vti.com.service.AccountServiceImp;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

  private final AccountServiceImp accountServiceImp;

  public AccountController(AccountServiceImp accountServiceImp) {
    this.accountServiceImp = accountServiceImp;
  }

  @GetMapping("")
  ResponseEntity<Page<AccountDto>> findAll(Pageable pageable) {
    return ResponseEntity.ok(accountServiceImp.findAll(pageable));
  }

  @GetMapping("{id}")
  ResponseEntity<Optional<Account>> findOne(@PathVariable Long id) {
    return ResponseEntity.ok(accountServiceImp.findOne(id));
  }


  @DeleteMapping("/{id}")
  ResponseEntity<AccountDto> deleteAccountByID(@PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(accountServiceImp.deleteAccount(id));
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("")
  public ResponseEntity<AccountDto> create(@RequestBody Account account) {
    try {
      return ResponseEntity.ok().body(accountServiceImp.createAccount(account));
    } catch (NullPointerException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccountDto> update(@RequestBody Account account, @PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(accountServiceImp.updateAccount(id, account));
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
