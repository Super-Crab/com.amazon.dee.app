package com.drew.metadata.exif.makernotes;

import amazon.speech.simclient.capability.CapabilityQueryConstants;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazon.alexa.voice.tta.Constants;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.PhotographicConversions;
import com.drew.lang.DateUtil;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory;
import com.here.sdk.search.PlaceCategory;
import java.math.RoundingMode;
import java.text.DecimalFormat;
/* loaded from: classes2.dex */
public class OlympusMakernoteDescriptor extends TagDescriptor<OlympusMakernoteDirectory> {
    public OlympusMakernoteDescriptor(@NotNull OlympusMakernoteDirectory olympusMakernoteDirectory) {
        super(olympusMakernoteDirectory);
    }

    @Nullable
    public String getApertureValueDescription() {
        Double doubleObject = ((OlympusMakernoteDirectory) this._directory).getDoubleObject(4098);
        if (doubleObject == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(PhotographicConversions.apertureToFStop(doubleObject.doubleValue()));
    }

    @Nullable
    public String getApexApertureDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_APEX_APERTURE_VALUE);
        if (longObject == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(Math.pow((longObject.longValue() / 16.0d) - 0.5d, 2.0d));
    }

    @Nullable
    public String getApexBrightnessDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_APEX_BRIGHTNESS_VALUE);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format((longObject.longValue() / 8.0d) - 6.0d);
    }

    @Nullable
    public String getApexFilmSpeedDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_APEX_FILM_SPEED_VALUE);
        if (longObject == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(Math.pow((longObject.longValue() / 8.0d) - 1.0d, 2.0d) * 3.125d);
    }

    @Nullable
    public String getApexShutterSpeedTimeDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_APEX_SHUTTER_SPEED_TIME_VALUE);
        if (longObject == null) {
            return null;
        }
        double pow = Math.pow((49 - longObject.longValue()) / 8.0d, 2.0d);
        DecimalFormat decimalFormat = new DecimalFormat("0.###");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(pow) + " sec";
    }

    @Nullable
    public String getBWModeDescription() {
        return getIndexedDescription(515, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getBlackAndWhiteFilterDescription() {
        return super.getDescription(OlympusMakernoteDirectory.CameraSettings.TAG_BLACK_AND_WHITE_FILTER);
    }

    @Nullable
    public String getBlueBalanceDescription() {
        int[] intArray = ((OlympusMakernoteDirectory) this._directory).getIntArray(OlympusMakernoteDirectory.TAG_BLUE_BALANCE);
        if (intArray == null) {
            return null;
        }
        return String.valueOf(((short) intArray[0]) / 256.0d);
    }

    @Nullable
    public String getBracketStepDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_BRACKET_STEP, "1/3 EV", "2/3 EV", "1 EV");
    }

    @Nullable
    public String getCameraIdDescription() {
        byte[] byteArray = ((OlympusMakernoteDirectory) this._directory).getByteArray(521);
        if (byteArray == null) {
            return null;
        }
        return new String(byteArray);
    }

    @Nullable
    public String getCameraModelDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_CAMERA_MODEL, "DiMAGE 7", "DiMAGE 5", "DiMAGE S304", "DiMAGE S404", "DiMAGE 7i", "DiMAGE 7Hi", "DiMAGE A1", "DiMAGE S414");
    }

    @Nullable
    public String getCameraTypeDescription() {
        String string = ((OlympusMakernoteDirectory) this._directory).getString(519);
        if (string == null) {
            return null;
        }
        return OlympusMakernoteDirectory.OlympusCameraTypes.containsKey(string) ? OlympusMakernoteDirectory.OlympusCameraTypes.get(string) : string;
    }

    @Nullable
    public String getColorFilterDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_COLOR_FILTER);
        if (longObject == null) {
            return null;
        }
        return Long.toString(longObject.longValue() - 3);
    }

    @Nullable
    public String getColorMatrixDescription() {
        int[] intArray = ((OlympusMakernoteDirectory) this._directory).getIntArray(4113);
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

    @Nullable
    public String getColorModeCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_COLOR_MODE, "Natural Color", "Black & White", "Vivid Color", "Solarization", "AdobeRGB");
    }

    @Nullable
    public String getColorModeDescription() {
        return getIndexedDescription(257, "Natural Colour", "Black & White", "Vivid Colour", "Solarization", "AdobeRGB");
    }

    @Nullable
    public String getContrastCameraSettingDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_CONTRAST);
        if (longObject == null) {
            return null;
        }
        return Long.toString(longObject.longValue() - 3);
    }

    @Nullable
    public String getContrastDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.TAG_CONTRAST, DCMEndpoint.Priority.HIGH, DCMEndpoint.Priority.NORMAL, "Low");
    }

    @Nullable
    public String getDateDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_DATE);
        if (longObject == null) {
            return null;
        }
        int longValue = (int) (longObject.longValue() & 255);
        int longValue2 = (int) ((longObject.longValue() >> 16) & 255);
        int longValue3 = ((int) (255 & (longObject.longValue() >> 8))) + 1970;
        return !DateUtil.isValidDate(longValue3, longValue2, longValue) ? "Invalid date" : String.format("%04d-%02d-%02d", Integer.valueOf(longValue3), Integer.valueOf(longValue2 + 1), Integer.valueOf(longValue));
    }

    @Nullable
    public String getDecSwitchPositionDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_DEC_SWITCH_POSITION, "Exposure", ExifInterface.TAG_CONTRAST, ExifInterface.TAG_SATURATION, "Filter");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        if (i != 0) {
            if (i == 519) {
                return getCameraTypeDescription();
            }
            if (i == 521) {
                return getCameraIdDescription();
            }
            if (i == 770) {
                return getOneTouchWbDescription();
            }
            if (i == 4100) {
                return getFlashModeDescription();
            }
            if (i == 4111) {
                return getSharpnessDescription();
            }
            if (i == 4113) {
                return getColorMatrixDescription();
            }
            if (i == 4117) {
                return getWbModeDescription();
            }
            if (i == 4137) {
                return getContrastDescription();
            }
            if (i == 4149) {
                return getPreviewImageValidDescription();
            }
            if (i == 4106) {
                return getFocusRangeDescription();
            }
            if (i == 4107) {
                return getFocusModeDescription();
            }
            if (i == 4119) {
                return getRedBalanceDescription();
            }
            if (i == 4120) {
                return getBlueBalanceDescription();
            }
            switch (i) {
                case 257:
                    return getColorModeDescription();
                case 258:
                    return getImageQuality1Description();
                case 259:
                    return getImageQuality2Description();
                default:
                    switch (i) {
                        case 512:
                            return getSpecialModeDescription();
                        case 513:
                            return getJpegQualityDescription();
                        case 514:
                            return getMacroModeDescription();
                        case 515:
                            return getBWModeDescription();
                        case 516:
                            return getDigitalZoomDescription();
                        case 517:
                            return getFocalPlaneDiagonalDescription();
                        default:
                            switch (i) {
                                case 4096:
                                    return getShutterSpeedDescription();
                                case 4097:
                                    return getIsoValueDescription();
                                case 4098:
                                    return getApertureValueDescription();
                                default:
                                    switch (i) {
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE /* 61442 */:
                                            return getExposureModeDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_MODE /* 61443 */:
                                            return getFlashModeCameraSettingDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE /* 61444 */:
                                            return getWhiteBalanceDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE /* 61445 */:
                                            return getImageSizeDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_IMAGE_QUALITY /* 61446 */:
                                            return getImageQualityDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SHOOTING_MODE /* 61447 */:
                                            return getShootingModeDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_METERING_MODE /* 61448 */:
                                            return getMeteringModeDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_APEX_FILM_SPEED_VALUE /* 61449 */:
                                            return getApexFilmSpeedDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_APEX_SHUTTER_SPEED_TIME_VALUE /* 61450 */:
                                            return getApexShutterSpeedTimeDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_APEX_APERTURE_VALUE /* 61451 */:
                                            return getApexApertureDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_MACRO_MODE /* 61452 */:
                                            return getMacroModeCameraSettingDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM /* 61453 */:
                                            return getDigitalZoomCameraSettingDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_EXPOSURE_COMPENSATION /* 61454 */:
                                            return getExposureCompensationDescription();
                                        case OlympusMakernoteDirectory.CameraSettings.TAG_BRACKET_STEP /* 61455 */:
                                            return getBracketStepDescription();
                                        default:
                                            switch (i) {
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_LENGTH /* 61457 */:
                                                    return getIntervalLengthDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_NUMBER /* 61458 */:
                                                    return getIntervalNumberDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_FOCAL_LENGTH /* 61459 */:
                                                    return getFocalLengthDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_DISTANCE /* 61460 */:
                                                    return getFocusDistanceDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_FIRED /* 61461 */:
                                                    return getFlashFiredDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_DATE /* 61462 */:
                                                    return getDateDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_TIME /* 61463 */:
                                                    return getTimeDescription();
                                                case OlympusMakernoteDirectory.CameraSettings.TAG_MAX_APERTURE_AT_FOCAL_LENGTH /* 61464 */:
                                                    return getMaxApertureAtFocalLengthDescription();
                                                default:
                                                    switch (i) {
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FILE_NUMBER_MEMORY /* 61467 */:
                                                            return getFileNumberMemoryDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_LAST_FILE_NUMBER /* 61468 */:
                                                            return getLastFileNumberDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_RED /* 61469 */:
                                                            return getWhiteBalanceRedDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_GREEN /* 61470 */:
                                                            return getWhiteBalanceGreenDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_BLUE /* 61471 */:
                                                            return getWhiteBalanceBlueDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SATURATION /* 61472 */:
                                                            return getSaturationDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_CONTRAST /* 61473 */:
                                                            return getContrastCameraSettingDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SHARPNESS /* 61474 */:
                                                            return getSharpnessCameraSettingDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SUBJECT_PROGRAM /* 61475 */:
                                                            return getSubjectProgramDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_COMPENSATION /* 61476 */:
                                                            return getFlashCompensationDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_ISO_SETTING /* 61477 */:
                                                            return getIsoSettingDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_CAMERA_MODEL /* 61478 */:
                                                            return getCameraModelDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_MODE /* 61479 */:
                                                            return getIntervalModeDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FOLDER_NAME /* 61480 */:
                                                            return getFolderNameDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_COLOR_MODE /* 61481 */:
                                                            return getColorModeCameraSettingDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_COLOR_FILTER /* 61482 */:
                                                            return getColorFilterDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_BLACK_AND_WHITE_FILTER /* 61483 */:
                                                            return getBlackAndWhiteFilterDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_INTERNAL_FLASH /* 61484 */:
                                                            return getInternalFlashDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_APEX_BRIGHTNESS_VALUE /* 61485 */:
                                                            return getApexBrightnessDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SPOT_FOCUS_POINT_X_COORDINATE /* 61486 */:
                                                            return getSpotFocusPointXCoordinateDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_SPOT_FOCUS_POINT_Y_COORDINATE /* 61487 */:
                                                            return getSpotFocusPointYCoordinateDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_WIDE_FOCUS_ZONE /* 61488 */:
                                                            return getWideFocusZoneDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE /* 61489 */:
                                                            return getFocusModeCameraSettingDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_AREA /* 61490 */:
                                                            return getFocusAreaDescription();
                                                        case OlympusMakernoteDirectory.CameraSettings.TAG_DEC_SWITCH_POSITION /* 61491 */:
                                                            return getDecSwitchPositionDescription();
                                                        default:
                                                            return super.getDescription(i);
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return getMakernoteVersionDescription();
    }

    @Nullable
    public String getDigitalZoomCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM, BucketVersioningConfiguration.OFF, "Electronic magnification", "Digital zoom 2x");
    }

    @Nullable
    public String getDigitalZoomDescription() {
        Rational rational = ((OlympusMakernoteDirectory) this._directory).getRational(516);
        if (rational == null) {
            return null;
        }
        return rational.toSimpleString(false);
    }

    @Nullable
    public String getExposureCompensationDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_EXPOSURE_COMPENSATION);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format((longObject.longValue() / 3.0d) - 2.0d) + " EV";
    }

    @Nullable
    public String getExposureModeDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE, "P", ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, ExifInterface.LATITUDE_SOUTH, "M");
    }

    @Nullable
    public String getFileNumberMemoryDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FILE_NUMBER_MEMORY, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getFlashCompensationDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_COMPENSATION);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format((longObject.longValue() - 6) / 3.0d) + " EV";
    }

    @Nullable
    public String getFlashFiredDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_FIRED, "No", "Yes");
    }

    @Nullable
    public String getFlashModeCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FLASH_MODE, DCMEndpoint.Priority.NORMAL, "Red-eye reduction", "Rear flash sync", "Wireless");
    }

    @Nullable
    public String getFlashModeDescription() {
        return getIndexedDescription(4100, null, null, "On", BucketVersioningConfiguration.OFF);
    }

    @Nullable
    public String getFocalLengthDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_FOCAL_LENGTH);
        if (longObject == null) {
            return null;
        }
        return TagDescriptor.getFocalLengthDescription(longObject.longValue() / 256.0d);
    }

    @Nullable
    public String getFocalPlaneDiagonalDescription() {
        Rational rational = ((OlympusMakernoteDirectory) this._directory).getRational(517);
        if (rational == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.###");
        return decimalFormat.format(rational.doubleValue()) + " mm";
    }

    @Nullable
    public String getFocusAreaDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_AREA, "Wide Focus (Normal)", "Spot Focus");
    }

    @Nullable
    public String getFocusDistanceDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_DISTANCE);
        if (longObject == null) {
            return null;
        }
        if (longObject.longValue() == 0) {
            return "Infinity";
        }
        return longObject + " mm";
    }

    @Nullable
    public String getFocusModeCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE, "Auto Focus", "Manual Focus");
    }

    @Nullable
    public String getFocusModeDescription() {
        return getIndexedDescription(4107, "Auto", "Manual");
    }

    @Nullable
    public String getFocusRangeDescription() {
        return getIndexedDescription(4106, DCMEndpoint.Priority.NORMAL, "Macro");
    }

    @Nullable
    public String getFolderNameDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_FOLDER_NAME, "Standard Form", "Data Form");
    }

    @Nullable
    public String getImageQuality1Description() {
        return getIndexedDescription(258, "Raw", "Super Fine", "Fine", "Standard", "Extra Fine");
    }

    @Nullable
    public String getImageQuality2Description() {
        return getIndexedDescription(259, "Raw", "Super Fine", "Fine", "Standard", "Extra Fine");
    }

    @Nullable
    public String getImageQualityDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_IMAGE_QUALITY, "Raw", "Super Fine", "Fine", "Standard", "Economy", "Extra Fine");
    }

    @Nullable
    public String getImageSizeDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE, "2560 x 1920", "1600 x 1200", "1280 x 960", "640 x 480");
    }

    @Nullable
    public String getInternalFlashDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_INTERNAL_FLASH, "Did Not Fire", "Fired");
    }

    @Nullable
    public String getIntervalLengthDescription() {
        if (!((OlympusMakernoteDirectory) this._directory).isIntervalMode()) {
            return CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
        }
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_LENGTH);
        if (longObject == null) {
            return null;
        }
        return longObject + " min";
    }

    @Nullable
    public String getIntervalModeDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_MODE, "Still Image", "Time Lapse Movie");
    }

    @Nullable
    public String getIntervalNumberDescription() {
        if (!((OlympusMakernoteDirectory) this._directory).isIntervalMode()) {
            return CapabilityQueryConstants.TARGET_NOT_AVAILABLE;
        }
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_INTERVAL_NUMBER);
        if (longObject != null) {
            return Long.toString(longObject.longValue());
        }
        return null;
    }

    @Nullable
    public String getIsoSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_ISO_SETTING, PlaceCategory.EAT_AND_DRINK, "200", PlaceCategory.TRANSPORT, PlaceCategory.FACILITIES, "Auto", "64");
    }

    @Nullable
    public String getIsoValueDescription() {
        Rational rational = ((OlympusMakernoteDirectory) this._directory).getRational(4097);
        if (rational == null) {
            return null;
        }
        return String.valueOf(Math.round(Math.pow(2.0d, rational.doubleValue() - 5.0d) * 100.0d));
    }

    @Nullable
    public String getJpegQualityDescription() {
        String string = ((OlympusMakernoteDirectory) this._directory).getString(519);
        if (string != null) {
            Integer integer = ((OlympusMakernoteDirectory) this._directory).getInteger(513);
            if (integer == null) {
                return null;
            }
            if ((string.startsWith("SX") && !string.startsWith("SX151")) || string.startsWith("D4322")) {
                int intValue = integer.intValue();
                if (intValue == 0) {
                    return "Standard Quality (Low)";
                }
                if (intValue == 1) {
                    return "High Quality (Normal)";
                }
                if (intValue == 2) {
                    return "Super High Quality (Fine)";
                }
                if (intValue == 6) {
                    return "RAW";
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown (");
                outline107.append(integer.toString());
                outline107.append(")");
                return outline107.toString();
            }
            int intValue2 = integer.intValue();
            if (intValue2 == 0) {
                return "Standard Quality (Low)";
            }
            if (intValue2 == 1) {
                return "High Quality (Normal)";
            }
            if (intValue2 == 2) {
                return "Super High Quality (Fine)";
            }
            if (intValue2 == 4) {
                return "RAW";
            }
            if (intValue2 == 5) {
                return "Medium-Fine";
            }
            if (intValue2 == 6) {
                return "Small-Fine";
            }
            if (intValue2 == 33) {
                return "Uncompressed";
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unknown (");
            outline1072.append(integer.toString());
            outline1072.append(")");
            return outline1072.toString();
        }
        return getIndexedDescription(513, 1, "Standard Quality", "High Quality", "Super High Quality");
    }

    @Nullable
    public String getLastFileNumberDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_LAST_FILE_NUMBER);
        if (longObject == null) {
            return null;
        }
        return longObject.longValue() == 0 ? "File Number Memory Off" : Long.toString(longObject.longValue());
    }

    @Nullable
    public String getMacroModeCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_MACRO_MODE, BucketVersioningConfiguration.OFF, "On");
    }

    @Nullable
    public String getMacroModeDescription() {
        return getIndexedDescription(514, "Normal (no macro)", "Macro");
    }

    @Nullable
    public String getMakernoteVersionDescription() {
        return getVersionBytesDescription(0, 2);
    }

    @Nullable
    public String getMaxApertureAtFocalLengthDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_TIME);
        if (longObject == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(Math.pow((longObject.longValue() / 16.0d) - 0.5d, 2.0d));
    }

    @Nullable
    public String getMeteringModeDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_METERING_MODE, "Multi-Segment", "Centre Weighted", "Spot");
    }

    @Nullable
    public String getOneTouchWbDescription() {
        return getIndexedDescription(770, BucketVersioningConfiguration.OFF, "On", "On (Preset)");
    }

    @Nullable
    public String getPreviewImageValidDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE_VALID, "No", "Yes");
    }

    @Nullable
    public String getRedBalanceDescription() {
        int[] intArray = ((OlympusMakernoteDirectory) this._directory).getIntArray(OlympusMakernoteDirectory.TAG_RED_BALANCE);
        if (intArray == null) {
            return null;
        }
        return String.valueOf(((short) intArray[0]) / 256.0d);
    }

    @Nullable
    public String getSaturationDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_SATURATION);
        if (longObject == null) {
            return null;
        }
        return Long.toString(longObject.longValue() - 3);
    }

    @Nullable
    public String getSharpnessCameraSettingDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_SHARPNESS, "Hard", DCMEndpoint.Priority.NORMAL, "Soft");
    }

    @Nullable
    public String getSharpnessDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.TAG_SHARPNESS, DCMEndpoint.Priority.NORMAL, "Hard", "Soft");
    }

    @Nullable
    public String getShootingModeDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_SHOOTING_MODE, "Single", "Continuous", "Self Timer", null, "Bracketing", "Interval", "UHS Continuous", "HS Continuous");
    }

    @Nullable
    public String getShutterSpeedDescription() {
        return super.getShutterSpeedDescription(4096);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0066  */
    @com.drew.lang.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getSpecialModeDescription() {
        /*
            r8 = this;
            T extends com.drew.metadata.Directory r0 = r8._directory
            com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory r0 = (com.drew.metadata.exif.makernotes.OlympusMakernoteDirectory) r0
            r1 = 512(0x200, float:7.175E-43)
            java.lang.Object r0 = r0.getObject(r1)
            long[] r0 = (long[]) r0
            if (r0 != 0) goto L10
            r0 = 0
            return r0
        L10:
            int r1 = r0.length
            r2 = 1
            if (r1 >= r2) goto L17
            java.lang.String r0 = ""
            return r0
        L17:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r3 = 0
            r3 = r0[r3]
            int r3 = (int) r3
            r4 = 3
            r5 = 2
            if (r3 == 0) goto L36
            java.lang.String r6 = "Unknown picture taking mode"
            if (r3 == r2) goto L2c
            if (r3 == r5) goto L33
            if (r3 == r4) goto L30
        L2c:
            r1.append(r6)
            goto L3b
        L30:
            java.lang.String r3 = "Panorama picture taking mode"
            goto L38
        L33:
            java.lang.String r3 = "Fast picture taking mode"
            goto L38
        L36:
            java.lang.String r3 = "Normal picture taking mode"
        L38:
            r1.append(r3)
        L3b:
            int r3 = r0.length
            if (r3 < r5) goto L63
            r6 = r0[r2]
            int r3 = (int) r6
            if (r3 == 0) goto L63
            if (r3 == r2) goto L60
            if (r3 == r5) goto L5d
            if (r3 == r4) goto L5a
            java.lang.String r3 = " / "
            r1.append(r3)
            r6 = r0[r2]
            r1.append(r6)
            java.lang.String r3 = "th in a sequence"
        L56:
            r1.append(r3)
            goto L63
        L5a:
            java.lang.String r3 = " / 3rd in a sequence"
            goto L56
        L5d:
            java.lang.String r3 = " / 2nd in a sequence"
            goto L56
        L60:
            java.lang.String r3 = " / 1st in a sequence"
            goto L56
        L63:
            int r3 = r0.length
            if (r3 < r4) goto L81
            r6 = r0[r5]
            int r0 = (int) r6
            if (r0 == r2) goto L7c
            if (r0 == r5) goto L79
            if (r0 == r4) goto L76
            r2 = 4
            if (r0 == r2) goto L73
            goto L81
        L73:
            java.lang.String r0 = " / Top to bottom panorama direction"
            goto L7e
        L76:
            java.lang.String r0 = " / Bottom to top panorama direction"
            goto L7e
        L79:
            java.lang.String r0 = " / Right to left panorama direction"
            goto L7e
        L7c:
            java.lang.String r0 = " / Left to right panorama direction"
        L7e:
            r1.append(r0)
        L81:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.makernotes.OlympusMakernoteDescriptor.getSpecialModeDescription():java.lang.String");
    }

    @Nullable
    public String getSpotFocusPointXCoordinateDescription() {
        return super.getDescription(OlympusMakernoteDirectory.CameraSettings.TAG_SPOT_FOCUS_POINT_X_COORDINATE);
    }

    @Nullable
    public String getSpotFocusPointYCoordinateDescription() {
        return super.getDescription(OlympusMakernoteDirectory.CameraSettings.TAG_SPOT_FOCUS_POINT_Y_COORDINATE);
    }

    @Nullable
    public String getSubjectProgramDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_SUBJECT_PROGRAM, "None", "Portrait", Constants.Namespaces.TEXT, "Night Portrait", "Sunset", "Sports Action");
    }

    @Nullable
    public String getTimeDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_TIME);
        if (longObject == null) {
            return null;
        }
        int longValue = (int) ((longObject.longValue() >> 8) & 255);
        int longValue2 = (int) ((longObject.longValue() >> 16) & 255);
        int longValue3 = (int) (255 & longObject.longValue());
        return !DateUtil.isValidTime(longValue, longValue2, longValue3) ? "Invalid time" : String.format("%02d:%02d:%02d", Integer.valueOf(longValue), Integer.valueOf(longValue2), Integer.valueOf(longValue3));
    }

    @Nullable
    public String getWbModeDescription() {
        int[] intArray = ((OlympusMakernoteDirectory) this._directory).getIntArray(OlympusMakernoteDirectory.TAG_WB_MODE);
        if (intArray == null) {
            return null;
        }
        String format = String.format("%d %d", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[1]));
        return format.equals("1 0") ? "Auto" : format.equals("1 2") ? "Auto (2)" : format.equals("1 4") ? "Auto (4)" : format.equals("2 2") ? "3000 Kelvin" : format.equals("2 3") ? "3700 Kelvin" : format.equals("2 4") ? "4000 Kelvin" : format.equals("2 5") ? "4500 Kelvin" : format.equals("2 6") ? "5500 Kelvin" : format.equals("2 7") ? "6500 Kelvin" : format.equals("2 8") ? "7500 Kelvin" : format.equals("3 0") ? "One-touch" : GeneratedOutlineSupport1.outline72("Unknown ", format);
    }

    @Nullable
    public String getWhiteBalanceBlueDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_BLUE);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format(longObject.longValue() / 256.0d);
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE, "Auto", "Daylight", "Cloudy", "Tungsten", null, "Custom", null, "Fluorescent", "Fluorescent 2", null, null, "Custom 2", "Custom 3");
    }

    @Nullable
    public String getWhiteBalanceGreenDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_GREEN);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format(longObject.longValue() / 256.0d);
    }

    @Nullable
    public String getWhiteBalanceRedDescription() {
        Long longObject = ((OlympusMakernoteDirectory) this._directory).getLongObject(OlympusMakernoteDirectory.CameraSettings.TAG_WHITE_BALANCE_RED);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        if (longObject == null) {
            return null;
        }
        return decimalFormat.format(longObject.longValue() / 256.0d);
    }

    @Nullable
    public String getWideFocusZoneDescription() {
        return getIndexedDescription(OlympusMakernoteDirectory.CameraSettings.TAG_WIDE_FOCUS_ZONE, "No Zone or AF Failed", "Center Zone (Horizontal Orientation)", "Center Zone (Vertical Orientation)", "Left Zone", "Right Zone");
    }
}
