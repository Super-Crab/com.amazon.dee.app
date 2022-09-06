package com.amazon.dee.app.dependencies;

import com.amazon.dee.app.ui.main.MainActivity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideMainActivityFactory implements Factory<MainActivity> {
    private final MainModule module;

    public MainModule_ProvideMainActivityFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideMainActivityFactory create(MainModule mainModule) {
        return new MainModule_ProvideMainActivityFactory(mainModule);
    }

    public static MainActivity provideInstance(MainModule mainModule) {
        return proxyProvideMainActivity(mainModule);
    }

    public static MainActivity proxyProvideMainActivity(MainModule mainModule) {
        return (MainActivity) Preconditions.checkNotNull(mainModule.provideMainActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MainActivity mo10268get() {
        return provideInstance(this.module);
    }
}
