package com.drew.metadata.exif.makernotes;

import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class NikonType1MakernoteDescriptor extends TagDescriptor<NikonType1MakernoteDirectory> {
    public NikonType1MakernoteDescriptor(@NotNull NikonType1MakernoteDirectory nikonType1MakernoteDirectory) {
        super(nikonType1MakernoteDirectory);
    }

    @Nullable
    public String getCcdSensitivityDescription() {
        return getIndexedDescription(6, "ISO80", null, "ISO160", null, "ISO320", "ISO100");
    }

    @Nullable
    public String getColorModeDescription() {
        return getIndexedDescription(4, 1, "Color", "Monochrome");
    }

    @Nullable
    public String getConverterDescription() {
        return getIndexedDescription(11, "None", "Fisheye converter");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        switch (i) {
            case 3:
                return getQualityDescription();
            case 4:
                return getColorModeDescription();
            case 5:
                return getImageAdjustmentDescription();
            case 6:
                return getCcdSensitivityDescription();
            case 7:
                return getWhiteBalanceDescription();
            case 8:
                return getFocusDescription();
            case 9:
            default:
                return super.getDescription(i);
            case 10:
                return getDigitalZoomDescription();
            case 11:
                return getConverterDescription();
        }
    }

    @Nullable
    public String getDigitalZoomDescription() {
        Rational rational = ((NikonType1MakernoteDirectory) this._directory).getRational(10);
        if (rational == null) {
            return null;
        }
        if (rational.getNumerator() == 0) {
            return "No digital zoom";
        }
        return rational.toSimpleString(true) + "x digital zoom";
    }

    @Nullable
    public String getFocusDescription() {
        Rational rational = ((NikonType1MakernoteDirectory) this._directory).getRational(8);
        if (rational == null) {
            return null;
        }
        return (rational.getNumerator() == 1 && rational.getDenominator() == 0) ? "Infinite" : rational.toSimpleString(true);
    }

    @Nullable
    public String getImageAdjustmentDescription() {
        return getIndexedDescription(5, DCMEndpoint.Priority.NORMAL, "Bright +", "Bright -", "Contrast +", "Contrast -");
    }

    @Nullable
    public String getQualityDescription() {
        return getIndexedDescription(3, 1, "VGA Basic", "VGA Normal", "VGA Fine", "SXGA Basic", "SXGA Normal", "SXGA Fine");
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        return getIndexedDescription(7, "Auto", "Preset", "Daylight", "Incandescence", "Florescence", "Cloudy", "SpeedLight");
    }
}
