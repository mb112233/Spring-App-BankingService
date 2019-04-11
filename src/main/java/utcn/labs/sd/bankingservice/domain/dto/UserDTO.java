package utcn.labs.sd.bankingservice.domain.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import utcn.labs.sd.bankingservice.domain.data.entity.ActivityReport;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.UserType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private int userId;
	
	@NotNull
	 @Size(min=2, message="Username should have atleast 2 characters")
	private String username;
	
	@NotNull
	 @Size(min=2, message="Password should have atleast 2 characters")
	private String password;
	
	@NotNull
	private UserType type;
	
	private List<ActivityReport> reportList;

	@JsonCreator
	public UserDTO(@JsonProperty("userId") int userId, @JsonProperty("username") String username,
			@JsonProperty("password") String password, @JsonProperty("type") UserType type,
			@JsonProperty("reportList") List<ActivityReport> reportList) {

		this.userId = userId;
		this.username = username;
		this.password = password;
		this.type = type;
		this.reportList = reportList;

	}

	public List<ActivityReport> getReportList() {
		return reportList;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserDto{");
		sb.append("userId=").append(userId);
		sb.append(", username=").append(username);
		sb.append(", password='").append(password);
		sb.append(", type=").append(type);
		sb.append('}');
		return sb.toString();
	}

	@JsonProperty("userId")
	public int getUserId() {
		return userId;
	}

	@JsonProperty("username")
	public String getUsername() {
		return username;
	}

	@JsonProperty("password")
	public String getPassword() {
		return password;
	}

	@JsonProperty("type")
	public UserType getType() {
		return type;
	}

}
