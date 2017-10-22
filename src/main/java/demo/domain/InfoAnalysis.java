package demo.domain;
//  domain is responsible for processing model and database persistency
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.*;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)   //  strategy of class serilization, if something should be included or ignored in json
//  NON_NULL: including fields that are not null
@Data
@Entity   //  An entity corresponds to a table in database
// If a class is @Embeddable, then its columns are in the same table where it is embedded, and this class cannot be @Entity
@Table(name = "RUNNING_ANALYSIS")
public class InfoAnalysis {

    @Embedded
    @JsonIgnore
    @AttributeOverrides({   //  if field has a different name in database, otherwise no need to @AttributeOverrides
            @AttributeOverride(name = "username", column = @Column(name = "username")),
            @AttributeOverride(name = "address", column = @Column(name = "address"))
    })
    private UserInfo userInfo;

    private String runningId;
    @JsonIgnore
    private double latitude;
    @JsonIgnore
    private double longitude;
    @JsonIgnore
    private double runningDistance;
    private double totalRunningTime;
    private int heartRate = 60 + (int)(Math.random() * 141);

    @Id   //  primary key in database, only one Id is allowed in an entity
    @GeneratedValue    //  generate automatically, no need to explicitly specify, however the primary key needs a value in database if not specified
    private Long userId;

    private String healthWarningLevel;
//    private String healthWarningLevel = (heartRate >= 60 && heartRate <= 75) ? "NORMAL" : ((heartRate > 120) ? "HIGH" : "LOW");

    @JsonIgnore
    private Date timestamp = new Date();

    public InfoAnalysis() {
        this.userInfo = null;
    }

    @JsonCreator
    public InfoAnalysis(@JsonProperty("runningId") String runningId,
                        @JsonProperty("totalRunningTime") double totalRunningTime,
                        @JsonProperty("heartRate") int heartRate,
                        @JsonProperty("userId") Long userId,
                        @JsonProperty("userInfo") UserInfo userInfo,
                        @JsonProperty("username") String username,
                        @JsonProperty("address") String address,
                        @JsonProperty("healthWarningLevel") String healthWarningLevel){
//        this.userInfo = new UserInfo();
        this.runningId = runningId;
        this.totalRunningTime = totalRunningTime;
        this.heartRate = 60 + (int)(Math.random() * 141);
        this.userId = userId;
        this.userInfo = userInfo;
        this.userInfo.username = userInfo.username;
        this.userInfo.address = userInfo.address;
        this.healthWarningLevel = healthWarningLevel;
//        this.healthWarningLevel = (heartRate >= 60 && heartRate <= 75) ? "NORMAL" : ((heartRate > 120) ? "HIGH" : "LOW");
    }

//    public InfoAnalysis(UserInfo userInfo) {
//        this.userInfo = userInfo;
//    }

    @JsonIgnore
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public String getUsername() {  // Jackson序列化的时候会包括getter得到的值
        return userInfo.username;
    }

//    public void setUsername(String username) {
//        this.userInfo.username = username;
//    }

    public String getAddress() {   //  Jackson序列化的时候会包括getter得到的值
        return userInfo.address;
    }

//    public void setAddress(String address) {
//        this.userInfo.address = address;
//    }

//    public String getRunningId(){
//        return this.userInfo == null ? null : this.userInfo.getUserId();
//    }
//
//

    public String getHealthWarningLevel() {
        if (this.heartRate >= 60 && this.heartRate <= 75) {
            return "LOW";
        } else if (this.heartRate > 75 && this.heartRate <= 120) {
            return "NORMAL";
        } else if (this.heartRate > 120) {
            return "HIGH";
        }
        return "UNKNOWN";
    }

//    public void setHealthWarningLevel() {
//        if (this.heartRate >= 60 && this.heartRate <= 75) {
//            this.healthWarningLevel = "LOW";
//        } else if (this.heartRate > 75 && this.heartRate <= 120) {
//            this.healthWarningLevel = "NORMAL";
//        } else if (this.heartRate > 120) {
//            this.healthWarningLevel = "HIGH";
//        }
//    }
}