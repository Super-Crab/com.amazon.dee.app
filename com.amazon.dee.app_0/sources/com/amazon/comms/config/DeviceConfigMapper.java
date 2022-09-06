package com.amazon.comms.config;

import android.content.Context;
import com.amazon.comms.config.alexacompanionapp.A2TF17PFR55MTBConfig;
import com.amazon.comms.config.echo.headless.A32DOYMUN6DTXAConfig;
import com.amazon.comms.config.echo.headless.HeadlessFos5FastMixerCommonConfig;
import com.amazon.comms.config.echo.headless.HeadlessFos6FastMixerCommonConfig;
import com.amazon.comms.config.echo.headless.HeadlessSpeakerlessConfig;
import com.amazon.comms.config.echo.multimodal.A10A33FOX2NUBKConfig;
import com.amazon.comms.config.echo.multimodal.A15996VY63BQ2DConfig;
import com.amazon.comms.config.echo.multimodal.A1EIANJ7PNB0Q7Config;
import com.amazon.comms.config.echo.multimodal.A1NL4BVLQ4L3N3Config;
import com.amazon.comms.config.echo.multimodal.A1XWJRHALS1REPConfig;
import com.amazon.comms.config.echo.multimodal.A1Z88NGR2BK6A2Config;
import com.amazon.comms.config.echo.multimodal.A2MDL5WTJIU8SZConfig;
import com.amazon.comms.config.echo.multimodal.A4ZP7ZC4PI6TOConfig;
import com.amazon.comms.config.echo.multimodal.AIPK7MM90V7TBConfig;
import com.amazon.comms.config.echo.multimodal.AWZZ5CVHX2CDConfig;
import com.amazon.comms.config.emulator.MMSdkTestAppConfig;
import com.amazon.comms.config.firetv.A1AGU0A4YA5RF9Config;
import com.amazon.comms.config.firetv.A1VGB7MHSIEYFKConfig;
import com.amazon.comms.config.firetv.A2JKHJ0PX4J3L3Config;
import com.amazon.comms.config.firetv.A31DTMEEVDDOIVConfig;
import com.amazon.comms.config.firetv.A3HF4YRA2L7XGCConfig;
import com.amazon.comms.config.firetv.A8MCGN45KMHDHConfig;
import com.amazon.comms.config.firetv.FireTVEditionsConfig;
import com.amazon.comms.config.tablets.AVU7CPPF2ZRASConfig;
import com.amazon.comms.config.tablets.HDTabletASPVoIPConfig;
import com.amazon.comms.config.tablets.HDTabletConfig;
import com.amazon.comms.config.tablets.TabletConfig;
import com.amazon.comms.config.tablets.VegaConfig;
import com.amazon.comms.config.util.DeviceInfo;
import com.amazon.comms.config.util.DeviceType;
import com.amazon.comms.log.CommsLogger;
import com.amazon.tarazed.core.logging.TarazedLogFormatter;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class DeviceConfigMapper {
    private static final String DEFAULT_3P_DEVICE_TYPE = "A2YNT4UBW9Y48K";
    private static final CommsLogger log = CommsLogger.getLogger(DeviceConfigMapper.class);

    public static DeviceConfig getDeviceConfigInstance(Context context) {
        String deviceType = DeviceInfo.getDeviceType(context, "A2YNT4UBW9Y48K");
        char c = 65535;
        try {
            switch (deviceType.hashCode()) {
                case -2139850095:
                    if (deviceType.equals("AWZZ5CVHX2CD")) {
                        c = 11;
                        break;
                    }
                    break;
                case -2038995837:
                    if (deviceType.equals("A1XWJRHALS1REP")) {
                        c = '\n';
                        break;
                    }
                    break;
                case -1942958879:
                    if (deviceType.equals("A1NL4BVLQ4L3N3")) {
                        c = '\b';
                        break;
                    }
                    break;
                case -1875515213:
                    if (deviceType.equals(DeviceType.Echo.Headless.A2M35JJZWCQOMZ)) {
                        c = 0;
                        break;
                    }
                    break;
                case -1670191639:
                    if (deviceType.equals(DeviceType.Echo.Headless.A3S5BH2HU6VAYF)) {
                        c = 2;
                        break;
                    }
                    break;
                case -1432568730:
                    if (deviceType.equals(DeviceType.FireTV.A2MGXR8OYP6PPI)) {
                        c = '#';
                        break;
                    }
                    break;
                case -1402759613:
                    if (deviceType.equals(DeviceType.Tablet.ThirdParty.AZKMZEQAGSURB)) {
                        c = Typography.amp;
                        break;
                    }
                    break;
                case -1384655872:
                    if (deviceType.equals("A1EIANJ7PNB0Q7")) {
                        c = 16;
                        break;
                    }
                    break;
                case -1362614210:
                    if (deviceType.equals(DeviceType.Tablet.A38EHHIB10L47V)) {
                        c = 24;
                        break;
                    }
                    break;
                case -1232236605:
                    if (deviceType.equals(DeviceType.Echo.Headless.A3RBAYBE7VM004)) {
                        c = 4;
                        break;
                    }
                    break;
                case -1194922061:
                    if (deviceType.equals(DeviceType.Tablet.ThirdParty.A3V88YODYKUDL4)) {
                        c = TarazedLogFormatter.FILE_SEPARATOR;
                        break;
                    }
                    break;
                case -944319628:
                    if (deviceType.equals("A10A33FOX2NUBK")) {
                        c = 7;
                        break;
                    }
                    break;
                case -775109930:
                    if (deviceType.equals(DeviceType.Echo.Headless.A7WXQPH584YP)) {
                        c = 1;
                        break;
                    }
                    break;
                case -650935243:
                    if (deviceType.equals(DeviceType.FireTV.A8MCGN45KMHDH)) {
                        c = '!';
                        break;
                    }
                    break;
                case -475961028:
                    if (deviceType.equals(DeviceType.FireTV.A1AGU0A4YA5RF9)) {
                        c = Chars.SPACE;
                        break;
                    }
                    break;
                case -288134989:
                    if (deviceType.equals(DeviceType.Tablet.A1DOD0Z74XEFYC)) {
                        c = 22;
                        break;
                    }
                    break;
                case -251188094:
                    if (deviceType.equals(DeviceType.Tablet.A1ZB65LA390I4K)) {
                        c = 20;
                        break;
                    }
                    break;
                case -217702176:
                    if (deviceType.equals(DeviceType.FireTV.A2JKHJ0PX4J3L3)) {
                        c = 29;
                        break;
                    }
                    break;
                case -154109413:
                    if (deviceType.equals(DeviceType.FireTV.A3HF4YRA2L7XGC)) {
                        c = 31;
                        break;
                    }
                    break;
                case -130458717:
                    if (deviceType.equals("A2YNT4UBW9Y48K")) {
                        c = '%';
                        break;
                    }
                    break;
                case -32246693:
                    if (deviceType.equals("A1Z88NGR2BK6A2")) {
                        c = '\f';
                        break;
                    }
                    break;
                case -28912293:
                    if (deviceType.equals(DeviceType.Tablet.A1TD5Z1R8IWBHA)) {
                        c = 21;
                        break;
                    }
                    break;
                case 312760178:
                    if (deviceType.equals("A15996VY63BQ2D")) {
                        c = 14;
                        break;
                    }
                    break;
                case 525679285:
                    if (deviceType.equals(DeviceType.Echo.Headless.A1JJ0KFC4ZPNJ3)) {
                        c = 6;
                        break;
                    }
                    break;
                case 791478753:
                    if (deviceType.equals(DeviceType.Tablet.ThirdParty.A1GPRTT66OBCF0)) {
                        c = 27;
                        break;
                    }
                    break;
                case 797598749:
                    if (deviceType.equals(DeviceType.FireTV.A1GAVERFF7K6QE)) {
                        c = '$';
                        break;
                    }
                    break;
                case 798147287:
                    if (deviceType.equals("A2MDL5WTJIU8SZ")) {
                        c = '\r';
                        break;
                    }
                    break;
                case 816846231:
                    if (deviceType.equals(DeviceType.Echo.Headless.A32DOYMUN6DTXA)) {
                        c = 5;
                        break;
                    }
                    break;
                case 1084722810:
                    if (deviceType.equals("AIPK7MM90V7TB")) {
                        c = 15;
                        break;
                    }
                    break;
                case 1153488872:
                    if (deviceType.equals("A2TF17PFR55MTB")) {
                        c = Chars.QUOTE;
                        break;
                    }
                    break;
                case 1368430427:
                    if (deviceType.equals(DeviceType.Tablet.A2N49KXGVA18AR)) {
                        c = 19;
                        break;
                    }
                    break;
                case 1463839682:
                    if (deviceType.equals(DeviceType.Tablet.AVU7CPPF2ZRAS)) {
                        c = 23;
                        break;
                    }
                    break;
                case 1585685657:
                    if (deviceType.equals(DeviceType.Tablet.ATNLRCEBX3W4P)) {
                        c = 18;
                        break;
                    }
                    break;
                case 1679355419:
                    if (deviceType.equals("A4ZP7ZC4PI6TO")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 1688625095:
                    if (deviceType.equals(DeviceType.Tablet.A3R9S4ZZECZ6YL)) {
                        c = 25;
                        break;
                    }
                    break;
                case 1691032504:
                    if (deviceType.equals(DeviceType.Echo.Headless.A18O6U1UQFJ0XK)) {
                        c = 3;
                        break;
                    }
                    break;
                case 1695423292:
                    if (deviceType.equals("A2J0R2SD7G9LPA")) {
                        c = 26;
                        break;
                    }
                    break;
                case 1709758701:
                    if (deviceType.equals(DeviceType.Tablet.A1C66CX2XD756O)) {
                        c = 17;
                        break;
                    }
                    break;
                case 1751474656:
                    if (deviceType.equals(DeviceType.FireTV.A1VGB7MHSIEYFK)) {
                        c = 30;
                        break;
                    }
                    break;
                case 2083999812:
                    if (deviceType.equals(DeviceType.FireTV.A31DTMEEVDDOIV)) {
                        c = '\"';
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                case 2:
                    return HeadlessFos5FastMixerCommonConfig.getDeviceConfigInstance();
                case 3:
                case 4:
                    return HeadlessFos6FastMixerCommonConfig.getDeviceConfigInstance();
                case 5:
                    return A32DOYMUN6DTXAConfig.getDeviceConfigInstance();
                case 6:
                    return HeadlessSpeakerlessConfig.getDeviceConfigInstance();
                case 7:
                    return A10A33FOX2NUBKConfig.getDeviceConfigInstance();
                case '\b':
                    return A1NL4BVLQ4L3N3Config.getDeviceConfigInstance();
                case '\t':
                    return A4ZP7ZC4PI6TOConfig.getDeviceConfigInstance();
                case '\n':
                    return A1XWJRHALS1REPConfig.getDeviceConfigInstance();
                case 11:
                    return AWZZ5CVHX2CDConfig.getDeviceConfigInstance();
                case '\f':
                    return A1Z88NGR2BK6A2Config.getDeviceConfigInstance();
                case '\r':
                    return A2MDL5WTJIU8SZConfig.getDeviceConfigInstance();
                case 14:
                    return A15996VY63BQ2DConfig.getDeviceConfigInstance();
                case 15:
                    return AIPK7MM90V7TBConfig.getDeviceConfigInstance();
                case 16:
                    return A1EIANJ7PNB0Q7Config.getDeviceConfigInstance();
                case 17:
                    return HDTabletConfig.getDeviceConfigInstance();
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                    return HDTabletASPVoIPConfig.getDeviceConfigInstance();
                case 23:
                    return AVU7CPPF2ZRASConfig.getDeviceConfigInstance();
                case 24:
                case 25:
                    return TabletConfig.getDeviceConfigInstance();
                case 26:
                case 27:
                case 28:
                    return VegaConfig.getDeviceConfigInstance();
                case 29:
                    return A2JKHJ0PX4J3L3Config.getDeviceConfigInstance();
                case 30:
                    return A1VGB7MHSIEYFKConfig.getDeviceConfigInstance();
                case 31:
                    return A3HF4YRA2L7XGCConfig.getDeviceConfigInstance();
                case ' ':
                    return A1AGU0A4YA5RF9Config.getDeviceConfigInstance();
                case '!':
                    return A8MCGN45KMHDHConfig.getDeviceConfigInstance();
                case '\"':
                    return A31DTMEEVDDOIVConfig.getDeviceConfigInstance();
                case '#':
                case '$':
                    return FireTVEditionsConfig.getDeviceConfigInstance();
                case '%':
                case '&':
                    return MMSdkTestAppConfig.getDeviceConfigInstance();
                case '\'':
                    return A2TF17PFR55MTBConfig.getDeviceConfigInstance();
                default:
                    CommsLogger commsLogger = log;
                    commsLogger.e("Failed to get DeviceConfig class for device type:" + deviceType);
                    throw new RuntimeException();
            }
        } catch (Exception e) {
            CommsLogger commsLogger2 = log;
            commsLogger2.e("Failed to get DeviceConfig class, exception:" + e);
            throw new RuntimeException(e);
        }
    }
}
