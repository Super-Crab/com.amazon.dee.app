package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import java.util.List;
/* loaded from: classes11.dex */
public interface LanguageCombinationPrimerContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        LanguageCombinationPrimerParameters getLanguageCombinationPrimerParameters();

        void updateLanguageCombinationAndExit(LanguageGroup languageGroup);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void updateLanguageCombinationAndExit(LanguageGroup languageGroup);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void close();

        void continueClicked(LanguageGroup languageGroup);

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setAvailableLanguages(List<LanguageCombinationListItem> list);

        void setAvailableLanguagesHeaderText(CharSequence charSequence);

        void setAvailableSectionVisible(boolean z);

        void setContinueButtonEnabled(boolean z);

        void setContinueButtonText(CharSequence charSequence);

        void setHeadingText(CharSequence charSequence);

        void setRecommendedLanguages(List<LanguageCombinationListItem> list);

        void setRecommendedLanguagesHeaderText(CharSequence charSequence);

        void setRecommendedSectionVisible(boolean z);

        void setSectionDescription(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}
