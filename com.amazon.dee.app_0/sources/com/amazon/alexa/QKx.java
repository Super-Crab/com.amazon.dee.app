package com.amazon.alexa;

import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.client.core.marketplace.MarketplaceAuthority;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: CardModule_ProvidesCardExtrasFactory.java */
/* loaded from: classes.dex */
public final class QKx implements Factory<AlexaCardExtras> {
    public final Provider<MarketplaceAuthority> BIo;
    public final Provider<MBE> zQM;
    public final xfG zZm;

    public QKx(xfG xfg, Provider<MarketplaceAuthority> provider, Provider<MBE> provider2) {
        this.zZm = xfg;
        this.BIo = provider;
        this.zQM = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (AlexaCardExtras) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get(), this.zQM.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
