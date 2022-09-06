package com.amazon.deecomms.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.calling.service.WebRTCViewRenderer;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.accessibility.RealTimeTextEnablementAuthority;
import com.amazon.deecomms.calling.model.CallContext;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.DeviceInfo;
/* loaded from: classes12.dex */
public class SelfViewManager extends SurfaceViewManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG + SelfViewManager.class);
    private final CallAddedBroadcastReceiver callAddedListener;
    private boolean isVideoOn;
    private final Context mContext;
    private WebRTCViewRenderer mLocalRenderer;
    private RelativeLayout mRelativeLayout;
    private LinearLayout mScrim;
    private final RealTimeTextEnablementAuthority realTimeTextEnablementAuthority;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class CallAddedBroadcastReceiver extends BroadcastReceiver {
        private CallAddedBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!CommsDaggerWrapper.getComponent().getCallManager().isAnyActiveCallPresent() || !CallUtils.isVideoOrDropInVideoCall()) {
                return;
            }
            SelfViewManager selfViewManager = SelfViewManager.this;
            selfViewManager.mLocalRenderer = selfViewManager.mDeviceCallingService.getLocalViewRenderer();
            SelfViewManager.this.start();
        }

        /* synthetic */ CallAddedBroadcastReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    public SelfViewManager(@NonNull RelativeLayout relativeLayout, @NonNull DeviceCallingService deviceCallingService, @NonNull LinearLayout linearLayout, @NonNull Context context, @NonNull CallContext callContext, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        super(relativeLayout, deviceCallingService);
        this.mLocalRenderer = null;
        this.callAddedListener = new CallAddedBroadcastReceiver(null);
        this.isVideoOn = true;
        this.mRelativeLayout = relativeLayout;
        this.mScrim = linearLayout;
        this.mContext = context;
        this.mLocalRenderer = deviceCallingService.getLocalViewRenderer();
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        if (callContext == null || !callContext.isVideoCall()) {
            return;
        }
        setupVideoCall(context);
    }

    private int getColor(int i) {
        return Utils.getColorFromResource(i);
    }

    private int getDimension(int i) {
        return Utils.getDimensionFromResource(i);
    }

    private void setPipParamsForTablet(CoordinatorLayout.LayoutParams layoutParams, int i, double d) {
        if (i != 1 && i != 3) {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_tablet_width) * d);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_tablet_height) * d);
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_tablet_width) * d);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_tablet_height) * d);
        }
        layoutParams.setMarginEnd(Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_end));
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = Utils.getDimensionFromResource(R.dimen.videocall_toolbar_top_margin);
        layoutParams.gravity = GravityCompat.END;
        this.mRelativeLayout.setLayoutParams(layoutParams);
    }

    private void setupVideoCall(@NonNull Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this.callAddedListener, new IntentFilter(Constants.CALL_ADDED));
        WebRTCViewRenderer webRTCViewRenderer = this.mLocalRenderer;
        if (webRTCViewRenderer != null) {
            webRTCViewRenderer.setMirror(true);
            this.mLocalRenderer.getSurfaceView().setZOrderMediaOverlay(true);
        }
    }

    public void bringToFront() {
        this.mRelativeLayout.bringToFront();
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    @Nullable
    protected SurfaceView getSurfaceView() {
        LOG.i(" Get SurfaceView in SelfViewManager ");
        WebRTCViewRenderer webRTCViewRenderer = this.mLocalRenderer;
        if (webRTCViewRenderer != null) {
            return webRTCViewRenderer.getSurfaceView();
        }
        return null;
    }

    public void hideScrim() {
        LOG.i(" Hide scrim");
        this.mScrim.setVisibility(8);
    }

    public void hideSelfView() {
        setSelfViewVisibility(8);
    }

    public void maximizeSelfView() {
        LOG.i(" Maximize self View ");
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(this.mRelativeLayout.getLayoutParams());
        layoutParams.setMargins(0, 0, 0, 0);
        ((ViewGroup.MarginLayoutParams) layoutParams).width = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = -1;
        this.mRelativeLayout.setLayoutParams(layoutParams);
    }

    public void minimizeSelfView(int i) {
        LOG.i(" Minimize Self View ");
        CoordinatorLayout.LayoutParams layoutParams = new CoordinatorLayout.LayoutParams(this.mRelativeLayout.getLayoutParams());
        if (!DeviceInfo.isPhone(this.mContext)) {
            setPipParamsForTablet(layoutParams, i, this.mContext.getResources().getInteger(R.integer.size) / 6.5d);
            return;
        }
        if (i == 1) {
            layoutParams.setMarginStart(Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_landscape_start_end));
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_landscape_top_bottom);
            layoutParams.gravity = GravityCompat.START;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_width) * 1.0d);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_height) * 1.0d);
        } else if (i != 3) {
            layoutParams.setMarginEnd(Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_end));
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = Utils.getDimensionFromResource(R.dimen.videocall_toolbar_top_margin);
            layoutParams.gravity = GravityCompat.END;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_width) * 1.0d);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_height) * 1.0d);
        } else {
            layoutParams.setMarginEnd(Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_landscape_start_end));
            if (this.realTimeTextEnablementAuthority.isRTTAvailableForTheCall()) {
                layoutParams.gravity = GravityCompat.END;
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = Utils.getDimensionFromResource(R.dimen.videocall_toolbar_top_margin);
            } else {
                layoutParams.gravity = 8388693;
                ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = Utils.getDimensionFromResource(R.dimen.video_call_self_view_margin_landscape_top_bottom);
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_width) * 1.0d);
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) (Utils.getDimensionFromResource(R.dimen.video_call_self_view_landscape_height) * 1.0d);
        }
        this.mRelativeLayout.setLayoutParams(layoutParams);
    }

    public void removePIP() {
        this.mRelativeLayout.removeAllViews();
        this.mRelativeLayout.setVisibility(8);
    }

    public void setContentDescription(@NonNull String str) {
        this.mRelativeLayout.setContentDescription(str);
    }

    public void setMirror(boolean z) {
        WebRTCViewRenderer webRTCViewRenderer = this.mLocalRenderer;
        if (webRTCViewRenderer != null) {
            webRTCViewRenderer.setMirror(z);
        }
    }

    @VisibleForTesting
    void setSelfViewVisibility(int i) {
        View findViewById = this.mRelativeLayout.findViewById(R.id.self_view);
        if (findViewById != null) {
            findViewById.setVisibility(i);
        }
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    protected void setSurfaceViewID(@NonNull SurfaceView surfaceView) {
        surfaceView.setId(R.id.self_view);
    }

    public void setToggleCamera(ImageView imageView, RelativeLayout.LayoutParams layoutParams) {
        this.mRelativeLayout.removeView(imageView);
        this.mRelativeLayout.addView(imageView, layoutParams);
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    public void setVideoViewBackground(Context context, boolean z, boolean z2) {
        int colorFromResource;
        this.isVideoOn = z;
        if (z2) {
            if (z) {
                colorFromResource = Utils.getColorFromResource(R.color.transparent);
                setSelfViewVisibility(0);
            } else {
                colorFromResource = Utils.getColorFromResource(R.color.video_call_self_view_background);
                this.mLocalRenderer.setScalingType(WebRTCViewRenderer.ScalingType.SCALE_ASPECT_FILL);
                setSelfViewVisibility(4);
            }
            SurfaceView surfaceView = this.mLocalRenderer.getSurfaceView();
            if (surfaceView == null) {
                return;
            }
            surfaceView.setBackgroundColor(colorFromResource);
        }
    }

    public void showScrim() {
        LOG.i(" Show scrim");
        this.mScrim.setVisibility(0);
    }

    public void showSelfView() {
        if (this.isVideoOn) {
            setSelfViewVisibility(0);
        }
    }

    @Override // com.amazon.deecomms.app.SurfaceViewManager
    public void stop() {
        super.stop();
        LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.callAddedListener);
    }

    public SelfViewManager(@NonNull RelativeLayout relativeLayout, @NonNull DeviceCallingService deviceCallingService, @NonNull LinearLayout linearLayout, @NonNull Context context, @NonNull RealTimeTextEnablementAuthority realTimeTextEnablementAuthority) {
        super(relativeLayout, deviceCallingService);
        this.mLocalRenderer = null;
        this.callAddedListener = new CallAddedBroadcastReceiver(null);
        this.isVideoOn = true;
        this.mRelativeLayout = relativeLayout;
        this.mScrim = linearLayout;
        this.mContext = context;
        this.mLocalRenderer = deviceCallingService.getLocalViewRenderer();
        this.realTimeTextEnablementAuthority = realTimeTextEnablementAuthority;
        setupVideoCall(context);
    }
}
