package app.saikat.waspdroid.NetworkLayer;

import android.os.Handler;
import android.os.HandlerThread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.text.DateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIRequestHandler{

    private OkHttpClient httpClient;
    private Handler requesthandler;
    private Gson gson;

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
                .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
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

        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        gson = builder.create();
    }

    public <T> void request(final URLs url, final Optional<Map<String, String>> params, final Optional<Map<String, String>> formData, final OnResponse<T> onResponse) {

        final String completeUrl = addParamsToUrl(url.getUrl(), params);

        requesthandler.post(() -> {
            Request.Builder requestBuilder = new Request.Builder()
                    .url(completeUrl);

            if (formData.isPresent()) {
                FormBody.Builder bodyBuilder = new FormBody.Builder();

                for(Map.Entry<String, String> keyVal : formData.get().entrySet()) {
                    bodyBuilder.add(keyVal.getKey(), keyVal.getValue());
                }

                requestBuilder.post(bodyBuilder.build());
            }

            try (Response response = httpClient.newCall(requestBuilder.build()).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                if (response.body() != null) {
                    Object respObj = gson.fromJson(response.body().string(), url.getResponseClass());
                    onResponse.onResponse(response.code(), (T) respObj);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private String addParamsToUrl(String url, Optional<Map<String, String>> params) {

        if (!params.isPresent()) {
            return  url;
        }

        StringBuilder sb = new StringBuilder(url);

        sb.append('?');
        for(Map.Entry<String, String> param: params.get().entrySet()) {
            sb.append(param.getKey());
            sb.append('=');
            sb.append(param.getValue());
            sb.append('&');
        }

        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }
}
