package com.amazon.alexa.drive.entertainment.contract;

import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import java.util.List;
/* loaded from: classes7.dex */
public interface EntertainmentLandingPageContract {

    /* loaded from: classes7.dex */
    public interface EntertainmentCardListChangeListener {
        void onItemRangeChanged(List list, int i, int i2);

        void onItemRangeInserted(List list, int i, int i2);

        void onItemRangeRemoved(List list, int i, int i2);
    }

    /* loaded from: classes7.dex */
    public interface EntertainmentInteractor {

        /* loaded from: classes7.dex */
        public interface OnEntertainmentCardsFetchCompleteListener {
            void onEntertainmentCardsFetchComplete(List<EntertainmentCardItem> list);

            void onEntertainmentCardsFetchFailed(MediaErrorPayload mediaErrorPayload);
        }

        /* loaded from: classes7.dex */
        public interface OnPlaylistInitTaskCompleteListener {
            void onPlaylistInitFailed(MediaErrorPayload mediaErrorPayload);
        }

        /* loaded from: classes7.dex */
        public interface OnUpdateNowPlayingCardListener {
            void updateNowPlayingCard(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState);
        }

        void addOnUpdateNowPlayingCardListener(OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener);

        void addOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener);

        void cleanUp();

        void destroy();

        boolean isMediaPlaying();

        boolean isNowPlayingItemActive();

        boolean isRecentlyPlayedDataFetchComplete();

        void loadEntertainmentListItems();

        void navigateToNowPlayingScreen();

        void removeOnUpdateNowPlayingCardListener(OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener);

        void removeOnUpdateNowPlayingScreenListener(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener);

        void requestEntertainmentCards();

        void requestEntertainmentCardsWhenReady();

        void requestNext();

        void requestNowPlayingCardItem();

        void requestPause();

        void requestPlay();

        void requestPlaylistPlayback(EntertainmentCardItem entertainmentCardItem);

        void requestPrevious();
    }

    /* loaded from: classes7.dex */
    public interface Presenter {
        void cleanUp();

        int getCurrentEntertainmentDataSize();

        void initialize(View view);

        boolean isEntertainmentDataFetched();

        boolean isMediaPlaying();

        boolean isNowPlayingItemActive();

        boolean isRecentlyPlayedDataFetchComplete();

        void onEntertainmentCardClick(EntertainmentCardItem entertainmentCardItem);

        void onEntertainmentDataListFetchComplete(int i);

        void onMediaError(MediaErrorPayload mediaErrorPayload);

        void onNowPlayingCardButtonClick(boolean z);

        void onNowPlayingCardClick();

        void requestEntertainmentCards();
    }

    /* loaded from: classes7.dex */
    public interface View {
        void onEntertainmentDataListFetchComplete(int i);

        void onMediaError(MediaErrorPayload mediaErrorPayload);

        void updateNowPlayingCard(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState);
    }
}
