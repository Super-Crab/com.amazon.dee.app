package com.amazon.rtcsc.appclient.surfaceview;

import com.amazon.rtcsc.interfaces.IRtcscViewRendererListener;
import com.amazon.rtcsc.utils.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class RtcscViewRendererListener extends IRtcscViewRendererListener.Stub {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscViewRendererListener.class);
    private RTCSurfaceView mSurfaceView;

    public RtcscViewRendererListener(RTCSurfaceView rTCSurfaceView) {
        this.mSurfaceView = null;
        this.mSurfaceView = rTCSurfaceView;
        RtcscLogger rtcscLogger = this.mLog;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Constructor -- mSurfaceView : ");
        outline107.append(this.mSurfaceView);
        rtcscLogger.i(outline107.toString());
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public int getAPIVersion() {
        return 1;
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public int onGetHeight() {
        this.mLog.i("getHeight");
        return this.mSurfaceView.getHeight();
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public int onGetWidth() {
        this.mLog.i("getWidth");
        return this.mSurfaceView.getWidth();
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public void onHolderSetFixedSize(final int i, final int i2) {
        this.mLog.i("onSetFixedSize");
        this.mSurfaceView.post(new Runnable() { // from class: com.amazon.rtcsc.appclient.surfaceview.RtcscViewRendererListener.2
            @Override // java.lang.Runnable
            public void run() {
                RtcscViewRendererListener.this.mSurfaceView.getHolder().setFixedSize(i, i2);
            }
        });
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public void onHolderSetSizeFromLayout() {
        this.mLog.i("onSetSizeFromLayout");
        this.mSurfaceView.post(new Runnable() { // from class: com.amazon.rtcsc.appclient.surfaceview.RtcscViewRendererListener.1
            @Override // java.lang.Runnable
            public void run() {
                RtcscViewRendererListener.this.mSurfaceView.getHolder().setSizeFromLayout();
            }
        });
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public void onMeasuredDimension(int i, int i2) {
        this.mLog.i("onMeasuredDimension");
        this.mSurfaceView.forwardMeasuredDimension(i, i2);
    }

    @Override // com.amazon.rtcsc.interfaces.IRtcscViewRendererListener
    public void onRequestLayout() {
        this.mLog.i("onRequestLayout");
        this.mSurfaceView.post(new Runnable() { // from class: com.amazon.rtcsc.appclient.surfaceview.RtcscViewRendererListener.3
            @Override // java.lang.Runnable
            public void run() {
                RtcscViewRendererListener.this.mSurfaceView.requestLayout();
            }
        });
    }
}
