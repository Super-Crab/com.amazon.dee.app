package com.drew.metadata.exif.makernotes;

import amazon.communication.connection.Channels;
import amazon.speech.simclient.capability.CapabilityQueryConstants;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.mobilytics.configuration.DCMEndpoint;
import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import com.drew.metadata.exif.makernotes.CanonMakernoteDirectory;
import com.esotericsoftware.reflectasm.shaded.org.objectweb.asm.Opcodes;
import com.facebook.imageutils.JfifUtil;
import com.here.sdk.search.PlaceCategory;
import java.text.DecimalFormat;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.net.telnet.TelnetCommand;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes2.dex */
public class CanonMakernoteDescriptor extends TagDescriptor<CanonMakernoteDirectory> {
    private static final HashMap<Integer, String> _lensTypeById = new HashMap<>();

    static {
        _lensTypeById.put(1, "Canon EF 50mm f/1.8");
        _lensTypeById.put(2, "Canon EF 28mm f/2.8");
        _lensTypeById.put(3, "Canon EF 135mm f/2.8 Soft");
        _lensTypeById.put(4, "Canon EF 35-105mm f/3.5-4.5 or Sigma Lens");
        _lensTypeById.put(5, "Canon EF 35-70mm f/3.5-4.5");
        _lensTypeById.put(6, "Canon EF 28-70mm f/3.5-4.5 or Sigma or Tokina Lens");
        _lensTypeById.put(7, "Canon EF 100-300mm f/5.6L");
        _lensTypeById.put(8, "Canon EF 100-300mm f/5.6 or Sigma or Tokina Lens");
        _lensTypeById.put(9, "Canon EF 70-210mm f/4");
        _lensTypeById.put(10, "Canon EF 50mm f/2.5 Macro or Sigma Lens");
        _lensTypeById.put(11, "Canon EF 35mm f/2");
        _lensTypeById.put(13, "Canon EF 15mm f/2.8 Fisheye");
        _lensTypeById.put(14, "Canon EF 50-200mm f/3.5-4.5L");
        _lensTypeById.put(15, "Canon EF 50-200mm f/3.5-4.5");
        _lensTypeById.put(16, "Canon EF 35-135mm f/3.5-4.5");
        _lensTypeById.put(17, "Canon EF 35-70mm f/3.5-4.5A");
        _lensTypeById.put(18, "Canon EF 28-70mm f/3.5-4.5");
        _lensTypeById.put(20, "Canon EF 100-200mm f/4.5A");
        _lensTypeById.put(21, "Canon EF 80-200mm f/2.8L");
        _lensTypeById.put(22, "Canon EF 20-35mm f/2.8L or Tokina Lens");
        _lensTypeById.put(23, "Canon EF 35-105mm f/3.5-4.5");
        _lensTypeById.put(24, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        _lensTypeById.put(25, "Canon EF 35-80mm f/4-5.6 Power Zoom");
        _lensTypeById.put(26, "Canon EF 100mm f/2.8 Macro or Other Lens");
        _lensTypeById.put(27, "Canon EF 35-80mm f/4-5.6");
        _lensTypeById.put(28, "Canon EF 80-200mm f/4.5-5.6 or Tamron Lens");
        _lensTypeById.put(29, "Canon EF 50mm f/1.8 II");
        _lensTypeById.put(30, "Canon EF 35-105mm f/4.5-5.6");
        _lensTypeById.put(31, "Canon EF 75-300mm f/4-5.6 or Tamron Lens");
        _lensTypeById.put(32, "Canon EF 24mm f/2.8 or Sigma Lens");
        _lensTypeById.put(33, "Voigtlander or Carl Zeiss Lens");
        _lensTypeById.put(35, "Canon EF 35-80mm f/4-5.6");
        _lensTypeById.put(36, "Canon EF 38-76mm f/4.5-5.6");
        _lensTypeById.put(37, "Canon EF 35-80mm f/4-5.6 or Tamron Lens");
        _lensTypeById.put(38, "Canon EF 80-200mm f/4.5-5.6");
        _lensTypeById.put(39, "Canon EF 75-300mm f/4-5.6");
        _lensTypeById.put(40, "Canon EF 28-80mm f/3.5-5.6");
        _lensTypeById.put(41, "Canon EF 28-90mm f/4-5.6");
        _lensTypeById.put(42, "Canon EF 28-200mm f/3.5-5.6 or Tamron Lens");
        _lensTypeById.put(43, "Canon EF 28-105mm f/4-5.6");
        _lensTypeById.put(44, "Canon EF 90-300mm f/4.5-5.6");
        _lensTypeById.put(45, "Canon EF-S 18-55mm f/3.5-5.6 [II]");
        _lensTypeById.put(46, "Canon EF 28-90mm f/4-5.6");
        _lensTypeById.put(47, "Zeiss Milvus 35mm f/2 or 50mm f/2");
        _lensTypeById.put(48, "Canon EF-S 18-55mm f/3.5-5.6 IS");
        _lensTypeById.put(49, "Canon EF-S 55-250mm f/4-5.6 IS");
        _lensTypeById.put(50, "Canon EF-S 18-200mm f/3.5-5.6 IS");
        _lensTypeById.put(51, "Canon EF-S 18-135mm f/3.5-5.6 IS");
        _lensTypeById.put(52, "Canon EF-S 18-55mm f/3.5-5.6 IS II");
        _lensTypeById.put(53, "Canon EF-S 18-55mm f/3.5-5.6 III");
        _lensTypeById.put(54, "Canon EF-S 55-250mm f/4-5.6 IS II");
        _lensTypeById.put(94, "Canon TS-E 17mm f/4L");
        _lensTypeById.put(95, "Canon TS-E 24.0mm f/3.5 L II");
        _lensTypeById.put(124, "Canon MP-E 65mm f/2.8 1-5x Macro Photo");
        _lensTypeById.put(125, "Canon TS-E 24mm f/3.5L");
        _lensTypeById.put(126, "Canon TS-E 45mm f/2.8");
        _lensTypeById.put(127, "Canon TS-E 90mm f/2.8");
        _lensTypeById.put(129, "Canon EF 300mm f/2.8L");
        _lensTypeById.put(130, "Canon EF 50mm f/1.0L");
        _lensTypeById.put(131, "Canon EF 28-80mm f/2.8-4L or Sigma Lens");
        _lensTypeById.put(132, "Canon EF 1200mm f/5.6L");
        _lensTypeById.put(134, "Canon EF 600mm f/4L IS");
        _lensTypeById.put(135, "Canon EF 200mm f/1.8L");
        _lensTypeById.put(136, "Canon EF 300mm f/2.8L");
        _lensTypeById.put(137, "Canon EF 85mm f/1.2L or Sigma or Tamron Lens");
        _lensTypeById.put(138, "Canon EF 28-80mm f/2.8-4L");
        _lensTypeById.put(139, "Canon EF 400mm f/2.8L");
        _lensTypeById.put(140, "Canon EF 500mm f/4.5L");
        _lensTypeById.put(141, "Canon EF 500mm f/4.5L");
        _lensTypeById.put(142, "Canon EF 300mm f/2.8L IS");
        _lensTypeById.put(143, "Canon EF 500mm f/4L IS or Sigma Lens");
        _lensTypeById.put(144, "Canon EF 35-135mm f/4-5.6 USM");
        _lensTypeById.put(145, "Canon EF 100-300mm f/4.5-5.6 USM");
        _lensTypeById.put(146, "Canon EF 70-210mm f/3.5-4.5 USM");
        _lensTypeById.put(147, "Canon EF 35-135mm f/4-5.6 USM");
        _lensTypeById.put(148, "Canon EF 28-80mm f/3.5-5.6 USM");
        _lensTypeById.put(149, "Canon EF 100mm f/2 USM");
        _lensTypeById.put(150, "Canon EF 14mm f/2.8L or Sigma Lens");
        _lensTypeById.put(151, "Canon EF 200mm f/2.8L");
        _lensTypeById.put(152, "Canon EF 300mm f/4L IS or Sigma Lens");
        _lensTypeById.put(153, "Canon EF 35-350mm f/3.5-5.6L or Sigma or Tamron Lens");
        _lensTypeById.put(154, "Canon EF 20mm f/2.8 USM or Zeiss Lens");
        _lensTypeById.put(155, "Canon EF 85mm f/1.8 USM");
        _lensTypeById.put(156, "Canon EF 28-105mm f/3.5-4.5 USM or Tamron Lens");
        _lensTypeById.put(160, "Canon EF 20-35mm f/3.5-4.5 USM or Tamron or Tokina Lens");
        _lensTypeById.put(161, "Canon EF 28-70mm f/2.8L or Sigma or Tamron Lens");
        _lensTypeById.put(162, "Canon EF 200mm f/2.8L");
        _lensTypeById.put(163, "Canon EF 300mm f/4L");
        _lensTypeById.put(164, "Canon EF 400mm f/5.6L");
        _lensTypeById.put(165, "Canon EF 70-200mm f/2.8 L");
        _lensTypeById.put(166, "Canon EF 70-200mm f/2.8 L + 1.4x");
        _lensTypeById.put(167, "Canon EF 70-200mm f/2.8 L + 2x");
        _lensTypeById.put(168, "Canon EF 28mm f/1.8 USM or Sigma Lens");
        _lensTypeById.put(169, "Canon EF 17-35mm f/2.8L or Sigma Lens");
        _lensTypeById.put(170, "Canon EF 200mm f/2.8L II");
        _lensTypeById.put(171, "Canon EF 300mm f/4L");
        _lensTypeById.put(172, "Canon EF 400mm f/5.6L or Sigma Lens");
        _lensTypeById.put(173, "Canon EF 180mm Macro f/3.5L or Sigma Lens");
        _lensTypeById.put(174, "Canon EF 135mm f/2L or Other Lens");
        _lensTypeById.put(175, "Canon EF 400mm f/2.8L");
        _lensTypeById.put(176, "Canon EF 24-85mm f/3.5-4.5 USM");
        _lensTypeById.put(177, "Canon EF 300mm f/4L IS");
        _lensTypeById.put(178, "Canon EF 28-135mm f/3.5-5.6 IS");
        _lensTypeById.put(179, "Canon EF 24mm f/1.4L");
        _lensTypeById.put(180, "Canon EF 35mm f/1.4L or Other Lens");
        _lensTypeById.put(181, "Canon EF 100-400mm f/4.5-5.6L IS + 1.4x or Sigma Lens");
        _lensTypeById.put(182, "Canon EF 100-400mm f/4.5-5.6L IS + 2x or Sigma Lens");
        _lensTypeById.put(183, "Canon EF 100-400mm f/4.5-5.6L IS or Sigma Lens");
        _lensTypeById.put(184, "Canon EF 400mm f/2.8L + 2x");
        _lensTypeById.put(185, "Canon EF 600mm f/4L IS");
        _lensTypeById.put(186, "Canon EF 70-200mm f/4L");
        _lensTypeById.put(187, "Canon EF 70-200mm f/4L + 1.4x");
        _lensTypeById.put(188, "Canon EF 70-200mm f/4L + 2x");
        _lensTypeById.put(189, "Canon EF 70-200mm f/4L + 2.8x");
        _lensTypeById.put(190, "Canon EF 100mm f/2.8 Macro USM");
        _lensTypeById.put(191, "Canon EF 400mm f/4 DO IS");
        _lensTypeById.put(193, "Canon EF 35-80mm f/4-5.6 USM");
        _lensTypeById.put(194, "Canon EF 80-200mm f/4.5-5.6 USM");
        _lensTypeById.put(195, "Canon EF 35-105mm f/4.5-5.6 USM");
        _lensTypeById.put(Integer.valueOf((int) CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256), "Canon EF 75-300mm f/4-5.6 USM");
        _lensTypeById.put(197, "Canon EF 75-300mm f/4-5.6 IS USM");
        _lensTypeById.put(Integer.valueOf((int) Opcodes.IFNULL), "Canon EF 50mm f/1.4 USM or Zeiss Lens");
        _lensTypeById.put(199, "Canon EF 28-80mm f/3.5-5.6 USM");
        _lensTypeById.put(200, "Canon EF 75-300mm f/4-5.6 USM");
        _lensTypeById.put(201, "Canon EF 28-80mm f/3.5-5.6 USM");
        _lensTypeById.put(202, "Canon EF 28-80mm f/3.5-5.6 USM IV");
        _lensTypeById.put(208, "Canon EF 22-55mm f/4-5.6 USM");
        _lensTypeById.put(209, "Canon EF 55-200mm f/4.5-5.6");
        _lensTypeById.put(210, "Canon EF 28-90mm f/4-5.6 USM");
        _lensTypeById.put(211, "Canon EF 28-200mm f/3.5-5.6 USM");
        _lensTypeById.put(212, "Canon EF 28-105mm f/4-5.6 USM");
        _lensTypeById.put(213, "Canon EF 90-300mm f/4.5-5.6 USM or Tamron Lens");
        _lensTypeById.put(214, "Canon EF-S 18-55mm f/3.5-5.6 USM");
        _lensTypeById.put(215, "Canon EF 55-200mm f/4.5-5.6 II USM");
        _lensTypeById.put(Integer.valueOf((int) JfifUtil.MARKER_EOI), "Tamron AF 18-270mm f/3.5-6.3 Di II VC PZD");
        _lensTypeById.put(224, "Canon EF 70-200mm f/2.8L IS");
        _lensTypeById.put(225, "Canon EF 70-200mm f/2.8L IS + 1.4x");
        _lensTypeById.put(226, "Canon EF 70-200mm f/2.8L IS + 2x");
        _lensTypeById.put(227, "Canon EF 70-200mm f/2.8L IS + 2.8x");
        _lensTypeById.put(228, "Canon EF 28-105mm f/3.5-4.5 USM");
        _lensTypeById.put(229, "Canon EF 16-35mm f/2.8L");
        _lensTypeById.put(230, "Canon EF 24-70mm f/2.8L");
        _lensTypeById.put(231, "Canon EF 17-40mm f/4L");
        _lensTypeById.put(232, "Canon EF 70-300mm f/4.5-5.6 DO IS USM");
        _lensTypeById.put(233, "Canon EF 28-300mm f/3.5-5.6L IS");
        _lensTypeById.put(234, "Canon EF-S 17-85mm f/4-5.6 IS USM or Tokina Lens");
        _lensTypeById.put(235, "Canon EF-S 10-22mm f/3.5-4.5 USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.EOF), "Canon EF-S 60mm f/2.8 Macro USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.SUSP), "Canon EF 24-105mm f/4L IS");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.ABORT), "Canon EF 70-300mm f/4-5.6 IS USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.EOR), "Canon EF 85mm f/1.2L II");
        _lensTypeById.put(240, "Canon EF-S 17-55mm f/2.8 IS USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.NOP), "Canon EF 50mm f/1.2L");
        _lensTypeById.put(242, "Canon EF 70-200mm f/4L IS");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.BREAK), "Canon EF 70-200mm f/4L IS + 1.4x");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.IP), "Canon EF 70-200mm f/4L IS + 2x");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.AO), "Canon EF 70-200mm f/4L IS + 2.8x");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.AYT), "Canon EF 16-35mm f/2.8L II");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.EC), "Canon EF 14mm f/2.8L II USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.EL), "Canon EF 200mm f/2L IS or Sigma Lens");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.GA), "Canon EF 800mm f/5.6L IS");
        _lensTypeById.put(250, "Canon EF 24mm f/1.4L II or Sigma Lens");
        _lensTypeById.put(251, "Canon EF 70-200mm f/2.8L IS II USM");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.WONT), "Canon EF 70-200mm f/2.8L IS II USM + 1.4x");
        _lensTypeById.put(Integer.valueOf((int) TelnetCommand.DO), "Canon EF 70-200mm f/2.8L IS II USM + 2x");
        _lensTypeById.put(254, "Canon EF 100mm f/2.8L Macro IS USM");
        _lensTypeById.put(255, "Sigma 24-105mm f/4 DG OS HSM | A or Other Sigma Lens");
        _lensTypeById.put(Integer.valueOf((int) Channels.ODOT_RESP), "Canon EF-S 15-85mm f/3.5-5.6 IS USM");
        _lensTypeById.put(489, "Canon EF 70-300mm f/4-5.6L IS USM");
        _lensTypeById.put(490, "Canon EF 8-15mm f/4L Fisheye USM");
        _lensTypeById.put(491, "Canon EF 300mm f/2.8L IS II USM");
        _lensTypeById.put(492, "Canon EF 400mm f/2.8L IS II USM");
        _lensTypeById.put(493, "Canon EF 500mm f/4L IS II USM or EF 24-105mm f4L IS USM");
        _lensTypeById.put(494, "Canon EF 600mm f/4.0L IS II USM");
        _lensTypeById.put(495, "Canon EF 24-70mm f/2.8L II USM");
        _lensTypeById.put(496, "Canon EF 200-400mm f/4L IS USM");
        _lensTypeById.put(499, "Canon EF 200-400mm f/4L IS USM + 1.4x");
        _lensTypeById.put(502, "Canon EF 28mm f/2.8 IS USM");
        _lensTypeById.put(503, "Canon EF 24mm f/2.8 IS USM");
        _lensTypeById.put(504, "Canon EF 24-70mm f/4L IS USM");
        _lensTypeById.put(Integer.valueOf((int) HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED), "Canon EF 35mm f/2 IS USM");
        _lensTypeById.put(506, "Canon EF 400mm f/4 DO IS II USM");
        _lensTypeById.put(507, "Canon EF 16-35mm f/4L IS USM");
        _lensTypeById.put(508, "Canon EF 11-24mm f/4L USM");
        _lensTypeById.put(747, "Canon EF 100-400mm f/4.5-5.6L IS II USM");
        _lensTypeById.put(750, "Canon EF 35mm f/1.4L II USM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_OLYMPUS_IMAGE_WIDTH), "Canon EF-S 18-135mm f/3.5-5.6 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_OLYMPUS_IMAGE_HEIGHT), "Canon EF-M 18-55mm f/3.5-5.6 IS STM or Tamron Lens");
        _lensTypeById.put(4144, "Canon EF 40mm f/2.8 STM");
        _lensTypeById.put(4145, "Canon EF-M 22mm f/2 STM");
        _lensTypeById.put(4146, "Canon EF-S 18-55mm f/3.5-5.6 IS STM");
        _lensTypeById.put(4147, "Canon EF-M 11-22mm f/4-5.6 IS STM");
        _lensTypeById.put(4148, "Canon EF-S 55-250mm f/4-5.6 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE_VALID), "Canon EF-M 55-200mm f/4.5-6.3 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_PREVIEW_IMAGE_START), "Canon EF-S 10-18mm f/4.5-5.6 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_AF_RESULT), "Canon EF 24-105mm f/3.5-5.6 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_CCD_SCAN_MODE), "Canon EF-M 15-45mm f/3.5-6.3 IS STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_NOISE_REDUCTION), "Canon EF-S 24mm f/2.8 STM");
        _lensTypeById.put(Integer.valueOf((int) OlympusMakernoteDirectory.TAG_NEAR_LENS_STEP), "Canon EF 50mm f/1.8 STM");
        _lensTypeById.put(36912, "Canon EF-S 18-135mm f/3.5-5.6 IS USM");
        _lensTypeById.put(65535, CapabilityQueryConstants.TARGET_NOT_AVAILABLE);
    }

    public CanonMakernoteDescriptor(@NotNull CanonMakernoteDirectory canonMakernoteDirectory) {
        super(canonMakernoteDirectory);
    }

    private double decodeCanonEv(int i) {
        int i2;
        if (i < 0) {
            i = -i;
            i2 = -1;
        } else {
            i2 = 1;
        }
        int i3 = i & 31;
        int i4 = i - i3;
        if (i3 == 12) {
            i3 = 10;
        } else if (i3 == 20) {
            i3 = 21;
        }
        return ((i4 + i3) * i2) / 32.0d;
    }

    @Nullable
    public String getAESettingDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_AE_SETTING, 0, "Normal AE", "Exposure Compensation", "AE Lock", "AE Lock + Exposure Comp.", "No AE");
    }

    @Nullable
    public String getAfPointSelectedDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_AF_POINT_SELECTED, 12288, "None (MF)", "Auto selected", "Right", "Centre", "Left");
    }

    @Nullable
    public String getAfPointUsedDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.FocalLength.TAG_AF_POINT_USED);
        if (integer == null) {
            return null;
        }
        return (integer.intValue() & 7) == 0 ? "Right" : (integer.intValue() & 7) == 1 ? "Centre" : (integer.intValue() & 7) == 2 ? "Left" : GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
    }

    @Nullable
    public String getColorToneDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_COLOR_TONE);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 32767 ? "n/a" : integer.toString();
    }

    @Nullable
    public String getContinuousDriveModeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_CONTINUOUS_DRIVE_MODE);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue != 0) {
            return intValue != 1 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Continuous";
        }
        Integer integer2 = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY);
        return integer2 != null ? integer2.intValue() == 0 ? "Single shot" : "Single shot with self-timer" : "Continuous";
    }

    @Nullable
    public String getContrastDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_CONTRAST);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 65535 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Low" : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL;
    }

    @Override // com.drew.metadata.TagDescriptor
    @Nullable
    public String getDescription(int i) {
        switch (i) {
            case 12:
                return getSerialNumberDescription();
            case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_1 /* 49415 */:
                return getFocusMode1Description();
            case CanonMakernoteDirectory.CameraSettings.TAG_COLOR_TONE /* 49449 */:
                return getColorToneDescription();
            case CanonMakernoteDirectory.CameraSettings.TAG_SRAW_QUALITY /* 49453 */:
                return getSRawQualityDescription();
            case CanonMakernoteDirectory.FocalLength.TAG_WHITE_BALANCE /* 49671 */:
                return getWhiteBalanceDescription();
            case CanonMakernoteDirectory.AFInfo.TAG_AF_POINTS_IN_FOCUS /* 53770 */:
                return getTagAfPointsInFocus();
            default:
                switch (i) {
                    case CanonMakernoteDirectory.CameraSettings.TAG_MACRO_MODE /* 49409 */:
                        return getMacroModeDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY /* 49410 */:
                        return getSelfTimerDelayDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_QUALITY /* 49411 */:
                        return getQualityDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_MODE /* 49412 */:
                        return getFlashModeDescription();
                    case CanonMakernoteDirectory.CameraSettings.TAG_CONTINUOUS_DRIVE_MODE /* 49413 */:
                        return getContinuousDriveModeDescription();
                    default:
                        switch (i) {
                            case CanonMakernoteDirectory.CameraSettings.TAG_RECORD_MODE /* 49417 */:
                                return getRecordModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE /* 49418 */:
                                return getImageSizeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_EASY_SHOOTING_MODE /* 49419 */:
                                return getEasyShootingModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM /* 49420 */:
                                return getDigitalZoomDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_CONTRAST /* 49421 */:
                                return getContrastDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_SATURATION /* 49422 */:
                                return getSaturationDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_SHARPNESS /* 49423 */:
                                return getSharpnessDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_ISO /* 49424 */:
                                return getIsoDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_METERING_MODE /* 49425 */:
                                return getMeteringModeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_TYPE /* 49426 */:
                                return getFocusTypeDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_AF_POINT_SELECTED /* 49427 */:
                                return getAfPointSelectedDescription();
                            case CanonMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE /* 49428 */:
                                return getExposureModeDescription();
                            default:
                                switch (i) {
                                    case CanonMakernoteDirectory.CameraSettings.TAG_LENS_TYPE /* 49430 */:
                                        return getLensTypeDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_LONG_FOCAL_LENGTH /* 49431 */:
                                        return getLongFocalLengthDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_SHORT_FOCAL_LENGTH /* 49432 */:
                                        return getShortFocalLengthDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCAL_UNITS_PER_MM /* 49433 */:
                                        return getFocalUnitsPerMillimetreDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_MAX_APERTURE /* 49434 */:
                                        return getMaxApertureDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_MIN_APERTURE /* 49435 */:
                                        return getMinApertureDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_ACTIVITY /* 49436 */:
                                        return getFlashActivityDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FLASH_DETAILS /* 49437 */:
                                        return getFlashDetailsDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_CONTINUOUS /* 49438 */:
                                        return getFocusContinuousDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_AE_SETTING /* 49439 */:
                                        return getAESettingDescription();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_2 /* 49440 */:
                                        return getFocusMode2Description();
                                    case CanonMakernoteDirectory.CameraSettings.TAG_DISPLAY_APERTURE /* 49441 */:
                                        return getDisplayApertureDescription();
                                    default:
                                        switch (i) {
                                            case CanonMakernoteDirectory.CameraSettings.TAG_SPOT_METERING_MODE /* 49445 */:
                                                return getSpotMeteringModeDescription();
                                            case CanonMakernoteDirectory.CameraSettings.TAG_PHOTO_EFFECT /* 49446 */:
                                                return getPhotoEffectDescription();
                                            case CanonMakernoteDirectory.CameraSettings.TAG_MANUAL_FLASH_OUTPUT /* 49447 */:
                                                return getManualFlashOutputDescription();
                                            default:
                                                switch (i) {
                                                    case CanonMakernoteDirectory.FocalLength.TAG_AF_POINT_USED /* 49678 */:
                                                        return getAfPointUsedDescription();
                                                    case CanonMakernoteDirectory.FocalLength.TAG_FLASH_BIAS /* 49679 */:
                                                        return getFlashBiasDescription();
                                                    default:
                                                        return super.getDescription(i);
                                                }
                                        }
                                }
                        }
                }
        }
    }

    @Nullable
    public String getDigitalZoomDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_DIGITAL_ZOOM, "No digital zoom", "2x", "4x");
    }

    @Nullable
    public String getDisplayApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_DISPLAY_APERTURE);
        if (integer == null) {
            return null;
        }
        return integer.intValue() == 65535 ? integer.toString() : TagDescriptor.getFStopDescription(integer.intValue() / 10.0f);
    }

    @Nullable
    public String getEasyShootingModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_EASY_SHOOTING_MODE, "Full auto", "Manual", "Landscape", "Fast shutter", "Slow shutter", "Night", "B&W", "Sepia", "Portrait", "Sports", "Macro / Closeup", "Pan focus");
    }

    @Nullable
    public String getExposureModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_EXPOSURE_MODE, "Easy shooting", "Program", "Tv-priority", "Av-priority", "Manual", "A-DEP");
    }

    @Nullable
    public String getFlashActivityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_ACTIVITY, "Flash did not fire", "Flash fired");
    }

    @Nullable
    public String getFlashBiasDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.FocalLength.TAG_FLASH_BIAS);
        if (integer == null) {
            return null;
        }
        boolean z = false;
        if (integer.intValue() > 61440) {
            integer = Integer.valueOf(Integer.valueOf(65535 - integer.intValue()).intValue() + 1);
            z = true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(z ? ProcessIdUtil.DEFAULT_PROCESSID : "");
        sb.append(Float.toString(integer.intValue() / 32.0f));
        sb.append(" EV");
        return sb.toString();
    }

    @Nullable
    public String getFlashDetailsDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_DETAILS);
        if (integer == null) {
            return null;
        }
        return ((integer.intValue() >> 14) & 1) != 0 ? "External E-TTL" : ((integer.intValue() >> 13) & 1) != 0 ? "Internal flash" : ((integer.intValue() >> 11) & 1) != 0 ? "FP sync used" : ((integer.intValue() >> 4) & 1) != 0 ? "FP sync enabled" : GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
    }

    @Nullable
    public String getFlashModeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FLASH_MODE);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 16) {
            return "External flash";
        }
        switch (intValue) {
            case 0:
                return "No flash fired";
            case 1:
                return "Auto";
            case 2:
                return "On";
            case 3:
                return "Red-eye reduction";
            case 4:
                return "Slow-synchro";
            case 5:
                return "Auto and red-eye reduction";
            case 6:
                return "On and red-eye reduction";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getFocalUnitsPerMillimetreDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FOCAL_UNITS_PER_MM);
        if (integer == null) {
            return null;
        }
        return integer.intValue() != 0 ? Integer.toString(integer.intValue()) : "";
    }

    @Nullable
    public String getFocusContinuousDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_CONTINUOUS, 0, "Single", "Continuous", null, null, null, null, null, null, "Manual");
    }

    @Nullable
    public String getFocusMode1Description() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_1, "One-shot", "AI Servo", "AI Focus", "Manual Focus", "Single", "Continuous", "Manual Focus");
    }

    @Nullable
    public String getFocusMode2Description() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_MODE_2, "Single", "Continuous");
    }

    @Nullable
    public String getFocusTypeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_FOCUS_TYPE);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 3 ? intValue != 8 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Locked (Pan Mode)" : "Close-up (Macro)" : "Auto" : "Manual";
    }

    @Nullable
    public String getImageSizeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_IMAGE_SIZE, "Large", "Medium", "Small");
    }

    @Nullable
    public String getIsoDescription() {
        StringBuilder sb;
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_ISO);
        if (integer == null) {
            return null;
        }
        if ((integer.intValue() & 16384) != 0) {
            sb = GeneratedOutlineSupport1.outline107("");
            sb.append(integer.intValue() & (-16385));
        } else {
            int intValue = integer.intValue();
            if (intValue == 0) {
                return "Not specified (see ISOSpeedRatings tag)";
            }
            switch (intValue) {
                case 15:
                    return "Auto";
                case 16:
                    return "50";
                case 17:
                    return PlaceCategory.EAT_AND_DRINK;
                case 18:
                    return "200";
                case 19:
                    return PlaceCategory.TRANSPORT;
                default:
                    sb = new StringBuilder();
                    sb.append("Unknown (");
                    sb.append(integer);
                    sb.append(")");
                    break;
            }
        }
        return sb.toString();
    }

    @Nullable
    public String getLensTypeDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_LENS_TYPE);
        if (integer == null) {
            return null;
        }
        return _lensTypeById.containsKey(integer) ? _lensTypeById.get(integer) : String.format("Unknown (%d)", integer);
    }

    @Nullable
    public String getLongFocalLengthDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_LONG_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        String focalUnitsPerMillimetreDescription = getFocalUnitsPerMillimetreDescription();
        return Integer.toString(integer.intValue()) + " " + focalUnitsPerMillimetreDescription;
    }

    @Nullable
    public String getMacroModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_MACRO_MODE, 1, "Macro", DCMEndpoint.Priority.NORMAL);
    }

    @Nullable
    public String getManualFlashOutputDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MANUAL_FLASH_OUTPUT);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1280 ? intValue != 1282 ? intValue != 1284 ? intValue != 32767 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "n/a" : "Low" : "Medium" : "Full" : "n/a";
    }

    @Nullable
    public String getMaxApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MAX_APERTURE);
        if (integer == null) {
            return null;
        }
        return integer.intValue() > 512 ? String.format("Unknown (%d)", integer) : TagDescriptor.getFStopDescription(Math.exp((Math.log(2.0d) * decodeCanonEv(integer.intValue())) / 2.0d));
    }

    @Nullable
    public String getMeteringModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_METERING_MODE, 3, "Evaluative", "Partial", "Centre weighted");
    }

    @Nullable
    public String getMinApertureDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_MIN_APERTURE);
        if (integer == null) {
            return null;
        }
        return integer.intValue() > 512 ? String.format("Unknown (%d)", integer) : TagDescriptor.getFStopDescription(Math.exp((Math.log(2.0d) * decodeCanonEv(integer.intValue())) / 2.0d));
    }

    @Nullable
    public String getPhotoEffectDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_PHOTO_EFFECT);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        if (intValue == 100) {
            return "My Color Data";
        }
        switch (intValue) {
            case 0:
                return BucketVersioningConfiguration.OFF;
            case 1:
                return "Vivid";
            case 2:
                return "Neutral";
            case 3:
                return "Smooth";
            case 4:
                return "Sepia";
            case 5:
                return "B&W";
            case 6:
                return "Custom";
            default:
                return GeneratedOutlineSupport1.outline69("Unknown (", integer, ")");
        }
    }

    @Nullable
    public String getQualityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_QUALITY, 2, DCMEndpoint.Priority.NORMAL, "Fine", null, "Superfine");
    }

    @Nullable
    public String getRecordModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_RECORD_MODE, 1, "JPEG", "CRW+THM", "AVI+THM", "TIF", "TIF+JPEG", "CR2", "CR2+JPEG", null, "MOV", "MP4");
    }

    @Nullable
    public String getSRawQualityDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_SRAW_QUALITY, 0, "n/a", "sRAW1 (mRAW)", "sRAW2 (sRAW)");
    }

    @Nullable
    public String getSaturationDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SATURATION);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 65535 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Low" : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL;
    }

    @Nullable
    public String getSelfTimerDelayDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SELF_TIMER_DELAY);
        if (integer == null) {
            return null;
        }
        if (integer.intValue() == 0) {
            return "Self timer not used";
        }
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(integer.intValue() * 0.1d) + " sec";
    }

    @Nullable
    public String getSerialNumberDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(12);
        if (integer == null) {
            return null;
        }
        return String.format("%04X%05d", Integer.valueOf((integer.intValue() >> 8) & 255), Integer.valueOf(integer.intValue() & 255));
    }

    @Nullable
    public String getSharpnessDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SHARPNESS);
        if (integer == null) {
            return null;
        }
        int intValue = integer.intValue();
        return intValue != 0 ? intValue != 1 ? intValue != 65535 ? GeneratedOutlineSupport1.outline69("Unknown (", integer, ")") : "Low" : DCMEndpoint.Priority.HIGH : DCMEndpoint.Priority.NORMAL;
    }

    @Nullable
    public String getShortFocalLengthDescription() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.CameraSettings.TAG_SHORT_FOCAL_LENGTH);
        if (integer == null) {
            return null;
        }
        String focalUnitsPerMillimetreDescription = getFocalUnitsPerMillimetreDescription();
        return Integer.toString(integer.intValue()) + " " + focalUnitsPerMillimetreDescription;
    }

    @Nullable
    public String getSpotMeteringModeDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.CameraSettings.TAG_SPOT_METERING_MODE, 0, "Center", "AF Point");
    }

    @Nullable
    public String getTagAfPointsInFocus() {
        Integer integer = ((CanonMakernoteDirectory) this._directory).getInteger(CanonMakernoteDirectory.AFInfo.TAG_AF_POINTS_IN_FOCUS);
        if (integer == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            if ((integer.intValue() & (1 << i)) != 0) {
                if (sb.length() != 0) {
                    sb.append(JsonReaderKt.COMMA);
                }
                sb.append(i);
            }
        }
        return sb.length() == 0 ? "None" : sb.toString();
    }

    @Nullable
    public String getWhiteBalanceDescription() {
        return getIndexedDescription(CanonMakernoteDirectory.FocalLength.TAG_WHITE_BALANCE, "Auto", "Sunny", "Cloudy", "Tungsten", "Florescent", ExifInterface.TAG_FLASH, "Custom");
    }
}
