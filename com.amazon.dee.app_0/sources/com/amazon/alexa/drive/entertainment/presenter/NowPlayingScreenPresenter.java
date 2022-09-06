package com.amazon.alexa.drive.entertainment.presenter;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.TransportControlCommand;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class NowPlayingScreenPresenter implements NowPlayingScreenContract.Presenter {
    private static final String TAG = "NowPlayingScreenPresenter";
    private NowPlayingScreenContract.Interactor mInteractor;
    private NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener mOnUpdateNowPlayingPlaybackStateListener;
    private NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener mOnUpdateNowPlayingScreenListener;
    private NowPlayingScreenContract.View mView;

    public NowPlayingScreenPresenter(NowPlayingScreenContract.Interactor interactor) {
        Log.i(TAG, "NowPlayingScreenPresenter Constructor");
        this.mInteractor = interactor;
        initListeners(createOnUpdateNowPlayingScreenListener(), createOnUpdateNowPlayingPlaybackStateListener());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void cleanUp() {
        Log.i(TAG, "cleanUp");
        this.mInteractor.removeOnUpdateNowPlayingScreenListener(this.mOnUpdateNowPlayingScreenListener);
        this.mInteractor.removeOnUpdateNowPlayingPlaybackStateListener(this.mOnUpdateNowPlayingPlaybackStateListener);
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener createOnUpdateNowPlayingPlaybackStateListener() {
        Log.i(TAG, "createOnUpdateNowPlayingPlaybackStateListener");
        return new NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener() { // from class: com.amazon.alexa.drive.entertainment.presenter.-$$Lambda$NowPlayingScreenPresenter$xm6zETmww31PEVO6BiZVGu5NkMQ
            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener
            public final void updateNowPlayingPlaybackState(boolean z) {
                NowPlayingScreenPresenter.this.lambda$createOnUpdateNowPlayingPlaybackStateListener$1$NowPlayingScreenPresenter(z);
            }
        };
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener createOnUpdateNowPlayingScreenListener() {
        Log.i(TAG, "createOnUpdateNowPlayingScreenListener");
        return new NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener() { // from class: com.amazon.alexa.drive.entertainment.presenter.-$$Lambda$NowPlayingScreenPresenter$EdFaX_Uv7SIrVbRriA94JfqhPTU
            @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener
            public final void updateNowPlayingScreen(JSONObject jSONObject) {
                NowPlayingScreenPresenter.this.lambda$createOnUpdateNowPlayingScreenListener$0$NowPlayingScreenPresenter(jSONObject);
            }
        };
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public JSONObject getCurrentPlayerInfo() {
        return this.mInteractor.getCurrentPlayerInfo();
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener getOnUpdateNowPlayingPlaybackStateListener() {
        return this.mOnUpdateNowPlayingPlaybackStateListener;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener getOnUpdateNowPlayingScreenListener() {
        return this.mOnUpdateNowPlayingScreenListener;
    }

    NowPlayingScreenContract.View getView() {
        return this.mView;
    }

    @VisibleForTesting
    void initListeners(NowPlayingScreenContract.Interactor.OnUpdateNowPlayingScreenListener onUpdateNowPlayingScreenListener, NowPlayingScreenContract.Interactor.OnUpdateNowPlayingPlaybackStateListener onUpdateNowPlayingPlaybackStateListener) {
        Log.i(TAG, "initOnUpdateNowPlayingScreenListener");
        this.mOnUpdateNowPlayingScreenListener = onUpdateNowPlayingScreenListener;
        this.mOnUpdateNowPlayingPlaybackStateListener = onUpdateNowPlayingPlaybackStateListener;
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void initialize(NowPlayingScreenContract.View view) {
        String str = TAG;
        Log.i(str, "initialize" + view);
        this.mView = view;
        this.mInteractor.addOnUpdateNowPlayingScreenListener(this.mOnUpdateNowPlayingScreenListener);
        this.mInteractor.addOnUpdateNowPlayingPlaybackStateListener(this.mOnUpdateNowPlayingPlaybackStateListener);
    }

    public /* synthetic */ void lambda$createOnUpdateNowPlayingPlaybackStateListener$1$NowPlayingScreenPresenter(boolean z) {
        GeneratedOutlineSupport1.outline173("updateNowPlayingPlaybackState ", z, TAG);
        if (getView() != null) {
            getView().updateNowPlayingPlaybackState(z);
        }
    }

    public /* synthetic */ void lambda$createOnUpdateNowPlayingScreenListener$0$NowPlayingScreenPresenter(JSONObject jSONObject) {
        Log.i(TAG, "updateNowPlayingScreen ");
        if (getView() != null) {
            getView().updateNowPlayingScreen(jSONObject);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void onMediaError(MediaErrorPayload mediaErrorPayload) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onMediaError ");
        outline107.append(getView());
        Log.e(str, outline107.toString());
        if (getView() != null) {
            getView().onMediaError(mediaErrorPayload);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void requestPlayerInfo() {
        Log.i(TAG, "requestPlayerInfo");
        this.mInteractor.requestPlayerInfo();
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void sendPrimaryTransportControlCommand(TransportControlCommand transportControlCommand) {
        String str = TAG;
        Log.i(str, "sendPrimaryTransportControlCommand " + transportControlCommand);
        this.mInteractor.sendPrimaryTransportControlCommand(transportControlCommand);
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract.Presenter
    public void sendSecondaryTransportControlCommand(TransportControlCommand transportControlCommand, boolean z) {
        String str = TAG;
        Log.i(str, "sendPrimaryTransportControlCommand " + transportControlCommand + " enabled " + z);
        this.mInteractor.sendSecondaryTransportControlCommand(transportControlCommand, z);
    }
}
