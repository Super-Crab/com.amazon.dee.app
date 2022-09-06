package com.amazon.alexa.handsfree.audio.features;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.api.LocaleApi;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
/* loaded from: classes8.dex */
public class TypingAvailabilityResolver {
    private static final Set<Locale> SUPPORTED_LOCALES = new HashSet(Arrays.asList(Locale.forLanguageTag("en-US")));
    private static final String TAG = "TypingAvailabilityResolver";
    private final ComponentEnablementResolver mComponentEnablementResolver;
    private final LocaleApi mLocaleApi;

    public TypingAvailabilityResolver(@NonNull Context context) {
        this(new LocaleApi(), new ComponentEnablementResolver(context));
    }

    private boolean isFeatureEnabledForI18N() {
        return this.mComponentEnablementResolver.isComponentEnabled(HandsFreeComponent.ALEXA_VOX_TTA);
    }

    private boolean isLocaleSupported(Locale locale) {
        if (locale == null) {
            Log.w(TAG, "Unable to get alexa locale.");
            return false;
        }
        return SUPPORTED_LOCALES.contains(locale);
    }

    public boolean isTypingEnabled() {
        Locale currentAlexaLocale = this.mLocaleApi.getCurrentAlexaLocale();
        boolean z = currentAlexaLocale != null && currentAlexaLocale.equals(this.mLocaleApi.getCurrentDeviceLocale()) && (isLocaleSupported(currentAlexaLocale) || isFeatureEnabledForI18N());
        String str = TAG;
        Log.i(str, "Typing feature is enabled : " + z);
        return z;
    }

    @VisibleForTesting
    TypingAvailabilityResolver(@NonNull LocaleApi localeApi, @NonNull ComponentEnablementResolver componentEnablementResolver) {
        this.mLocaleApi = localeApi;
        this.mComponentEnablementResolver = componentEnablementResolver;
    }
}
