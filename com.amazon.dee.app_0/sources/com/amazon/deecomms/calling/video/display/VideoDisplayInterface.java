package com.amazon.deecomms.calling.video.display;

import androidx.annotation.NonNull;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
/* loaded from: classes12.dex */
public interface VideoDisplayInterface {
    void hideSelfView();

    void maximiseVideo();

    void minimizeVideo(int i);

    void removePIP();

    void setBackground(boolean z, boolean z2);

    void setScalingType(@NonNull WebRTCViewRenderer.ScalingType scalingType);

    void showSelfView();
}
