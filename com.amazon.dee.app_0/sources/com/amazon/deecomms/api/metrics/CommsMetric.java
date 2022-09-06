package com.amazon.deecomms.api.metrics;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.DateUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public abstract class CommsMetric {
    private static final String METRIC_SOURCE = "ANDROID-NATIVE";
    private String mMetricName;
    private final String mServiceName;
    private final MetricType mType;
    private Map<String, Object> metaData;

    /* loaded from: classes12.dex */
    public enum MetricType {
        Operational,
        ClickStream
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CommsMetric(MetricType metricType, String str, String str2) {
        this.metaData = new HashMap();
        this.mType = metricType;
        this.mServiceName = str;
        this.mMetricName = str2;
    }

    protected void addAttributesForToString(StringBuilder sb) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String format(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(DateUtil.getUTCTime(new Date()));
        sb.append(",");
        sb.append(METRIC_SOURCE);
        sb.append(",");
        sb.append(this.mMetricName);
        sb.append(",");
        sb.append(this.mType);
        sb.append(",");
        sb.append(str);
        sb.append(",");
        addAttributesForToString(sb);
        for (Map.Entry<String, Object> entry : this.metaData.entrySet()) {
            String key = entry.getKey();
            sb.append(key);
            sb.append(Chars.EQ);
            sb.append(!MetricKeys.META_HASHED_COMMS_ID.equals(key) ? entry.getValue() : "*****");
            sb.append(" ");
        }
        return sb.toString();
    }

    public String formatMetric() {
        return format("");
    }

    public Map<String, Object> getMetadata() {
        return this.metaData;
    }

    public String getMetricName() {
        return this.mMetricName;
    }

    public String getServiceName() {
        return this.mServiceName;
    }

    public MetricType getType() {
        return this.mType;
    }

    public void setMetadata(Map<String, Object> map) {
        this.metaData = map;
    }

    public void setMetricName(String str) {
        this.mMetricName = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetricType=");
        outline107.append(this.mType);
        outline107.append(", ");
        outline107.append("ServiceName=");
        GeneratedOutlineSupport1.outline180(outline107, this.mServiceName, ", ", "MetricName=");
        outline107.append(this.mMetricName);
        outline107.append(", ");
        addAttributesForToString(outline107);
        outline107.append(", ");
        outline107.append("Metadata={");
        for (Map.Entry<String, Object> entry : this.metaData.entrySet()) {
            outline107.append(entry.getKey());
            outline107.append(Chars.EQ);
            outline107.append(entry.getValue());
            outline107.append(", ");
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    protected CommsMetric(MetricType metricType, String str) {
        this(metricType, "Comms", str);
    }
}
