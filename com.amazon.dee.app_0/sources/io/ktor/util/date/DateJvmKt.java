package io.ktor.util.date;

import com.amazon.clouddrive.cdasdk.cds.common.TimeGroupBy;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DateJvm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006\u001a\u0017\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000f\u001a\u001b\u0010\u0010\u001a\u00020\u0004*\u00020\u00112\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010\u0012\u001a\n\u0010\u0013\u001a\u00020\u0014*\u00020\u0004\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"GMT_TIMEZONE", "Ljava/util/TimeZone;", "kotlin.jvm.PlatformType", "GMTDate", "Lio/ktor/util/date/GMTDate;", BizMetricsConstants.DURATION_METADATA_NAME, "", "minutes", "hours", "dayOfMonth", TimeGroupBy.MONTH, "Lio/ktor/util/date/Month;", TimeGroupBy.YEAR, "timestamp", "", "(Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "toDate", "Ljava/util/Calendar;", "(Ljava/util/Calendar;Ljava/lang/Long;)Lio/ktor/util/date/GMTDate;", "toJvmDate", "Ljava/util/Date;", "ktor-utils"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class DateJvmKt {
    private static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");

    @NotNull
    public static final GMTDate GMTDate(@Nullable Long l) {
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        if (calendar == null) {
            Intrinsics.throwNpe();
        }
        return toDate(calendar, l);
    }

    @NotNull
    public static /* synthetic */ GMTDate GMTDate$default(Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            l = null;
        }
        return GMTDate(l);
    }

    private static final GMTDate toDate(@NotNull Calendar calendar, Long l) {
        if (l != null) {
            calendar.setTimeInMillis(l.longValue());
        }
        return new GMTDate(calendar.get(13), calendar.get(12), calendar.get(11), WeekDay.Companion.from(((calendar.get(7) + 7) - 2) % 7), calendar.get(5), calendar.get(6), Month.Companion.from(calendar.get(2)), calendar.get(1), calendar.getTimeInMillis());
    }

    @NotNull
    public static final Date toJvmDate(@NotNull GMTDate receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        if (calendar == null) {
            Intrinsics.throwNpe();
        }
        Date time = calendar.getTime();
        if (time == null) {
            Intrinsics.throwNpe();
        }
        return time;
    }

    @NotNull
    public static final GMTDate GMTDate(int i, int i2, int i3, int i4, @NotNull Month month, int i5) {
        Intrinsics.checkParameterIsNotNull(month, "month");
        Calendar calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
        if (calendar == null) {
            Intrinsics.throwNpe();
        }
        calendar.set(1, i5);
        calendar.set(2, month.ordinal());
        calendar.set(5, i4);
        calendar.set(11, i3);
        calendar.set(12, i2);
        calendar.set(13, i);
        calendar.set(14, 0);
        return toDate(calendar, null);
    }
}
