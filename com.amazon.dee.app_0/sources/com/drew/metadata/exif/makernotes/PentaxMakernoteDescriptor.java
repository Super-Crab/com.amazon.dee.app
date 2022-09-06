package com.drew.metadata.exif.makernotes;

import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class PentaxMakernoteDescriptor extends TagDescriptor<PentaxMakernoteDirectory> {
    public PentaxMakernoteDescriptor(@NotNull PentaxMakernoteDirectory pentaxMakernoteDirectory) {
        super(pentaxMakernoteDirectory);
    }

    @Nullable
    public String getCaptureModeDescription() {
        return getIndexedDescription(1, "Auto", "Night-scene", "Manual", null, "Multiple");
    }

    @Nullable
    public String getColourDescription() {
        return getIndexedDescription(23, 1, DCMEndpoint.Priority.NORMAL, "Black & White", "Sepia");
    }

    @Nullable
    public String getContrastDescription() {
        return getIndexedDescription(12, DCMEndpoint.Priority.NORMAL, "Low", DCMEndpoint.Priority.HIGH);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 1) {
            if (i == 2) {
                return getQualityLevelDescription();
            }
            if (i == 3) {
                return getFocusModeDescription();
            }
            if (i == 4) {
                return getFlashModeDescription();
            }
            if (i == 7) {
                return getWhiteBalanceDescription();
            }
            if (i == 20) {
                return getIsoSpeedDescription();
            }
            if (i == 23) {
                return getColourDescription();
            }
            switch (i) {
                case 10:
                    return getDigitalZoomDescription();
                case 11:
                    return getSharpnessDescription();
                case 12:
                    return getContrastDescription();
                case 13:
                    return getSaturationDescription();
                default:
                    return super.getDescription(i);
            }
        }
        return getCaptureModeDescription();
    }

    @Nullable
    public String getDigitalZoomDescription() {
        Float floatObject = ((PentaxMakernoteDirectory) this._directory).getFloatObject(10);
        if (floatObject == null) {
            return null;
        }
        return floatObject.floatValue() == 0.0f ? BucketVersioningConfiguration.OFF : Float.toString(floatObject.floatValue());
    }

    @Nullable
    public String getFlashModeDescription() {
        return getIndexedDescription(4, 1, "Auto", "Flash On", null, "Flash Off", null, "Red-eye Reduction");
    }

    @Nullable
    public String getFocusModeDescription() {
        return getIndexedDescription(3, 2, "Custom", "Auto");
    }

    @Nullable
    public String getIsoSpeedDescription() {
        Integer integer = ((PentaxMakernoteDirectory) this._directory).getInteger(20);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 10 ? intValue != 16 ? intValue != 100 ? intValue != 200 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "ISO 200" : "ISO 100" : "ISO 200" : "ISO 100";
    }

    @Nullable
    public String getQualityLevelDescription() {
        return getIndexedDescription(2, "Good", "Better", "Best");
    }

    @Nullable
    public String getSaturationDescription() {
        return getIndexedDescription(13, DCMEndpoint.Priority.NORMAL, "Low", DCMEndpoint.Priority.HIGH);
    }

    @Nullable
    public String getSharpnessDescription() {
        return getIndexedDescription(11, DCMEndpoint.Priority.NORMAL, "Soft", "Hard");
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        return getIndexedDescription(7, "Auto", "Daylight", "Shade", "Tungsten", "Fluorescent", "Manual");
    }
}
