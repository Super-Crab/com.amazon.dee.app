package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import com.facebook.imageutils.JfifUtil;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public class LocaleGroup {
    private List<Locale> locales;

    public LocaleGroup(List<Locale> list) {
        this.locales = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && LocaleGroup.class == obj.getClass() && this.locales.equals(((LocaleGroup) obj).locales);
    }

    public List<Locale> getLocales() {
        return this.locales;
    }

    public Locale getPrimaryLocale() {
        return this.locales.get(0);
    }

    public int hashCode() {
        return this.locales.hashCode() + JfifUtil.MARKER_EOI;
    }
}
