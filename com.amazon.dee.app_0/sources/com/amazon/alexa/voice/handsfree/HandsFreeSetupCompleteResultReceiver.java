package com.amazon.alexa.voice.handsfree;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes11.dex */
public class HandsFreeSetupCompleteResultReceiver extends ResultReceiver {
    private static final String TAG = "HandsFreeCompleteRcvr";
    private final Context context;
    private final HandsFreePermissionsSettings handsFreePermissionsSettings;

    public HandsFreeSetupCompleteResultReceiver(Handler handler, Context context, HandsFreePermissionsSettings handsFreePermissionsSettings) {
        super(handler);
        this.context = context;
        this.handsFreePermissionsSettings = handsFreePermissionsSettings;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        if (i != 0) {
            if (i == 3) {
                this.handsFreePermissionsSettings.setPermissionGranted();
                return;
            } else {
                Log.w(TAG, "Received non-success result code for setup finish");
                return;
            }
        }
        Log.d(TAG, "Hands-free setup returned success code. Checking whether to ask for Alexa app permissions");
        if (!this.handsFreePermissionsSettings.wasPermissionShown() && HandsFreePermissionsActivityPresenter.permissionsNeeded(this.context, HandsFreeSetup.REQUIRED_PERMISSIONS)) {
            Log.d(TAG, "Alexa app permissions needed. Launching permissions activity.");
            HandsFreePermissionsActivityPresenter.showHandsfreeOOBEPermissions(this.context, HandsFreeSetup.REQUESTED_PERMISSIONS, HandsFreeSetup.REQUIRED_PERMISSIONS, this);
            this.handsFreePermissionsSettings.setPermissionShown();
            return;
        }
        Log.d(TAG, "Permissions already granted or shown. Setup complete.");
    }
}
