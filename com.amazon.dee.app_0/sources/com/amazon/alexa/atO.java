package com.amazon.alexa;

import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AuthorizationAuthority_Factory.java */
/* loaded from: classes.dex */
public final class atO implements Factory<tol> {
    public final Provider<AccountManager> BIo;
    public final Provider<AlexaClientEventBus> zZm;

    public atO(Provider<AlexaClientEventBus> provider, Provider<AccountManager> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new tol(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
