package com.amazon.alexa.voice.handsfree.metrics.permission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.ui.EnrollmentResumeStateStore;
/* loaded from: classes11.dex */
public class PermissionCheckReceiver extends BroadcastReceiver {
    private static final String TAG = BroadcastReceiver.class.getSimpleName();

    @VisibleForTesting
    void enqueuePermissionCheck(@NonNull Context context, @NonNull Intent intent) {
        PermissionCheckJobIntentService.enqueueWork(context, intent);
    }

    @VisibleForTesting
    boolean isAMPDDevice(@NonNull Context context) {
        return AMPDInformationProvider.getInstance(context).isAMPDDevice();
    }

    @VisibleForTesting
    boolean isEnrollmentComplete(@NonNull Context context) {
        return new EnrollmentResumeStateStore(context).getEnrollmentComplete();
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (isAMPDDevice(context) && isEnrollmentComplete(context)) {
            enqueuePermissionCheck(context, intent);
        } else {
            Log.i(TAG, "onReceive: not an AMPD device or user has not completed HandsFree enrollment, skip permission check.");
        }
    }
}
