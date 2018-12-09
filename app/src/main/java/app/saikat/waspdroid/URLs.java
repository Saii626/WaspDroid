package app.saikat.waspdroid;

public final class URLs {

    private URLs() {}

    static String BASE_URL = "https://saikat.app";
//    static String BASE_URL = "http://192.168.100.3";

    static String POST_TOKEN = BASE_URL + "/notify/register";
    static String POST_THINKPAD = BASE_URL + "/notify/thinkpad";
    static String POST_FIREFOX = BASE_URL + "/notify/firefox";
    static String POST_WORK = BASE_URL + "/notify/workLaptop";
}
