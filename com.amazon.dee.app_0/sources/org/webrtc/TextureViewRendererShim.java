package org.webrtc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.webrtc.WebRTCRendererShim;
/* loaded from: classes5.dex */
public class TextureViewRendererShim extends TextureViewRenderer implements WebRTCRendererShim<TextureView> {
    private static final String TAG = TextureViewRendererShim.class.getSimpleName();
    private boolean firstFrameReceived;
    private boolean firstFrameRendered;

    public TextureViewRendererShim(Context context) {
        super(context);
        this.firstFrameRendered = false;
        this.firstFrameReceived = false;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void enableRenderBlack(boolean z) {
        GeneratedOutlineSupport1.outline173("enableRenderBlack enable = ", z, TAG);
    }

    @Override // org.webrtc.WebRTCRendererShim
    public boolean firstFrameReceived() {
        return this.firstFrameReceived;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public boolean firstFrameRendered() {
        return this.firstFrameRendered;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.webrtc.WebRTCRendererShim
    /* renamed from: getView */
    public TextureView mo13076getView() {
        return this;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setFirstFrameReceived() {
        this.firstFrameReceived = true;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setFirstFrameRendered() {
        this.firstFrameRendered = true;
    }

    @Override // org.webrtc.WebRTCRendererShim
    public void setShape(WebRTCRendererShim.Shape shape, int i) {
        Log.e(TAG, "setShape not supported; use ViewOutlineProvider and View#setClipToOutline");
    }

    public TextureViewRendererShim(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.firstFrameRendered = false;
        this.firstFrameReceived = false;
    }
}
