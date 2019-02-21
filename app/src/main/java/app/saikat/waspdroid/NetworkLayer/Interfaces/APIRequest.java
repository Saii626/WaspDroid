package app.saikat.waspdroid.NetworkLayer.Interfaces;

import org.json.JSONObject;

import java.util.Map;

public interface APIRequest {

    void getJsonObject(String url, Map<String, String> params, OnResponse<JSONObject> onResponse);
}
