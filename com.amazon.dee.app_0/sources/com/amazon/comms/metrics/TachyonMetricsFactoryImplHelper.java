package com.amazon.comms.metrics;

import com.amazon.alexa.comms.mediaInsights.TraceHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class TachyonMetricsFactoryImplHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public String getEncodedString(String str) {
        return TraceHelper.base64EncodePayload(str);
    }
}
