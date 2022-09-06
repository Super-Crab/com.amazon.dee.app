package com.amazon.alexa.voiceui.cards;

import com.amazon.alexa.voice.ui.locale.GetLocaleAuthority;
import java.util.List;
import java.util.Locale;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class DefaultLocaleAuthority implements GetLocaleAuthority {
    private Locale locale;
    private List<Locale> locales;

    @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
    public Locale getLocale() {
        return this.locale;
    }

    @Override // com.amazon.alexa.voice.ui.locale.GetLocaleAuthority
    public List<Locale> getLocales() {
        return this.locales;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setLocales(List<Locale> list) {
        this.locales = list;
    }
}
