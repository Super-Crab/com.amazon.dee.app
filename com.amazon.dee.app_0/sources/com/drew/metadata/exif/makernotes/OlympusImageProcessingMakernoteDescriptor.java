package com.drew.metadata.exif.makernotes;

import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class OlympusImageProcessingMakernoteDescriptor extends TagDescriptor<OlympusImageProcessingMakernoteDirectory> {
    public OlympusImageProcessingMakernoteDescriptor(@NotNull OlympusImageProcessingMakernoteDirectory olympusImageProcessingMakernoteDirectory) {
        super(olympusImageProcessingMakernoteDirectory);
    }

    @Nullable
    public String getAspectRatioDescription() {
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagAspectRatio);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1]));
        return format.equals("1 1") ? "4:3" : format.equals("1 4") ? "1:1" : format.equals("2 1") ? "3:2 (RAW)" : format.equals("2 2") ? "3:2" : format.equals("3 1") ? "16:9 (RAW)" : format.equals("3 3") ? "16:9" : format.equals("4 1") ? "1:1 (RAW)" : format.equals("4 4") ? "6:6" : format.equals("5 5") ? "5:4" : format.equals("6 6") ? "7:6" : format.equals("7 7") ? "6:5" : format.equals("8 8") ? "7:5" : format.equals("9 1") ? "3:4 (RAW)" : format.equals("9 9") ? "3:4" : GeneratedOutlineSupport1.outline75("Unknown (", format, ")");
    }

    @Nullable
    public String getColorMatrixDescription() {
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(512);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            if (i != 0) {
                sb.append(" ");
            }
            sb.append((int) ((short) intArray[i]));
        }
        return sb.toString();
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 0) {
            if (i == 512) {
                return getColorMatrixDescription();
            }
            if (i == 4124) {
                return getMultipleExposureModeDescription();
            }
            if (i == 4370) {
                return getAspectRatioDescription();
            }
            if (i == 6400) {
                return getKeystoneCompensationDescription();
            }
            if (i == 6401) {
                return getKeystoneDirectionDescription();
            }
            switch (i) {
                case 4112:
                    return getNoiseReduction2Description();
                case 4113:
                    return getDistortionCorrection2Description();
                case 4114:
                    return getShadingCompensation2Description();
                default:
                    return super.getDescription(i);
            }
        }
        return getImageProcessingVersionDescription();
    }

    @Nullable
    public String getDistortionCorrection2Description() {
        return getIndexedDescription(4113, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getImageProcessingVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    @Nullable
    public String getKeystoneCompensationDescription() {
        byte[] byteArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getByteArray(OlympusImageProcessingMakernoteDirectory.TagKeystoneCompensation);
        if (byteArray == null || byteArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", Byte.valueOf(byteArray[0]), Byte.valueOf(byteArray[1]));
        return format.equals("0 0") ? BucketVersioningConfiguration.OFF : format.equals("0 1") ? "On" : GeneratedOutlineSupport1.outline75("Unknown (", format, ")");
    }

    @Nullable
    public String getKeystoneDirectionDescription() {
        return getIndexedDescription(OlympusImageProcessingMakernoteDirectory.TagKeystoneDirection, "Vertical", "Horizontal");
    }

    @Nullable
    public String getMultipleExposureModeDescription() {
        String str;
        int[] intArray = ((OlympusImageProcessingMakernoteDirectory) this._directory).getIntArray(4124);
        if (intArray == null) {
            Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4124);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        short s = (short) intArray[0];
        if (s == 0) {
            str = BucketVersioningConfiguration.OFF;
        } else if (s == 2) {
            str = "On (2 frames)";
        } else if (s != 3) {
            sb.append("Unknown (");
            sb.append((int) ((short) intArray[0]));
            str = ")";
        } else {
            str = "On (3 frames)";
        }
        sb.append(str);
        if (intArray.length > 1) {
            sb.append("; ");
            sb.append((int) ((short) intArray[1]));
        }
        return sb.toString();
    }

    @Nullable
    public String getNoiseReduction2Description() {
        Integer integer = ((OlympusImageProcessingMakernoteDirectory) this._directory).getInteger(4112);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        short shortValue = integer.shortValue();
        if ((shortValue & 1) != 0) {
            sb.append("Noise Reduction, ");
        }
        if (((shortValue >> 1) & 1) != 0) {
            sb.append("Noise Filter, ");
        }
        if (((shortValue >> 2) & 1) != 0) {
            sb.append("Noise Filter (ISO Boost), ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    @Nullable
    public String getShadingCompensation2Description() {
        return getIndexedDescription(4114, BucketVersioningConfiguration.OFF, "On");
    }
}
