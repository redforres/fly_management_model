package ca.fly.mtm.admin.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestResult {
    public RequestResult() {}

    private String status;
    private String token;
    private String ref_number;
    private String msg;
}
