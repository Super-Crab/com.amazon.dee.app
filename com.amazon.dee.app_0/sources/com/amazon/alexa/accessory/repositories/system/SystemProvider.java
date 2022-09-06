package com.amazon.alexa.accessory.repositories.system;

import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.protocol.System;
/* loaded from: classes6.dex */
public interface SystemProvider {
    void provideI18nConfig(DavsI18nConfig davsI18nConfig);

    void provideI18nConfigError(Throwable th);

    void provideLocales(System.Locales locales);

    void provideLocalesError(Throwable th);

    void provideUsers(System.Users users);

    void provideUsersError(Throwable th);
}
