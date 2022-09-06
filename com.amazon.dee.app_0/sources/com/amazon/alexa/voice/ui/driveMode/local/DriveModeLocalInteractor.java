package com.amazon.alexa.voice.ui.driveMode.local;

import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract;
/* loaded from: classes11.dex */
public final class DriveModeLocalInteractor implements DriveModeLocalContract.Interactor {
    private final DriveModeLocalContract.Mediator mediator;

    public DriveModeLocalInteractor(DriveModeLocalContract.Mediator mediator) {
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Interactor
    public void showChildIfNeeded() {
        this.mediator.showChildIfNeeded();
    }
}
