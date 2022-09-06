package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.ClickStreamMetricsEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
/* loaded from: classes.dex */
final class DcmDemReporter implements DemReporter {
    private static final String TAG = "DcmDemReporter:";
    private final AdapterFactory<AccessorySession, AccessoryAttributes> accessoryAttributesFactory;
    private final EnvironmentAttributes environmentAttributes;
    private final SingletonSupplier<MetricsFactory> metricsFactorySupplier;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DcmDemReporter(@NonNull SingletonSupplier<MetricsFactory> singletonSupplier, @NonNull EnvironmentAttributes environmentAttributes, @NonNull AdapterFactory<AccessorySession, AccessoryAttributes> adapterFactory) {
        Preconditions.notNull(singletonSupplier, "metricsFactorySupplier");
        Preconditions.notNull(environmentAttributes, "environmentAttributes");
        Preconditions.notNull(adapterFactory, "accessoryAttributesFactory");
        this.metricsFactorySupplier = singletonSupplier;
        this.environmentAttributes = environmentAttributes;
        this.accessoryAttributesFactory = adapterFactory;
    }

    @Override // com.amazon.alexa.accessory.engagement.DemReporter
    public void report(@NonNull DemType demType, @NonNull AccessorySession accessorySession, String str) {
        Preconditions.notNull(demType, "aMetricType");
        Preconditions.notNull(accessorySession, "anAccessorySession");
        MetricsFactory supply = this.metricsFactorySupplier.supply();
        ClickStreamMetricsEvent createClickStreamMetricEvent = supply.createClickStreamMetricEvent("EMP.MetricsCollectionAgent", demType.getSourceName());
        createClickStreamMetricEvent.addString("MarketplaceID", this.environmentAttributes.getPreferredMarketplaceId());
        createClickStreamMetricEvent.addString(MetricsConfiguration.COUNTRY_OF_RESIDENCE, this.environmentAttributes.getCountryOfResidence());
        createClickStreamMetricEvent.setNonAnonymousCustomerId(this.environmentAttributes.getDirectedCustomerId());
        createClickStreamMetricEvent.addString("reasonCode", demType.getReasonCode());
        createClickStreamMetricEvent.addString("metricSource", "APP");
        AccessoryAttributes createFrom = this.accessoryAttributesFactory.createFrom(accessorySession);
        createClickStreamMetricEvent.addString("dem_connected_dsn", createFrom.getAccessoryDeviceSerialNumber());
        createClickStreamMetricEvent.addString("dem_connected_device_type", createFrom.getAccessoryDeviceType());
        createClickStreamMetricEvent.addString("dem_connected_software_version", createFrom.getAccessorySoftwareVersion());
        createClickStreamMetricEvent.addString("dem_connected_device_language", createFrom.getAccessoryDeviceLanguage());
        createClickStreamMetricEvent.addString("companion_app_version", this.environmentAttributes.getApplicationVersionCode());
        if (demType == DemType.USER_PRESENT) {
            createClickStreamMetricEvent.addString("dem_connected_device_record_time", str);
        }
        UsageInfo usageInfo = new UsageInfo(demType.getPageType(), "deviceAction", "AMA", "AlexaApplication");
        usageInfo.setPageAction(demType.getPageAction());
        createClickStreamMetricEvent.setUsageInfo(usageInfo);
        Logger.d("%s logging DEM metric: %s", TAG, createClickStreamMetricEvent.toString());
        supply.record(createClickStreamMetricEvent, Priority.CRITICAL, Channel.NON_ANONYMOUS);
    }
}
