package com.amazon.deecomms.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public class CommsActivityMonitor {
    @VisibleForTesting
    static final String ACTIVITY_MONITOR_ACTION = "com.amazon.deecomms.receiver.ACTIVITY_MONITOR_ACTION";
    private final ActivityManager activityManager;
    private final AlarmManager alarmManager;
    private final Application applicationContext;
    private boolean lastOnForeground;
    private final LocalBroadcastManager localBroadcastManager;
    private PendingIntent pendingIntent;
    private boolean started;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsActivityMonitor.class);
    @VisibleForTesting
    static final long IMPORTANCE_CHECK_DELAY = TimeUnit.SECONDS.toMillis(10);
    private final Object pendingIntentLock = new Object();
    @VisibleForTesting
    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.CommsActivityMonitor.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                CommsActivityMonitor.LOG.e("Invalid intent: null");
                return;
            }
            String action = intent.getAction();
            CommsActivityMonitor.LOG.v("onReceive: %s", action);
            if (CommsActivityMonitor.ACTIVITY_MONITOR_ACTION.equals(action)) {
                CommsActivityMonitor.this.performImportanceCheck();
            } else {
                CommsActivityMonitor.LOG.e("Invalid action: %s", action);
            }
        }
    };
    @VisibleForTesting
    final Application.ActivityLifecycleCallbacks callbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.amazon.deecomms.common.CommsActivityMonitor.2
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            CommsActivityMonitor.LOG.d("onActivityCreated: %s", activity);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            CommsActivityMonitor.LOG.d("onActivityDestroyed: %s", activity);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            CommsActivityMonitor.LOG.d("onActivityPaused: %s", activity);
            Utils.writeBooleanPreferenceToSharedPrefs(CommsActivityMonitor.this.applicationContext, Constants.IS_APP_IN_FOREGROUND, false);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            CommsActivityMonitor.LOG.d("onActivityResumed: %s", activity);
            Utils.writeBooleanPreferenceToSharedPrefs(CommsActivityMonitor.this.applicationContext, Constants.IS_APP_IN_FOREGROUND, true);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            CommsActivityMonitor.LOG.d("onActivityStarted: %s", activity);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            CommsActivityMonitor.LOG.d("onActivityStopped: %s", activity);
            CommsActivityMonitor.this.scheduleImportanceCheck();
        }
    };

    public CommsActivityMonitor(@NonNull Context context, @NonNull AlarmManager alarmManager, @NonNull ActivityManager activityManager) {
        LOG.v("Constructor");
        this.applicationContext = (Application) context.getApplicationContext();
        this.localBroadcastManager = LocalBroadcastManager.getInstance(this.applicationContext);
        this.alarmManager = alarmManager;
        this.activityManager = activityManager;
    }

    private void cancelImportantCheck() {
        synchronized (this.pendingIntentLock) {
            if (this.pendingIntent != null) {
                LOG.v("cancelImportantCheck");
                this.alarmManager.cancel(this.pendingIntent);
                this.pendingIntent.cancel();
                this.pendingIntent = null;
            }
        }
    }

    private PendingIntent getPendingIntent(int i) {
        return PendingIntent.getBroadcast(this.applicationContext, 0, new Intent(ACTIVITY_MONITOR_ACTION), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performImportanceCheck() {
        LOG.v("Checking application foreground/background state");
        int fetchAppImportance = fetchAppImportance();
        LOG.v("Current app importance: %s", importanceToString(fetchAppImportance));
        boolean importanceToForeground = importanceToForeground(fetchAppImportance);
        boolean z = this.lastOnForeground != importanceToForeground;
        if (z) {
            LOG.i("App foreground state changed from %b to %b", Boolean.valueOf(this.lastOnForeground), Boolean.valueOf(importanceToForeground));
        } else {
            LOG.i("App foreground state is unchanged: %b", Boolean.valueOf(importanceToForeground));
        }
        this.lastOnForeground = importanceToForeground;
        Intent intent = new Intent(Constants.APPLICATION_FOREGROUND_CHECKED_ACTION);
        intent.putExtra(Constants.KEY_APPLICATION_FOREGROUND_CHANGED, z);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleImportanceCheck() {
        synchronized (this.pendingIntentLock) {
            cancelImportantCheck();
            LOG.v("scheduleImportanceCheck: %d ms", Long.valueOf(IMPORTANCE_CHECK_DELAY));
            this.pendingIntent = getPendingIntent(268435456);
            this.alarmManager.setExact(2, SystemClock.elapsedRealtime() + IMPORTANCE_CHECK_DELAY, this.pendingIntent);
        }
    }

    @VisibleForTesting
    int fetchAppImportance() {
        return Utils.getAppImportance(this.activityManager);
    }

    @VisibleForTesting
    boolean importanceToForeground(int i) {
        if (i != 100) {
            if (i != 125 && i != 130) {
                if (i == 200) {
                    return true;
                }
                if (i == 230 || i == 300 || i == 325 || i == 400 || i != 500) {
                }
                return false;
            }
            return false;
        }
        return true;
    }

    @NonNull
    @VisibleForTesting
    String importanceToString(int i) {
        return i != 100 ? i != 125 ? i != 130 ? i != 200 ? i != 230 ? i != 300 ? i != 325 ? i != 400 ? i != 500 ? i != 1000 ? GeneratedOutlineSupport1.outline52("UNKNOWN (", i, ")") : "IMPORTANCE_GONE" : "IMPORTANCE_EMPTY" : "IMPORTANCE_CACHED" : "IMPORTANCE_TOP_SLEEPING" : "IMPORTANCE_SERVICE" : "IMPORTANCE_PERCEPTIBLE" : "IMPORTANCE_VISIBLE" : "IMPORTANCE_PERCEPTIBLE" : "IMPORTANCE_FOREGROUND_SERVICE" : "IMPORTANCE_FOREGROUND";
    }

    public boolean isOnForeground() {
        return importanceToForeground(fetchAppImportance());
    }

    public boolean isStarted() {
        return this.started;
    }

    @VisibleForTesting
    void setLastOnForeground(boolean z) {
        this.lastOnForeground = z;
    }

    public void start() {
        if (this.started) {
            LOG.d("Already started");
            return;
        }
        this.started = true;
        this.lastOnForeground = importanceToForeground(fetchAppImportance());
        LOG.d("register: onForeground=%b", Boolean.valueOf(this.lastOnForeground));
        this.applicationContext.registerReceiver(this.broadcastReceiver, new IntentFilter(ACTIVITY_MONITOR_ACTION));
        this.applicationContext.registerActivityLifecycleCallbacks(this.callbacks);
    }

    public void stop() {
        if (!this.started) {
            LOG.d("Already stopped");
            return;
        }
        this.started = false;
        LOG.d("unregister: onForeground=%b", Boolean.valueOf(importanceToForeground(fetchAppImportance())));
        this.applicationContext.unregisterReceiver(this.broadcastReceiver);
        cancelImportantCheck();
        this.applicationContext.unregisterActivityLifecycleCallbacks(this.callbacks);
    }

    @VisibleForTesting
    public CommsActivityMonitor(@NonNull Context context, @NonNull AlarmManager alarmManager, @NonNull ActivityManager activityManager, @NonNull LocalBroadcastManager localBroadcastManager) {
        LOG.v("Constructor");
        this.applicationContext = (Application) context.getApplicationContext();
        this.localBroadcastManager = localBroadcastManager;
        this.alarmManager = alarmManager;
        this.activityManager = activityManager;
    }
}
