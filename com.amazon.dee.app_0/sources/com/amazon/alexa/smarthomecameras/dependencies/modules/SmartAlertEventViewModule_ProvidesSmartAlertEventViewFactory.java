package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.view.SmartAlertEventView;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory implements Factory<SmartAlertEventView> {
    private final Provider<Context> contextProvider;
    private final Provider<String> imageUrlProvider;
    private final SmartAlertEventViewModule module;

    public SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory(SmartAlertEventViewModule smartAlertEventViewModule, Provider<Context> provider, Provider<String> provider2) {
        this.module = smartAlertEventViewModule;
        this.contextProvider = provider;
        this.imageUrlProvider = provider2;
    }

    public static SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory create(SmartAlertEventViewModule smartAlertEventViewModule, Provider<Context> provider, Provider<String> provider2) {
        return new SmartAlertEventViewModule_ProvidesSmartAlertEventViewFactory(smartAlertEventViewModule, provider, provider2);
    }

    public static SmartAlertEventView provideInstance(SmartAlertEventViewModule smartAlertEventViewModule, Provider<Context> provider, Provider<String> provider2) {
        return proxyProvidesSmartAlertEventView(smartAlertEventViewModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static SmartAlertEventView proxyProvidesSmartAlertEventView(SmartAlertEventViewModule smartAlertEventViewModule, Context context, String str) {
        return (SmartAlertEventView) Preconditions.checkNotNull(smartAlertEventViewModule.providesSmartAlertEventView(context, str), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SmartAlertEventView mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.imageUrlProvider);
    }
}
