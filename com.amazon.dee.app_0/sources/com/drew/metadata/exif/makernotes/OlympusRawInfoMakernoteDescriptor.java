package com.drew.metadata.exif.makernotes;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class OlympusRawInfoMakernoteDescriptor extends TagDescriptor<OlympusRawInfoMakernoteDirectory> {
    public OlympusRawInfoMakernoteDescriptor(@NotNull OlympusRawInfoMakernoteDirectory olympusRawInfoMakernoteDirectory) {
        super(olympusRawInfoMakernoteDirectory);
    }

    @Nullable
    public String getColorMatrix2Description() {
        int[] intArray = ((OlympusRawInfoMakernoteDirectory) this._directory).getIntArray(512);
        if (intArray == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArray.length; i++) {
            sb.append((int) ((short) intArray[i]));
            if (i < intArray.length - 1) {
                sb.append(" ");
            }
        }
        if (sb.length() != 0) {
            return sb.toString();
        }
        return null;
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 0 ? i != 512 ? i != 1537 ? i != 4096 ? super.getDescription(i) : getOlympusLightSourceDescription() : getYCbCrCoefficientsDescription() : getColorMatrix2Description() : getVersionBytesDescription(0, 4);
    }

    @Nullable
    public String getOlympusLightSourceDescription() {
        Integer integer = ((OlympusRawInfoMakernoteDirectory) this._directory).getInteger(4096);
        if (integer == null) {
            return null;
        }
        short shortValue = integer.shortValue();
        if (shortValue == 0) {
            return "Unknown";
        }
        if (shortValue == 20) {
            return "Tungsten (Incandescent)";
        }
        if (shortValue == 22) {
            return "Evening Sunlight";
        }
        if (shortValue == 256) {
            return "One Touch White Balance";
        }
        if (shortValue == 512) {
            return "Custom 1-4";
        }
        switch (shortValue) {
            case 16:
                return "Shade";
            case 17:
                return "Cloudy";
            case 18:
                return "Fine Weather";
            default:
                switch (shortValue) {
                    case 33:
                        return "Daylight Fluorescent";
                    case 34:
                        return "Day White Fluorescent";
                    case 35:
                        return "Cool White Fluorescent";
                    case 36:
                        return "White Fluorescent";
                    default:
                        return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
                }
        }
    }

    @Nullable
    public String getYCbCrCoefficientsDescription() {
        int[] intArray = ((OlympusRawInfoMakernoteDirectory) this._directory).getIntArray(1537);
        if (intArray == null) {
            return null;
        }
        Rational[] rationalArr = new Rational[intArray.length / 2];
        for (int i = 0; i < intArray.length / 2; i++) {
            int i2 = i * 2;
            rationalArr[i] = new Rational((short) intArray[i2], (short) intArray[i2 + 1]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < rationalArr.length; i3++) {
            sb.append(rationalArr[i3].doubleValue());
            if (i3 < rationalArr.length - 1) {
                sb.append(" ");
            }
        }
        if (sb.length() != 0) {
            return sb.toString();
        }
        return null;
    }
}
