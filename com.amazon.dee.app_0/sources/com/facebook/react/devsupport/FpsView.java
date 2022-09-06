package com.facebook.react.devsupport;

import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import java.util.Locale;
/* loaded from: classes2.dex */
public class FpsView extends FrameLayout {
    private static final int UPDATE_INTERVAL_MS = 500;
    private final FPSMonitorRunnable mFPSMonitorRunnable;
    private final FpsDebugFrameCallback mFrameCallback;
    private final TextView mTextView;

    /* loaded from: classes2.dex */
    private class FPSMonitorRunnable implements Runnable {
        private boolean mShouldStop;
        private int mTotal4PlusFrameStutters;
        private int mTotalFramesDropped;

        private FPSMonitorRunnable() {
            this.mShouldStop = false;
            this.mTotalFramesDropped = 0;
            this.mTotal4PlusFrameStutters = 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mShouldStop) {
                return;
            }
            this.mTotalFramesDropped = (FpsView.this.mFrameCallback.getExpectedNumFrames() - FpsView.this.mFrameCallback.getNumFrames()) + this.mTotalFramesDropped;
            this.mTotal4PlusFrameStutters = FpsView.this.mFrameCallback.get4PlusFrameStutters() + this.mTotal4PlusFrameStutters;
            FpsView fpsView = FpsView.this;
            fpsView.setCurrentFPS(fpsView.mFrameCallback.getFPS(), FpsView.this.mFrameCallback.getJSFPS(), this.mTotalFramesDropped, this.mTotal4PlusFrameStutters);
            FpsView.this.mFrameCallback.reset();
            FpsView.this.postDelayed(this, 500L);
        }

        public void start() {
            this.mShouldStop = false;
            FpsView.this.post(this);
        }

        public void stop() {
            this.mShouldStop = true;
        }
    }

    public FpsView(ReactContext reactContext) {
        super(reactContext);
        FrameLayout.inflate(reactContext, R.layout.fps_view, this);
        this.mTextView = (TextView) findViewById(R.id.fps_text);
        this.mFrameCallback = new FpsDebugFrameCallback(reactContext);
        this.mFPSMonitorRunnable = new FPSMonitorRunnable();
        setCurrentFPS(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentFPS(double d, double d2, int i, int i2) {
        String format = String.format(Locale.US, "UI: %.1f fps\n%d dropped so far\n%d stutters (4+) so far\nJS: %.1f fps", Double.valueOf(d), Integer.valueOf(i), Integer.valueOf(i2), Double.valueOf(d2));
        this.mTextView.setText(format);
        FLog.d(ReactConstants.TAG, format);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFrameCallback.reset();
        this.mFrameCallback.start();
        this.mFPSMonitorRunnable.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFrameCallback.stop();
        this.mFPSMonitorRunnable.stop();
    }
}
