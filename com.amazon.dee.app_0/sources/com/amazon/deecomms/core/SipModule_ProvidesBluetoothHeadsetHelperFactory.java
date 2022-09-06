package com.amazon.deecomms.core;

import android.content.Context;
import com.amazon.deecomms.calling.service.BluetoothHeadsetHelper;
import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class SipModule_ProvidesBluetoothHeadsetHelperFactory implements Factory<BluetoothHeadsetHelper> {
    private final Provider<Context> applicationContextProvider;
    private final SipModule module;
    private final Provider<SipClientState> sipClientStateProvider;

    public SipModule_ProvidesBluetoothHeadsetHelperFactory(SipModule sipModule, Provider<Context> provider, Provider<SipClientState> provider2) {
        this.module = sipModule;
        this.applicationContextProvider = provider;
        this.sipClientStateProvider = provider2;
    }

    public static SipModule_ProvidesBluetoothHeadsetHelperFactory create(SipModule sipModule, Provider<Context> provider, Provider<SipClientState> provider2) {
        return new SipModule_ProvidesBluetoothHeadsetHelperFactory(sipModule, provider, provider2);
    }

    public static BluetoothHeadsetHelper provideInstance(SipModule sipModule, Provider<Context> provider, Provider<SipClientState> provider2) {
        return (BluetoothHeadsetHelper) Preconditions.checkNotNull(sipModule.providesBluetoothHeadsetHelper(provider.mo10268get(), provider2.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static BluetoothHeadsetHelper proxyProvidesBluetoothHeadsetHelper(SipModule sipModule, Context context, SipClientState sipClientState) {
        return (BluetoothHeadsetHelper) Preconditions.checkNotNull(sipModule.providesBluetoothHeadsetHelper(context, sipClientState), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothHeadsetHelper mo10268get() {
        return provideInstance(this.module, this.applicationContextProvider, this.sipClientStateProvider);
    }
}
