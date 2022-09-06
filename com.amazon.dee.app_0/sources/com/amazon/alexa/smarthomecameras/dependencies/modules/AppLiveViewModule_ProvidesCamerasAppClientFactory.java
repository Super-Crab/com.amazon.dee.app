package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesCamerasAppClientFactory implements Factory<CamerasAppClient> {
    private final Provider<Context> contextProvider;

    public AppLiveViewModule_ProvidesCamerasAppClientFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AppLiveViewModule_ProvidesCamerasAppClientFactory create(Provider<Context> provider) {
        return new AppLiveViewModule_ProvidesCamerasAppClientFactory(provider);
    }

    public static CamerasAppClient provideInstance(Provider<Context> provider) {
        return proxyProvidesCamerasAppClient(provider.mo10268get());
    }

    public static CamerasAppClient proxyProvidesCamerasAppClient(Context context) {
        return (CamerasAppClient) Preconditions.checkNotNull(AppLiveViewModule.providesCamerasAppClient(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CamerasAppClient mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
