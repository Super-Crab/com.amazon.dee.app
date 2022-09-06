package com.amazon.client.metrics.thirdparty.configuration;

import android.os.Build;
import com.amazon.client.metrics.thirdparty.Channel;
import com.amazon.client.metrics.thirdparty.Priority;
import com.amazon.client.metrics.thirdparty.batch.creator.PriorityChannelPair;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.map.device.token.MAPCookie;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class MetricsConfigurationParser {
    public static final String HTTP_CONFIGURATION_FIELD = "HttpConfiguration";
    private static final String TAG = "MetricsConfigurationParser";
    private static final DPLogger log = new DPLogger(TAG);

    private HttpConfiguration getDefaultHttpConfiguration() throws MetricsConfigurationException {
        return new HttpConfiguration(HttpRequestSignerType.OAUTH, Build.TYPE.equals("userdebug") ? "https://dp-mont.integ.amazon.com:443" : "https://device-metrics-us.amazon.com:443", "https://device-metrics-us-2.amazon.com:443");
    }

    public Map<PriorityChannelPair, BatchPipelineConfiguration> getBatchPipelineConfigurationMap(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject jSONObject2 = jSONObject;
        HashMap hashMap = new HashMap();
        hashMap.put("NormalPriority", Priority.NORMAL);
        hashMap.put("HighPriority", Priority.HIGH);
        hashMap.put("CriticalPriority", Priority.CRITICAL);
        HashMap hashMap2 = new HashMap();
        JSONObject jSONObject3 = jSONObject2.getJSONObject("PipelineConfiguration");
        HashSet<Channel> hashSet = new HashSet();
        if (jSONObject3.optJSONObject("ReservedForLocationServicePriority") != null) {
            hashSet.add(Channel.LOCATION);
        }
        if (jSONObject3.optJSONObject("ReservedForNonAnonymousMetricsPriority") != null) {
            hashSet.add(Channel.NON_ANONYMOUS);
        }
        Iterator it2 = hashMap.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            JSONObject optJSONObject = jSONObject3.optJSONObject((String) entry.getKey());
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("BatchQueueType");
                String optString2 = optJSONObject.optString("BatchQueueDirectoryPrefix");
                JSONObject optJSONObject2 = jSONObject2.optJSONObject("BatchQueueConfiguration");
                if (optString.isEmpty()) {
                    if (optJSONObject2 != null) {
                        optString = optJSONObject2.getString("Type");
                        optString2 = optJSONObject2.getString("DirectoryPrefix");
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Missing Batch Queue type for priority: ");
                        outline107.append(entry.getValue());
                        throw new JSONException(outline107.toString());
                    }
                }
                String str = optString2;
                String str2 = optString;
                JSONArray optJSONArray = optJSONObject.optJSONArray("Channels");
                if (optJSONArray != null && optJSONArray.length() != 0) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        hashSet.add(Channel.valueOf(optJSONArray.getString(i)));
                    }
                } else {
                    hashSet.add(Channel.ANONYMOUS);
                }
                Iterator it3 = it2;
                MetricsBatchPipelineConfiguration metricsBatchPipelineConfiguration = new MetricsBatchPipelineConfiguration(BatchQueueType.fromString(str2), str, optJSONObject.getLong("MaxBatchOpenTimeMillis"), optJSONObject.getLong("CheckBatchOpenTimeMillis"), optJSONObject.getInt("MaxBatchEntries"), optJSONObject.getInt("MaxBatchSizeBytes"), optJSONObject.getInt("MaxBatchQueueCapacityBytes"), optJSONObject.getInt("MaxBatchQueueEntries"), optJSONObject.getLong("ExpiryTimeMillis"), optJSONObject.getLong("PurgePeriodMillis"), optJSONObject.getLong("TransmissionPeriodMillis"), BatchTransmitterType.fromString(optJSONObject.getString("BatchTransmitterType")));
                for (Channel channel : hashSet) {
                    hashMap2.put(new PriorityChannelPair((Priority) entry.getValue(), channel), metricsBatchPipelineConfiguration);
                }
                jSONObject2 = jSONObject;
                it2 = it3;
            }
        }
        return hashMap2;
    }

    public BatchQueueConfiguration getBatchQueueConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject optJSONObject = jSONObject.optJSONObject("BatchQueueConfiguration");
        if (optJSONObject != null) {
            return new BatchQueueConfiguration(BatchQueueType.fromString(optJSONObject.getString("Type")), optJSONObject.getString("DirectoryPrefix"));
        }
        return null;
    }

    public CodecConfiguration getCodecConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("CodecConfiguration");
        return new CodecConfiguration(CodecType.fromString(jSONObject2.getString("Type")), jSONObject2.getString("Version"));
    }

    public HttpConfiguration getHttpConfiguration(JSONObject jSONObject) throws MetricsConfigurationException {
        JSONObject optJSONObject = jSONObject == null ? null : jSONObject.optJSONObject(HTTP_CONFIGURATION_FIELD);
        HttpConfiguration defaultHttpConfiguration = getDefaultHttpConfiguration();
        if (jSONObject == null || optJSONObject == null) {
            return defaultHttpConfiguration;
        }
        return new HttpConfiguration(HttpRequestSignerType.fromString(optJSONObject.optString("RequestSignerType", defaultHttpConfiguration.getHttpRequestSignerType().getName())), optJSONObject.optString("UrlEndpoint", defaultHttpConfiguration.getUrlEndpoint()), optJSONObject.optString("StaticCredentialUrlEndpoint", defaultHttpConfiguration.getStaticCredentialUrlEndpoint()), optJSONObject.optInt("ConnectTimeout", defaultHttpConfiguration.getConnectTimeout()), optJSONObject.optInt("ReadTimeout", defaultHttpConfiguration.getReadTimeout()), optJSONObject.optLong("WakeLockTimeout", defaultHttpConfiguration.getWakeLockTimeout()));
    }

    public JSONObject getMetricsConfigurationForDomain(JSONObject jSONObject, boolean z) throws JSONException {
        String name = MetricsConfiguration.Domain.DEVO.getName();
        String name2 = MetricsConfiguration.Domain.MASTER.getName();
        String name3 = MetricsConfiguration.Domain.PROD.getName();
        if (jSONObject.has(name) && (Build.TYPE.equals("eng") || Build.TYPE.equals("userdebug") || z)) {
            log.info(TAG, "Picking configuration", MAPCookie.KEY_DOMAIN, "devo", "Build", Build.TYPE, "isDebuggable", Boolean.valueOf(z));
            return jSONObject.getJSONObject(name);
        } else if (jSONObject.has(name2) && Build.TYPE.equals("user") && Build.TAGS.equals("test-keys")) {
            log.info(TAG, "Picking configuration", MAPCookie.KEY_DOMAIN, "master", "Build", Build.TYPE, "isDebuggable", Boolean.valueOf(z));
            return jSONObject.getJSONObject(name2);
        } else {
            log.info(TAG, "Picking configuration", MAPCookie.KEY_DOMAIN, "prod", "Build", Build.TYPE, "isDebuggable", Boolean.valueOf(z));
            return jSONObject.getJSONObject(name3);
        }
    }

    public NetworkConfiguration getNetworkConfiguration(JSONObject jSONObject) throws JSONException, MetricsConfigurationException {
        TransportType fromString = TransportType.fromString(jSONObject.getString("TransportType"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("NetworkTypes");
        for (int i = 0; i < jSONArray.length(); i++) {
            hashSet.add(NetworkType.fromString(jSONArray.getString(i)));
        }
        return new MetricsNetworkConfiguration(fromString, hashSet);
    }

    public MetricsConfiguration parseConfiguration(JSONObject jSONObject, boolean z) throws MetricsConfigurationException {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() != 0) {
                    JSONObject metricsConfigurationForDomain = getMetricsConfigurationForDomain(jSONObject, z);
                    NetworkConfiguration networkConfiguration = getNetworkConfiguration(metricsConfigurationForDomain);
                    BatchQueueConfiguration batchQueueConfiguration = getBatchQueueConfiguration(metricsConfigurationForDomain);
                    CodecConfiguration codecConfiguration = getCodecConfiguration(metricsConfigurationForDomain);
                    HttpConfiguration httpConfiguration = getHttpConfiguration(metricsConfigurationForDomain);
                    Map<PriorityChannelPair, BatchPipelineConfiguration> batchPipelineConfigurationMap = getBatchPipelineConfigurationMap(metricsConfigurationForDomain);
                    if (batchQueueConfiguration != null) {
                        return new MetricsConfiguration(networkConfiguration, batchQueueConfiguration, codecConfiguration, httpConfiguration, batchPipelineConfigurationMap);
                    }
                    return new MetricsConfiguration(networkConfiguration, codecConfiguration, httpConfiguration, batchPipelineConfigurationMap);
                }
            } catch (JSONException e) {
                throw new MetricsConfigurationException("An JSONException was throw was parsing the metrics configuration file", e);
            }
        }
        throw new IllegalArgumentException("Unable to parse metrics configuration. It can not be null or empty");
    }
}
