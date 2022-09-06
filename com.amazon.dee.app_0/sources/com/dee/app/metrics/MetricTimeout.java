package com.dee.app.metrics;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricTimeout;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class MetricTimeout {
    private static final String TAG = "MetricTimeout";
    private TimeoutDescriptor firstTimeout;
    private final Handler handler = new Handler();
    private boolean hasFiredOnce = false;
    private MetricsService metricsService;
    private TimeoutDescriptor secondTimeout;
    private Timer timer;
    private Map<String, Object> timerEntries;
    private String timerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.dee.app.metrics.MetricTimeout$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass1 extends TimerTask {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$run$0$MetricTimeout$1() {
            if (MetricTimeout.this.metricsService != null) {
                MetricTimeout.this.metricsService.recordEvent(MetricTimeout.this.timerName, "Application", MetricTimeout.this.timerEntries);
            }
            String unused = MetricTimeout.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event occurred ");
            outline107.append(MetricTimeout.this.timerName);
            outline107.toString();
            if (!MetricTimeout.this.hasFiredOnce) {
                MetricTimeout.this.hasFiredOnce = true;
                MetricTimeout metricTimeout = MetricTimeout.this;
                metricTimeout.timerName = metricTimeout.secondTimeout.metricEvent;
                MetricTimeout metricTimeout2 = MetricTimeout.this;
                metricTimeout2.timerEntries = metricTimeout2.secondTimeout.metricEntries;
                return;
            }
            MetricTimeout.this.stop();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MetricTimeout.this.handler.post(new Runnable() { // from class: com.dee.app.metrics.-$$Lambda$MetricTimeout$1$bG1Ky6q9ipqVpVn9G3OOEbVm6YI
                @Override // java.lang.Runnable
                public final void run() {
                    MetricTimeout.AnonymousClass1.this.lambda$run$0$MetricTimeout$1();
                }
            });
        }
    }

    /* renamed from: com.dee.app.metrics.MetricTimeout$2  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$dee$app$metrics$MetricTimeout$Timeout = new int[Timeout.values().length];

        static {
            try {
                $SwitchMap$com$dee$app$metrics$MetricTimeout$Timeout[Timeout.FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$dee$app$metrics$MetricTimeout$Timeout[Timeout.SECOND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public enum Timeout {
        FIRST,
        SECOND
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TimeoutDescriptor {
        Map<String, Object> metricEntries;
        String metricEvent;
        long timeout;

        TimeoutDescriptor(String str, Map<String, Object> map, long j) {
            if (TextUtils.isEmpty(str) || j <= 0) {
                String str2 = MetricTimeout.TAG;
                Log.wtf(str2, "invalid timeout name: " + str + " timeout: " + j);
            }
            this.metricEvent = str;
            this.metricEntries = map;
            this.timeout = j;
        }
    }

    public MetricTimeout(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    public void setEventData(Timeout timeout, String str, int i) {
        setEventData(timeout, str, null, i);
    }

    public void start() {
        TimeoutDescriptor timeoutDescriptor;
        stop();
        TimeoutDescriptor timeoutDescriptor2 = this.firstTimeout;
        if (timeoutDescriptor2 != null && (timeoutDescriptor = this.secondTimeout) != null && timeoutDescriptor2.timeout <= timeoutDescriptor.timeout) {
            this.timerName = timeoutDescriptor2.metricEvent;
            this.timerEntries = timeoutDescriptor2.metricEntries;
            this.hasFiredOnce = false;
            this.timer = new Timer();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1();
            Timer timer = this.timer;
            long j = this.firstTimeout.timeout;
            timer.schedule(anonymousClass1, j, this.secondTimeout.timeout - j);
            return;
        }
        Log.wtf(TAG, "events or timeouts are not correct");
    }

    public void stop() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }

    public void setEventData(Timeout timeout, String str, Map<String, Object> map, int i) {
        int ordinal = timeout.ordinal();
        if (ordinal == 0) {
            this.firstTimeout = new TimeoutDescriptor(str, map, TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS));
        } else if (ordinal != 1) {
            Log.wtf(TAG, "setEventData timeout is not FIRST or SECOND");
        } else {
            this.secondTimeout = new TimeoutDescriptor(str, map, TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS));
        }
    }
}
