package com.amazon.alexa.drivemode.api;

import android.content.Context;
/* loaded from: classes7.dex */
public interface DriveModeThemeManager {
    public static final String DRIVE_MODE_THEME_CHANGE_EVENT = "drive::mode:theme:changed";
    public static final String DRIVE_MODE_THEME_CHANGE_INTENT_ACTION = "ACTION_DRIVE_MODE_THEME_CHANGED";
    public static final String DRIVE_MODE_THEME_CHANGE_KEY = "attr_theme_change";
    public static final String MOSAIC_THEME_DARK = "mosaic_theme_dark";
    public static final String MOSAIC_THEME_LIGHT = "mosaic_theme_light";
    public static final String SHARED_PREFERENCE_KEY = "DriveModeThemeSetting";
    public static final String SHARED_PREFERENCE_THEME_ATTR = "DriveModeThemeSettingValue";

    /* loaded from: classes7.dex */
    public enum ThemeType {
        LIGHT(1),
        DARK(2);
        
        private final int value;

        ThemeType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    void destroy();

    int getColorFromAttribute(Context context, int i);

    int getTheme();

    void init(Context context);

    boolean isCurrentThemeDark();

    void notifyThemeValue();

    void setSystemBarTheme(Context context);
}
