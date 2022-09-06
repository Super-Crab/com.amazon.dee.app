package com.amazon.alexa.handsfree.settings.voxsettings;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class VoxSettingsEnqueuer_Factory implements Factory<VoxSettingsEnqueuer> {
    private static final VoxSettingsEnqueuer_Factory INSTANCE = new VoxSettingsEnqueuer_Factory();

    public static VoxSettingsEnqueuer_Factory create() {
        return INSTANCE;
    }

    public static VoxSettingsEnqueuer newVoxSettingsEnqueuer() {
        return new VoxSettingsEnqueuer();
    }

    public static VoxSettingsEnqueuer provideInstance() {
        return new VoxSettingsEnqueuer();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoxSettingsEnqueuer mo10268get() {
        return provideInstance();
    }
}
