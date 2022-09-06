package com.amazon.photos.discovery.internal.util;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.sun.mail.imap.IMAPStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DateUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/DateUtils;", "", "()V", "UTC_DATE_FORMAT", "Lcom/amazon/photos/discovery/internal/util/DateUtils$ThreadLocalSimpleDateFormat;", "getDateFromString", "Ljava/util/Date;", IMAPStore.ID_DATE, "", "getDayString", "utcTime", "", "getUTCString", "ThreadLocalSimpleDateFormat", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DateUtils {
    public static final DateUtils INSTANCE = new DateUtils();
    private static final ThreadLocalSimpleDateFormat UTC_DATE_FORMAT = new ThreadLocalSimpleDateFormat();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DateUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0014¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/DateUtils$ThreadLocalSimpleDateFormat;", "Ljava/lang/ThreadLocal;", "Ljava/text/SimpleDateFormat;", "()V", "initialValue", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class ThreadLocalSimpleDateFormat extends ThreadLocal<SimpleDateFormat> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        @Nullable
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
            return simpleDateFormat;
        }
    }

    private DateUtils() {
    }

    @NotNull
    public final Date getDateFromString(@NotNull String date) throws ParseException {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(date, "date");
        SimpleDateFormat simpleDateFormat = UTC_DATE_FORMAT.get();
        if (simpleDateFormat == null) {
            Intrinsics.throwNpe();
        }
        replace$default = StringsKt__StringsJVMKt.replace$default(date, "Z", "+0000", false, 4, (Object) null);
        Date parse = simpleDateFormat.parse(replace$default);
        Intrinsics.checkExpressionValueIsNotNull(parse, "UTC_DATE_FORMAT.get()!!.…te.replace(\"Z\", \"+0000\"))");
        return parse;
    }

    @NotNull
    public final String getDayString(long j) {
        return getDayString(new Date(j));
    }

    @NotNull
    public final String getUTCString(long j) {
        return getUTCString(new Date(j));
    }

    @NotNull
    public final String getDayString(@NotNull Date date) {
        Intrinsics.checkParameterIsNotNull(date, "date");
        Calendar calendar = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
        calendar.setTime(date);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.US;
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.US");
        Object[] objArr = {Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5))};
        String format = String.format(locale, "%d-%02d-%02d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    @NotNull
    public final String getUTCString(@NotNull Date date) {
        String replace$default;
        Intrinsics.checkParameterIsNotNull(date, "date");
        SimpleDateFormat simpleDateFormat = UTC_DATE_FORMAT.get();
        if (simpleDateFormat == null) {
            Intrinsics.throwNpe();
        }
        String format = simpleDateFormat.format(date);
        Intrinsics.checkExpressionValueIsNotNull(format, "UTC_DATE_FORMAT.get()!!.format(date)");
        replace$default = StringsKt__StringsJVMKt.replace$default(format, "+0000", "Z", false, 4, (Object) null);
        return replace$default;
    }
}
