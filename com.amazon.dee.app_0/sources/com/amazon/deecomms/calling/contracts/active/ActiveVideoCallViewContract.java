package com.amazon.deecomms.calling.contracts.active;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.contracts.BaseViewContract;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
/* loaded from: classes12.dex */
public interface ActiveVideoCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    int getCurrentRotation();

    void hideCallStatus();

    void hideRemoteParticipantName();

    void setKeyboardPanMechanism();

    void setRotationSupported();

    void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener);

    void showAudioPickerPopup();

    void showBluetoothHeadsetOn();

    void showCallDuration(String str);

    void showCallStatus(String str);

    void showEffectsMenuButton();

    void showFrontSelfVideoFeed();

    void showMicMuted();

    void showMicUnMuted();

    void showRealTimeTextIncapable();

    void showRearSelfVideoFeed();

    void showRemoteParticipantNameIfRequired(String str, boolean z);

    void showSpeakerOff();

    void showSpeakerOn();

    void showToggleCamera();

    void showVideoOff();

    void showVideoOffCallStatus();

    void showVideoOn();

    void updateRemoteViewBackground(boolean z);
}
