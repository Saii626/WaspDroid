package app.saikat.waspdroid.Services;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessagingService;

import java.util.HashMap;
import java.util.Map;

import app.saikat.waspdroid.NetworkLayer.URLs;

public class FirebaseService extends FirebaseMessagingService {

    private static String TAG = FirebaseService.class.getSimpleName();

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        sendTokenToServer(token);
        Log.i(TAG, "newToken: "+token);
    }

    private void sendTokenToServer(final String token) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, URLs.REGISTER_DEVICE, new Response.Listener<String>() {
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

                params.put("source", "zuk");
                params.put("token", token);

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
}


