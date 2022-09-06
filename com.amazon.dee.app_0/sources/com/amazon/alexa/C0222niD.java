package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Map;
/* compiled from: ChannelsModule_ProvideInteractionInterfaceNameMapFactory.java */
/* renamed from: com.amazon.alexa.niD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0222niD implements Factory<Map<dnp, dnp>> {
    public final dsY zZm;

    public C0222niD(dsY dsy) {
        this.zZm = dsy;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Map) Preconditions.checkNotNull(this.zZm.BIo(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
