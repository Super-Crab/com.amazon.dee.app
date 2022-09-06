package com.amazon.alexa.voice.ui.driveMode.local;

import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract;
/* loaded from: classes11.dex */
public final class DriveModeLocalPresenter implements DriveModeLocalContract.Presenter {
    private final DriveModeLocalContract.Interactor interactor;

    public DriveModeLocalPresenter(DriveModeLocalContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Presenter
    public void start() {
        this.interactor.showChildIfNeeded();
    }
}
