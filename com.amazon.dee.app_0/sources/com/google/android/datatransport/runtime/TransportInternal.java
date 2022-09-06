package com.google.android.datatransport.runtime;

import com.google.android.datatransport.TransportScheduleCallback;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.datatransport:transport-runtime@@2.2.0 */
/* loaded from: classes2.dex */
public interface TransportInternal {
    void send(SendRequest sendRequest, TransportScheduleCallback transportScheduleCallback);
}