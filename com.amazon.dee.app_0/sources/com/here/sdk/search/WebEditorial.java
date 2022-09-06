package com.here.sdk.search;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class WebEditorial {
    @NonNull
    public String description;
    @NonNull
    public String language;
    @NonNull
    public WebSource source;

    public WebEditorial(@NonNull String str, @NonNull String str2, @NonNull WebSource webSource) {
        this.description = str;
        this.language = str2;
        this.source = webSource;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WebEditorial)) {
            return false;
        }
        WebEditorial webEditorial = (WebEditorial) obj;
        return Objects.equals(this.description, webEditorial.description) && Objects.equals(this.language, webEditorial.language) && Objects.equals(this.source, webEditorial.source);
    }

    public int hashCode() {
        String str = this.description;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        String str2 = this.language;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        WebSource webSource = this.source;
        if (webSource != null) {
            i = webSource.hashCode();
        }
        return hashCode2 + i;
    }
}
