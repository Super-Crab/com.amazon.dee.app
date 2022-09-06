package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes11.dex */
public final class LanguageCombinationPrimerParameters implements LanguageCombinationPrimerParametersModel {
    private final ArrayList<LanguageGroup> availableLanguages;
    private final LanguageGroup currentLanguage;
    private final ArrayList<LanguageGroup> recommendedLanguages;

    /* loaded from: classes11.dex */
    public static final class Builder {
        ArrayList<LanguageGroup> availableLanguages;
        LanguageGroup currentLanguage;
        ArrayList<LanguageGroup> recommendedLanguages;

        public Builder availableLanguages(@NonNull ArrayList<LanguageGroup> arrayList) {
            this.availableLanguages = arrayList;
            return this;
        }

        public LanguageCombinationPrimerParameters build() {
            if (this.currentLanguage != null) {
                if (this.availableLanguages != null) {
                    if (this.recommendedLanguages != null) {
                        return new LanguageCombinationPrimerParameters(this);
                    }
                    throw new IllegalArgumentException("recommendedLanguages == null");
                }
                throw new IllegalArgumentException("availableLanguages == null");
            }
            throw new IllegalArgumentException("currentLanguage == null");
        }

        public Builder currentLanguage(@NonNull LanguageGroup languageGroup) {
            this.currentLanguage = languageGroup;
            return this;
        }

        public Builder recommendedLanguages(@NonNull ArrayList<LanguageGroup> arrayList) {
            this.recommendedLanguages = arrayList;
            return this;
        }
    }

    LanguageCombinationPrimerParameters(Builder builder) {
        this.currentLanguage = builder.currentLanguage;
        this.availableLanguages = builder.availableLanguages;
        this.recommendedLanguages = builder.recommendedLanguages;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LanguageCombinationPrimerParameters.class != obj.getClass()) {
            return false;
        }
        LanguageCombinationPrimerParameters languageCombinationPrimerParameters = (LanguageCombinationPrimerParameters) obj;
        return this.currentLanguage.equals(languageCombinationPrimerParameters.currentLanguage) && this.availableLanguages.equals(languageCombinationPrimerParameters.availableLanguages) && this.recommendedLanguages.equals(languageCombinationPrimerParameters.recommendedLanguages);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParametersModel
    @NonNull
    public ArrayList<LanguageGroup> getAvailableLanguages() {
        return this.availableLanguages;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParametersModel
    @NonNull
    public LanguageGroup getCurrentLanguage() {
        return this.currentLanguage;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParametersModel
    @NonNull
    public ArrayList<LanguageGroup> getRecommendedLanguages() {
        return this.recommendedLanguages;
    }

    public int hashCode() {
        int hashCode = this.availableLanguages.hashCode();
        return this.recommendedLanguages.hashCode() + ((hashCode + ((this.currentLanguage.hashCode() + JfifUtil.MARKER_EOI) * 31)) * 31);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LanguageCombinationPrimerParameters{currentLanguage=");
        outline107.append(this.currentLanguage);
        outline107.append(", availableLanguages=");
        outline107.append(this.availableLanguages);
        outline107.append(", recommendedLanguages=");
        outline107.append(this.recommendedLanguages);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
