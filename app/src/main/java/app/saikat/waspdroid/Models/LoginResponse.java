package app.saikat.waspdroid.Models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    public String status;

    @SerializedName("userId")
    public String userId;

    @SerializedName("error")
    public String error;
}
