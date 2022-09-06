package com.amazon.alexa.voice.wakeword;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface WakewordPreference {
    void enableWakeword(boolean z);

    boolean isWakewordEnabled();

    Observable<Boolean> onChanged();
}
