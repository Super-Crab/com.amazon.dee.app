package com.amazon.alexa.voice.ui.onedesign.local;

import com.amazon.alexa.voice.ui.onedesign.local.LocalContract;
/* loaded from: classes11.dex */
public final class LocalInteractor implements LocalContract.Interactor {
    private final LocalContract.Mediator mediator;

    public LocalInteractor(LocalContract.Mediator mediator) {
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Interactor
    public void showChildIfNeeded() {
        this.mediator.showChildIfNeeded();
    }
}
