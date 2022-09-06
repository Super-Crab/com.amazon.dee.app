package com.amazon.alexa.voice.tta.sdk;

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

    public Observable<Boolean> onListening() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$x5OlmDPYTEBsdRHPrL4zAp1K1o.INSTANCE).distinctUntilChanged();
    }

    public Observable<Boolean> onThinking() {
        return this.alexaState.map($$Lambda$AlexaStateTracker$EbUam_yXtzp4mdU5vVJ4U2EHc8.INSTANCE).distinctUntilChanged();
    }
}
