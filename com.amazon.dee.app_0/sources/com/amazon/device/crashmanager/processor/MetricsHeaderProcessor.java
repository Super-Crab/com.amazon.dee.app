package com.amazon.device.crashmanager.processor;

import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.device.crashmanager.CrashManagerActions;
import com.amazon.device.crashmanager.utils.AmazonPackageLookup;
import com.amazon.device.utils.det.DetUtil;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Writer;
/* loaded from: classes12.dex */
public class MetricsHeaderProcessor implements DetUtil.HeaderProcessor {
    private static final DPLogger log = new DPLogger("MetricsHeaderProcessor");
    private final AmazonPackageLookup mAmazonPackageLookup;
    private final MetricEvent mMetricEvent;
    private final DetUtil.HeaderProcessor mNextProcessor;
    private String mTag = null;
    private String mProcessName = null;
    private String mAction = null;

    public MetricsHeaderProcessor(DetUtil.HeaderProcessor headerProcessor, AmazonPackageLookup amazonPackageLookup, MetricEvent metricEvent) {
        if (headerProcessor != null) {
            if (amazonPackageLookup == null) {
                throw new IllegalArgumentException("Package lookup must not be null");
            }
            if (metricEvent != null) {
                this.mNextProcessor = headerProcessor;
                this.mAmazonPackageLookup = amazonPackageLookup;
                this.mMetricEvent = metricEvent;
                return;
            }
            throw new IllegalArgumentException("Metric Event must not be null");
        }
        throw new IllegalArgumentException("Next processor must not be null.");
    }

    private void emitMetrics(String str, double d) {
        String str2 = this.mAction;
        if (str2 == null || !str2.equals(CrashManagerActions.BUILD_MAP)) {
            this.mMetricEvent.addCounter(str, d);
        }
    }

    public String getProcessName() {
        return this.mProcessName;
    }

    @Override // com.amazon.device.utils.det.DetUtil.HeaderProcessor
    public void process(String str, String str2, Writer writer) throws Exception {
        if (str != null && str2 != null) {
            if (str.equals("Process") && this.mTag != null) {
                this.mProcessName = str2;
                emitMetrics(this.mAmazonPackageLookup.generateFriendlyName(this.mProcessName) + "." + this.mTag, 1.0d);
            } else if (str.equals("hasForegroundActivities") && this.mProcessName != null && this.mTag != null) {
                if (Boolean.valueOf(str2).booleanValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.mAmazonPackageLookup.generateFriendlyName(this.mProcessName));
                    sb.append(".");
                    emitMetrics(GeneratedOutlineSupport1.outline91(sb, this.mTag, ".foreground"), 1.0d);
                }
            } else {
                log.error("MetricsHeaderProcessor.process", "unknown header. cannot process ", "header", str);
            }
        }
        this.mNextProcessor.process(str, str2, writer);
    }

    public void setAction(String str) {
        this.mAction = str;
    }

    public void setProcessTag(String str) {
        this.mTag = str;
    }
}
