package com.amazon.alexa.drive.entertainment.contract;

import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.TransportControlCommand;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public interface NowPlayingScreenContract {

    /* loaded from: classes7.dex */
    public interface Interactor {

        /* loaded from: classes7.dex */
        public interface OnNowPlayingScreenInfoAsyncTaskListener {
            void onNowPlayingScreenInfoFetchComplete(String str);

            void onNowPlayingScreenInfoFetchFailed(MediaErrorPayload mediaErrorPayload);
        }

        /* loaded from: classes7.dex */
        public interface OnUpdateNowPlayingPlaybackStateListener {
            void updateNowPlayingPlaybackState(boolean z);
        }

        /* loaded from: classes7.dex */
        public interface OnUpdateNowPlayingScreenListener {
            void updateNowPlayingScreen(JSONObject jSONObject);
        }

        /* loaded from: classes7.dex */
        public interface PlayerCommandAsyncTaskStatusListener {
            void onPlayerCommandAsyncTaskFailed(MediaErrorPayload mediaErrorPayload);
        }

        void addOnUpdateNowPlayingPlaybackStateListener(OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener);

        void addOnUpdateNowPlayingScreenListener(OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener);

        void destroy();

        JSONObject getCurrentPlayerInfo();

        void removeOnUpdateNowPlayingPlaybackStateListener(OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener);

        void removeOnUpdateNowPlayingScreenListener(OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener);

        void requestPlayerInfo();

        void sendPrimaryTransportControlCommand(TransportControlCommand transportControlCommand);

        void sendSecondaryTransportControlCommand(TransportControlCommand transportControlCommand, boolean z);

        void setCurrentPlayerInfo(JSONObject jSONObject);

        void updateAudioPlayerState(AlexaPlayerInfoState alexaPlayerInfoState);
    }

    /* loaded from: classes7.dex */
    public interface Presenter {
        void cleanUp();

        JSONObject getCurrentPlayerInfo();

        void initialize(View view);

        void onMediaError(MediaErrorPayload mediaErrorPayload);

        void requestPlayerInfo();

        void sendPrimaryTransportControlCommand(TransportControlCommand transportControlCommand);

        void sendSecondaryTransportControlCommand(TransportControlCommand transportControlCommand, boolean z);
    }

    /* loaded from: classes7.dex */
    public interface View {

        /* loaded from: classes7.dex */
        public interface MediaErrorDialogListener {
            void handleMediaErrorDialogClose();
        }

        void onMediaError(MediaErrorPayload mediaErrorPayload);

        void updateNowPlayingPlaybackState(boolean z);

        void updateNowPlayingScreen(JSONObject jSONObject);
    }
}
