package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class LanguagePrimerPresenter implements LanguagePrimerContract.Presenter {
    private final LanguagePrimerContract.Interactor interactor;
    private final MetricsBridge metricsBridge;
    private final Resources resources;
    private final LanguagePrimerContract.View view;

    public LanguagePrimerPresenter(@NonNull LanguagePrimerContract.View view, @NonNull LanguagePrimerContract.Interactor interactor, @NonNull Resources resources, @Nullable MetricsBridge metricsBridge) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.metricsBridge = metricsBridge;
    }

    private List<LanguageListItem> getLanguageListItems(List<Language> list, Language language) {
        ArrayList arrayList = new ArrayList();
        for (Language language2 : list) {
            arrayList.add(new LanguageListItem(language2, language2.equals(language)));
        }
        return arrayList;
    }

    private void reportEvent(String str) {
        MetricsBridge metricsBridge = this.metricsBridge;
        if (metricsBridge != null) {
            metricsBridge.reportEvent(str);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Presenter
    public void close() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Presenter
    public void continueClicked(Language language) {
        this.interactor.updateLanguageAndExit(language);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Presenter
    public void start() {
        this.view.setTitle(this.resources.getString(R.string.voice_ui_od_language_ftue_page_title));
        this.view.setHeadingText(this.resources.getString(R.string.voice_ui_od_language_ftue_heading));
        this.view.setContinueButtonText(this.resources.getString(R.string.voice_ui_od_language_ftue_button_continue));
        LanguagePrimerParameters languagePrimerParameters = this.interactor.getLanguagePrimerParameters();
        ArrayList arrayList = new ArrayList(languagePrimerParameters.getAvailableLanguages());
        arrayList.removeAll(languagePrimerParameters.getRecommendedLanguages());
        if (languagePrimerParameters.getAvailableLanguages().size() > 0) {
            this.view.setAvailableLanguagesHeaderText(this.resources.getString(R.string.voice_ui_od_language_ftue_available_header));
            this.view.setSectionDescription(this.resources.getString(R.string.voice_ui_od_language_ftue_note_not_fully_supported));
            this.view.setAvailableLanguages(getLanguageListItems(arrayList, languagePrimerParameters.getCurrentLanguage()));
            this.view.setAvailableSectionVisible(true);
        } else {
            this.view.setAvailableSectionVisible(false);
        }
        if (languagePrimerParameters.getRecommendedLanguages().size() > 0) {
            this.view.setRecommendedLanguagesHeaderText(this.resources.getString(R.string.voice_ui_od_language_ftue_recommended_header));
            this.view.setRecommendedLanguages(getLanguageListItems(languagePrimerParameters.getRecommendedLanguages(), languagePrimerParameters.getCurrentLanguage()));
            this.view.setRecommendedSectionVisible(true);
        } else {
            this.view.setRecommendedSectionVisible(false);
        }
        reportEvent(VoxUiMetricEventName.FTUE_LANGUAGE_PICKER_SHOWN);
    }
}
