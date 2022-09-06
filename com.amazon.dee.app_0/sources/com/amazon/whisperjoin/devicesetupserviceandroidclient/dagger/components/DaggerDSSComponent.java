package com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.components;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule_ProvidesAccessTokenProviderFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule_ProvidesMapAccountManagerFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule_ProvidesTokenKeysFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule_ProvidesTokenManagementFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ClockModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ClockModule_ProvidesClockFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ContextModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ContextModule_ProvidesContextFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProvidesDSSRetroInterfaceFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProvidesDSSServiceConfigurationFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProvidesJacksonConverterFactoryFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProvidesOkHttpClientFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule_ProviesObjectMapperFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.RxModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.RxModule_ProvidesBackgroundWorkerSchedulerFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.RxModule_ProvidesMainThreadSchedulerFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.SharedPreferencesModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.SharedPreferencesModule_ProvideSharedPreferencesProviderFactory;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl_MembersInjector;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSRetrofitInterface;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.FFSApiGatewayInterface;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.SharedPreferencesProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes13.dex */
public final class DaggerDSSComponent implements DSSComponent {
    private AuthModule authModule;
    private ClockModule clockModule;
    private ContextModule contextModule;
    private Provider<DSSRetrofitInterface> providesDSSRetroInterfaceProvider;
    private Provider<DSSServiceConfiguration> providesDSSServiceConfigurationProvider;
    private Provider<FFSApiGatewayInterface> providesFFSApiGatewayInterfaceProvider;
    private Provider<JacksonConverterFactory> providesJacksonConverterFactoryProvider;
    private Provider<OkHttpClient> providesOkHttpClientProvider;
    private Provider<ObjectMapper> proviesObjectMapperProvider;
    private RxModule rxModule;
    private SharedPreferencesModule sharedPreferencesModule;

    /* loaded from: classes13.dex */
    public static final class Builder {
        private AuthModule authModule;
        private ClockModule clockModule;
        private ContextModule contextModule;
        private NetworkingModule networkingModule;
        private RxModule rxModule;
        private SharedPreferencesModule sharedPreferencesModule;

        public Builder authModule(AuthModule authModule) {
            this.authModule = (AuthModule) Preconditions.checkNotNull(authModule);
            return this;
        }

        public DSSComponent build() {
            if (this.clockModule == null) {
                this.clockModule = new ClockModule();
            }
            Preconditions.checkBuilderRequirement(this.networkingModule, NetworkingModule.class);
            if (this.authModule == null) {
                this.authModule = new AuthModule();
            }
            Preconditions.checkBuilderRequirement(this.contextModule, ContextModule.class);
            if (this.sharedPreferencesModule == null) {
                this.sharedPreferencesModule = new SharedPreferencesModule();
            }
            if (this.rxModule == null) {
                this.rxModule = new RxModule();
            }
            return new DaggerDSSComponent(this);
        }

        public Builder clockModule(ClockModule clockModule) {
            this.clockModule = (ClockModule) Preconditions.checkNotNull(clockModule);
            return this;
        }

        public Builder contextModule(ContextModule contextModule) {
            this.contextModule = (ContextModule) Preconditions.checkNotNull(contextModule);
            return this;
        }

        public Builder networkingModule(NetworkingModule networkingModule) {
            this.networkingModule = (NetworkingModule) Preconditions.checkNotNull(networkingModule);
            return this;
        }

        public Builder rxModule(RxModule rxModule) {
            this.rxModule = (RxModule) Preconditions.checkNotNull(rxModule);
            return this;
        }

        public Builder sharedPreferencesModule(SharedPreferencesModule sharedPreferencesModule) {
            this.sharedPreferencesModule = (SharedPreferencesModule) Preconditions.checkNotNull(sharedPreferencesModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private MAPAccountManager getMAPAccountManager() {
        return AuthModule_ProvidesMapAccountManagerFactory.proxyProvidesMapAccountManager(this.authModule, ContextModule_ProvidesContextFactory.proxyProvidesContext(this.contextModule));
    }

    private MapAccessTokenProvider getMapAccessTokenProvider() {
        return AuthModule_ProvidesAccessTokenProviderFactory.proxyProvidesAccessTokenProvider(this.authModule, getMAPAccountManager(), getTokenManagement(), getString());
    }

    private SharedPreferencesProvider getSharedPreferencesProvider() {
        return SharedPreferencesModule_ProvideSharedPreferencesProviderFactory.proxyProvideSharedPreferencesProvider(this.sharedPreferencesModule, ContextModule_ProvidesContextFactory.proxyProvidesContext(this.contextModule));
    }

    private String getString() {
        return AuthModule_ProvidesTokenKeysFactory.proxyProvidesTokenKeys(this.authModule, ContextModule_ProvidesContextFactory.proxyProvidesContext(this.contextModule));
    }

    private TokenManagement getTokenManagement() {
        return AuthModule_ProvidesTokenManagementFactory.proxyProvidesTokenManagement(this.authModule, ContextModule_ProvidesContextFactory.proxyProvidesContext(this.contextModule));
    }

    private void initialize(Builder builder) {
        this.providesDSSServiceConfigurationProvider = DoubleCheck.provider(NetworkingModule_ProvidesDSSServiceConfigurationFactory.create(builder.networkingModule));
        this.providesOkHttpClientProvider = DoubleCheck.provider(NetworkingModule_ProvidesOkHttpClientFactory.create(builder.networkingModule, this.providesDSSServiceConfigurationProvider));
        this.proviesObjectMapperProvider = DoubleCheck.provider(NetworkingModule_ProviesObjectMapperFactory.create(builder.networkingModule));
        this.providesJacksonConverterFactoryProvider = DoubleCheck.provider(NetworkingModule_ProvidesJacksonConverterFactoryFactory.create(builder.networkingModule, this.proviesObjectMapperProvider));
        this.providesDSSRetroInterfaceProvider = DoubleCheck.provider(NetworkingModule_ProvidesDSSRetroInterfaceFactory.create(builder.networkingModule, this.providesDSSServiceConfigurationProvider, this.providesOkHttpClientProvider, this.providesJacksonConverterFactoryProvider));
        this.providesFFSApiGatewayInterfaceProvider = DoubleCheck.provider(NetworkingModule_ProvidesFFSApiGatewayInterfaceFactory.create(builder.networkingModule, this.providesDSSServiceConfigurationProvider, this.providesOkHttpClientProvider, this.providesJacksonConverterFactoryProvider));
    }

    private DSSClientImpl injectDSSClientImpl(DSSClientImpl dSSClientImpl) {
        DSSClientImpl_MembersInjector.injectMClock(dSSClientImpl, ClockModule_ProvidesClockFactory.proxyProvidesClock(this.clockModule));
        DSSClientImpl_MembersInjector.injectMDssApi(dSSClientImpl, this.providesDSSRetroInterfaceProvider.mo10268get());
        DSSClientImpl_MembersInjector.injectMFFSApiGatewayInterface(dSSClientImpl, this.providesFFSApiGatewayInterfaceProvider.mo10268get());
        DSSClientImpl_MembersInjector.injectMAccessTokenProvider(dSSClientImpl, getMapAccessTokenProvider());
        DSSClientImpl_MembersInjector.injectMSharedPreferencesProvider(dSSClientImpl, getSharedPreferencesProvider());
        DSSClientImpl_MembersInjector.injectMBackgroundScheduler(dSSClientImpl, RxModule_ProvidesBackgroundWorkerSchedulerFactory.proxyProvidesBackgroundWorkerScheduler(this.rxModule));
        DSSClientImpl_MembersInjector.injectMMainThreadScheduler(dSSClientImpl, RxModule_ProvidesMainThreadSchedulerFactory.proxyProvidesMainThreadScheduler(this.rxModule));
        DSSClientImpl_MembersInjector.injectJacksonConverterFactory(dSSClientImpl, this.providesJacksonConverterFactoryProvider.mo10268get());
        return dSSClientImpl;
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.components.DSSComponent
    public void inject(DSSClientImpl dSSClientImpl) {
        injectDSSClientImpl(dSSClientImpl);
    }

    private DaggerDSSComponent(Builder builder) {
        this.clockModule = builder.clockModule;
        this.contextModule = builder.contextModule;
        this.authModule = builder.authModule;
        this.sharedPreferencesModule = builder.sharedPreferencesModule;
        this.rxModule = builder.rxModule;
        initialize(builder);
    }
}
