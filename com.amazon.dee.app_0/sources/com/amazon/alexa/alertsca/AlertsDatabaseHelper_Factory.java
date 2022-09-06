package com.amazon.alexa.alertsca;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertsDatabaseHelper_Factory implements Factory<AlertsDatabaseHelper> {
    private final Provider<Context> contextProvider;

    public AlertsDatabaseHelper_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public static AlertsDatabaseHelper_Factory create(Provider<Context> provider) {
        return new AlertsDatabaseHelper_Factory(provider);
    }

    public static AlertsDatabaseHelper newAlertsDatabaseHelper(Context context) {
        return new AlertsDatabaseHelper(context);
    }

    public static AlertsDatabaseHelper provideInstance(Provider<Context> provider) {
        return new AlertsDatabaseHelper(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlertsDatabaseHelper mo10268get() {
        return provideInstance(this.contextProvider);
    }
}
