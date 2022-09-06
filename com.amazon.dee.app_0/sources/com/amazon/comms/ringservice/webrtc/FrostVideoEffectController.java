package com.amazon.comms.ringservice.webrtc;

import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.ringservice.FrostVideoEffectCommandBuilder;
import com.amazon.comms.ringservice.VideoEffectCommand;
import com.amazon.comms.ringservice.webrtc.PeerConnectionClient;
import com.amazon.comms.ringservice.webrtc.VideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes12.dex */
public class FrostVideoEffectController implements VideoEffectController, PeerConnectionClient.VideoEffectTransitionListener {
    private static final int DEFAULT_FROST_RADIUS = 64;
    private static final double EPSILON = 1.0E-14d;
    public static final double VIDEO_STRENGTH_CLEAR = 0.0d;
    public static final double VIDEO_STRENGTH_FROSTED = 1.0d;
    public static final double VIDEO_STRENGTH_UNKNOWN = 100.0d;
    private final PeerConnectionClient peerConnectionClient;
    private volatile Call.VideoEffect videoEffect = Call.VideoEffect.None;
    private final VideoEffectController.VideoEffectChangeListener videoEffectChangeListener;
    private static final CommsLogger log = CommsLogger.getLogger(FrostVideoEffectController.class);
    public static final String regEx = "^.*\\sCURRENT:(\\d+\\.\\d+).*";
    private static final Pattern pattern = Pattern.compile(regEx);
    private static final VideoEffectCommand endVideoEffectCommand = new FrostVideoEffectCommandBuilder().getCommand();

    public FrostVideoEffectController(PeerConnectionClient peerConnectionClient, VideoEffectController.VideoEffectChangeListener videoEffectChangeListener) {
        this.peerConnectionClient = peerConnectionClient;
        this.videoEffectChangeListener = videoEffectChangeListener;
    }

    private VideoEffectCommand getEndVideoEffectCommand() {
        return endVideoEffectCommand;
    }

    private double getFrostStrength(String str) {
        double d = 100.0d;
        if (str != null && !str.isEmpty()) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.matches()) {
                try {
                    d = Double.valueOf(matcher.group(1)).doubleValue();
                } catch (NumberFormatException unused) {
                    CommsLogger commsLogger = log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid CURRENT strength value: ");
                    outline107.append(matcher.group(1));
                    commsLogger.e(outline107.toString());
                }
            } else {
                CommsLogger commsLogger2 = log;
                commsLogger2.e("Invalid transition string:" + str);
            }
            CommsLogger commsLogger3 = log;
            commsLogger3.d("TransitionInfo strength=" + d);
            return d;
        }
        log.e("TransitionInfo value returned by platform's camera object is null or empty");
        return 100.0d;
    }

    private VideoEffectCommand getFrostToClearVideoEffectCommand(double d) {
        return new FrostVideoEffectCommandBuilder().setStreamsCount(3).setEffect(FrostVideoEffectCommandBuilder.EffectType.Box).setStrength(0.0f).setTransitionType(FrostVideoEffectCommandBuilder.TransitionType.Normal).setTransitionTime((float) d).setFrostRadius(64).getCommand();
    }

    private VideoEffectCommand getFrostVideoEffectCommand(double d) {
        return new FrostVideoEffectCommandBuilder().setStreamsCount(3).setEffect(FrostVideoEffectCommandBuilder.EffectType.Box).setStrength(1.0f).setTransitionType(FrostVideoEffectCommandBuilder.TransitionType.Normal).setTransitionTime((float) d).setFrostRadius(64).getCommand();
    }

    private void updateVideoEffect(Call.VideoEffect videoEffect) {
        if (videoEffect != this.videoEffect) {
            this.videoEffect = videoEffect;
            VideoEffectController.VideoEffectChangeListener videoEffectChangeListener = this.videoEffectChangeListener;
            if (videoEffectChangeListener == null) {
                return;
            }
            videoEffectChangeListener.onVideoEffectChanged(this);
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoEffectController
    public Call.VideoEffect getVideoEffect() {
        return this.videoEffect;
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.VideoEffectTransitionListener
    public void onAbort() {
        log.i("onAbort");
        updateVideoEffect(Call.VideoEffect.None);
    }

    @Override // com.amazon.comms.ringservice.webrtc.PeerConnectionClient.VideoEffectTransitionListener
    public void onVideoEffectTransition(String str) {
        GeneratedOutlineSupport1.outline159("onPeerConnectionVideoEffectTransition=", str, log);
        double frostStrength = getFrostStrength(str);
        if (this.videoEffect == Call.VideoEffect.None && Math.abs(frostStrength - VIDEO_STRENGTH_CLEAR) > EPSILON) {
            updateVideoEffect(Call.VideoEffect.Frosted);
        } else if (this.videoEffect != Call.VideoEffect.Frosted || Math.abs(frostStrength - VIDEO_STRENGTH_CLEAR) > EPSILON) {
        } else {
            updateVideoEffect(Call.VideoEffect.None);
            this.peerConnectionClient.sendVideoEffectCommand(getEndVideoEffectCommand());
            shutdown();
        }
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoEffectController
    public void shutdown() {
        log.i("shutdown");
        this.peerConnectionClient.stopVideoEffectPipeline();
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoEffectController
    public void start(double d) {
        log.i("start");
        this.peerConnectionClient.initializeVideoEffectPipeline(this);
        this.peerConnectionClient.sendVideoEffectCommand(getFrostVideoEffectCommand(d));
    }

    @Override // com.amazon.comms.ringservice.webrtc.VideoEffectController
    public void stop(double d) {
        log.i("stop");
        if (this.videoEffect != Call.VideoEffect.None) {
            this.peerConnectionClient.sendVideoEffectCommand(getFrostToClearVideoEffectCommand(d));
        }
    }
}
