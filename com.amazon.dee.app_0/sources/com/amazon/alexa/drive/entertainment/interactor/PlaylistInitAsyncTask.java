package com.amazon.alexa.drive.entertainment.interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.model.PlayPayload;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import okhttp3.Response;
/* loaded from: classes7.dex */
public class PlaylistInitAsyncTask extends AsyncTask<Object, Integer, Boolean> {
    private static final String TAG = PlaylistInitAsyncTask.class.getSimpleName();
    private Context mContext;
    private final EntertainmentMetrics mEntertainmentMetrics;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener mOnPlaylistInitTaskCompleteListener;

    public PlaylistInitAsyncTask(EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener onPlaylistInitTaskCompleteListener, Context context, EntertainmentMetrics entertainmentMetrics) {
        Log.i(TAG, "PlaylistInitAsyncTask Constructor");
        this.mOnPlaylistInitTaskCompleteListener = onPlaylistInitTaskCompleteListener;
        this.mContext = context;
        this.mEntertainmentMetrics = entertainmentMetrics;
    }

    @VisibleForTesting
    EntertainmentLandingPageContract.EntertainmentInteractor.OnPlaylistInitTaskCompleteListener getOnPlaylistInitTaskCompleteListener() {
        return this.mOnPlaylistInitTaskCompleteListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // android.os.AsyncTask
    /* renamed from: doInBackground */
    public Boolean mo1213doInBackground(Object... objArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            Log.i(TAG, "doInBackground ");
            coralService.request((String) objArr[0]).post((PlayPayload) objArr[1]).as(Response.class).execute();
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
            this.mEntertainmentMetrics.logPlaylistInitApi("Failure");
            getOnPlaylistInitTaskCompleteListener().onPlaylistInitFailed(new MediaErrorPayload(this.mContext.getString(R.string.media_error_play_title), this.mContext.getString(R.string.media_error_play_subtitle)));
            return;
        }
        this.mEntertainmentMetrics.logPlaylistInitApi("Success");
    }
}
