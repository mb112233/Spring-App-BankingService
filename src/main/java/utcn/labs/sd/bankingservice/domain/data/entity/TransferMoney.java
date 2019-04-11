package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "transfer_table")
public class TransferMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transfer_id")
    private int transferId;

    @Column(name = "account1_id")
    private int account1Id;
    
    @Column(name = "account2_id")
    private int account2Id;

    @Column(name = "amount")
    private float amount;

	public TransferMoney(int transferId, int account1Id, int account2Id, float amount) {
		super();
		this.transferId = transferId;
		this.account1Id = account1Id;
		this.account2Id = account2Id;
		this.amount = amount;
	}
	
	public TransferMoney() {
		
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getAccount1Id() {
		return account1Id;
	}

	public void setAccount1Id(int account1Id) {
		this.account1Id = account1Id;
	}

	public int getAccount2Id() {
		return account2Id;
	}

	public void setAccount2Id(int account2Id) {
		this.account2Id = account2Id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
    
    
}
