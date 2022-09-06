package io.ktor.http;

import com.amazonaws.util.DateUtils;
import com.sun.mail.imap.IMAPStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpMessagePropertiesJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\f\u0010\t\u001a\u0004\u0018\u00010\u0007*\u00020\n\u001a\f\u0010\t\u001a\u0004\u0018\u00010\u0007*\u00020\u000b\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\n\u001a\f\u0010\u000e\u001a\u0004\u0018\u00010\u0007*\u00020\u000b\"\u0014\u0010\u0000\u001a\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u000f"}, d2 = {"HTTP_DATE_FORMAT", "Ljava/text/SimpleDateFormat;", "getHTTP_DATE_FORMAT", "()Ljava/text/SimpleDateFormat;", "formatHttpDate", "", IMAPStore.ID_DATE, "Ljava/util/Date;", "parseHttpDate", "expires", "Lio/ktor/http/HttpMessage;", "Lio/ktor/http/HttpMessageBuilder;", "ifModifiedSince", "", "lastModified", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpMessagePropertiesJvmKt {
    @Nullable
    public static final Date expires(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
        if (str != null) {
            return parseHttpDate(str);
        }
        return null;
    }

    private static final String formatHttpDate(Date date) {
        String format = getHTTP_DATE_FORMAT().format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "HTTP_DATE_FORMAT.format(date)");
        return format;
    }

    private static final SimpleDateFormat getHTTP_DATE_FORMAT() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }

    public static final void ifModifiedSince(@NotNull HttpMessageBuilder receiver$0, @NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(date, "date");
        receiver$0.getHeaders().set(HttpHeaders.INSTANCE.getIfModifiedSince(), formatHttpDate(date));
    }

    @Nullable
    public static final Date lastModified(@NotNull HttpMessageBuilder receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getLastModified());
        if (str != null) {
            return parseHttpDate(str);
        }
        return null;
    }

    private static final Date parseHttpDate(String str) {
        Date parse = getHTTP_DATE_FORMAT().parse(str);
        Intrinsics.checkExpressionValueIsNotNull(parse, "HTTP_DATE_FORMAT.parse(date)");
        return parse;
    }

    @Nullable
    public static final Date expires(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
        if (str != null) {
            return parseHttpDate(str);
        }
        return null;
    }

    @Nullable
    public static final Date lastModified(@NotNull HttpMessage receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        String str = receiver$0.getHeaders().get(HttpHeaders.INSTANCE.getLastModified());
        if (str != null) {
            return parseHttpDate(str);
        }
        return null;
    }
}
