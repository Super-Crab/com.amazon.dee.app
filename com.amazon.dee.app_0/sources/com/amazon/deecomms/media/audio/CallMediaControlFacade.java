package com.amazon.deecomms.media.audio;

import android.annotation.SuppressLint;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import androidx.annotation.NonNull;
import com.amazon.deecomms.media.VideoStateController;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class CallMediaControlFacade {
    private final AudioInputController audioInputController;
    private final AudioManager audioManager;
    private final AudioOutputController audioOutputController;
    private final VideoStateController videoStateController;

    @Inject
    public CallMediaControlFacade(@NonNull AudioOutputController audioOutputController, @NonNull AudioInputController audioInputController, @NonNull VideoStateController videoStateController, @NonNull AudioManager audioManager) {
        this.audioOutputController = audioOutputController;
        this.audioInputController = audioInputController;
        this.videoStateController = videoStateController;
        this.audioManager = audioManager;
    }

    @SuppressLint({"WrongConstant"})
    public boolean isHeadphonesPlugged() {
        AudioDeviceInfo[] devices;
        int i = Build.VERSION.SDK_INT;
        for (AudioDeviceInfo audioDeviceInfo : this.audioManager.getDevices(3)) {
            if (audioDeviceInfo.getType() == 4 || audioDeviceInfo.getType() == 3) {
                return true;
            }
        }
        return false;
    }

    public void setCurrentAudioOutputState() {
        this.audioOutputController.setCurrentAudioState();
    }

    public void toggleMic() {
        this.audioInputController.toggleMic();
    }

    public void toggleSpeaker() {
        this.audioOutputController.toggleSpeaker();
    }

    public void toggleVideo() {
        this.videoStateController.toggle();
    }
}
