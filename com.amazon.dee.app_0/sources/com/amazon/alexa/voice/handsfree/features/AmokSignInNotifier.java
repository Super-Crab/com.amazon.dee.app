package com.amazon.alexa.voice.handsfree.features;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.utils.Log;
@AhfScope
/* loaded from: classes11.dex */
public class AmokSignInNotifier {
    private static final String ACTION = "com.amazon.alexa.handsfree.settings.REPORT_ALEXA_SIGN_IN";
    private static final String COMPONENT_CLASS = "com.amazon.alexa.handsfree.settings.receivers.AlexaSignInReceiver";
    private static final String TAG = "AmokSignInNotifier";

    @NonNull
    AMPDInformationProvider getAMPDInformationProvider(@NonNull Context context) {
        return AMPDInformationProvider.getInstance(context);
    }

    public void notifySignIn(@NonNull Context context) {
        String voiceAppPackageName = getAMPDInformationProvider(context).getVoiceAppPackageName();
        if (voiceAppPackageName == null) {
            Log.d(TAG, "There's no voice apk, skipping sending the broadcast");
            return;
        }
        Log.d(TAG, "Notify of Alexa app sign in.");
        context.sendBroadcast(new Intent().setComponent(new ComponentName(voiceAppPackageName, COMPONENT_CLASS)).setAction(ACTION));
    }
}
