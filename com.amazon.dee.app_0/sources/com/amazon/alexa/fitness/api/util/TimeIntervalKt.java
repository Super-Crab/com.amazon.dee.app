package com.amazon.alexa.fitness.api.util;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TimeInterval.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001a#\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\u0006\u001a\u0019\u0010\u0007\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\b\u001a\u0019\u0010\t\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\b\u001a\u0019\u0010\n\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\b\u001a\u0019\u0010\u000b\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\b\u001a\u0019\u0010\f\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002¢\u0006\u0002\u0010\b¨\u0006\r"}, d2 = {"asTimeInterval", "Lcom/amazon/alexa/fitness/api/util/TimeInterval;", ExifInterface.GPS_DIRECTION_TRUE, "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(Ljava/lang/Number;Ljava/util/concurrent/TimeUnit;)Lcom/amazon/alexa/fitness/api/util/TimeInterval;", "days", "(Ljava/lang/Number;)Lcom/amazon/alexa/fitness/api/util/TimeInterval;", "hours", "milliseconds", "minutes", BizMetricsConstants.DURATION_METADATA_NAME, "AlexaMobileAndroidFitnessAPI_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class TimeIntervalKt {
    private static final <T extends Number> TimeInterval asTimeInterval(@NotNull T t, TimeUnit timeUnit) {
        return new TimeInterval(TimeUnit.MILLISECONDS.convert(t.longValue(), timeUnit));
    }

    @NotNull
    public static final <T extends Number> TimeInterval days(@NotNull T days) {
        Intrinsics.checkParameterIsNotNull(days, "$this$days");
        return asTimeInterval(days, TimeUnit.DAYS);
    }

    @NotNull
    public static final <T extends Number> TimeInterval hours(@NotNull T hours) {
        Intrinsics.checkParameterIsNotNull(hours, "$this$hours");
        return asTimeInterval(hours, TimeUnit.HOURS);
    }

    @NotNull
    public static final <T extends Number> TimeInterval milliseconds(@NotNull T milliseconds) {
        Intrinsics.checkParameterIsNotNull(milliseconds, "$this$milliseconds");
        return new TimeInterval(milliseconds.longValue());
    }

    @NotNull
    public static final <T extends Number> TimeInterval minutes(@NotNull T minutes) {
        Intrinsics.checkParameterIsNotNull(minutes, "$this$minutes");
        return asTimeInterval(minutes, TimeUnit.MINUTES);
    }

    @NotNull
    public static final <T extends Number> TimeInterval seconds(@NotNull T seconds) {
        Intrinsics.checkParameterIsNotNull(seconds, "$this$seconds");
        return asTimeInterval(seconds, TimeUnit.SECONDS);
    }
}
