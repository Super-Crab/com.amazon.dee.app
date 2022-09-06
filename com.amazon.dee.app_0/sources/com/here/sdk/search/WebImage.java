package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Date;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class WebImage {
    @NonNull
    public Date date;
    @NonNull
    public WebSource source;

    public WebImage(@NonNull Date date, @NonNull WebSource webSource) {
        this.date = date;
        this.source = webSource;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebImage)) {
            return false;
        }
        WebImage webImage = (WebImage) obj;
        return Objects.equals(this.date, webImage.date) && Objects.equals(this.source, webImage.source);
    }

    public int hashCode() {
        Date date = this.date;
        int i = 0;
        int hashCode = ((date != null ? date.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        WebSource webSource = this.source;
        if (webSource != null) {
            i = webSource.hashCode();
        }
        return hashCode + i;
    }
}
