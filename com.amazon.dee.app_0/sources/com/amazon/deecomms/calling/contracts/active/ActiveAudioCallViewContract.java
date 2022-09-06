package com.amazon.deecomms.calling.contracts.active;

import com.amazon.deecomms.calling.contracts.BaseViewContract;
/* loaded from: classes12.dex */
public interface ActiveAudioCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    void hideCallStatus();

    void hideDialpad();

    void hideErrorMessage();

    void hideRemoteParticipantName();

    void setKeyboardPanMechanism();

    void showAudioPickerPopup();

    void showBluetoothHeadsetOn();

    void showCallDuration(String str);

    void showCallStatus(String str);

    void showCallVideoDowngradedUI();

    void showDialpad();

    void showErrorMessage();

    void showMicMuted();

    void showMicUnMuted();

    void showRealTimeTextIncapable();

    void showRemoteParticipantName(String str);

    void showSpeakerOff();

    void showSpeakerOn();
}
