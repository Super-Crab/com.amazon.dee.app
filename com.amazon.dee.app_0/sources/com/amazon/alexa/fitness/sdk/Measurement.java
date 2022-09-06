package com.amazon.alexa.fitness.sdk;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: SampleDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Measurement;", "Ljava/io/Serializable;", "()V", "Discrete", "Lcom/amazon/alexa/fitness/sdk/Measurement$Discrete;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Measurement implements Serializable {

    /* compiled from: SampleDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Measurement$Discrete;", "Lcom/amazon/alexa/fitness/sdk/Measurement;", "value", "", "(D)V", "getValue", "()D", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Discrete extends Measurement {
        private final double value;

        public Discrete(double d) {
            super(null);
            this.value = d;
        }

        public final double getValue() {
            return this.value;
        }
    }

    private Measurement() {
    }

    public /* synthetic */ Measurement(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
