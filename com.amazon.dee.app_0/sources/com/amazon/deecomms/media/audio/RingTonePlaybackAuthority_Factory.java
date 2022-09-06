package com.amazon.deecomms.media.audio;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class RingTonePlaybackAuthority_Factory implements Factory<RingTonePlaybackAuthority> {
    private final Provider<Context> applicationContextProvider;

    public RingTonePlaybackAuthority_Factory(Provider<Context> provider) {
        this.applicationContextProvider = provider;
    }

    public static RingTonePlaybackAuthority_Factory create(Provider<Context> provider) {
        return new RingTonePlaybackAuthority_Factory(provider);
    }

    public static RingTonePlaybackAuthority newRingTonePlaybackAuthority(Context context) {
        return new RingTonePlaybackAuthority(context);
    }

    public static RingTonePlaybackAuthority provideInstance(Provider<Context> provider) {
        return new RingTonePlaybackAuthority(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public RingTonePlaybackAuthority mo10268get() {
        return provideInstance(this.applicationContextProvider);
    }
}
