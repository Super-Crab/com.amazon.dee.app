package com.amazon.alexa.fitness.message;

import java.util.Random;
import kotlin.Metadata;
/* compiled from: SpeechletEventEmitterImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"TAG", "", "calculateDelay", "", "attempt", "", "baseDelay", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SpeechletEventEmitterImplKt {
    private static final String TAG = "SpeechletEventEmitter";

    public static final long calculateDelay(int i, int i2) {
        return (long) ((Math.pow(2.0d, i - 1) * i2) - new Random().nextInt(i2 / 2));
    }
}
