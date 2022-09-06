package com.drew.metadata.exif.makernotes;

import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class CasioType1MakernoteDescriptor extends TagDescriptor<CasioType1MakernoteDirectory> {
    public CasioType1MakernoteDescriptor(@NotNull CasioType1MakernoteDirectory casioType1MakernoteDirectory) {
        super(casioType1MakernoteDirectory);
    }

    @Nullable
    public String getCcdSensitivityDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(20);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 64 ? intValue != 80 ? intValue != 100 ? intValue != 125 ? intValue != 244 ? intValue != 250 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "+2.0" : "+3.0" : "+1.0" : DCMEndpoint.Priority.HIGH : "Normal (ISO 80 equivalent)" : DCMEndpoint.Priority.NORMAL;
    }

    @Nullable
    public String getContrastDescription() {
        return getIndexedDescription(12, DCMEndpoint.Priority.NORMAL, "Low", DCMEndpoint.Priority.HIGH);
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 20) {
            switch (i) {
                case 1:
                    return getRecordingModeDescription();
                case 2:
                    return getQualityDescription();
                case 3:
                    return getFocusingModeDescription();
                case 4:
                    return getFlashModeDescription();
                case 5:
                    return getFlashIntensityDescription();
                case 6:
                    return getObjectDistanceDescription();
                case 7:
                    return getWhiteBalanceDescription();
                default:
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
        }
        return getCcdSensitivityDescription();
    }

    @Nullable
    public String getDigitalZoomDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(10);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 65536 ? (intValue == 65537 || intValue == 131072) ? "2x digital zoom" : intValue != 262144 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "4x digital zoom" : "No digital zoom";
    }

    @Nullable
    public String getFlashIntensityDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(5);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 11 ? intValue != 13 ? intValue != 15 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Strong" : DCMEndpoint.Priority.NORMAL : "Weak";
    }

    @Nullable
    public String getFlashModeDescription() {
        return getIndexedDescription(4, 1, "Auto", "On", BucketVersioningConfiguration.OFF, "Red eye reduction");
    }

    @Nullable
    public String getFocusingModeDescription() {
        return getIndexedDescription(3, 2, "Macro", "Auto focus", "Manual focus", "Infinity");
    }

    @Nullable
    public String getObjectDistanceDescription() {
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(6);
        if (integer == null) {
            return null;
        }
        return TagDescriptor.getFocalLengthDescription(integer.intValue());
    }

    @Nullable
    public String getQualityDescription() {
        return getIndexedDescription(2, 1, "Economy", DCMEndpoint.Priority.NORMAL, "Fine");
    }

    @Nullable
    public String getRecordingModeDescription() {
        return getIndexedDescription(1, 1, "Single shutter", "Panorama", "Night scene", "Portrait", "Landscape");
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
        Integer integer = ((CasioType1MakernoteDirectory) this._directory).getInteger(7);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 1 ? intValue != 2 ? intValue != 3 ? intValue != 4 ? intValue != 5 ? intValue != 129 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Manual" : "Shade" : "Florescent" : "Daylight" : "Tungsten" : "Auto";
    }
}
