package com.amazon.deecomms.perms;

import android.content.Context;
import com.amazon.deecomms.util.IBuildVersionProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VoicePermissionsAuthority_Factory implements Factory<VoicePermissionsAuthority> {
    private final Provider<IBuildVersionProvider> buildVersionProvider;
    private final Provider<Context> contextProvider;

    public VoicePermissionsAuthority_Factory(Provider<Context> provider, Provider<IBuildVersionProvider> provider2) {
        this.contextProvider = provider;
        this.buildVersionProvider = provider2;
    }

    public static VoicePermissionsAuthority_Factory create(Provider<Context> provider, Provider<IBuildVersionProvider> provider2) {
        return new VoicePermissionsAuthority_Factory(provider, provider2);
    }

    public static VoicePermissionsAuthority newVoicePermissionsAuthority(Context context, IBuildVersionProvider iBuildVersionProvider) {
        return new VoicePermissionsAuthority(context, iBuildVersionProvider);
    }

    public static VoicePermissionsAuthority provideInstance(Provider<Context> provider, Provider<IBuildVersionProvider> provider2) {
        return new VoicePermissionsAuthority(provider.mo10268get(), provider2.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoicePermissionsAuthority mo10268get() {
        return provideInstance(this.contextProvider, this.buildVersionProvider);
    }
}
