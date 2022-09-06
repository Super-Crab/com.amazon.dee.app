package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaLocalesListener;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocaleApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\rH&J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H&J\u001a\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&J\u0016\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u0018H&J \u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00182\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/api/compat/LocaleApi;", "", "deregisterListener", "", "compatAlexaSupportedLocalesListener", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListener;", "compatAlexaSupportedLocalesListenerv2", "Lcom/amazon/alexa/api/compat/AlexaSupportedLocalesListenerv2;", "deregisterLocalesListener", "alexaLocalesListener", "Lcom/amazon/alexa/api/AlexaLocalesListener;", "deregisterSupportedLocalesListener", "alexaSupportedLocalesListener", "Lcom/amazon/alexa/api/AlexaSupportedLocalesListener;", "registerListener", "registerLocalesListener", "registerSupportedLocalesListener", "setLocale", "locale", "Ljava/util/Locale;", "alexaApiCallbacks", "Lcom/amazon/alexa/api/AlexaApiCallbacks;", "setLocales", SystemModelI18nConfigTransformer.KEY_LOCALES, "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface LocaleApi {
    void deregisterListener(@NotNull AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void deregisterListener(@NotNull AlexaSupportedLocalesListenerv2 alexaSupportedLocalesListenerv2);

    void deregisterLocalesListener(@NotNull AlexaLocalesListener alexaLocalesListener);

    void deregisterSupportedLocalesListener(@Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void registerListener(@NotNull AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void registerListener(@NotNull AlexaSupportedLocalesListenerv2 alexaSupportedLocalesListenerv2);

    void registerLocalesListener(@NotNull AlexaLocalesListener alexaLocalesListener);

    void registerSupportedLocalesListener(@Nullable com.amazon.alexa.api.AlexaSupportedLocalesListener alexaSupportedLocalesListener);

    void setLocale(@NotNull Locale locale);

    void setLocale(@NotNull Locale locale, @Nullable AlexaApiCallbacks alexaApiCallbacks);

    void setLocales(@NotNull List<Locale> list);

    void setLocales(@NotNull List<Locale> list, @Nullable AlexaApiCallbacks alexaApiCallbacks);
}
