package com.amazon.alexa.voice.ui.locale;

import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public interface AlexaLocaleAuthority extends GetLocaleAuthority {
    void setLocale(Locale locale);

    void setLocale(Locale locale, LocaleUpdateCallback localeUpdateCallback);

    void setLocales(List<Locale> list);

    void setLocales(List<Locale> list, LocaleUpdateCallback localeUpdateCallback);
}
