package com.amazon.alexa.drive.entertainment.presenter;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EntertainmentLandingPagePresenter implements EntertainmentLandingPageContract.Presenter {
    private static final String TAG = "EntertainmentLandingPagePresenter";
    private int mCurrentEntertainmentDataSize;
    private EntertainmentLandingPageContract.EntertainmentInteractor mEntertainmentInteractor;
    private boolean mIsEntertainmentDataFetched;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener mOnUpdateNowPlayingCardListener;
    private EntertainmentLandingPageContract.View mView;

    public EntertainmentLandingPagePresenter(EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor) {
        Log.i(TAG, "EntertainmentLandingPagePresenter Constructor");
        this.mEntertainmentInteractor = entertainmentInteractor;
        initOnUpdateNowPlayingCardListener(new EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener() { // from class: com.amazon.alexa.drive.entertainment.presenter.-$$Lambda$EntertainmentLandingPagePresenter$L5-TmmSqVIxhHCtqmsMl0ibJybo
            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener
            public final void updateNowPlayingCard(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
                EntertainmentLandingPagePresenter.this.lambda$new$0$EntertainmentLandingPagePresenter(entertainmentCardItem, alexaPlayerInfoState);
            }
        });
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void cleanUp() {
        Log.i(TAG, "cleanUp");
        this.mEntertainmentInteractor.cleanUp();
        this.mEntertainmentInteractor.removeOnUpdateNowPlayingCardListener(getOnUpdateNowPlayingCardListener());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public int getCurrentEntertainmentDataSize() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getCurrentEntertainmentDataSize ");
        outline107.append(this.mCurrentEntertainmentDataSize);
        Log.i(str, outline107.toString());
        return this.mCurrentEntertainmentDataSize;
    }

    EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener getOnUpdateNowPlayingCardListener() {
        return this.mOnUpdateNowPlayingCardListener;
    }

    EntertainmentLandingPageContract.View getView() {
        return this.mView;
    }

    public void initOnUpdateNowPlayingCardListener(EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener) {
        Log.i(TAG, "initOnUpdateNowPlayingCardListener");
        this.mOnUpdateNowPlayingCardListener = onUpdateNowPlayingCardListener;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void initialize(EntertainmentLandingPageContract.View view) {
        Log.i(TAG, "initialize");
        this.mView = view;
        this.mEntertainmentInteractor.addOnUpdateNowPlayingCardListener(getOnUpdateNowPlayingCardListener());
        this.mEntertainmentInteractor.requestNowPlayingCardItem();
        this.mEntertainmentInteractor.requestEntertainmentCardsWhenReady();
        if (!isEntertainmentDataFetched()) {
            this.mEntertainmentInteractor.loadEntertainmentListItems();
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public boolean isEntertainmentDataFetched() {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isEntertainmentDataFetched ");
        outline107.append(this.mIsEntertainmentDataFetched);
        Log.i(str, outline107.toString());
        return this.mIsEntertainmentDataFetched;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    @VisibleForTesting
    public boolean isMediaPlaying() {
        return this.mEntertainmentInteractor.isMediaPlaying();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    @VisibleForTesting
    public boolean isNowPlayingItemActive() {
        return this.mEntertainmentInteractor.isNowPlayingItemActive();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public boolean isRecentlyPlayedDataFetchComplete() {
        return this.mEntertainmentInteractor.isRecentlyPlayedDataFetchComplete();
    }

    public /* synthetic */ void lambda$new$0$EntertainmentLandingPagePresenter(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
        Log.i(TAG, "updateNowPlayingCard");
        if (getView() != null) {
            getView().updateNowPlayingCard(entertainmentCardItem, alexaPlayerInfoState);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void onEntertainmentCardClick(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "onEntertainmentCardClick ");
        this.mEntertainmentInteractor.requestPlaylistPlayback(entertainmentCardItem);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void onEntertainmentDataListFetchComplete(int i) {
        String str = TAG;
        Log.i(str, "onEntertainmentDataListFetchComplete " + i);
        this.mIsEntertainmentDataFetched = true;
        this.mCurrentEntertainmentDataSize = i;
        if (getView() != null) {
            getView().onEntertainmentDataListFetchComplete(i);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void onMediaError(MediaErrorPayload mediaErrorPayload) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMediaError ");
        outline107.append(getView());
        Log.e(str, outline107.toString());
        if (getView() != null) {
            getView().onMediaError(mediaErrorPayload);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void onNowPlayingCardButtonClick(boolean z) {
        GeneratedOutlineSupport1.outline173("onNowPlayingCardButtonClick isPlaying ", z, TAG);
        if (z) {
            this.mEntertainmentInteractor.requestPlay();
        } else {
            this.mEntertainmentInteractor.requestPause();
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void onNowPlayingCardClick() {
        Log.i(TAG, "onNowPlayingCardClick");
        this.mEntertainmentInteractor.navigateToNowPlayingScreen();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.Presenter
    public void requestEntertainmentCards() {
        Log.i(TAG, "requestEntertainmentCards");
        this.mEntertainmentInteractor.requestEntertainmentCards();
    }
}
