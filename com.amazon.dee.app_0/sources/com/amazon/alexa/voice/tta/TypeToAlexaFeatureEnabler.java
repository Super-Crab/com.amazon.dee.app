package com.amazon.alexa.voice.tta;

import com.amazon.alexa.voice.features.FeatureEnabler;
import com.amazon.alexa.voice.features.VoiceFeature;
import com.amazon.alexa.voice.locale.LocaleInteractor;
import java.util.Locale;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class TypeToAlexaFeatureEnabler implements FeatureEnabler {
    private static final Locale EN_US = Locale.forLanguageTag("en-US");
    private boolean isWeblabEnabled = false;
    private final LocaleInteractor localeInteractor;

    @Inject
    public TypeToAlexaFeatureEnabler(LocaleInteractor localeInteractor) {
        this.localeInteractor = localeInteractor;
    }

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public void enableFeature(boolean z) {
        this.isWeblabEnabled = z;
    }

    @Override // com.amazon.alexa.voice.features.FeatureEnabler
    public VoiceFeature getFeature() {
        return VoiceFeature.TTA_I18N_DEV;
    }

    public boolean isTypeToAlexaAvailable() {
        return EN_US.equals(Locale.getDefault()) || this.isWeblabEnabled;
    }

    public boolean isTypeToAlexaEnabled() {
        Locale primaryLocale = this.localeInteractor.getPrimaryLocale();
        Locale locale = Locale.getDefault();
        return primaryLocale != null && locale != null && primaryLocale.toLanguageTag().equals(locale.toLanguageTag()) && (EN_US.equals(primaryLocale) || this.isWeblabEnabled);
    }
}
