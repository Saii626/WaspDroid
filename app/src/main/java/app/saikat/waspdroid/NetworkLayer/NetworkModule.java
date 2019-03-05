package app.saikat.waspdroid.NetworkLayer;

import android.os.Handler;
import android.os.HandlerThread;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.saikat.waspdroid.Application.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionSpec;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    private Handler requesthandler;

    public NetworkModule() {
        HandlerThread handlerThread = new HandlerThread("api_request");
        handlerThread.start();
        requesthandler = new Handler(handlerThread.getLooper());
    }

    @Provides
    @ApplicationScope
    OkHttpClient getOkHttpClient(CookieJar cookieJar) {
        return new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(15))
                .connectionSpecs(Arrays.asList(ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .retryOnConnectionFailure(true)
                .cookieJar(cookieJar)
                .build();
    }

    @Provides
    @ApplicationScope
    CookieJar getCookieJar() {
        return new CookieJar() {

            private final Map<String, List<Cookie>> cookieStore = new HashMap<>();
            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                return cookieStore.getOrDefault(url.host(), new ArrayList<Cookie>());
            }
        };
    }

    @Provides
    @ApplicationScope
    Handler handler() {
        return requesthandler;
    }

    @Provides
    @ApplicationScope
    Gson getGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return builder.create();
    }
}
