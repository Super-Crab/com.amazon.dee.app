package com.here.sdk.search;

import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.core.LanguageCode;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class SearchOptions {
    @Nullable
    public LanguageCode languageCode;
    @Nullable
    public Integer maxItems;

    public SearchOptions() {
        this.languageCode = null;
        this.maxItems = null;
    }

    public SearchOptions(@Nullable LanguageCode languageCode, @Nullable Integer num) {
        this.languageCode = languageCode;
        this.maxItems = num;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SearchOptions)) {
            return false;
        }
        SearchOptions searchOptions = (SearchOptions) obj;
        return Objects.equals(this.languageCode, searchOptions.languageCode) && Objects.equals(this.maxItems, searchOptions.maxItems);
    }

    public int hashCode() {
        LanguageCode languageCode = this.languageCode;
        int i = 0;
        int hashCode = ((languageCode != null ? languageCode.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Integer num = this.maxItems;
        if (num != null) {
            i = num.hashCode();
        }
        return hashCode + i;
    }
}
