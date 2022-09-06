package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class WebSource {
    @NonNull
    public String href;
    @NonNull
    public String supplierId;

    public WebSource(@NonNull String str, @NonNull String str2) {
        this.href = str;
        this.supplierId = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebSource)) {
            return false;
        }
        WebSource webSource = (WebSource) obj;
        return Objects.equals(this.href, webSource.href) && Objects.equals(this.supplierId, webSource.supplierId);
    }

    public int hashCode() {
        String str = this.href;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.supplierId;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }
}
