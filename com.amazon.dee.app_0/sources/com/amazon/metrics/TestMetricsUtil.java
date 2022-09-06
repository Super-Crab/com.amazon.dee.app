package com.amazon.metrics;

import amazon.speech.util.Log;
import android.content.Context;
import android.content.Intent;
import com.amazon.metrics.MetricsUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class TestMetricsUtil extends MetricsUtil {
    static final String COUNT_VALUE = "count_value";
    static final String DISCRETE_VALUE = "discrete_value";
    static final String FALLBACK_METADATA = "{}";
    static final String META_DATA = "meta_data";
    static final String METRICS_TYPE = "metricsType";
    static final String METRICS_TYPE_COUNT = "count";
    static final String METRICS_TYPE_DISCRETE = "discrete";
    static final String METRICS_TYPE_TIMER = "timer";
    static final String PROGRAM_NAME = "programName";
    static final String REPEAT = "repeat";
    static final String SOURCE_TAG = "sourceTag";
    private static final String TAG = "TestMetricsUtil";
    public static final String TEST_PACKAGE_NAME = "com.amazon.knight.test.support";
    static final String TIMER_VALUE = "timer_value";
    Map<String, SimpleMetricsTimer> mTimerMap = new HashMap();
    private Map<String, String> mGlobalMetricMetadata = new ConcurrentHashMap();

    /* loaded from: classes9.dex */
    class SimpleMetricsTimer {
        final String mProgramName;
        final String mSourceTag;
        final long mStartTimeStamp;
        final String mTimerName;

        private SimpleMetricsTimer(String str, String str2, String str3) {
            this.mSourceTag = str;
            this.mProgramName = str2;
            this.mTimerName = str3;
            this.mStartTimeStamp = System.currentTimeMillis();
        }
    }

    private void decorateIntentForCountInfo(Intent intent, double d) {
        intent.putExtra(METRICS_TYPE, "count");
        intent.putExtra(COUNT_VALUE, d);
    }

    private void decorateIntentForDiscreteInfo(Intent intent, String str) {
        intent.putExtra(METRICS_TYPE, METRICS_TYPE_DISCRETE);
        intent.putExtra(DISCRETE_VALUE, str);
    }

    private void decorateIntentForMetaDataInfo(Intent intent, MetricsUtil.MetadataHelper metadataHelper) {
        if (metadataHelper != null) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(metadataHelper.mMetadataMap);
            hashMap.putAll(this.mGlobalMetricMetadata);
            decorateIntentForMetaDataInfo(intent, hashMap);
        }
    }

    private void decorateIntentForTimerInfo(Intent intent, long j, int i) {
        intent.putExtra(METRICS_TYPE, "timer");
        intent.putExtra(TIMER_VALUE, j);
        intent.putExtra("repeat", i);
    }

    private void sendIntent(Context context, Intent intent) {
        if (!intent.hasExtra(META_DATA)) {
            decorateIntentForMetaDataInfo(intent, this.mGlobalMetricMetadata);
        }
        String str = "metrics forward to test package with eventName:" + intent.getAction();
        context.sendBroadcast(intent);
    }

    @Override // com.amazon.metrics.MetricsUtil
    public void addGlobalMetadata(Map<String, String> map) {
        if (map == null) {
            Log.w(TAG, "Global metadata was null.");
        } else {
            this.mGlobalMetricMetadata.putAll(map);
        }
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean addTimer(Context context, String str, String str2, String str3, long j, int i, MetricsUtil.MetadataHelper metadataHelper) {
        Intent constructIntent = constructIntent(str, str2, str3);
        decorateIntentForTimerInfo(constructIntent, j, i);
        decorateIntentForMetaDataInfo(constructIntent, metadataHelper);
        sendIntent(context, constructIntent);
        return true;
    }

    Intent constructIntent(String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setAction(str3);
        intent.setPackage(TEST_PACKAGE_NAME);
        intent.putExtra(SOURCE_TAG, str);
        intent.putExtra(PROGRAM_NAME, str2);
        return intent;
    }

    JSONObject constructJsonObject() {
        return new JSONObject();
    }

    String metaDataToJson(Map<String, String> map) throws JSONException {
        if (map != null) {
            JSONObject constructJsonObject = constructJsonObject();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                constructJsonObject.put(entry.getKey(), entry.getValue());
            }
            return constructJsonObject.toString();
        }
        throw new IllegalArgumentException();
    }

    public boolean recordClickStreamMetrics(Context context, String str, String str2, String str3, String str4, String str5, String str6, boolean z, String str7) {
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean recordCounter(Context context, String str, String str2, String str3, MetricsUtil.MetadataHelper metadataHelper, double d) {
        recordCounter(context, str, str2, str3, metadataHelper, d, false);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean recordDiscreteValue(Context context, String str, String str2, String str3, String str4, boolean z, MetricsUtil.MetadataHelper metadataHelper) {
        Intent constructIntent = constructIntent(str, str2, str3);
        decorateIntentForDiscreteInfo(constructIntent, str4);
        decorateIntentForMetaDataInfo(constructIntent, metadataHelper);
        sendIntent(context, constructIntent);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean removeTimer(Context context, String str) {
        if (this.mTimerMap.get(str) == null) {
            return false;
        }
        this.mTimerMap.remove(str);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean startTimer(Context context, String str, String str2, String str3) {
        if (this.mTimerMap.containsKey(str3)) {
            return false;
        }
        this.mTimerMap.put(str3, new SimpleMetricsTimer(str, str2, str3));
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean stopTimer(Context context, String str, MetricsUtil.MetadataHelper metadataHelper) {
        SimpleMetricsTimer simpleMetricsTimer = this.mTimerMap.get(str);
        if (simpleMetricsTimer == null) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() - simpleMetricsTimer.mStartTimeStamp;
        this.mTimerMap.remove(str);
        Intent constructIntent = constructIntent(simpleMetricsTimer.mSourceTag, simpleMetricsTimer.mProgramName, simpleMetricsTimer.mTimerName);
        decorateIntentForTimerInfo(constructIntent, currentTimeMillis, 1);
        decorateIntentForMetaDataInfo(constructIntent, metadataHelper);
        sendIntent(context, constructIntent);
        return true;
    }

    @Override // com.amazon.metrics.MetricsUtil
    public boolean recordCounter(Context context, String str, String str2, String str3, MetricsUtil.MetadataHelper metadataHelper, double d, boolean z) {
        Intent constructIntent = constructIntent(str, str2, str3);
        decorateIntentForCountInfo(constructIntent, d);
        decorateIntentForMetaDataInfo(constructIntent, metadataHelper);
        sendIntent(context, constructIntent);
        return true;
    }

    private void decorateIntentForMetaDataInfo(Intent intent, Map<String, String> map) {
        String str;
        try {
            str = metaDataToJson(map);
        } catch (JSONException unused) {
            str = FALLBACK_METADATA;
        }
        intent.putExtra(META_DATA, str);
    }
}
