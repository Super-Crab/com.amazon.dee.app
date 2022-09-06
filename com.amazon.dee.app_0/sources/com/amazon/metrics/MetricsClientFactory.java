package com.amazon.metrics;

import amazon.speech.simclient.metrics.Counter;
import amazon.speech.simclient.metrics.MetricsClient;
import amazon.speech.simclient.metrics.MetricsRecord;
import amazon.speech.simclient.metrics.Timer;
import amazon.speech.simclient.metrics.recorder.MetricsRecorder;
import android.content.Context;
import android.content.Intent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class MetricsClientFactory {
    public static MetricsClient createClient(Context context, String str, String str2) {
        if (MetricsUtil.isTest()) {
            return new MetricsClient(new IntentMetricsRecorder(context), str, str2);
        }
        return new MetricsClient(context, str, str2);
    }

    /* loaded from: classes9.dex */
    private static class IntentMetricsRecorder implements MetricsRecorder {
        static final String COUNT_VALUE = "count_value";
        static final String FALLBACK_METADATA = "{}";
        static final String META_DATA = "meta_data";
        static final String METRICS_TYPE = "metricsType";
        static final String METRICS_TYPE_COUNT = "count";
        static final String METRICS_TYPE_TIMER = "timer";
        static final String PROGRAM_NAME = "programName";
        static final String REPEAT = "repeat";
        static final String SOURCE_TAG = "sourceTag";
        private static final String TAG = "IntentMetricsRecorder";
        static final String TEST_PACKAGE_NAME = "com.amazon.knight.test.support";
        static final String TIMER_VALUE = "timer_value";
        private final Context mContext;

        public IntentMetricsRecorder(Context context) {
            this.mContext = context;
        }

        private Intent constructIntent(MetricsRecord metricsRecord) {
            Intent intent = new Intent();
            intent.setAction(metricsRecord.getName());
            intent.setPackage("com.amazon.knight.test.support");
            intent.putExtra(SOURCE_TAG, metricsRecord.getSourceName());
            intent.putExtra(PROGRAM_NAME, metricsRecord.getProgramName());
            return intent;
        }

        private void decorateIntentForCountInfo(Intent intent, Counter counter) {
            intent.putExtra(METRICS_TYPE, "count");
            intent.putExtra(COUNT_VALUE, counter.getCount());
            String str = "Counter recorded:" + counter.getName() + ", source name:" + counter.getSourceName() + ", program name:" + counter.getProgramName() + ", count:" + counter.getCount();
        }

        private void decorateIntentForMetaDataInfo(Intent intent, MetricsRecord metricsRecord) {
            String str;
            if (metricsRecord != null) {
                try {
                    str = metaDataToJson(metricsRecord.getMetadata());
                } catch (JSONException unused) {
                    str = FALLBACK_METADATA;
                }
                intent.putExtra(META_DATA, str);
            }
        }

        private void decorateIntentForTimerInfo(Intent intent, Timer timer) {
            intent.putExtra(METRICS_TYPE, "timer");
            intent.putExtra(TIMER_VALUE, timer.getDuration());
            intent.putExtra("repeat", 1);
            String str = "Timer Recorded :" + timer.getName() + ", source name:" + timer.getSourceName() + ", program name:" + timer.getProgramName() + ", duration:" + timer.getDuration();
        }

        static String metaDataToJson(MetricsRecord.Metadata metadata) throws JSONException {
            if (metadata != null) {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry<String, String> entry : metadata.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
                return jSONObject.toString();
            }
            throw new IllegalArgumentException();
        }

        private void sendIntent(Intent intent) {
            GeneratedOutlineSupport1.outline158("metrics forward to test package with eventName:", intent.getAction());
            this.mContext.sendBroadcast(intent);
        }

        void onRecord(Counter counter) {
            Intent constructIntent = constructIntent(counter);
            decorateIntentForCountInfo(constructIntent, counter);
            decorateIntentForMetaDataInfo(constructIntent, counter);
            sendIntent(constructIntent);
        }

        @Override // amazon.speech.simclient.metrics.recorder.MetricsRecorder
        public void record(MetricsRecord<?> metricsRecord) {
            if (metricsRecord instanceof Timer) {
                onRecord((Timer) metricsRecord);
            } else if (!(metricsRecord instanceof Counter)) {
                return;
            } else {
                onRecord((Counter) metricsRecord);
            }
            metricsRecord.recycle();
        }

        void onRecord(Timer timer) {
            Intent constructIntent = constructIntent(timer);
            decorateIntentForTimerInfo(constructIntent, timer);
            decorateIntentForMetaDataInfo(constructIntent, timer);
            sendIntent(constructIntent);
        }
    }
}
