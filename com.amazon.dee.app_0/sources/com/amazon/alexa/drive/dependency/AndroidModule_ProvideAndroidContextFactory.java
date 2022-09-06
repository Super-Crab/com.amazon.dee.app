package com.amazon.alexa.drive.dependency;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class AndroidModule_ProvideAndroidContextFactory implements Factory<Context> {
    private final AndroidModule module;

    public AndroidModule_ProvideAndroidContextFactory(AndroidModule androidModule) {
        this.module = androidModule;
    }

    public static AndroidModule_ProvideAndroidContextFactory create(AndroidModule androidModule) {
        return new AndroidModule_ProvideAndroidContextFactory(androidModule);
    }

    public static Context provideInstance(AndroidModule androidModule) {
        return proxyProvideAndroidContext(androidModule);
    }

    public static Context proxyProvideAndroidContext(AndroidModule androidModule) {
        return (Context) Preconditions.checkNotNull(androidModule.provideAndroidContext(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Context mo10268get() {
        return provideInstance(this.module);
    }
}
