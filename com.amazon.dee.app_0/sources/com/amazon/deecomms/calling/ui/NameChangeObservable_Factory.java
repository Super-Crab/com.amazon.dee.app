package com.amazon.deecomms.calling.ui;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class NameChangeObservable_Factory implements Factory<NameChangeObservable> {
    private static final NameChangeObservable_Factory INSTANCE = new NameChangeObservable_Factory();

    public static NameChangeObservable_Factory create() {
        return INSTANCE;
    }

    public static NameChangeObservable newNameChangeObservable() {
        return new NameChangeObservable();
    }

    public static NameChangeObservable provideInstance() {
        return new NameChangeObservable();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NameChangeObservable mo10268get() {
        return provideInstance();
    }
}
