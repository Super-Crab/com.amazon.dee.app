package com.amazon.alexa.smarthomecameras.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import com.amazon.alexa.smarthomecameras.R;
import com.amazon.alexa.smarthomecameras.util.NotificationHelper;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class ALVUIService extends Service {
    public static final String ACTION_STOP = "com.amazon.alexa.smarthomecameras.service.liveview.ui.stop";
    public static final String NOTIFICATION_CHANNEL_ID = "com.amazon.alexa.smarthomecameras.service.liveview.ui";
    public static final int NOTIFICATION_ID = 1;
    private static final String TAG = ALVUIService.class.getSimpleName();
    private final IBinder binder = new LocalBinder();

    /* loaded from: classes10.dex */
    public class LocalBinder extends Binder {
        public LocalBinder() {
        }

        public ALVUIService getService() {
            return ALVUIService.this;
        }
    }

    private void startForegroundForRTC() {
        Log.i(TAG, "Starting foreground for RTC ...");
        startForeground(1, new NotificationHelper().build(this, NOTIFICATION_CHANNEL_ID, getResources().getString(R.string.alexa_mobile_live_view)));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onCreate() {
    }

    @Override // android.app.Service
    public void onDestroy() {
        int i = Build.VERSION.SDK_INT;
        stopForeground(true);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i(TAG, "Received start id " + i2 + RealTimeTextConstants.COLON_SPACE + intent);
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                char c = 65535;
                int hashCode = action.hashCode();
                if (hashCode != -468804090) {
                    if (hashCode == -127810312 && action.equals(ACTION_STOP)) {
                        c = 1;
                    }
                } else if (action.equals("com.amazon.rtcsessioncontroller.ACTION_START_APPCLIENT")) {
                    c = 0;
                }
                if (c == 0) {
                    startForegroundForRTC();
                    return 2;
                } else if (c != 1) {
                    GeneratedOutlineSupport1.outline162("Received unknown intent action: ", action, TAG);
                    return 2;
                } else {
                    Log.i(TAG, "Received intent for stop service");
                    stopForeground(true);
                    return 2;
                }
            }
            Log.i(TAG, "Intent action does not exist");
            return 2;
        }
        return 2;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        return true;
    }
}
