package com.dee.app.metrics;

import android.text.TextUtils;
import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
public class MetricName {
    private String metricId;
    private String module;
    private String source;

    /* loaded from: classes2.dex */
    public static class Builder {
        private String metricId;
        private String module;
        private String source;

        public Builder(@NonNull String str) {
            this.metricId = str;
        }

        public MetricName build() {
            return new MetricName(this.metricId, this.module, this.source);
        }

        public Builder withMetricId(@NonNull String str) {
            this.metricId = str;
            return this;
        }

        public Builder withModule(String str) {
            this.module = str;
            return this;
        }

        public Builder withSource(String str) {
            this.source = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        boolean equals;
        boolean equals2;
        boolean equals3;
        if (obj instanceof MetricName) {
            MetricName metricName = (MetricName) obj;
            String str = this.metricId;
            if (str == null) {
                equals = metricName.metricId == null;
            } else {
                equals = str.equals(metricName.metricId);
            }
            String str2 = this.module;
            if (str2 == null) {
                equals2 = metricName.module == null;
            } else {
                equals2 = str2.equals(metricName.module);
            }
            String str3 = this.source;
            String str4 = metricName.source;
            if (str3 == null) {
                equals3 = str4 == null;
            } else {
                equals3 = str3.equals(str4);
            }
            return equals && equals2 && equals3;
        }
        return false;
    }

    public String getMetricId() {
        return this.metricId;
    }

    public String getModule() {
        return this.module;
    }

    public String getSource() {
        return this.source;
    }

    public int hashCode() {
        String str = this.metricId;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        String str2 = this.module;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.source;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public void setMetricId(@NonNull String str) {
        this.metricId = str;
    }

    public void setModule(String str) {
        this.module = str;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String toString() {
        String[] strArr;
        StringBuilder sb = new StringBuilder(this.metricId);
        for (String str : new String[]{this.module, this.source}) {
            if (!TextUtils.isEmpty(str)) {
                sb.insert(0, '.');
                sb.insert(0, str);
            }
        }
        return sb.toString();
    }

    private MetricName(@NonNull String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            this.metricId = str;
            this.module = str2;
            this.source = str3;
            return;
        }
        throw new IllegalArgumentException("Metric id cannot be blank.");
    }

    public MetricName(@NonNull MetricName metricName) {
        this.metricId = metricName.getMetricId();
        this.module = metricName.getModule();
        this.source = metricName.getSource();
    }
}
