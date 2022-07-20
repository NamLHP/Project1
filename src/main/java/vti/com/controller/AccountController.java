package vti.com.controller;

import java.util.List;
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
import vti.com.entity.dto.AccountDTO;
import vti.com.service.AccountServiceImp;
import vti.com.service.IAccountService;
import vti.com.service.specification.Expression;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

  private final IAccountService IAccountService;

  public AccountController(AccountServiceImp accountServiceImp) {
    this.IAccountService = accountServiceImp;
  }

  @GetMapping("")
  ResponseEntity<Page<AccountDTO>> findAll(Pageable pageable) {
    return ResponseEntity.ok(IAccountService.findAll(pageable));
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
  public ResponseEntity<AccountDTO> create(@RequestBody Account account) {
    try {
      return ResponseEntity.ok().body(IAccountService.createAccount(account));
    } catch (NullPointerException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccountDTO> update(@RequestBody Account account, @PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(IAccountService.updateAccount(id, account));
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
