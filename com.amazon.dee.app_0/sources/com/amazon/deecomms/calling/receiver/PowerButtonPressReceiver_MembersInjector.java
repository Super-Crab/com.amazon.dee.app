package com.amazon.deecomms.calling.receiver;

import com.amazon.deecomms.common.audio.AlexaAudioPlayer;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class PowerButtonPressReceiver_MembersInjector implements MembersInjector<PowerButtonPressReceiver> {
    private final Provider<AlexaAudioPlayer> audioPlayerProvider;

    public PowerButtonPressReceiver_MembersInjector(Provider<AlexaAudioPlayer> provider) {
        this.audioPlayerProvider = provider;
    }

    public static MembersInjector<PowerButtonPressReceiver> create(Provider<AlexaAudioPlayer> provider) {
        return new PowerButtonPressReceiver_MembersInjector(provider);
    }

    public static void injectAudioPlayer(PowerButtonPressReceiver powerButtonPressReceiver, AlexaAudioPlayer alexaAudioPlayer) {
        powerButtonPressReceiver.audioPlayer = alexaAudioPlayer;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(PowerButtonPressReceiver powerButtonPressReceiver) {
        powerButtonPressReceiver.audioPlayer = this.audioPlayerProvider.mo10268get();
    }
}
