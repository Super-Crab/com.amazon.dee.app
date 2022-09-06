package com.amazon.alexa.drive.entertainment.interactor;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
/* loaded from: classes7.dex */
public class EntertainmentAsyncTaskFactory {
    private static final String TAG = "EntertainmentAsyncTaskFactory";
    private Context mContext;
    private final EntertainmentMetrics mEntertainmentMetrics;

    public EntertainmentAsyncTaskFactory(Context context, EntertainmentMetrics entertainmentMetrics) {
        this.mContext = context;
        this.mEntertainmentMetrics = entertainmentMetrics;
    }

    public FetchEntertainmentCardDataAsyncTask createFetchEntertainmentCardDataAsyncTask(EntertainmentLandingPageContract.EntertainmentInteractor.OnEntertainmentCardsFetchCompleteListener onEntertainmentCardsFetchCompleteListener) {
        Log.i(TAG, "createFetchEntertainmentCardDataAsyncTask");
        return new FetchEntertainmentCardDataAsyncTask(onEntertainmentCardsFetchCompleteListener, this, this.mContext, this.mEntertainmentMetrics);
    }

    public NowPlayingScreenInfoAsyncTask createNowPlayingScreenInfoAsyncTask(NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener onNowPlayingScreenInfoAsyncTaskListener) {
        Log.i(TAG, "createNowPlayingScreenInfoAsyncTask");
        return new NowPlayingScreenInfoAsyncTask(onNowPlayingScreenInfoAsyncTaskListener, this.mContext, this.mEntertainmentMetrics);
    }

    public PlayerCommandAsyncTask createPlayerCommandAsyncTask(NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener playerCommandAsyncTaskStatusListener) {
        Log.i(TAG, "createPlayerCommandAsyncTask");
        return new PlayerCommandAsyncTask(playerCommandAsyncTaskStatusListener, this.mContext, this.mEntertainmentMetrics);
    }

    public PlaylistInitAsyncTask createPlaylistInitAsyncTask(EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener onPlaylistInitTaskCompleteListener) {
        Log.i(TAG, "createPlaylistInitAsyncTask");
        return new PlaylistInitAsyncTask(onPlaylistInitTaskCompleteListener, this.mContext, this.mEntertainmentMetrics);
    }
}
