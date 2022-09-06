package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.List;
/* loaded from: classes6.dex */
public interface LocaleApi {
    void deregisterListener(@NonNull AlexaLocalesListener alexaLocalesListener);

    void deregisterListener(@NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void registerListener(@NonNull AlexaLocalesListener alexaLocalesListener);

    void registerListener(@NonNull AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void setLocale(@NonNull java.util.Locale locale);

    void setLocale(@NonNull java.util.Locale locale, @Nullable AlexaApiCallbacks alexaApiCallbacks);

    void setLocales(@NonNull List<java.util.Locale> list);

    void setLocales(@NonNull List<java.util.Locale> list, @Nullable AlexaApiCallbacks alexaApiCallbacks);
}
