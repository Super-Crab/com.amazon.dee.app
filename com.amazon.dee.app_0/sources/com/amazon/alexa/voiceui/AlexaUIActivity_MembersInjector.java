package com.amazon.alexa.voiceui;

import com.amazon.alexa.voiceui.voice.VoicePresenter;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class AlexaUIActivity_MembersInjector implements MembersInjector<AlexaUIActivity> {
    private final Provider<VoicePresenter> voicePresenterProvider;

    public AlexaUIActivity_MembersInjector(Provider<VoicePresenter> provider) {
        this.voicePresenterProvider = provider;
    }

    public static MembersInjector<AlexaUIActivity> create(Provider<VoicePresenter> provider) {
        return new AlexaUIActivity_MembersInjector(provider);
    }

    public static void injectVoicePresenter(AlexaUIActivity alexaUIActivity, VoicePresenter voicePresenter) {
        alexaUIActivity.voicePresenter = voicePresenter;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlexaUIActivity alexaUIActivity) {
        injectVoicePresenter(alexaUIActivity, this.voicePresenterProvider.mo10268get());
    }
}
