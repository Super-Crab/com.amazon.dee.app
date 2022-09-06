package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: AlexaUserSpeechProviderFactory_Factory.java */
/* renamed from: com.amazon.alexa.zOR  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0243zOR implements Factory<CGv> {
    public final Provider<KvZ> zZm;

    public C0243zOR(Provider<KvZ> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new CGv(this.zZm.mo10268get());
    }
}
