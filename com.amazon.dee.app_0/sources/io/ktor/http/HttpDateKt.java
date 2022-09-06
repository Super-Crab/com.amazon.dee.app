package io.ktor.http;

import com.amazonaws.util.DateUtils;
import io.ktor.util.KtorExperimentalAPI;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpDate.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0007\u001a\n\u0010\u000b\u001a\u00020\n*\u00020\f\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\rH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"GreenwichMeanTime", "Ljava/time/ZoneId;", "httpDateFormat", "Ljava/time/format/DateTimeFormatter;", "httpDateFormat$annotations", "()V", "getHttpDateFormat", "()Ljava/time/format/DateTimeFormatter;", "fromHttpDateString", "Ljava/time/ZonedDateTime;", "", "toHttpDateString", "Ljava/time/temporal/Temporal;", "", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpDateKt {
    private static final ZoneId GreenwichMeanTime;
    @NotNull
    private static final DateTimeFormatter httpDateFormat;

    static {
        ZoneId of = ZoneId.of("GMT");
        Intrinsics.checkExpressionValueIsNotNull(of, "ZoneId.of(\"GMT\")");
        GreenwichMeanTime = of;
        DateTimeFormatter withZone = DateTimeFormatter.ofPattern(DateUtils.RFC822_DATE_PATTERN).withLocale(Locale.US).withZone(GreenwichMeanTime);
        if (withZone == null) {
            Intrinsics.throwNpe();
        }
        httpDateFormat = withZone;
    }

    @KtorExperimentalAPI
    @NotNull
    public static final ZonedDateTime fromHttpDateString(@NotNull String receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        ZonedDateTime parse = ZonedDateTime.parse(receiver$0, httpDateFormat);
        Intrinsics.checkExpressionValueIsNotNull(parse, "ZonedDateTime.parse(this, httpDateFormat)");
        return parse;
    }

    @NotNull
    public static final DateTimeFormatter getHttpDateFormat() {
        return httpDateFormat;
    }

    @KtorExperimentalAPI
    public static /* synthetic */ void httpDateFormat$annotations() {
    }

    @KtorExperimentalAPI
    @NotNull
    public static final String toHttpDateString(long j) {
        Instant ofEpochMilli = Instant.ofEpochMilli(j);
        Intrinsics.checkExpressionValueIsNotNull(ofEpochMilli, "Instant.ofEpochMilli(this)");
        return toHttpDateString(ofEpochMilli);
    }

    @NotNull
    public static final String toHttpDateString(@NotNull Temporal receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String format = httpDateFormat.format(receiver$0);
        Intrinsics.checkExpressionValueIsNotNull(format, "httpDateFormat.format(this)");
        return format;
    }
}
