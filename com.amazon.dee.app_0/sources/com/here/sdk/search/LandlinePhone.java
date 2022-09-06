package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class LandlinePhone {
    @NonNull
    public final List<PlaceCategory> categories;
    @NonNull
    public final String phoneNumber;

    public LandlinePhone(@NonNull String str, @NonNull List<PlaceCategory> list) {
        this.phoneNumber = str;
        this.categories = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LandlinePhone)) {
            return false;
        }
        LandlinePhone landlinePhone = (LandlinePhone) obj;
        return Objects.equals(this.phoneNumber, landlinePhone.phoneNumber) && Objects.equals(this.categories, landlinePhone.categories);
    }

    public int hashCode() {
        String str = this.phoneNumber;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<PlaceCategory> list = this.categories;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }
}
