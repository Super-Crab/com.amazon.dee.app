package com.amazon.alexa.voice.wakeword;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface ObservableWakeWordPrecondition {
    Observable<Boolean> isWakeWordAllowed();
}
