package io.ktor.util.date;

import com.amazon.clouddrive.cdasdk.cds.common.TimeGroupBy;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Date.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 /2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001/BO\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\u0011\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u0000H\u0096\u0002J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u000bHÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u000eHÆ\u0003Jc\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u000eHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010\u001e\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020\u0003HÖ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u00060"}, d2 = {"Lio/ktor/util/date/GMTDate;", "", BizMetricsConstants.DURATION_METADATA_NAME, "", "minutes", "hours", "dayOfWeek", "Lio/ktor/util/date/WeekDay;", "dayOfMonth", "dayOfYear", TimeGroupBy.MONTH, "Lio/ktor/util/date/Month;", TimeGroupBy.YEAR, "timestamp", "", "(IIILio/ktor/util/date/WeekDay;IILio/ktor/util/date/Month;IJ)V", "getDayOfMonth", "()I", "getDayOfWeek", "()Lio/ktor/util/date/WeekDay;", "getDayOfYear", "getHours", "getMinutes", "getMonth", "()Lio/ktor/util/date/Month;", "getSeconds", "getTimestamp", "()J", "getYear", "compareTo", "other", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "", "hashCode", "toString", "", "Companion", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class GMTDate implements Comparable<GMTDate> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final GMTDate START = DateJvmKt.GMTDate(0L);
    private final int dayOfMonth;
    @NotNull
    private final WeekDay dayOfWeek;
    private final int dayOfYear;
    private final int hours;
    private final int minutes;
    @NotNull
    private final Month month;
    private final int seconds;
    private final long timestamp;
    private final int year;

    /* compiled from: Date.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/util/date/GMTDate$Companion;", "", "()V", "START", "Lio/ktor/util/date/GMTDate;", "getSTART", "()Lio/ktor/util/date/GMTDate;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final GMTDate getSTART() {
            return GMTDate.START;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public GMTDate(int i, int i2, int i3, @NotNull WeekDay dayOfWeek, int i4, int i5, @NotNull Month month, int i6, long j) {
        Intrinsics.checkParameterIsNotNull(dayOfWeek, "dayOfWeek");
        Intrinsics.checkParameterIsNotNull(month, "month");
        this.seconds = i;
        this.minutes = i2;
        this.hours = i3;
        this.dayOfWeek = dayOfWeek;
        this.dayOfMonth = i4;
        this.dayOfYear = i5;
        this.month = month;
        this.year = i6;
        this.timestamp = j;
    }

    public final int component1() {
        return this.seconds;
    }

    public final int component2() {
        return this.minutes;
    }

    public final int component3() {
        return this.hours;
    }

    @NotNull
    public final WeekDay component4() {
        return this.dayOfWeek;
    }

    public final int component5() {
        return this.dayOfMonth;
    }

    public final int component6() {
        return this.dayOfYear;
    }

    @NotNull
    public final Month component7() {
        return this.month;
    }

    public final int component8() {
        return this.year;
    }

    public final long component9() {
        return this.timestamp;
    }

    @NotNull
    public final GMTDate copy(int i, int i2, int i3, @NotNull WeekDay dayOfWeek, int i4, int i5, @NotNull Month month, int i6, long j) {
        Intrinsics.checkParameterIsNotNull(dayOfWeek, "dayOfWeek");
        Intrinsics.checkParameterIsNotNull(month, "month");
        return new GMTDate(i, i2, i3, dayOfWeek, i4, i5, month, i6, j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof GMTDate) {
                GMTDate gMTDate = (GMTDate) obj;
                if (this.seconds == gMTDate.seconds) {
                    if (this.minutes == gMTDate.minutes) {
                        if ((this.hours == gMTDate.hours) && Intrinsics.areEqual(this.dayOfWeek, gMTDate.dayOfWeek)) {
                            if (this.dayOfMonth == gMTDate.dayOfMonth) {
                                if ((this.dayOfYear == gMTDate.dayOfYear) && Intrinsics.areEqual(this.month, gMTDate.month)) {
                                    if (this.year == gMTDate.year) {
                                        if (this.timestamp == gMTDate.timestamp) {
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final int getDayOfMonth() {
        return this.dayOfMonth;
    }

    @NotNull
    public final WeekDay getDayOfWeek() {
        return this.dayOfWeek;
    }

    public final int getDayOfYear() {
        return this.dayOfYear;
    }

    public final int getHours() {
        return this.hours;
    }

    public final int getMinutes() {
        return this.minutes;
    }

    @NotNull
    public final Month getMonth() {
        return this.month;
    }

    public final int getSeconds() {
        return this.seconds;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getYear() {
        return this.year;
    }

    public int hashCode() {
        int i = ((((this.seconds * 31) + this.minutes) * 31) + this.hours) * 31;
        WeekDay weekDay = this.dayOfWeek;
        int i2 = 0;
        int hashCode = (((((i + (weekDay != null ? weekDay.hashCode() : 0)) * 31) + this.dayOfMonth) * 31) + this.dayOfYear) * 31;
        Month month = this.month;
        if (month != null) {
            i2 = month.hashCode();
        }
        long j = this.timestamp;
        return ((((hashCode + i2) * 31) + this.year) * 31) + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GMTDate(seconds=");
        outline107.append(this.seconds);
        outline107.append(", minutes=");
        outline107.append(this.minutes);
        outline107.append(", hours=");
        outline107.append(this.hours);
        outline107.append(", dayOfWeek=");
        outline107.append(this.dayOfWeek);
        outline107.append(", dayOfMonth=");
        outline107.append(this.dayOfMonth);
        outline107.append(", dayOfYear=");
        outline107.append(this.dayOfYear);
        outline107.append(", month=");
        outline107.append(this.month);
        outline107.append(", year=");
        outline107.append(this.year);
        outline107.append(", timestamp=");
        return GeneratedOutlineSupport1.outline87(outline107, this.timestamp, ")");
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull GMTDate other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return (this.timestamp > other.timestamp ? 1 : (this.timestamp == other.timestamp ? 0 : -1));
    }
}
