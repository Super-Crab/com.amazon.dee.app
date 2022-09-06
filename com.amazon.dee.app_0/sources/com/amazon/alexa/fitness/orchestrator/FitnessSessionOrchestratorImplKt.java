package com.amazon.alexa.fitness.orchestrator;

import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.api.fitnessSdk.UserProfile;
import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.alexa.fitness.model.biometric.FitnessSessionSummary;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import com.amazon.alexa.fitness.util.ConversionUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.EnumMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionOrchestratorImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\r\u001a\u001a\u0010\u000e\u001a\u00020\u000f*\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"TAG", "", "areCaloriesValid", "", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "toActivitySummary", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;", "durationInSeconds", "", "toLocationCoordinate", "Lcom/amazon/alexa/fitness/api/LocationCoordinate;", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "toSessionSummary", "Lcom/amazon/alexa/fitness/service/hds/model/SessionSummary;", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionOrchestratorImplKt {
    private static final String TAG = "FitnessSessionOrchestrator";

    public static final boolean areCaloriesValid(@NotNull UserProfile userProfile) {
        Intrinsics.checkParameterIsNotNull(userProfile, "userProfile");
        Double weightInKilograms = userProfile.getWeightInKilograms();
        return (weightInKilograms == null || weightInKilograms.doubleValue() == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) ? false : true;
    }

    @NotNull
    public static final ActivitySummary toActivitySummary(@NotNull FitnessMetrics.StepBased toActivitySummary, int i) {
        double doubleValue;
        Intrinsics.checkParameterIsNotNull(toActivitySummary, "$this$toActivitySummary");
        Pair<Double, Units> distance = toActivitySummary.getDistance();
        if (distance.getSecond() == Units.Miles) {
            doubleValue = ConversionUtils.Companion.convertMilesToFeet(distance.getFirst().doubleValue());
        } else {
            doubleValue = distance.getSecond() == Units.Feet ? distance.getFirst().doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        return new ActivitySummary(i, toActivitySummary.getSteps().getFirst().intValue(), doubleValue, toActivitySummary.getSteps().getFirst().intValue() / i, toActivitySummary.getSpeed().getFirst().doubleValue(), (int) toActivitySummary.getCalories().getFirst().doubleValue());
    }

    @NotNull
    public static final LocationCoordinate toLocationCoordinate(@NotNull Sample.LocationSample toLocationCoordinate) {
        Intrinsics.checkParameterIsNotNull(toLocationCoordinate, "$this$toLocationCoordinate");
        return new LocationCoordinate(toLocationCoordinate.getSampleData().getLatitude(), toLocationCoordinate.getSampleData().getLongitude());
    }

    @NotNull
    public static final SessionSummary toSessionSummary(@NotNull ActivitySummary toSessionSummary, @NotNull Session session, @NotNull UserProfile userProfile) {
        Map mapOf;
        Intrinsics.checkParameterIsNotNull(toSessionSummary, "$this$toSessionSummary");
        Intrinsics.checkParameterIsNotNull(session, "session");
        Intrinsics.checkParameterIsNotNull(userProfile, "userProfile");
        mapOf = MapsKt__MapsJVMKt.mapOf(TuplesKt.to(ActivityType.RUNNING, toSessionSummary));
        return new SessionSummary(session.getConfiguration().getSessionId(), userProfile.getDirectedId(), session.getCreatedAt(), session.getEndTime(), null, new FitnessSessionSummary(new EnumMap(mapOf)), areCaloriesValid(userProfile), session.getDeviceTypeInUse());
    }
}
