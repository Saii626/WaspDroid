package app.saikat.waspdroid.SharedPreferenceLayer;

public enum SharedPreferenceKey {
    USER_ID("user_id"),
    FIREBASE_TOKEN("firebase_token");

    private String key;

    SharedPreferenceKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
