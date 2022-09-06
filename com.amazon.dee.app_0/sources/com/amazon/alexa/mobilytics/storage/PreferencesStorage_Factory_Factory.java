package com.amazon.alexa.mobilytics.storage;

import android.content.Context;
import com.amazon.alexa.mobilytics.internal.JsonConverter;
import com.amazon.alexa.mobilytics.storage.PreferencesStorage;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PreferencesStorage_Factory_Factory implements Factory<PreferencesStorage.Factory> {
    private final Provider<Context> contextProvider;
    private final Provider<JsonConverter> serializerProvider;

    public PreferencesStorage_Factory_Factory(Provider<Context> provider, Provider<JsonConverter> provider2) {
        this.contextProvider = provider;
        this.serializerProvider = provider2;
    }

    public static PreferencesStorage_Factory_Factory create(Provider<Context> provider, Provider<JsonConverter> provider2) {
        return new PreferencesStorage_Factory_Factory(provider, provider2);
    }

    public static PreferencesStorage.Factory newFactory(Context context, JsonConverter jsonConverter) {
        return new PreferencesStorage.Factory(context, jsonConverter);
    }

    public static PreferencesStorage.Factory provideInstance(Provider<Context> provider, Provider<JsonConverter> provider2) {
        return new PreferencesStorage.Factory(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PreferencesStorage.Factory mo10268get() {
        return provideInstance(this.contextProvider, this.serializerProvider);
    }
}
