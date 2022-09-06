package com.amazon.trace;

import amazon.speech.util.SystemPropertiesHelper;
import android.content.Context;
import android.os.Trace;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.metrics.MetricsUtil;
/* loaded from: classes13.dex */
public class TraceUtil {
    private static final String PROP_ENABLE_APP_TRACES = "persist.amazon.speech.trace.app";
    private static final String TAG = "TraceUtil";
    public static final String TRACE_NAME_TTS_END = "tts_end";
    public static final String TRACE_NAME_TTS_START = "tts_start";
    public static final String TRACE_NAME_UTTERANCE_END = "utterance_end";
    public static final String TRACE_NAME_UTTERANCE_START = "utterance_start";
    private static TraceUtil mInstance;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TraceUtil() {
        new SystemPropertiesHelper().getBoolean(PROP_ENABLE_APP_TRACES, false);
    }

    public static TraceUtil getInstance(Context context) {
        if (mInstance == null) {
            if (new SystemPropertiesHelper().getBoolean(MetricsUtil.KNIGHT_METRICS_TEST_PROPERTY, false)) {
                mInstance = new TestTraceUtil(context);
            } else {
                mInstance = new TraceUtil();
            }
        }
        return mInstance;
    }

    public void recordTrace(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "Attempted to record a trace with an empty name, ignoring");
        } else {
            recordTraceInternal(str);
        }
    }

    protected void recordTraceInternal(String str) {
        Trace.beginSection(str);
        Trace.endSection();
    }
}
