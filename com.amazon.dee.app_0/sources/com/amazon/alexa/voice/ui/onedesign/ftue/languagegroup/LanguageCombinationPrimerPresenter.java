package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract;
import com.amazon.alexa.voice.ui.onedesign.ftue.metrics.VoxUiMetricEventName;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class LanguageCombinationPrimerPresenter implements LanguageCombinationPrimerContract.Presenter {
    private final LanguageCombinationPrimerContract.Interactor interactor;
    private final MetricsBridge metricsBridge;
    private final Resources resources;
    private final LanguageCombinationPrimerContract.View view;

    public LanguageCombinationPrimerPresenter(@NonNull LanguageCombinationPrimerContract.View view, @NonNull LanguageCombinationPrimerContract.Interactor interactor, @NonNull Resources resources, @Nullable MetricsBridge metricsBridge) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.metricsBridge = metricsBridge;
    }

    private List<LanguageCombinationListItem> getLanguageCombinationListItems(List<LanguageGroup> list, LanguageGroup languageGroup) {
        ArrayList arrayList = new ArrayList();
        for (LanguageGroup languageGroup2 : list) {
            arrayList.add(new LanguageCombinationListItem(languageGroup2, languageGroup2.equals(languageGroup)));
        }
        return arrayList;
    }

    private void reportEvent(String str) {
        MetricsBridge metricsBridge = this.metricsBridge;
        if (metricsBridge != null) {
            metricsBridge.reportEvent(str);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Presenter
    public void close() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Presenter
    public void continueClicked(LanguageGroup languageGroup) {
        this.interactor.updateLanguageCombinationAndExit(languageGroup);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Presenter
    public void start() {
        this.view.setTitle(this.resources.getString(R.string.voice_ui_od_language_ftue_page_title));
        this.view.setHeadingText(this.resources.getString(R.string.voice_ui_od_language_ftue_heading));
        this.view.setContinueButtonText(this.resources.getString(R.string.voice_ui_od_language_ftue_button_continue));
        LanguageCombinationPrimerParameters languageCombinationPrimerParameters = this.interactor.getLanguageCombinationPrimerParameters();
        ArrayList arrayList = new ArrayList(languageCombinationPrimerParameters.getAvailableLanguages());
        arrayList.removeAll(languageCombinationPrimerParameters.getRecommendedLanguages());
        if (languageCombinationPrimerParameters.getAvailableLanguages().size() > 0) {
            this.view.setAvailableLanguagesHeaderText(this.resources.getString(R.string.voice_ui_od_language_ftue_available_header));
            this.view.setSectionDescription(this.resources.getString(R.string.voice_ui_od_language_ftue_note_not_fully_supported));
            this.view.setAvailableLanguages(getLanguageCombinationListItems(arrayList, languageCombinationPrimerParameters.getCurrentLanguage()));
            this.view.setAvailableSectionVisible(true);
        } else {
            this.view.setAvailableSectionVisible(false);
        }
        if (languageCombinationPrimerParameters.getRecommendedLanguages().size() > 0) {
            this.view.setRecommendedLanguagesHeaderText(this.resources.getString(R.string.voice_ui_od_language_ftue_recommended_header));
            this.view.setRecommendedLanguages(getLanguageCombinationListItems(languageCombinationPrimerParameters.getRecommendedLanguages(), languageCombinationPrimerParameters.getCurrentLanguage()));
            this.view.setRecommendedSectionVisible(true);
        } else {
            this.view.setRecommendedSectionVisible(false);
        }
        reportEvent(VoxUiMetricEventName.FTUE_LANGUAGE_PICKER_SHOWN);
    }
}
