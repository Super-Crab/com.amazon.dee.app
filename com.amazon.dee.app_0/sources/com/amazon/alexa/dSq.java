package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.AlexaDialogTurn;
import com.amazon.alexa.api.AlexaNextDialogTurn;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.DialogData;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.DialogTurnData;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import javax.inject.Singleton;
/* compiled from: LegacySystemWakeWordUserSpeechProvider.java */
@Singleton
/* loaded from: classes.dex */
public class dSq implements AlexaUserSpeechProvider {
    public static final String zZm = "dSq";

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogFinished(DialogData dialogData) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequestDenied(DialogRequestDeniedReason dialogRequestDeniedReason) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequested(AlexaDialogTurn alexaDialogTurn) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogStarted(DialogData dialogData) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnFinished(DialogTurnData dialogTurnData) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnStarted(DialogTurnData dialogTurnData) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void pauseWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void resumeWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("System wakeword is expected to be ");
        zZm2.append(z ? "enabled" : FeatureState.DISABLED);
        Log.w(str, zZm2.toString());
    }
}
