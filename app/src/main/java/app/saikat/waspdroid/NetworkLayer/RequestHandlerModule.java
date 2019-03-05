package app.saikat.waspdroid.NetworkLayer;

import android.os.Handler;

import com.google.gson.Gson;

import app.saikat.waspdroid.Application.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module(includes = {NetworkModule.class})
public class RequestHandlerModule {

    @Provides
    @ApplicationScope
    APIRequestHandler getRequestHandler(OkHttpClient okHttpClient, Handler handler, Gson gson){
        return new APIRequestHandler(okHttpClient, handler, gson);
    }
}
