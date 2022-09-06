package com.amazon.alexa.voice.wakeword;

import io.reactivex.rxjava3.core.Observable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class UserAllowedWakewordPrecondition implements ObservableWakeWordPrecondition {
    private final WakewordPreference wakewordPreference;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserAllowedWakewordPrecondition(WakewordPreference wakewordPreference) {
        this.wakewordPreference = wakewordPreference;
    }

    @Override // com.amazon.alexa.voice.wakeword.ObservableWakeWordPrecondition
    public Observable<Boolean> isWakeWordAllowed() {
        return this.wakewordPreference.onChanged();
    }
}
