package com.amazon.alexa.fitness.service.afits;

import com.amazon.alexa.fitness.api.afits.ActivitySummary;
import com.amazon.alexa.fitness.util.ConversionUtilsKt;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkoutSessionUploader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"TAG", "", "toAfitsActivitySummary", "Lcom/amazon/alexa/fitness/api/afits/ActivitySummary;", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutSessionUploaderKt {
    private static final String TAG = "AFX-SessionUploader";

    @NotNull
    public static final ActivitySummary toAfitsActivitySummary(@NotNull com.amazon.alexa.fitness.model.biometric.ActivitySummary toAfitsActivitySummary) {
        Intrinsics.checkParameterIsNotNull(toAfitsActivitySummary, "$this$toAfitsActivitySummary");
        double distanceInFeet = toAfitsActivitySummary.getDistanceInFeet();
        return new ActivitySummary(ConversionUtilsKt.feetToMeters(distanceInFeet), ConversionUtilsKt.mphToMetersPerSecond(toAfitsActivitySummary.getSpeedInMph()), toAfitsActivitySummary.getTotalSteps(), ConversionUtilsKt.mphToMinutesPerKm(toAfitsActivitySummary.getSpeedInMph()), Double.isNaN(toAfitsActivitySummary.getCadence()) ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : toAfitsActivitySummary.getCadence(), ConversionUtilsKt.secondsToMilliseconds(toAfitsActivitySummary.getTimeInSeconds()), Double.valueOf(toAfitsActivitySummary.getCalories()));
    }
}
