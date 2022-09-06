package com.amazon.client.metrics.common.configuration;

import android.util.Log;
import com.amazon.client.metrics.common.batch.creator.internal.PriorityChannelPairConverter;
import com.amazon.client.metrics.common.configuration.internal.IBatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration;
import com.amazon.client.metrics.common.configuration.internal.IHttpConfiguration;
import com.amazon.client.metrics.common.configuration.internal.IMetricsConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidBatchQueueConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidCodecConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidHttpConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsBatchPipelineConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidMetricsNetworkConfiguration;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes11.dex */
public class MetricsConfigurationConverter {
    public static Map<PriorityChannelPair, com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration> convertBatchPipelineConfigurationMap_fromCommonToThirdParty(Map<com.amazon.client.metrics.common.batch.creator.PriorityChannelPair, BatchPipelineConfiguration> map) {
        if (map == null) {
            Log.i("convertConfigMap", "Returning null for null Common Batch Pipeline Config Map Input");
            return null;
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry<com.amazon.client.metrics.common.batch.creator.PriorityChannelPair, BatchPipelineConfiguration> entry : map.entrySet()) {
            PriorityChannelPair convertCommonToThirdParty = PriorityChannelPairConverter.convertCommonToThirdParty(entry.getKey());
            com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration convertBatchPipelineConfiguration_fromCommonToThirdParty = convertBatchPipelineConfiguration_fromCommonToThirdParty(entry.getValue());
            if (convertCommonToThirdParty != null && convertBatchPipelineConfiguration_fromCommonToThirdParty != null) {
                hashMap.put(convertCommonToThirdParty, convertBatchPipelineConfiguration_fromCommonToThirdParty);
            }
        }
        return hashMap;
    }

    public static com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration convertBatchPipelineConfiguration_fromCommonToThirdParty(BatchPipelineConfiguration batchPipelineConfiguration) {
        if (batchPipelineConfiguration == null) {
            Log.i("convertPipelineConfig", "Returning null for null Common Batch Pipeline Config Input");
            return null;
        } else if (!(batchPipelineConfiguration instanceof MetricsBatchPipelineConfiguration)) {
            Log.i("convertPipelineConfig", "BatchPipelineConfig not instance of MetricsBatchPipelineConfig, returning null");
            return null;
        } else {
            BatchPipelineConfiguration delegateBatchPipelineConfiguration = ((MetricsBatchPipelineConfiguration) batchPipelineConfiguration).getDelegateBatchPipelineConfiguration();
            if (delegateBatchPipelineConfiguration instanceof AndroidMetricsBatchPipelineConfiguration) {
                return ((AndroidMetricsBatchPipelineConfiguration) delegateBatchPipelineConfiguration).getDelegateBatchPipelineConfiguration();
            }
            Log.i("convertPipelineConfig", "Returning null for Common BatchPipeline Config that does not delegate to ThirdParty");
            return null;
        }
    }

    public static BatchPipelineConfiguration convertBatchPipelineConfiguration_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.configuration.BatchPipelineConfiguration batchPipelineConfiguration) {
        if (batchPipelineConfiguration == null) {
            Log.i("convertPipelineConfig", "Returning null for null ThirdParty BatchPipelineConfig Input");
            return null;
        }
        return new MetricsBatchPipelineConfiguration(new AndroidMetricsBatchPipelineConfiguration(batchPipelineConfiguration));
    }

    public static com.amazon.client.metrics.thirdparty.configuration.BatchQueueConfiguration convertBatchQueueConfiguration_fromCommonToThirdParty(BatchQueueConfiguration batchQueueConfiguration) {
        if (batchQueueConfiguration == null) {
            Log.i("convertBatchQueueConfig", "Returning null for null Common BatchQueue Config Input");
            return null;
        }
        IBatchQueueConfiguration delegateBatchQueueConfiguration = batchQueueConfiguration.getDelegateBatchQueueConfiguration();
        if (delegateBatchQueueConfiguration instanceof AndroidBatchQueueConfiguration) {
            return ((AndroidBatchQueueConfiguration) delegateBatchQueueConfiguration).getDelegateBatchQueueConfiguration();
        }
        Log.i("convertBatchQueueConfig", "Returning null for Common BatchQueueConfig that does not delegate to ThirdParty");
        return null;
    }

    public static BatchQueueConfiguration convertBatchQueueConfiguration_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.configuration.BatchQueueConfiguration batchQueueConfiguration) {
        if (batchQueueConfiguration == null) {
            Log.i("convertBatchQueueConfig", "Returning null for null ThirdParty BatchQueueConfig Input");
            return null;
        }
        return new BatchQueueConfiguration(new AndroidBatchQueueConfiguration(batchQueueConfiguration));
    }

    public static com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration convertCodecConfiguration_fromCommonToThirdParty(CodecConfiguration codecConfiguration) {
        if (codecConfiguration == null) {
            Log.i("convertCodecConfig", "Returning null for null Common Codec Config Input");
            return null;
        }
        ICodecConfiguration delegateCodecConfiguration = codecConfiguration.getDelegateCodecConfiguration();
        if (delegateCodecConfiguration instanceof AndroidCodecConfiguration) {
            return ((AndroidCodecConfiguration) delegateCodecConfiguration).getDelegateCodecConfiguration();
        }
        Log.i("convertCodecConfig", "Returning null for Common CodecConfig that does not delegate to ThirdParty");
        return null;
    }

    public static CodecConfiguration convertCodecConfiguration_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration codecConfiguration) {
        if (codecConfiguration == null) {
            Log.i("convertCodecConfig", "Returning null for null ThirdParty CodecConfig Input");
            return null;
        }
        return new CodecConfiguration(new AndroidCodecConfiguration(codecConfiguration));
    }

    public static com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration convertHttpConfiguration_fromCommonToThirdParty(HttpConfiguration httpConfiguration) {
        if (httpConfiguration == null) {
            Log.i("convertHttpConfig", "Returning null for null Common Http Config Input");
            return null;
        }
        IHttpConfiguration delegateHttpConfiguration = httpConfiguration.getDelegateHttpConfiguration();
        if (delegateHttpConfiguration instanceof AndroidHttpConfiguration) {
            return ((AndroidHttpConfiguration) delegateHttpConfiguration).getDelegateHttpConfiguration();
        }
        Log.i("convertHttpConfig", "Returning null for Common HttpConfig that does not delegate to ThirdParty");
        return null;
    }

    public static HttpConfiguration convertHttpConfiguration_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration httpConfiguration) {
        if (httpConfiguration == null) {
            Log.i("convertHttpConfig", "Returning null for null ThirdParty HttpConfig Input");
            return null;
        }
        return new HttpConfiguration(new AndroidHttpConfiguration(httpConfiguration));
    }

    public static com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration convertMetricsConfiguration_fromCommonToThirdParty(MetricsConfiguration metricsConfiguration) {
        if (metricsConfiguration == null) {
            Log.i("convertMetricsConfig", "Returning null for null Common MetricsConfiguration Input");
            return null;
        }
        IMetricsConfiguration delegateMetricsConfiguration = metricsConfiguration.getDelegateMetricsConfiguration();
        if (delegateMetricsConfiguration instanceof AndroidMetricsConfiguration) {
            return ((AndroidMetricsConfiguration) delegateMetricsConfiguration).getDelegateThirdPartyMetricsConfiguration();
        }
        Log.i("convertMetricsConfig", "Returning null for Common MetricsConfiguration that does not delegate to ThirdParty");
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration convertNetworkConfiguration_fromCommonToThirdParty(NetworkConfiguration networkConfiguration) {
        if (networkConfiguration == null) {
            Log.i("convertNetworkConfig", "Returning null for null Common NetworkConfig Input");
            return null;
        } else if (!(networkConfiguration instanceof MetricsNetworkConfiguration)) {
            Log.i("convertNetworkConfig", "NetworkConfig not instance of MetricsNetworkConfig, returning null");
            return null;
        } else {
            NetworkConfiguration delegateNetworkConfiguration = ((MetricsNetworkConfiguration) networkConfiguration).getDelegateNetworkConfiguration();
            if (delegateNetworkConfiguration instanceof AndroidMetricsNetworkConfiguration) {
                return ((AndroidMetricsNetworkConfiguration) delegateNetworkConfiguration).getDelegateNetworkConfiguration();
            }
            Log.i("convertNetworkConfig", "Returning null for Common NetworkConfig that does not delegate to ThirdParty");
            return null;
        }
    }

    public static NetworkConfiguration convertNetworkConfiguration_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.configuration.NetworkConfiguration networkConfiguration) {
        if (networkConfiguration == null) {
            Log.i("convertNetworkConfig", "Returning null for null ThirdParty NetworkConfig Input");
            return null;
        }
        return new MetricsNetworkConfiguration(new AndroidMetricsNetworkConfiguration(networkConfiguration));
    }

    public static Set<com.amazon.client.metrics.thirdparty.configuration.NetworkType> convertNetworkTypes_fromCommonToThirdParty(Set<NetworkType> set) {
        if (set == null) {
            Log.i("convertNetworkTypes", "Returning null for null Common NetworkTypes Input");
            return null;
        }
        HashSet hashSet = new HashSet(set.size());
        for (NetworkType networkType : set) {
            hashSet.add(com.amazon.client.metrics.thirdparty.configuration.NetworkType.valueOf(networkType.name()));
        }
        return hashSet;
    }

    public static Set<NetworkType> convertNetworkTypes_fromThirdPartyToCommon(Set<com.amazon.client.metrics.thirdparty.configuration.NetworkType> set) {
        if (set == null) {
            Log.i("convertNetworkTypes", "Returning null for null ThirdParty NetworkTypes Input");
            return null;
        }
        HashSet hashSet = new HashSet(set.size());
        for (com.amazon.client.metrics.thirdparty.configuration.NetworkType networkType : set) {
            hashSet.add(NetworkType.valueOf(networkType.name()));
        }
        return hashSet;
    }
}
