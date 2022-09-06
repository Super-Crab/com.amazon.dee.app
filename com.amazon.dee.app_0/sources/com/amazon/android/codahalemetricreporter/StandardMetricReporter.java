package com.amazon.android.codahalemetricreporter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.amazon.android.codahalemetricreporter.impl.JsonReportGenerator;
import com.amazon.android.codahalemetricreporter.impl.MetricIntentReporter;
import com.amazon.android.codahalemetricreporter.impl.TextReportGenerator;
import com.codahale.metrics.MetricRegistry;
/* loaded from: classes11.dex */
public final class StandardMetricReporter {
    private static final boolean SUPPORTED;
    private static final String THREAD_NAME = "StandardMetricReporter";
    private HandlerThread mHandlerThread;
    private final MetricIntentReporter mReporter;

    static {
        int i = Build.VERSION.SDK_INT;
        SUPPORTED = true;
    }

    private StandardMetricReporter(Context context, MetricRegistry metricRegistry, String str) {
        this.mReporter = new MetricIntentReporter(context, metricRegistry, str);
        this.mReporter.addFormat(JsonReportFormat.NAME, new JsonReportGenerator());
        this.mReporter.addFormat(TextReportFormat.NAME, TextReportGenerator.builder().build());
    }

    public static StandardMetricReporter create(Context context, MetricRegistry metricRegistry, String str) {
        if (SUPPORTED) {
            return new StandardMetricReporter(context, metricRegistry, str);
        }
        return null;
    }

    public void start() {
        this.mHandlerThread = new HandlerThread(THREAD_NAME);
        this.mHandlerThread.start();
        this.mReporter.start(new Handler(this.mHandlerThread.getLooper()));
    }

    public void stop() {
        this.mReporter.stop();
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
    }

    public void start(Handler handler) {
        this.mReporter.start(handler);
    }
}
