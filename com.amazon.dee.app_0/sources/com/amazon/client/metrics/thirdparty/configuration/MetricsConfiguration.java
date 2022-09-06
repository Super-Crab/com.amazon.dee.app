package com.amazon.client.metrics.thirdparty.configuration;

import com.amazon.alexa.featureservice.constants.FeatureConstants;
import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes11.dex */
public class MetricsConfiguration {
    public static final String BUILD_TYPE = "buildType";
    public static final String COUNTRY_OF_RESIDENCE = "countryOfResidence";
    public static final String CUSTOMER_ID = "CustomerId";
    public static final String DEVICE_ID = "deviceId";
    public static final String DEVICE_LANGUAGE = "deviceLanguage";
    public static final String DEVICE_MODE = "deviceMode";
    public static final String DEVICE_TYPE = "deviceType";
    public static final String HARDWARE = "hardware";
    public static final String IP_ADDRESS = "REMOTE_ADDR";
    public static final String LEGACY_DEVICE_ID = "oldDeviceId";
    protected static final String METRICS_SERVICE_NAME = "DeviceMetricsService";
    protected static final String METRIC_NON_TCOMM_SUFFIX = "_NonTComm";
    public static final String MODEL = "model";
    public static final String OS_FILE_TAG = "osFileTag";
    public static final String OTA_GROUP = "otaGroup";
    protected static final String PASSTHROUGH_SETTINGS_PREFIX = "PASSTHROUGH_";
    public static final String PLATFORM = "platform";
    public static final String PREFERRED_MARKETPLACE = "MarketplaceID";
    public static final String PROTOCOL_BUFFER_CODEC_FORMAT_HEADER = "x-codec-format";
    public static final String PROTOCOL_BUFFER_CODEC_VERSION_HEADER = "x-codec-version";
    public static final String RSM_GROUP = "remoteSettingsGroup";
    public static final String SESSION_ID = "Session";
    public static final String SOFTWARE_VERSION = "softwareVersion";
    public static final String USER_AGENT = "HTTP_USER_AGENT";
    private final BatchQueueConfiguration mBatchQueueConfiguration;
    private CodecConfiguration mCodecConfiguration;
    private Domain mDomain;
    private final HttpConfiguration mHttpConfiguration;
    private NetworkConfiguration mNetworkConfiguration;
    private Map<PriorityChannelPair, BatchPipelineConfiguration> mPipelineConfigurationMap;
    protected static MetricsBatchPipelineConfiguration sPassThroughNormalPriorityPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.NON_VOLATILE, "", 10000, 500, 10, 65536, 5242880, 65536, 604800000, 86400000, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS, BatchTransmitterType.PERIODIC);
    protected static MetricsBatchPipelineConfiguration sPassThroughHighPriorityPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.SEMI_VOLATILE, "", 1000, 500, 1, 65536, 1048576, 65536, 604800000, 86400000, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS, BatchTransmitterType.URGENT);

    /* renamed from: com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$Priority = new int[Priority.values().length];

        static {
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Priority[Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$Priority[Priority.RESERVED_FOR_LOCATION_SERVICE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes11.dex */
    public enum Domain {
        DEVO("devo"),
        MASTER("master"),
        PROD("prod");
        
        private String mName;

        Domain(String str) {
            this.mName = str;
        }

        public String getName() {
            return this.mName;
        }
    }

    @Deprecated
    public MetricsConfiguration(NetworkConfiguration networkConfiguration, BatchQueueConfiguration batchQueueConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map map) throws MetricsConfigurationException {
        if (networkConfiguration != null) {
            if (batchQueueConfiguration == null) {
                throw new MetricsConfigurationException("BatchQueueConfiguration is null");
            }
            if (codecConfiguration == null) {
                throw new MetricsConfigurationException("CodecConfiguration is null");
            }
            if (httpConfiguration != null) {
                if (map != null && !map.isEmpty()) {
                    this.mNetworkConfiguration = networkConfiguration;
                    this.mBatchQueueConfiguration = batchQueueConfiguration;
                    this.mCodecConfiguration = codecConfiguration;
                    this.mHttpConfiguration = httpConfiguration;
                    this.mPipelineConfigurationMap = sanitizeBatchPipelineConfigurationMap(map);
                    for (PriorityChannelPair priorityChannelPair : this.mPipelineConfigurationMap.keySet()) {
                        if (this.mPipelineConfigurationMap.get(priorityChannelPair).getBatchQueueType() == null) {
                            this.mPipelineConfigurationMap.get(priorityChannelPair).setBatchQueueType(this.mBatchQueueConfiguration.getBatchQueueType());
                            this.mPipelineConfigurationMap.get(priorityChannelPair).setDirectoryPrefix(this.mBatchQueueConfiguration.getDirectoryPrefix());
                        }
                    }
                    return;
                }
                throw new MetricsConfigurationException("PipelineConfiguration map is null or empty");
            }
            throw new MetricsConfigurationException("HttpConfiguration is null");
        }
        throw new MetricsConfigurationException("NetworkConfiguration is null");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isPipelineConfigurationMapPriorityBased(java.util.Map r4) {
        /*
            r3 = this;
            java.util.Set r0 = r4.keySet()
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L20
            java.lang.Object r1 = r0.next()
            boolean r2 = r1 instanceof com.amazon.client.metrics.thirdparty.Priority
            if (r2 == 0) goto L1e
            java.lang.Object r1 = r4.get(r1)
            boolean r1 = r1 instanceof com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
            if (r1 != 0) goto L8
        L1e:
            r4 = 0
            return r4
        L20:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration.isPipelineConfigurationMapPriorityBased(java.util.Map):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isPipelineConfigurationMapPriorityChannelPairBased(java.util.Map r4) {
        /*
            r3 = this;
            java.util.Set r0 = r4.keySet()
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L20
            java.lang.Object r1 = r0.next()
            boolean r2 = r1 instanceof com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair
            if (r2 == 0) goto L1e
            java.lang.Object r1 = r4.get(r1)
            boolean r1 = r1 instanceof com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration
            if (r1 != 0) goto L8
        L1e:
            r4 = 0
            return r4
        L20:
            r4 = 1
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration.isPipelineConfigurationMapPriorityChannelPairBased(java.util.Map):boolean");
    }

    private static void removeNormalAndHightPriorityConfigurations(Map<PriorityChannelPair, BatchPipelineConfiguration> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<PriorityChannelPair, BatchPipelineConfiguration>> it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<PriorityChannelPair, BatchPipelineConfiguration> next = it2.next();
            if (Priority.NORMAL.equals(next.getKey().getPriority()) || Priority.HIGH.equals(next.getKey().getPriority())) {
                it2.remove();
            }
        }
    }

    private Map<PriorityChannelPair, BatchPipelineConfiguration> sanitizeBatchPipelineConfigurationMap(Map map) throws MetricsConfigurationException {
        PriorityChannelPair priorityChannelPair;
        if (isPipelineConfigurationMapPriorityChannelPairBased(map)) {
            return map;
        }
        if (isPipelineConfigurationMapPriorityBased(map)) {
            HashMap hashMap = new HashMap();
            for (Object obj : map.keySet()) {
                Priority priority = (Priority) obj;
                int ordinal = priority.ordinal();
                if (ordinal == 2) {
                    priorityChannelPair = new PriorityChannelPair(Priority.NORMAL, Channel.LOCATION);
                } else if (ordinal != 3) {
                    priorityChannelPair = new PriorityChannelPair(priority, Channel.ANONYMOUS);
                } else {
                    priorityChannelPair = new PriorityChannelPair(Priority.NORMAL, Channel.NON_ANONYMOUS);
                }
                hashMap.put(priorityChannelPair, (BatchPipelineConfiguration) map.get(obj));
            }
            return hashMap;
        }
        throw new MetricsConfigurationException("Invalid Batch Pipeline Configuration");
    }

    @Deprecated
    public BatchQueueConfiguration getBatchQueueConfiguration() {
        return this.mBatchQueueConfiguration;
    }

    public String getBatchQueueDirectoryName(PriorityChannelPair priorityChannelPair) {
        String str = this.mPipelineConfigurationMap.get(priorityChannelPair).getDirectoryPrefix() + priorityChannelPair.getPriority() + "_" + priorityChannelPair.getChannel();
        return getNetworkConfiguration().getTransportType().equals(TransportType.OUTPUT_STREAM) ? GeneratedOutlineSupport1.outline75(PASSTHROUGH_SETTINGS_PREFIX, str, METRIC_NON_TCOMM_SUFFIX) : str;
    }

    public CodecConfiguration getCodecConfiguration() {
        return this.mCodecConfiguration;
    }

    public HttpConfiguration getHttpConfiguration() {
        return this.mHttpConfiguration;
    }

    public NetworkConfiguration getNetworkConfiguration() {
        return this.mNetworkConfiguration;
    }

    public BatchPipelineConfiguration getPipelineConfiguration(PriorityChannelPair priorityChannelPair) {
        return this.mPipelineConfigurationMap.get(priorityChannelPair);
    }

    public void setPassThroughMode() throws MetricsConfigurationException {
        Channel[] values;
        this.mNetworkConfiguration = new MetricsNetworkConfiguration(TransportType.OUTPUT_STREAM, this.mNetworkConfiguration.getNetworkTypes());
        this.mCodecConfiguration = new CodecConfiguration(CodecType.STRING, "1.0");
        removeNormalAndHightPriorityConfigurations(this.mPipelineConfigurationMap);
        for (Channel channel : Channel.values()) {
            this.mPipelineConfigurationMap.put(new PriorityChannelPair(Priority.NORMAL, channel), sPassThroughNormalPriorityPipelineConfiguration);
            this.mPipelineConfigurationMap.put(new PriorityChannelPair(Priority.HIGH, channel), sPassThroughHighPriorityPipelineConfiguration);
        }
    }

    public MetricsConfiguration(NetworkConfiguration networkConfiguration, CodecConfiguration codecConfiguration, HttpConfiguration httpConfiguration, Map map) throws MetricsConfigurationException {
        if (networkConfiguration != null) {
            if (codecConfiguration == null) {
                throw new MetricsConfigurationException("CodecConfiguration is null");
            }
            if (httpConfiguration != null) {
                if (map != null && !map.isEmpty()) {
                    this.mNetworkConfiguration = networkConfiguration;
                    this.mCodecConfiguration = codecConfiguration;
                    this.mHttpConfiguration = httpConfiguration;
                    this.mPipelineConfigurationMap = sanitizeBatchPipelineConfigurationMap(map);
                    BatchPipelineConfiguration batchPipelineConfiguration = this.mPipelineConfigurationMap.get(new PriorityChannelPair(Priority.NORMAL, Channel.ANONYMOUS));
                    if (batchPipelineConfiguration != null) {
                        this.mBatchQueueConfiguration = new BatchQueueConfiguration(batchPipelineConfiguration.getBatchQueueType(), batchPipelineConfiguration.getDirectoryPrefix());
                        return;
                    }
                    BatchPipelineConfiguration next = this.mPipelineConfigurationMap.values().iterator().next();
                    this.mBatchQueueConfiguration = new BatchQueueConfiguration(next.getBatchQueueType(), next.getDirectoryPrefix());
                    return;
                }
                throw new MetricsConfigurationException("PipelineConfiguration map is null or empty");
            }
            throw new MetricsConfigurationException("HttpConfiguration is null");
        }
        throw new MetricsConfigurationException("NetworkConfiguration is null");
    }
}
