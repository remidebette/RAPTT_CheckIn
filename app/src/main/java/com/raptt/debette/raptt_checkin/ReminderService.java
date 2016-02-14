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
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ReminderService extends IntentService {

    private static final int NOTIF_ID = 1;

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
        public static final String ACTION_SET_NOTIFICATION = "com.raptt.debette.raptt_checkin.action.FOO";

    // TODO: Rename parameters
    //private static final String EXTRA_PARAM1 = "com.raptt.debette.raptt_checkin.extra.PARAM1";
    //private static final String EXTRA_PARAM2 = "com.raptt.debette.raptt_checkin.extra.PARAM2";

    public ReminderService() {
        super("ReminderService");
    }




    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
                final int icon = intent.getIntExtra(MainActivity.ICON, -1);
                handleActionFoo(icon);
            }
        }


    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(int icon) {
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

        final Intent notificationIntent = new Intent(ReminderService.this, ReminderService.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        final PendingIntent contentIntent = PendingIntent.getActivity(ReminderService.this, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);


        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Resources res = ReminderService.this.getResources();

        Notification.Builder nb = new Notification.Builder(ReminderService.this);
        nb.setContentTitle("title");
        nb.setContentText("message");
        nb.setSmallIcon(R.drawable.ic_line_a);
        nb.setWhen(System.currentTimeMillis());
        nb.setAutoCancel(true);
        nb.setTicker("message");

        long[] v = {500, 1000};
        nb.setVibrate(v);

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        //nb.setSound(uri);

        //final Uri ringtone = Uri.parse(PreferenceManager.getDefaultSharedPreferences(ReminderService.this).getString("ringtone", getString(R.string.settings_default_ringtone)));

        nb.setDefaults(Notification.DEFAULT_VIBRATE);
        nb.setSound(uri);
        nb.setDefaults(Notification.DEFAULT_LIGHTS);



        //nb.setContentIntent(contentIntent);


        Notification notification = nb.build();

        nm.notify(NOTIF_ID, notification);
    }


}
