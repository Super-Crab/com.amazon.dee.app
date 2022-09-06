package com.amazon.client.metrics.thirdparty;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.batch.creator.BatchCreator;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.batch.queue.ByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.NonVolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.SemiVolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.queue.VolatileBoundedByteArrayQueue;
import com.amazon.client.metrics.thirdparty.batch.transmitter.BatchTransmitter;
import com.amazon.client.metrics.thirdparty.batch.transmitter.PeriodicBatchTransmitter;
import com.amazon.client.metrics.thirdparty.batch.transmitter.UploadResultBroadcaster;
import com.amazon.client.metrics.thirdparty.batch.transmitter.UrgentBatchTransmitter;
import com.amazon.client.metrics.thirdparty.codec.MetricBatchProtocolBuffersCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricBatchToStringCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricEntryProtocolBuffersCodec;
import com.amazon.client.metrics.thirdparty.codec.MetricEntryToStringCodec;
import com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.BatchQueueType;
import com.amazon.client.metrics.thirdparty.configuration.BatchTransmitterType;
import com.amazon.client.metrics.thirdparty.configuration.CodecType;
import com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationHelper;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationParser;
import com.amazon.client.metrics.thirdparty.configuration.TransportType;
import com.amazon.client.metrics.thirdparty.transport.MAPMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.MetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
import com.amazon.client.metrics.thirdparty.transport.OutputStreamMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.ProvidedOAuthMetricsTransport;
import com.amazon.client.metrics.thirdparty.transport.StaticCredentialMetricsTransport;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public class BaseMetricsServiceFactory {
    private static final String BATCH_TYPE_CRITICAL = "critical";
    private static final String CRITICAL_BATCH_SOURCE_APP = "app";
    private static final String KEY_CRITICAL_METRIC_BATCH_SOURCE = "criticalMetricBatchSource";
    private static final String KEY_METRIC_BATCH_TYPE = "metricBatchType";
    protected static final String METRIC_LOG_FILE_NAME = "metric-log";
    protected static final int PERIODIC_METRIC_REPORTER_PERIOD_MINS = 5;
    protected static final String PREF_USE_PASS_THROUGH_MODE = "PREF_USE_PASS_THROUGH_MODE";
    public static final String PRIORITY_CHANNEL_DELIMITER = "_";
    public static final String PROGRAM_NAME_METRICS_SERVICE = "MetricsService";
    public static final String SOURCE_NAME_RECORD_METRIC = "RecordMetric";
    protected static final String TRANSPORT_PREF_FILENAME = "transport-preferences";
    protected static final DPLogger log = new DPLogger("BaseMetricsServiceFactory");
    protected Context mContext;
    protected DelegatingOAuthHelper mDelegatingOAuthHelper;
    protected final DeviceUtil mDeviceUtil;
    protected MetricsConfiguration mMetricsConfiguration;
    protected MetricsFactory mMetricsFactory;
    protected MetricsTransport mMetricsTransport;
    protected PeriodicMetricReporter mPeriodicMetricReporter;
    protected long mTransmissionOffsetMillis;

    /* renamed from: com.amazon.client.metrics.thirdparty.BaseMetricsServiceFactory$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$Channel;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType = new int[BatchTransmitterType.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType;

        static {
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType[BatchTransmitterType.PERIODIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchTransmitterType[BatchTransmitterType.URGENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType = new int[BatchQueueType.values().length];
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[BatchQueueType.VOLATILE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[BatchQueueType.NON_VOLATILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$BatchQueueType[BatchQueueType.SEMI_VOLATILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType = new int[CodecType.values().length];
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType[CodecType.PROTOCOL_BUFFERS.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$CodecType[CodecType.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType = new int[TransportType.values().length];
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType[TransportType.HTTP.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$TransportType[TransportType.OUTPUT_STREAM.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType = new int[HttpRequestSignerType.values().length];
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.OAUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP_OAUTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            $SwitchMap$com$amazon$client$metrics$thirdparty$Channel = new int[Channel.values().length];
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Channel[Channel.ANONYMOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Channel[Channel.NON_ANONYMOUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Channel[Channel.LOCATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes11.dex */
    public static class DelegatingOAuthHelper implements OAuthHelper {
        public OAuthHelper mInnerHelper;

        protected DelegatingOAuthHelper() {
        }

        @Override // com.amazon.client.metrics.thirdparty.transport.OAuthHelper
        public String getAccessToken() throws Exception {
            OAuthHelper oAuthHelper = this.mInnerHelper;
            if (oAuthHelper == null) {
                return null;
            }
            return oAuthHelper.getAccessToken();
        }

        public void setInnerHelper(OAuthHelper oAuthHelper) {
            this.mInnerHelper = oAuthHelper;
        }
    }

    public BaseMetricsServiceFactory(Context context, DeviceUtil deviceUtil) throws MetricsConfigurationException {
        this(context, deviceUtil, null);
    }

    protected long addOffset(long j, long j2) {
        long j3 = j2 % j;
        return j3 < j / 2 ? j3 + j : j3;
    }

    protected BatchCreator createBatchCreator(ByteArrayQueue byteArrayQueue, PriorityChannelPair priorityChannelPair, DeviceInfoManager deviceInfoManager) throws CodecException {
        BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(priorityChannelPair);
        UserAgentHelper userAgentHelper = new UserAgentHelper(this.mContext, deviceInfoManager);
        int ordinal = this.mMetricsConfiguration.getCodecConfiguration().getCodecType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new BatchCreator(byteArrayQueue, new MetricBatchToStringCodec(), new MetricEntryToStringCodec(), batchPipelineConfiguration, this.mPeriodicMetricReporter, deviceInfoManager, userAgentHelper);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported CodecType: ");
            outline107.append(this.mMetricsConfiguration.getCodecConfiguration().getCodecType());
            throw new IllegalArgumentException(outline107.toString());
        }
        return new BatchCreator(byteArrayQueue, new MetricBatchProtocolBuffersCodec(), new MetricEntryProtocolBuffersCodec(), batchPipelineConfiguration, this.mPeriodicMetricReporter, deviceInfoManager, userAgentHelper);
    }

    protected ByteArrayQueue createBatchQueue(PriorityChannelPair priorityChannelPair) throws IOException {
        BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(priorityChannelPair);
        int ordinal = this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair).getBatchQueueType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                File dir = this.mContext.getDir(this.mMetricsConfiguration.getBatchQueueDirectoryName(priorityChannelPair), 0);
                if (dir != null && dir.isDirectory()) {
                    return new NonVolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter, dir);
                }
                String format = String.format("Failed to create batch directory for non-volatile queue. No metrics will be recorded for Channel: %s and Priority: %s in the NonVolatile queue. Falling back to VolatileQueue", priorityChannelPair.getPriority().name(), priorityChannelPair.getChannel().name());
                log.error("createBatchQueue", format, new Object[0]);
                Log.wtf("createBatchQueue", format);
                return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
            } else if (ordinal == 2) {
                File dir2 = this.mContext.getDir(this.mMetricsConfiguration.getBatchQueueDirectoryName(priorityChannelPair), 0);
                if (dir2 != null && dir2.isDirectory()) {
                    return new SemiVolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter, dir2);
                }
                String format2 = String.format("Failed to create batch directory for semi-volatile queue. No metrics will be recorded for Channel: %s and Priority: %s in the SemiVolatile queue. Falling back to VolatileQueue", priorityChannelPair.getPriority().name(), priorityChannelPair.getChannel().name());
                log.error("createBatchQueue", format2, new Object[0]);
                Log.wtf("createBatchQueue", format2);
                return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported BatchQueueType: ");
                outline107.append(this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair).getBatchQueueType());
                throw new IllegalArgumentException(outline107.toString());
            }
        }
        return new VolatileBoundedByteArrayQueue(batchPipelineConfiguration, this.mPeriodicMetricReporter);
    }

    protected BatchTransmitter createBatchTransmitter(ByteArrayQueue byteArrayQueue, MetricsTransport metricsTransport, UploadResultBroadcaster uploadResultBroadcaster, BatchPipelineConfiguration batchPipelineConfiguration, PriorityChannelPair priorityChannelPair) {
        long addOffset = addOffset(batchPipelineConfiguration.getTransmissionPeriodMillis(), this.mTransmissionOffsetMillis);
        int ordinal = batchPipelineConfiguration.getBatchTransmitterType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 2) {
                return new UrgentBatchTransmitter(byteArrayQueue, metricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, this.mPeriodicMetricReporter, addOffset, this.mContext);
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported PeriodicBatchTransmitterType ");
            outline107.append(batchPipelineConfiguration.getBatchTransmitterType());
            throw new IllegalArgumentException(outline107.toString());
        }
        return new PeriodicBatchTransmitter(byteArrayQueue, metricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, this.mPeriodicMetricReporter, addOffset, this.mContext);
    }

    protected DeviceInfoManager createDeviceInfoManager(Priority priority, Channel channel) {
        DeviceInfoManager rotatingDeviceInfoManager;
        int ordinal = channel.ordinal();
        if (ordinal == 0) {
            rotatingDeviceInfoManager = new RotatingDeviceInfoManager(this.mContext, this.mDeviceUtil, DeviceInfoManagerConstants.LOCATION_DSN_UUID_KEY, DeviceInfoManagerConstants.LOCATION_SESSION_ID_KEY, DeviceInfoManagerConstants.LOCATION_CUSTOMER_ID_KEY);
        } else if (ordinal == 1) {
            rotatingDeviceInfoManager = new OverridingDeviceInfoManager(this.mContext, this.mDeviceUtil, DeviceInfoManagerConstants.ANONYMOUS_DSN_UUID_KEY, DeviceInfoManagerConstants.ANONYMOUS_SESSION_ID_KEY, DeviceInfoManagerConstants.ANONYMOUS_CUSTOMER_ID_KEY, false);
        } else if (ordinal != 2) {
            rotatingDeviceInfoManager = new AndroidDeviceInfoManager(this.mDeviceUtil);
        } else {
            rotatingDeviceInfoManager = new OverridingDeviceInfoManager(this.mContext, this.mDeviceUtil, DeviceInfoManagerConstants.NON_ANONYMOUS_DSN_UUID_KEY, DeviceInfoManagerConstants.NON_ANONYMOUS_SESSION_ID_KEY, DeviceInfoManagerConstants.NON_ANONYMOUS_CUSTOMER_ID_KEY, true);
        }
        if (Priority.CRITICAL.equals(priority)) {
            rotatingDeviceInfoManager.addToMetricsDeviceInfo(KEY_METRIC_BATCH_TYPE, "critical");
            rotatingDeviceInfoManager.addToMetricsDeviceInfo(KEY_CRITICAL_METRIC_BATCH_SOURCE, "app");
        }
        return rotatingDeviceInfoManager;
    }

    protected Map<PriorityChannelPair, BatchCreator> createExtraBatchCreators() {
        return Collections.emptyMap();
    }

    protected MetricsTransport createMAPMetricsTransport(MetricsTransport metricsTransport) {
        return new MAPMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, metricsTransport);
    }

    protected MetricsConfigurationParser createMetricsConfigurationParser() {
        return new MetricsConfigurationParser();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MetricsService createMetricsService() {
        int i;
        int i2;
        try {
            UploadResultBroadcaster uploadResultBroadcaster = new UploadResultBroadcaster(this.mContext);
            HashMap hashMap = new HashMap();
            ArrayList<BatchTransmitter> arrayList = new ArrayList(Priority.values().length);
            this.mMetricsTransport = createMetricsTransport();
            HashSet<Priority> hashSet = new HashSet(3);
            hashSet.add(Priority.HIGH);
            hashSet.add(Priority.NORMAL);
            hashSet.add(Priority.CRITICAL);
            for (Priority priority : hashSet) {
                Channel[] values = Channel.values();
                int length = values.length;
                int i3 = 0;
                while (i3 < length) {
                    Channel channel = values[i3];
                    BatchPipelineConfiguration batchPipelineConfiguration = getBatchPipelineConfiguration(new PriorityChannelPair(priority, channel));
                    if (batchPipelineConfiguration == null) {
                        log.warn("createMetricsService", String.format("Skipping batch pipeline setup for Priority %s and Channel %s because no configuration is provided.", priority, channel), new Object[0]);
                        i = i3;
                        i2 = length;
                    } else {
                        ByteArrayQueue createBatchQueue = createBatchQueue(new PriorityChannelPair(priority, channel));
                        createBatchQueue.setQueueName(priority.name() + "_" + channel.name());
                        BatchCreator createBatchCreator = createBatchCreator(createBatchQueue, new PriorityChannelPair(priority, channel), createDeviceInfoManager(priority, channel));
                        PriorityChannelPair priorityChannelPair = new PriorityChannelPair(priority, channel);
                        hashMap.put(priorityChannelPair, createBatchCreator);
                        i = i3;
                        i2 = length;
                        arrayList.add(createBatchTransmitter(createBatchQueue, this.mMetricsTransport, uploadResultBroadcaster, batchPipelineConfiguration, priorityChannelPair));
                    }
                    i3 = i + 1;
                    length = i2;
                }
            }
            hashMap.putAll(createExtraBatchCreators());
            log.verbose("createMetricsService", "Triggering intial push for stored metrics on service startup", new Object[0]);
            for (BatchTransmitter batchTransmitter : arrayList) {
                batchTransmitter.transmitBatches(false);
            }
            return new MetricsService(this.mDeviceUtil, hashMap, arrayList);
        } catch (CodecException e) {
            log.error("createMetricsService", "could not serialize device info", e);
            throw new RuntimeException(e);
        } catch (IOException e2) {
            log.error("createMetricsService", "'could not create batch queue", e2);
            throw new RuntimeException(e2);
        }
    }

    protected MetricsTransport createMetricsTransport() {
        StaticCredentialMetricsTransport staticCredentialMetricsTransport = new StaticCredentialMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, null);
        int ordinal = this.mMetricsConfiguration.getNetworkConfiguration().getTransportType().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                try {
                    return createOutputStreamMetricsTransport();
                } catch (IOException e) {
                    log.error("createMetricsTransport", "postInitialize failed", e);
                    throw new RuntimeException(e);
                }
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported TransportType: ");
            outline107.append(this.mMetricsConfiguration.getNetworkConfiguration().getTransportType());
            throw new IllegalArgumentException(outline107.toString());
        }
        int ordinal2 = this.mMetricsConfiguration.getHttpConfiguration().getHttpRequestSignerType().ordinal();
        if (ordinal2 == 0 || ordinal2 == 1) {
            return createMAPMetricsTransport(staticCredentialMetricsTransport);
        }
        if (ordinal2 == 2) {
            return createProvidedOAuthTransport(this.mDelegatingOAuthHelper, staticCredentialMetricsTransport);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unknown request signer type: ");
        outline1072.append(this.mMetricsConfiguration.getHttpConfiguration().getHttpRequestSignerType());
        throw new IllegalArgumentException(outline1072.toString());
    }

    protected MetricsTransport createOutputStreamMetricsTransport() throws IOException {
        File file = new File(this.mContext.getCacheDir(), METRIC_LOG_FILE_NAME);
        file.createNewFile();
        DPLogger dPLogger = log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Metric log file: ");
        outline107.append(file.getAbsolutePath());
        dPLogger.info("createOutputStreamMetricsService", outline107.toString(), new Object[0]);
        return new OutputStreamMetricsTransport(new BufferedOutputStream(new FileOutputStream(file)));
    }

    protected MetricsTransport createProvidedOAuthTransport(OAuthHelper oAuthHelper, MetricsTransport metricsTransport) {
        return new ProvidedOAuthMetricsTransport(this.mContext, this.mMetricsConfiguration, this.mDeviceUtil, oAuthHelper, metricsTransport);
    }

    protected BatchPipelineConfiguration getBatchPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return this.mMetricsConfiguration.getPipelineConfiguration(priorityChannelPair);
    }

    protected MetricsConfiguration getMetricsConfiguration() {
        return this.mMetricsConfiguration;
    }

    public PeriodicMetricReporter getPeriodicMetricReporter() {
        return this.mPeriodicMetricReporter;
    }

    protected String getSessionID() {
        log.debug("BaseMetricsServiceFactory_getSessionID", AlexaMetricsConstants.EventConstants.SESSION_ID, this.mDeviceUtil.fetchSessionID());
        return this.mDeviceUtil.fetchSessionID();
    }

    protected MetricsConfiguration initializeMetricsConfiguration(Context context) throws MetricsConfigurationException {
        return new MetricsConfigurationHelper(context).initializeMetricsConfiguration(createMetricsConfigurationParser());
    }

    public void setOAuthHelper(OAuthHelper oAuthHelper) {
        this.mDelegatingOAuthHelper.setInnerHelper(oAuthHelper);
    }

    protected boolean shouldUsePassThroughMode(Context context) {
        log.verbose("shouldUsePassThroughMode", "Looking up transport preferences at transport-preferences", new Object[0]);
        boolean z = context.getSharedPreferences(TRANSPORT_PREF_FILENAME, 0).getBoolean(PREF_USE_PASS_THROUGH_MODE, false);
        log.verbose("shouldUsePassThroughMode", "usePassThroughMode", Boolean.valueOf(z));
        return z;
    }

    public void shutdown() {
        PeriodicMetricReporter periodicMetricReporter = this.mPeriodicMetricReporter;
        if (periodicMetricReporter != null) {
            periodicMetricReporter.shutdown();
        }
    }

    public BaseMetricsServiceFactory(Context context, DeviceUtil deviceUtil, MetricsConfiguration metricsConfiguration) throws MetricsConfigurationException {
        if (context != null) {
            this.mContext = context;
            this.mDeviceUtil = deviceUtil;
            this.mMetricsConfiguration = metricsConfiguration == null ? initializeMetricsConfiguration(context) : metricsConfiguration;
            this.mDelegatingOAuthHelper = new DelegatingOAuthHelper();
            if (shouldUsePassThroughMode(context)) {
                this.mMetricsConfiguration.setPassThroughMode();
            }
            log.verbose("createMetricsService", "Getting MetricsFactory via AndroidMetricsFactoryImpl.getInstance(Context).", new Object[0]);
            this.mMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
            this.mPeriodicMetricReporter = new PeriodicMetricReporterImpl(this.mMetricsFactory, PROGRAM_NAME_METRICS_SERVICE, SOURCE_NAME_RECORD_METRIC);
            this.mPeriodicMetricReporter.startRecordingPeriodically(5L, TimeUnit.MINUTES);
            this.mTransmissionOffsetMillis = ((long) ((Math.random() * 9.223372036854776E18d) / 1000.0d)) * 1000;
            return;
        }
        throw new IllegalArgumentException("Context must not be null");
    }
}
