package com.amazon.alexa.voice.tta.simba;

import com.amazon.alexa.voice.tta.dependencies.DeviceInformationProvider;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class SimbaServiceClient_MembersInjector implements MembersInjector<SimbaServiceClient> {
    private final Provider<DeviceInformationProvider> deviceInformationProvider;
    private final Provider<SimbaClient> simbaClientProvider;

    public SimbaServiceClient_MembersInjector(Provider<SimbaClient> provider, Provider<DeviceInformationProvider> provider2) {
        this.simbaClientProvider = provider;
        this.deviceInformationProvider = provider2;
    }

    public static MembersInjector<SimbaServiceClient> create(Provider<SimbaClient> provider, Provider<DeviceInformationProvider> provider2) {
        return new SimbaServiceClient_MembersInjector(provider, provider2);
    }

    public static void injectDeviceInformationProvider(SimbaServiceClient simbaServiceClient, DeviceInformationProvider deviceInformationProvider) {
        simbaServiceClient.deviceInformationProvider = deviceInformationProvider;
    }

    public static void injectSimbaClient(SimbaServiceClient simbaServiceClient, SimbaClient simbaClient) {
        simbaServiceClient.simbaClient = simbaClient;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(SimbaServiceClient simbaServiceClient) {
        injectSimbaClient(simbaServiceClient, this.simbaClientProvider.mo10268get());
        injectDeviceInformationProvider(simbaServiceClient, this.deviceInformationProvider.mo10268get());
    }
}
