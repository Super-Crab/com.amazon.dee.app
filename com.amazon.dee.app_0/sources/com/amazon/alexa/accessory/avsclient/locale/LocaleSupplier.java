package com.amazon.alexa.accessory.avsclient.locale;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public interface LocaleSupplier {

    /* loaded from: classes.dex */
    public interface Listener {
        void onLocale(@Nullable AlexaLocale alexaLocale);
    }

    void deRegisterLocaleListener(Listener listener);

    void registerLocaleListener(Listener listener);
}
