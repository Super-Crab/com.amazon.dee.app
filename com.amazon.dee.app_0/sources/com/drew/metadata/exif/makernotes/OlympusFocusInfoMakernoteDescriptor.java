package com.drew.metadata.exif.makernotes;

import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
public class OlympusFocusInfoMakernoteDescriptor extends TagDescriptor<OlympusFocusInfoMakernoteDirectory> {
    public OlympusFocusInfoMakernoteDescriptor(@NotNull OlympusFocusInfoMakernoteDirectory olympusFocusInfoMakernoteDirectory) {
        super(olympusFocusInfoMakernoteDirectory);
    }

    @Nullable
    public String getAfPointDescription() {
        Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagAfPoint);
        if (integer == null) {
            return null;
        }
        return integer.toString();
    }

    @Nullable
    public String getAutoFocusDescription() {
        return getIndexedDescription(521, BucketVersioningConfiguration.OFF, "On");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 0 ? i != 521 ? i != 773 ? i != 776 ? i != 4609 ? i != 5376 ? i != 5632 ? i != 4612 ? i != 4613 ? i != 4617 ? i != 4618 ? super.getDescription(i) : getMacroLedDescription() : getManualFlashDescription() : getExternalFlashZoomDescription() : getExternalFlashBounceDescription() : getImageStabilizationDescription() : getSensorTemperatureDescription() : getExternalFlashDescription() : getAfPointDescription() : getFocusDistanceDescription() : getAutoFocusDescription() : getFocusInfoVersionDescription();
    }

    @Nullable
    public String getExternalFlashBounceDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagExternalFlashBounce, "Bounce or Off", "Direct");
    }

    @Nullable
    public String getExternalFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(4609);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        String format = String.format("%d %d", Short.valueOf((short) intArray[0]), Short.valueOf((short) intArray[1]));
        return format.equals("0 0") ? BucketVersioningConfiguration.OFF : format.equals("1 0") ? "On" : GeneratedOutlineSupport1.outline75("Unknown (", format, ")");
    }

    @Nullable
    public String getExternalFlashZoomDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
        if (intArray == null) {
            Integer integer = ((OlympusFocusInfoMakernoteDirectory) this._directory).getInteger(OlympusFocusInfoMakernoteDirectory.TagExternalFlashZoom);
            if (integer == null) {
                return null;
            }
            intArray = new int[]{integer.intValue()};
        }
        if (intArray.length == 0) {
            return null;
        }
        String format = String.format("%d", Short.valueOf((short) intArray[0]));
        if (intArray.length > 1) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(format, " ");
            outline113.append(String.format("%d", Short.valueOf((short) intArray[1])));
            format = outline113.toString();
        }
        return format.equals("0") ? BucketVersioningConfiguration.OFF : format.equals("1") ? "On" : format.equals("0 0") ? BucketVersioningConfiguration.OFF : format.equals("1 0") ? "On" : GeneratedOutlineSupport1.outline75("Unknown (", format, ")");
    }

    @Nullable
    public String getFocusDistanceDescription() {
        Rational rational = ((OlympusFocusInfoMakernoteDirectory) this._directory).getRational(773);
        if (rational == null || rational.getNumerator() == BodyPartID.bodyIdMax || rational.getNumerator() == 0) {
            return "inf";
        }
        return (rational.getNumerator() / 1000.0d) + " m";
    }

    @Nullable
    public String getFocusInfoVersionDescription() {
        return getVersionBytesDescription(0, 4);
    }

    @Nullable
    public String getImageStabilizationDescription() {
        byte[] byteArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getByteArray(OlympusFocusInfoMakernoteDirectory.TagImageStabilization);
        if (byteArray == null) {
            return null;
        }
        if ((byteArray[0] | byteArray[1] | byteArray[2] | byteArray[3]) == 0) {
            return BucketVersioningConfiguration.OFF;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("On, ");
        outline107.append((byteArray[43] & 1) > 0 ? "Mode 1" : "Mode 2");
        return outline107.toString();
    }

    @Nullable
    public String getMacroLedDescription() {
        return getIndexedDescription(OlympusFocusInfoMakernoteDirectory.TagMacroLed, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getManualFlashDescription() {
        int[] intArray = ((OlympusFocusInfoMakernoteDirectory) this._directory).getIntArray(OlympusFocusInfoMakernoteDirectory.TagManualFlash);
        if (intArray == null) {
            return null;
        }
        return ((short) intArray[0]) == 0 ? BucketVersioningConfiguration.OFF : ((short) intArray[1]) == 1 ? "Full" : GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("On (1/"), (short) intArray[1], " strength)");
    }

    @Nullable
    public String getSensorTemperatureDescription() {
        return ((OlympusFocusInfoMakernoteDirectory) this._directory).getString(OlympusFocusInfoMakernoteDirectory.TagSensorTemperature);
    }
}
