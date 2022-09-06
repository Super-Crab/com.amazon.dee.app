package com.amazon.comms.config.base;

import android.content.Context;
import com.amazon.comms.calling.service.VideoConstraints;
import com.amazon.comms.config.DeviceConfig;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.comms.device.AvsDeviceFacade;
import com.amazon.comms.device.KnightDeviceFacade;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import org.joda.time.DateTimeConstants;
/* loaded from: classes11.dex */
public abstract class MultiModalConfig extends DeviceConfig {
    private static final Map<Integer, VideoConstraints> supportedVideoConstraints;

    static {
        TreeMap treeMap = new TreeMap();
        GeneratedOutlineSupport1.outline140(DeviceConfigConstants.VIDEO_WIDTH_320, 180, 15, treeMap, 3600);
        GeneratedOutlineSupport1.outline140(640, 360, 24, treeMap, 21600);
        GeneratedOutlineSupport1.outline140(640, 360, 30, treeMap, 27600);
        GeneratedOutlineSupport1.outline140(960, 540, 24, treeMap, 48600);
        GeneratedOutlineSupport1.outline140(960, 540, 30, treeMap, 61200);
        GeneratedOutlineSupport1.outline140(1280, 720, 24, treeMap, Integer.valueOf((int) DateTimeConstants.SECONDS_PER_DAY));
        GeneratedOutlineSupport1.outline140(1280, 720, 30, treeMap, 108000);
        supportedVideoConstraints = Collections.unmodifiableMap(treeMap);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public AvsDeviceFacade getAvsDeviceFacade(Context context) {
        return new KnightDeviceFacade(context);
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getLocalSurfaceViewShape() {
        return "rectangle";
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getRemoteSurfaceViewShape() {
        return "rectangle";
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public Map<Integer, VideoConstraints> getSupportedResolutions() {
        return supportedVideoConstraints;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUILauncherName() {
        return DeviceConfigConstants.CALLING_UI_LAUNCHER;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUIMainActivity() {
        return DeviceConfigConstants.UI_MAIN_ACTIVITY_CALLING_ACTIVITY;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public String getUINotificationManagerName() {
        return DeviceConfigConstants.CALLING_UI_NOTIFICATION_MANAGER;
    }

    @Override // com.amazon.comms.config.DeviceConfig
    public boolean supportsCallReconnection() {
        return true;
    }
}
