package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.ui.main.ThemeRecorder;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideThemeRecorderFactory implements Factory<ThemeRecorder> {
    private final Provider<Context> contextProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<Mobilytics> mobilyticsServiceProvider;
    private final MainModule module;

    public MainModule_ProvideThemeRecorderFactory(MainModule mainModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<IdentityService> provider3) {
        this.module = mainModule;
        this.contextProvider = provider;
        this.mobilyticsServiceProvider = provider2;
        this.identityServiceProvider = provider3;
    }

    public static MainModule_ProvideThemeRecorderFactory create(MainModule mainModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<IdentityService> provider3) {
        return new MainModule_ProvideThemeRecorderFactory(mainModule, provider, provider2, provider3);
    }

    public static ThemeRecorder provideInstance(MainModule mainModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<IdentityService> provider3) {
        return proxyProvideThemeRecorder(mainModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3));
    }

    public static ThemeRecorder proxyProvideThemeRecorder(MainModule mainModule, Context context, Lazy<Mobilytics> lazy, Lazy<IdentityService> lazy2) {
        return (ThemeRecorder) Preconditions.checkNotNull(mainModule.provideThemeRecorder(context, lazy, lazy2), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ThemeRecorder mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mobilyticsServiceProvider, this.identityServiceProvider);
    }
}
