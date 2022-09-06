package com.amazon.dee.app.dependencies;

import android.app.Activity;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes12.dex */
public final class MainModule_ProvideActivityFactory implements Factory<Activity> {
    private final MainModule module;

    public MainModule_ProvideActivityFactory(MainModule mainModule) {
        this.module = mainModule;
    }

    public static MainModule_ProvideActivityFactory create(MainModule mainModule) {
        return new MainModule_ProvideActivityFactory(mainModule);
    }

    public static Activity provideInstance(MainModule mainModule) {
        return proxyProvideActivity(mainModule);
    }

    public static Activity proxyProvideActivity(MainModule mainModule) {
        return (Activity) Preconditions.checkNotNull(mainModule.provideActivity(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Activity mo10268get() {
        return provideInstance(this.module);
    }
}
