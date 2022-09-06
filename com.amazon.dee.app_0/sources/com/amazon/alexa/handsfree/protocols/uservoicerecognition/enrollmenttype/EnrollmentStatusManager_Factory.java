package com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype;

import android.content.ContentResolver;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class EnrollmentStatusManager_Factory implements Factory<EnrollmentStatusManager> {
    private final Provider<ContentResolver> contentResolverProvider;

    public EnrollmentStatusManager_Factory(Provider<ContentResolver> provider) {
        this.contentResolverProvider = provider;
    }

    public static EnrollmentStatusManager_Factory create(Provider<ContentResolver> provider) {
        return new EnrollmentStatusManager_Factory(provider);
    }

    public static EnrollmentStatusManager newEnrollmentStatusManager(ContentResolver contentResolver) {
        return new EnrollmentStatusManager(contentResolver);
    }

    public static EnrollmentStatusManager provideInstance(Provider<ContentResolver> provider) {
        return new EnrollmentStatusManager(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EnrollmentStatusManager mo10268get() {
        return provideInstance(this.contentResolverProvider);
    }
}
