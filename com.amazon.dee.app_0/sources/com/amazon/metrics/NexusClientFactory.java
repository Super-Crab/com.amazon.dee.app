package com.amazon.metrics;

import amazon.speech.nexusclient.INexusRecorder;
import amazon.speech.nexusclient.NexusClient;
import amazon.speech.nexusclient.event.INexusEvent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
/* loaded from: classes9.dex */
public class NexusClientFactory {
    private static final String INTENT_ACTION = "com.amazon.knight.test.support.NexusEvent";
    private static final String JSON_KEY = "json";
    private static final String TAG = GeneratedOutlineSupport1.outline39(NexusClientFactory.class, GeneratedOutlineSupport1.outline107("SPCH-Metric-"));

    /* loaded from: classes9.dex */
    private static class IntentNexusRecorder implements INexusRecorder {
        private Context mContext;

        IntentNexusRecorder(Context context) {
            this.mContext = context;
        }

        private Intent constructIntent(String str) {
            Intent intent = new Intent();
            intent.setAction(NexusClientFactory.INTENT_ACTION);
            intent.setPackage(TestMetricsUtil.TEST_PACKAGE_NAME);
            intent.putExtra(NexusClientFactory.JSON_KEY, str);
            return intent;
        }

        private void fire(String str) {
            String unused = NexusClientFactory.TAG;
            GeneratedOutlineSupport1.outline158("fire json: ", str);
            if (this.mContext == null) {
                return;
            }
            Intent constructIntent = constructIntent(str);
            String unused2 = NexusClientFactory.TAG;
            String str2 = "broadcast intent:" + constructIntent;
            this.mContext.sendBroadcast(constructIntent);
        }

        @Override // amazon.speech.nexusclient.INexusRecorder
        public void flush() {
        }

        @Override // amazon.speech.nexusclient.INexusRecorder
        public void record(INexusEvent iNexusEvent) {
            try {
                fire(iNexusEvent.toJSON().toString());
            } catch (JSONException e) {
                Log.e(NexusClientFactory.TAG, "nexus event to json failed", e);
            }
        }

        @Override // amazon.speech.nexusclient.INexusRecorder
        public void release() {
            this.mContext = null;
        }
    }

    public static NexusClient createClient(Context context, NexusClient.Config config, INexusRecorder iNexusRecorder) {
        if (MetricsUtil.isTest()) {
            return new NexusClient(config, new IntentNexusRecorder(context), context);
        }
        return new NexusClient(config, iNexusRecorder, context);
    }
}
