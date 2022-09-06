package com.amazon.deecomms.calling.contracts.incoming;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.contracts.BaseViewContract;
import com.amazon.deecomms.calling.ui.listener.VideoDisplayListener;
/* loaded from: classes12.dex */
public interface IncomingVideoCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    void hideCallControls();

    void hideCallStatus();

    void hideRemoteParticipantName();

    void setVideoDisplayListener(@NonNull VideoDisplayListener videoDisplayListener);

    void showCallStatus(@NonNull String str);

    void showIncomingCallPermissions(@NonNull String[] strArr);

    void showMaximisedSelfView();

    void showRemoteParticipantName(@NonNull String str);
}
