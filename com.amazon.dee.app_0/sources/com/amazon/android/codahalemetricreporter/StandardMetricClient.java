package com.amazon.android.codahalemetricreporter;

import android.content.Context;
import com.amazon.android.codahalemetricreporter.impl.MetricIntentClient;
/* loaded from: classes11.dex */
public final class StandardMetricClient {
    private StandardMetricClient() {
    }

    public static MetricClient create(Context context, String str) {
        return new MetricIntentClient(context, str);
    }
}
