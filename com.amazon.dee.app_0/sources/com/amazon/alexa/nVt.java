package com.amazon.alexa;

import android.content.pm.PackageManager;
import com.amazon.alexa.client.core.device.PersistentStorage;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: PreferredNavigationAppSettingRetriever_Factory.java */
/* loaded from: classes.dex */
public final class nVt implements Factory<spf> {
    public final Provider<PersistentStorage> BIo;
    public final Provider<PackageManager> zZm;

    public nVt(Provider<PackageManager> provider, Provider<PersistentStorage> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new spf(this.zZm.mo10268get(), DoubleCheck.lazy(this.BIo));
    }
}
