package app.saikat.waspdroid.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {

    @JsonProperty("status")
    public String status;

    @JsonProperty("userId")
    public String userId;

    @JsonProperty("error")
    public String error;
}
