package com.amazon.alexa;

import android.app.KeyguardManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: LockScreenManager_Factory.java */
/* loaded from: classes.dex */
public final class gKS implements Factory<Msx> {
    public final Provider<KeyguardManager> zZm;

    public gKS(Provider<KeyguardManager> provider) {
        this.zZm = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new Msx(this.zZm.mo10268get());
    }
}
