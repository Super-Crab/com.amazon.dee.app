package com.amazon.dee.app.services.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.features.FeatureQuery;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.dee.app.metrics.AlexaMetricsEvent;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import dagger.Lazy;
import java.util.Map;
/* loaded from: classes12.dex */
public class FeatureCheckedMetricsService implements MetricsService {
    private static final String TAG = Log.tag(FeatureCheckedMetricsService.class);
    private final CrashMetadata crashMetadata;
    private final Lazy<DCMMetricsConnector> dcmMetricsConnectorLazy;
    private final EnvironmentService environmentService;
    private final Lazy<FeatureQuery> featureQueryLazy;
    private final Lazy<KinesisMetricsConnector> kinesisMetricsConnectorLazy;
    private final Lazy<Mobilytics> mobilytics;
    private final Lazy<MobilyticsEventFactory> mobilyticsEventFactory;
    @VisibleForTesting
    MobilyticsMetricsService mobilyticsMetricsService = null;
    private final PersistentStorage.Factory persistentStorageFactory;
    private final Lazy<PreloadAttributionManager> preloadAttributionManagerLazy;

    public FeatureCheckedMetricsService(EnvironmentService environmentService, PersistentStorage.Factory factory, CrashMetadata crashMetadata, Lazy<PreloadAttributionManager> lazy, Lazy<DCMMetricsConnector> lazy2, Lazy<KinesisMetricsConnector> lazy3, Lazy<Mobilytics> lazy4, Lazy<MobilyticsEventFactory> lazy5, Lazy<FeatureQuery> lazy6) {
        this.environmentService = environmentService;
        this.persistentStorageFactory = factory;
        this.crashMetadata = crashMetadata;
        this.preloadAttributionManagerLazy = lazy;
        this.dcmMetricsConnectorLazy = lazy2;
        this.kinesisMetricsConnectorLazy = lazy3;
        this.mobilytics = lazy4;
        this.mobilyticsEventFactory = lazy5;
        this.featureQueryLazy = lazy6;
    }

    private MetricsService getMetricsService() {
        if (this.mobilyticsMetricsService == null) {
            this.mobilyticsMetricsService = new MobilyticsMetricsService(this.environmentService, this.persistentStorageFactory, this.crashMetadata, this.preloadAttributionManagerLazy, this.mobilytics, this.mobilyticsEventFactory);
            this.mobilyticsMetricsService.recordEvent(AlexaMetricsConstants.MetricEvents.MOBILYTICS_INITIALIZED, "Application", null);
        }
        return this.mobilyticsMetricsService;
    }

    @Override // com.dee.app.metrics.MetricsService
    public void beginSession() {
        getMetricsService().beginSession();
    }

    @Override // com.dee.app.metrics.MetricsService
    public void cancelTimer(String str) {
        getMetricsService().cancelTimer(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public AlexaMetricsEvent createEvent(String str, String str2, Map<String, Object> map) {
        return getMetricsService().createEvent(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void endSession() {
        getMetricsService().endSession();
    }

    @Override // com.dee.app.metrics.MetricsService
    public void incrementCounter(String str) {
        getMetricsService().incrementCounter(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void incrementCounterByValue(String str, int i) {
        getMetricsService().incrementCounterByValue(str, i);
    }

    @Override // com.dee.app.metrics.MetricsService
    public boolean invalidateEvent(String str) {
        return getMetricsService().invalidateEvent(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public boolean isOngoingEvent(String str) {
        return getMetricsService().isOngoingEvent(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void pauseSession() {
        getMetricsService().pauseSession();
    }

    @Override // com.dee.app.metrics.MetricsService
    public void pauseTimer(String str) {
        getMetricsService().pauseTimer(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordCounter(MetricsCounter metricsCounter) {
        getMetricsService().recordCounter(metricsCounter);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordCustom(String str, String str2, String str3, Map<String, Object> map) {
        getMetricsService().recordCustom(str, str2, str3, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordData(String str, String str2, String str3, Map<String, Object> map) {
        getMetricsService().recordData(str, str2, str3, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEngagement(String str, String str2, Map<String, Object> map) {
        getMetricsService().recordEngagement(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordError(String str, String str2, String str3, Map<String, Object> map) {
        getMetricsService().recordError(str, str2, str3, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(AlexaMetricsEvent alexaMetricsEvent) {
        getMetricsService().recordEvent(alexaMetricsEvent);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordImpression(String str, String str2, Map<String, Object> map) {
        getMetricsService().recordImpression(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    @Deprecated
    public void recordMonetization(String str, String str2, Map<String, Object> map) {
        getMetricsService().recordMonetization(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordOccurrence(String str, String str2, boolean z, @Nullable Map<String, Object> map) {
        getMetricsService().recordOccurrence(str, str2, z, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordPercentOccurrence(String str, String str2, boolean z, @Nullable Map<String, Object> map) {
        getMetricsService().recordPercentOccurrence(str, str2, z, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(MetricsTimer metricsTimer) {
        getMetricsService().recordTimer(metricsTimer);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimerAs(String str, String str2) {
        getMetricsService().recordTimerAs(str, str2);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void resumeSession() {
        getMetricsService().resumeSession();
    }

    @Override // com.dee.app.metrics.MetricsService
    public void resumeTimer(String str) {
        getMetricsService().resumeTimer(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public MetricsCounter startCounter(String str, String str2, Map<String, Object> map) {
        return getMetricsService().startCounter(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public MetricsTimer startTimer(String str, String str2, Map<String, Object> map) {
        return getMetricsService().startTimer(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(String str) {
        getMetricsService().recordEvent(str, "Application", null);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(String str) {
        getMetricsService().recordTimer(str);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordEvent(String str, @NonNull String str2, Map<String, Object> map) {
        getMetricsService().recordEvent(str, str2, map);
    }

    @Override // com.dee.app.metrics.MetricsService
    public void recordTimer(String str, Map<String, Object> map) {
        getMetricsService().recordTimer(str, map);
    }
}
