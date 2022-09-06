package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.DeviceDataStore;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ee {
    public static void N(Context context) {
        bc.bm().clearCache();
        ab.generateNewInstance(context);
        MAPApplicationInformationQueryer.E(context).P();
        cc.a(context, (Boolean) null);
        cg.generateNewInstance(context);
        cj.generateNewInstance(context);
        dl.generateNewInstance(context);
        ch.bP().P();
        ar.generateNewInstance(context);
        h.generateNewInstance(context);
        az.c(ed.M(context));
        hj.generateNewInstance(context);
        CustomerAttributeStore.generateNewInstance(context);
        DeviceDataStore.generateNewInstance(context);
    }
}
