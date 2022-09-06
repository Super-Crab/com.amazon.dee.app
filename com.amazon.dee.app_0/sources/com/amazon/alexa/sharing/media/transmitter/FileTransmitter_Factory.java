package com.amazon.alexa.sharing.media.transmitter;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.alexa.sharing.util.SharedPreferenceUtils;
import com.amazon.clouddrive.extended.AmazonCloudDriveExtended;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class FileTransmitter_Factory implements Factory<FileTransmitter> {
    private final Provider<AmazonCloudDriveExtended> cloudDriveClientProvider;
    private final Provider<CommsMetricsEmitter> commsMetricsEmitterProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<SharedPreferenceUtils> sharedPreferenceUtilsProvider;

    public FileTransmitter_Factory(Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtended> provider3, Provider<EventBus> provider4, Provider<SharedPreferenceUtils> provider5, Provider<CommsMetricsEmitter> provider6) {
        this.contextProvider = provider;
        this.identityServiceProvider = provider2;
        this.cloudDriveClientProvider = provider3;
        this.eventBusProvider = provider4;
        this.sharedPreferenceUtilsProvider = provider5;
        this.commsMetricsEmitterProvider = provider6;
    }

    public static FileTransmitter_Factory create(Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtended> provider3, Provider<EventBus> provider4, Provider<SharedPreferenceUtils> provider5, Provider<CommsMetricsEmitter> provider6) {
        return new FileTransmitter_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static FileTransmitter newFileTransmitter(Context context, IdentityService identityService, AmazonCloudDriveExtended amazonCloudDriveExtended, EventBus eventBus, SharedPreferenceUtils sharedPreferenceUtils, CommsMetricsEmitter commsMetricsEmitter) {
        return new FileTransmitter(context, identityService, amazonCloudDriveExtended, eventBus, sharedPreferenceUtils, commsMetricsEmitter);
    }

    public static FileTransmitter provideInstance(Provider<Context> provider, Provider<IdentityService> provider2, Provider<AmazonCloudDriveExtended> provider3, Provider<EventBus> provider4, Provider<SharedPreferenceUtils> provider5, Provider<CommsMetricsEmitter> provider6) {
        return new FileTransmitter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public FileTransmitter mo10268get() {
        return provideInstance(this.contextProvider, this.identityServiceProvider, this.cloudDriveClientProvider, this.eventBusProvider, this.sharedPreferenceUtilsProvider, this.commsMetricsEmitterProvider);
    }
}
