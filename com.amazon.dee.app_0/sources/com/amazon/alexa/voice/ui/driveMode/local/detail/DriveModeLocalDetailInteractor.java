package com.amazon.alexa.voice.ui.driveMode.local.detail;

import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalLocationProvider;
import com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
/* loaded from: classes11.dex */
public final class DriveModeLocalDetailInteractor implements DriveModeLocalDetailContract.Interactor {
    private final LocalCardModel.BusinessModel business;
    private final LocalDelegate delegate;
    private final DriveModeLocalLocationProvider localLocationProvider;
    private final DriveModeLocalDetailContract.Mediator mediator;

    public DriveModeLocalDetailInteractor(LocalCardModel.BusinessModel businessModel, DriveModeLocalDetailContract.Mediator mediator, LocalDelegate localDelegate, DriveModeLocalLocationProvider driveModeLocalLocationProvider) {
        this.business = businessModel;
        this.mediator = mediator;
        this.delegate = localDelegate;
        this.localLocationProvider = driveModeLocalLocationProvider;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Interactor
    public void back() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Interactor
    public void callBusiness() {
        this.mediator.dialPhoneNumber(String.valueOf(this.business.getPhoneNumber()));
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Interactor
    public void close() {
        this.delegate.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Interactor
    public LocalCardModel.BusinessModel getBusiness() {
        return this.business;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Interactor
    public void showLocation() {
        this.localLocationProvider.showLocation(this.business.getGeoLocation(), this.business.getAddress());
    }
}
