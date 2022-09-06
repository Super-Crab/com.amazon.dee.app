package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl;
import com.amazon.identity.auth.device.mv;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mw extends ms {
    private static mw vF;
    private Context mContext;
    private a vG;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a {
        final MetricEvent mMetricEvent;
        private final PeriodicMetricReporter mPeriodicMetricReporter;

        a(Context context) {
            this.mPeriodicMetricReporter = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(context), context.getPackageName(), "MAPAndroidPeriodicMetric");
            this.mPeriodicMetricReporter.startRecordingPeriodically(5L, TimeUnit.MINUTES);
            this.mMetricEvent = this.mPeriodicMetricReporter.getMetricEvent();
        }
    }

    private mw(Context context) {
        this.mContext = context;
        this.vG = new a(context);
        this.vG.mMetricEvent.incrementCounter("MAPAPP_DCMThirdPartyPeriodic_SUPPORTED", 1.0d);
        io.i("ThirdPartyPeriodicMetricsCollector", "Successfully create ThirdPartyPeriodicMetricsCollector");
    }

    public static synchronized mw aT(Context context) {
        mw mwVar;
        synchronized (mw.class) {
            if (vF == null) {
                vF = new mw(context);
            }
            mwVar = vF;
        }
        return mwVar;
    }

    @Override // com.amazon.identity.auth.device.ms
    public void bA(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (!mq.aP(this.mContext) || (aVar = this.vG) == null || (metricEvent = aVar.mMetricEvent) == null) {
            return;
        }
        metricEvent.incrementCounter(str, 1.0d);
    }

    @Override // com.amazon.identity.auth.device.ms
    public mv eN(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (mq.aP(this.mContext) && (aVar = this.vG) != null && (metricEvent = aVar.mMetricEvent) != null) {
            return new my(metricEvent, str);
        }
        return new mv.b();
    }
}
