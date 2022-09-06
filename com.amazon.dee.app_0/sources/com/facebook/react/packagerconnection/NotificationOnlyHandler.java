package com.facebook.react.packagerconnection;

import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
/* loaded from: classes2.dex */
public abstract class NotificationOnlyHandler implements RequestHandler {
    private static final String TAG = JSPackagerClient.class.getSimpleName();

    @Override // com.facebook.react.packagerconnection.RequestHandler
    public abstract void onNotification(@Nullable Object obj);

    @Override // com.facebook.react.packagerconnection.RequestHandler
    public final void onRequest(@Nullable Object obj, Responder responder) {
        responder.error("Request is not supported");
        FLog.e(TAG, "Request is not supported");
    }
}
