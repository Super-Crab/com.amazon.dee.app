package com.amazon.tarazed.dagger.modules;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import kotlinx.coroutines.CoroutineScope;
/* loaded from: classes13.dex */
public final class GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory implements Factory<CoroutineScope> {
    private final GlobalModule module;

    public GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory(GlobalModule globalModule) {
        this.module = globalModule;
    }

    public static GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory create(GlobalModule globalModule) {
        return new GlobalModule_ProvideMainCoroutineScope$TarazedAndroidLibrary_releaseFactory(globalModule);
    }

    public static CoroutineScope provideInstance(GlobalModule globalModule) {
        return proxyProvideMainCoroutineScope$TarazedAndroidLibrary_release(globalModule);
    }

    public static CoroutineScope proxyProvideMainCoroutineScope$TarazedAndroidLibrary_release(GlobalModule globalModule) {
        return (CoroutineScope) Preconditions.checkNotNull(globalModule.provideMainCoroutineScope$TarazedAndroidLibrary_release(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get  reason: collision with other method in class */
    public CoroutineScope mo10268get() {
        return provideInstance(this.module);
    }
}
