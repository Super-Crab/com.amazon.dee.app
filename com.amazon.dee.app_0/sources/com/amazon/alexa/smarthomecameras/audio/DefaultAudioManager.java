package com.amazon.alexa.smarthomecameras.audio;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes10.dex */
public class DefaultAudioManager implements AudioManager {
    private static final String TAG = "DefaultAudioManager";
    private final CamerasAppClient appClient;
    private final android.media.AudioManager audioManager;

    public DefaultAudioManager(Context context, CamerasAppClient camerasAppClient) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(camerasAppClient, "App Client cannot be null");
        this.appClient = camerasAppClient;
        this.audioManager = (android.media.AudioManager) context.getSystemService("audio");
    }

    @Override // com.amazon.alexa.smarthomecameras.audio.AudioManager
    public void setMicrophoneMute(boolean z, String str) {
        GeneratedOutlineSupport1.outline172("setMicrophoneMute ", z);
        this.appClient.setLocalAudioState(str, !z);
    }

    @Override // com.amazon.alexa.smarthomecameras.audio.AudioManager
    public void setSpeakerOn(boolean z, String str) {
        GeneratedOutlineSupport1.outline172("setSpeakerOn ", z);
        this.appClient.setRemoteAudioState(str, z);
        this.audioManager.setSpeakerphoneOn(z);
    }

    @Override // com.amazon.alexa.smarthomecameras.audio.AudioManager
    public void teardown() {
        this.audioManager.setSpeakerphoneOn(false);
    }
}
