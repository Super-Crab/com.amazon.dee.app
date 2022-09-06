package com.amazon.alexa.alertsca;

import android.content.Context;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ScoAuthority_Factory implements Factory<ScoAuthority> {
    private final Provider<BluetoothScoController> bluetoothScoControllerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ScoPrioritizer> scoPrioritizerProvider;

    public ScoAuthority_Factory(Provider<Context> provider, Provider<BluetoothScoController> provider2, Provider<ScoPrioritizer> provider3) {
        this.contextProvider = provider;
        this.bluetoothScoControllerProvider = provider2;
        this.scoPrioritizerProvider = provider3;
    }

    public static ScoAuthority_Factory create(Provider<Context> provider, Provider<BluetoothScoController> provider2, Provider<ScoPrioritizer> provider3) {
        return new ScoAuthority_Factory(provider, provider2, provider3);
    }

    public static ScoAuthority newScoAuthority(Context context, BluetoothScoController bluetoothScoController, ScoPrioritizer scoPrioritizer) {
        return new ScoAuthority(context, bluetoothScoController, scoPrioritizer);
    }

    public static ScoAuthority provideInstance(Provider<Context> provider, Provider<BluetoothScoController> provider2, Provider<ScoPrioritizer> provider3) {
        return new ScoAuthority(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ScoAuthority mo10268get() {
        return provideInstance(this.contextProvider, this.bluetoothScoControllerProvider, this.scoPrioritizerProvider);
    }
}
