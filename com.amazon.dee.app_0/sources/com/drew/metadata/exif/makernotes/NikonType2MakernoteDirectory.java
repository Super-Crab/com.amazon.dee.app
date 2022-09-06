package com.drew.metadata.exif.makernotes;

import androidx.exifinterface.media.ExifInterface;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.facebook.imageutils.JfifUtil;
import java.util.HashMap;
import org.apache.commons.net.telnet.TelnetCommand;
/* loaded from: classes2.dex */
public class NikonType2MakernoteDirectory extends Directory {
    public static final int TAG_ACTIVE_D_LIGHTING = 34;
    public static final int TAG_ADAPTER = 130;
    public static final int TAG_AE_BRACKET_COMPENSATION = 25;
    public static final int TAG_AF_FOCUS_POSITION = 136;
    public static final int TAG_AF_INFO_2 = 183;
    public static final int TAG_AF_RESPONSE = 173;
    public static final int TAG_AF_TUNE = 185;
    public static final int TAG_AF_TYPE = 7;
    public static final int TAG_AUTO_FLASH_COMPENSATION = 18;
    public static final int TAG_AUTO_FLASH_MODE = 9;
    public static final int TAG_CAMERA_COLOR_MODE = 141;
    public static final int TAG_CAMERA_HUE_ADJUSTMENT = 146;
    public static final int TAG_CAMERA_SERIAL_NUMBER = 29;
    public static final int TAG_CAMERA_SERIAL_NUMBER_2 = 160;
    public static final int TAG_CAMERA_SHARPENING = 6;
    public static final int TAG_CAMERA_TONE_COMPENSATION = 129;
    public static final int TAG_CAMERA_WHITE_BALANCE = 5;
    public static final int TAG_CAMERA_WHITE_BALANCE_FINE = 11;
    public static final int TAG_CAMERA_WHITE_BALANCE_RB_COEFF = 12;
    public static final int TAG_COLOR_BALANCE = 151;
    public static final int TAG_COLOR_MODE = 3;
    public static final int TAG_COLOR_SPACE = 30;
    public static final int TAG_CONTRAST_CURVE = 140;
    public static final int TAG_CROP_HIGH_SPEED = 27;
    public static final int TAG_DATA_DUMP = 16;
    public static final int TAG_DELETED_IMAGE_COUNT = 166;
    public static final int TAG_DIGITAL_VARI_PROGRAM = 171;
    public static final int TAG_DIGITAL_ZOOM = 134;
    public static final int TAG_EXPOSURE_DIFFERENCE = 14;
    public static final int TAG_EXPOSURE_SEQUENCE_NUMBER = 167;
    public static final int TAG_EXPOSURE_TUNING = 28;
    public static final int TAG_FILE_INFO = 184;
    public static final int TAG_FIRMWARE_VERSION = 1;
    public static final int TAG_FLASH_BRACKET_COMPENSATION = 24;
    public static final int TAG_FLASH_EXPOSURE_COMPENSATION = 23;
    public static final int TAG_FLASH_INFO = 168;
    public static final int TAG_FLASH_MODE = 26;
    public static final int TAG_FLASH_SYNC_MODE = 8;
    public static final int TAG_FLASH_USED = 135;
    public static final int TAG_HIGH_ISO_NOISE_REDUCTION = 177;
    public static final int TAG_IMAGE_ADJUSTMENT = 128;
    public static final int TAG_IMAGE_AUTHENTICATION = 32;
    public static final int TAG_IMAGE_BOUNDARY = 22;
    public static final int TAG_IMAGE_COUNT = 165;
    public static final int TAG_IMAGE_DATA_SIZE = 162;
    public static final int TAG_IMAGE_OPTIMISATION = 169;
    public static final int TAG_IMAGE_STABILISATION = 172;
    public static final int TAG_ISO_1 = 2;
    public static final int TAG_ISO_INFO = 37;
    public static final int TAG_ISO_MODE = 15;
    public static final int TAG_ISO_REQUESTED = 19;
    public static final int TAG_LENS = 132;
    public static final int TAG_LENS_DATA = 152;
    public static final int TAG_LENS_STOPS = 139;
    public static final int TAG_LENS_TYPE = 131;
    public static final int TAG_LIGHT_SOURCE = 144;
    public static final int TAG_LINEARIZATION_TABLE = 150;
    public static final int TAG_MANUAL_FOCUS_DISTANCE = 133;
    public static final int TAG_MULTI_EXPOSURE = 176;
    public static final int TAG_NEF_BIT_DEPTH = 3618;
    public static final int TAG_NEF_COMPRESSION = 147;
    public static final int TAG_NEF_THUMBNAIL_SIZE = 153;
    public static final int TAG_NIKON_CAPTURE_DATA = 3585;
    public static final int TAG_NIKON_CAPTURE_OFFSETS = 3598;
    public static final int TAG_NIKON_CAPTURE_VERSION = 3593;
    public static final int TAG_NIKON_SCAN = 3600;
    public static final int TAG_NOISE_REDUCTION = 149;
    public static final int TAG_PICTURE_CONTROL = 35;
    public static final int TAG_POWER_UP_TIME = 182;
    public static final int TAG_PREVIEW_IFD = 17;
    public static final int TAG_PRINT_IMAGE_MATCHING_INFO = 3584;
    public static final int TAG_PROGRAM_SHIFT = 13;
    public static final int TAG_QUALITY_AND_FILE_FORMAT = 4;
    public static final int TAG_RETOUCH_HISTORY = 158;
    public static final int TAG_SATURATION = 148;
    public static final int TAG_SATURATION_2 = 170;
    public static final int TAG_SCENE_ASSIST = 156;
    public static final int TAG_SCENE_MODE = 143;
    public static final int TAG_SENSOR_PIXEL_SIZE = 154;
    public static final int TAG_SHOOTING_MODE = 137;
    public static final int TAG_SHOT_INFO = 145;
    public static final int TAG_UNKNOWN_10 = 155;
    public static final int TAG_UNKNOWN_11 = 157;
    public static final int TAG_UNKNOWN_12 = 159;
    public static final int TAG_UNKNOWN_20 = 138;
    public static final int TAG_UNKNOWN_27 = 163;
    public static final int TAG_UNKNOWN_28 = 164;
    public static final int TAG_UNKNOWN_29 = 174;
    public static final int TAG_UNKNOWN_30 = 175;
    public static final int TAG_UNKNOWN_31 = 178;
    public static final int TAG_UNKNOWN_32 = 179;
    public static final int TAG_UNKNOWN_33 = 180;
    public static final int TAG_UNKNOWN_34 = 10;
    public static final int TAG_UNKNOWN_35 = 33;
    public static final int TAG_UNKNOWN_36 = 38;
    public static final int TAG_UNKNOWN_37 = 39;
    public static final int TAG_UNKNOWN_38 = 40;
    public static final int TAG_UNKNOWN_39 = 41;
    public static final int TAG_UNKNOWN_40 = 43;
    public static final int TAG_UNKNOWN_41 = 44;
    public static final int TAG_UNKNOWN_42 = 45;
    public static final int TAG_UNKNOWN_43 = 46;
    public static final int TAG_UNKNOWN_44 = 47;
    public static final int TAG_UNKNOWN_45 = 48;
    public static final int TAG_UNKNOWN_46 = 49;
    public static final int TAG_UNKNOWN_47 = 142;
    public static final int TAG_UNKNOWN_48 = 181;
    public static final int TAG_UNKNOWN_49 = 187;
    public static final int TAG_UNKNOWN_50 = 189;
    public static final int TAG_UNKNOWN_51 = 259;
    public static final int TAG_UNKNOWN_52 = 3589;
    public static final int TAG_UNKNOWN_53 = 3592;
    public static final int TAG_UNKNOWN_54 = 3609;
    public static final int TAG_UNKNOWN_55 = 3619;
    public static final int TAG_VIGNETTE_CONTROL = 42;
    public static final int TAG_VR_INFO = 31;
    public static final int TAG_WORLD_TIME = 36;
    private static final int[] _decTable1;
    private static final int[] _decTable2;
    @NotNull
    protected static final HashMap<Integer, String> _tagNameMap = new HashMap<>();

    static {
        _tagNameMap.put(1, "Firmware Version");
        _tagNameMap.put(2, ExifInterface.TAG_RW2_ISO);
        _tagNameMap.put(4, "Quality & File Format");
        _tagNameMap.put(5, "White Balance");
        _tagNameMap.put(6, "Sharpening");
        _tagNameMap.put(7, "AF Type");
        _tagNameMap.put(11, "White Balance Fine");
        _tagNameMap.put(12, "White Balance RB Coefficients");
        _tagNameMap.put(19, ExifInterface.TAG_RW2_ISO);
        _tagNameMap.put(15, "ISO Mode");
        _tagNameMap.put(16, "Data Dump");
        _tagNameMap.put(13, "Program Shift");
        _tagNameMap.put(14, "Exposure Difference");
        _tagNameMap.put(17, "Preview IFD");
        _tagNameMap.put(131, "Lens Type");
        _tagNameMap.put(135, "Flash Used");
        _tagNameMap.put(136, "AF Focus Position");
        _tagNameMap.put(137, "Shooting Mode");
        _tagNameMap.put(139, "Lens Stops");
        _tagNameMap.put(140, "Contrast Curve");
        _tagNameMap.put(144, "Light source");
        _tagNameMap.put(145, "Shot Info");
        _tagNameMap.put(151, "Color Balance");
        _tagNameMap.put(152, "Lens Data");
        _tagNameMap.put(153, "NEF Thumbnail Size");
        _tagNameMap.put(154, "Sensor Pixel Size");
        _tagNameMap.put(155, "Unknown 10");
        _tagNameMap.put(156, "Scene Assist");
        _tagNameMap.put(157, "Unknown 11");
        _tagNameMap.put(158, "Retouch History");
        _tagNameMap.put(159, "Unknown 12");
        _tagNameMap.put(8, "Flash Sync Mode");
        _tagNameMap.put(9, "Auto Flash Mode");
        _tagNameMap.put(18, "Auto Flash Compensation");
        _tagNameMap.put(167, "Exposure Sequence Number");
        _tagNameMap.put(3, "Color Mode");
        _tagNameMap.put(138, "Unknown 20");
        _tagNameMap.put(22, "Image Boundary");
        _tagNameMap.put(23, "Flash Exposure Compensation");
        _tagNameMap.put(24, "Flash Bracket Compensation");
        _tagNameMap.put(25, "AE Bracket Compensation");
        _tagNameMap.put(26, "Flash Mode");
        _tagNameMap.put(27, "Crop High Speed");
        _tagNameMap.put(28, "Exposure Tuning");
        _tagNameMap.put(29, "Camera Serial Number");
        _tagNameMap.put(30, "Color Space");
        _tagNameMap.put(31, "VR Info");
        _tagNameMap.put(32, "Image Authentication");
        _tagNameMap.put(33, "Unknown 35");
        _tagNameMap.put(34, "Active D-Lighting");
        _tagNameMap.put(35, "Picture Control");
        _tagNameMap.put(36, "World Time");
        _tagNameMap.put(37, "ISO Info");
        _tagNameMap.put(38, "Unknown 36");
        _tagNameMap.put(39, "Unknown 37");
        _tagNameMap.put(40, "Unknown 38");
        _tagNameMap.put(41, "Unknown 39");
        _tagNameMap.put(42, "Vignette Control");
        _tagNameMap.put(43, "Unknown 40");
        _tagNameMap.put(44, "Unknown 41");
        _tagNameMap.put(45, "Unknown 42");
        _tagNameMap.put(46, "Unknown 43");
        _tagNameMap.put(47, "Unknown 44");
        _tagNameMap.put(48, "Unknown 45");
        _tagNameMap.put(49, "Unknown 46");
        _tagNameMap.put(142, "Unknown 47");
        _tagNameMap.put(143, "Scene Mode");
        _tagNameMap.put(160, "Camera Serial Number");
        _tagNameMap.put(162, "Image Data Size");
        _tagNameMap.put(163, "Unknown 27");
        _tagNameMap.put(164, "Unknown 28");
        _tagNameMap.put(165, "Image Count");
        _tagNameMap.put(166, "Deleted Image Count");
        _tagNameMap.put(170, ExifInterface.TAG_SATURATION);
        _tagNameMap.put(171, "Digital Vari Program");
        _tagNameMap.put(172, "Image Stabilisation");
        _tagNameMap.put(173, "AF Response");
        _tagNameMap.put(174, "Unknown 29");
        _tagNameMap.put(175, "Unknown 30");
        _tagNameMap.put(176, "Multi Exposure");
        _tagNameMap.put(177, "High ISO Noise Reduction");
        _tagNameMap.put(178, "Unknown 31");
        _tagNameMap.put(179, "Unknown 32");
        _tagNameMap.put(180, "Unknown 33");
        _tagNameMap.put(181, "Unknown 48");
        _tagNameMap.put(182, "Power Up Time");
        _tagNameMap.put(183, "AF Info 2");
        _tagNameMap.put(184, "File Info");
        _tagNameMap.put(185, "AF Tune");
        _tagNameMap.put(168, "Flash Info");
        _tagNameMap.put(169, "Image Optimisation");
        _tagNameMap.put(128, "Image Adjustment");
        _tagNameMap.put(129, "Tone Compensation");
        _tagNameMap.put(130, "Adapter");
        _tagNameMap.put(132, "Lens");
        _tagNameMap.put(133, "Manual Focus Distance");
        _tagNameMap.put(134, "Digital Zoom");
        _tagNameMap.put(141, "Colour Mode");
        _tagNameMap.put(146, "Camera Hue Adjustment");
        _tagNameMap.put(147, "NEF Compression");
        _tagNameMap.put(148, ExifInterface.TAG_SATURATION);
        _tagNameMap.put(149, "Noise Reduction");
        _tagNameMap.put(150, "Linearization Table");
        _tagNameMap.put(Integer.valueOf((int) TAG_NIKON_CAPTURE_DATA), "Nikon Capture Data");
        _tagNameMap.put(187, "Unknown 49");
        _tagNameMap.put(189, "Unknown 50");
        _tagNameMap.put(259, "Unknown 51");
        _tagNameMap.put(3584, "Print IM");
        _tagNameMap.put(Integer.valueOf((int) TAG_UNKNOWN_52), "Unknown 52");
        _tagNameMap.put(Integer.valueOf((int) TAG_UNKNOWN_53), "Unknown 53");
        _tagNameMap.put(Integer.valueOf((int) TAG_NIKON_CAPTURE_VERSION), "Nikon Capture Version");
        _tagNameMap.put(Integer.valueOf((int) TAG_NIKON_CAPTURE_OFFSETS), "Nikon Capture Offsets");
        _tagNameMap.put(3600, "Nikon Scan");
        _tagNameMap.put(Integer.valueOf((int) TAG_UNKNOWN_54), "Unknown 54");
        _tagNameMap.put(Integer.valueOf((int) TAG_NEF_BIT_DEPTH), "NEF Bit Depth");
        _tagNameMap.put(Integer.valueOf((int) TAG_UNKNOWN_55), "Unknown 55");
        _decTable1 = new int[]{193, 191, 109, 13, 89, 197, 19, 157, 131, 97, 107, 79, 199, 127, 61, 61, 83, 89, 227, 199, 233, 47, 149, 167, 149, 31, 223, 127, 43, 41, 199, 13, 223, 7, TelnetCommand.EOR, 113, 137, 61, 19, 61, 59, 19, 251, 13, 137, 193, 101, 31, 179, 13, 107, 41, 227, 251, TelnetCommand.EOR, 163, 107, 71, 127, 149, 53, 167, 71, 79, 199, TelnetCommand.NOP, 89, 149, 53, 17, 41, 97, TelnetCommand.NOP, 61, 179, 43, 13, 67, 137, 193, 157, 157, 137, 101, TelnetCommand.NOP, 233, 223, 191, 61, 127, 83, 151, 229, 233, 149, 23, 29, 61, 139, 251, 199, 227, 103, 167, 7, TelnetCommand.NOP, 113, 167, 83, 181, 41, 137, 229, 43, 167, 23, 41, 233, 79, 197, 101, 109, 107, TelnetCommand.EOR, 13, 137, 73, 47, 179, 67, 83, 101, 29, 73, 163, 19, 137, 89, TelnetCommand.EOR, 107, TelnetCommand.EOR, 101, 29, 11, 89, 19, 227, 79, 157, 179, 41, 67, 43, 7, 29, 149, 89, 89, 71, 251, 229, 233, 97, 71, 47, 53, 127, 23, 127, TelnetCommand.EOR, 127, 149, 149, 113, 211, 163, 11, 113, 163, 173, 11, 59, 181, 251, 163, 191, 79, 131, 29, 173, 233, 47, 113, 101, 163, 229, 7, 53, 61, 13, 181, 233, 229, 71, 59, 157, TelnetCommand.EOR, 53, 163, 191, 179, 223, 83, 211, 151, 83, 73, 113, 7, 53, 97, 113, 47, 67, 47, 17, 223, 23, 151, 251, 149, 59, 127, 107, 211, 37, 191, 173, 199, 197, 197, 181, 139, TelnetCommand.EOR, 47, 211, 7, 107, 37, 73, 149, 37, 73, 109, 113, 199};
        _decTable2 = new int[]{167, 188, 201, 173, 145, 223, 133, 229, 212, 120, 213, 23, 70, 124, 41, 76, 77, 3, 233, 37, 104, 17, 134, 179, 189, TelnetCommand.EC, 111, 97, 34, 162, 38, 52, 42, 190, 30, 70, 20, 104, 157, 68, 24, 194, 64, TelnetCommand.IP, 126, 95, 27, 173, 11, 148, 182, 103, 180, 11, 225, 234, 149, 156, 102, 220, 231, 93, 108, 5, JfifUtil.MARKER_SOS, 213, 223, 122, TelnetCommand.EOR, TelnetCommand.AYT, 219, 31, 130, 76, 192, 104, 71, 161, 189, TelnetCommand.ABORT, 57, 80, 86, 74, 221, 223, 165, TelnetCommand.EL, Opcodes.IFNULL, JfifUtil.MARKER_SOS, 202, 144, 202, 1, 66, 157, 139, 12, 115, 67, 117, 5, 148, 222, 36, 179, 128, 52, 229, 44, 220, 155, 63, 202, 51, 69, 208, 219, 95, TelnetCommand.AO, 82, 195, 33, JfifUtil.MARKER_SOS, 226, 34, 114, 107, 62, 208, 91, 168, 135, 140, 6, 93, 15, 221, 9, 25, 147, 208, 185, TelnetCommand.WONT, 139, 15, 132, 96, 51, 28, 155, 69, TelnetCommand.NOP, 240, 163, 148, 58, 18, 119, 51, 77, 68, 120, 40, 60, 158, TelnetCommand.DO, 101, 87, 22, 148, 107, 251, 89, 208, 200, 34, 54, 219, 210, 99, 152, 67, 161, 4, 135, 134, TelnetCommand.EC, 166, 38, 187, 214, 89, 77, 191, 106, 46, 170, 43, TelnetCommand.EOR, 230, 120, 182, 78, 224, 47, 220, 124, 190, 87, 25, 50, 126, 42, 208, 184, 186, 41, 0, 60, 82, 125, 168, 73, 59, 45, 235, 37, 73, 250, 163, 170, 57, 167, 197, 167, 80, 17, 54, 251, Opcodes.IFNULL, 103, 74, TelnetCommand.AO, 165, 18, 101, 126, 176, 223, 175, 78, 179, 97, 127, 47};
    }

    public NikonType2MakernoteDirectory() {
        setDescriptor(new NikonType2MakernoteDescriptor(this));
    }

    @Nullable
    public int[] getDecryptedIntArray(int i) {
        int i2;
        int[] intArray = getIntArray(i);
        Integer integer = getInteger(29);
        Integer integer2 = getInteger(167);
        if (intArray == null || integer == null || integer2 == null) {
            return null;
        }
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= 4) {
                break;
            }
            i4 ^= (integer2.intValue() >> (i3 * 8)) & 255;
            i3++;
        }
        int i5 = _decTable1[integer.intValue() & 255];
        int i6 = _decTable2[i4];
        int i7 = 96;
        for (i2 = 4; i2 < intArray.length; i2++) {
            i6 = ((i5 * i7) + i6) & 255;
            i7 = (i7 + 1) & 255;
            intArray[i2] = intArray[i2] ^ i6;
        }
        return intArray;
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    public String getName() {
        return "Nikon Makernote";
    }

    @Override // com.drew.metadata.Directory
    @NotNull
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }
}
