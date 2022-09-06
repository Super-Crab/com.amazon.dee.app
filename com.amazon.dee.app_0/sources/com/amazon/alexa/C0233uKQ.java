package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SqliteExternalComponentStateDao_Factory.java */
/* renamed from: com.amazon.alexa.uKQ  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0233uKQ implements Factory<dCo> {
    public final Provider<Whr> zZm;

    public C0233uKQ(Provider<Whr> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new dCo(this.zZm.mo10268get());
    }
}
