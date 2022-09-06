package com.amazon.alexa.voice.downchannel;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DownchannelModule_ProvideDownchannelControllerFactory implements Factory<DownchannelController> {
    private final Provider<Context> contextProvider;

    public DownchannelModule_ProvideDownchannelControllerFactory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static DownchannelModule_ProvideDownchannelControllerFactory create(Provider<Context> provider) {
        return new DownchannelModule_ProvideDownchannelControllerFactory(provider);
    }

    public static DownchannelController provideInstance(Provider<Context> provider) {
        return proxyProvideDownchannelController(provider.mo10268get());
    }

    public static DownchannelController proxyProvideDownchannelController(Context context) {
        return (DownchannelController) Preconditions.checkNotNull(DownchannelModule.provideDownchannelController(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DownchannelController mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
