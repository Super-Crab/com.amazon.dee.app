package com.amazon.deecomms.calling.contracts.incoming;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.contracts.BaseViewContract;
/* loaded from: classes12.dex */
public interface IncomingAudioCallViewContract<T> extends BaseViewContract<T> {
    void activateCallControls();

    void hideCallControls();

    void hideCallStatus();

    void hideRemoteParticipantName();

    void showCallStatus(@NonNull String str);

    void showIncomingCallPermissions(@NonNull String[] strArr);

    void showRemoteParticipantName(@NonNull String str);
}
