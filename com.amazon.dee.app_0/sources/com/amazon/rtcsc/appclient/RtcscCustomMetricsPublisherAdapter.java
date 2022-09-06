package com.amazon.rtcsc.appclient;

import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.RtcscCustomMetric;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
/* loaded from: classes13.dex */
public abstract class RtcscCustomMetricsPublisherAdapter extends IRtcscCustomMetricsPublisherAdapter.Stub {
    @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
    public int getAPIVersion() {
        return 1;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
    public void onError(RtcscErrorCode rtcscErrorCode) {
        throw new UnsupportedOperationException("onError is not implemented.");
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter
    public void recordMetric(RtcscCustomMetric rtcscCustomMetric) {
        throw new UnsupportedOperationException("recordMetric is not implemented");
    }
}
