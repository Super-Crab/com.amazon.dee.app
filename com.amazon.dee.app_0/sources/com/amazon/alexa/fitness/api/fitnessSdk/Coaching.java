package com.amazon.alexa.fitness.api.fitnessSdk;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "Ljava/io/Serializable;", "()V", "GuidedWorkout", "Milestones", "None", "Target", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$None;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$Milestones;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$Target;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$GuidedWorkout;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Coaching implements Serializable {

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$GuidedWorkout;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "guidedWorkoutId", "", "(Ljava/lang/String;)V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class GuidedWorkout extends Coaching {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GuidedWorkout(@NotNull String guidedWorkoutId) {
            super(null);
            Intrinsics.checkParameterIsNotNull(guidedWorkoutId, "guidedWorkoutId");
        }
    }

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$Milestones;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "units", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingUnits;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingUnits;)V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Milestones extends Coaching {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Milestones(@NotNull CoachingUnits units) {
            super(null);
            Intrinsics.checkParameterIsNotNull(units, "units");
        }
    }

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$None;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "()V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class None extends Coaching {
        public static final None INSTANCE = new None();

        private None() {
            super(null);
        }
    }

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching$Target;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Coaching;", "target", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget;)V", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Target extends Coaching {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Target(@NotNull CoachingTarget target) {
            super(null);
            Intrinsics.checkParameterIsNotNull(target, "target");
        }
    }

    private Coaching() {
    }

    public /* synthetic */ Coaching(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
