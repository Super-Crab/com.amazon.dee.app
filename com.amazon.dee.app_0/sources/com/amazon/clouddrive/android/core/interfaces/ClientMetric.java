package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.LinkedHashMap;
import java.util.Map;
@SuppressFBWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NONNULL_VALUE"})
/* loaded from: classes11.dex */
public final class ClientMetric {
    @Nullable
    private String pageName;
    @Nullable
    private String status;
    @Nullable
    private String tagName;
    @NonNull
    private final Map<MetricName, Integer> counters = new LinkedHashMap();
    @NonNull
    private final Map<MetricName, Double> timers = new LinkedHashMap();
    @NonNull
    private Map<String, String> metadata = new LinkedHashMap();
    @NonNull
    private final Map<MetricName, Exception> errors = new LinkedHashMap();

    public ClientMetric addCounter(@NonNull MetricName metricName, int i) {
        this.counters.put(metricName, Integer.valueOf(i));
        return this;
    }

    public ClientMetric addError(@NonNull MetricName metricName, @NonNull Exception exc) {
        this.errors.put(metricName, exc);
        return this;
    }

    public ClientMetric addMetadata(@NonNull String str, @Nullable String str2) {
        if (str2 != null) {
            this.metadata.put(str, str2);
        } else {
            this.metadata.remove(str);
        }
        return this;
    }

    public ClientMetric addPivot(@NonNull MetricPivot metricPivot) {
        return addMetadata(metricPivot.getPivotKey(), metricPivot.getPivotValue());
    }

    public ClientMetric addTimer(@NonNull MetricName metricName, double d) {
        this.timers.put(metricName, Double.valueOf(d));
        return this;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ClientMetric)) {
            return false;
        }
        ClientMetric clientMetric = (ClientMetric) obj;
        Map<MetricName, Integer> counters = getCounters();
        Map<MetricName, Integer> counters2 = clientMetric.getCounters();
        if (counters != null ? !counters.equals(counters2) : counters2 != null) {
            return false;
        }
        Map<MetricName, Double> timers = getTimers();
        Map<MetricName, Double> timers2 = clientMetric.getTimers();
        if (timers != null ? !timers.equals(timers2) : timers2 != null) {
            return false;
        }
        Map<MetricName, Exception> errors = getErrors();
        Map<MetricName, Exception> errors2 = clientMetric.getErrors();
        if (errors != null ? !errors.equals(errors2) : errors2 != null) {
            return false;
        }
        Map<String, String> metadata = getMetadata();
        Map<String, String> metadata2 = clientMetric.getMetadata();
        if (metadata != null ? !metadata.equals(metadata2) : metadata2 != null) {
            return false;
        }
        String pageName = getPageName();
        String pageName2 = clientMetric.getPageName();
        if (pageName != null ? !pageName.equals(pageName2) : pageName2 != null) {
            return false;
        }
        String status = getStatus();
        String status2 = clientMetric.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        String tagName = getTagName();
        String tagName2 = clientMetric.getTagName();
        return tagName != null ? tagName.equals(tagName2) : tagName2 == null;
    }

    public Map<MetricName, Integer> getCounters() {
        return this.counters;
    }

    public Map<MetricName, Exception> getErrors() {
        return this.errors;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public String getPageName() {
        return this.pageName;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTagName() {
        return this.tagName;
    }

    public Map<MetricName, Double> getTimers() {
        return this.timers;
    }

    public int hashCode() {
        Map<MetricName, Integer> counters = getCounters();
        int i = 43;
        int hashCode = counters == null ? 43 : counters.hashCode();
        Map<MetricName, Double> timers = getTimers();
        int hashCode2 = ((hashCode + 59) * 59) + (timers == null ? 43 : timers.hashCode());
        Map<MetricName, Exception> errors = getErrors();
        int hashCode3 = (hashCode2 * 59) + (errors == null ? 43 : errors.hashCode());
        Map<String, String> metadata = getMetadata();
        int hashCode4 = (hashCode3 * 59) + (metadata == null ? 43 : metadata.hashCode());
        String pageName = getPageName();
        int hashCode5 = (hashCode4 * 59) + (pageName == null ? 43 : pageName.hashCode());
        String status = getStatus();
        int hashCode6 = (hashCode5 * 59) + (status == null ? 43 : status.hashCode());
        String tagName = getTagName();
        int i2 = hashCode6 * 59;
        if (tagName != null) {
            i = tagName.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ClientMetric(counters=");
        outline107.append(getCounters());
        outline107.append(", timers=");
        outline107.append(getTimers());
        outline107.append(", errors=");
        outline107.append(getErrors());
        outline107.append(", metadata=");
        outline107.append(getMetadata());
        outline107.append(", pageName=");
        outline107.append(getPageName());
        outline107.append(", status=");
        outline107.append(getStatus());
        outline107.append(", tagName=");
        outline107.append(getTagName());
        outline107.append(")");
        return outline107.toString();
    }

    public ClientMetric withMetadata(@NonNull Map<String, String> map) {
        this.metadata = map;
        return this;
    }

    public ClientMetric withPageName(@NonNull String str) {
        this.pageName = str;
        return this;
    }

    public ClientMetric withStatus(@NonNull String str) {
        this.status = str;
        return this;
    }

    public ClientMetric withTagName(@NonNull String str) {
        this.tagName = str;
        return this;
    }
}
