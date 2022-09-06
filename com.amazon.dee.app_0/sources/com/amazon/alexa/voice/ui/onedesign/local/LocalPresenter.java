package com.amazon.alexa.voice.ui.onedesign.local;

import com.amazon.alexa.voice.ui.onedesign.local.LocalContract;
/* loaded from: classes11.dex */
public final class LocalPresenter implements LocalContract.Presenter {
    private final LocalContract.Interactor interactor;

    public LocalPresenter(LocalContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Presenter
    public void start() {
        this.interactor.showChildIfNeeded();
    }
}
