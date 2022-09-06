package com.facebook.react.packagerconnection;

import androidx.annotation.Nullable;
/* loaded from: classes2.dex */
public interface RequestHandler {
    void onNotification(@Nullable Object obj);

    void onRequest(@Nullable Object obj, Responder responder);
}
