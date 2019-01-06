package app.saikat.waspdroid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NotificationService extends NotificationListenerService {

    private static String TAG = NotificationService.class.getSimpleName();
    private RequestQueue queue;

    @Override
    public void onNotificationPosted(StatusBarNotification notification) {
        String pack = notification.getPackageName();
        if (pack!=null) {
            PackageManager packageManager = getApplicationContext().getPackageManager();
            try {
                pack = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(pack, PackageManager.GET_META_DATA));
            } catch (PackageManager.NameNotFoundException ignored) { }
        } else {
            pack = "<<Package>>";
        }
//        String ticker ="";
//        if(notification.getNotification().tickerText !=null) {
//            ticker = notification.getNotification().tickerText.toString();
//        }
        Bundle extras = notification.getNotification().extras;
        String title = extras.getString("android.title");
        title = title==null?"<<Title>>":title;
        CharSequence charSequence = extras.getCharSequence("android.text");
        String text = charSequence!=null?charSequence.toString():"<<Body>>";


        sendNotificationToServer(title, text, pack, URLs.POST_FIREFOX);
        sendNotificationToServer(title, text, pack, URLs.POST_THINKPAD);
        sendNotificationToServer(title, text, pack, URLs.POST_WORK);
//        Log.i(TAG,pack);
//        Log.i(TAG,ticker);
//        Log.i(TAG,title);
//        Log.i(TAG,text);
    }

    private void sendNotificationToServer(final String title, final String text, final String pack, String url) {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: Success " + response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: Error" + error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams () {
                Map<String, String> params = new HashMap<>();

                String t = title+" ("+pack+")";

                params.put("title", t);
                params.put("body", text);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };

        queue.add(request);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(this);
    }
}
