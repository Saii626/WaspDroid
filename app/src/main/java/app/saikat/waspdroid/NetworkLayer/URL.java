package app.saikat.waspdroid.NetworkLayer;

public enum URL {

    //login
    LOGIN("/login"),
    GET_LOGGED_IN_USER("/getLoggedInUser"),


    // notify
    REGISTER_DEVICE("/notify/register"),
    POST_THINKPAD("/notify/thinkpad"),
    POST_FIREFOX("/notify/firefox"),
    POST_WORK("/notify/workLaptop");

    private final String url;
    private static BASE_URLs base_url = BASE_URLs.REMOTE_URL;

    URL(String url) {
        this.url = url;
    }

    public static void changeBaseUrl(BASE_URLs newBaseUrl) {
        URL.base_url = newBaseUrl;
    }

    public static BASE_URLs getBase_url() {
        return URL.base_url;
    }

    public String getUrl() {
        return URL.base_url.getUrl().concat(this.url);
    }

}
