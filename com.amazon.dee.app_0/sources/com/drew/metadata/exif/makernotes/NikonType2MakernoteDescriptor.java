package com.drew.metadata.exif.makernotes;

import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazon.alexa.mode.metrics.DriveModeMetrics;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public class NikonType2MakernoteDescriptor extends TagDescriptor<NikonType2MakernoteDirectory> {
    public NikonType2MakernoteDescriptor(@NotNull NikonType2MakernoteDirectory nikonType2MakernoteDirectory) {
        super(nikonType2MakernoteDirectory);
    }

    private double getDistanceInMeters(int i) {
        if (i < 0) {
            i += 256;
        }
        return Math.pow(10.0d, i / 40.0f) * 0.01d;
    }

    @Nullable
    private String getEVDescription(int i) {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(i);
        if (intArray == null || intArray.length < 2 || intArray.length < 3 || intArray[2] == 0) {
            return null;
        }
        return new DecimalFormat("0.##").format((intArray[0] * intArray[1]) / intArray[2]) + " EV";
    }

    @Nullable
    public String getActiveDLightingDescription() {
        Integer integer = ((NikonType2MakernoteDirectory) this._directory).getInteger(34);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 3 ? intValue != 5 ? intValue != 7 ? intValue != 65535 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Auto" : "Extra High" : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL : DriveModeMetrics.Theme.LIGHT : BucketVersioningConfiguration.OFF;
    }

    @Nullable
    public String getAutoFlashCompensationDescription() {
        return getEVDescription(18);
    }

    @Nullable
    public String getAutoFocusPositionDescription() {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(136);
        if (intArray == null) {
            return null;
        }
        if (intArray.length == 4 && intArray[0] == 0 && intArray[2] == 0 && intArray[3] == 0) {
            int i = intArray[1];
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("Unknown ("), intArray[1], ")") : "Right" : "Left" : "Bottom" : "Top" : "Centre";
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown (");
        outline107.append(((NikonType2MakernoteDirectory) this._directory).getString(136));
        outline107.append(")");
        return outline107.toString();
    }

    @Nullable
    public String getColorModeDescription() {
        String string = ((NikonType2MakernoteDirectory) this._directory).getString(141);
        if (string == null) {
            return null;
        }
        return string.startsWith("MODE1") ? "Mode I (sRGB)" : string;
    }

    @Nullable
    public String getColorSpaceDescription() {
        return getIndexedDescription(30, 1, "sRGB", "Adobe RGB");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 1) {
            if (i == 2) {
                return getIsoSettingDescription();
            }
            if (i == 13) {
                return getProgramShiftDescription();
            }
            if (i == 14) {
                return getExposureDifferenceDescription();
            }
            if (i == 18) {
                return getAutoFlashCompensationDescription();
            }
            if (i == 28) {
                return getExposureTuningDescription();
            }
            if (i == 30) {
                return getColorSpaceDescription();
            }
            if (i == 34) {
                return getActiveDLightingDescription();
            }
            if (i == 42) {
                return getVignetteControlDescription();
            }
            if (i == 139) {
                return getLensStopsDescription();
            }
            if (i == 141) {
                return getColorModeDescription();
            }
            if (i == 177) {
                return getHighISONoiseReductionDescription();
            }
            if (i == 182) {
                return getPowerUpTimeDescription();
            }
            if (i == 23) {
                return getFlashExposureCompensationDescription();
            }
            if (i == 24) {
                return getFlashBracketCompensationDescription();
            }
            if (i == 131) {
                return getLensTypeDescription();
            }
            if (i == 132) {
                return getLensDescription();
            }
            if (i == 146) {
                return getHueAdjustmentDescription();
            }
            if (i == 147) {
                return getNEFCompressionDescription();
            }
            switch (i) {
                case 134:
                    return getDigitalZoomDescription();
                case 135:
                    return getFlashUsedDescription();
                case 136:
                    return getAutoFocusPositionDescription();
                case 137:
                    return getShootingModeDescription();
                default:
                    return super.getDescription(i);
            }
        }
        return getFirmwareVersionDescription();
    }

    @Nullable
    public String getDigitalZoomDescription() {
        Rational rational = ((NikonType2MakernoteDirectory) this._directory).getRational(134);
        if (rational == null) {
            return null;
        }
        if (rational.intValue() == 1) {
            return "No digital zoom";
        }
        return rational.toSimpleString(true) + "x digital zoom";
    }

    @Nullable
    public String getExposureDifferenceDescription() {
        return getEVDescription(14);
    }

    @Nullable
    public String getExposureTuningDescription() {
        return getEVDescription(28);
    }

    @Nullable
    public String getFirmwareVersionDescription() {
        return getVersionBytesDescription(1, 2);
    }

    @Nullable
    public String getFlashBracketCompensationDescription() {
        return getEVDescription(24);
    }

    @Nullable
    public String getFlashExposureCompensationDescription() {
        return getEVDescription(23);
    }

    @Nullable
    public String getFlashUsedDescription() {
        return getIndexedDescription(135, "Flash Not Used", "Manual Flash", null, "Flash Not Ready", null, null, null, "External Flash", "Fired, Commander Mode", "Fired, TTL Mode");
    }

    @Nullable
    public String getHighISONoiseReductionDescription() {
        return getIndexedDescription(177, BucketVersioningConfiguration.OFF, "Minimal", "Low", null, DCMEndpoint.Priority.NORMAL, null, DCMEndpoint.Priority.HIGH);
    }

    @Nullable
    public String getHueAdjustmentDescription() {
        return getFormattedString(146, "%s degrees");
    }

    @Nullable
    public String getIsoSettingDescription() {
        int[] intArray = ((NikonType2MakernoteDirectory) this._directory).getIntArray(2);
        if (intArray == null) {
            return null;
        }
        if (intArray[0] == 0 && intArray[1] != 0) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ISO ");
            outline107.append(intArray[1]);
            return outline107.toString();
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unknown (");
        outline1072.append(((NikonType2MakernoteDirectory) this._directory).getString(2));
        outline1072.append(")");
        return outline1072.toString();
    }

    @Nullable
    public String getLensDescription() {
        return getLensSpecificationDescription(132);
    }

    @Nullable
    public String getLensFocusDistance() {
        int[] decryptedIntArray = ((NikonType2MakernoteDirectory) this._directory).getDecryptedIntArray(152);
        if (decryptedIntArray == null || decryptedIntArray.length < 11) {
            return null;
        }
        return String.format("%.2fm", Double.valueOf(getDistanceInMeters(decryptedIntArray[10])));
    }

    @Nullable
    public String getLensStopsDescription() {
        return getEVDescription(139);
    }

    @Nullable
    public String getLensTypeDescription() {
        return getBitFlagDescription(131, new String[]{"AF", "MF"}, "D", "G", "VR");
    }

    @Nullable
    public String getNEFCompressionDescription() {
        return getIndexedDescription(147, 1, "Lossy (Type 1)", null, "Uncompressed", null, null, null, "Lossless", "Lossy (Type 2)");
    }

    @Nullable
    public String getPowerUpTimeDescription() {
        return getEpochTimeDescription(182);
    }

    @Nullable
    public String getProgramShiftDescription() {
        return getEVDescription(13);
    }

    @Nullable
    public String getShootingModeDescription() {
        return getBitFlagDescription(137, new String[]{"Single Frame", "Continuous"}, "Delay", null, "PC Control", "Exposure Bracketing", "Auto ISO", "White-Balance Bracketing", "IR Control");
    }

    @Nullable
    public String getVignetteControlDescription() {
        Integer integer = ((NikonType2MakernoteDirectory) this._directory).getInteger(42);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 3 ? intValue != 5 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL : "Low" : BucketVersioningConfiguration.OFF;
    }
}
