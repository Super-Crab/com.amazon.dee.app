package com.amazon.rtcsc.service.appclient;

import android.os.RemoteException;
import com.amazon.rtcsc.interfaces.IRtcscCustomMetricsPublisherAdapter;
import com.amazon.rtcsc.interfaces.RtcscCustomMetric;
import com.amazon.rtcsc.interfaces.RtcscErrorCode;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.amazon.rtcsc.wrappers.CounterVector;
import com.amazon.rtcsc.wrappers.MetadataVector;
import com.amazon.rtcsc.wrappers.RTCCustomMetricInterface;
import com.amazon.rtcsc.wrappers.RTCCustomMetricsPublisherAdapterInterface;
import com.amazon.rtcsc.wrappers.TimerVector;
/* loaded from: classes13.dex */
public class RtcscCustomMetricsPublisherAdapterJNIImpl extends RTCCustomMetricsPublisherAdapterInterface {
    private IRtcscCustomMetricsPublisherAdapter mAdapter;
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscCustomMetricsPublisherAdapterJNIImpl.class);

    public RtcscCustomMetricsPublisherAdapterJNIImpl(IRtcscCustomMetricsPublisherAdapter iRtcscCustomMetricsPublisherAdapter) {
        this.mAdapter = null;
        this.mAdapter = iRtcscCustomMetricsPublisherAdapter;
    }

    public void onError(RtcscErrorCode rtcscErrorCode) {
        this.mLog.e(String.format("onError received with errorCode %s. Forwarding to adapter.", rtcscErrorCode));
        try {
            this.mAdapter.onError(rtcscErrorCode);
        } catch (RemoteException unused) {
            this.mLog.w("onError callback could not be invoked.");
        }
    }

    @Override // com.amazon.rtcsc.wrappers.RTCCustomMetricsPublisherAdapterInterface
    public boolean recordMetric(RTCCustomMetricInterface rTCCustomMetricInterface) {
        RtcscCustomMetric.Priority priority;
        if (rTCCustomMetricInterface == null) {
            this.mLog.e("recordMetric. null metric passed.");
            return false;
        }
        String programName = rTCCustomMetricInterface.getProgramName();
        String sourceName = rTCCustomMetricInterface.getSourceName();
        TimerVector timers = rTCCustomMetricInterface.getTimers();
        int size = timers.size();
        CounterVector counters = rTCCustomMetricInterface.getCounters();
        int size2 = counters.size();
        MetadataVector metadata = rTCCustomMetricInterface.getMetadata();
        int size3 = metadata.size();
        RTCCustomMetricInterface.Priority priority2 = rTCCustomMetricInterface.getPriority();
        RtcscCustomMetric.Timer[] timerArr = new RtcscCustomMetric.Timer[size];
        for (int i = 0; i < timerArr.length; i++) {
            timerArr[i] = new RtcscCustomMetric.Timer(timers.mo4532get(i).getName(), timers.mo4532get(i).getValue());
        }
        RtcscCustomMetric.Counter[] counterArr = new RtcscCustomMetric.Counter[size2];
        for (int i2 = 0; i2 < counterArr.length; i2++) {
            counterArr[i2] = new RtcscCustomMetric.Counter(counters.mo4528get(i2).getName(), counters.mo4528get(i2).getValue());
        }
        RtcscCustomMetric.Metadata[] metadataArr = new RtcscCustomMetric.Metadata[size3];
        for (int i3 = 0; i3 < metadataArr.length; i3++) {
            metadataArr[i3] = new RtcscCustomMetric.Metadata(metadata.mo4530get(i3).getName(), metadata.mo4530get(i3).getValue());
        }
        if (priority2 == RTCCustomMetricInterface.Priority.CRITICAL) {
            priority = RtcscCustomMetric.Priority.CRITICAL;
        } else if (priority2 == RTCCustomMetricInterface.Priority.HIGH) {
            priority = RtcscCustomMetric.Priority.HIGH;
        } else {
            priority = RtcscCustomMetric.Priority.NORMAL;
        }
        try {
            this.mAdapter.recordMetric(new RtcscCustomMetric(programName, sourceName, timerArr, counterArr, metadataArr, priority));
            return true;
        } catch (RemoteException unused) {
            this.mLog.w("recordMetric callback could not be invoked.");
            return false;
        }
    }
}
