package demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {
    //  UserInfo class is separated from InfoAnalysis class then it can be used somewhere else

    public String username;
    public String address;

    public UserInfo() {    //  default constructor
    }

    public UserInfo(/*String runningId, */String username, String address) {
        this.username = username;
        this.address = address;
    }
}