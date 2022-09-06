package com.amazon.alexa.voice.model;

import android.content.Context;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class ModelModule_ProvideHandsFreeSupportCheckerFactory implements Factory<HandsFreeSupportChecker> {
    private final Provider<AbiCompatibilityInterface> compatibilityInterfaceProvider;
    private final Provider<Context> contextProvider;

    public ModelModule_ProvideHandsFreeSupportCheckerFactory(Provider<Context> provider, Provider<AbiCompatibilityInterface> provider2) {
        this.contextProvider = provider;
        this.compatibilityInterfaceProvider = provider2;
    }

    public static ModelModule_ProvideHandsFreeSupportCheckerFactory create(Provider<Context> provider, Provider<AbiCompatibilityInterface> provider2) {
        return new ModelModule_ProvideHandsFreeSupportCheckerFactory(provider, provider2);
    }

    public static HandsFreeSupportChecker provideInstance(Provider<Context> provider, Provider<AbiCompatibilityInterface> provider2) {
        return proxyProvideHandsFreeSupportChecker(provider.mo10268get(), provider2.mo10268get());
    }

    public static HandsFreeSupportChecker proxyProvideHandsFreeSupportChecker(Context context, AbiCompatibilityInterface abiCompatibilityInterface) {
        return (HandsFreeSupportChecker) Preconditions.checkNotNull(ModelModule.provideHandsFreeSupportChecker(context, abiCompatibilityInterface), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HandsFreeSupportChecker mo10268get() {
        return provideInstance(this.contextProvider, this.compatibilityInterfaceProvider);
    }
}
