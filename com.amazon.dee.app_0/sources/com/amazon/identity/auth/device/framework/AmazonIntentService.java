package com.amazon.identity.auth.device.framework;

import android.app.IntentService;
import android.content.Intent;
import com.amazon.identity.auth.device.io;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class AmazonIntentService extends IntentService {
    public static final String TAG = AmazonIntentService.class.getName();

    public AmazonIntentService(String str) {
        super(str);
    }

    protected abstract void cx();

    @Override // android.app.IntentService
    public final void onHandleIntent(Intent intent) {
        if (intent == null) {
            io.w(TAG, "Ignore: Received (null) Intent");
            return;
        }
        try {
            io.i(TAG, "Service Received: ".concat(String.valueOf(intent)));
            cx();
        } catch (Error e) {
            stopSelf();
            throw e;
        } catch (RuntimeException e2) {
            stopSelf();
            throw e2;
        }
    }
}
