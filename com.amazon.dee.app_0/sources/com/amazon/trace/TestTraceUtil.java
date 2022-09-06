package com.amazon.trace;

import android.content.Context;
import android.content.Intent;
import com.amazon.metrics.TestMetricsUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class TestTraceUtil extends TraceUtil {
    private static final String TAG = "TestTraceUtil";
    private final Context mContext;

    public TestTraceUtil(Context context) {
        this.mContext = context;
    }

    Intent constructIntent(String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setPackage(TestMetricsUtil.TEST_PACKAGE_NAME);
        return intent;
    }

    @Override // com.amazon.trace.TraceUtil
    protected void recordTraceInternal(String str) {
        sendIntentForTest(str);
    }

    void sendIntent(Intent intent) {
        GeneratedOutlineSupport1.outline158("Test TraceUtil sending intent for trace ", intent.getAction());
        this.mContext.sendBroadcast(intent);
    }

    void sendIntentForTest(String str) {
        sendIntent(constructIntent(str));
    }
}
