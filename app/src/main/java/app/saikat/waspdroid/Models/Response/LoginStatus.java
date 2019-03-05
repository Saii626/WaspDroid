package app.saikat.waspdroid.Models.Response;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class LoginStatus {

    @SerializedName("status")
    public String status;

    @SerializedName("id")
    public UUID userId;
}
