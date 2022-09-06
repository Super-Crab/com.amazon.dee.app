package com.amazon.alexa.sharing.comms.dependencies;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.OkHttpClient;
/* loaded from: classes10.dex */
public final class AlexaSharingModule_ProvideHttpClientFactory implements Factory<OkHttpClient> {
    private final AlexaSharingModule module;

    public AlexaSharingModule_ProvideHttpClientFactory(AlexaSharingModule alexaSharingModule) {
        this.module = alexaSharingModule;
    }

    public static AlexaSharingModule_ProvideHttpClientFactory create(AlexaSharingModule alexaSharingModule) {
        return new AlexaSharingModule_ProvideHttpClientFactory(alexaSharingModule);
    }

    public static OkHttpClient provideInstance(AlexaSharingModule alexaSharingModule) {
        return proxyProvideHttpClient(alexaSharingModule);
    }

    public static OkHttpClient proxyProvideHttpClient(AlexaSharingModule alexaSharingModule) {
        return (OkHttpClient) Preconditions.checkNotNull(alexaSharingModule.provideHttpClient(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public OkHttpClient mo10268get() {
        return provideInstance(this.module);
    }
}
