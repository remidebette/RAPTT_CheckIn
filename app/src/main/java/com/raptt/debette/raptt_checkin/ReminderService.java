package com.raptt.debette.raptt_checkin;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class ReminderService extends IntentService {

    private NotificationManager nm;

    public ReminderService() {
        super("ReminderService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        sendNotification("Wake Up! Wake Up!");
    }

    private void sendNotification(String msg) {
        Log.d("AlarmService", "Preparing to send notification...: " + msg);
        nm = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        Resources res = ReminderService.this.getResources();

        Notification.Builder nb = new Notification.Builder(ReminderService.this);
        nb.setContentTitle("title");
        nb.setContentText("message");
        nb.setContentIntent(contentIntent);
        nb.setSmallIcon(R.drawable.ic_line_a);
        nb.setAutoCancel(true);
        nb.setTicker("message");

        // Check Ringtone
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(ReminderService.this);
        String strRingtonePreference = preference.getString("notifications_new_message_ringtone", "DEFAULT_SOUND");
        nb.setSound(Uri.parse(strRingtonePreference));

        int notificationDefault = Notification.DEFAULT_LIGHTS;

        // Check vibrate
        boolean isVibrate = preference.getBoolean("notifications_new_message_vibrate", true);
        if (isVibrate) {
            notificationDefault = notificationDefault | Notification.DEFAULT_VIBRATE;
        }

        nb.setDefaults(notificationDefault);

        Notification notification = nb.build();
        nm.notify(1, notification);

        Log.d("AlarmService", "Notification sent.");
    }
}
