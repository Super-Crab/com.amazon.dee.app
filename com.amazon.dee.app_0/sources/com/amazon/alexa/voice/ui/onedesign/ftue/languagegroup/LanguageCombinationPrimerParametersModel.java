package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.ArrayList;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface LanguageCombinationPrimerParametersModel {
    @NonNull
    ArrayList<LanguageGroup> getAvailableLanguages();

    @NonNull
    LanguageGroup getCurrentLanguage();

    @NonNull
    ArrayList<LanguageGroup> getRecommendedLanguages();
}
