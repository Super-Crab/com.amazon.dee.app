package com.amazon.alexa.mode.util;

import android.content.Context;
import android.preference.PreferenceManager;
import com.amazon.alexa.mode.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class PrefsDialogHelper {
    private static final String DRIVE_MODE_AUTO_LAUNCH_PREFS = "DriveModeAutoLaunchPrefs";
    private static final String DRIVE_MODE_AUTO_SWITCH_PREFS = "DriveModeAutoSwitchPrefs";
    static final String DRIVE_MODE_CONFIGURED_PREFS = "DriveModeConfiguredPrefs";
    private static final String DRIVE_MODE_DARK_MODE_PREFS = "DriveModeDarkModePrefs";
    private static final String DRIVE_MODE_NOTIFICATION_PREFS = "DriveModeNotificationsPrefs";
    static final String STANDALONE_MODE_CONFIGURED_PREFS = "StandAloneModeConfiguredPrefs";
    private Context context;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface DriveModeConfigured {
        public static final int CONFIGURED = 1;
        public static final int NOT_CONFIGURED = 0;
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface UserSettingValue {
        public static final int ALWAYS = 1;
        public static final int NEVER = -1;
        public static final int UNKNOWN = 0;
    }

    public PrefsDialogHelper(Context context) {
        this.context = context;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String getPreferenceName(String str) {
        char c;
        switch (str.hashCode()) {
            case -1859948073:
                if (str.equals(Constants.DRIVE_MODE_DARK_MODE_SETTING)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -280062443:
                if (str.equals(Constants.DRIVE_MODE_NOTIFICATION_SETTING)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1333229930:
                if (str.equals(Constants.DRIVE_MODE_AUTO_INGRESS_SETTING)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1565160205:
                if (str.equals(Constants.DRIVE_MODE_AUTO_SWITCH_SETTING)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c != 0 ? c != 1 ? c != 2 ? c != 3 ? "" : DRIVE_MODE_DARK_MODE_PREFS : DRIVE_MODE_AUTO_SWITCH_PREFS : DRIVE_MODE_NOTIFICATION_PREFS : DRIVE_MODE_AUTO_LAUNCH_PREFS;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int processUnknownPreferenceValue(String str) {
        char c;
        switch (str.hashCode()) {
            case -1859948073:
                if (str.equals(Constants.DRIVE_MODE_DARK_MODE_SETTING)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -280062443:
                if (str.equals(Constants.DRIVE_MODE_NOTIFICATION_SETTING)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1333229930:
                if (str.equals(Constants.DRIVE_MODE_AUTO_INGRESS_SETTING)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1565160205:
                if (str.equals(Constants.DRIVE_MODE_AUTO_SWITCH_SETTING)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return (c == 0 || c == 1 || c == 2 || c == 3) ? 1 : 0;
    }

    public int getDriveModeConfiguredPref() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getInt(DRIVE_MODE_CONFIGURED_PREFS, 0);
    }

    public int getPreferenceValue(String str) {
        int i = PreferenceManager.getDefaultSharedPreferences(this.context).getInt(getPreferenceName(str), 0);
        return i == 0 ? processUnknownPreferenceValue(str) : i;
    }

    public int getStandAloneModeConfiguredPref() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getInt(STANDALONE_MODE_CONFIGURED_PREFS, 0);
    }

    public boolean isDriveModeAutoLaunchEnabled() {
        return getPreferenceValue(Constants.DRIVE_MODE_AUTO_INGRESS_SETTING) == 1;
    }

    public boolean isDriveModeConfigured() {
        return PreferenceManager.getDefaultSharedPreferences(this.context).getInt(DRIVE_MODE_CONFIGURED_PREFS, 0) == 1;
    }

    public boolean isNotificationEnabled() {
        return getPreferenceValue(Constants.DRIVE_MODE_NOTIFICATION_SETTING) == 1;
    }

    public void setDriveModeConfiguredPref(int i) {
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().putInt(DRIVE_MODE_CONFIGURED_PREFS, i).apply();
    }

    public void setPreferenceValue(String str, int i) {
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().putInt(getPreferenceName(str), i).apply();
    }

    public void setStandAloneModeConfiguredPref(int i) {
        PreferenceManager.getDefaultSharedPreferences(this.context).edit().putInt(STANDALONE_MODE_CONFIGURED_PREFS, i).apply();
    }
}
