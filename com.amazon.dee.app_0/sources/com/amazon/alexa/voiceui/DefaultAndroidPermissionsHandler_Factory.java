package com.amazon.alexa.voiceui;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class DefaultAndroidPermissionsHandler_Factory implements Factory<DefaultAndroidPermissionsHandler> {
    private final Provider<Context> contextProvider;

    public DefaultAndroidPermissionsHandler_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static DefaultAndroidPermissionsHandler_Factory create(Provider<Context> provider) {
        return new DefaultAndroidPermissionsHandler_Factory(provider);
    }

    public static DefaultAndroidPermissionsHandler newDefaultAndroidPermissionsHandler(Context context) {
        return new DefaultAndroidPermissionsHandler(context);
    }

    public static DefaultAndroidPermissionsHandler provideInstance(Provider<Context> provider) {
        return new DefaultAndroidPermissionsHandler(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DefaultAndroidPermissionsHandler mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
