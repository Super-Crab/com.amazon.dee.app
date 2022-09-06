package com.amazon.deecomms.calling.model;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class BeginCallMapper_Factory implements Factory<BeginCallMapper> {
    private static final BeginCallMapper_Factory INSTANCE = new BeginCallMapper_Factory();

    public static BeginCallMapper_Factory create() {
        return INSTANCE;
    }

    public static BeginCallMapper newBeginCallMapper() {
        return new BeginCallMapper();
    }

    public static BeginCallMapper provideInstance() {
        return new BeginCallMapper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BeginCallMapper mo10268get() {
        return provideInstance();
    }
}
