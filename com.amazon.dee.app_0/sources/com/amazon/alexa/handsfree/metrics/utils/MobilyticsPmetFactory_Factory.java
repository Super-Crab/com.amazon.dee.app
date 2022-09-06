package com.amazon.alexa.handsfree.metrics.utils;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class MobilyticsPmetFactory_Factory implements Factory<MobilyticsPmetFactory> {
    private static final MobilyticsPmetFactory_Factory INSTANCE = new MobilyticsPmetFactory_Factory();

    public static MobilyticsPmetFactory_Factory create() {
        return INSTANCE;
    }

    public static MobilyticsPmetFactory newMobilyticsPmetFactory() {
        return new MobilyticsPmetFactory();
    }

    public static MobilyticsPmetFactory provideInstance() {
        return new MobilyticsPmetFactory();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsPmetFactory mo10268get() {
        return provideInstance();
    }
}
