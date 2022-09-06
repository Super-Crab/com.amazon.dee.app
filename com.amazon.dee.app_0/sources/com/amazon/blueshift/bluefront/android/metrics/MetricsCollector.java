package com.amazon.blueshift.bluefront.android.metrics;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.MetricsException;
import com.amazon.client.metrics.thirdparty.MetricsFactory;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.configuration.BatchQueueType;
import com.amazon.client.metrics.thirdparty.configuration.BatchTransmitterType;
import com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.CodecType;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsBatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.thirdparty.configuration.MetricsNetworkConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.NetworkType;
import com.amazon.client.metrics.thirdparty.configuration.TransportType;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class MetricsCollector {
    private static final String BLUEFRONT_ANDROID_DEVICE_TYPE = "A3FX1PXTNGXI6A";
    private static final String BLUE_FRONT_DIRECTORY_PREFIX = "BluefrontAndroid";
    private static final String CLIENT_SPECIFIC_FORMAT = "%s_%s";
    private static final String DEFAULT_DEVICE_ID = "Anonymous";
    private static final String PROGRAM = "BluefrontAndroid";
    private static final String SOURCE = "BFSpeechClient";
    private int mAutomaticEndpointingUsed;
    private int mClientConnectionFailed;
    private int mFailedRequest;
    private AtomicBoolean mIsPublished;
    private final MetricEvent mMetricEvent;
    private final MetricsFactory mMetricsFactory;
    private int mRequestCancelled;
    private int mRequestError;
    private static AtomicBoolean sMetricsConfigured = new AtomicBoolean(false);
    private static SpeechMetricsConfiguration sMetricsConfiguration = SpeechMetricsConfiguration.CONFIGURE_METRICS_EXTERNALLY;
    private static final String TAG = MetricsCollector.class.getCanonicalName();

    /* renamed from: com.amazon.blueshift.bluefront.android.metrics.MetricsCollector$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$blueshift$bluefront$android$metrics$SpeechMetricsConfiguration = new int[SpeechMetricsConfiguration.values().length];

        static {
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$metrics$SpeechMetricsConfiguration[SpeechMetricsConfiguration.CONFIGURE_METRICS_INTERNALLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$metrics$SpeechMetricsConfiguration[SpeechMetricsConfiguration.CONFIGURE_METRICS_EXTERNALLY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$blueshift$bluefront$android$metrics$SpeechMetricsConfiguration[SpeechMetricsConfiguration.DISABLE_METRICS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public MetricsCollector(Context context, Optional<String> optional) {
        this(context, optional, AndroidMetricsFactoryImpl.getInstance(context));
    }

    public static void configure(SpeechMetricsConfiguration speechMetricsConfiguration) {
        Preconditions.checkNotNull(speechMetricsConfiguration, "metricsConfiguration cannot be null");
        sMetricsConfiguration = speechMetricsConfiguration;
    }

    private static void configureMetricsFactory(Context context, String str) {
        int ordinal = sMetricsConfiguration.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                sMetricsConfigured.set(false);
                return;
            } else {
                sMetricsConfigured.set(true);
                return;
            }
        }
        if (!sMetricsConfigured.getAndSet(true)) {
            AndroidMetricsFactoryImpl.setMetricsConfiguration(createMetricsConfiguration());
            AndroidMetricsFactoryImpl.setDeviceType(context, BLUEFRONT_ANDROID_DEVICE_TYPE);
        }
        AndroidMetricsFactoryImpl.setDeviceId(context, str);
    }

    private MetricEvent createClientSpecificEvent() {
        MetricEvent createMetricEvent = this.mMetricsFactory.createMetricEvent("BluefrontAndroid", String.format(CLIENT_SPECIFIC_FORMAT, SOURCE, getLibraryVersion()));
        try {
            createMetricEvent.addDataPoints(this.mMetricEvent.getAsDataPoints());
        } catch (MetricsException unused) {
            Log.w(TAG, "An invalid data point was detected while creating client specific event.");
        }
        return createMetricEvent;
    }

    private static MetricsConfiguration createMetricsConfiguration() {
        try {
            TransportType transportType = TransportType.HTTP;
            HashSet hashSet = new HashSet();
            hashSet.add(NetworkType.WIFI);
            hashSet.add(NetworkType.ETHERNET);
            hashSet.add(NetworkType.WAN);
            MetricsNetworkConfiguration metricsNetworkConfiguration = new MetricsNetworkConfiguration(transportType, hashSet);
            CodecConfiguration codecConfiguration = new CodecConfiguration(CodecType.PROTOCOL_BUFFERS, "1.0");
            HttpConfiguration httpConfiguration = new HttpConfiguration(HttpRequestSignerType.OAUTH, "https://device-metrics-us.amazon.com:443", "https://device-metrics-us-2.amazon.com:443");
            HashMap hashMap = new HashMap();
            hashMap.put(new PriorityChannelPair(Priority.NORMAL, Channel.ANONYMOUS), new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, "BluefrontAndroid", 300000L, 180000L, 65536, 65536, 5242880, 65536, 604800000L, 86400000L, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS, BatchTransmitterType.PERIODIC));
            hashMap.put(new PriorityChannelPair(Priority.HIGH, Channel.ANONYMOUS), new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, "", 1000L, 500L, 65536, 65536, 1048576, 65536, 604800000L, 86400000L, 300000L, BatchTransmitterType.URGENT));
            return new MetricsConfiguration(metricsNetworkConfiguration, codecConfiguration, httpConfiguration, hashMap);
        } catch (MetricsConfigurationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private String getLibraryVersion() {
        return "1.3";
    }

    public void addMetadata(MetricKey metricKey, String str) {
        Preconditions.checkNotNull(metricKey, "key cannot be null");
        Preconditions.checkNotNull(str, "value cannot be null");
        addMetadata(metricKey.toString(), str);
    }

    public void cancelTimer(MetricKey metricKey) {
        Preconditions.checkNotNull(metricKey, "name cannot be null");
        cancelTimer(metricKey.toString());
    }

    public void publish() {
        if (this.mIsPublished.getAndSet(true) || !sMetricsConfigured.get()) {
            return;
        }
        this.mMetricEvent.addCounter(MetricKey.CONNECTION_FAILED.toString(), this.mClientConnectionFailed);
        this.mMetricEvent.addCounter(MetricKey.REQUEST_CANCELLED.toString(), this.mRequestCancelled);
        this.mMetricEvent.addCounter(MetricKey.REQUEST_RETURNED_WITH_ERROR.toString(), this.mRequestError);
        this.mMetricEvent.addCounter(MetricKey.AUTOMATIC_ENDPOINTING_USED.toString(), this.mAutomaticEndpointingUsed);
        this.mMetricEvent.addCounter(MetricKey.REQUEST_FAILED.toString(), this.mFailedRequest);
        MetricEvent createClientSpecificEvent = createClientSpecificEvent();
        this.mMetricsFactory.record(this.mMetricEvent, Priority.HIGH);
        this.mMetricsFactory.record(createClientSpecificEvent, Priority.HIGH);
    }

    public void requestAutomaticallyEndpointed() {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mAutomaticEndpointingUsed = 1;
    }

    public void requestCancelled() {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mRequestCancelled = 1;
    }

    public void requestConnectionFailed() {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mClientConnectionFailed = 1;
        this.mFailedRequest = 1;
    }

    public void requestReturnedError() {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mRequestError = 1;
        this.mFailedRequest = 1;
    }

    public void startTimer(MetricKey metricKey) {
        Preconditions.checkNotNull(metricKey, "name cannot be null");
        startTimer(metricKey.toString());
    }

    public void stopTimer(MetricKey metricKey) {
        Preconditions.checkNotNull(metricKey, "name cannot be null");
        stopTimer(metricKey.toString());
    }

    @VisibleForTesting
    MetricsCollector(Context context, Optional<String> optional, MetricsFactory metricsFactory) {
        Preconditions.checkNotNull(context, "Application context must not be null.");
        Preconditions.checkNotNull(optional, "DeviceId must not be null or empty.");
        this.mIsPublished = new AtomicBoolean(false);
        this.mMetricsFactory = metricsFactory;
        this.mRequestCancelled = 0;
        this.mClientConnectionFailed = 0;
        this.mRequestError = 0;
        this.mAutomaticEndpointingUsed = 0;
        this.mFailedRequest = 0;
        configureMetricsFactory(context, optional.or((Optional<String>) DEFAULT_DEVICE_ID));
        this.mMetricEvent = this.mMetricsFactory.createConcurrentMetricEvent("BluefrontAndroid", SOURCE);
        addMetadata(MetricKey.LIBRARY_VERSION, getLibraryVersion());
    }

    private void cancelTimer(String str) {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mMetricEvent.removeTimer(str);
    }

    private void startTimer(String str) {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mMetricEvent.startTimer(str);
    }

    private void stopTimer(String str) {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mMetricEvent.stopTimer(str);
    }

    private void addMetadata(String str, String str2) {
        if (this.mIsPublished.get()) {
            return;
        }
        this.mMetricEvent.addString(str, str2);
    }
}
