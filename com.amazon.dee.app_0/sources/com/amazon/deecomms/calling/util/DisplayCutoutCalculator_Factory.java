package com.amazon.deecomms.calling.util;

import com.amazon.deecomms.util.IBuildVersionProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class DisplayCutoutCalculator_Factory implements Factory<DisplayCutoutCalculator> {
    private final Provider<IBuildVersionProvider> buildVersionProvider;

    public DisplayCutoutCalculator_Factory(Provider<IBuildVersionProvider> provider) {
        this.buildVersionProvider = provider;
    }

    public static DisplayCutoutCalculator_Factory create(Provider<IBuildVersionProvider> provider) {
        return new DisplayCutoutCalculator_Factory(provider);
    }

    public static DisplayCutoutCalculator newDisplayCutoutCalculator(IBuildVersionProvider iBuildVersionProvider) {
        return new DisplayCutoutCalculator(iBuildVersionProvider);
    }

    public static DisplayCutoutCalculator provideInstance(Provider<IBuildVersionProvider> provider) {
        return new DisplayCutoutCalculator(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DisplayCutoutCalculator mo10268get() {
        return provideInstance(this.buildVersionProvider);
    }
}
