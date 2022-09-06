package com.amazon.alexa.drive.comms.presenter;

import android.util.Log;
import com.amazon.alexa.drive.comms.contract.CommsLandingPageContract;
/* loaded from: classes7.dex */
public class CommsLandingPagePresenter implements CommsLandingPageContract.Presenter {
    private static final String TAG = "CommsLandingPagePresenter";
    private CommsLandingPageContract.Interactor mInteractor;
    private CommsLandingPageContract.View mView;

    public CommsLandingPagePresenter(CommsLandingPageContract.Interactor interactor) {
        Log.i(TAG, "CommsLandingPagePresenter Constructor");
        this.mInteractor = interactor;
    }

    CommsLandingPageContract.View getView() {
        return this.mView;
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Presenter
    public void initialize(CommsLandingPageContract.View view) {
        Log.i(TAG, "initialize");
        this.mView = view;
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Presenter
    public void onAnnouncementCardClick() {
        Log.i(TAG, "onAnnouncementCardClick");
        this.mInteractor.onAnnouncementCardClick();
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Presenter
    public void onCallCardClick() {
        Log.i(TAG, "onCallCardClick");
        this.mInteractor.onCallCardClick();
    }

    @Override // com.amazon.alexa.drive.comms.contract.CommsLandingPageContract.Presenter
    public void onDropInCardClick() {
        Log.i(TAG, "onDropInCardClick");
        this.mInteractor.onDropInCardClick();
    }
}
