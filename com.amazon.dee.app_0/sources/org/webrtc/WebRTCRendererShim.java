package org.webrtc;

import android.view.View;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
/* loaded from: classes5.dex */
public interface WebRTCRendererShim<T extends View> extends VideoRenderer.Callbacks, VideoSink {

    /* loaded from: classes5.dex */
    public enum Shape {
        Circle,
        Rectangle,
        RoundedRectangle
    }

    void enableRenderBlack(boolean z);

    boolean firstFrameReceived();

    boolean firstFrameRendered();

    /* renamed from: getView */
    T mo13076getView();

    void init(EglBase.Context context, RendererCommon.RendererEvents rendererEvents);

    void release();

    void setFirstFrameReceived();

    void setFirstFrameRendered();

    void setMirror(boolean z);

    void setScalingType(RendererCommon.ScalingType scalingType);

    void setShape(Shape shape, int i);
}
