package com.amazon.alexa.enrollment.module.library;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class EnrollmentLibraryModule_ProvidesEventBusFactory implements Factory<EventBus> {
    private final EnrollmentLibraryModule module;

    public EnrollmentLibraryModule_ProvidesEventBusFactory(EnrollmentLibraryModule enrollmentLibraryModule) {
        this.module = enrollmentLibraryModule;
    }

    public static EnrollmentLibraryModule_ProvidesEventBusFactory create(EnrollmentLibraryModule enrollmentLibraryModule) {
        return new EnrollmentLibraryModule_ProvidesEventBusFactory(enrollmentLibraryModule);
    }

    public static EventBus provideInstance(EnrollmentLibraryModule enrollmentLibraryModule) {
        return proxyProvidesEventBus(enrollmentLibraryModule);
    }

    public static EventBus proxyProvidesEventBus(EnrollmentLibraryModule enrollmentLibraryModule) {
        return (EventBus) Preconditions.checkNotNull(enrollmentLibraryModule.providesEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
