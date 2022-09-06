package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.menu.AlexaMenu;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMenuFactory implements Factory<AlexaMenu> {
    private final Provider<Activity> activityProvider;
    private final MainModule module;

    public MainModule_ProvideMenuFactory(MainModule mainModule, Provider<Activity> provider) {
        this.module = mainModule;
        this.activityProvider = provider;
    }

    public static MainModule_ProvideMenuFactory create(MainModule mainModule, Provider<Activity> provider) {
        return new MainModule_ProvideMenuFactory(mainModule, provider);
    }

    public static AlexaMenu provideInstance(MainModule mainModule, Provider<Activity> provider) {
        return proxyProvideMenu(mainModule, provider.mo10268get());
    }

    public static AlexaMenu proxyProvideMenu(MainModule mainModule, Activity activity) {
        return (AlexaMenu) Preconditions.checkNotNull(mainModule.provideMenu(activity), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaMenu mo10268get() {
        return provideInstance(this.module, this.activityProvider);
    }
}
