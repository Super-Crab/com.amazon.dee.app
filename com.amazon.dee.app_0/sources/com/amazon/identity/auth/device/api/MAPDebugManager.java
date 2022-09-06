package com.amazon.identity.auth.device.api;

import android.content.Context;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.io;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPDebugManager {
    private static final String TAG = "com.amazon.identity.auth.device.api.MAPDebugManager";
    private final ed o;

    public MAPDebugManager(Context context) {
        this.o = ed.M(context);
    }

    public String getDeviceSnapshot() {
        io.i(TAG, "GetDeviceSnapshot API called");
        return this.o.dV().getDeviceSnapshot();
    }
}
