package com.amazon.deecomms.calling.ui;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class PipVisibilityObservable_Factory implements Factory<PipVisibilityObservable> {
    private static final PipVisibilityObservable_Factory INSTANCE = new PipVisibilityObservable_Factory();

    public static PipVisibilityObservable_Factory create() {
        return INSTANCE;
    }

    public static PipVisibilityObservable newPipVisibilityObservable() {
        return new PipVisibilityObservable();
    }

    public static PipVisibilityObservable provideInstance() {
        return new PipVisibilityObservable();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PipVisibilityObservable mo10268get() {
        return provideInstance();
    }
}
