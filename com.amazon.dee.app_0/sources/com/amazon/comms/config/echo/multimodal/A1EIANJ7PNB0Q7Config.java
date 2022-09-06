package com.amazon.comms.config.echo.multimodal;

import com.amazon.comms.calling.service.VideoConstraints;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.joda.time.DateTimeConstants;
/* loaded from: classes11.dex */
public class A1EIANJ7PNB0Q7Config extends A1Z88NGR2BK6A2Config {
    private static final String WEBRTC_FIELD_TRIALS = "WebRTC-AMLOGICH264/Enabled-FpsAdjust/WebRTC-Rfc5389StunRetransmissions/Enabled/VideoFrameEmit/Enabled/";
    private static A1EIANJ7PNB0Q7Config a1EIANJ7PNB0Q7Config;
    private static final Map<Integer, VideoConstraints> supportedVideoConstraints;

    static {
        TreeMap treeMap = new TreeMap();
        GeneratedOutlineSupport1.outline140(640, 360, 24, treeMap, 21600);
        GeneratedOutlineSupport1.outline140(640, 360, 30, treeMap, 27600);
        GeneratedOutlineSupport1.outline140(960, 540, 24, treeMap, 48600);
        GeneratedOutlineSupport1.outline140(960, 540, 30, treeMap, 61200);
        GeneratedOutlineSupport1.outline140(1280, 720, 24, treeMap, Integer.valueOf((int) DateTimeConstants.SECONDS_PER_DAY));
        GeneratedOutlineSupport1.outline140(1280, 720, 30, treeMap, 108000);
        supportedVideoConstraints = Collections.unmodifiableMap(treeMap);
        a1EIANJ7PNB0Q7Config = new A1EIANJ7PNB0Q7Config();
    }

    public static A1EIANJ7PNB0Q7Config getDeviceConfigInstance() {
        return a1EIANJ7PNB0Q7Config;
    }

    @Override // com.amazon.comms.config.base.MultiModalConfig, com.amazon.comms.config.DeviceConfig
    public Map<Integer, VideoConstraints> getSupportedResolutions() {
        return supportedVideoConstraints;
    }

    @Override // com.amazon.comms.config.echo.multimodal.A1Z88NGR2BK6A2Config, com.amazon.comms.config.DeviceConfig
    public String getWebRTCFieldTrials() {
        return WEBRTC_FIELD_TRIALS;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isPipSwitchSupported() {
        return true;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean isSmartMotionTrackingSupported() {
        return true;
    }

    @Override // com.amazon.comms.config.echo.multimodal.A1Z88NGR2BK6A2Config, com.amazon.comms.config.DeviceConfig
    public boolean preferCamera1API() {
        return false;
    }

    @Override // com.amazon.comms.config.echo.multimodal.A1Z88NGR2BK6A2Config, com.amazon.comms.config.DeviceConfig
    public boolean updateCameraHalFramerateAllowed() {
        return false;
    }
}
