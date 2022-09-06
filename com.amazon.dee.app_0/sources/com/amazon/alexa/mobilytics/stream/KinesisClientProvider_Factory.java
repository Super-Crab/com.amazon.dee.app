package com.amazon.alexa.mobilytics.stream;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class KinesisClientProvider_Factory implements Factory<KinesisClientProvider> {
    private static final KinesisClientProvider_Factory INSTANCE = new KinesisClientProvider_Factory();

    public static KinesisClientProvider_Factory create() {
        return INSTANCE;
    }

    public static KinesisClientProvider newKinesisClientProvider() {
        return new KinesisClientProvider();
    }

    public static KinesisClientProvider provideInstance() {
        return new KinesisClientProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public KinesisClientProvider mo10268get() {
        return provideInstance();
    }
}
