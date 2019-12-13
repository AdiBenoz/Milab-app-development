package com.example.ex03;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class NotifyIntentService extends IntentService {
    private static final String ACTION_BUMP_QUOTE = "com.example.exercise_03.action.BUMP_QUOTE";


    public NotifyIntentService() {
        super("NotifyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    private void startAlarm() {
        Log.d("alarm", "Alarm");
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(this.ALARM_SERVICE);
        long when = System.currentTimeMillis();
        Intent intent = new Intent(this, NotificationReceiver.class);
        intent.addFlags(Intent.FLAG_RECEIVER_FOREGROUND); //x
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NotificationReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, when, (AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15), pendingIntent);
    }


    public static void startActionQuote(MainActivity context) {
        Intent intent = new Intent(context, NotifyIntentService.class);
        intent.setAction(ACTION_BUMP_QUOTE);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.d("Intent Service", "intent service");
            if (ACTION_BUMP_QUOTE.equals(action)) {
                handleActionBumpQuote();
            } else {
                throw new RuntimeException("Unknown action provided");
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBumpQuote() {
        startAlarm();
    }
}
