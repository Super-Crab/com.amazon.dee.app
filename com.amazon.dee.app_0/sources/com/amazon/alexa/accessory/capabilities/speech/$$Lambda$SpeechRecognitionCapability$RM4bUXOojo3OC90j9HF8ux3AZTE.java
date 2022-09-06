package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.repositories.device.DeviceFeature;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE implements Predicate {
    public static final /* synthetic */ $$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE INSTANCE = new $$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE();

    private /* synthetic */ $$Lambda$SpeechRecognitionCapability$RM4bUXOojo3OC90j9HF8ux3AZTE() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return SpeechRecognitionCapability.lambda$initializeSpeechRelatedFeatures$11((DeviceFeature) obj);
    }
}
