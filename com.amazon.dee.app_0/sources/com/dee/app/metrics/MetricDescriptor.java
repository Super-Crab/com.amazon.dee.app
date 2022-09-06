package com.dee.app.metrics;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class MetricDescriptor {
    public static final int EMISSION_FACTOR_MAX = 100;
    public static final int EMISSION_FACTOR_MIN = 1;
    private MetricComponentName componentName;
    private Map<String, Object> customEntries;
    private int emissionFactor;
    private MetricName name;

    /* loaded from: classes2.dex */
    public static class Builder {
        private MetricComponentName componentName;
        private MetricName name;
        private int emissionFactor = 100;
        private Map<String, Object> customEntries = new HashMap();

        public Builder(@NonNull MetricName metricName, @NonNull MetricComponentName metricComponentName) {
            this.name = metricName;
            this.componentName = metricComponentName;
        }

        public MetricDescriptor build() {
            return new MetricDescriptor(this.name, this.componentName, this.emissionFactor, this.customEntries);
        }

        public Builder withComponentName(@NonNull MetricComponentName metricComponentName) {
            this.componentName = metricComponentName;
            return this;
        }

        public Builder withCustomEntries(@NonNull Map<String, Object> map) {
            this.customEntries = map;
            return this;
        }

        public Builder withEmissionFactor(@IntRange(from = 1, to = 100) int i) {
            this.emissionFactor = i;
            return this;
        }

        public Builder withName(@NonNull MetricName metricName) {
            this.name = metricName;
            return this;
        }
    }

    @VisibleForTesting
    MetricDescriptor(@NonNull MetricName metricName, @NonNull MetricComponentName metricComponentName, @IntRange(from = 1, to = 100) int i, @NonNull Map<String, Object> map) {
        if (i >= 1 && i <= 100) {
            this.name = metricName;
            this.componentName = metricComponentName;
            this.emissionFactor = i;
            this.customEntries = map;
            return;
        }
        throw new IllegalArgumentException("Emission factor should be between 1 and 100");
    }

    public boolean equals(Object obj) {
        boolean equals;
        boolean equals2;
        boolean equals3;
        if (obj instanceof MetricDescriptor) {
            MetricDescriptor metricDescriptor = (MetricDescriptor) obj;
            MetricName metricName = this.name;
            if (metricName == null) {
                equals = metricDescriptor.name == null;
            } else {
                equals = metricName.equals(metricDescriptor.name);
            }
            MetricComponentName metricComponentName = this.componentName;
            if (metricComponentName == null) {
                equals2 = metricDescriptor.componentName == null;
            } else {
                equals2 = metricComponentName.equals(metricDescriptor.componentName);
            }
            boolean z = this.emissionFactor == metricDescriptor.emissionFactor;
            Map<String, Object> map = this.customEntries;
            Map<String, Object> map2 = metricDescriptor.customEntries;
            if (map == null) {
                equals3 = map2 == null;
            } else {
                equals3 = map.equals(map2);
            }
            return equals && equals2 && z && equals3;
        }
        return false;
    }

    public MetricComponentName getComponentName() {
        return this.componentName;
    }

    public Map<String, Object> getCustomEntries() {
        return this.customEntries;
    }

    public int getEmissionFactor() {
        return this.emissionFactor;
    }

    public MetricName getName() {
        return this.name;
    }

    public int hashCode() {
        MetricName metricName = this.name;
        int i = 0;
        int hashCode = ((metricName != null ? metricName.hashCode() : 0) + 31) * 31;
        MetricComponentName metricComponentName = this.componentName;
        int hashCode2 = (((hashCode + (metricComponentName != null ? metricComponentName.hashCode() : 0)) * 31) + this.emissionFactor) * 31;
        Map<String, Object> map = this.customEntries;
        if (map != null) {
            i = map.hashCode();
        }
        return hashCode2 + i;
    }

    public void setComponentName(@NonNull MetricComponentName metricComponentName) {
        this.componentName = metricComponentName;
    }

    public void setCustomEntries(@NonNull Map<String, Object> map) {
        this.customEntries = map;
    }

    public void setEmissionFactor(@IntRange(from = 1, to = 100) int i) {
        if (i >= 1 && i <= 100) {
            this.emissionFactor = i;
            return;
        }
        throw new IllegalArgumentException("Emission factor should be between 1 and 100");
    }

    public void setName(@NonNull MetricName metricName) {
        this.name = metricName;
    }

    public MetricDescriptor(@NonNull MetricDescriptor metricDescriptor) {
        this.name = metricDescriptor.getName();
        this.componentName = metricDescriptor.getComponentName();
        this.emissionFactor = metricDescriptor.getEmissionFactor();
        this.customEntries = metricDescriptor.getCustomEntries();
    }
}
