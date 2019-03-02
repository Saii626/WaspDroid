package app.saikat.waspdroid.NetworkLayer;

import app.saikat.waspdroid.Models.LoginResponse;
import app.saikat.waspdroid.Models.NotifyDeviceResponse;
import app.saikat.waspdroid.Models.NotifyRegisterResponse;

public enum URLs {

    //login
    LOGIN("/login", LoginResponse.class),


    // notify
    REGISTER_DEVICE("/notify/register", NotifyRegisterResponse.class),
    POST_THINKPAD("/notify/thinkpad", NotifyDeviceResponse.class),
    POST_FIREFOX("/notify/firefox", NotifyDeviceResponse.class),
    POST_WORK("/notify/workLaptop", NotifyRegisterResponse.class);

    private final String url;
    private final Class responseClass;
    private static BASE_URLs base_url = BASE_URLs.REMOTE_URL;

    URLs(String url, Class response) {
        this.url = url;
        this.responseClass = response;
    }

    public static void changeBaseUrl(BASE_URLs newBaseUrl) {
        URLs.base_url = newBaseUrl;
    }

    public static BASE_URLs getBase_url() {
        return URLs.base_url;
    }

    public String getUrl() {
        return base_url.getUrl().concat(url);
    }

    public Class getResponseClass() {
        return responseClass;
    }
}
