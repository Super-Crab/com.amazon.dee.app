package com.amazon.alexa.drive.comms.interactor;

import android.util.Log;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
import dagger.Lazy;
/* loaded from: classes7.dex */
public class CommsLandingPageInteractor implements CommsLandingPageContract.Interactor {
    private static final String TAG = "CommsLandingPageInteractor";
    private Lazy<CommsLandingPageContract.Presenter> mPresenter;

    public CommsLandingPageInteractor(Lazy<CommsLandingPageContract.Presenter> lazy) {
        Log.i(TAG, "CommsLandingPageInteractor Constructor");
        this.mPresenter = lazy;
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Interactor
    public void onAnnouncementCardClick() {
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Interactor
    public void onCallCardClick() {
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Interactor
    public void onDropInCardClick() {
    }
}
