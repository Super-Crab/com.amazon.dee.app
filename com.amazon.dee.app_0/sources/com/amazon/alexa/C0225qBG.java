package com.amazon.alexa;

import com.amazon.alexa.auth.TokenProvider;
import com.amazon.alexa.wakeword.davs.NetworkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* compiled from: WakeWordModule_ProvideNetworkManagerFactory.java */
/* renamed from: com.amazon.alexa.qBG  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0225qBG implements Factory<NetworkManager> {
    public final Provider<TokenProvider> BIo;
    public final iPU zZm;

    public C0225qBG(iPU ipu, Provider<TokenProvider> provider) {
        this.zZm = ipu;
        this.BIo = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return (NetworkManager) Preconditions.checkNotNull(this.zZm.zZm(this.BIo.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }
}
