package com.amazon.alexa.voice.features;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.voice.features.VoiceFeatureChecker;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class VoiceFeatureChecker implements FeatureChecker {
    public static final String TAG = "VoiceFeatureChecker";
    private FeatureServiceV2 featureService;
    private Handler handler;
    private final Map<String, BehaviorSubject<Boolean>> featureObservables = new HashMap();
    private VoiceFeatureUpdateListener featureUpdateListener = new VoiceFeatureUpdateListener();

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public class VoiceFeatureUpdateListener implements FeatureServiceV2.FeatureUpdateListener {
        VoiceFeatureUpdateListener() {
        }

        public /* synthetic */ void lambda$onFeatureUpdate$0$VoiceFeatureChecker$VoiceFeatureUpdateListener(String str) {
            VoiceFeatureChecker voiceFeatureChecker = VoiceFeatureChecker.this;
            voiceFeatureChecker.updateFeatureAvailability(str, voiceFeatureChecker.featureService);
        }

        @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
        public void onFeatureUpdate(final String str) {
            VoiceFeatureChecker.this.handler.post(new Runnable() { // from class: com.amazon.alexa.voice.features.-$$Lambda$VoiceFeatureChecker$VoiceFeatureUpdateListener$iz_t2_r4R1O2ZZfCzbjw0iQ1j2o
                @Override // java.lang.Runnable
                public final void run() {
                    VoiceFeatureChecker.VoiceFeatureUpdateListener.this.lambda$onFeatureUpdate$0$VoiceFeatureChecker$VoiceFeatureUpdateListener(str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceFeatureChecker(@NonNull FeatureServiceV2 featureServiceV2, @NonNull Handler handler) {
        this.handler = handler;
        this.featureService = featureServiceV2;
        for (VoiceFeature voiceFeature : VoiceFeature.values()) {
            this.featureObservables.put(voiceFeature.getName(), BehaviorSubject.createDefault(false));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFeatureAvailability(String str, FeatureServiceV2 featureServiceV2) {
        if (this.featureObservables.containsKey(str)) {
            this.featureObservables.get(str).onNext(Boolean.valueOf(featureServiceV2.hasAccess(str, false)));
        }
    }

    private void updateFeaturesAvailability() {
        for (Map.Entry<String, BehaviorSubject<Boolean>> entry : this.featureObservables.entrySet()) {
            entry.getValue().onNext(Boolean.valueOf(this.featureService.hasAccess(entry.getKey(), false)));
        }
    }

    @Override // com.amazon.alexa.voice.features.FeatureChecker
    public boolean hasFeature(VoiceFeature voiceFeature) {
        BehaviorSubject<Boolean> behaviorSubject = this.featureObservables.get(voiceFeature.getName());
        if (behaviorSubject != null) {
            return behaviorSubject.getValue().booleanValue();
        }
        return false;
    }

    public void initialize() {
        for (VoiceFeature voiceFeature : VoiceFeature.values()) {
            this.featureService.observeFeature(voiceFeature.getName(), this.featureUpdateListener);
        }
        updateFeaturesAvailability();
    }

    @Override // com.amazon.alexa.voice.features.FeatureChecker
    public Observable<Boolean> onFeatureAvailability(VoiceFeature voiceFeature) {
        BehaviorSubject<Boolean> behaviorSubject = this.featureObservables.get(voiceFeature.getName());
        this.featureObservables.get(voiceFeature.getName()).getValue();
        return behaviorSubject == null ? BehaviorSubject.createDefault(false) : behaviorSubject;
    }

    public void release() {
        this.featureService.unsubscribe(this.featureUpdateListener);
    }
}
