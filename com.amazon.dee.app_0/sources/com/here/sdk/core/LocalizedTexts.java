package com.here.sdk.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class LocalizedTexts {
    @NonNull
    public List<LocalizedText> items;

    public LocalizedTexts(@NonNull List<LocalizedText> list) {
        this.items = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof LocalizedTexts) {
            return Objects.equals(this.items, ((LocalizedTexts) obj).items);
        }
        return false;
    }

    @Nullable
    public native String getDefaultValue();

    @Nullable
    public native String getPreferredValueForLocales(@NonNull List<Locale> list);

    public int hashCode() {
        List<LocalizedText> list = this.items;
        return (list != null ? list.hashCode() : 0) + JfifUtil.MARKER_EOI;
    }
}
