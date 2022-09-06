package com.amazon.alexa.handsfree.protocols.sync;

import android.content.ComponentName;
/* loaded from: classes8.dex */
public interface DataSyncServiceConnectionListener {
    void onBindingFailure(ComponentName componentName, String str);

    void onBindingSuccess(ComponentName componentName);
}
