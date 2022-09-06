package com.amazon.alexa.drive.entertainment.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import okhttp3.Response;
/* loaded from: classes7.dex */
public class PlayerCommandAsyncTask extends AsyncTask<String, Integer, Boolean> {
    private static final int PAYLOAD_SIZE = 2;
    private static final String TAG = PlayerCommandAsyncTask.class.getSimpleName();
    private Context mContext;
    private final EntertainmentMetrics mEntertainmentMetrics;
    private NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener mPlayerCommandAsyncTaskStatusListener;

    public PlayerCommandAsyncTask(NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener playerCommandAsyncTaskStatusListener, Context context, EntertainmentMetrics entertainmentMetrics) {
        Log.i(TAG, "PlayerCommandAsyncTask Constructor");
        this.mPlayerCommandAsyncTaskStatusListener = playerCommandAsyncTaskStatusListener;
        this.mContext = context;
        this.mEntertainmentMetrics = entertainmentMetrics;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.PlayerCommandAsyncTaskStatusListener getPlayerCommandAsyncTaskStatusListener() {
        return this.mPlayerCommandAsyncTaskStatusListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Boolean doInBackground(String... strArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            if (strArr.length >= 2) {
                Log.i(TAG, "doInBackground ");
                coralService.request(strArr[0]).post(strArr[1]).as(Response.class).execute();
            }
            return true;
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "doInBackground() | Exception: " + e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Boolean bool) {
        String str = TAG;
        Log.i(str, "onPostExecute " + bool);
        if (!bool.booleanValue()) {
            this.mEntertainmentMetrics.logPlayerCommandApi("Failure");
            getPlayerCommandAsyncTaskStatusListener().onPlayerCommandAsyncTaskFailed(new MediaErrorPayload(this.mContext.getString(R.string.media_error_player_control_title), this.mContext.getString(R.string.media_error_play_subtitle)));
            return;
        }
        this.mEntertainmentMetrics.logPlayerCommandApi("Success");
    }
}
