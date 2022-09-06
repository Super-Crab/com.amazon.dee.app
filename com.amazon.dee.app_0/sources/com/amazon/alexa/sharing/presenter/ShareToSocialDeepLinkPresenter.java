package com.amazon.alexa.sharing.presenter;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.broadcast.ShareSheetIntentBroadcastReceiver;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Random;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class ShareToSocialDeepLinkPresenter {
    private static final String KEY_SHARING_CONTENT = "shareContent";
    private static final String METRICS_KEY_SHARE_SHEET_FAILED_COUNT = "comms.cx.ShareToSocialDeepLinkPresenter.constructMobileShareSheetIntent.FailedCount";
    private static final String METRICS_KEY_SHARE_SHEET_FAILED_REASON_PREFIX = "comms.cx.ShareToSocialDeepLinkPresenter.constructMobileShareSheetIntent.shareSheetFailed.%s";
    private static final String METRICS_KEY_SHARE_SHEET_SUCCEED_COUNT = "comms.cx.ShareToSocialDeepLinkPresenter.constructMobileShareSheetIntent.SucceedCount";
    private CommsMetricsEmitter metricsEmitter;
    public Lazy<AlexaCommsCoreMetricsService> metricsServiceLazy;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ShareToSocialDeepLinkPresenter.class);
    private static final String TAG = ShareToSocialDeepLinkPresenter.class.getSimpleName();
    private static final int PENDING_INTENT_REQUEST_CODE = new Random(System.currentTimeMillis()).nextInt();

    @Inject
    public ShareToSocialDeepLinkPresenter(Lazy<AlexaCommsCoreMetricsService> lazy) {
        this.metricsEmitter = new CommsMetricsEmitter(lazy, TAG);
    }

    private Intent constructMobileShareSheetIntent(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.putExtra("android.intent.extra.TITLE", str);
        intent.setType(com.amazon.deecomms.common.Constants.TEXT_PLAIN_MEDIA_TYPE);
        return intent;
    }

    @SuppressLint({"UnspecifiedImmutableFlag"})
    @VisibleForTesting
    PendingIntent getPendingIntent(Context context) {
        return PendingIntent.getBroadcast(context, PENDING_INTENT_REQUEST_CODE, new Intent(context, ShareSheetIntentBroadcastReceiver.class), 134217728);
    }

    public Intent prepareMobileShareSheet(Intent intent, Context context) throws UnsupportedEncodingException {
        Uri data = intent.getData();
        CommsLogger commsLogger = LOG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sharing URI:");
        outline107.append(data.toString());
        commsLogger.i(outline107.toString());
        try {
            String decode = URLDecoder.decode(data.getQueryParameter(KEY_SHARING_CONTENT), "UTF-8");
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("Decoded share content:" + decode);
            Intent constructMobileShareSheetIntent = constructMobileShareSheetIntent(decode);
            this.metricsEmitter.recordOccurrenceMetric(METRICS_KEY_SHARE_SHEET_SUCCEED_COUNT, Collections.EMPTY_MAP);
            return Intent.createChooser(constructMobileShareSheetIntent, null, getPendingIntent(context).getIntentSender());
        } catch (Throwable th) {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Share sheet did not open due to: ");
            outline1072.append(th.getMessage());
            commsLogger3.e(outline1072.toString(), th);
            this.metricsEmitter.recordOccurrenceMetric(METRICS_KEY_SHARE_SHEET_FAILED_COUNT, Collections.EMPTY_MAP);
            this.metricsEmitter.recordOccurrenceMetric(String.format(METRICS_KEY_SHARE_SHEET_FAILED_REASON_PREFIX, th.getClass().getSimpleName()), Collections.EMPTY_MAP);
            throw th;
        }
    }
}
