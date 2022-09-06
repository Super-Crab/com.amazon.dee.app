package com.amazon.alexa.voice.ui.onedesign.local.detail;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract;
/* loaded from: classes11.dex */
public final class LocalDetailInteractor implements LocalDetailContract.Interactor {
    private final LocalCardModel.BusinessModel business;
    private final LocalDelegate delegate;
    private final LocalDetailContract.Mediator mediator;

    public LocalDetailInteractor(LocalCardModel.BusinessModel businessModel, LocalDetailContract.Mediator mediator, LocalDelegate localDelegate) {
        this.business = businessModel;
        this.mediator = mediator;
        this.delegate = localDelegate;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public void back() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public void callBusiness() {
        this.mediator.dialPhoneNumber(String.valueOf(this.business.getPhoneNumber()));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public void close() {
        this.delegate.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public LocalCardModel.BusinessModel getBusiness() {
        return this.business;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public void openLink() {
        this.mediator.openLinkUrl(this.business.goToActionUrl());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Interactor
    public void showLocation() {
        CharSequence geoLocation = this.business.getGeoLocation();
        if (geoLocation != null && geoLocation.length() != 0) {
            try {
                this.mediator.mapGeoLocation(this.business.getGeoLocation().toString());
                return;
            } catch (IllegalArgumentException unused) {
                this.mediator.mapAddress(this.business.getAddress().toString());
                return;
            }
        }
        this.mediator.mapAddress(this.business.getAddress().toString());
    }
}
