package com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup;

import com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract;
/* loaded from: classes11.dex */
public class LanguageCombinationPrimerInteractor implements LanguageCombinationPrimerContract.Interactor {
    private final LanguageCombinationPrimerParameters languageCombinationPrimerParameters;
    private final LanguageCombinationPrimerContract.Mediator mediator;

    public LanguageCombinationPrimerInteractor(LanguageCombinationPrimerContract.Mediator mediator, LanguageCombinationPrimerParameters languageCombinationPrimerParameters) {
        this.mediator = mediator;
        this.languageCombinationPrimerParameters = languageCombinationPrimerParameters;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Interactor
    public LanguageCombinationPrimerParameters getLanguageCombinationPrimerParameters() {
        return this.languageCombinationPrimerParameters;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.languagegroup.LanguageCombinationPrimerContract.Interactor
    public void updateLanguageCombinationAndExit(LanguageGroup languageGroup) {
        this.mediator.updateLanguageCombinationAndExit(languageGroup);
    }
}
