package com.amazon.alexa.voice.sdk;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
public class AlexaStateTracker implements AlexaStateListener {
    private static final String TAG = "AlexaStateTracker";
    private final BehaviorSubject<AlexaState> alexaState = BehaviorSubject.createDefault(AlexaState.UNKNOWN);

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isListening(AlexaState alexaState) {
        return AlexaState.LISTENING == alexaState || AlexaState.PREPARING_TO_LISTEN == alexaState || AlexaState.FINISHING_LISTENING == alexaState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNotRunning(AlexaState alexaState) {
        return AlexaState.IDLE == alexaState || AlexaState.UNKNOWN == alexaState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isProcessing(AlexaState alexaState) {
        return AlexaState.THINKING == alexaState;
    }

    public boolean isListeningOrProcessing() {
        return isListeningOrProcessing(this.alexaState.getValue());
    }

    public boolean isSpeaking() {
        return isSpeaking(this.alexaState.getValue());
    }

    @Override // com.amazon.alexa.api.compat.AlexaStateListener
    public void onAlexaStateChanged(@NonNull AlexaState alexaState) {
        if (this.alexaState.getValue() == alexaState) {
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Alexa state changed: ");
        outline107.append(this.alexaState.getValue());
        outline107.append(" -> ");
        outline107.append(alexaState);
        Log.i(str, outline107.toString());
        this.alexaState.onNext(alexaState);
    }

    public Observable<Boolean> onIdle() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$XyPxBjN6N6sYMCP_MaZ9eZSn4Q.INSTANCE).distinctUntilChanged();
    }

    public Observable<Boolean> onListening() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$xxFNbcsdzrWyA3pB9UyzpEnSHgQ.INSTANCE).distinctUntilChanged();
    }

    public Observable<Boolean> onListeningOrProcessing() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$RX4cH7DkFpmsrpINFKt2wGPznPM.INSTANCE).distinctUntilChanged();
    }

    public Observable<Boolean> onProcessing() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$4_DxCEYGtvQnS52U_oaOTYe4A.INSTANCE).distinctUntilChanged();
    }

    public Observable<Boolean> onSpeaking() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$nWKgogokCFfEe7Q9uz41gsoJZI.INSTANCE).distinctUntilChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isListeningOrProcessing(AlexaState alexaState) {
        return isProcessing(alexaState) || isListening(alexaState);
    }

    private static boolean isSpeaking(AlexaState alexaState) {
        return AlexaState.SPEAKING == alexaState;
    }
}
