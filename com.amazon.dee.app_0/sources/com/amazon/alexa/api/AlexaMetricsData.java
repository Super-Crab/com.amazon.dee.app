package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaMetricsData {
    @Nullable
    private final AlexaMetricsCount alexaMetricsCount;
    @Nullable
    private final AlexaMetricsTime alexaMetricsTime;
    private final String componentName;
    private final String eventName;
    private final String hostPackageName;
    private final AlexaMetricsMetadata metadata;
    private final AlexaMetricsCategory metricsCategory;
    private final AlexaMetricsType metricsType;

    /* loaded from: classes6.dex */
    public static class Builder {
        private AlexaMetricsCount alexaMetricsCount;
        private AlexaMetricsTime alexaMetricsTime;
        private String componentName;
        private String eventName;
        private String hostPackageName;
        private Bundle metadata;
        private AlexaMetricsCategory metricCategory;
        private AlexaMetricsType metricType;

        public Builder(String str, String str2) {
            setHostPackageName(str);
            setComponentName(str2);
        }

        public AlexaMetricsData build() {
            return new AlexaMetricsData(this.hostPackageName, this.componentName, this.eventName, this.metricCategory, this.metricType, AlexaMetricsMetadata.create(this.metadata), this.alexaMetricsTime, this.alexaMetricsCount);
        }

        public Builder setAlexaMetricsCount(AlexaMetricsCount alexaMetricsCount) {
            this.alexaMetricsCount = alexaMetricsCount;
            return this;
        }

        public Builder setAlexaMetricsTime(AlexaMetricsTime alexaMetricsTime) {
            this.alexaMetricsTime = alexaMetricsTime;
            return this;
        }

        public Builder setComponentName(String str) {
            this.componentName = str;
            return this;
        }

        public Builder setEventName(String str) {
            this.eventName = str;
            return this;
        }

        public Builder setHostPackageName(String str) {
            this.hostPackageName = str;
            return this;
        }

        public Builder setMetadata(Bundle bundle) {
            this.metadata = bundle;
            return this;
        }

        public Builder setMetricCategory(AlexaMetricsCategory alexaMetricsCategory) {
            this.metricCategory = alexaMetricsCategory;
            return this;
        }

        public Builder setMetricType(AlexaMetricsType alexaMetricsType) {
            this.metricType = alexaMetricsType;
            return this;
        }
    }

    AlexaMetricsData(String str, String str2, String str3, AlexaMetricsCategory alexaMetricsCategory, AlexaMetricsType alexaMetricsType, AlexaMetricsMetadata alexaMetricsMetadata, @Nullable AlexaMetricsTime alexaMetricsTime, @Nullable AlexaMetricsCount alexaMetricsCount) {
        this.hostPackageName = str;
        this.componentName = str2;
        this.eventName = str3;
        this.alexaMetricsTime = alexaMetricsTime;
        this.alexaMetricsCount = alexaMetricsCount;
        this.metricsCategory = alexaMetricsCategory;
        this.metricsType = alexaMetricsType;
        this.metadata = alexaMetricsMetadata;
    }

    public static AlexaMetricsData fromBundle(Bundle bundle) {
        Preconditions.notNull(bundle, "bundle is null");
        return new AlexaMetricsData(Bundles.getString(bundle, bc.HOST_PACKAGE_NAME), Bundles.getString(bundle, bc.COMPONENT_NAME), Bundles.getString(bundle, bc.EVENT_NAME), AlexaMetricsCategory.valueOf(Bundles.getString(bundle, bc.METRICS_CATEGORY)), AlexaMetricsType.valueOf(Bundles.getString(bundle, bc.METRICS_TYPE)), AlexaMetricsMetadata.create(Bundles.getBundle(bundle, bc.METADATA)), (AlexaMetricsTime) Bundles.getOptionalParcelable(bundle, bc.ALEXA_TIMER, AlexaMetricsTime.class), (AlexaMetricsCount) Bundles.getOptionalParcelable(bundle, bc.ALEXA_COUNTER, AlexaMetricsCount.class));
    }

    public static Bundle toBundle(AlexaMetricsData alexaMetricsData) {
        Preconditions.notNull(alexaMetricsData, "MetricsData is null");
        Bundle bundle = new Bundle();
        bundle.putString(bc.HOST_PACKAGE_NAME.name(), alexaMetricsData.getHostPackageName());
        bundle.putString(bc.COMPONENT_NAME.name(), alexaMetricsData.getComponentName());
        bundle.putString(bc.EVENT_NAME.name(), alexaMetricsData.getEventName());
        bundle.putString(bc.METRICS_CATEGORY.name(), alexaMetricsData.getMetricsCategory().name());
        bundle.putString(bc.METRICS_TYPE.name(), alexaMetricsData.getMetricsType().name());
        bundle.putBundle(bc.METADATA.name(), alexaMetricsData.getMetadata().getBundle());
        bundle.putParcelable(bc.ALEXA_TIMER.name(), alexaMetricsData.getAlexaMetricsTime());
        bundle.putParcelable(bc.ALEXA_COUNTER.name(), alexaMetricsData.getAlexaMetricsCount());
        return bundle;
    }

    public AlexaMetricsCount getAlexaMetricsCount() {
        return this.alexaMetricsCount;
    }

    public AlexaMetricsTime getAlexaMetricsTime() {
        return this.alexaMetricsTime;
    }

    public String getComponentName() {
        return this.componentName;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getHostPackageName() {
        return this.hostPackageName;
    }

    public AlexaMetricsMetadata getMetadata() {
        return this.metadata;
    }

    public AlexaMetricsCategory getMetricsCategory() {
        return this.metricsCategory;
    }

    public AlexaMetricsType getMetricsType() {
        return this.metricsType;
    }
}
