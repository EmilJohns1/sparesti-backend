package edu.ntnu.idi.stud.team10.sparesti.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.ntnu.idi.stud.team10.sparesti.dto.AccountDto;
import edu.ntnu.idi.stud.team10.sparesti.dto.TransactionDto;
import edu.ntnu.idi.stud.team10.sparesti.service.BankService;
import edu.ntnu.idi.stud.team10.sparesti.service.UserAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/** Controller to mock interactions with a bank. */
@RestController
@RequestMapping("/api/bank")
@Tag(name = "Bank", description = "Operations related to the bank mock")
public class BankController {
  private final BankService bankService;
  private final UserAccountService userAccountService;

  @Autowired
  public BankController(BankService bankService, UserAccountService userAccountService) {
    this.bankService = bankService;
    this.userAccountService = userAccountService;
  }

  /**
   * Create a new account.
   *
   * @param accountDto (AccountDto) The account to create
   * @return (ResponseEntity&lt;AccountDto&gt;) The created account
   */
  @PutMapping("/account/create")
  @Operation(summary = "Create a new account")
  public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
    AccountDto createdAccount = bankService.createAccount(accountDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
  }

  /**
   * Connects a created account to either user's savings or checking account.
   *
   * @param accountDto (AccountDto) The account being connected
   * @param isSavings (boolean) Whether it is a savings (true) or checking (false) account
   * @return
   */
  @PostMapping("/account/assign")
  @Operation(summary = "Assign an existing account as user's savings or checkings account")
  public ResponseEntity<?> assignAccountToOwner(
      @RequestBody AccountDto accountDto, @RequestParam boolean isSavings) {
    userAccountService.setUserAccount(accountDto, isSavings);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  /**
   * Add a transaction to an account.
   *
   * @param transaction (TransactionDto) The transaction to add
   * @return (ResponseEntity<Void>) The response entity
   */
  @PostMapping("/account/transactions")
  @Operation(summary = "Add a transaction to an account.")
  public ResponseEntity<Void> addTransaction(@RequestBody TransactionDto transaction) {
    bankService.addTransaction(transaction);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  /**
   * Get account details for an account number.
   *
   * @param accountNr (int) The account number
   * @return (ResponseEntity<AccountDto>) The account details
   */
  @GetMapping("/account/details/{accountNr}")
  @Operation(summary = "Get account details for an account number.")
  public ResponseEntity<AccountDto> getAccountDetails(@PathVariable int accountNr) {
    AccountDto accountDetails = bankService.getAccountDetails(accountNr);
    return ResponseEntity.ok(accountDetails);
  }

  /**
   * Get all accounts for a user.
   *
   * @param userId (Long) The user ID
   * @return (ResponseEntity<Set<AccountDto>>) The set of account details
   */
  @GetMapping("/account/all/{userId}")
  @Operation(summary = "Get all accounts for a user.")
  public ResponseEntity<Set<AccountDto>> getAllAccounts(@PathVariable Long userId) {
    Set<AccountDto> accountDetails = bankService.getUserAccounts(userId);
    return ResponseEntity.ok(accountDetails);
  }

  /**
   * Endpoint for transferring money between two accounts.
   *
   * @param fromAccountNr the account number where money is coming from
   * @param toAccountNr the account number receiving money
   * @param amount the amount of money being transferred, in NOK.
   * @return An ("ok") ResponseEntity stating that the transfer was successful.
   */
  @PutMapping("/account/transfer")
  @Operation(summary = "Transfer money from one account to another")
  public ResponseEntity<?> transferMoney(
      @RequestParam Integer fromAccountNr,
      @RequestParam Integer toAccountNr,
      @RequestParam double amount) {
    bankService.transferMoney(fromAccountNr, toAccountNr, amount);
    return ResponseEntity.ok().body("Transfer successful");
  }

  @GetMapping("/transactions/{accountNr}")
  @Operation(summary = "Get all transactions by an account number")
  public ResponseEntity<Set<TransactionDto>> getAllTransactionsByAccountNr(
      @PathVariable Integer accountNr) {
    return ResponseEntity.ok().body(bankService.getTransactionsByAccountNr(accountNr));
  }
}
