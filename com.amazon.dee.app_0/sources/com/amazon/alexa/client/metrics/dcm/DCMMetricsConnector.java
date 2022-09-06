package com.amazon.alexa.client.metrics.dcm;

import android.text.TextUtils;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.client.metrics.core.AlexaMetricsUtils;
import com.amazon.alexa.client.metrics.core.AsyncMetricsConnector;
import com.amazon.alexa.client.metrics.core.DefaultAlexaMetricsEvent;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import com.amazon.alexa.client.metrics.core.MetricsCounter;
import com.amazon.alexa.client.metrics.core.MetricsTimer;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.Map;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class DCMMetricsConnector extends AsyncMetricsConnector {
    private static final String NULL_VALUE = "NULL";
    private static final String TAG = DCMMetricsConnector.class.getName();
    private final DeviceInformation deviceInformation;
    private final TimeZone localTimeZone;
    private final MarketplaceAuthority marketplaceAuthority;
    private MetricsFactory metrics;
    private final Lazy<MetricsFactory> metricsFactoryProvider;

    @Inject
    public DCMMetricsConnector(DeviceInformation deviceInformation, Lazy<MetricsFactory> lazy, MarketplaceAuthority marketplaceAuthority, Lazy<ClientConfiguration> lazy2) {
        super(lazy2);
        this.localTimeZone = TimeZone.getDefault();
        this.deviceInformation = deviceInformation;
        this.metricsFactoryProvider = lazy;
        this.marketplaceAuthority = marketplaceAuthority;
    }

    private void addMetadata(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent, MetricEvent metricEvent) {
        addString(metricEvent, "EventName", defaultAlexaMetricsEvent.getEventName());
        addString(metricEvent, "EventTimestamp", Long.toString(defaultAlexaMetricsEvent.getEventDate()));
        addString(metricEvent, "localTimezone", this.localTimeZone.getDisplayName());
        String osType = this.deviceInformation.getOsType();
        String str = "NULL";
        String countryCode = !TextUtils.isEmpty(this.deviceInformation.getCountryCode()) ? this.deviceInformation.getCountryCode() : str;
        if (!TextUtils.isEmpty(this.deviceInformation.getVersionName())) {
            str = this.deviceInformation.getVersionName();
        }
        addString(metricEvent, "AppVersion", str);
        addString(metricEvent, "CountryCode", countryCode);
        addString(metricEvent, "MarketplaceIDCode", this.marketplaceAuthority.getMarketplace().name());
        addString(metricEvent, "OSType", osType);
        addString(metricEvent, "AppVersion:MarketplaceIDCode", AlexaMetricsUtils.concatValues(str, this.marketplaceAuthority.getMarketplace().name()));
        addString(metricEvent, "AppVersion:CountryCode", AlexaMetricsUtils.concatValues(str, countryCode));
        addString(metricEvent, "CountryCode:MarketplaceIDCode", AlexaMetricsUtils.concatValues(countryCode, this.marketplaceAuthority.getMarketplace().name()));
        addString(metricEvent, "AppVersion:OSType", AlexaMetricsUtils.concatValues(str, osType));
        addString(metricEvent, "AppVersion:CountryCode:MarketplaceIDCode", AlexaMetricsUtils.concatValues(str, countryCode, this.marketplaceAuthority.getMarketplace().name()));
        Map<String, Object> customEntries = defaultAlexaMetricsEvent.getCustomEntries();
        if (customEntries != null) {
            for (String str2 : customEntries.keySet()) {
                Object obj = customEntries.get(str2);
                if (obj != null) {
                    addString(metricEvent, str2, String.valueOf(obj));
                }
            }
        }
    }

    private void addString(MetricEvent metricEvent, String str, String str2) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            if (str2 != null && !TextUtils.isEmpty(str2.trim())) {
                metricEvent.addString(str, str2);
                return;
            } else {
                GeneratedOutlineSupport1.outline164("Refusing to add blank value as metadata for ", str, TAG);
                return;
            }
        }
        throw new IllegalArgumentException("Refusing to add metric metadata with blank key.");
    }

    private double getCounterValue(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        if (defaultAlexaMetricsEvent instanceof MetricsCounter) {
            return ((MetricsCounter) defaultAlexaMetricsEvent).getCount();
        }
        Double asDouble = AlexaMetricsUtils.getAsDouble(defaultAlexaMetricsEvent.getCustomEntries(), "CounterValue");
        if (asDouble != null) {
            return asDouble.doubleValue();
        }
        return 1.0d;
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onBeginSession() {
        onRecordEvent(new DefaultAlexaMetricsEvent(AlexaMetricsConstants.MetricEvents.BEGIN_SESSION, AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null));
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onEndSession() {
        onRecordEvent(new DefaultAlexaMetricsEvent("END_SESSION", AlexaMetricsConstants.MetricsComponents.ALEXA_VOICE_SERVICE, null));
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onInitialize() {
        this.metrics = this.metricsFactoryProvider.mo358get();
    }

    @Override // com.amazon.alexa.client.metrics.core.AsyncMetricsConnector
    protected void onRecordEvent(DefaultAlexaMetricsEvent defaultAlexaMetricsEvent) {
        MetricEvent createMetricEvent = this.metrics.createMetricEvent(getServiceName(defaultAlexaMetricsEvent), defaultAlexaMetricsEvent.getComponentName());
        addMetadata(defaultAlexaMetricsEvent, createMetricEvent);
        if (defaultAlexaMetricsEvent instanceof MetricsTimer) {
            Object obj = defaultAlexaMetricsEvent.getCustomEntries().get("RecordTimerEnd");
            if (obj != null && (obj instanceof Long)) {
                ((MetricsTimer) defaultAlexaMetricsEvent).finishTimer(Long.valueOf(((Long) obj).longValue()));
            } else {
                ((MetricsTimer) defaultAlexaMetricsEvent).finishTimer();
            }
            createMetricEvent.addTimer(defaultAlexaMetricsEvent.getEventName(), ((MetricsTimer) defaultAlexaMetricsEvent).getElapsedTime());
        } else {
            Double asDouble = AlexaMetricsUtils.getAsDouble(defaultAlexaMetricsEvent.getCustomEntries(), "TimerValue");
            if (asDouble != null) {
                createMetricEvent.addTimer(defaultAlexaMetricsEvent.getEventName(), asDouble.doubleValue());
            } else {
                createMetricEvent.addCounter(defaultAlexaMetricsEvent.getEventName(), getCounterValue(defaultAlexaMetricsEvent));
            }
        }
        this.metrics.record(createMetricEvent, Priority.NORMAL, Channel.ANONYMOUS);
    }
}
