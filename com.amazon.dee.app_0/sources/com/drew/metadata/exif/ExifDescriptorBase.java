package com.drew.metadata.exif;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.PhotographicConversions;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class ExifDescriptorBase<T extends Directory> extends TagDescriptor<T> {
    private final boolean _allowDecimalRepresentationOfRationals;

    public ExifDescriptorBase(@NotNull T t) {
        super(t);
        this._allowDecimalRepresentationOfRationals = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0052, code lost:
        if (r12.length >= ((r5 * r6) + 2)) goto L33;
     */
    @com.drew.lang.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int[] decodeCfaPattern(int r12) {
        /*
            r11 = this;
            T extends com.drew.metadata.Directory r0 = r11._directory
            byte[] r12 = r0.getByteArray(r12)
            if (r12 != 0) goto La
            r12 = 0
            return r12
        La:
            int r0 = r12.length
            r1 = 4
            r2 = 0
            if (r0 >= r1) goto L1d
            int r0 = r12.length
            int[] r0 = new int[r0]
        L12:
            int r1 = r12.length
            if (r2 >= r1) goto L1c
            r1 = r12[r2]
            r0[r2] = r1
            int r2 = r2 + 1
            goto L12
        L1c:
            return r0
        L1d:
            int r0 = r12.length
            r3 = 2
            int r0 = r0 - r3
            int[] r0 = new int[r0]
            com.drew.lang.ByteArrayReader r4 = new com.drew.lang.ByteArrayReader     // Catch: java.io.IOException -> L70
            r4.<init>(r12)     // Catch: java.io.IOException -> L70
            short r5 = r4.getInt16(r2)     // Catch: java.io.IOException -> L70
            short r6 = r4.getInt16(r3)     // Catch: java.io.IOException -> L70
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r2)     // Catch: java.io.IOException -> L70
            int r8 = r5 * r6
            int r8 = r8 + r3
            int r9 = r12.length     // Catch: java.io.IOException -> L70
            r10 = 1
            if (r8 <= r9) goto L54
            boolean r5 = r4.isMotorolaByteOrder()     // Catch: java.io.IOException -> L70
            if (r5 != 0) goto L42
            r5 = r10
            goto L43
        L42:
            r5 = r2
        L43:
            r4.setMotorolaByteOrder(r5)     // Catch: java.io.IOException -> L70
            short r5 = r4.getInt16(r2)     // Catch: java.io.IOException -> L70
            short r6 = r4.getInt16(r3)     // Catch: java.io.IOException -> L70
            int r8 = r12.length     // Catch: java.io.IOException -> L70
            int r9 = r5 * r6
            int r9 = r9 + r3
            if (r8 < r9) goto L58
        L54:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r10)     // Catch: java.io.IOException -> L70
        L58:
            boolean r3 = r7.booleanValue()     // Catch: java.io.IOException -> L70
            if (r3 == 0) goto L87
            r0[r2] = r5     // Catch: java.io.IOException -> L70
            r0[r10] = r6     // Catch: java.io.IOException -> L70
        L62:
            int r2 = r12.length     // Catch: java.io.IOException -> L70
            if (r1 >= r2) goto L87
            int r2 = r1 + (-2)
            byte r3 = r4.getInt8(r1)     // Catch: java.io.IOException -> L70
            r0[r2] = r3     // Catch: java.io.IOException -> L70
            int r1 = r1 + 1
            goto L62
        L70:
            r12 = move-exception
            T extends com.drew.metadata.Directory r1 = r11._directory
            java.lang.String r2 = "IO exception processing data: "
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r2)
            java.lang.String r12 = r12.getMessage()
            r2.append(r12)
            java.lang.String r12 = r2.toString()
            r1.addError(r12)
        L87:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.exif.ExifDescriptorBase.decodeCfaPattern(int):int[]");
    }

    @Nullable
    private static String formatCFAPattern(@Nullable int[] iArr) {
        String str;
        if (iArr == null) {
            return null;
        }
        if (iArr.length < 2) {
            return "<truncated data>";
        }
        if (iArr[0] == 0 && iArr[1] == 0) {
            return "<zero pattern size>";
        }
        int i = (iArr[0] * iArr[1]) + 2;
        if (i > iArr.length) {
            return "<invalid pattern size>";
        }
        String[] strArr = {"Red", "Green", "Blue", "Cyan", "Magenta", "Yellow", "White"};
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
        for (int i2 = 2; i2 < i; i2++) {
            outline107.append(iArr[i2] <= strArr.length - 1 ? strArr[iArr[i2]] : "Unknown");
            if ((i2 - 2) % iArr[1] == 0) {
                str = ",";
            } else if (i2 != i - 1) {
                str = "][";
            }
            outline107.append(str);
        }
        outline107.append("]");
        return outline107.toString();
    }

    @Nullable
    private String getUnicodeDescription(int i) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return new String(byteArray, "UTF-16LE").trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public String get35mmFilmEquivFocalLengthDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 0 ? "Unknown" : TagDescriptor.getFocalLengthDescription(integer.intValue());
    }

    @Nullable
    public String getApertureValueDescription() {
        Double doubleObject = this._directory.getDoubleObject(ExifDirectoryBase.TAG_APERTURE);
        if (doubleObject == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(PhotographicConversions.apertureToFStop(doubleObject.doubleValue()));
    }

    @Nullable
    public String getBitsPerSampleDescription() {
        String string = this._directory.getString(258);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " bits/component/pixel");
    }

    @Nullable
    public String getCfaPattern2Description() {
        byte[] byteArray = this._directory.getByteArray(ExifDirectoryBase.TAG_CFA_PATTERN_2);
        if (byteArray == null) {
            return null;
        }
        int[] intArray = this._directory.getIntArray(ExifDirectoryBase.TAG_CFA_REPEAT_PATTERN_DIM);
        if (intArray == null) {
            return String.format("Repeat Pattern not found for CFAPattern (%s)", super.getDescription(ExifDirectoryBase.TAG_CFA_PATTERN_2));
        }
        if (intArray.length != 2 || byteArray.length != intArray[0] * intArray[1]) {
            return String.format("Unknown Pattern (%s)", super.getDescription(ExifDirectoryBase.TAG_CFA_PATTERN_2));
        }
        int[] iArr = new int[byteArray.length + 2];
        iArr[0] = intArray[0];
        iArr[1] = intArray[1];
        for (int i = 0; i < byteArray.length; i++) {
            iArr[i + 2] = byteArray[i] & 255;
        }
        return formatCFAPattern(iArr);
    }

    @Nullable
    public String getCfaPatternDescription() {
        return formatCFAPattern(decodeCfaPattern(ExifDirectoryBase.TAG_CFA_PATTERN));
    }

    @Nullable
    public String getColorSpaceDescription() {
        Integer integer = this._directory.getInteger(40961);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 1 ? "sRGB" : integer.intValue() == 65535 ? "Undefined" : GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
    }

    @Nullable
    public String getComponentConfigurationDescription() {
        int[] intArray = this._directory.getIntArray(ExifDirectoryBase.TAG_COMPONENTS_CONFIGURATION);
        if (intArray == null) {
            return null;
        }
        String[] strArr = {"", "Y", "Cb", "Cr", "R", "G", "B"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(4, intArray.length); i++) {
            int i2 = intArray[i];
            if (i2 > 0 && i2 < strArr.length) {
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }

    @Nullable
    public String getCompressedAverageBitsPerPixelDescription() {
        StringBuilder outline107;
        String str;
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL);
        if (rational == null) {
            return null;
        }
        String simpleString = rational.toSimpleString(true);
        if (!rational.isInteger() || rational.intValue() != 1) {
            outline107 = GeneratedOutlineSupport1.outline107(simpleString);
            str = " bits/pixel";
        } else {
            outline107 = GeneratedOutlineSupport1.outline107(simpleString);
            str = " bit/pixel";
        }
        outline107.append(str);
        return outline107.toString();
    }

    @Nullable
    public String getCompressionDescription() {
        Integer integer = this._directory.getInteger(259);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 32766) {
            return EntertainmentMetrics.Button.NEXT;
        }
        if (intValue == 32767) {
            return "Sony ARW Compressed";
        }
        switch (intValue) {
            case 1:
                return "Uncompressed";
            case 2:
                return "CCITT 1D";
            case 3:
                return "T4/Group 3 Fax";
            case 4:
                return "T6/Group 4 Fax";
            case 5:
                return "LZW";
            case 6:
                return "JPEG (old-style)";
            case 7:
                return "JPEG";
            case 8:
                return "Adobe Deflate";
            case 9:
                return "JBIG B&W";
            case 10:
                return "JBIG Color";
            default:
                switch (intValue) {
                    case 99:
                        return "JPEG";
                    case 262:
                        return "Kodak 262";
                    case 32809:
                        return "Thunderscan";
                    case 32867:
                        return "Kodak KDC Compressed";
                    case 34661:
                        return "JBIG";
                    case 34715:
                        return "JBIG2 TIFF FX";
                    case ExifInterface.DATA_LOSSY_JPEG /* 34892 */:
                        return "Lossy JPEG";
                    case 65000:
                        return "Kodak DCR Compressed";
                    case 65535:
                        return "Pentax PEF Compressed";
                    default:
                        switch (intValue) {
                            case PanasonicMakernoteDirectory.TAG_SCENE_MODE /* 32769 */:
                                return "Packed RAW";
                            case FujifilmMakernoteDirectory.TAG_ORDER_NUMBER /* 32770 */:
                                return "Samsung SRW Compressed";
                            case FujifilmMakernoteDirectory.TAG_FRAME_NUMBER /* 32771 */:
                                return "CCIRLEW";
                            case PanasonicMakernoteDirectory.TAG_WB_RED_LEVEL /* 32772 */:
                                return "Samsung SRW Compressed 2";
                            case 32773:
                                return "PackBits";
                            default:
                                switch (intValue) {
                                    case 32895:
                                        return "IT8CTPAD";
                                    case 32896:
                                        return "IT8LW";
                                    case 32897:
                                        return "IT8MP";
                                    case 32898:
                                        return "IT8BL";
                                    default:
                                        switch (intValue) {
                                            case 32908:
                                                return "PixarFilm";
                                            case 32909:
                                                return "PixarLog";
                                            default:
                                                switch (intValue) {
                                                    case 32946:
                                                        return "Deflate";
                                                    case 32947:
                                                        return "DCS";
                                                    default:
                                                        switch (intValue) {
                                                            case 34676:
                                                                return "SGILog";
                                                            case 34677:
                                                                return "SGILog24";
                                                            default:
                                                                switch (intValue) {
                                                                    case 34712:
                                                                        return "JPEG 2000";
                                                                    case 34713:
                                                                        return "Nikon NEF Compressed";
                                                                    default:
                                                                        switch (intValue) {
                                                                            case 34718:
                                                                                return "Microsoft Document Imaging (MDI) Binary Level Codec";
                                                                            case 34719:
                                                                                return "Microsoft Document Imaging (MDI) Progressive Transform Codec";
                                                                            case 34720:
                                                                                return "Microsoft Document Imaging (MDI) Vector";
                                                                            default:
                                                                                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
                                                                        }
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String getContrastDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_CONTRAST, "None", "Soft", "Hard");
    }

    @Nullable
    public String getCustomRenderedDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_CUSTOM_RENDERED, "Normal process", "Custom process");
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        switch (i) {
            case 1:
                return getInteropIndexDescription();
            case 2:
                return getInteropVersionDescription();
            case 254:
                return getNewSubfileTypeDescription();
            case 255:
                return getSubfileTypeDescription();
            case 256:
                return getImageWidthDescription();
            case 257:
                return getImageHeightDescription();
            case 258:
                return getBitsPerSampleDescription();
            case 259:
                return getCompressionDescription();
            case 262:
                return getPhotometricInterpretationDescription();
            case 263:
                return getThresholdingDescription();
            case 266:
                return getFillOrderDescription();
            case 274:
                return getOrientationDescription();
            case 277:
                return getSamplesPerPixelDescription();
            case 278:
                return getRowsPerStripDescription();
            case 279:
                return getStripByteCountsDescription();
            case 282:
                return getXResolutionDescription();
            case 283:
                return getYResolutionDescription();
            case 284:
                return getPlanarConfigurationDescription();
            case 296:
                return getResolutionDescription();
            case 512:
                return getJpegProcDescription();
            case 530:
                return getYCbCrSubsamplingDescription();
            case 531:
                return getYCbCrPositioningDescription();
            case 532:
                return getReferenceBlackWhiteDescription();
            case ExifDirectoryBase.TAG_CFA_PATTERN_2 /* 33422 */:
                return getCfaPattern2Description();
            case ExifDirectoryBase.TAG_EXPOSURE_TIME /* 33434 */:
                return getExposureTimeDescription();
            case ExifDirectoryBase.TAG_FNUMBER /* 33437 */:
                return getFNumberDescription();
            case ExifDirectoryBase.TAG_EXPOSURE_PROGRAM /* 34850 */:
                return getExposureProgramDescription();
            case ExifDirectoryBase.TAG_ISO_EQUIVALENT /* 34855 */:
                return getIsoEquivalentDescription();
            case ExifDirectoryBase.TAG_SENSITIVITY_TYPE /* 34864 */:
                return getSensitivityTypeRangeDescription();
            case 36864:
                return getExifVersionDescription();
            case ExifDirectoryBase.TAG_COMPONENTS_CONFIGURATION /* 37121 */:
                return getComponentConfigurationDescription();
            case ExifDirectoryBase.TAG_COMPRESSED_AVERAGE_BITS_PER_PIXEL /* 37122 */:
                return getCompressedAverageBitsPerPixelDescription();
            case ExifDirectoryBase.TAG_SHUTTER_SPEED /* 37377 */:
                return getShutterSpeedDescription();
            case ExifDirectoryBase.TAG_APERTURE /* 37378 */:
                return getApertureValueDescription();
            case ExifDirectoryBase.TAG_EXPOSURE_BIAS /* 37380 */:
                return getExposureBiasDescription();
            case ExifDirectoryBase.TAG_MAX_APERTURE /* 37381 */:
                return getMaxApertureValueDescription();
            case ExifDirectoryBase.TAG_SUBJECT_DISTANCE /* 37382 */:
                return getSubjectDistanceDescription();
            case ExifDirectoryBase.TAG_METERING_MODE /* 37383 */:
                return getMeteringModeDescription();
            case 37384:
                return getWhiteBalanceDescription();
            case ExifDirectoryBase.TAG_FLASH /* 37385 */:
                return getFlashDescription();
            case ExifDirectoryBase.TAG_FOCAL_LENGTH /* 37386 */:
                return getFocalLengthDescription();
            case ExifDirectoryBase.TAG_USER_COMMENT /* 37510 */:
                return getUserCommentDescription();
            case ExifDirectoryBase.TAG_WIN_TITLE /* 40091 */:
                return getWindowsTitleDescription();
            case ExifDirectoryBase.TAG_WIN_COMMENT /* 40092 */:
                return getWindowsCommentDescription();
            case ExifDirectoryBase.TAG_WIN_AUTHOR /* 40093 */:
                return getWindowsAuthorDescription();
            case ExifDirectoryBase.TAG_WIN_KEYWORDS /* 40094 */:
                return getWindowsKeywordsDescription();
            case ExifDirectoryBase.TAG_WIN_SUBJECT /* 40095 */:
                return getWindowsSubjectDescription();
            case ExifDirectoryBase.TAG_FLASHPIX_VERSION /* 40960 */:
                return getFlashPixVersionDescription();
            case 40961:
                return getColorSpaceDescription();
            case ExifDirectoryBase.TAG_EXIF_IMAGE_WIDTH /* 40962 */:
                return getExifImageWidthDescription();
            case ExifDirectoryBase.TAG_EXIF_IMAGE_HEIGHT /* 40963 */:
                return getExifImageHeightDescription();
            case ExifDirectoryBase.TAG_FOCAL_PLANE_X_RESOLUTION /* 41486 */:
                return getFocalPlaneXResolutionDescription();
            case ExifDirectoryBase.TAG_FOCAL_PLANE_Y_RESOLUTION /* 41487 */:
                return getFocalPlaneYResolutionDescription();
            case ExifDirectoryBase.TAG_FOCAL_PLANE_RESOLUTION_UNIT /* 41488 */:
                return getFocalPlaneResolutionUnitDescription();
            case ExifDirectoryBase.TAG_SENSING_METHOD /* 41495 */:
                return getSensingMethodDescription();
            case ExifDirectoryBase.TAG_FILE_SOURCE /* 41728 */:
                return getFileSourceDescription();
            case ExifDirectoryBase.TAG_SCENE_TYPE /* 41729 */:
                return getSceneTypeDescription();
            case ExifDirectoryBase.TAG_CFA_PATTERN /* 41730 */:
                return getCfaPatternDescription();
            case ExifDirectoryBase.TAG_CUSTOM_RENDERED /* 41985 */:
                return getCustomRenderedDescription();
            case ExifDirectoryBase.TAG_EXPOSURE_MODE /* 41986 */:
                return getExposureModeDescription();
            case ExifDirectoryBase.TAG_WHITE_BALANCE_MODE /* 41987 */:
                return getWhiteBalanceModeDescription();
            case ExifDirectoryBase.TAG_DIGITAL_ZOOM_RATIO /* 41988 */:
                return getDigitalZoomRatioDescription();
            case ExifDirectoryBase.TAG_35MM_FILM_EQUIV_FOCAL_LENGTH /* 41989 */:
                return get35mmFilmEquivFocalLengthDescription();
            case ExifDirectoryBase.TAG_SCENE_CAPTURE_TYPE /* 41990 */:
                return getSceneCaptureTypeDescription();
            case ExifDirectoryBase.TAG_GAIN_CONTROL /* 41991 */:
                return getGainControlDescription();
            case ExifDirectoryBase.TAG_CONTRAST /* 41992 */:
                return getContrastDescription();
            case ExifDirectoryBase.TAG_SATURATION /* 41993 */:
                return getSaturationDescription();
            case ExifDirectoryBase.TAG_SHARPNESS /* 41994 */:
                return getSharpnessDescription();
            case ExifDirectoryBase.TAG_SUBJECT_DISTANCE_RANGE /* 41996 */:
                return getSubjectDistanceRangeDescription();
            case ExifDirectoryBase.TAG_LENS_SPECIFICATION /* 42034 */:
                return getLensSpecificationDescription();
            default:
                return super.getDescription(i);
        }
    }

    @Nullable
    public String getDigitalZoomRatioDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_DIGITAL_ZOOM_RATIO);
        if (rational == null) {
            return null;
        }
        return rational.getNumerator() == 0 ? "Digital zoom not used" : new DecimalFormat("0.#").format(rational.doubleValue());
    }

    @Nullable
    public String getExifImageHeightDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_EXIF_IMAGE_HEIGHT);
        if (integer == null) {
            return null;
        }
        return integer + " pixels";
    }

    @Nullable
    public String getExifImageWidthDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_EXIF_IMAGE_WIDTH);
        if (integer == null) {
            return null;
        }
        return integer + " pixels";
    }

    @Nullable
    public String getExifVersionDescription() {
        return getVersionBytesDescription(36864, 2);
    }

    @Nullable
    public String getExposureBiasDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_EXPOSURE_BIAS);
        if (rational == null) {
            return null;
        }
        return rational.toSimpleString(true) + " EV";
    }

    @Nullable
    public String getExposureModeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_EXPOSURE_MODE, "Auto exposure", "Manual exposure", "Auto bracket");
    }

    @Nullable
    public String getExposureProgramDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_EXPOSURE_PROGRAM, 1, "Manual control", "Program normal", "Aperture priority", "Shutter priority", "Program creative (slow program)", "Program action (high-speed program)", "Portrait mode", "Landscape mode");
    }

    @Nullable
    public String getExposureTimeDescription() {
        String string = this._directory.getString(ExifDirectoryBase.TAG_EXPOSURE_TIME);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " sec");
    }

    @Nullable
    public String getFNumberDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FNUMBER);
        if (rational == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(rational.doubleValue());
    }

    @Nullable
    public String getFileSourceDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_FILE_SOURCE, 1, "Film Scanner", "Reflection Print Scanner", "Digital Still Camera (DSC)");
    }

    @Nullable
    public String getFillOrderDescription() {
        return getIndexedDescription(266, 1, DCMEndpoint.Priority.NORMAL, "Reversed");
    }

    @Nullable
    public String getFlashDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_FLASH);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((integer.intValue() & 1) != 0 ? "Flash fired" : "Flash did not fire");
        if ((integer.intValue() & 4) != 0) {
            sb.append((integer.intValue() & 2) != 0 ? ", return detected" : ", return not detected");
        }
        if ((integer.intValue() & 16) != 0) {
            sb.append(", auto");
        }
        if ((integer.intValue() & 64) != 0) {
            sb.append(", red-eye reduction");
        }
        return sb.toString();
    }

    @Nullable
    public String getFlashPixVersionDescription() {
        return getVersionBytesDescription(ExifDirectoryBase.TAG_FLASHPIX_VERSION, 2);
    }

    @Nullable
    public String getFocalLengthDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_LENGTH);
        if (rational == null) {
            return null;
        }
        return TagDescriptor.getFocalLengthDescription(rational.doubleValue());
    }

    @Nullable
    public String getFocalPlaneResolutionUnitDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 1, "(No unit)", "Inches", "cm");
    }

    @Nullable
    public String getFocalPlaneXResolutionDescription() {
        String sb;
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_PLANE_X_RESOLUTION);
        if (rational == null) {
            return null;
        }
        String focalPlaneResolutionUnitDescription = getFocalPlaneResolutionUnitDescription();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(rational.getReciprocal().toSimpleString(true));
        if (focalPlaneResolutionUnitDescription == null) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" ");
            outline107.append(focalPlaneResolutionUnitDescription.toLowerCase());
            sb = outline107.toString();
        }
        sb2.append(sb);
        return sb2.toString();
    }

    @Nullable
    public String getFocalPlaneYResolutionDescription() {
        String sb;
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_FOCAL_PLANE_Y_RESOLUTION);
        if (rational == null) {
            return null;
        }
        String focalPlaneResolutionUnitDescription = getFocalPlaneResolutionUnitDescription();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(rational.getReciprocal().toSimpleString(true));
        if (focalPlaneResolutionUnitDescription == null) {
            sb = "";
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(" ");
            outline107.append(focalPlaneResolutionUnitDescription.toLowerCase());
            sb = outline107.toString();
        }
        sb2.append(sb);
        return sb2.toString();
    }

    @Nullable
    public String getGainControlDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_GAIN_CONTROL, "None", "Low gain up", "Low gain down", "High gain up", "High gain down");
    }

    @Nullable
    public String getImageHeightDescription() {
        String string = this._directory.getString(257);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }

    @Nullable
    public String getImageWidthDescription() {
        String string = this._directory.getString(256);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " pixels");
    }

    @Nullable
    public String getInteropIndexDescription() {
        String string = this._directory.getString(1);
        if (string == null) {
            return null;
        }
        return "R98".equalsIgnoreCase(string.trim()) ? "Recommended Exif Interoperability Rules (ExifR98)" : GeneratedOutlineSupport1.outline75("Unknown (", string, ")");
    }

    @Nullable
    public String getInteropVersionDescription() {
        return getVersionBytesDescription(2, 2);
    }

    @Nullable
    public String getIsoEquivalentDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_ISO_EQUIVALENT);
        if (integer != null) {
            return Integer.toString(integer.intValue());
        }
        return null;
    }

    @Nullable
    public String getJpegProcDescription() {
        Integer integer = this._directory.getInteger(512);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 1 ? intValue != 14 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Lossless" : "Baseline";
    }

    @Nullable
    public String getLensSpecificationDescription() {
        return getLensSpecificationDescription(ExifDirectoryBase.TAG_LENS_SPECIFICATION);
    }

    @Nullable
    public String getMaxApertureValueDescription() {
        Double doubleObject = this._directory.getDoubleObject(ExifDirectoryBase.TAG_MAX_APERTURE);
        if (doubleObject == null) {
            return null;
        }
        return TagDescriptor.getFStopDescription(PhotographicConversions.apertureToFStop(doubleObject.doubleValue()));
    }

    @Nullable
    public String getMeteringModeDescription() {
        Integer integer = this._directory.getInteger(ExifDirectoryBase.TAG_METERING_MODE);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 255) {
            return "(Other)";
        }
        switch (intValue) {
            case 0:
                return "Unknown";
            case 1:
                return "Average";
            case 2:
                return "Center weighted average";
            case 3:
                return "Spot";
            case 4:
                return "Multi-spot";
            case 5:
                return "Multi-segment";
            case 6:
                return "Partial";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getNewSubfileTypeDescription() {
        return getIndexedDescription(254, 0, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image", "Single page of multi-page reduced-resolution image", "Transparency mask", "Transparency mask of reduced-resolution image", "Transparency mask of multi-page image", "Transparency mask of reduced-resolution multi-page image");
    }

    @Nullable
    public String getOrientationDescription() {
        return super.getOrientationDescription(274);
    }

    @Nullable
    public String getPhotometricInterpretationDescription() {
        Integer integer = this._directory.getInteger(262);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 32803) {
            return "Color Filter Array";
        }
        if (intValue == 32892) {
            return "Linear Raw";
        }
        switch (intValue) {
            case 0:
                return "WhiteIsZero";
            case 1:
                return "BlackIsZero";
            case 2:
                return "RGB";
            case 3:
                return "RGB Palette";
            case 4:
                return "Transparency Mask";
            case 5:
                return "CMYK";
            case 6:
                return "YCbCr";
            default:
                switch (intValue) {
                    case 8:
                        return "CIELab";
                    case 9:
                        return "ICCLab";
                    case 10:
                        return "ITULab";
                    default:
                        switch (intValue) {
                            case 32844:
                                return "Pixar LogL";
                            case 32845:
                                return "Pixar LogLuv";
                            default:
                                return "Unknown colour space";
                        }
                }
        }
    }

    @Nullable
    public String getPlanarConfigurationDescription() {
        return getIndexedDescription(284, 1, "Chunky (contiguous for each subsampling pixel)", "Separate (Y-plane/Cb-plane/Cr-plane format)");
    }

    @Nullable
    public String getReferenceBlackWhiteDescription() {
        int[] intArray = this._directory.getIntArray(532);
        if (intArray == null || intArray.length < 6) {
            Object object = this._directory.getObject(532);
            if (object == null || !(object instanceof long[])) {
                return null;
            }
            long[] jArr = (long[]) object;
            if (jArr.length < 6) {
                return null;
            }
            int[] iArr = new int[jArr.length];
            for (int i = 0; i < jArr.length; i++) {
                iArr[i] = (int) jArr[i];
            }
            intArray = iArr;
        }
        return String.format("[%d,%d,%d] [%d,%d,%d]", Integer.valueOf(intArray[0]), Integer.valueOf(intArray[2]), Integer.valueOf(intArray[4]), Integer.valueOf(intArray[1]), Integer.valueOf(intArray[3]), Integer.valueOf(intArray[5]));
    }

    @Nullable
    public String getResolutionDescription() {
        return getIndexedDescription(296, 1, "(No unit)", "Inch", "cm");
    }

    @Nullable
    public String getRowsPerStripDescription() {
        String string = this._directory.getString(278);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " rows/strip");
    }

    @Nullable
    public String getSamplesPerPixelDescription() {
        String string = this._directory.getString(277);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " samples/pixel");
    }

    @Nullable
    public String getSaturationDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SATURATION, "None", "Low saturation", "High saturation");
    }

    @Nullable
    public String getSceneCaptureTypeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SCENE_CAPTURE_TYPE, "Standard", "Landscape", "Portrait", "Night scene");
    }

    @Nullable
    public String getSceneTypeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SCENE_TYPE, 1, "Directly photographed image");
    }

    @Nullable
    public String getSensingMethodDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SENSING_METHOD, 1, "(Not defined)", "One-chip color area sensor", "Two-chip color area sensor", "Three-chip color area sensor", "Color sequential area sensor", null, "Trilinear sensor", "Color sequential linear sensor");
    }

    @Nullable
    public String getSensitivityTypeRangeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SENSITIVITY_TYPE, "Unknown", "Standard Output Sensitivity", "Recommended Exposure Index", "ISO Speed", "Standard Output Sensitivity and Recommended Exposure Index", "Standard Output Sensitivity and ISO Speed", "Recommended Exposure Index and ISO Speed", "Standard Output Sensitivity, Recommended Exposure Index and ISO Speed");
    }

    @Nullable
    public String getSharpnessDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SHARPNESS, "None", "Low", "Hard");
    }

    @Nullable
    public String getShutterSpeedDescription() {
        return super.getShutterSpeedDescription(ExifDirectoryBase.TAG_SHUTTER_SPEED);
    }

    @Nullable
    public String getStripByteCountsDescription() {
        String string = this._directory.getString(279);
        if (string == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(string, " bytes");
    }

    @Nullable
    public String getSubfileTypeDescription() {
        return getIndexedDescription(255, 1, "Full-resolution image", "Reduced-resolution image", "Single page of multi-page image");
    }

    @Nullable
    public String getSubjectDistanceDescription() {
        Rational rational = this._directory.getRational(ExifDirectoryBase.TAG_SUBJECT_DISTANCE);
        if (rational == null) {
            return null;
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.0##");
        return decimalFormat.format(rational.doubleValue()) + " metres";
    }

    @Nullable
    public String getSubjectDistanceRangeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_SUBJECT_DISTANCE_RANGE, "Unknown", "Macro", "Close view", "Distant view");
    }

    @Nullable
    public String getThresholdingDescription() {
        return getIndexedDescription(263, 1, "No dithering or halftoning", "Ordered dither or halftone", "Randomized dither");
    }

    @Nullable
    public String getUserCommentDescription() {
        byte[] byteArray = this._directory.getByteArray(ExifDirectoryBase.TAG_USER_COMMENT);
        if (byteArray == null) {
            return null;
        }
        if (byteArray.length == 0) {
            return "";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("ASCII", System.getProperty("file.encoding"));
        hashMap.put("UNICODE", "UTF-16LE");
        hashMap.put("JIS", "Shift-JIS");
        try {
            if (byteArray.length >= 10) {
                String str = new String(byteArray, 0, 10);
                for (Map.Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    String str3 = (String) entry.getValue();
                    if (str.startsWith(str2)) {
                        for (int length = str2.length(); length < 10; length++) {
                            byte b = byteArray[length];
                            if (b != 0 && b != 32) {
                                return new String(byteArray, length, byteArray.length - length, str3).trim();
                            }
                        }
                        return new String(byteArray, 10, byteArray.length - 10, str3).trim();
                    }
                }
            }
            return new String(byteArray, System.getProperty("file.encoding")).trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        Integer integer = this._directory.getInteger(37384);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 0) {
            return "Unknown";
        }
        if (intValue == 1) {
            return "Daylight";
        }
        if (intValue == 2) {
            return "Florescent";
        }
        if (intValue == 3) {
            return "Tungsten";
        }
        if (intValue == 4) {
            return ExifInterface.TAG_FLASH;
        }
        if (intValue == 255) {
            return "(Other)";
        }
        switch (intValue) {
            case 9:
                return "Fine Weather";
            case 10:
                return "Cloudy";
            case 11:
                return "Shade";
            case 12:
                return "Daylight Fluorescent";
            case 13:
                return "Day White Fluorescent";
            case 14:
                return "Cool White Fluorescent";
            case 15:
                return "White Fluorescent";
            case 16:
                return "Warm White Fluorescent";
            case 17:
                return "Standard light";
            case 18:
                return "Standard light (B)";
            case 19:
                return "Standard light (C)";
            case 20:
                return "D55";
            case 21:
                return "D65";
            case 22:
                return "D75";
            case 23:
                return "D50";
            case 24:
                return "Studio Tungsten";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getWhiteBalanceModeDescription() {
        return getIndexedDescription(ExifDirectoryBase.TAG_WHITE_BALANCE_MODE, "Auto white balance", "Manual white balance");
    }

    @Nullable
    public String getWindowsAuthorDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_AUTHOR);
    }

    @Nullable
    public String getWindowsCommentDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_COMMENT);
    }

    @Nullable
    public String getWindowsKeywordsDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_KEYWORDS);
    }

    @Nullable
    public String getWindowsSubjectDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_SUBJECT);
    }

    @Nullable
    public String getWindowsTitleDescription() {
        return getUnicodeDescription(ExifDirectoryBase.TAG_WIN_TITLE);
    }

    @Nullable
    public String getXResolutionDescription() {
        Rational rational = this._directory.getRational(282);
        if (rational == null) {
            return null;
        }
        String resolutionDescription = getResolutionDescription();
        Object[] objArr = new Object[2];
        objArr[0] = rational.toSimpleString(true);
        objArr[1] = resolutionDescription == null ? "unit" : resolutionDescription.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }

    @Nullable
    public String getYCbCrPositioningDescription() {
        return getIndexedDescription(531, 1, "Center of pixel array", "Datum point");
    }

    @Nullable
    public String getYCbCrSubsamplingDescription() {
        int[] intArray = this._directory.getIntArray(530);
        if (intArray == null || intArray.length < 2) {
            return null;
        }
        return (intArray[0] == 2 && intArray[1] == 1) ? "YCbCr4:2:2" : (intArray[0] == 2 && intArray[1] == 2) ? "YCbCr4:2:0" : "(Unknown)";
    }

    @Nullable
    public String getYResolutionDescription() {
        Rational rational = this._directory.getRational(283);
        if (rational == null) {
            return null;
        }
        String resolutionDescription = getResolutionDescription();
        Object[] objArr = new Object[2];
        objArr[0] = rational.toSimpleString(true);
        objArr[1] = resolutionDescription == null ? "unit" : resolutionDescription.toLowerCase();
        return String.format("%s dots per %s", objArr);
    }
}
