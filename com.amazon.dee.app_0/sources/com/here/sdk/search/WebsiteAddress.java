package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class WebsiteAddress {
    @NonNull
    public final String address;
    @NonNull
    public final List<PlaceCategory> categories;

    public WebsiteAddress(@NonNull String str, @NonNull List<PlaceCategory> list) {
        this.address = str;
        this.categories = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebsiteAddress)) {
            return false;
        }
        WebsiteAddress websiteAddress = (WebsiteAddress) obj;
        return Objects.equals(this.address, websiteAddress.address) && Objects.equals(this.categories, websiteAddress.categories);
    }

    public int hashCode() {
        String str = this.address;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        List<PlaceCategory> list = this.categories;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }
}
