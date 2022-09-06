package com.amazon.deecomms.calling.incallexperiences.effects;

import dagger.internal.Factory;
/* loaded from: classes12.dex */
public final class EffectsState_Factory implements Factory<EffectsState> {
    private static final EffectsState_Factory INSTANCE = new EffectsState_Factory();

    public static EffectsState_Factory create() {
        return INSTANCE;
    }

    public static EffectsState newEffectsState() {
        return new EffectsState();
    }

    public static EffectsState provideInstance() {
        return new EffectsState();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EffectsState mo10268get() {
        return provideInstance();
    }
}
