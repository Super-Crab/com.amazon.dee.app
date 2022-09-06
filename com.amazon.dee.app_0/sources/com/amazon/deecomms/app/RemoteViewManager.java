package com.amazon.deecomms.app;

import android.content.Context;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
/* loaded from: classes12.dex */
public class RemoteViewManager extends SurfaceViewManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + RemoteViewManager.class);

    public RemoteViewManager(RelativeLayout relativeLayout, DeviceCallingService deviceCallingService) {
        super(relativeLayout, deviceCallingService);
        LOG.i("RemoteViewManager created");
    }

    private int getColor(int i) {
        return Utils.getColorFromResource(i);
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    @Nullable
    protected SurfaceView getSurfaceView() {
        WebRTCViewRenderer remoteViewRenderer = this.mDeviceCallingService.getRemoteViewRenderer();
        if (remoteViewRenderer != null) {
            return remoteViewRenderer.getSurfaceView();
        }
        return null;
    }

    public void setScalingType(WebRTCViewRenderer.ScalingType scalingType) {
        WebRTCViewRenderer remoteViewRenderer = this.mDeviceCallingService.getRemoteViewRenderer();
        if (remoteViewRenderer != null) {
            remoteViewRenderer.setScalingType(scalingType);
            remoteViewRenderer.getSurfaceView().requestLayout();
        }
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    protected void setSurfaceViewID(@NonNull SurfaceView surfaceView) {
        LOG.i(" Set SurfaceView ID of remoteView ");
        surfaceView.setId(R.id.remote_view);
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    public void setVideoViewBackground(Context context, boolean z, boolean z2) {
        int colorFromResource;
        if (z) {
            colorFromResource = Utils.getColorFromResource(R.color.transparent);
        } else if (CommsDaggerWrapper.getComponent().getCapabilitiesManager().isMosaicThemingEnabled()) {
            colorFromResource = ThemeUtil.getColorFromAttribute(context, R.attr.mosaicBackground);
        } else {
            colorFromResource = Utils.getColorFromResource(R.color.video_call_remote_view_background);
        }
        SurfaceView surfaceView = getSurfaceView();
        if (surfaceView != null) {
            surfaceView.setBackgroundColor(colorFromResource);
        }
    }
}
