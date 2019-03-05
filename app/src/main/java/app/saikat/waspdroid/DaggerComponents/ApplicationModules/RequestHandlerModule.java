package app.saikat.waspdroid.DaggerComponents.ApplicationModules;

import android.os.Handler;

import com.google.gson.Gson;

import app.saikat.waspdroid.DaggerComponents.Scopes.ApplicationScope;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
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
