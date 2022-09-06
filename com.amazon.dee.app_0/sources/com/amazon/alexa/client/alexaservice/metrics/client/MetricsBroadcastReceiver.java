package com.amazon.alexa.client.alexaservice.metrics.client;

import android.content.BroadcastReceiver;
import android.content.Intent;
import com.amazon.alexa.IUt;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class MetricsBroadcastReceiver extends BroadcastReceiver {
    public static final String zZm = "MetricsBroadcastReceiver";
    @Inject
    public IUt BIo;

    /* JADX WARN: Removed duplicated region for block: B:29:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onReceive(android.content.Context r19, android.content.Intent r20) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.client.alexaservice.metrics.client.MetricsBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public final String zZm(Intent intent, String str, String str2) {
        String stringExtra = intent.hasExtra(str) ? intent.getStringExtra(str) : null;
        return stringExtra == null ? str2 : stringExtra;
    }
}
