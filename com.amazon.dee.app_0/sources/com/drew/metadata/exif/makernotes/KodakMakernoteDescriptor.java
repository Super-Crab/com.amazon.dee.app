package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class KodakMakernoteDescriptor extends TagDescriptor<KodakMakernoteDirectory> {
    public KodakMakernoteDescriptor(@NotNull KodakMakernoteDirectory kodakMakernoteDirectory) {
        super(kodakMakernoteDirectory);
    }

    @Nullable
    public String getBurstModeDescription() {
        return getIndexedDescription(10, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getColorModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(102);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 1 ? intValue != 2 ? intValue != 3 ? intValue != 4 ? intValue != 32 ? intValue != 64 ? intValue != 256 ? intValue != 512 ? intValue != 8192 ? intValue != 16384 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Sepia" : "B&W" : "Neutral Color" : "Saturated Color" : "Neutral Color" : "Saturated Color" : "B&W Red Filter" : "B&W Yellow Filter" : "Sepia" : "B&W";
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        return i != 9 ? i != 10 ? i != 27 ? i != 56 ? i != 64 ? i != 102 ? i != 107 ? i != 92 ? i != 93 ? super.getDescription(i) : getFlashFiredDescription() : getFlashModeDescription() : getSharpnessDescription() : getColorModeDescription() : getWhiteBalanceDescription() : getFocusModeDescription() : getShutterModeDescription() : getBurstModeDescription() : getQualityDescription();
    }

    @Nullable
    public String getFlashFiredDescription() {
        return getIndexedDescription(93, "No", "Yes");
    }

    @Nullable
    public String getFlashModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(92);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 2 ? intValue != 3 ? intValue != 16 ? intValue != 32 ? intValue != 64 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Red Eye" : BucketVersioningConfiguration.OFF : "Fill Flash" : "Red Eye" : BucketVersioningConfiguration.OFF : "Fill Flash" : "Auto";
    }

    @Nullable
    public String getFocusModeDescription() {
        return getIndexedDescription(56, DCMEndpoint.Priority.NORMAL, null, "Macro");
    }

    @Nullable
    public String getQualityDescription() {
        return getIndexedDescription(9, 1, "Fine", DCMEndpoint.Priority.NORMAL);
    }

    @Nullable
    public String getSharpnessDescription() {
        return getIndexedDescription(107, DCMEndpoint.Priority.NORMAL);
    }

    @Nullable
    public String getShutterModeDescription() {
        Integer integer = ((KodakMakernoteDirectory) this._directory).getInteger(27);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 8 ? intValue != 32 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Manual" : "Aperture Priority" : "Auto";
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        return getIndexedDescription(64, "Auto", ExifInterface.TAG_FLASH, "Tungsten", "Daylight");
    }
}
