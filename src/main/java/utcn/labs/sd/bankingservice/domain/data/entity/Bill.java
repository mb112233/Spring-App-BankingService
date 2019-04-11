package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;

@Entity
@Table(name = "bill_table")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bill_id")
	private int billId;

	@Column(name = "date_creation")
	private String date;

	@Column(name = "paid")
	private boolean paid;

	@Column(name = "amount")
	private float amount;
	
	public Bill() {
		
	}

	public Bill(int billId, String date, boolean paid, float amount) {
		super();
		this.billId = billId;
		this.date = date;
		this.paid = paid;
		this.amount = amount;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
