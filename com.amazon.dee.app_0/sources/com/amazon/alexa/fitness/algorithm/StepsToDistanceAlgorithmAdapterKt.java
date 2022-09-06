package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.algorithms.ActivityType;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: StepsToDistanceAlgorithmAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\b\u001a\n\u0010\t\u001a\u00020\b*\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"CENTIMETRES_TO_INCHES_CONVERSION", "", "STDAlgorithmId", "", "Lcom/amazon/alexa/fitness/algorithm/AlgorithmId;", "TAG", "toAfxActivity", "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "toAlgorithmActivityType", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class StepsToDistanceAlgorithmAdapterKt {
    private static final double CENTIMETRES_TO_INCHES_CONVERSION = 2.54d;
    @NotNull
    public static final String STDAlgorithmId = "com.amazon.fitness.stepsToDistance";
    private static final String TAG = "StepsToDistanceAlgorithmAdapter";

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_WALK.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_RUN.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_IDLE.ordinal()] = 3;
            $EnumSwitchMapping$1 = new int[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.values().length];
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.UNRECOGNIZED.ordinal()] = 1;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.WALKING.ordinal()] = 2;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.RUNNING.ordinal()] = 3;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.IDLE.ordinal()] = 4;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.UNKNOWN.ordinal()] = 5;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.BICYCLING.ordinal()] = 6;
            $EnumSwitchMapping$1[com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.DRIVING.ordinal()] = 7;
        }
    }

    @NotNull
    public static final com.amazon.alexa.fitness.api.fitnessSdk.ActivityType toAfxActivity(@NotNull ActivityType toAfxActivity) {
        Intrinsics.checkParameterIsNotNull(toAfxActivity, "$this$toAfxActivity");
        int i = WhenMappings.$EnumSwitchMapping$0[toAfxActivity.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.RUNNING;
            }
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            return com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.IDLE;
        }
        return com.amazon.alexa.fitness.api.fitnessSdk.ActivityType.WALKING;
    }

    @NotNull
    public static final ActivityType toAlgorithmActivityType(@NotNull com.amazon.alexa.fitness.api.fitnessSdk.ActivityType toAlgorithmActivityType) {
        Intrinsics.checkParameterIsNotNull(toAlgorithmActivityType, "$this$toAlgorithmActivityType");
        switch (WhenMappings.$EnumSwitchMapping$1[toAlgorithmActivityType.ordinal()]) {
            case 1:
                return ActivityType.ACTIVITY_TYPE_IDLE;
            case 2:
                return ActivityType.ACTIVITY_TYPE_WALK;
            case 3:
                return ActivityType.ACTIVITY_TYPE_RUN;
            case 4:
            case 5:
            case 6:
            case 7:
                return ActivityType.ACTIVITY_TYPE_IDLE;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
