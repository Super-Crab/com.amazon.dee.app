package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
/* loaded from: classes11.dex */
public class HandsfreePrimerInteractor implements HandsfreePrimerContract.Interactor {
    private final HandsfreePrimerContract.Mediator mediator;

    public HandsfreePrimerInteractor(@NonNull HandsfreePrimerContract.Mediator mediator) {
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Interactor
    public void deferPrimer() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Interactor
    public void learnMoreClicked(String str) {
        this.mediator.openLearnMore(str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Interactor
    public void requestPermissions() {
        this.mediator.requestPermissions();
    }
}
