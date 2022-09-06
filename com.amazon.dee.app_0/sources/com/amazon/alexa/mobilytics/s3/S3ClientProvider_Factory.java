package com.amazon.alexa.mobilytics.s3;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class S3ClientProvider_Factory implements Factory<S3ClientProvider> {
    private static final S3ClientProvider_Factory INSTANCE = new S3ClientProvider_Factory();

    public static S3ClientProvider_Factory create() {
        return INSTANCE;
    }

    public static S3ClientProvider newS3ClientProvider() {
        return new S3ClientProvider();
    }

    public static S3ClientProvider provideInstance() {
        return new S3ClientProvider();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public S3ClientProvider mo10268get() {
        return provideInstance();
    }
}
