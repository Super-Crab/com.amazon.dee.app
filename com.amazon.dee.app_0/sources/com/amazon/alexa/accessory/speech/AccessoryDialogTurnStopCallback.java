package com.amazon.alexa.accessory.speech;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.speech.AccessoryUserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
/* loaded from: classes6.dex */
public class AccessoryDialogTurnStopCallback implements DialogTurnStopCallback {
    private final String accessoryIdentifier;
    private final Handler mainThreadHandler;
    private final SpeechSession speechSession;
    private final AccessoryUserSpeechProvider.SpeechSessionCallback speechSessionCallback;

    public AccessoryDialogTurnStopCallback(AccessoryUserSpeechProvider.SpeechSessionCallback speechSessionCallback, SpeechSession speechSession, String str) {
        Preconditions.notNull(speechSessionCallback, "callback");
        Preconditions.notNull(speechSession, "speechSession");
        this.speechSessionCallback = speechSessionCallback;
        this.speechSession = speechSession;
        this.accessoryIdentifier = str;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    public /* synthetic */ void lambda$stopRecording$0$AccessoryDialogTurnStopCallback() {
        Logger.d("AccessoryDialogTurnStopCallback: stopRecording for accessory: %s", this.accessoryIdentifier);
        Logger.i("AccessoryDialogTurnStopCallback: stopRecording for accessory.");
        this.speechSessionCallback.setEndpointedByController();
        this.speechSession.endpointSpeech();
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback
    public void stopRecording() {
        this.mainThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryDialogTurnStopCallback$pwZssICcHwuGwAXPv-ka5ucP1TE
            @Override // java.lang.Runnable
            public final void run() {
                AccessoryDialogTurnStopCallback.this.lambda$stopRecording$0$AccessoryDialogTurnStopCallback();
            }
        });
    }
}
