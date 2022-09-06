package com.amazon.alexa;

import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaNotificationPreferences_Factory.java */
/* loaded from: classes.dex */
public final class VGq implements Factory<Bch> {
    public final Provider<PersistentStorage> zZm;

    public VGq(Provider<PersistentStorage> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Bch(DoubleCheck.lazy(this.zZm));
    }
}
