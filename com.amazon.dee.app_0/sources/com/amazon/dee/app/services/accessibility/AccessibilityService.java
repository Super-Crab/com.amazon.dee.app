package com.amazon.dee.app.services.accessibility;
/* loaded from: classes12.dex */
public interface AccessibilityService {

    /* loaded from: classes12.dex */
    public enum ColorCorrectionMode {
        None,
        Deuteranomaly,
        Protanomaly,
        Tritanomaly
    }

    /* loaded from: classes12.dex */
    public enum DisplaySizeSetting {
        Small,
        Medium,
        Large
    }

    /* loaded from: classes12.dex */
    public enum FontSizeSetting {
        Small,
        Medium,
        Large,
        Largest
    }

    /* loaded from: classes12.dex */
    public enum TouchAndHoldDelaySetting {
        Short,
        Medium,
        Long
    }

    ColorCorrectionMode getColorCorrectionMode();

    DisplaySizeSetting getDisplaySizeSetting();

    FontSizeSetting getFontSizeSetting();

    TouchAndHoldDelaySetting getTouchAndHoldDelaySetting();

    boolean isAccessibilityEnabled();

    boolean isAccessibilityShortcutEnabled();

    boolean isBrailleReaderEnabled();

    boolean isClickAfterPointerStopsMovingEnabled();

    boolean isClosedCaptioningEnabled();

    boolean isHearingDeviceEnabled();

    boolean isHighContrastEnabled();

    boolean isInvertedColorsEnabled();

    boolean isLargeMousePointerEnabled();

    boolean isMagnificationGestureEnabled();

    boolean isMonoAudioEnabled();

    boolean isPowerButtonEndsCallEnabled();

    boolean isSpeakPasswordsEnabled();

    boolean isSwitchAccessEnabled();

    boolean isTalkBackEnabled();

    void recordAccessibilityMetrics();
}
