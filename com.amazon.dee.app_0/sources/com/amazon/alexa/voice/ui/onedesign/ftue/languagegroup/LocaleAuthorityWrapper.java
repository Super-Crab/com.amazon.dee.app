package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.locale.AlexaLocaleAuthority;
import com.amazon.alexa.voice.ui.locale.LocaleUpdateCallback;
import com.amazon.regulator.Component;
import com.amazon.regulator.internal.Preconditions;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
class LocaleAuthorityWrapper {
    private static final String TAG = "LocaleAuthorityWrapper";
    private final AlexaLocaleAuthority alexaLocaleAuthority;

    @VisibleForTesting
    LocaleAuthorityWrapper(@Nullable AlexaLocaleAuthority alexaLocaleAuthority) {
        this.alexaLocaleAuthority = alexaLocaleAuthority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LocaleAuthorityWrapper from(Component component) {
        Preconditions.nonNull(component, "component must not be null");
        if (component.isRegistered(AlexaLocaleAuthority.class)) {
            return new LocaleAuthorityWrapper((AlexaLocaleAuthority) component.get(AlexaLocaleAuthority.class));
        }
        throw new IllegalStateException("Requires a localeAuthority object. Couldn't find any");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Locale getLocale() {
        AlexaLocaleAuthority alexaLocaleAuthority = this.alexaLocaleAuthority;
        if (alexaLocaleAuthority != null) {
            return alexaLocaleAuthority.getLocale();
        }
        return null;
    }

    List<Locale> getLocales() {
        AlexaLocaleAuthority alexaLocaleAuthority = this.alexaLocaleAuthority;
        if (alexaLocaleAuthority != null) {
            return alexaLocaleAuthority.getLocales();
        }
        return null;
    }

    void setLocale(Locale locale, LocaleUpdateCallback localeUpdateCallback) {
        AlexaLocaleAuthority alexaLocaleAuthority = this.alexaLocaleAuthority;
        if (alexaLocaleAuthority != null) {
            alexaLocaleAuthority.setLocale(locale);
            localeUpdateCallback.onSuccess();
            return;
        }
        Log.w(TAG, "All the locale authorities are null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLocales(List<Locale> list, LocaleUpdateCallback localeUpdateCallback) {
        AlexaLocaleAuthority alexaLocaleAuthority = this.alexaLocaleAuthority;
        if (alexaLocaleAuthority != null) {
            alexaLocaleAuthority.setLocales(list);
            localeUpdateCallback.onSuccess();
            return;
        }
        Log.w(TAG, "AlexaLocaleAuthority is null");
    }
}
