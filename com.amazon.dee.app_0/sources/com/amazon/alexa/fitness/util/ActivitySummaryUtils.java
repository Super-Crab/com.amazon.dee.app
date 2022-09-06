package com.amazon.alexa.fitness.util;

import com.amazon.alexa.fitness.model.biometric.ActivitySummary;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ActivitySummaryUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/fitness/util/ActivitySummaryUtils;", "", "()V", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ActivitySummaryUtils {
    public static final Companion Companion = new Companion(null);

    /* compiled from: ActivitySummaryUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/util/ActivitySummaryUtils$Companion;", "", "()V", "aggregateActivitySummaries", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "activitySummaries", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ActivitySummary aggregateActivitySummaries(@NotNull Collection<ActivitySummary> activitySummaries) {
            double d;
            double d2;
            ActivitySummary activitySummary;
            Intrinsics.checkParameterIsNotNull(activitySummaries, "activitySummaries");
            double d3 = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            double d4 = 0.0d;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            for (ActivitySummary activitySummary2 : activitySummaries) {
                i2 += activitySummary2.getTotalSteps();
                d4 += activitySummary2.getDistanceInFeet();
                i += activitySummary2.getTimeInSeconds();
                i3 += activitySummary2.getCalories();
            }
            if (i > 0) {
                double d5 = i;
                double convertFeetPerSecondToMilesPerHour = ConversionUtils.Companion.convertFeetPerSecondToMilesPerHour(d4 / d5);
                for (Iterator it2 = activitySummaries.iterator(); it2.hasNext(); it2 = it2) {
                    d3 += (((ActivitySummary) it2.next()).getCadence() * activitySummary.getTimeInSeconds()) / d5;
                }
                d2 = convertFeetPerSecondToMilesPerHour;
                d = d3;
            } else {
                d = 0.0d;
                d2 = 0.0d;
            }
            return new ActivitySummary(i, i2, d4, d, d2, i3);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
