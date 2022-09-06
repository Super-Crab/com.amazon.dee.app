package io.ktor.util.date;

import io.ktor.util.KtorExperimentalAPI;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Date.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0002\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0002\u001a\f\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0007¨\u0006\u0006"}, d2 = {"minus", "Lio/ktor/util/date/GMTDate;", "milliseconds", "", "plus", "truncateToSeconds", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DateKt {
    @NotNull
    public static final GMTDate minus(@NotNull GMTDate receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return DateJvmKt.GMTDate(Long.valueOf(receiver$0.getTimestamp() - j));
    }

    @NotNull
    public static final GMTDate plus(@NotNull GMTDate receiver$0, long j) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return DateJvmKt.GMTDate(Long.valueOf(receiver$0.getTimestamp() + j));
    }

    @KtorExperimentalAPI
    @NotNull
    public static final GMTDate truncateToSeconds(@NotNull GMTDate receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return DateJvmKt.GMTDate(receiver$0.getSeconds(), receiver$0.getMinutes(), receiver$0.getHours(), receiver$0.getDayOfMonth(), receiver$0.getMonth(), receiver$0.getYear());
    }
}
