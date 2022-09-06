package com.amazon.alexa.voice.handsfree;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.ui.utils.ResultReceiverWrapper;
import com.amazon.alexa.voice.handsfree.HandsFreeSetup;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class HandsFreeStatusQueryResultReceiver extends ResultReceiver {
    static final String ACTION_HANDS_FREE_START_SETUP = "com.amazon.alexa.handsfree.START_SETUP_FLOW_FROM_ALEXA_APP";
    static final String EXTRA_HANDS_FREE_SETUP_PENDING_INTENT = "com.amazon.alexa.handsfree.extra.HANDS_FREE_SETUP_PENDING_INTENT";
    static final int REQUEST_CODE_PENDING_INTENT = 1;
    private static final String TAG = "HandsFreeQueryRcvr";
    private final Context context;
    private final Handler handler;
    private final HandsFreeSetup.HandsFreeSetupCallback handsFreeCallback;
    private final HandsFreePermissionsSettings handsFreePermissionsSettings;

    public HandsFreeStatusQueryResultReceiver(Context context, Handler handler, HandsFreeSetup.HandsFreeSetupCallback handsFreeSetupCallback, HandsFreePermissionsSettings handsFreePermissionsSettings) {
        super(handler);
        this.context = context;
        this.handler = handler;
        this.handsFreeCallback = handsFreeSetupCallback;
        this.handsFreePermissionsSettings = handsFreePermissionsSettings;
    }

    private void showHandsFreePermissionsIfRequired() {
        if (this.handsFreePermissionsSettings.wasPermissionShown() || !HandsFreePermissionsActivityPresenter.permissionsNeeded(this.context, HandsFreeSetup.REQUIRED_PERMISSIONS)) {
            return;
        }
        Log.d(TAG, "Alexa app permissions needed. Launching permissions activity.");
        HandsFreePermissionsActivityPresenter.showHandsfreeOOBEPermissions(this.context, HandsFreeSetup.REQUESTED_PERMISSIONS, HandsFreeSetup.REQUIRED_PERMISSIONS, this);
        this.handsFreePermissionsSettings.setPermissionShown();
    }

    private void startHandsFreeSetup(Bundle bundle) {
        Intent intent = new Intent(ACTION_HANDS_FREE_START_SETUP);
        intent.setFlags(268435456);
        intent.putExtra("com.amazon.alexa.handsfree.extra.HANDS_FREE_SETUP_REQUEST_ID", ResultReceiverWrapper.getReceiverOf(new HandsFreeSetupCompleteResultReceiver(this.handler, this.context, this.handsFreePermissionsSettings)));
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(EXTRA_HANDS_FREE_SETUP_PENDING_INTENT);
        if (pendingIntent != null) {
            try {
                pendingIntent.send(this.context, 1, intent);
                this.handsFreeCallback.onSuccess();
                return;
            } catch (PendingIntent.CanceledException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Hands-free setup PendingIntent cancelled.");
                outline107.append(e.getMessage());
                Log.w(TAG, outline107.toString());
                this.handsFreeCallback.onError();
                return;
            }
        }
        this.handsFreeCallback.onError();
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        if (i == -1) {
            Log.w(TAG, "Received failure result code for setup query");
            this.handsFreeCallback.onError();
        } else if (i == 0) {
            showHandsFreePermissionsIfRequired();
            this.handsFreeCallback.onSuccess();
        } else if (i == 2) {
            Log.d(TAG, "Hands-free is not completed. Launching hands-free setup.");
            startHandsFreeSetup(bundle);
        } else if (i != 3) {
            Log.w(TAG, "Received invalid result code for setup query");
            this.handsFreeCallback.onError();
        } else {
            this.handsFreePermissionsSettings.setPermissionGranted();
        }
    }
}
