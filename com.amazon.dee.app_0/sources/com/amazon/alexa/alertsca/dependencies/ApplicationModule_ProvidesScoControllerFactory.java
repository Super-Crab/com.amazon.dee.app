package com.amazon.alexa.alertsca.dependencies;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.TelephonyManager;
import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvidesScoControllerFactory implements Factory<BluetoothScoController> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<Context> contextProvider;
    private final ApplicationModule module;
    private final Provider<TelephonyManager> telephonyManagerProvider;

    public ApplicationModule_ProvidesScoControllerFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<AudioManager> provider2, Provider<TelephonyManager> provider3) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.audioManagerProvider = provider2;
        this.telephonyManagerProvider = provider3;
    }

    public static ApplicationModule_ProvidesScoControllerFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<AudioManager> provider2, Provider<TelephonyManager> provider3) {
        return new ApplicationModule_ProvidesScoControllerFactory(applicationModule, provider, provider2, provider3);
    }

    public static BluetoothScoController provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<AudioManager> provider2, Provider<TelephonyManager> provider3) {
        return proxyProvidesScoController(applicationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    public static BluetoothScoController proxyProvidesScoController(ApplicationModule applicationModule, Context context, AudioManager audioManager, TelephonyManager telephonyManager) {
        return (BluetoothScoController) Preconditions.checkNotNull(applicationModule.providesScoController(context, audioManager, telephonyManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothScoController mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.audioManagerProvider, this.telephonyManagerProvider);
    }
}
