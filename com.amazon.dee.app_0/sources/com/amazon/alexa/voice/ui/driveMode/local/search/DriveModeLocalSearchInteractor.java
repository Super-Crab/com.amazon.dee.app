package com.amazon.alexa.voice.ui.driveMode.local.search;

import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalLocationProvider;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
/* loaded from: classes11.dex */
public final class DriveModeLocalSearchInteractor implements DriveModeLocalSearchContract.Interactor {
    private final LocalCard card;
    private final LocalDelegate delegate;
    private final DriveModeLocalLocationProvider localLocationProvider;

    public DriveModeLocalSearchInteractor(LocalCard localCard, LocalDelegate localDelegate, DriveModeLocalLocationProvider driveModeLocalLocationProvider) {
        this.card = localCard;
        this.delegate = localDelegate;
        this.localLocationProvider = driveModeLocalLocationProvider;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Interactor
    public void close() {
        this.delegate.close();
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Interactor
    public LocalCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchContract.Interactor
    public void showLocation(LocalCardModel.BusinessModel businessModel) {
        this.localLocationProvider.showLocation(businessModel.getGeoLocation(), businessModel.getAddress());
    }
}
