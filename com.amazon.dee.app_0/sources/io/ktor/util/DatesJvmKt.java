package io.ktor.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DatesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0007\u001a\f\u0010\t\u001a\u00020\n*\u00020\bH\u0007\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000b"}, d2 = {"GreenwichMeanTime", "Ljava/time/ZoneId;", "GreenwichMeanTime$annotations", "()V", "getGreenwichMeanTime", "()Ljava/time/ZoneId;", "toLocalDateTime", "Ljava/time/LocalDateTime;", "Ljava/util/Date;", "toZonedDateTime", "Ljava/time/ZonedDateTime;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DatesJvmKt {
    @NotNull
    private static final ZoneId GreenwichMeanTime;

    static {
        ZoneId of = ZoneId.of("GMT");
        Intrinsics.checkExpressionValueIsNotNull(of, "ZoneId.of(\"GMT\")");
        GreenwichMeanTime = of;
    }

    @Deprecated(message = "Shouldn't be used outside of ktor")
    public static /* synthetic */ void GreenwichMeanTime$annotations() {
    }

    @NotNull
    public static final ZoneId getGreenwichMeanTime() {
        return GreenwichMeanTime;
    }

    @Deprecated(message = "Shouldn't be used outside of ktor")
    @NotNull
    public static final LocalDateTime toLocalDateTime(@NotNull Date receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        LocalDateTime ofInstant = LocalDateTime.ofInstant(receiver$0.toInstant(), ZoneId.systemDefault());
        Intrinsics.checkExpressionValueIsNotNull(ofInstant, "LocalDateTime.ofInstant(…, ZoneId.systemDefault())");
        return ofInstant;
    }

    @Deprecated(message = "Shouldn't be used outside of ktor")
    @NotNull
    public static final ZonedDateTime toZonedDateTime(@NotNull Date receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ZonedDateTime ofInstant = ZonedDateTime.ofInstant(receiver$0.toInstant(), GreenwichMeanTime);
        Intrinsics.checkExpressionValueIsNotNull(ofInstant, "ZonedDateTime.ofInstant(…ant(), GreenwichMeanTime)");
        return ofInstant;
    }
}
