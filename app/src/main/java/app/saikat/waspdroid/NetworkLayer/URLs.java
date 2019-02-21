package app.saikat.waspdroid.NetworkLayer;

public final class URLs {

    private URLs() {}

    static String BASE_URL = "https://saikat.app";
//    static String BASE_URL = "http://192.168.100.3";

    public static String REGISTER_DEVICE = BASE_URL + "/notify/register";

    public static String POST_THINKPAD = BASE_URL + "/notify/thinkpad";
    public static String POST_FIREFOX = BASE_URL + "/notify/firefox";
    public static String POST_WORK = BASE_URL + "/notify/workLaptop";

    public static String getUrlAppendedToBase(String path) {
        if (path.charAt(0) == '/') {
            return BASE_URL + path;
        }else {
            return BASE_URL + '/' + path;
        }
    }
}
