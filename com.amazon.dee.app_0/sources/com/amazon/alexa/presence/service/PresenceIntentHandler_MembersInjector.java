package com.amazon.alexa.presence.service;

import android.bluetooth.BluetoothAdapter;
import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.identity.IdentityHelper;
import com.amazon.alexa.presence.library.ContextHelper;
import com.amazon.alexa.presence.utils.BluetoothHelper;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceIntentHandler_MembersInjector implements MembersInjector<PresenceIntentHandler> {
    private final Provider<BLEScannerCallback> mBLEScannerCallbackProvider;
    private final Provider<BluetoothAdapter> mBluetoothAdapterProvider;
    private final Provider<BluetoothHelper> mBluetoothHelperProvider;
    private final Provider<ContextHelper> mContextHelperProvider;
    private final Provider<IdentityHelper> mIdentityHelperProvider;
    private final Provider<MetricsServiceV2> mMetricsServiceV2Provider;

    public PresenceIntentHandler_MembersInjector(Provider<BluetoothAdapter> provider, Provider<MetricsServiceV2> provider2, Provider<BLEScannerCallback> provider3, Provider<IdentityHelper> provider4, Provider<ContextHelper> provider5, Provider<BluetoothHelper> provider6) {
        this.mBluetoothAdapterProvider = provider;
        this.mMetricsServiceV2Provider = provider2;
        this.mBLEScannerCallbackProvider = provider3;
        this.mIdentityHelperProvider = provider4;
        this.mContextHelperProvider = provider5;
        this.mBluetoothHelperProvider = provider6;
    }

    public static MembersInjector<PresenceIntentHandler> create(Provider<BluetoothAdapter> provider, Provider<MetricsServiceV2> provider2, Provider<BLEScannerCallback> provider3, Provider<IdentityHelper> provider4, Provider<ContextHelper> provider5, Provider<BluetoothHelper> provider6) {
        return new PresenceIntentHandler_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectMBLEScannerCallback(PresenceIntentHandler presenceIntentHandler, BLEScannerCallback bLEScannerCallback) {
        presenceIntentHandler.mBLEScannerCallback = bLEScannerCallback;
    }

    public static void injectMBluetoothAdapter(PresenceIntentHandler presenceIntentHandler, BluetoothAdapter bluetoothAdapter) {
        presenceIntentHandler.mBluetoothAdapter = bluetoothAdapter;
    }

    public static void injectMBluetoothHelper(PresenceIntentHandler presenceIntentHandler, BluetoothHelper bluetoothHelper) {
        presenceIntentHandler.mBluetoothHelper = bluetoothHelper;
    }

    public static void injectMContextHelper(PresenceIntentHandler presenceIntentHandler, ContextHelper contextHelper) {
        presenceIntentHandler.mContextHelper = contextHelper;
    }

    public static void injectMIdentityHelper(PresenceIntentHandler presenceIntentHandler, IdentityHelper identityHelper) {
        presenceIntentHandler.mIdentityHelper = identityHelper;
    }

    public static void injectMMetricsServiceV2(PresenceIntentHandler presenceIntentHandler, MetricsServiceV2 metricsServiceV2) {
        presenceIntentHandler.mMetricsServiceV2 = metricsServiceV2;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PresenceIntentHandler presenceIntentHandler) {
        injectMBluetoothAdapter(presenceIntentHandler, this.mBluetoothAdapterProvider.mo10268get());
        injectMMetricsServiceV2(presenceIntentHandler, this.mMetricsServiceV2Provider.mo10268get());
        injectMBLEScannerCallback(presenceIntentHandler, this.mBLEScannerCallbackProvider.mo10268get());
        injectMIdentityHelper(presenceIntentHandler, this.mIdentityHelperProvider.mo10268get());
        injectMContextHelper(presenceIntentHandler, this.mContextHelperProvider.mo10268get());
        injectMBluetoothHelper(presenceIntentHandler, this.mBluetoothHelperProvider.mo10268get());
    }
}
