package app.saikat.waspdroid.NetworkLayer;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class APIRequestHandler{

    private OkHttpClient httpClient;
    private Handler requesthandler;
    private Gson gson;

    public APIRequestHandler(OkHttpClient okHttpClient, Handler handler, Gson gson) {
        this.httpClient = okHttpClient;
        this.requesthandler = handler;
        this.gson = gson;
    }

    public <T> void request(final URL url,
                            final Optional<Map<String, String>> params,
                            final Optional<Map<String, String>> formData,
                            final Class<T> serializeClass,
                            final OnResponse<T> onResponse) {

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
                    T t = gson.fromJson(response.body().string(), serializeClass);
                    onResponse.onResponse(response.code(), t);
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
