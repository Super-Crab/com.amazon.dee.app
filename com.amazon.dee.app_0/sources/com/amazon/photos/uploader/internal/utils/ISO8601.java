package com.amazon.photos.uploader.internal.utils;

import com.amazon.dee.sdk.iotsoftap.Constants;
import com.sun.mail.imap.IMAPStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ISO8601.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006J\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/ISO8601;", "", "()V", "UTC_DATE_FORMAT", "Lcom/amazon/photos/uploader/internal/utils/ISO8601$ThreadLocalSimpleDateFormat;", "getDateFromString", "Ljava/util/Date;", IMAPStore.ID_DATE, "", "getUTCString", "getUnixTimestampFromString", "", "(Ljava/lang/String;)Ljava/lang/Long;", "ThreadLocalSimpleDateFormat", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ISO8601 {
    public static final ISO8601 INSTANCE = new ISO8601();
    private static final ThreadLocalSimpleDateFormat UTC_DATE_FORMAT = new ThreadLocalSimpleDateFormat();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ISO8601.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0014¨\u0006\u0005"}, d2 = {"Lcom/amazon/photos/uploader/internal/utils/ISO8601$ThreadLocalSimpleDateFormat;", "Ljava/lang/ThreadLocal;", "Ljava/text/SimpleDateFormat;", "()V", "initialValue", "AndroidPhotosUploader_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class ThreadLocalSimpleDateFormat extends ThreadLocal<SimpleDateFormat> {
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        @NotNull
        public SimpleDateFormat initialValue() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
            return simpleDateFormat;
        }
    }

    private ISO8601() {
    }

    @Nullable
    public final Date getDateFromString(@NotNull String date) throws ParseException {
        boolean isBlank;
        SimpleDateFormat simpleDateFormat;
        String replace$default;
        Intrinsics.checkParameterIsNotNull(date, "date");
        isBlank = StringsKt__StringsJVMKt.isBlank(date);
        if (!isBlank && (simpleDateFormat = UTC_DATE_FORMAT.get()) != null) {
            replace$default = StringsKt__StringsJVMKt.replace$default(date, "Z", "+0000", false, 4, (Object) null);
            return simpleDateFormat.parse(replace$default);
        }
        return null;
    }

    @Nullable
    public final String getUTCString(@NotNull Date date) {
        String format;
        String replace$default;
        Intrinsics.checkParameterIsNotNull(date, "date");
        SimpleDateFormat simpleDateFormat = UTC_DATE_FORMAT.get();
        if (simpleDateFormat == null || (format = simpleDateFormat.format(date)) == null) {
            return null;
        }
        replace$default = StringsKt__StringsJVMKt.replace$default(format, "+0000", "Z", false, 4, (Object) null);
        return replace$default;
    }

    @Nullable
    public final Long getUnixTimestampFromString(@NotNull String date) throws ParseException {
        boolean isBlank;
        Date dateFromString;
        Intrinsics.checkParameterIsNotNull(date, "date");
        isBlank = StringsKt__StringsJVMKt.isBlank(date);
        if (!isBlank && (dateFromString = getDateFromString(date)) != null) {
            return Long.valueOf(dateFromString.getTime());
        }
        return null;
    }
}
