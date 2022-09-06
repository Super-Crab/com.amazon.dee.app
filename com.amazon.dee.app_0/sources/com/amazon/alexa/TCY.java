package com.amazon.alexa;

import android.app.ActivityManager;
import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: UserInterfaceManager_Factory.java */
/* loaded from: classes.dex */
public final class TCY implements Factory<vYS> {
    public final Provider<peZ> BIo;
    public final Provider<rqw> Qle;
    public final Provider<ActivityManager> jiA;
    public final Provider<IYJ> zQM;
    public final Provider<Context> zZm;
    public final Provider<Msx> zyO;

    public TCY(Provider<Context> provider, Provider<peZ> provider2, Provider<IYJ> provider3, Provider<Msx> provider4, Provider<ActivityManager> provider5, Provider<rqw> provider6) {
        this.zZm = provider;
        this.BIo = provider2;
        this.zQM = provider3;
        this.zyO = provider4;
        this.jiA = provider5;
        this.Qle = provider6;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new vYS(this.zZm.mo10268get(), this.BIo.mo10268get(), this.zQM.mo10268get(), this.zyO.mo10268get(), this.jiA.mo10268get(), this.Qle.mo10268get());
    }
}
