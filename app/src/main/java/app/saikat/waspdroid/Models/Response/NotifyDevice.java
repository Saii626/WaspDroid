package app.saikat.waspdroid.Models.Response;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class NotifyDevice {

    @SerializedName("id")
    public UUID id;

    @SerializedName("timestamp")
    public Date timestamp;

    @SerializedName("source")
    public String source;

    @SerializedName("title")
    public String title;

    @SerializedName("message")
    public String message;

    @SerializedName("destination")
    public String destination;

    @SerializedName("response")
    public String response;

}
