package com.amazon.alexa.voiceui.chrome;

import com.amazon.alexa.voiceui.RouterDelegate;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class VoiceChromeView_Factory implements Factory<VoiceChromeView> {
    private final Provider<RouterDelegate> voiceChromeRouterProvider;

    public VoiceChromeView_Factory(Provider<RouterDelegate> provider) {
        this.voiceChromeRouterProvider = provider;
    }

    public static VoiceChromeView_Factory create(Provider<RouterDelegate> provider) {
        return new VoiceChromeView_Factory(provider);
    }

    public static VoiceChromeView newVoiceChromeView(RouterDelegate routerDelegate) {
        return new VoiceChromeView(routerDelegate);
    }

    public static VoiceChromeView provideInstance(Provider<RouterDelegate> provider) {
        return new VoiceChromeView(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public VoiceChromeView mo10268get() {
        return provideInstance(this.voiceChromeRouterProvider);
    }
}
