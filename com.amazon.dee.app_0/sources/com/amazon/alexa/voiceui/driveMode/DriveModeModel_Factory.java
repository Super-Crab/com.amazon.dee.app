package com.amazon.alexa.voiceui.driveMode;

import com.amazon.alexa.voiceui.AlexaServicesApis;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DriveModeModel_Factory implements Factory<DriveModeModel> {
    private final Provider<AlexaServicesApis> alexaServicesApisProvider;

    public DriveModeModel_Factory(Provider<AlexaServicesApis> provider) {
        this.alexaServicesApisProvider = provider;
    }

    public static DriveModeModel_Factory create(Provider<AlexaServicesApis> provider) {
        return new DriveModeModel_Factory(provider);
    }

    public static DriveModeModel newDriveModeModel(AlexaServicesApis alexaServicesApis) {
        return new DriveModeModel(alexaServicesApis);
    }

    public static DriveModeModel provideInstance(Provider<AlexaServicesApis> provider) {
        return new DriveModeModel(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DriveModeModel mo10268get() {
        return provideInstance(this.alexaServicesApisProvider);
    }
}
