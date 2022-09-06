package com.amazon.alexa.presence.service;

import android.bluetooth.BluetoothAdapter;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.identity.IdentityHelper;
import com.amazon.alexa.presence.library.ContextHelper;
import com.amazon.alexa.presence.utils.BluetoothHelper;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceIntentHandler_Factory implements Factory<PresenceIntentHandler> {
    private final Provider<BLEScannerCallback> mBLEScannerCallbackAndBleScannerCallbackProvider;
    private final Provider<BluetoothAdapter> mBluetoothAdapterAndBluetoothAdapterProvider;
    private final Provider<BluetoothHelper> mBluetoothHelperAndBluetoothHelperProvider;
    private final Provider<ContextHelper> mContextHelperAndContextHelperProvider;
    private final Provider<IdentityHelper> mIdentityHelperAndIdentityHelperProvider;
    private final Provider<MetricsServiceV2> mMetricsServiceV2AndMetricsServiceV2Provider;

    public PresenceIntentHandler_Factory(Provider<BluetoothAdapter> provider, Provider<MetricsServiceV2> provider2, Provider<BLEScannerCallback> provider3, Provider<IdentityHelper> provider4, Provider<BluetoothHelper> provider5, Provider<ContextHelper> provider6) {
        this.mBluetoothAdapterAndBluetoothAdapterProvider = provider;
        this.mMetricsServiceV2AndMetricsServiceV2Provider = provider2;
        this.mBLEScannerCallbackAndBleScannerCallbackProvider = provider3;
        this.mIdentityHelperAndIdentityHelperProvider = provider4;
        this.mBluetoothHelperAndBluetoothHelperProvider = provider5;
        this.mContextHelperAndContextHelperProvider = provider6;
    }

    public static PresenceIntentHandler_Factory create(Provider<BluetoothAdapter> provider, Provider<MetricsServiceV2> provider2, Provider<BLEScannerCallback> provider3, Provider<IdentityHelper> provider4, Provider<BluetoothHelper> provider5, Provider<ContextHelper> provider6) {
        return new PresenceIntentHandler_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static PresenceIntentHandler newPresenceIntentHandler(BluetoothAdapter bluetoothAdapter, MetricsServiceV2 metricsServiceV2, BLEScannerCallback bLEScannerCallback, IdentityHelper identityHelper, BluetoothHelper bluetoothHelper, ContextHelper contextHelper) {
        return new PresenceIntentHandler(bluetoothAdapter, metricsServiceV2, bLEScannerCallback, identityHelper, bluetoothHelper, contextHelper);
    }

    public static PresenceIntentHandler provideInstance(Provider<BluetoothAdapter> provider, Provider<MetricsServiceV2> provider2, Provider<BLEScannerCallback> provider3, Provider<IdentityHelper> provider4, Provider<BluetoothHelper> provider5, Provider<ContextHelper> provider6) {
        PresenceIntentHandler presenceIntentHandler = new PresenceIntentHandler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMBluetoothAdapter(presenceIntentHandler, provider.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMMetricsServiceV2(presenceIntentHandler, provider2.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMBLEScannerCallback(presenceIntentHandler, provider3.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMIdentityHelper(presenceIntentHandler, provider4.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMContextHelper(presenceIntentHandler, provider6.mo10268get());
        PresenceIntentHandler_MembersInjector.injectMBluetoothHelper(presenceIntentHandler, provider5.mo10268get());
        return presenceIntentHandler;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceIntentHandler mo10268get() {
        return provideInstance(this.mBluetoothAdapterAndBluetoothAdapterProvider, this.mMetricsServiceV2AndMetricsServiceV2Provider, this.mBLEScannerCallbackAndBleScannerCallbackProvider, this.mIdentityHelperAndIdentityHelperProvider, this.mBluetoothHelperAndBluetoothHelperProvider, this.mContextHelperAndContextHelperProvider);
    }
}
