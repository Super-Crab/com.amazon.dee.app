package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.util.PrefsDialogHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class ModeModule_ProvidePrefsDialogHelperFactory implements Factory<PrefsDialogHelper> {
    private final ModeModule module;

    public ModeModule_ProvidePrefsDialogHelperFactory(ModeModule modeModule) {
        this.module = modeModule;
    }

    public static ModeModule_ProvidePrefsDialogHelperFactory create(ModeModule modeModule) {
        return new ModeModule_ProvidePrefsDialogHelperFactory(modeModule);
    }

    public static PrefsDialogHelper provideInstance(ModeModule modeModule) {
        return proxyProvidePrefsDialogHelper(modeModule);
    }

    public static PrefsDialogHelper proxyProvidePrefsDialogHelper(ModeModule modeModule) {
        return (PrefsDialogHelper) Preconditions.checkNotNull(modeModule.providePrefsDialogHelper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PrefsDialogHelper mo10268get() {
        return provideInstance(this.module);
    }
}
