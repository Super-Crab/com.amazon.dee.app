package com.google.android.exoplayer2.util;

import android.os.Build;
import com.amazon.alexa.mobilytics.event.LogLevel;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttTopic;
import com.dee.app.metrics.MetricsConstants;
import com.google.android.exoplayer2.util.Logger;
/* loaded from: classes2.dex */
public final class AmazonQuirks {
    private static final String AMAZON = "Amazon";
    private static final int AUDIO_HARDWARE_LATENCY_FOR_TABLETS = 90000;
    private static final String FIRETV_GEN1_DEVICE_MODEL = "AFTB";
    private static final String FIRETV_GEN2_DEVICE_MODEL = "AFTS";
    private static final String FIRETV_STICK_DEVICE_MODEL = "AFTM";
    private static final String FIRETV_STICK_GEN2_DEVICE_MODEL = "AFTT";
    private static final String FIRE_PHONE_DEVICE_MODEL = "SD";
    private static final String KINDLE_TABLET_DEVICE_MODEL = "KF";
    private static final String TAG = "AmazonQuirks";
    private static final boolean isFirePhone;
    private static final boolean isFireTVGen1;
    private static final boolean isFireTVGen2;
    private static final boolean isFireTVStick;
    private static final boolean isKindleTablet;
    private static boolean skipProfileLevelCheck;
    private static final String DEVICEMODEL = Build.MODEL;
    private static final String MANUFACTURER = Build.MANUFACTURER;
    private static final boolean isAmazonDevice = MANUFACTURER.equalsIgnoreCase("Amazon");

    static {
        boolean z = true;
        isFireTVGen1 = isAmazonDevice && DEVICEMODEL.equalsIgnoreCase(FIRETV_GEN1_DEVICE_MODEL);
        isFireTVGen2 = isAmazonDevice && DEVICEMODEL.equalsIgnoreCase(FIRETV_GEN2_DEVICE_MODEL);
        isFireTVStick = isAmazonDevice && DEVICEMODEL.equalsIgnoreCase(FIRETV_STICK_DEVICE_MODEL);
        isKindleTablet = isAmazonDevice && DEVICEMODEL.startsWith(KINDLE_TABLET_DEVICE_MODEL);
        if (!isAmazonDevice || !DEVICEMODEL.startsWith(FIRE_PHONE_DEVICE_MODEL)) {
            z = false;
        }
        isFirePhone = z;
        loadForcedLogSettings();
    }

    private AmazonQuirks() {
    }

    public static int getAudioHWLatency() {
        return AUDIO_HARDWARE_LATENCY_FOR_TABLETS;
    }

    public static String getSystemProperty(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod(MetricsConstants.Method.CACHE_GET, String.class).invoke(null, str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isAmazonDevice() {
        return isAmazonDevice;
    }

    public static boolean isDolbyPassthroughQuirkEnabled() {
        return isFireTVGen1Family();
    }

    public static boolean isFireTVGen1Family() {
        return isFireTVGen1 || isFireTVStick;
    }

    public static boolean isFireTVGen2() {
        return isFireTVGen2;
    }

    public static boolean isLatencyQuirkEnabled() {
        return Util.SDK_INT <= 19 && (isKindleTablet || isFirePhone);
    }

    private static void loadForcedLogSettings() {
        String systemProperty = getSystemProperty("com.amazon.exoplayer.forcelog");
        if (systemProperty == null || systemProperty.equals("")) {
            return;
        }
        try {
            for (String str : systemProperty.split(MqttTopic.MULTI_LEVEL_WILDCARD)) {
                String[] split = str.split(":");
                Logger.Module valueOf = Logger.Module.valueOf(split[0]);
                String lowerCase = split[1].toLowerCase();
                char c = 65535;
                int i = 3;
                switch (lowerCase.hashCode()) {
                    case 3237038:
                        if (lowerCase.equals(LogLevel.INFO)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 3641990:
                        if (lowerCase.equals(LogLevel.WARN)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 96784904:
                        if (lowerCase.equals("error")) {
                            c = 0;
                            break;
                        }
                        break;
                    case 351107458:
                        if (lowerCase.equals("verbose")) {
                            c = 2;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    i = 6;
                } else if (c == 1) {
                    i = 4;
                } else if (c == 2) {
                    i = 2;
                } else if (c == 3) {
                    i = 5;
                }
                Logger.setLogLevel(valueOf, i);
            }
        } catch (Exception e) {
            android.util.Log.e(TAG, "Could not set logging level.", e);
        }
    }

    public static boolean shouldSkipProfileLevelCheck() {
        return skipProfileLevelCheck;
    }

    public static void skipProfileLevelCheck(boolean z) {
        skipProfileLevelCheck = z;
    }

    public static boolean useDefaultPassthroughDecoder() {
        if (isFireTVGen1Family()) {
            android.util.Log.i(TAG, "Using platform Dolby decoder");
            return false;
        }
        android.util.Log.i(TAG, "Using default Dolby pass-through decoder");
        return true;
    }
}
