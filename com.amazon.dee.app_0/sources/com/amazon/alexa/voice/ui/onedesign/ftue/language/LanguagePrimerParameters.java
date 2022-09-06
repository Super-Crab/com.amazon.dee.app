package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class LanguagePrimerParameters implements LanguagePrimerParametersModel {
    private final ArrayList<Language> availableLanguages;
    private final Language currentLanguage;
    private final ArrayList<Language> recommendedLanguages;

    /* loaded from: classes11.dex */
    public static final class Builder {
        ArrayList<Language> availableLanguages;
        Language currentLanguage;
        ArrayList<Language> recommendedLanguages;

        public Builder availableLanguages(@NonNull ArrayList<Language> arrayList) {
            this.availableLanguages = arrayList;
            return this;
        }

        public LanguagePrimerParameters build() {
            if (this.currentLanguage != null) {
                if (this.availableLanguages != null) {
                    if (this.recommendedLanguages != null) {
                        return new LanguagePrimerParameters(this);
                    }
                    throw new IllegalArgumentException("recommendedLanguages == null");
                }
                throw new IllegalArgumentException("availableLanguages == null");
            }
            throw new IllegalArgumentException("currentLanguage == null");
        }

        public Builder currentLanguage(@NonNull Language language) {
            this.currentLanguage = language;
            return this;
        }

        public Builder recommendedLanguages(@NonNull ArrayList<Language> arrayList) {
            this.recommendedLanguages = arrayList;
            return this;
        }
    }

    LanguagePrimerParameters(Builder builder) {
        this.currentLanguage = builder.currentLanguage;
        this.availableLanguages = builder.availableLanguages;
        this.recommendedLanguages = builder.recommendedLanguages;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LanguagePrimerParameters.class != obj.getClass()) {
            return false;
        }
        LanguagePrimerParameters languagePrimerParameters = (LanguagePrimerParameters) obj;
        return this.currentLanguage.equals(languagePrimerParameters.currentLanguage) && this.availableLanguages.equals(languagePrimerParameters.availableLanguages) && this.recommendedLanguages.equals(languagePrimerParameters.recommendedLanguages);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParametersModel
    @NonNull
    public ArrayList<Language> getAvailableLanguages() {
        return this.availableLanguages;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParametersModel
    @NonNull
    public Language getCurrentLanguage() {
        return this.currentLanguage;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerParametersModel
    @NonNull
    public ArrayList<Language> getRecommendedLanguages() {
        return this.recommendedLanguages;
    }

    public int hashCode() {
        int hashCode = this.availableLanguages.hashCode();
        return this.recommendedLanguages.hashCode() + ((hashCode + ((this.currentLanguage.hashCode() + JfifUtil.MARKER_EOI) * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LanguagePrimerParameters{currentLanguage=");
        outline107.append(this.currentLanguage);
        outline107.append(", availableLanguages=");
        outline107.append(this.availableLanguages);
        outline107.append(", recommendedLanguages=");
        outline107.append(this.recommendedLanguages);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
