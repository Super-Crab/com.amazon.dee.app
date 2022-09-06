package com.amazon.alexa.fitness.context;

import com.amazon.alexa.drive.navigation.MappingApplication;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaFitnessContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"CONTEXT_NAME", "", "CONTEXT_NAMESPACE", "TAG", "isNotUnrecognized", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "toStepBasedActivityType", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AlexaFitnessContextProviderKt {
    private static final String CONTEXT_NAME = "FitnessState";
    @NotNull
    public static final String CONTEXT_NAMESPACE = "Alexa.Health.Fitness";
    private static final String TAG = "AlexaFitnessContextProvider";

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityType.values().length];

        static {
            $EnumSwitchMapping$0[ActivityType.IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityType.WALKING.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityType.RUNNING.ordinal()] = 3;
        }
    }

    private static final boolean isNotUnrecognized(@NotNull ActivityType activityType) {
        return ActivityType.UNRECOGNIZED != activityType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toStepBasedActivityType(@NotNull ActivityType activityType) {
        int i = WhenMappings.$EnumSwitchMapping$0[activityType.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return MappingApplication.WALKING;
            }
            if (i == 3) {
                return PresenceBleService.ServiceState.RUNNING;
            }
            throw new Error("Invalid Activity to convert to StepBasedActivityType. Activity: " + activityType);
        }
        return "STATIONARY";
    }
}
