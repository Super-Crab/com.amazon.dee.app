package com.amazon.deecomms.media;

import com.amazon.deecomms.common.sip.SipClientState;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class VideoStateController_Factory implements Factory<VideoStateController> {
    private final Provider<SipClientState> sipClientStateProvider;

    public VideoStateController_Factory(Provider<SipClientState> provider) {
        this.sipClientStateProvider = provider;
    }

    public static VideoStateController_Factory create(Provider<SipClientState> provider) {
        return new VideoStateController_Factory(provider);
    }

    public static VideoStateController newVideoStateController(SipClientState sipClientState) {
        return new VideoStateController(sipClientState);
    }

    public static VideoStateController provideInstance(Provider<SipClientState> provider) {
        return new VideoStateController(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VideoStateController mo10268get() {
        return provideInstance(this.sipClientStateProvider);
    }
}
