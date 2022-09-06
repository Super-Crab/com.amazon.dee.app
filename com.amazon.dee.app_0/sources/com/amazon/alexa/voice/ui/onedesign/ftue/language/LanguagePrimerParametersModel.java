package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.ArrayList;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface LanguagePrimerParametersModel {
    @NonNull
    ArrayList<Language> getAvailableLanguages();

    @NonNull
    Language getCurrentLanguage();

    @NonNull
    ArrayList<Language> getRecommendedLanguages();
}
