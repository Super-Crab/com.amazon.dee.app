package com.amazon.alexa.voice.wakeword;

import androidx.annotation.VisibleForTesting;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
public class FeatureOverrideWakewordPrecondition implements ObservableWakeWordPrecondition {
    private final WakeWordFeatureOverrideObserver wakeWordFeatureOverrideObserver;

    /* loaded from: classes11.dex */
    static class WakeWordFeatureOverrideObserver implements WakeWordEventObserver {
        private final BehaviorSubject<Boolean> isEnabled = BehaviorSubject.createDefault(true);

        WakeWordFeatureOverrideObserver() {
        }

        BehaviorSubject<Boolean> isEnabled() {
            return this.isEnabled;
        }

        @Override // com.amazon.alexa.voice.wakeword.WakeWordEventObserver
        public void onWakeWordDisableEvent() {
            if (this.isEnabled.getValue().booleanValue()) {
                this.isEnabled.onNext(false);
            }
        }

        @Override // com.amazon.alexa.voice.wakeword.WakeWordEventObserver
        public void onWakeWordEnableEvent() {
            if (!this.isEnabled.getValue().booleanValue()) {
                this.isEnabled.onNext(true);
            }
        }
    }

    public FeatureOverrideWakewordPrecondition(WakeWordEventHandler wakeWordEventHandler) {
        this(wakeWordEventHandler, new WakeWordFeatureOverrideObserver());
    }

    @Override // com.amazon.alexa.voice.wakeword.ObservableWakeWordPrecondition
    public Observable<Boolean> isWakeWordAllowed() {
        return this.wakeWordFeatureOverrideObserver.isEnabled();
    }

    @VisibleForTesting
    FeatureOverrideWakewordPrecondition(WakeWordEventHandler wakeWordEventHandler, WakeWordFeatureOverrideObserver wakeWordFeatureOverrideObserver) {
        this.wakeWordFeatureOverrideObserver = wakeWordFeatureOverrideObserver;
        wakeWordEventHandler.setWakeWordEventObserver(wakeWordFeatureOverrideObserver);
    }
}
