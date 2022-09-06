package com.amazon.deecomms.calling.ui.listener;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.CallState;
import com.amazon.deecomms.calling.enums.CallType;
/* loaded from: classes12.dex */
public interface VideoStreamingListener {
    void onCameraToggledToFront(@NonNull CallState callState, @NonNull CallType callType);

    void onCameraToggledToRear(@NonNull CallState callState, @NonNull CallType callType);

    void onStartStreamingVideo(@NonNull CallState callState, @NonNull CallType callType);

    void onStopStreamingVideo(@NonNull CallState callState, @NonNull CallType callType);
}
