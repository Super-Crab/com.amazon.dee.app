package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class OpeningHours {
    @NonNull
    public final List<PlaceCategory> categories;
    public final boolean isOpen;
    @NonNull
    public final List<ScheduleDetails> scheduleDetailsList;
    @NonNull
    public final List<String> text;

    public OpeningHours(@NonNull List<String> list, boolean z, @NonNull List<ScheduleDetails> list2, @NonNull List<PlaceCategory> list3) {
        this.text = list;
        this.isOpen = z;
        this.scheduleDetailsList = list2;
        this.categories = list3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof OpeningHours)) {
            return false;
        }
        OpeningHours openingHours = (OpeningHours) obj;
        return Objects.equals(this.text, openingHours.text) && this.isOpen == openingHours.isOpen && Objects.equals(this.scheduleDetailsList, openingHours.scheduleDetailsList) && Objects.equals(this.categories, openingHours.categories);
    }

    public int hashCode() {
        List<String> list = this.text;
        int i = 0;
        int hashCode = ((((list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31) + (this.isOpen ? 79 : 97)) * 31;
        List<ScheduleDetails> list2 = this.scheduleDetailsList;
        int hashCode2 = (hashCode + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<PlaceCategory> list3 = this.categories;
        if (list3 != null) {
            i = list3.hashCode();
        }
        return hashCode2 + i;
    }
}
