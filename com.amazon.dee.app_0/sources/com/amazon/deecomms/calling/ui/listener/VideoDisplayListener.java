package com.amazon.deecomms.calling.ui.listener;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
/* loaded from: classes12.dex */
public interface VideoDisplayListener {
    void hidePip(boolean z);

    void hideScrim();

    void hideSelfView();

    void maximizeVideo(boolean z);

    void minimizeVideo(@NonNull int i, boolean z);

    void onSetBackground(boolean z, boolean z2, boolean z3);

    void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType, boolean z);

    void showPip(boolean z);

    void showScrim();
}
