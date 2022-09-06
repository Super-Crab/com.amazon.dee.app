package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerParameters;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageGroup;
import java.util.List;
/* loaded from: classes11.dex */
public interface LanguagePrimerContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        LanguagePrimerParameters getLanguagePrimerParameters();

        void updateLanguageAndExit(Language language);
    }

    /* loaded from: classes11.dex */
    public interface LocaleInteractor {
        void close();

        LanguageCombinationPrimerParameters getLanguagePrimerParameters();

        void updateLanguageCombinationAndExit(LanguageGroup languageGroup);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void updateLanguageAndExit(Language language);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void close();

        void continueClicked(Language language);

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setAvailableLanguages(List<LanguageListItem> list);

        void setAvailableLanguagesHeaderText(CharSequence charSequence);

        void setAvailableSectionVisible(boolean z);

        void setContinueButtonEnabled(boolean z);

        void setContinueButtonText(CharSequence charSequence);

        void setHeadingText(CharSequence charSequence);

        void setRecommendedLanguages(List<LanguageListItem> list);

        void setRecommendedLanguagesHeaderText(CharSequence charSequence);

        void setRecommendedSectionVisible(boolean z);

        void setSectionDescription(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
