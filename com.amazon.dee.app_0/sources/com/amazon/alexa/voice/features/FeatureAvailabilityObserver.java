package com.amazon.alexa.voice.features;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class FeatureAvailabilityObserver {
    private final List<Disposable> disposables;
    private final VoiceFeatureChecker featureChecker;
    private final FeatureEnabler[] featureEnablers;

    public FeatureAvailabilityObserver(@NonNull VoiceFeatureChecker voiceFeatureChecker, @NonNull FeatureEnabler... featureEnablerArr) {
        this.featureChecker = voiceFeatureChecker;
        this.featureEnablers = featureEnablerArr;
        this.disposables = new ArrayList(featureEnablerArr.length);
    }

    public void startObserving() {
        FeatureEnabler[] featureEnablerArr;
        for (final FeatureEnabler featureEnabler : this.featureEnablers) {
            this.disposables.add(this.featureChecker.onFeatureAvailability(featureEnabler.getFeature()).subscribe(new Consumer() { // from class: com.amazon.alexa.voice.features.-$$Lambda$FeatureAvailabilityObserver$Pj269OQ-o30QmwVuuxDScWYpxRM
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    FeatureEnabler.this.enableFeature(((Boolean) obj).booleanValue());
                }
            }));
        }
        this.featureChecker.initialize();
    }

    public void stopObserving() {
        this.featureChecker.release();
        for (Disposable disposable : this.disposables) {
            disposable.dispose();
        }
        this.disposables.clear();
    }
}
