package com.amazon.alexa.voice.ui.onedesign.ftue.language;

import com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract;
/* loaded from: classes11.dex */
public class LanguagePrimerInteractor implements LanguagePrimerContract.Interactor {
    private final LanguagePrimerParameters languagePrimerParameters;
    private final LanguagePrimerContract.Mediator mediator;

    public LanguagePrimerInteractor(LanguagePrimerContract.Mediator mediator, LanguagePrimerParameters languagePrimerParameters) {
        this.mediator = mediator;
        this.languagePrimerParameters = languagePrimerParameters;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Interactor
    public LanguagePrimerParameters getLanguagePrimerParameters() {
        return this.languagePrimerParameters;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.language.LanguagePrimerContract.Interactor
    public void updateLanguageAndExit(Language language) {
        this.mediator.updateLanguageAndExit(language);
    }
}
