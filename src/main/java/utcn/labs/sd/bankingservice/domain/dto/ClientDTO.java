package utcn.labs.sd.bankingservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private String ssn;
    
    @NotNull
    @Size(min=2, message="First should have at least 2 characters")
    private String firstname;
    
    @NotNull
    @Size(min=2, message="Lastname should have atleast 2 characters")
    private String lastname;
    
    @NotNull
    @Size(min = 13,message="Identity card must 13 characters" )
    private String identityCardNumber;
    
    @NotNull
    @Size(min=5, message="Address should have atleast 2 characters")
    private String address;
   
    @NotNull
    @Email(message = "Email should be valid")
    private String email;
    
    private List<Account> accountList;
    private List<Bill> billList;

    @JsonCreator
    public ClientDTO(@JsonProperty("ssn") String ssn,
                     @JsonProperty("firstname") String firstname,
                     @JsonProperty("lastname") String lastname,
                     @JsonProperty("identityCardNumber") String identityCardNumber,
                     @JsonProperty("address") String address,
                     @JsonProperty("email") String email,
                     @JsonProperty("accountList") List<Account> accountList,
                     @JsonProperty("billList") List<Bill> billList) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.identityCardNumber = identityCardNumber;
        this.address = address;
        this.email = email;
        this.accountList = accountList;
        this.billList=billList;
    }

    @JsonProperty("ssn")
    public String getSsn() {
        return ssn;
    }

    public List<Bill> getBillList() {
		return billList;
	}

	@JsonProperty("firstname")
    public String getFirstname() {
        return firstname;
    }

    @JsonProperty("lastname")
    public String getLastname() {
        return lastname;
    }

    @JsonProperty("identityCardNumber")
    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("accountList")
    public List<Account> getAccountList() {
        return accountList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ClientDto{");
        sb.append("ssn='").append(ssn).append('\'');
        sb.append(", firstname='").append(firstname).append('\'');
        sb.append(", lastname='").append(lastname).append('\'');
        sb.append(", identityCardNumber='").append(identityCardNumber).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", accountList=").append(accountList);
        sb.append('}');
        return sb.toString();
    }
}
