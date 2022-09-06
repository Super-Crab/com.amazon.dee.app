package com.amazon.alexa.voice.locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public interface LocaleInteractor {
    void fetchSupportedLocales(SupportedLocalesCallback supportedLocalesCallback);

    void fetchSupportedLocalesCombinations(SupportedLocaleCombinationsCallback supportedLocaleCombinationsCallback);

    List<Locale> getCurrentLocales();

    Locale getPrimaryLocale();

    void initialize();

    boolean needsLanguagePickerEducation();

    void release();

    void setSystemLocalesUpdatedTo(@NonNull List<Locale> list, int i, @Nullable LocaleUpdateCallback localeUpdateCallback);
}
