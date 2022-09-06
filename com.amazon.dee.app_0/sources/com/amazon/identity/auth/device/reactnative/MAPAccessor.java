package com.amazon.identity.auth.device.reactnative;

import android.content.Context;
import com.amazon.identity.auth.device.ac;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.hr;
import com.amazon.identity.auth.device.mq;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPAccessor {
    private final ac bK;
    private final Context mContext;

    public MAPAccessor(Context context) {
        this.mContext = context;
        this.bK = new ac(context);
    }

    public String getAuthPortalHost(String str) {
        return hr.m(ed.M(this.mContext), str);
    }

    public String getPandaHost(String str) {
        return hr.c(ed.M(this.mContext), str);
    }

    public boolean hasPrimaryRole(String str) {
        return this.bK.hasPrimaryRole(str);
    }

    public void incrementCounterAndRecord(String str, String... strArr) {
        mq.incrementCounterAndRecord("RNAndroid:".concat(String.valueOf(str)), strArr);
    }
}
