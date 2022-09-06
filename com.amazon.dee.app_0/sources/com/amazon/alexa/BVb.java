package com.amazon.alexa;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.Set;
/* compiled from: ChannelsModule_ProvideInactiveInteractionInterfaceNamesFactory.java */
/* loaded from: classes.dex */
public final class BVb implements Factory<Set<dnp>> {
    public final dsY zZm;

    public BVb(dsY dsy) {
        this.zZm = dsy;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (Set) Preconditions.checkNotNull(this.zZm.zZm(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
