package com.amazon.alexa.fitness.service.hds.model;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/RecordType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "STEPS", "DISTANCE", "SPEED", "PACE", "CADENCE", "WORKOUT_DURATION", "CALORIES", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum RecordType {
    STEPS("Steps"),
    DISTANCE("Distance"),
    SPEED("Speed"),
    PACE("Pace"),
    CADENCE("Cadence"),
    WORKOUT_DURATION("WorkoutDuration"),
    CALORIES("Calories");
    
    @NotNull
    private final String value;

    RecordType(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
