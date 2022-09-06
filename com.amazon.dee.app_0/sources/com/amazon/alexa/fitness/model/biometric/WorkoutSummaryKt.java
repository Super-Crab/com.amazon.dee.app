package com.amazon.alexa.fitness.model.biometric;

import com.amazon.alexa.fitness.util.GsonUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WorkoutSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toJsonFormat", "", "Lcom/amazon/alexa/fitness/model/biometric/WorkoutSummary;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutSummaryKt {
    @NotNull
    public static final String toJsonFormat(@NotNull WorkoutSummary toJsonFormat) {
        Intrinsics.checkParameterIsNotNull(toJsonFormat, "$this$toJsonFormat");
        return GsonUtilsKt.toJson(toJsonFormat);
    }
}
