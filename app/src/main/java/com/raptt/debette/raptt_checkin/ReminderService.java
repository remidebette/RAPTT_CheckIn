package com.raptt.debette.raptt_checkin;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
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

        /*NotificationCompat.Builder alamNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.drawable.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg);*/


        //alarmNotificationManager.notify(1, alamNotificationBuilder.build());


        // TODO: Handle action Foo
        /*NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        long when = System.currentTimeMillis();         // notification time
        Notification notification = new Notification(icon, "reminder", when);    //  Extra should be R.drawable.ic_line_a
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.flags |= notification.FLAG_AUTO_CANCEL;
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        //notification.setLatestEventInfo(getApplicationContext(), "It's about time", "You should open the app now", contentIntent);
        nm.notify(NOTIF_ID, notification);*/


        long when = System.currentTimeMillis();


        Resources res = ReminderService.this.getResources();

        Notification.Builder nb = new Notification.Builder(ReminderService.this);
        nb.setContentTitle("title");
        nb.setContentText("message");
        //nb.setWhen(when);
        nb.setContentIntent(contentIntent);
        nb.setSmallIcon(R.drawable.ic_line_a);
        nb.setAutoCancel(true);
        nb.setTicker("message");

        long[] v = {500, 1000};
        nb.setVibrate(v);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        nb.setSound(uri);

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        //final Uri ringtone = Uri.parse(PreferenceManager.getDefaultSharedPreferences(ReminderService.this).getString("ringtone", getString(R.string.settings_default_ringtone)));

        nb.setDefaults(Notification.DEFAULT_VIBRATE);
        nb.setSound(uri);
        nb.setDefaults(Notification.DEFAULT_LIGHTS);



        //nb.setContentIntent(contentIntent);


        Notification notification = nb.build();

        nm.notify(1, notification);

        Log.d("AlarmService", "Notification sent.");
    }
}
