package utcn.labs.sd.bankingservice.domain.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
	
	

    private final int accountId;
    
    @NotNull
    private final AccountType accountType;
    
    @NotNull
    @Size(min=2, message="Name should have atleast 2 characters")
    private final String creationDate;
    
    @NotNull
    private final float balance;

    @JsonCreator
    public AccountDTO(@JsonProperty("accountId") int accountId,
                      @JsonProperty("accountType") AccountType accountType,
                      @JsonProperty("creationDate") String creationDate,
                      @JsonProperty("balance") float balance) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.creationDate = creationDate;
        this.balance = balance;
    }

    @JsonProperty("accountId")
    public int getAccountId() {
        return accountId;
    }

    @JsonProperty("accountType")
    public AccountType getAccountType() {
        return accountType;
    }

    @JsonProperty("creationDate")
    public String getCreationDate() {
        return creationDate;
    }

    @JsonProperty("balance")
    public float getBalance() {
        return balance;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountDto{");
        sb.append("accountId=").append(accountId);
        sb.append(", accountType=").append(accountType);
        sb.append(", creationDate='").append(creationDate).append('\'');
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
