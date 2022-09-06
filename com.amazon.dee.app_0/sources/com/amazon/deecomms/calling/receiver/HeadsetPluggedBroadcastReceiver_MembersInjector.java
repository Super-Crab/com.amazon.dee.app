package com.amazon.deecomms.calling.receiver;

import android.media.AudioManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.media.audio.AudioOutputController;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class HeadsetPluggedBroadcastReceiver_MembersInjector implements MembersInjector<HeadsetPluggedBroadcastReceiver> {
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<AudioOutputController> audioOutputControllerProvider;
    private final Provider<CapabilitiesManager> capabilitiesManagerProvider;
    private final Provider<SipClientState> sipClientStateProvider;

    public HeadsetPluggedBroadcastReceiver_MembersInjector(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2, Provider<AudioManager> provider3, Provider<AudioOutputController> provider4) {
        this.sipClientStateProvider = provider;
        this.capabilitiesManagerProvider = provider2;
        this.audioManagerProvider = provider3;
        this.audioOutputControllerProvider = provider4;
    }

    public static MembersInjector<HeadsetPluggedBroadcastReceiver> create(Provider<SipClientState> provider, Provider<CapabilitiesManager> provider2, Provider<AudioManager> provider3, Provider<AudioOutputController> provider4) {
        return new HeadsetPluggedBroadcastReceiver_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectAudioManager(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver, AudioManager audioManager) {
        headsetPluggedBroadcastReceiver.audioManager = audioManager;
    }

    public static void injectAudioOutputController(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver, AudioOutputController audioOutputController) {
        headsetPluggedBroadcastReceiver.audioOutputController = audioOutputController;
    }

    public static void injectCapabilitiesManager(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver, CapabilitiesManager capabilitiesManager) {
        headsetPluggedBroadcastReceiver.capabilitiesManager = capabilitiesManager;
    }

    public static void injectSipClientState(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver, SipClientState sipClientState) {
        headsetPluggedBroadcastReceiver.sipClientState = sipClientState;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(HeadsetPluggedBroadcastReceiver headsetPluggedBroadcastReceiver) {
        headsetPluggedBroadcastReceiver.sipClientState = this.sipClientStateProvider.mo10268get();
        headsetPluggedBroadcastReceiver.capabilitiesManager = this.capabilitiesManagerProvider.mo10268get();
        headsetPluggedBroadcastReceiver.audioManager = this.audioManagerProvider.mo10268get();
        headsetPluggedBroadcastReceiver.audioOutputController = this.audioOutputControllerProvider.mo10268get();
    }
}
