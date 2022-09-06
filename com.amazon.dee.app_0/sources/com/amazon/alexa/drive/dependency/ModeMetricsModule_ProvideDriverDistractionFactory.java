package com.amazon.alexa.drive.dependency;

import android.content.Context;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes7.dex */
public final class ModeMetricsModule_ProvideDriverDistractionFactory implements Factory<DriverDistractionLog> {
    private final Provider<Context> contextProvider;
    private final ModeMetricsModule module;

    public ModeMetricsModule_ProvideDriverDistractionFactory(ModeMetricsModule modeMetricsModule, Provider<Context> provider) {
        this.module = modeMetricsModule;
        this.contextProvider = provider;
    }

    public static ModeMetricsModule_ProvideDriverDistractionFactory create(ModeMetricsModule modeMetricsModule, Provider<Context> provider) {
        return new ModeMetricsModule_ProvideDriverDistractionFactory(modeMetricsModule, provider);
    }

    public static DriverDistractionLog provideInstance(ModeMetricsModule modeMetricsModule, Provider<Context> provider) {
        return proxyProvideDriverDistraction(modeMetricsModule, provider.mo10268get());
    }

    public static DriverDistractionLog proxyProvideDriverDistraction(ModeMetricsModule modeMetricsModule, Context context) {
        return (DriverDistractionLog) Preconditions.checkNotNull(modeMetricsModule.provideDriverDistraction(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriverDistractionLog mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
