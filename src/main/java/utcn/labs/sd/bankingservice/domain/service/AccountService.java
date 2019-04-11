package utcn.labs.sd.bankingservice.domain.service;


import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.ActivityReport;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityReportRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.BillRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class AccountService {
	

	
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private BillRepository billRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ActivityReportRepository reportRepository;
    

    public List<AccountDTO> getAllAccounts() {
        return AccountConverter.toDto(accountRepository.findAll());
    }

    public AccountDTO getAccountById(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) throw new NotFoundException("No account found with that accountId");
        return AccountConverter.toDto(account);
    }

    public AccountDTO createAccount(AccountDTO accountDto) throws Exception {
        Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
        if (accountDto.getBalance() < 0) {
            throw new Exception("Impossible to have a negative balance");
        }
        Account account = new Account(accountDto.getAccountId(), accountDto.getAccountType(), currentTimestamp.toString(), accountDto.getBalance());
        Account newAccount = accountRepository.save(account);
        return AccountConverter.toDto(newAccount);
    }


    public AccountDTO updateAccount(Integer accountId, AccountDTO accountDto) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account found with that id");
        }
        account.setBalance(accountDto.getBalance());
        return AccountConverter.toDto(accountRepository.save(account));
    }


    public void deleteAccount(Integer accountId) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            throw new NotFoundException("No account with that accountId");
        }
        accountRepository.delete(account);
    }
    public void transferMoney(Integer d1,Integer d2,float amount) {
    	Account a1=accountRepository.findById(d1).orElse(null);
    	Account a2=accountRepository.findById(d2).orElse(null);
    	if (a1.getBalance()>=amount) a1.setBalance(a1.getBalance()-amount);
    	a2.setBalance(a2.getBalance()+amount);
    	accountRepository.save(a1);
    	accountRepository.save(a2);
    	this.getCurrentEmployeeId("transfered money");
    }
    
    public void payBill(Integer billId,Integer accountId,float amount) throws NotFoundException {
    	Bill bill=billRepository.findById(billId).orElse(null);
    	Account account = accountRepository.findById(accountId).orElse(null);
    	 if (bill == null) {
             throw new NotFoundException("No account with that accountId");
         }
    	 if (account == null) {
             throw new NotFoundException("No account with that accountId");
         }
    	 if (account.getBalance()>bill.getAmount()) {
    		 account.setBalance(account.getBalance()-amount);
    		 bill.setPaid(true);
    	 }
    	 billRepository.save(bill);
    	 accountRepository.save(account);
    	 this.getCurrentEmployeeId("paid a bill");
    	 
    }
    
    private  void getCurrentEmployeeId(String s) {
		String emp = SecurityContextHolder.getContext().getAuthentication().getName();
		User em = userRepository.getByUsername(emp).orElse(null);
		ActivityReport h1 = new ActivityReport(em.getUserId(),s, Instant.now().toString() );
		em.getReportList().add(h1);
		reportRepository.save(h1);
	}






}
