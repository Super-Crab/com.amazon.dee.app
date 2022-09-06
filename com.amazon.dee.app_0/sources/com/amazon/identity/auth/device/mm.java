package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.client.metrics.PeriodicMetricReporter;
import com.amazon.client.metrics.PeriodicMetricReporterImpl;
import com.amazon.identity.auth.device.mv;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class mm extends ms {
    private static mm vj;
    private a vk;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a {
        private final PeriodicMetricReporter vl;
        final MetricEvent vm;

        a(Context context) {
            this.vl = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(context), context.getPackageName(), "MAPAndroidPeriodicMetric");
            this.vl.startRecordingPeriodically(5L, TimeUnit.MINUTES);
            this.vm = this.vl.getMetricEvent();
        }
    }

    private mm(Context context) {
        this.vk = new a(context);
        this.vk.vm.incrementCounter("MAPAPP_DCMFireOSPeriodic_SUPPORTED", 1.0d);
        io.i("FireOSPeriodicMetricsCollector", "Successfully create FireOSPeriodicMetricsCollector.");
    }

    public static synchronized mm aK(Context context) {
        mm mmVar;
        synchronized (mm.class) {
            if (vj == null) {
                vj = new mm(context);
            }
            mmVar = vj;
        }
        return mmVar;
    }

    @Override // com.amazon.identity.auth.device.ms
    public void bA(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (!mq.iN() || (aVar = this.vk) == null || (metricEvent = aVar.vm) == null) {
            return;
        }
        metricEvent.incrementCounter(str, 1.0d);
    }

    @Override // com.amazon.identity.auth.device.ms
    public mv eN(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (mq.iN() && (aVar = this.vk) != null && (metricEvent = aVar.vm) != null) {
            return new mo(metricEvent, str);
        }
        return new mv.b();
    }
}
