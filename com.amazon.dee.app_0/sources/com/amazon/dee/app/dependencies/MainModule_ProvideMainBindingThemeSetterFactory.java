package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.main.MainBindingThemeSetter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMainBindingThemeSetterFactory implements Factory<MainBindingThemeSetter> {
    private final Provider<Activity> activityProvider;
    private final MainModule module;

    public MainModule_ProvideMainBindingThemeSetterFactory(MainModule mainModule, Provider<Activity> provider) {
        this.module = mainModule;
        this.activityProvider = provider;
    }

    public static MainModule_ProvideMainBindingThemeSetterFactory create(MainModule mainModule, Provider<Activity> provider) {
        return new MainModule_ProvideMainBindingThemeSetterFactory(mainModule, provider);
    }

    public static MainBindingThemeSetter provideInstance(MainModule mainModule, Provider<Activity> provider) {
        return proxyProvideMainBindingThemeSetter(mainModule, provider.mo10268get());
    }

    public static MainBindingThemeSetter proxyProvideMainBindingThemeSetter(MainModule mainModule, Activity activity) {
        return (MainBindingThemeSetter) Preconditions.checkNotNull(mainModule.provideMainBindingThemeSetter(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainBindingThemeSetter mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
