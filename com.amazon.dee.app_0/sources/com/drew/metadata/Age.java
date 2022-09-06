package com.drew.metadata;

import com.amazon.clouddrive.cdasdk.cds.common.TimeGroupBy;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class Age {
    private final int _days;
    private final int _hours;
    private final int _minutes;
    private final int _months;
    private final int _seconds;
    private final int _years;

    public Age(int i, int i2, int i3, int i4, int i5, int i6) {
        this._years = i;
        this._months = i2;
        this._days = i3;
        this._hours = i4;
        this._minutes = i5;
        this._seconds = i6;
    }

    private static void appendAgePart(StringBuilder sb, int i, String str) {
        if (i == 0) {
            return;
        }
        if (sb.length() != 0) {
            sb.append(Chars.SPACE);
        }
        sb.append(i);
        sb.append(Chars.SPACE);
        sb.append(str);
        if (i == 1) {
            return;
        }
        sb.append('s');
    }

    @Nullable
    public static Age fromPanasonicString(@NotNull String str) {
        if (str.length() == 19 && !str.startsWith("9999:99:99")) {
            try {
                return new Age(Integer.parseInt(str.substring(0, 4)), Integer.parseInt(str.substring(5, 7)), Integer.parseInt(str.substring(8, 10)), Integer.parseInt(str.substring(11, 13)), Integer.parseInt(str.substring(14, 16)), Integer.parseInt(str.substring(17, 19)));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Age.class != obj.getClass()) {
            return false;
        }
        Age age = (Age) obj;
        return this._days == age._days && this._hours == age._hours && this._minutes == age._minutes && this._months == age._months && this._seconds == age._seconds && this._years == age._years;
    }

    public int getDays() {
        return this._days;
    }

    public int getHours() {
        return this._hours;
    }

    public int getMinutes() {
        return this._minutes;
    }

    public int getMonths() {
        return this._months;
    }

    public int getSeconds() {
        return this._seconds;
    }

    public int getYears() {
        return this._years;
    }

    public int hashCode() {
        return (((((((((this._years * 31) + this._months) * 31) + this._days) * 31) + this._hours) * 31) + this._minutes) * 31) + this._seconds;
    }

    public String toFriendlyString() {
        StringBuilder sb = new StringBuilder();
        appendAgePart(sb, this._years, TimeGroupBy.YEAR);
        appendAgePart(sb, this._months, TimeGroupBy.MONTH);
        appendAgePart(sb, this._days, TimeGroupBy.DAY);
        appendAgePart(sb, this._hours, "hour");
        appendAgePart(sb, this._minutes, "minute");
        appendAgePart(sb, this._seconds, "second");
        return sb.toString();
    }

    public String toString() {
        return String.format("%04d:%02d:%02d %02d:%02d:%02d", Integer.valueOf(this._years), Integer.valueOf(this._months), Integer.valueOf(this._days), Integer.valueOf(this._hours), Integer.valueOf(this._minutes), Integer.valueOf(this._seconds));
    }
}
