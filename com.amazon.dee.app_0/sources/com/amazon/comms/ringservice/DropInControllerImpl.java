package com.amazon.comms.ringservice;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.calling.service.DropInController;
import com.amazon.comms.calling.service.MediaListener;
import com.amazon.comms.calling.service.MediaStateChangeTrigger;
import com.amazon.comms.calling.service.MediaStatus;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class DropInControllerImpl implements DropInController, MediaListener {
    private static final double DEFAULT_TOTAL_FROSTED_TRANSITION_TIME = 2.5d;
    private static final double DEFAULT_TOTAL_FROST_TIME = 10.0d;
    private final Call call;
    private long frostExpireTimeMs;
    private static final CommsLogger log = CommsLogger.getLogger(DropInControllerImpl.class);
    private static final DropInController.UserPreference DEFAULT_USER_PREFERENCE = DropInController.UserPreference.Transition;
    private final int FROST_REENABLE_BUFFER_IN_SECS = 3;
    private boolean reEnableFrosting = false;
    private double totalFrostTime = DEFAULT_TOTAL_FROST_TIME;
    private long frostTimeRemainingMs = ((long) this.totalFrostTime) * 1000;
    private double frostedTransitionTime = DEFAULT_TOTAL_FROSTED_TRANSITION_TIME;
    private DropInController.UserPreference userPreference = DEFAULT_USER_PREFERENCE;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable frostToClearRunnable = new Runnable() { // from class: com.amazon.comms.ringservice.DropInControllerImpl.1
        @Override // java.lang.Runnable
        public void run() {
            DropInControllerImpl.log.i("executing frost clear runnable to remove frosting.");
            DropInControllerImpl.this.call.setVideoEffect(Call.VideoEffect.None, DropInControllerImpl.this.frostedTransitionTime);
        }
    };

    public DropInControllerImpl(Call call) {
        this.call = call;
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public double getFrostedTransitionTime() {
        return this.frostedTransitionTime;
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public double getTotalFrostTime() {
        return this.totalFrostTime;
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public DropInController.UserPreference getUserPreference() {
        return this.userPreference;
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public void initializeWithAcceptParams(Call.AcceptParams acceptParams) {
        boolean isVideoEnabled = acceptParams.isVideoEnabled();
        this.userPreference = acceptParams.getDropInUserPreference();
        this.totalFrostTime = acceptParams.getTotalFrostTime();
        this.frostTimeRemainingMs = ((long) this.totalFrostTime) * 1000;
        this.frostedTransitionTime = acceptParams.getFrostedTransitionTime();
        if (!isVideoEnabled || !this.call.getMediaStatus().isLocalVideoEnabled() || !this.call.isVideoRequested()) {
            return;
        }
        DropInController.UserPreference userPreference = this.userPreference;
        if (userPreference != DropInController.UserPreference.Transition && userPreference != DropInController.UserPreference.AlwaysFrosted) {
            return;
        }
        this.call.registerMediaListener(this);
        this.call.setVideoEffect(Call.VideoEffect.Frosted, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        this.frostExpireTimeMs = SystemClock.elapsedRealtime() + this.frostTimeRemainingMs;
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchDone(boolean z) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onCameraSwitchError(String str) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onDtmfTonesSent(Call call, String str, int i, int i2) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameReceived(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onFirstFrameRendered(Call.Side side) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onLocalFrameResolutionChanged(int i, int i2, int i3) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatsUpdated(Call call) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onMediaStatusUpdated(Call call, Call.Side side, MediaStatus mediaStatus, MediaStateChangeTrigger mediaStateChangeTrigger) {
        if (!mediaStatus.isLocalVideoEnabled() && mediaStateChangeTrigger == MediaStateChangeTrigger.PERMISSION_CHANGE && SystemClock.elapsedRealtime() < this.frostExpireTimeMs) {
            log.i("Re-enable frosting when the video re-starts");
            this.reEnableFrosting = true;
        } else if (!mediaStatus.isLocalVideoEnabled() || !this.reEnableFrosting) {
        } else {
            if (this.frostExpireTimeMs > SystemClock.elapsedRealtime() + DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS) {
                log.i("Re-enabling frosting effect");
                this.frostTimeRemainingMs = this.frostExpireTimeMs - SystemClock.elapsedRealtime();
                call.setVideoEffect(Call.VideoEffect.Frosted, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
                startDefrostTimer(this.call);
            } else {
                this.call.unregisterMediaListener(this);
            }
            this.reEnableFrosting = false;
        }
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onRemoteFrameResolutionChanged(int i, int i2, int i3) {
    }

    @Override // com.amazon.comms.calling.service.MediaListener
    public void onVideoEffectChanged(Call call) {
        if (this.call.getVideoEffect() == Call.VideoEffect.None) {
            this.handler.removeCallbacks(this.frostToClearRunnable);
            if (this.reEnableFrosting) {
                return;
            }
            this.call.unregisterMediaListener(this);
        }
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public boolean shouldShowRemoteView(Call call) {
        return DropInController.UserPreference.AudioOnly != getUserPreference();
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public boolean shouldShowSelfView(Call call) {
        return DropInController.UserPreference.AudioOnly != getUserPreference();
    }

    @Override // com.amazon.comms.calling.service.DropInController
    public void startDefrostTimer(Call call) {
        if (this.userPreference == DropInController.UserPreference.Transition) {
            CommsLogger commsLogger = log;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Starting the defrost timer frostTimeRemainingMs - ");
            outline107.append(this.frostTimeRemainingMs);
            commsLogger.i(outline107.toString());
            this.handler.removeCallbacks(this.frostToClearRunnable);
            this.handler.postDelayed(this.frostToClearRunnable, this.frostTimeRemainingMs);
        }
    }
}
