package com.amazon.deecomms.calling.contracts.outgoing;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.contracts.BaseViewContract;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
/* loaded from: classes12.dex */
public interface OutgoingVideoCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    void hideCallScreenTitleMessage();

    void hideCallStatus();

    void hideRemoteParticipantName();

    void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener);

    void showCallControls(boolean z);

    void showCallScreenTitleMessage(String str);

    void showCallStatus(String str);

    void showMaximisedSelfView();

    void showOutgoingCallIsInProgress();

    void showRemoteParticipantName(String str);

    void showSpeakerOff();

    void showSpeakerOn();
}
