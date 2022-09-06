package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class AmazonMessagingModule_ProvideADMFactory implements Factory<ADM> {
    private final Provider<Context> contextProvider;
    private final AmazonMessagingModule module;

    public AmazonMessagingModule_ProvideADMFactory(AmazonMessagingModule amazonMessagingModule, Provider<Context> provider) {
        this.module = amazonMessagingModule;
        this.contextProvider = provider;
    }

    public static AmazonMessagingModule_ProvideADMFactory create(AmazonMessagingModule amazonMessagingModule, Provider<Context> provider) {
        return new AmazonMessagingModule_ProvideADMFactory(amazonMessagingModule, provider);
    }

    public static ADM provideInstance(AmazonMessagingModule amazonMessagingModule, Provider<Context> provider) {
        return proxyProvideADM(amazonMessagingModule, provider.mo10268get());
    }

    public static ADM proxyProvideADM(AmazonMessagingModule amazonMessagingModule, Context context) {
        return (ADM) Preconditions.checkNotNull(amazonMessagingModule.provideADM(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ADM mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
