package com.amazon.alexa.voice.tta.simba;

import com.amazon.alexa.voice.tta.dependencies.DependenciesModule;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule_ProvidesDeviceInformationProviderFactory;
import com.amazon.alexa.voice.tta.dependencies.DependenciesModule_ProvidesGsonFactory;
import com.amazon.alexa.voice.tta.dependencies.DeviceInformationProvider;
import com.amazon.alexa.voice.tta.simba.SimbaServiceClient;
import com.google.gson.Gson;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DaggerSimbaServiceClient_SimbaServiceComponent implements SimbaServiceClient.SimbaServiceComponent {
    private Provider<SimbaClient> provideSimbaClientProvider;
    private Provider<DeviceInformationProvider> providesDeviceInformationProvider;
    private Provider<Gson> providesGsonProvider;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private DependenciesModule dependenciesModule;
        private SimbaModule simbaModule;

        public SimbaServiceClient.SimbaServiceComponent build() {
            if (this.simbaModule == null) {
                this.simbaModule = new SimbaModule();
            }
            if (this.dependenciesModule == null) {
                this.dependenciesModule = new DependenciesModule();
            }
            return new DaggerSimbaServiceClient_SimbaServiceComponent(this);
        }

        public Builder dependenciesModule(DependenciesModule dependenciesModule) {
            this.dependenciesModule = (DependenciesModule) Preconditions.checkNotNull(dependenciesModule);
            return this;
        }

        public Builder simbaModule(SimbaModule simbaModule) {
            this.simbaModule = (SimbaModule) Preconditions.checkNotNull(simbaModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static SimbaServiceClient.SimbaServiceComponent create() {
        return new Builder().build();
    }

    private void initialize(Builder builder) {
        this.providesGsonProvider = DoubleCheck.provider(DependenciesModule_ProvidesGsonFactory.create(builder.dependenciesModule));
        this.provideSimbaClientProvider = DoubleCheck.provider(SimbaModule_ProvideSimbaClientFactory.create(builder.simbaModule, this.providesGsonProvider));
        this.providesDeviceInformationProvider = DoubleCheck.provider(DependenciesModule_ProvidesDeviceInformationProviderFactory.create(builder.dependenciesModule));
    }

    private SimbaServiceClient injectSimbaServiceClient(SimbaServiceClient simbaServiceClient) {
        SimbaServiceClient_MembersInjector.injectSimbaClient(simbaServiceClient, this.provideSimbaClientProvider.mo10268get());
        SimbaServiceClient_MembersInjector.injectDeviceInformationProvider(simbaServiceClient, this.providesDeviceInformationProvider.mo10268get());
        return simbaServiceClient;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaServiceClient.SimbaServiceComponent
    public void inject(SimbaServiceClient simbaServiceClient) {
        injectSimbaServiceClient(simbaServiceClient);
    }

    private DaggerSimbaServiceClient_SimbaServiceComponent(Builder builder) {
        initialize(builder);
    }
}
