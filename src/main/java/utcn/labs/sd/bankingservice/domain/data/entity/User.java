package utcn.labs.sd.bankingservice.domain.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import utcn.labs.sd.bankingservice.domain.data.entity.enums.UserType;

@Entity
@Table(name = "user_table")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "typee")
	@Enumerated(EnumType.STRING)
	private UserType userType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<ActivityReport> reportList;

	public User() {

	}

	public User(int userId, String username, String password, UserType userType,List<ActivityReport> reportList) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.reportList=reportList;
	}

	public List<ActivityReport> getReportList() {
		return reportList;
	}

	public void setReportList(List<ActivityReport> reportList) {
		this.reportList = reportList;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getUserType() {
		return userType;
	}

}
