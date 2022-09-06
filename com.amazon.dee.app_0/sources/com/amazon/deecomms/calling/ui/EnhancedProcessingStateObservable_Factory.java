package com.amazon.deecomms.calling.ui;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class EnhancedProcessingStateObservable_Factory implements Factory<EnhancedProcessingStateObservable> {
    private static final EnhancedProcessingStateObservable_Factory INSTANCE = new EnhancedProcessingStateObservable_Factory();

    public static EnhancedProcessingStateObservable_Factory create() {
        return INSTANCE;
    }

    public static EnhancedProcessingStateObservable newEnhancedProcessingStateObservable() {
        return new EnhancedProcessingStateObservable();
    }

    public static EnhancedProcessingStateObservable provideInstance() {
        return new EnhancedProcessingStateObservable();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnhancedProcessingStateObservable mo10268get() {
        return provideInstance();
    }
}
