package com.amazon.deecomms.media.audio;

import android.media.AudioManager;
import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.common.sip.SipClientState;
/* loaded from: classes12.dex */
public class AudioOutputController {
    private final AudioManager audioManager;
    private final AudioStateObservable audioStateObservable;
    private final SipClientState sipClientState;

    public AudioOutputController(@NonNull AudioStateObservable audioStateObservable, @NonNull AudioManager audioManager, @NonNull SipClientState sipClientState) {
        this.audioStateObservable = audioStateObservable;
        this.audioManager = audioManager;
        this.sipClientState = sipClientState;
    }

    private AudioRoutes determineNextAudioRoute(@NonNull AudioRoutes audioRoutes) {
        if (audioRoutes == AudioRoutes.WIREDHEADSET) {
            return determineRouteAfterWiredHeadsetIsUnplugged();
        }
        if (audioRoutes == AudioRoutes.SPEAKER) {
            return determineRouteAfterSpeakerIsTurnedOff();
        }
        return AudioRoutes.EARPIECE;
    }

    private AudioRoutes determineRouteAfterSpeakerIsTurnedOff() {
        if (this.audioManager.isWiredHeadsetOn()) {
            return AudioRoutes.WIREDHEADSET;
        }
        return AudioRoutes.EARPIECE;
    }

    private AudioRoutes determineRouteAfterWiredHeadsetIsUnplugged() {
        if (this.sipClientState.getCurrentActiveCall() != null && this.sipClientState.getCurrentActiveCall().isVideoRequested()) {
            return AudioRoutes.SPEAKER;
        }
        return AudioRoutes.EARPIECE;
    }

    private void updateUI(@NonNull AudioRoutes audioRoutes) {
        this.audioStateObservable.onCallAudioRouteChanged(audioRoutes);
    }

    public void changeAudioRoute(@NonNull AudioRoutes audioRoutes) {
        if (audioRoutes == AudioRoutes.SPEAKER) {
            this.audioManager.setSpeakerphoneOn(true);
        } else if (audioRoutes == AudioRoutes.EARPIECE) {
            this.audioManager.setSpeakerphoneOn(false);
        } else if (audioRoutes != AudioRoutes.WIREDHEADSET) {
        } else {
            this.audioManager.setSpeakerphoneOn(false);
        }
    }

    public void setCurrentAudioState() {
        AudioRoutes audioRoutes;
        if (this.audioManager.isSpeakerphoneOn()) {
            audioRoutes = AudioRoutes.SPEAKER;
        } else {
            audioRoutes = AudioRoutes.EARPIECE;
        }
        updateUI(audioRoutes);
    }

    public void toggleSpeaker() {
        AudioRoutes audioRoutes;
        if (this.audioManager.isSpeakerphoneOn()) {
            audioRoutes = determineRouteAfterSpeakerIsTurnedOff();
        } else {
            audioRoutes = AudioRoutes.SPEAKER;
        }
        changeAudioRoute(audioRoutes);
        updateUI(audioRoutes);
    }

    public void wiredHeadsetStateChanged(boolean z) {
        AudioRoutes determineNextAudioRoute;
        if (z) {
            determineNextAudioRoute = AudioRoutes.WIREDHEADSET;
        } else {
            determineNextAudioRoute = determineNextAudioRoute(AudioRoutes.WIREDHEADSET);
        }
        changeAudioRoute(determineNextAudioRoute);
        updateUI(determineNextAudioRoute);
    }
}
