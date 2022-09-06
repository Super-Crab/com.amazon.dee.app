package com.amazon.alexa.fitness.service.hds;

import dagger.internal.Factory;
/* loaded from: classes8.dex */
public final class HdsThreadHandlerImpl_Factory implements Factory<HdsThreadHandlerImpl> {
    private static final HdsThreadHandlerImpl_Factory INSTANCE = new HdsThreadHandlerImpl_Factory();

    public static HdsThreadHandlerImpl_Factory create() {
        return INSTANCE;
    }

    public static HdsThreadHandlerImpl newHdsThreadHandlerImpl() {
        return new HdsThreadHandlerImpl();
    }

    public static HdsThreadHandlerImpl provideInstance() {
        return new HdsThreadHandlerImpl();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HdsThreadHandlerImpl mo10268get() {
        return provideInstance();
    }
}
