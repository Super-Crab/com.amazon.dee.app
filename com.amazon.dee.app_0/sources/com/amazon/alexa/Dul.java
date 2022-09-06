package com.amazon.alexa;

import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import dagger.internal.Factory;
import javax.inject.Provider;
/* compiled from: SpecialCaseOverrideAuthority_Factory.java */
/* loaded from: classes.dex */
public final class Dul implements Factory<vVi> {
    public final Provider<Wyh> BIo;
    public final Provider<BluetoothScoController> zZm;

    public Dul(Provider<BluetoothScoController> provider, Provider<Wyh> provider2) {
        this.zZm = provider;
        this.BIo = provider2;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        return new vVi(this.zZm.mo10268get(), this.BIo.mo10268get());
    }
}
