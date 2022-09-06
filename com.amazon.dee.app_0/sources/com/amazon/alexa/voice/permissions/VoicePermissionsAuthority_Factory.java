package com.amazon.alexa.voice.permissions;

import android.content.Context;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoicePermissionsAuthority_Factory implements Factory<VoicePermissionsAuthority> {
    private final Provider<ApplicationLifecycleService> applicationLifecycleServiceProvider;
    private final Provider<Context> contextProvider;

    public VoicePermissionsAuthority_Factory(Provider<Context> provider, Provider<ApplicationLifecycleService> provider2) {
        this.contextProvider = provider;
        this.applicationLifecycleServiceProvider = provider2;
    }

    public static VoicePermissionsAuthority_Factory create(Provider<Context> provider, Provider<ApplicationLifecycleService> provider2) {
        return new VoicePermissionsAuthority_Factory(provider, provider2);
    }

    public static VoicePermissionsAuthority newVoicePermissionsAuthority(Context context, ApplicationLifecycleService applicationLifecycleService) {
        return new VoicePermissionsAuthority(context, applicationLifecycleService);
    }

    public static VoicePermissionsAuthority provideInstance(Provider<Context> provider, Provider<ApplicationLifecycleService> provider2) {
        return new VoicePermissionsAuthority(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoicePermissionsAuthority mo10268get() {
        return provideInstance(this.contextProvider, this.applicationLifecycleServiceProvider);
    }
}
