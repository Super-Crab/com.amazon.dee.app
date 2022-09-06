package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.utils.PermissionUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class AndroidModule_ProvidePermissionUtilsFactory implements Factory<PermissionUtils> {
    private final AndroidModule module;

    public AndroidModule_ProvidePermissionUtilsFactory(AndroidModule androidModule) {
        this.module = androidModule;
    }

    public static AndroidModule_ProvidePermissionUtilsFactory create(AndroidModule androidModule) {
        return new AndroidModule_ProvidePermissionUtilsFactory(androidModule);
    }

    public static PermissionUtils provideInstance(AndroidModule androidModule) {
        return proxyProvidePermissionUtils(androidModule);
    }

    public static PermissionUtils proxyProvidePermissionUtils(AndroidModule androidModule) {
        return (PermissionUtils) Preconditions.checkNotNull(androidModule.providePermissionUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PermissionUtils mo10268get() {
        return provideInstance(this.module);
    }
}
