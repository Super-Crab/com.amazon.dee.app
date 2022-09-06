package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class ScheduleDetails {
    @NonNull
    public final String duration;
    @NonNull
    public final String recurrence;
    @NonNull
    public final String start;

    public ScheduleDetails(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        this.start = str;
        this.duration = str2;
        this.recurrence = str3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScheduleDetails)) {
            return false;
        }
        ScheduleDetails scheduleDetails = (ScheduleDetails) obj;
        return Objects.equals(this.start, scheduleDetails.start) && Objects.equals(this.duration, scheduleDetails.duration) && Objects.equals(this.recurrence, scheduleDetails.recurrence);
    }

    public int hashCode() {
        String str = this.start;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.duration;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.recurrence;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }
}
