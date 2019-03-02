package app.saikat.waspdroid.NetworkLayer;

public interface OnResponse<T> {
    void onResponse(int statusCode, T data);
}
