package com.drew.metadata.exif.makernotes;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazon.alexa.voice.tta.Constants;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
/* loaded from: classes2.dex */
public class FujifilmMakernoteDescriptor extends TagDescriptor<FujifilmMakernoteDirectory> {
    public FujifilmMakernoteDescriptor(@NotNull FujifilmMakernoteDirectory fujifilmMakernoteDirectory) {
        super(fujifilmMakernoteDirectory);
    }

    @Nullable
    private String getMakernoteVersionDescription() {
        return getVersionBytesDescription(0, 2);
    }

    @Nullable
    public String getAutoBracketingDescription() {
        return getIndexedDescription(FujifilmMakernoteDirectory.TAG_AUTO_BRACKETING, BucketVersioningConfiguration.OFF, "On", "No Flash & Flash");
    }

    @Nullable
    public String getAutoExposureWarningDescription() {
        return getIndexedDescription(4866, "AE Good", "Over Exposed");
    }

    @Nullable
    public String getBlurWarningDescription() {
        return getIndexedDescription(FujifilmMakernoteDirectory.TAG_BLUR_WARNING, "No Blur Warning", "Blur warning");
    }

    @Nullable
    public String getColorSaturationDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4099);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return DCMEndpoint.Priority.NORMAL;
        }
        if (intValue == 128) {
            return "Medium High";
        }
        if (intValue == 256) {
            return DCMEndpoint.Priority.HIGH;
        }
        if (intValue == 384) {
            return "Medium Low";
        }
        if (intValue == 512) {
            return "Low";
        }
        if (intValue == 32768) {
            return "Film Simulation";
        }
        switch (intValue) {
            case 768:
                return "None (B&W)";
            case 769:
                return "B&W Green Filter";
            case 770:
                return "B&W Yellow Filter";
            case 771:
                return "B&W Blue Filter";
            case 772:
                return "B&W Sepia";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getContrastDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4102);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 256 ? intValue != 768 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Low" : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL;
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 0) {
            if (i == 4102) {
                return getContrastDescription();
            }
            if (i == 4107) {
                return getNoiseReductionDescription();
            }
            if (i == 4110) {
                return getHighIsoNoiseReductionDescription();
            }
            if (i == 4352) {
                return getAutoBracketingDescription();
            }
            if (i == 4624) {
                return getFinePixColorDescription();
            }
            if (i == 4112) {
                return getFlashModeDescription();
            }
            if (i == 4113) {
                return getFlashExposureValueDescription();
            }
            if (i == 4128) {
                return getMacroDescription();
            }
            if (i == 4129) {
                return getFocusModeDescription();
            }
            if (i == 4144) {
                return getSlowSyncDescription();
            }
            if (i == 4145) {
                return getPictureModeDescription();
            }
            if (i == 4147) {
                return getExrAutoDescription();
            }
            if (i == 4148) {
                return getExrModeDescription();
            }
            switch (i) {
                case 4097:
                    return getSharpnessDescription();
                case 4098:
                    return getWhiteBalanceDescription();
                case 4099:
                    return getColorSaturationDescription();
                case 4100:
                    return getToneDescription();
                default:
                    switch (i) {
                        case FujifilmMakernoteDirectory.TAG_BLUR_WARNING /* 4864 */:
                            return getBlurWarningDescription();
                        case 4865:
                            return getFocusWarningDescription();
                        case 4866:
                            return getAutoExposureWarningDescription();
                        default:
                            switch (i) {
                                case FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE /* 5120 */:
                                    return getDynamicRangeDescription();
                                case FujifilmMakernoteDirectory.TAG_FILM_MODE /* 5121 */:
                                    return getFilmModeDescription();
                                case FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE_SETTING /* 5122 */:
                                    return getDynamicRangeSettingDescription();
                                default:
                                    return super.getDescription(i);
                            }
                    }
            }
        }
        return getMakernoteVersionDescription();
    }

    @Nullable
    public String getDynamicRangeDescription() {
        return getIndexedDescription(FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE, 1, "Standard", null, "Wide");
    }

    @Nullable
    public String getDynamicRangeSettingDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE_SETTING);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 256 ? intValue != 32768 ? intValue != 512 ? intValue != 513 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Wide 2 (400%)" : "Wide 1 (230%)" : "Film Simulation" : "Standard (100%)" : "Manual" : "Auto (100-400%)";
    }

    @Nullable
    public String getExrAutoDescription() {
        return getIndexedDescription(4147, "Auto", "Manual");
    }

    @Nullable
    public String getExrModeDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4148);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 256 ? intValue != 512 ? intValue != 768 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "DR (Dynamic Range Priority)" : "SN (Signal to Noise Priority)" : "HR (High Resolution)";
    }

    @Nullable
    public String getFilmModeDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(FujifilmMakernoteDirectory.TAG_FILM_MODE);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 256 ? intValue != 272 ? intValue != 288 ? intValue != 304 ? intValue != 512 ? intValue != 768 ? intValue != 1024 ? intValue != 1280 ? intValue != 1281 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Pro Neg. Hi" : "Pro Neg. Std" : "F4/Velvia" : "F3/Studio Portrait Ex" : "F2/Fujichrome (Velvia)" : "F1c/Studio Portrait Increased Sharpness" : "F1b/Studio Portrait Smooth Skin Tone (Astia)" : "F1a/Studio Portrait Enhanced Saturation" : "F1/Studio Portrait" : "F0/Standard (Provia) ";
    }

    @Nullable
    public String getFinePixColorDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(FujifilmMakernoteDirectory.TAG_FINE_PIX_COLOR);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 16 ? intValue != 48 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "B&W" : "Chrome" : "Standard";
    }

    @Nullable
    public String getFlashExposureValueDescription() {
        Rational rational = ((FujifilmMakernoteDirectory) this._directory).getRational(4113);
        if (rational == null) {
            return null;
        }
        return rational.toSimpleString(false) + " EV (Apex)";
    }

    @Nullable
    public String getFlashModeDescription() {
        return getIndexedDescription(4112, "Auto", "On", BucketVersioningConfiguration.OFF, "Red-eye Reduction", "External");
    }

    @Nullable
    public String getFocusModeDescription() {
        return getIndexedDescription(4129, "Auto Focus", "Manual Focus");
    }

    @Nullable
    public String getFocusWarningDescription() {
        return getIndexedDescription(4865, "Good Focus", "Out Of Focus");
    }

    @Nullable
    public String getHighIsoNoiseReductionDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4110);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 256 ? intValue != 512 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Weak" : "Strong" : DCMEndpoint.Priority.NORMAL;
    }

    @Nullable
    public String getMacroDescription() {
        return getIndexedDescription(4128, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getNoiseReductionDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4107);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 64 ? intValue != 128 ? intValue != 256 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : CapabilityQueryConstants.TARGET_NOT_AVAILABLE : DCMEndpoint.Priority.NORMAL : "Low";
    }

    @Nullable
    public String getPictureModeDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4145);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 27) {
            return "Dog Face Detection";
        }
        if (intValue == 28) {
            return "Cat Face Detection";
        }
        if (intValue == 256) {
            return "Aperture priority AE";
        }
        if (intValue == 512) {
            return "Shutter priority AE";
        }
        if (intValue == 768) {
            return "Manual exposure";
        }
        switch (intValue) {
            case 0:
                return "Auto";
            case 1:
                return "Portrait scene";
            case 2:
                return "Landscape scene";
            case 3:
                return "Macro";
            case 4:
                return "Sports scene";
            case 5:
                return "Night scene";
            case 6:
                return "Program AE";
            case 7:
                return "Natural Light";
            case 8:
                return "Anti-blur";
            case 9:
                return "Beach & Snow";
            case 10:
                return "Sunset";
            case 11:
                return "Museum";
            case 12:
                return "Party";
            case 13:
                return "Flower";
            case 14:
                return Constants.Namespaces.TEXT;
            case 15:
                return "Natural Light & Flash";
            case 16:
                return "Beach";
            case 17:
                return "Snow";
            case 18:
                return "Fireworks";
            case 19:
                return "Underwater";
            case 20:
                return "Portrait with Skin Correction";
            default:
                switch (intValue) {
                    case 22:
                        return "Panorama";
                    case 23:
                        return "Night (Tripod)";
                    case 24:
                        return "Pro Low-light";
                    case 25:
                        return "Pro Focus";
                    default:
                        return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
                }
        }
    }

    @Nullable
    public String getSharpnessDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4097);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 1 ? intValue != 2 ? intValue != 3 ? intValue != 4 ? intValue != 5 ? intValue != 130 ? intValue != 132 ? intValue != 32768 ? intValue != 65535 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : CapabilityQueryConstants.TARGET_NOT_AVAILABLE : "Film Simulation" : "Medium Hard" : "Medium Soft" : "Hardest" : "Hard" : DCMEndpoint.Priority.NORMAL : "Soft" : "Softest";
    }

    @Nullable
    public String getSlowSyncDescription() {
        return getIndexedDescription(4144, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getToneDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4100);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 128 ? intValue != 256 ? intValue != 384 ? intValue != 512 ? intValue != 768 ? intValue != 32768 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Film Simulation" : "None (B&W)" : "Low" : "Medium Low" : DCMEndpoint.Priority.HIGH : "Medium High" : DCMEndpoint.Priority.NORMAL;
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        Integer integer = ((FujifilmMakernoteDirectory) this._directory).getInteger(4098);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return "Auto";
        }
        if (intValue == 256) {
            return "Daylight";
        }
        if (intValue == 512) {
            return "Cloudy";
        }
        if (intValue == 1024) {
            return "Incandescence";
        }
        if (intValue == 1280) {
            return ExifInterface.TAG_FLASH;
        }
        if (intValue == 4080) {
            return "Kelvin";
        }
        switch (intValue) {
            case 768:
                return "Daylight Fluorescent";
            case 769:
                return "Day White Fluorescent";
            case 770:
                return "White Fluorescent";
            case 771:
                return "Warm White Fluorescent";
            case 772:
                return "Living Room Warm White Fluorescent";
            default:
                switch (intValue) {
                    case 3840:
                        return "Custom White Balance";
                    case OlympusMakernoteDirectory.TAG_DATA_DUMP_2 /* 3841 */:
                        return "Custom White Balance 2";
                    case 3842:
                        return "Custom White Balance 3";
                    case 3843:
                        return "Custom White Balance 4";
                    case 3844:
                        return "Custom White Balance 5";
                    default:
                        return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
                }
        }
    }
}
