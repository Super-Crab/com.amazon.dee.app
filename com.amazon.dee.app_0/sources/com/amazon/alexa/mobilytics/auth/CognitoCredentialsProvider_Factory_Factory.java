package com.amazon.alexa.mobilytics.auth;

import android.content.Context;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class CognitoCredentialsProvider_Factory_Factory implements Factory<CognitoCredentialsProvider.Factory> {
    private final Provider<Context> contextProvider;

    public CognitoCredentialsProvider_Factory_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static CognitoCredentialsProvider_Factory_Factory create(Provider<Context> provider) {
        return new CognitoCredentialsProvider_Factory_Factory(provider);
    }

    public static CognitoCredentialsProvider.Factory newFactory(Context context) {
        return new CognitoCredentialsProvider.Factory(context);
    }

    public static CognitoCredentialsProvider.Factory provideInstance(Provider<Context> provider) {
        return new CognitoCredentialsProvider.Factory(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CognitoCredentialsProvider.Factory mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
