package app.saikat.waspdroid.Models.Response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.UUID;

public class NotifyRegister {

    @SerializedName("id")
    public UUID id;

    @SerializedName("created_at")
    public Date createdAt;

    @SerializedName("modified_at")
    public Date modifedAt;

    @SerializedName("name")
    public String name;

    @SerializedName("token")
    public String token;
}
