package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.here.sdk.search.PlaceCategory;
/* loaded from: classes2.dex */
public class CasioType2MakernoteDescriptor extends TagDescriptor<CasioType2MakernoteDirectory> {
    public CasioType2MakernoteDescriptor(@NotNull CasioType2MakernoteDirectory casioType2MakernoteDirectory) {
        super(casioType2MakernoteDirectory);
    }

    @Nullable
    public String getCasioPreviewThumbnailDescription() {
        byte[] byteArray = ((CasioType2MakernoteDirectory) this._directory).getByteArray(8192);
        if (byteArray == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107(Config.Compare.LESS_THAN), byteArray.length, " bytes of image data>");
    }

    @Nullable
    public String getCcdIsoSensitivityDescription() {
        return getIndexedDescription(CasioType2MakernoteDirectory.TAG_CCD_ISO_SENSITIVITY, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getColourModeDescription() {
        return getIndexedDescription(CasioType2MakernoteDirectory.TAG_COLOUR_MODE, BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getContrastDescription() {
        return getIndexedDescription(32, "-1", DCMEndpoint.Priority.NORMAL, "+1");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 2) {
            if (i == 3) {
                return getThumbnailSizeDescription();
            }
            if (i == 4) {
                return getThumbnailOffsetDescription();
            }
            if (i == 8) {
                return getQualityModeDescription();
            }
            if (i == 9) {
                return getImageSizeDescription();
            }
            if (i == 13) {
                return getFocusMode1Description();
            }
            if (i == 20) {
                return getIsoSensitivityDescription();
            }
            if (i == 25) {
                return getWhiteBalance1Description();
            }
            if (i == 29) {
                return getFocalLengthDescription();
            }
            if (i == 8192) {
                return getCasioPreviewThumbnailDescription();
            }
            if (i == 8226) {
                return getObjectDistanceDescription();
            }
            if (i == 8244) {
                return getFlashDistanceDescription();
            }
            if (i == 12294) {
                return getTimeZoneDescription();
            }
            if (i == 8209) {
                return getWhiteBalanceBiasDescription();
            }
            if (i == 8210) {
                return getWhiteBalance2Description();
            }
            switch (i) {
                case 31:
                    return getSaturationDescription();
                case 32:
                    return getContrastDescription();
                case 33:
                    return getSharpnessDescription();
                default:
                    switch (i) {
                        case 12288:
                            return getRecordModeDescription();
                        case 12289:
                            return getSelfTimerDescription();
                        case 12290:
                            return getQualityDescription();
                        case CasioType2MakernoteDirectory.TAG_FOCUS_MODE_2 /* 12291 */:
                            return getFocusMode2Description();
                        default:
                            switch (i) {
                                case CasioType2MakernoteDirectory.TAG_CCD_ISO_SENSITIVITY /* 12308 */:
                                    return getCcdIsoSensitivityDescription();
                                case CasioType2MakernoteDirectory.TAG_COLOUR_MODE /* 12309 */:
                                    return getColourModeDescription();
                                case CasioType2MakernoteDirectory.TAG_ENHANCEMENT /* 12310 */:
                                    return getEnhancementDescription();
                                case CasioType2MakernoteDirectory.TAG_FILTER /* 12311 */:
                                    return getFilterDescription();
                                default:
                                    return super.getDescription(i);
                            }
                    }
            }
        }
        return getThumbnailDimensionsDescription();
    }

    @Nullable
    public String getEnhancementDescription() {
        return getIndexedDescription(CasioType2MakernoteDirectory.TAG_ENHANCEMENT, BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getFilterDescription() {
        return getIndexedDescription(CasioType2MakernoteDirectory.TAG_FILTER, BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getFlashDistanceDescription() {
        return getIndexedDescription(CasioType2MakernoteDirectory.TAG_FLASH_DISTANCE, BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getFocalLengthDescription() {
        Double doubleObject = ((CasioType2MakernoteDirectory) this._directory).getDoubleObject(29);
        if (doubleObject == null) {
            return null;
        }
        return TagDescriptor.getFocalLengthDescription(doubleObject.doubleValue() / 10.0d);
    }

    @Nullable
    public String getFocusMode1Description() {
        return getIndexedDescription(13, DCMEndpoint.Priority.NORMAL, "Macro");
    }

    @Nullable
    public String getFocusMode2Description() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(CasioType2MakernoteDirectory.TAG_FOCUS_MODE_2);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 1 ? intValue != 6 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Multi-Area Focus" : "Fixation";
    }

    @Nullable
    public String getImageSizeDescription() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(9);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return "640 x 480 pixels";
        }
        if (intValue == 36) {
            return "3008 x 2008 pixels";
        }
        if (intValue == 4) {
            return "1600 x 1200 pixels";
        }
        if (intValue == 5) {
            return "2048 x 1536 pixels";
        }
        switch (intValue) {
            case 20:
                return "2288 x 1712 pixels";
            case 21:
                return "2592 x 1944 pixels";
            case 22:
                return "2304 x 1728 pixels";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getIsoSensitivityDescription() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(20);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 3 ? intValue != 4 ? intValue != 6 ? intValue != 9 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "200" : PlaceCategory.EAT_AND_DRINK : "64" : "50";
    }

    @Nullable
    public String getObjectDistanceDescription() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(8226);
        if (integer == null) {
            return null;
        }
        return Integer.toString(integer.intValue()) + " mm";
    }

    @Nullable
    public String getQualityDescription() {
        return getIndexedDescription(12290, 3, "Fine");
    }

    @Nullable
    public String getQualityModeDescription() {
        return getIndexedDescription(8, 1, "Fine", "Super Fine");
    }

    @Nullable
    public String getRecordModeDescription() {
        return getIndexedDescription(12288, 2, DCMEndpoint.Priority.NORMAL);
    }

    @Nullable
    public String getSaturationDescription() {
        return getIndexedDescription(31, "-1", DCMEndpoint.Priority.NORMAL, "+1");
    }

    @Nullable
    public String getSelfTimerDescription() {
        return getIndexedDescription(12289, 1, BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getSharpnessDescription() {
        return getIndexedDescription(33, "-1", DCMEndpoint.Priority.NORMAL, "+1");
    }

    @Nullable
    public String getThumbnailDimensionsDescription() {
        int[] intArray = ((CasioType2MakernoteDirectory) this._directory).getIntArray(2);
        if (intArray == null || intArray.length != 2) {
            return ((CasioType2MakernoteDirectory) this._directory).getString(2);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(intArray[0]);
        sb.append(" x ");
        return GeneratedOutlineSupport1.outline86(sb, intArray[1], " pixels");
    }

    @Nullable
    public String getThumbnailOffsetDescription() {
        return ((CasioType2MakernoteDirectory) this._directory).getString(4);
    }

    @Nullable
    public String getThumbnailSizeDescription() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(3);
        if (integer == null) {
            return null;
        }
        return Integer.toString(integer.intValue()) + " bytes";
    }

    @Nullable
    public String getTimeZoneDescription() {
        return ((CasioType2MakernoteDirectory) this._directory).getString(CasioType2MakernoteDirectory.TAG_TIME_ZONE);
    }

    @Nullable
    public String getWhiteBalance1Description() {
        return getIndexedDescription(25, "Auto", "Daylight", "Shade", "Tungsten", "Florescent", "Manual");
    }

    @Nullable
    public String getWhiteBalance2Description() {
        Integer integer = ((CasioType2MakernoteDirectory) this._directory).getInteger(8210);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? (intValue == 4 || intValue == 12) ? ExifInterface.TAG_FLASH : GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Auto" : "Manual";
    }

    @Nullable
    public String getWhiteBalanceBiasDescription() {
        return ((CasioType2MakernoteDirectory) this._directory).getString(8209);
    }
}
