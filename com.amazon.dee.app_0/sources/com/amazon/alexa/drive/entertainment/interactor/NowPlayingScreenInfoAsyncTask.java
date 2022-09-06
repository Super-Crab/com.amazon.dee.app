package com.amazon.alexa.drive.entertainment.interactor;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import java.lang.ref.WeakReference;
/* loaded from: classes7.dex */
public class NowPlayingScreenInfoAsyncTask extends AsyncTask<Object, Integer, String> {
    private static final int PAYLOAD_SIZE = 2;
    private static final String TAG = NowPlayingScreenInfoAsyncTask.class.getSimpleName();
    private WeakReference<Context> mContext;
    private final EntertainmentMetrics mEntertainmentMetrics;
    private NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener mOnNowPlayingScreenInfoAsyncTaskListener;

    public NowPlayingScreenInfoAsyncTask(NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener onNowPlayingScreenInfoAsyncTaskListener, Context context, EntertainmentMetrics entertainmentMetrics) {
        Log.i(TAG, "NowPlayingScreenInfoAsyncTask Constructor");
        this.mOnNowPlayingScreenInfoAsyncTaskListener = onNowPlayingScreenInfoAsyncTaskListener;
        this.mContext = new WeakReference<>(context);
        this.mEntertainmentMetrics = entertainmentMetrics;
    }

    @VisibleForTesting
    NowPlayingScreenContract.Interactor.OnNowPlayingScreenInfoAsyncTaskListener getOnNowPlayingScreenInfoAsyncTaskListener() {
        return this.mOnNowPlayingScreenInfoAsyncTaskListener;
    }

    int getWindowWidth() {
        Context context = this.mContext.get();
        if (context != null) {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getWindowWidth ");
            outline107.append(point.x);
            Log.i(str, outline107.toString());
            return point.x;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public String doInBackground(Object... objArr) {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline21(CoralService.class);
        Preconditions.checkNotNull(coralService);
        try {
            if (objArr.length < 2) {
                return null;
            }
            Log.i(TAG, "doInBackground ");
            StringBuilder sb = new StringBuilder();
            sb.append(EntertainmentConstants.API_PLAYER_INFO);
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            sb.append("deviceSerialNumber");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(objArr[1]);
            sb.append(";");
            sb.append("deviceType");
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(objArr[0]);
            if (getWindowWidth() != 0) {
                sb.append(";");
                sb.append(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_SCREEN_WIDTH);
                sb.append(Config.Compare.EQUAL_TO);
                sb.append(getWindowWidth());
            }
            return coralService.request(sb.toString()).get().asRaw().execute().body().string();
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline156("doInBackground() | IOException: ", e, TAG);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(String str) {
        Log.i(TAG, "onPostExecute ");
        if (str == null) {
            this.mEntertainmentMetrics.logNowPlayingScreenInfoApi("Failure");
            if (this.mContext.get() == null) {
                return;
            }
            getOnNowPlayingScreenInfoAsyncTaskListener().onNowPlayingScreenInfoFetchFailed(new MediaErrorPayload(this.mContext.get().getString(R.string.media_error_nps_title), this.mContext.get().getString(R.string.media_error_play_subtitle)));
            return;
        }
        this.mEntertainmentMetrics.logNowPlayingScreenInfoApi("Success");
        getOnNowPlayingScreenInfoAsyncTaskListener().onNowPlayingScreenInfoFetchComplete(str);
    }
}
