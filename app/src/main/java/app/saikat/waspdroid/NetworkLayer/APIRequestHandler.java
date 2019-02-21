package app.saikat.waspdroid.NetworkLayer;

import android.os.Handler;
import android.os.HandlerThread;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.saikat.waspdroid.NetworkLayer.Interfaces.APIRequest;
import app.saikat.waspdroid.NetworkLayer.Interfaces.OnResponse;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIRequestHandler implements APIRequest {

    private OkHttpClient httpClient;
    private Handler requesthandler;
    private ObjectMapper objectMapper;

    private static APIRequestHandler instance;

    static {
        instance = new APIRequestHandler();
    }

    public static APIRequestHandler getInstance() {
        return instance;
    }

    private APIRequestHandler() {
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(15))
                .retryOnConnectionFailure(true)
                .cookieJar(new CookieJar() {

                    private final Map<String, List<Cookie>> cookieStore = new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(url.host(), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        return cookieStore.getOrDefault(url.host(), new ArrayList<Cookie>());
                    }
                })
                .build();


        HandlerThread handlerThread = new HandlerThread("api_request");
        handlerThread.start();
        requesthandler = new Handler(handlerThread.getLooper());

        objectMapper = new ObjectMapper();
    }


    @Override
    public void getJsonObject(final String url, final Map<String, String> params, final OnResponse<JSONObject> onResponse) {

        final String completeUrl = addParamsToUrl(url,params);

        requesthandler.post(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(completeUrl)
                        .build();

                try (Response response = httpClient.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    }

                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    JSONObject jsonObject = null;

                    if(response.body() != null) {
                        try {
                            jsonObject = new JSONObject(response.body().string());
                        } catch (JSONException ignored) {
                        }

                        onResponse.onResponse(jsonObject);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private String addParamsToUrl(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(url);

        sb.append('?');
        for(Map.Entry<String, String> param: params.entrySet()) {
            sb.append(param.getKey());
            sb.append('=');
            sb.append(param.getValue());
            sb.append('&');
        }

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
