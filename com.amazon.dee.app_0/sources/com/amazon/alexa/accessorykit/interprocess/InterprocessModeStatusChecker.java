package com.amazon.alexa.accessorykit.interprocess;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.AccessoriesFactory;
import com.amazon.alexa.accessorykit.interprocess.utils.IPCUtils;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
/* loaded from: classes6.dex */
public class InterprocessModeStatusChecker implements ModeStatusChecker {
    public static final String INTENT_ACTION_DRIVE_MODE_STATUS = "com.amazon.alexa.accessorykit.action.drivemode.status";
    public static final String MODE_SERVICE_ACTIVE_BOOLEAN_INTENT_KEY = "active";
    private static final String TAG = "InterprocessModeStatusChecker:";
    private boolean active;
    private final ActivityManager activityManager;
    private final Context context;
    private volatile boolean isDriveModeForeground;
    private final IntentFilter receiveModeIntentFilter;
    private final Intent requestModeIntent;

    public InterprocessModeStatusChecker(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.activityManager = (ActivityManager) context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        this.receiveModeIntentFilter = new IntentFilter(INTENT_ACTION_DRIVE_MODE_STATUS);
        this.requestModeIntent = new Intent(ModeStatusEmitter.INTENT_ACTION_DRIVE_MODE_STATUS_REQUEST).setPackage(AccessoriesFactory.getAppName());
    }

    private BroadcastReceiver createModeServiceStateReceiver() {
        return new BroadcastReceiver() { // from class: com.amazon.alexa.accessorykit.interprocess.InterprocessModeStatusChecker.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Bundle extras;
                if (InterprocessModeStatusChecker.INTENT_ACTION_DRIVE_MODE_STATUS.equals(intent.getAction()) && (extras = intent.getExtras()) != null) {
                    boolean z = extras.getBoolean("active");
                    Logger.d("%s Drive mode changed, isActive: %b", InterprocessModeStatusChecker.TAG, Boolean.valueOf(z));
                    InterprocessModeStatusChecker.this.isDriveModeForeground = z;
                }
            }
        };
    }

    private void listenForModeChangesInterprocess() {
        this.context.registerReceiver(createModeServiceStateReceiver(), this.receiveModeIntentFilter, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION", null);
    }

    private void requestCurrentModeStateInterprocess() {
        this.context.sendBroadcast(this.requestModeIntent, "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    public synchronized InterprocessModeStatusChecker activate() {
        if (this.active) {
            return this;
        }
        this.active = true;
        listenForModeChangesInterprocess();
        requestCurrentModeStateInterprocess();
        return this;
    }

    @Override // com.amazon.alexa.accessory.avsclient.mode.ModeStatusChecker
    public boolean isDriveModeForeground() {
        return this.isDriveModeForeground && IPCUtils.isMainProcessAlive(this.activityManager);
    }
}
