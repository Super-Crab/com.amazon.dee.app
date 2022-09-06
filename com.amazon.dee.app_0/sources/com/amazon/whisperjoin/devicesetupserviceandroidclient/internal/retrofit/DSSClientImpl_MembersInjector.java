package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Clock;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.SharedPreferencesProvider;
import dagger.MembersInjector;
import io.reactivex.rxjava3.core.Scheduler;
import javax.inject.Provider;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes13.dex */
public final class DSSClientImpl_MembersInjector implements MembersInjector<DSSClientImpl> {
    private final Provider<JacksonConverterFactory> jacksonConverterFactoryProvider;
    private final Provider<MapAccessTokenProvider> mAccessTokenProvider;
    private final Provider<Scheduler> mBackgroundSchedulerProvider;
    private final Provider<Clock> mClockProvider;
    private final Provider<DSSRetrofitInterface> mDssApiProvider;
    private final Provider<FFSApiGatewayInterface> mFFSApiGatewayInterfaceProvider;
    private final Provider<Scheduler> mMainThreadSchedulerProvider;
    private final Provider<SharedPreferencesProvider> mSharedPreferencesProvider;

    public DSSClientImpl_MembersInjector(Provider<Clock> provider, Provider<DSSRetrofitInterface> provider2, Provider<FFSApiGatewayInterface> provider3, Provider<MapAccessTokenProvider> provider4, Provider<SharedPreferencesProvider> provider5, Provider<Scheduler> provider6, Provider<Scheduler> provider7, Provider<JacksonConverterFactory> provider8) {
        this.mClockProvider = provider;
        this.mDssApiProvider = provider2;
        this.mFFSApiGatewayInterfaceProvider = provider3;
        this.mAccessTokenProvider = provider4;
        this.mSharedPreferencesProvider = provider5;
        this.mBackgroundSchedulerProvider = provider6;
        this.mMainThreadSchedulerProvider = provider7;
        this.jacksonConverterFactoryProvider = provider8;
    }

    public static MembersInjector<DSSClientImpl> create(Provider<Clock> provider, Provider<DSSRetrofitInterface> provider2, Provider<FFSApiGatewayInterface> provider3, Provider<MapAccessTokenProvider> provider4, Provider<SharedPreferencesProvider> provider5, Provider<Scheduler> provider6, Provider<Scheduler> provider7, Provider<JacksonConverterFactory> provider8) {
        return new DSSClientImpl_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public static void injectJacksonConverterFactory(DSSClientImpl dSSClientImpl, JacksonConverterFactory jacksonConverterFactory) {
        dSSClientImpl.jacksonConverterFactory = jacksonConverterFactory;
    }

    public static void injectMAccessTokenProvider(DSSClientImpl dSSClientImpl, MapAccessTokenProvider mapAccessTokenProvider) {
        dSSClientImpl.mAccessTokenProvider = mapAccessTokenProvider;
    }

    public static void injectMBackgroundScheduler(DSSClientImpl dSSClientImpl, Scheduler scheduler) {
        dSSClientImpl.mBackgroundScheduler = scheduler;
    }

    public static void injectMClock(DSSClientImpl dSSClientImpl, Clock clock) {
        dSSClientImpl.mClock = clock;
    }

    public static void injectMDssApi(DSSClientImpl dSSClientImpl, DSSRetrofitInterface dSSRetrofitInterface) {
        dSSClientImpl.mDssApi = dSSRetrofitInterface;
    }

    public static void injectMFFSApiGatewayInterface(DSSClientImpl dSSClientImpl, FFSApiGatewayInterface fFSApiGatewayInterface) {
        dSSClientImpl.mFFSApiGatewayInterface = fFSApiGatewayInterface;
    }

    public static void injectMMainThreadScheduler(DSSClientImpl dSSClientImpl, Scheduler scheduler) {
        dSSClientImpl.mMainThreadScheduler = scheduler;
    }

    public static void injectMSharedPreferencesProvider(DSSClientImpl dSSClientImpl, SharedPreferencesProvider sharedPreferencesProvider) {
        dSSClientImpl.mSharedPreferencesProvider = sharedPreferencesProvider;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(DSSClientImpl dSSClientImpl) {
        injectMClock(dSSClientImpl, this.mClockProvider.mo10268get());
        injectMDssApi(dSSClientImpl, this.mDssApiProvider.mo10268get());
        injectMFFSApiGatewayInterface(dSSClientImpl, this.mFFSApiGatewayInterfaceProvider.mo10268get());
        injectMAccessTokenProvider(dSSClientImpl, this.mAccessTokenProvider.mo10268get());
        injectMSharedPreferencesProvider(dSSClientImpl, this.mSharedPreferencesProvider.mo10268get());
        injectMBackgroundScheduler(dSSClientImpl, this.mBackgroundSchedulerProvider.mo10268get());
        injectMMainThreadScheduler(dSSClientImpl, this.mMainThreadSchedulerProvider.mo10268get());
        injectJacksonConverterFactory(dSSClientImpl, this.jacksonConverterFactoryProvider.mo10268get());
    }
}
