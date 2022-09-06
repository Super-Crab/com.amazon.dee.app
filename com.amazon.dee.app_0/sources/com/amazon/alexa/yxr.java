package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.metrics.core.DeviceInformation;
import dagger.Module;
import dagger.Provides;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Named;
import javax.inject.Singleton;
/* compiled from: ComponentStateModule.java */
@Module
/* loaded from: classes.dex */
public class yxr {
    @Provides
    @Singleton
    public TFi zZm(dCo dco) {
        return dco;
    }

    @Provides
    @Singleton
    public lhN zZm(YKQ ykq, lEV lev, ClS clS, C0245zoo c0245zoo, zmg zmgVar, DeviceInformation deviceInformation, ZCC zcc) {
        HashSet hashSet = new HashSet(2);
        hashSet.add(clS);
        hashSet.add(zcc);
        return new lhN(ykq, lev, hashSet, c0245zoo, zmgVar, deviceInformation);
    }

    @Provides
    @Singleton
    @Named("namespaces_without_capabilities")
    public Set<Namespace> zZm() {
        return new tVr(this);
    }
}
