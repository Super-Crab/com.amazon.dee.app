package com.amazon.alexa;

import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.core.device.PersistentStorage;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: MetricsModule_ProvideMarketplaceAuthorityFactory.java */
/* loaded from: classes.dex */
public final class Qig implements Factory<MarketplaceAuthority> {
    public final Provider<AccountManager> BIo;
    public final Provider<PersistentStorage> zQM;
    public final kbj zZm;

    public Qig(kbj kbjVar, Provider<AccountManager> provider, Provider<PersistentStorage> provider2) {
        this.zZm = kbjVar;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (MarketplaceAuthority) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), DoubleCheck.lazy(this.zQM)), "Cannot return null from a non-@Nullable @Provides method");
    }
}
