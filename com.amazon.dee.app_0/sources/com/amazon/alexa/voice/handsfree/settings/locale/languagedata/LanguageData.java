package com.amazon.alexa.voice.handsfree.settings.locale.languagedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes11.dex */
public class LanguageData {
    private final String mCountryCode;
    private final Locale mDeviceLanguage;
    private final Locale mPreferredLanguage;
    private final Set<Locale> mSupportedLanguages;

    /* loaded from: classes11.dex */
    public enum LanguageCode {
        en,
        es,
        pt,
        fr,
        de,
        it,
        ja,
        hi
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LanguageData(@Nullable String str, @NonNull Locale locale, @NonNull Locale locale2, @NonNull Set<Locale> set) {
        this.mDeviceLanguage = locale;
        this.mCountryCode = str;
        this.mPreferredLanguage = locale2;
        this.mSupportedLanguages = set;
    }

    @Nullable
    public String getCountryCode() {
        return this.mCountryCode;
    }

    @NonNull
    public Locale getDeviceLanguage() {
        return this.mDeviceLanguage;
    }

    public Locale getPreferredLanguage() {
        return this.mPreferredLanguage;
    }

    public Set<Locale> getSupportedLanguages() {
        return this.mSupportedLanguages;
    }
}
