package com.amazon.dee.app.services.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.services.accessibility.AccessibilityService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class DefaultAccessibilityService implements AccessibilityService {
    static final float EPSILON = 1.0E-5f;
    static final String OPTION_ACCESSIBILITY_SHORTCUT = "AccessibilityShortcut";
    static final String OPTION_BRAILLE_READER = "BrailleReader";
    static final String OPTION_CLICK_AFTER_POINTER_STOPS_MOVING = "ClickAfterPointerStopsMoving";
    static final String OPTION_CLOSED_CAPTIONING = "ClosedCaptioning";
    static final String OPTION_COLOR_CORRECTION_DEUTERANOMALY = "ColorCorrectionDeuteranomaly";
    static final String OPTION_COLOR_CORRECTION_PROTANOMALY = "ColorCorrectionProtanomaly";
    static final String OPTION_COLOR_CORRECTION_TRITANOMALY = "ColorCorrectionTritanomaly";
    static final String OPTION_DISPLAY_SIZE_LARGE = "DisplaySizeLarge";
    static final String OPTION_DISPLAY_SIZE_SMALL = "DisplaySizeSmall";
    static final String OPTION_FONT_SIZE_LARGE = "FontSizeLarge";
    static final String OPTION_FONT_SIZE_LARGEST = "FontSizeLargest";
    static final String OPTION_FONT_SIZE_SMALL = "FontSizeSmall";
    static final String OPTION_HEARING_DEVICE = "HearingDevice";
    static final String OPTION_HIGH_CONTRAST = "HighContrast";
    static final String OPTION_INVERTED_COLORS = "InvertedColors";
    static final String OPTION_LARGE_MOUSE_POINTER = "LargeMousePointer";
    static final String OPTION_MAGNIFICATION_GESTURE = "MagnificationGesture";
    static final String OPTION_MONO_AUDIO = "MonoAudio";
    static final String OPTION_POWER_BUTTON_ENDS_CALL = "PowerButtonEndsCall";
    static final String OPTION_SPEAK_PASSWORDS = "SpeakPasswords";
    static final String OPTION_SWITCH_ACCESS = "SwitchAccess";
    static final String OPTION_TALK_BACK = "TalkBack";
    static final String OPTION_TOUCH_AND_HOLD_DELAY_LONG = "TouchAndHoldDelayLong";
    static final String OPTION_TOUCH_AND_HOLD_DELAY_MEDIUM = "TouchAndHoldDelayMedium";
    static final String TAG = Log.tag(DefaultAccessibilityService.class);
    AccessibilityManager accessibilityManager;
    ContentResolver contentResolver;
    Context context;
    Mobilytics mobilytics;

    /* renamed from: com.amazon.dee.app.services.accessibility.DefaultAccessibilityService$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$ColorCorrectionMode = new int[AccessibilityService.ColorCorrectionMode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$DisplaySizeSetting;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$FontSizeSetting;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$TouchAndHoldDelaySetting;

        static {
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$ColorCorrectionMode[AccessibilityService.ColorCorrectionMode.Deuteranomaly.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$ColorCorrectionMode[AccessibilityService.ColorCorrectionMode.Protanomaly.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$ColorCorrectionMode[AccessibilityService.ColorCorrectionMode.Tritanomaly.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$TouchAndHoldDelaySetting = new int[AccessibilityService.TouchAndHoldDelaySetting.values().length];
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$TouchAndHoldDelaySetting[AccessibilityService.TouchAndHoldDelaySetting.Medium.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$TouchAndHoldDelaySetting[AccessibilityService.TouchAndHoldDelaySetting.Long.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$DisplaySizeSetting = new int[AccessibilityService.DisplaySizeSetting.values().length];
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$DisplaySizeSetting[AccessibilityService.DisplaySizeSetting.Small.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$DisplaySizeSetting[AccessibilityService.DisplaySizeSetting.Large.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$FontSizeSetting = new int[AccessibilityService.FontSizeSetting.values().length];
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$FontSizeSetting[AccessibilityService.FontSizeSetting.Small.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$FontSizeSetting[AccessibilityService.FontSizeSetting.Large.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$services$accessibility$AccessibilityService$FontSizeSetting[AccessibilityService.FontSizeSetting.Largest.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public DefaultAccessibilityService(Context context, Mobilytics mobilytics) {
        this.context = context;
        this.mobilytics = mobilytics;
        this.contentResolver = context.getContentResolver();
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public AccessibilityService.ColorCorrectionMode getColorCorrectionMode() {
        try {
            if (Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED").get(Settings.Secure.class)) == 1) {
                switch (Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_DISPLAY_DALTONIZER").get(Settings.Secure.class))) {
                    case 11:
                        return AccessibilityService.ColorCorrectionMode.Protanomaly;
                    case 12:
                        return AccessibilityService.ColorCorrectionMode.Deuteranomaly;
                    case 13:
                        return AccessibilityService.ColorCorrectionMode.Tritanomaly;
                }
            }
        } catch (Exception unused) {
        }
        return AccessibilityService.ColorCorrectionMode.None;
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public AccessibilityService.DisplaySizeSetting getDisplaySizeSetting() {
        double intValue;
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Object invoke = cls.getMethod("getWindowManagerService", new Class[0]).invoke(cls, new Object[0]);
            intValue = ((Integer) invoke.getClass().getMethod("getBaseDisplayDensity", Integer.TYPE).invoke(invoke, 0)).intValue() / ((Integer) invoke.getClass().getMethod("getInitialDisplayDensity", Integer.TYPE).invoke(invoke, 0)).intValue();
        } catch (Exception unused) {
        }
        if (intValue >= 1.1249900000002526d) {
            return AccessibilityService.DisplaySizeSetting.Large;
        }
        if (intValue <= 0.8500099999997474d) {
            return AccessibilityService.DisplaySizeSetting.Small;
        }
        return AccessibilityService.DisplaySizeSetting.Medium;
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public AccessibilityService.FontSizeSetting getFontSizeSetting() {
        double d;
        try {
            d = Settings.System.getFloat(this.contentResolver, "font_scale");
        } catch (Exception unused) {
        }
        if (d >= 1.2999900000002527d) {
            return AccessibilityService.FontSizeSetting.Largest;
        }
        if (d >= 1.1499900000002525d) {
            return AccessibilityService.FontSizeSetting.Large;
        }
        if (d <= 0.8500099999997474d) {
            return AccessibilityService.FontSizeSetting.Small;
        }
        return AccessibilityService.FontSizeSetting.Medium;
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public AccessibilityService.TouchAndHoldDelaySetting getTouchAndHoldDelaySetting() {
        float f;
        try {
            f = Settings.Secure.getFloat(this.contentResolver, (String) Settings.Secure.class.getField("LONG_PRESS_TIMEOUT").get(Settings.Secure.class));
        } catch (Exception unused) {
        }
        if (f >= 1500.0f) {
            return AccessibilityService.TouchAndHoldDelaySetting.Long;
        }
        if (f >= 1000.0f) {
            return AccessibilityService.TouchAndHoldDelaySetting.Medium;
        }
        return AccessibilityService.TouchAndHoldDelaySetting.Short;
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isAccessibilityEnabled() {
        return this.accessibilityManager.isEnabled();
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isAccessibilityShortcutEnabled() {
        try {
            return Settings.Global.getInt(this.contentResolver, (String) Settings.Global.class.getField("ENABLE_ACCESSIBILITY_GLOBAL_GESTURE_ENABLED").get(Settings.Global.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isBrailleReaderEnabled() {
        try {
            if (!this.accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            return !this.accessibilityManager.getEnabledAccessibilityServiceList(32).isEmpty();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isClickAfterPointerStopsMovingEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_AUTOCLICK_ENABLED").get(Settings.Secure.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isClosedCaptioningEnabled() {
        try {
            if (!((CaptioningManager) this.context.getSystemService("captioning")).isEnabled()) {
                return false;
            }
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_CAPTIONING_ENABLED").get(Settings.Secure.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isHearingDeviceEnabled() {
        try {
            return Settings.System.getInt(this.contentResolver, (String) Settings.System.class.getField("HEARING_AID").get(Settings.System.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isHighContrastEnabled() {
        try {
            if (!((Boolean) this.accessibilityManager.getClass().getMethod("isHighTextContrastEnabled", new Class[0]).invoke(this.accessibilityManager, new Object[0])).booleanValue()) {
                return false;
            }
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED").get(Settings.Secure.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isInvertedColorsEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, "accessibility_display_inversion_enabled") == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isLargeMousePointerEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_LARGE_POINTER_ICON").get(Settings.Secure.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isMagnificationGestureEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("ACCESSIBILITY_DISPLAY_MAGNIFICATION_ENABLED").get(Settings.Secure.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isMonoAudioEnabled() {
        try {
            return Settings.System.getInt(this.contentResolver, (String) Settings.System.class.getField("MASTER_MONO").get(Settings.System.class)) == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isPowerButtonEndsCallEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, (String) Settings.Secure.class.getField("INCALL_POWER_BUTTON_BEHAVIOR").get(Settings.Secure.class)) == Settings.Secure.class.getField("INCALL_POWER_BUTTON_BEHAVIOR_HANGUP").getInt(Settings.Secure.class);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isSpeakPasswordsEnabled() {
        try {
            return Settings.Secure.getInt(this.contentResolver, "speak_password") == 1;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isSwitchAccessEnabled() {
        try {
            for (AccessibilityServiceInfo accessibilityServiceInfo : this.accessibilityManager.getEnabledAccessibilityServiceList(16)) {
                if (accessibilityServiceInfo.getSettingsActivityName().contains("SwitchAccessPreferenceActivity")) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public boolean isTalkBackEnabled() {
        try {
            if (!this.accessibilityManager.isTouchExplorationEnabled()) {
                return false;
            }
            return !this.accessibilityManager.getEnabledAccessibilityServiceList(1).isEmpty();
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.amazon.dee.app.services.accessibility.AccessibilityService
    public void recordAccessibilityMetrics() {
        recordAccessibilityMetrics(new ArrayList<>());
    }

    @VisibleForTesting
    void recordAccessibilityMetrics(ArrayList<String> arrayList) {
        String sb;
        boolean isTalkBackEnabled = isTalkBackEnabled();
        this.mobilytics.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.ACCESSIBILITY_ENABLED, isAccessibilityEnabled(), "Application", DefaultAccessibilityService.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        this.mobilytics.recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.ACCESSIBILITY_SCREEN_READER, isTalkBackEnabled, "Application", DefaultAccessibilityService.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        if (isTalkBackEnabled) {
            arrayList.add(OPTION_TALK_BACK);
        }
        if (isBrailleReaderEnabled()) {
            arrayList.add(OPTION_BRAILLE_READER);
        }
        if (isSwitchAccessEnabled()) {
            arrayList.add(OPTION_SWITCH_ACCESS);
        }
        if (isClosedCaptioningEnabled()) {
            arrayList.add(OPTION_CLOSED_CAPTIONING);
        }
        if (isMagnificationGestureEnabled()) {
            arrayList.add(OPTION_MAGNIFICATION_GESTURE);
        }
        int ordinal = getFontSizeSetting().ordinal();
        if (ordinal == 0) {
            arrayList.add(OPTION_FONT_SIZE_SMALL);
        } else if (ordinal == 2) {
            arrayList.add(OPTION_FONT_SIZE_LARGE);
        } else if (ordinal == 3) {
            arrayList.add(OPTION_FONT_SIZE_LARGEST);
        }
        int ordinal2 = getDisplaySizeSetting().ordinal();
        if (ordinal2 == 0) {
            arrayList.add(OPTION_DISPLAY_SIZE_SMALL);
        } else if (ordinal2 == 2) {
            arrayList.add(OPTION_DISPLAY_SIZE_LARGE);
        }
        if (isClickAfterPointerStopsMovingEnabled()) {
            arrayList.add(OPTION_CLICK_AFTER_POINTER_STOPS_MOVING);
        }
        if (isHighContrastEnabled()) {
            arrayList.add(OPTION_HIGH_CONTRAST);
        }
        if (isPowerButtonEndsCallEnabled()) {
            arrayList.add(OPTION_POWER_BUTTON_ENDS_CALL);
        }
        if (isSpeakPasswordsEnabled()) {
            arrayList.add(OPTION_SPEAK_PASSWORDS);
        }
        if (isLargeMousePointerEnabled()) {
            arrayList.add(OPTION_LARGE_MOUSE_POINTER);
        }
        if (isMonoAudioEnabled()) {
            arrayList.add(OPTION_MONO_AUDIO);
        }
        if (isHearingDeviceEnabled()) {
            arrayList.add(OPTION_HEARING_DEVICE);
        }
        if (isAccessibilityShortcutEnabled()) {
            arrayList.add(OPTION_ACCESSIBILITY_SHORTCUT);
        }
        int ordinal3 = getTouchAndHoldDelaySetting().ordinal();
        if (ordinal3 == 1) {
            arrayList.add(OPTION_TOUCH_AND_HOLD_DELAY_MEDIUM);
        } else if (ordinal3 == 2) {
            arrayList.add(OPTION_TOUCH_AND_HOLD_DELAY_LONG);
        }
        if (isInvertedColorsEnabled()) {
            arrayList.add(OPTION_INVERTED_COLORS);
        }
        int ordinal4 = getColorCorrectionMode().ordinal();
        if (ordinal4 == 1) {
            arrayList.add(OPTION_COLOR_CORRECTION_DEUTERANOMALY);
        } else if (ordinal4 == 2) {
            arrayList.add(OPTION_COLOR_CORRECTION_PROTANOMALY);
        } else if (ordinal4 == 3) {
            arrayList.add(OPTION_COLOR_CORRECTION_TRITANOMALY);
        }
        Mobilytics mobilytics = this.mobilytics;
        if (arrayList.isEmpty()) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" ");
            outline107.append(TextUtils.join(" ", arrayList));
            outline107.append(" ");
            sb = outline107.toString();
        }
        mobilytics.recordDataEvent(AlexaMetricsConstants.MetricEvents.ACCESSIBILITY_OPTIONS, sb, "Application", DefaultAccessibilityService.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
    }
}
