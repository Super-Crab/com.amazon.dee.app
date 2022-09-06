package com.here.sdk.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.imageutils.JfifUtil;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class LocalizedText {
    @Nullable
    public Locale locale;
    @NonNull
    public String text;

    public LocalizedText(@NonNull String str, @Nullable Locale locale) {
        this.text = str;
        this.locale = locale;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LocalizedText)) {
            return false;
        }
        LocalizedText localizedText = (LocalizedText) obj;
        return Objects.equals(this.text, localizedText.text) && Objects.equals(this.locale, localizedText.locale);
    }

    public int hashCode() {
        String str = this.text;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI) * 31;
        Locale locale = this.locale;
        if (locale != null) {
            i = locale.hashCode();
        }
        return hashCode + i;
    }
}
