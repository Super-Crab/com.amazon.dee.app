package com.amazon.deecomms.app;

import android.content.Context;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public abstract class SurfaceViewManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + SurfaceViewManager.class);
    protected final DeviceCallingService mDeviceCallingService;
    private boolean mIsStarted = false;
    private final RelativeLayout mRelativeLayout;

    public SurfaceViewManager(RelativeLayout relativeLayout, DeviceCallingService deviceCallingService) {
        this.mRelativeLayout = relativeLayout;
        if (!CallUtils.isDropInCall()) {
            this.mRelativeLayout.setVisibility(4);
        }
        this.mDeviceCallingService = deviceCallingService;
        if (this.mDeviceCallingService == null) {
            LOG.i(" DCS is null");
        } else {
            LOG.i(" DCS is not null");
        }
        LOG.i("SurfaceViewWrapperManager created");
    }

    @Nullable
    protected abstract SurfaceView getSurfaceView();

    public boolean isStarted() {
        return this.mIsStarted;
    }

    protected abstract void setSurfaceViewID(@NonNull SurfaceView surfaceView);

    public abstract void setVideoViewBackground(Context context, boolean z, boolean z2);

    public void start() {
        if (!this.mIsStarted) {
            SurfaceView surfaceView = getSurfaceView();
            if (surfaceView != null) {
                this.mIsStarted = true;
                ViewGroup viewGroup = (ViewGroup) surfaceView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                setSurfaceViewID(surfaceView);
                this.mRelativeLayout.addView(surfaceView);
            }
            this.mRelativeLayout.setVisibility(0);
            LOG.i("Start of SurfaceViewManager");
        }
    }

    public void stop() {
        if (this.mIsStarted) {
            this.mIsStarted = false;
            this.mRelativeLayout.removeAllViews();
            this.mRelativeLayout.setVisibility(4);
            LOG.i("Stop of SurfaceView manager");
        }
    }
}
