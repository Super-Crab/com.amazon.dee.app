package com.amazon.rtcsc.service.deviceconfiguration;

import android.content.Context;
import com.amazon.rtcsc.service.deviceconfiguration.alexacompanionapp.A2TF17PFR55MTBConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A10A33FOX2NUBKConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A15996VY63BQ2DConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A1EIANJ7PNB0Q7Config;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A1NL4BVLQ4L3N3Config;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A1XWJRHALS1REPConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A1Z88NGR2BK6A2Config;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.A4ZP7ZC4PI6TOConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.AIPK7MM90V7TBConfig;
import com.amazon.rtcsc.service.deviceconfiguration.echo.multimodal.AWZZ5CVHX2CDConfig;
import com.amazon.rtcsc.utils.RtcscLogger;
/* loaded from: classes13.dex */
public class DeviceConfigMapper {
    private static final String DEFAULT_DEVICE_TYPE = "A1NL4BVLQ4L3N3";
    private static RtcscLogger mLog = RtcscLogger.getLogger(DeviceConfigMapper.class);

    public static String getDeviceConfigInstance(Context context) {
        mLog.i("In DeviceConfigMapper::getDeviceConfigInstance");
        String deviceType = DeviceInfo.getDeviceType(context, "A1NL4BVLQ4L3N3");
        char c = 65535;
        try {
            switch (deviceType.hashCode()) {
                case -2139850095:
                    if (deviceType.equals("AWZZ5CVHX2CD")) {
                        c = 3;
                        break;
                    }
                    break;
                case -2038995837:
                    if (deviceType.equals("A1XWJRHALS1REP")) {
                        c = 6;
                        break;
                    }
                    break;
                case -1942958879:
                    if (deviceType.equals("A1NL4BVLQ4L3N3")) {
                        c = 1;
                        break;
                    }
                    break;
                case -1384655872:
                    if (deviceType.equals("A1EIANJ7PNB0Q7")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -944319628:
                    if (deviceType.equals("A10A33FOX2NUBK")) {
                        c = 0;
                        break;
                    }
                    break;
                case -32246693:
                    if (deviceType.equals("A1Z88NGR2BK6A2")) {
                        c = 4;
                        break;
                    }
                    break;
                case 312760178:
                    if (deviceType.equals("A15996VY63BQ2D")) {
                        c = 7;
                        break;
                    }
                    break;
                case 1084722810:
                    if (deviceType.equals("AIPK7MM90V7TB")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1153488872:
                    if (deviceType.equals("A2TF17PFR55MTB")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1679355419:
                    if (deviceType.equals("A4ZP7ZC4PI6TO")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return A10A33FOX2NUBKConfig.getDeviceConfigInstance();
                case 1:
                    return A1NL4BVLQ4L3N3Config.getDeviceConfigInstance();
                case 2:
                    return A4ZP7ZC4PI6TOConfig.getDeviceConfigInstance();
                case 3:
                    return AWZZ5CVHX2CDConfig.getDeviceConfigInstance();
                case 4:
                    return A1Z88NGR2BK6A2Config.getDeviceConfigInstance();
                case 5:
                    return AIPK7MM90V7TBConfig.getDeviceConfigInstance();
                case 6:
                    return A1XWJRHALS1REPConfig.getDeviceConfigInstance();
                case 7:
                    return A15996VY63BQ2DConfig.getDeviceConfigInstance();
                case '\b':
                    return A1EIANJ7PNB0Q7Config.getDeviceConfigInstance();
                case '\t':
                    return A2TF17PFR55MTBConfig.getDeviceConfigInstance();
                default:
                    RtcscLogger rtcscLogger = mLog;
                    rtcscLogger.w("Failed to get DeviceConfig class for device type:" + deviceType);
                    return DeviceConfiguration.defaultDeviceConfiguration;
            }
        } catch (Exception e) {
            RtcscLogger rtcscLogger2 = mLog;
            rtcscLogger2.e("Failed to get DeviceConfig class, exception:" + e);
            throw new RuntimeException(e);
        }
    }
}
