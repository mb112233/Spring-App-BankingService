package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.service.AccountService;

import java.util.List;

import javax.validation.Valid;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/employee/account")
@CrossOrigin
class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "getAllAccounts", tags = SwaggerTags.ACCOUNT_TAG)
    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @ApiOperation(value = "findAccountById", tags = SwaggerTags.ACCOUNT_TAG)
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<?> findAccountById(@PathVariable("accountId") Integer accountId) {
        try {
            AccountDTO accountDto = accountService.getAccountById(accountId);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "insertAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @PostMapping
    public ResponseEntity<?> insertAccount(@Valid @RequestBody AccountDTO accountDto) {
        AccountDTO accountDtoToBeInserted;
        try {
            accountDtoToBeInserted = accountService.createAccount(accountDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @PutMapping(value = "/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") Integer accountId,@Valid @RequestBody AccountDTO accountDto) {
        try {
            accountService.updateAccount(accountId, accountDto);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Integer accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    

    @ApiOperation(value = "transferMoney", tags = SwaggerTags.ACCOUNT_TAG)
    @GetMapping(value = "/transfer/{a1}/{a2}/{amount}")
    public ResponseEntity<?> transferMoney(@PathVariable("a1") Integer a1,@PathVariable("a2") Integer a2,@PathVariable("amount") Float amount) {
        try {
            accountService.transferMoney(a1,a2,amount);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    

    @ApiOperation(value = "payBill", tags = SwaggerTags.ACCOUNT_TAG)
    @GetMapping(value = "/bill/{bId}/{aId}/{amount}")
    public ResponseEntity<?> payBill(@PathVariable("bId") Integer bId,@PathVariable("aId") Integer aId,@PathVariable("amount") Float amount) {
        try {
            accountService.payBill(bId,aId,amount);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
