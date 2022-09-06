package com.amazon.alexa;

import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpeechSynthesizerComponentStateAuthority_Factory.java */
/* loaded from: classes.dex */
public final class UBx implements Factory<QeM> {
    public final Provider<dDK> zZm;

    public UBx(Provider<dDK> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new QeM(this.zZm.mo10268get());
    }
}
