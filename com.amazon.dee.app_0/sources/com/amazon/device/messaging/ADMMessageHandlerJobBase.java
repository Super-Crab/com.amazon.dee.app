package com.amazon.device.messaging;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.device.messaging.ADMConstants;
import com.amazon.fireos.sdk.annotations.Extends;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
@Extends(superClass = Object.class)
/* loaded from: classes12.dex */
public abstract class ADMMessageHandlerJobBase extends JobIntentService {
    private void handleNewRegistrationID(Context context, Intent intent) {
        onRegistered(context, intent.getStringExtra(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID));
    }

    private void handleRegistrationChange(Intent intent) {
        if (intent.hasExtra("error")) {
            String stringExtra = intent.getStringExtra("error");
            if (intent.hasExtra("error_description")) {
                Log.e("ADM", String.format("ADM Error %s - %s", stringExtra, intent.getStringExtra("error_description")));
            }
            handleRegistrationError(this, stringExtra);
        } else if (intent.hasExtra(ADMConstants.LowLevel.EXTRA_UNREGISTERED)) {
            handleUnregister(this, intent);
        } else if (intent.hasExtra(ADMConstants.LowLevel.EXTRA_REGISTRATION_ID)) {
            handleNewRegistrationID(this, intent);
        } else {
            handleRegistrationError(this, ADMConstants.ERROR_SERVICE_NOT_AVAILABLE);
        }
    }

    private void handleRegistrationError(Context context, String str) {
        onRegistrationError(context, str);
    }

    private void handleUnregister(Context context, Intent intent) {
        onUnregistered(context, intent.getStringExtra(ADMConstants.LowLevel.EXTRA_OLD_REGISTRATION_ID));
    }

    @Override // com.amazon.device.messaging.JobIntentService
    protected final void onHandleWork(Intent intent) {
        try {
            if (ADMConstants.LowLevel.ACTION_APP_REGISTRATION_EVENT.equals(intent.getAction())) {
                handleRegistrationChange(intent);
            } else if (ADMConstants.LowLevel.ACTION_RECEIVE_ADM_MESSAGE.equals(intent.getAction())) {
                onMessage(this, intent);
            }
        } catch (NullPointerException unused) {
            Log.i("ADM", "ADM message handler invoked with null intent");
        }
    }

    @FireOsSdk
    protected abstract void onMessage(Context context, Intent intent);

    @FireOsSdk
    protected abstract void onRegistered(Context context, String str);

    @FireOsSdk
    protected abstract void onRegistrationError(Context context, String str);

    @FireOsSdk
    protected abstract void onUnregistered(Context context, String str);
}
