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
import vti.com.service.AccountServiceImp;

import java.util.List;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

  private final AccountServiceImp accountServiceImp;

  public AccountController(AccountServiceImp accountServiceImp) {
    this.accountServiceImp = accountServiceImp;
  }


  @GetMapping("/oneAccounts/{id}")
  ResponseEntity<Optional<Account>> findOne(@PathVariable Long id) {
    return ResponseEntity.ok(accountServiceImp.findOne(id));
  }

  @GetMapping("/listAccounts")
  ResponseEntity<List<AccountDto>> findAll() {
    return ResponseEntity.ok(accountServiceImp.findAll());
  }

  @GetMapping("/listAccounts/page")
  ResponseEntity<Page<AccountDto>> findAll(Pageable pageable) {
    return ResponseEntity.ok(accountServiceImp.findAllWithPage(pageable));
  }

//    @GetMapping("/listAccountsByLastName/{username}")
//    ResponseEntity<List<AccountDto>> findAccountLikeLastName(@PathVariable String username ){
//        return ResponseEntity.ok(accountServiceImp.findAccountLikeLastNameToDTO(username));
//    }

  @GetMapping("/listAccountsByLastName")
  ResponseEntity<List<AccountDto>> findAccountLikeLastName(@RequestParam String username)
      throws NotFoundException {
    return ResponseEntity.ok(accountServiceImp.findAccountLikeLastNameToDTO(username));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<Account> deleteAccountByID(@PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(accountServiceImp.deleteAccount(id));
    } catch (NotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/newAccount")
  public ResponseEntity<Account> create(@RequestBody Account account) {
        accountServiceImp.createAccount(account);
    if (account == null) {
       new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } else {
      return  new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(account, HttpStatus.CREATED);
  }

  @PutMapping("/accountAfterModyfi/{id}")
  public ResponseEntity<Account> update(@RequestBody Account account , @PathVariable Long id) {
    try {
      accountServiceImp.updateAccount(id,account);
    } catch (NotFoundException e) {
      e.printStackTrace();
    }

    return new ResponseEntity<>(account, HttpStatus.OK);
  }
}
