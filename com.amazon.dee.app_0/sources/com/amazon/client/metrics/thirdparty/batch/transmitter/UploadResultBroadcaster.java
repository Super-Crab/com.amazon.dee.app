package com.amazon.client.metrics.thirdparty.batch.transmitter;

import android.content.Context;
import android.content.Intent;
import com.amazon.client.metrics.thirdparty.MetricsServiceConstants;
/* loaded from: classes11.dex */
public class UploadResultBroadcaster {
    private static final String DEFAULT_QUEUE_NAME = "Unspecified";
    private Context mContext;

    public UploadResultBroadcaster(Context context) {
        this.mContext = context;
    }

    public void broadcastResult(int i, int i2) {
        broadcastResult(i, i2, DEFAULT_QUEUE_NAME);
    }

    public void broadcastResult(int i, int i2, String str) {
        this.mContext.sendBroadcast(new Intent(MetricsServiceConstants.ACTION_METRICS_UPLOAD_RESULT).putExtra(MetricsServiceConstants.NUM_BATCHES_SENT_KEY, i2).putExtra(MetricsServiceConstants.RESULT_CODE_KEY, i).putExtra(MetricsServiceConstants.QUEUE_NAME_KEY, str));
    }
}
