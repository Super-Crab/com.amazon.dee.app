package com.amazon.deecomms.calling.contracts.outgoing;

import com.amazon.deecomms.calling.contracts.BaseViewContract;
/* loaded from: classes12.dex */
public interface OutgoingAudioCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    void hideCallScreenTitleMessage();

    void hideCallStatus();

    void hideDialpad();

    void hideRemoteParticipantName();

    void showCallScreenTitleMessage(String str);

    void showCallStatus(String str);

    void showDialpad();

    void showOutgoingCallIsInProgress();

    void showRemoteParticipantName(String str);

    void showSpeakerOff();

    void showSpeakerOn();
}
