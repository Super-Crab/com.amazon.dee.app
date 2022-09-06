package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AttentionSystemSettingsStore_Factory.java */
/* loaded from: classes.dex */
public final class CNj implements Factory<yGK> {
    public final Provider<PersistentStorage> zZm;

    public CNj(Provider<PersistentStorage> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new yGK(DoubleCheck.lazy(this.zZm));
    }
}
