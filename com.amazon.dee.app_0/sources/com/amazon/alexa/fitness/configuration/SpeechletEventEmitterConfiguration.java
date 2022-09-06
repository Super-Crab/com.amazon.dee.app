package com.amazon.alexa.fitness.configuration;

import kotlin.Metadata;
/* compiled from: Configuration.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/fitness/configuration/SpeechletEventEmitterConfiguration;", "Lcom/amazon/alexa/fitness/configuration/Configuration;", "baseDelayInMillis", "", "maximumAttempts", "(II)V", "getBaseDelayInMillis", "()I", "getMaximumAttempts", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SpeechletEventEmitterConfiguration implements Configuration {
    private final int baseDelayInMillis;
    private final int maximumAttempts;

    public SpeechletEventEmitterConfiguration(int i, int i2) {
        this.baseDelayInMillis = i;
        this.maximumAttempts = i2;
    }

    public final int getBaseDelayInMillis() {
        return this.baseDelayInMillis;
    }

    public final int getMaximumAttempts() {
        return this.maximumAttempts;
    }
}
