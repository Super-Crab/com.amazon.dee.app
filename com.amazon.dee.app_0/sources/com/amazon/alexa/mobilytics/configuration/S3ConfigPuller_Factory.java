package com.amazon.alexa.mobilytics.configuration;

import com.amazon.alexa.mobilytics.s3.S3ClientProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class S3ConfigPuller_Factory implements Factory<S3ConfigPuller> {
    private final Provider<S3ClientProvider> clientProvider;

    public S3ConfigPuller_Factory(Provider<S3ClientProvider> provider) {
        this.clientProvider = provider;
    }

    public static S3ConfigPuller_Factory create(Provider<S3ClientProvider> provider) {
        return new S3ConfigPuller_Factory(provider);
    }

    public static S3ConfigPuller newS3ConfigPuller(S3ClientProvider s3ClientProvider) {
        return new S3ConfigPuller(s3ClientProvider);
    }

    public static S3ConfigPuller provideInstance(Provider<S3ClientProvider> provider) {
        return new S3ConfigPuller(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public S3ConfigPuller mo10268get() {
        return provideInstance(this.clientProvider);
    }
}
