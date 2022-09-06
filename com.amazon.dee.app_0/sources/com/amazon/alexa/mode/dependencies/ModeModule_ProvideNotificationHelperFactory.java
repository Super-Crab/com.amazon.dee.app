package com.amazon.alexa.mode.dependencies;

import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazon.alexa.mode.util.NotificationHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class ModeModule_ProvideNotificationHelperFactory implements Factory<NotificationHelper> {
    private final Provider<DriveModeMetrics> driveModeMetricsProvider;
    private final ModeModule module;

    public ModeModule_ProvideNotificationHelperFactory(ModeModule modeModule, Provider<DriveModeMetrics> provider) {
        this.module = modeModule;
        this.driveModeMetricsProvider = provider;
    }

    public static ModeModule_ProvideNotificationHelperFactory create(ModeModule modeModule, Provider<DriveModeMetrics> provider) {
        return new ModeModule_ProvideNotificationHelperFactory(modeModule, provider);
    }

    public static NotificationHelper provideInstance(ModeModule modeModule, Provider<DriveModeMetrics> provider) {
        return proxyProvideNotificationHelper(modeModule, DoubleCheck.lazy(provider));
    }

    public static NotificationHelper proxyProvideNotificationHelper(ModeModule modeModule, Lazy<DriveModeMetrics> lazy) {
        return (NotificationHelper) Preconditions.checkNotNull(modeModule.provideNotificationHelper(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationHelper mo10268get() {
        return provideInstance(this.module, this.driveModeMetricsProvider);
    }
}
