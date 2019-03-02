package app.saikat.waspdroid.NetworkLayer;

public enum BASE_URLs {
    REMOTE_URL("https://saikat.app"),
    LOCAL_URL("http://192.168.100.3");

    private final String url;

    BASE_URLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
