package utcn.labs.sd.bankingservice.domain.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report_table")
public class ActivityReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "report_id")
	private int reportId;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "activity")
	private String activity;

	@Column(name = "date_creation")
	private String date;

	public ActivityReport(int reportId, int userId, String activity, String date) {
		super();
		this.reportId = reportId;
		this.userId = userId;
		this.activity = activity;
		this.date = date;
	}
	public ActivityReport(int userId, String activity, String date) {
	
		this.userId = userId;
		this.activity = activity;
		this.date = date;
	}
	
	public ActivityReport() {
		
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	 @Override
	    public String toString() {
	        final StringBuilder sb = new StringBuilder("ActivityReport{");
	        sb.append("reportId='").append(reportId).append('\'');
	        sb.append(", userId='").append(userId).append('\'');
	        sb.append(", activity='").append(activity).append('\'');
	        sb.append(", date='").append(date).append('\'');
	        sb.append('}');
	        return sb.toString();
	    }

}
