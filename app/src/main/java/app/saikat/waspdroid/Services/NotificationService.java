package app.saikat.waspdroid.Services;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import app.saikat.waspdroid.Models.NotifyDeviceResponse;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.NetworkLayer.OnResponse;
import app.saikat.waspdroid.NetworkLayer.URLs;

public class NotificationService extends NotificationListenerService {

    private static String TAG = NotificationService.class.getSimpleName();

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

        Bundle extras = notification.getNotification().extras;
        String title = extras.getString("android.title");
        title = title==null?"<<Title>>":title;
        CharSequence charSequence = extras.getCharSequence("android.text");
        String text = charSequence!=null?charSequence.toString():"<<Body>>";


        sendNotificationToServer(title, text, pack, URLs.POST_FIREFOX);
        sendNotificationToServer(title, text, pack, URLs.POST_THINKPAD);
        sendNotificationToServer(title, text, pack, URLs.POST_WORK);
    }

    private void sendNotificationToServer(final String title, final String text, final String pack, final URLs url ) {

        Map<String, String> formData = new HashMap<>();

        formData.put("title", title);
        formData.put("body", text);
        formData.put("source", pack);

        APIRequestHandler.getInstance().request(url, Optional.empty(), Optional.of(formData), (OnResponse<NotifyDeviceResponse>) (statusCode, data) -> {

            if(statusCode == 401 || statusCode == 500) {
                Log.e(TAG, "Server error");
            } else {
//                Log.v(TAG, String.format("Destination: %s,\nResponse: %s", data.destination, data.response));
                Toast.makeText(getBaseContext(), String.format("Notification send to %s", data.destination), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
