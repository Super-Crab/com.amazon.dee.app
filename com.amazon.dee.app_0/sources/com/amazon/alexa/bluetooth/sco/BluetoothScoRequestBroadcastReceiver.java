package com.amazon.alexa.bluetooth.sco;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.ConditionVariable;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
class BluetoothScoRequestBroadcastReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final String SAMSUNG_SCO_ERROR_STATE = "SAMSUNG_SCO_ERROR_STATE";
    @VisibleForTesting
    static final String SCO_ERROR_STATE = "SCO_ERROR_STATE";
    private static final String TAG = BluetoothScoRequestBroadcastReceiver.class.getSimpleName();
    private final Context context;
    private final DeviceInformation deviceInformation;
    private boolean hasDisconnected;
    private boolean hasHandledSamsungBug;
    private boolean isInSco;
    private volatile boolean isRegistered;
    private final ScoMetrics scoMetrics;
    private final ConditionVariable scoRequestConditionVariable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BluetoothScoRequestBroadcastReceiver(Context context, ConditionVariable conditionVariable, DeviceInformation deviceInformation, ScoMetrics scoMetrics) {
        this.context = context;
        this.scoRequestConditionVariable = conditionVariable;
        this.deviceInformation = deviceInformation;
        this.scoMetrics = scoMetrics;
    }

    private void setBluetoothSco(Intent intent) {
        boolean z = true;
        if (intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1) != 1) {
            z = false;
        }
        this.isInSco = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isHasDisconnected() {
        return this.hasDisconnected;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isInSco() {
        return this.isInSco;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(intent.getAction())) {
            int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
            if (intExtra == 0) {
                this.isInSco = false;
                this.hasHandledSamsungBug = false;
                this.hasDisconnected = true;
                this.scoRequestConditionVariable.open();
            } else if (intExtra == 1) {
                this.isInSco = true;
                this.hasHandledSamsungBug = false;
                this.hasDisconnected = false;
                this.scoRequestConditionVariable.open();
            } else if (intExtra == 2) {
            } else {
                if (!this.hasHandledSamsungBug && this.deviceInformation.isSamsungAndAndroidQ()) {
                    Log.i(TAG, "Entered error state on Samsung phone running Android 10, treating as connecting...");
                    this.hasHandledSamsungBug = true;
                    ScoMetrics scoMetrics = this.scoMetrics;
                    if (scoMetrics == null) {
                        return;
                    }
                    scoMetrics.recordCount(SAMSUNG_SCO_ERROR_STATE, 1);
                    return;
                }
                Log.e(TAG, "Error SCO State");
                ScoMetrics scoMetrics2 = this.scoMetrics;
                if (scoMetrics2 != null) {
                    scoMetrics2.recordCount(SCO_ERROR_STATE, 1);
                }
                this.scoRequestConditionVariable.open();
            }
        }
    }

    public void register() {
        Intent registerReceiver = this.context.registerReceiver(this, GeneratedOutlineSupport1.outline10("android.media.ACTION_SCO_AUDIO_STATE_UPDATED"));
        this.isRegistered = true;
        if (registerReceiver != null) {
            setBluetoothSco(registerReceiver);
        }
    }

    @VisibleForTesting
    void setIsInSco(boolean z) {
        this.isInSco = z;
    }

    public void unregister() {
        if (this.isRegistered) {
            this.isRegistered = false;
            this.context.unregisterReceiver(this);
        }
    }
}
