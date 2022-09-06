package com.drew.metadata.icc;

import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.TagDescriptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class IccDescriptor extends TagDescriptor<IccDirectory> {
    private static final int ICC_TAG_TYPE_CURV = 1668641398;
    private static final int ICC_TAG_TYPE_DESC = 1684370275;
    private static final int ICC_TAG_TYPE_MEAS = 1835360627;
    private static final int ICC_TAG_TYPE_MLUC = 1835824483;
    private static final int ICC_TAG_TYPE_SIG = 1936287520;
    private static final int ICC_TAG_TYPE_TEXT = 1952807028;
    private static final int ICC_TAG_TYPE_XYZ_ARRAY = 1482250784;

    public IccDescriptor(@NotNull IccDirectory iccDirectory) {
        super(iccDirectory);
    }

    @NotNull
    public static String formatDoubleAsString(double d, int i, boolean z) {
        int i2 = i;
        String str = "";
        boolean z2 = true;
        if (i2 < 1) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
            outline107.append(Math.round(d));
            return outline107.toString();
        }
        long abs = Math.abs((long) d);
        long round = (int) Math.round(Math.pow(10.0d, i2) * (Math.abs(d) - abs));
        String str2 = str;
        long j = round;
        while (i2 > 0) {
            byte abs2 = (byte) Math.abs(j % 10);
            j /= 10;
            if (str2.length() > 0 || z || abs2 != 0 || i2 == 1) {
                str2 = ((int) abs2) + str2;
            }
            i2--;
        }
        long j2 = abs + j;
        if (d >= FrostVideoEffectController.VIDEO_STRENGTH_CLEAR || (j2 == 0 && round == 0)) {
            z2 = false;
        }
        StringBuilder sb = new StringBuilder();
        if (z2) {
            str = ProcessIdUtil.DEFAULT_PROCESSID;
        }
        sb.append(str);
        sb.append(j2);
        sb.append(".");
        sb.append(str2);
        return sb.toString();
    }

    private static int getInt32FromString(@NotNull String str) throws IOException {
        return new ByteArrayReader(str.getBytes()).getInt32(0);
    }

    @Nullable
    private String getPlatformDescription() {
        String string = ((IccDirectory) this._directory).getString(40);
        if (string == null) {
            return null;
        }
        try {
            switch (getInt32FromString(string)) {
                case 1095782476:
                    return "Apple Computer, Inc.";
                case 1297303124:
                    return "Microsoft Corporation";
                case 1397180704:
                    return "Silicon Graphics, Inc.";
                case 1398099543:
                    return "Sun Microsystems, Inc.";
                case 1413959252:
                    return "Taligent, Inc.";
                default:
                    return String.format("Unknown (%s)", string);
            }
        } catch (IOException unused) {
            return string;
        }
    }

    @Nullable
    private String getProfileClassDescription() {
        String string = ((IccDirectory) this._directory).getString(12);
        if (string == null) {
            return null;
        }
        try {
            switch (getInt32FromString(string)) {
                case 1633842036:
                    return "Abstract";
                case 1818848875:
                    return "DeviceLink";
                case 1835955314:
                    return "Display Device";
                case 1852662636:
                    return "Named Color";
                case 1886549106:
                    return "Output Device";
                case 1935896178:
                    return "Input Device";
                case 1936744803:
                    return "ColorSpace Conversion";
                default:
                    return String.format("Unknown (%s)", string);
            }
        } catch (IOException unused) {
            return string;
        }
    }

    @Nullable
    private String getProfileVersionDescription() {
        Integer integer = ((IccDirectory) this._directory).getInteger(8);
        if (integer == null) {
            return null;
        }
        return String.format("%d.%d.%d", Integer.valueOf((integer.intValue() & ViewCompat.MEASURED_STATE_MASK) >> 24), Integer.valueOf((integer.intValue() & 15728640) >> 20), Integer.valueOf((integer.intValue() & 983040) >> 16));
    }

    @Nullable
    private String getRenderingIntentDescription() {
        return getIndexedDescription(64, "Perceptual", "Media-Relative Colorimetric", ExifInterface.TAG_SATURATION, "ICC-Absolute Colorimetric");
    }

    @Nullable
    private String getTagDataString(int i) {
        byte[] byteArray;
        String str;
        String str2;
        try {
            byteArray = ((IccDirectory) this._directory).getByteArray(i);
        } catch (IOException unused) {
            return null;
        }
        if (byteArray == null) {
            return ((IccDirectory) this._directory).getString(i);
        }
        ByteArrayReader byteArrayReader = new ByteArrayReader(byteArray);
        int i2 = 0;
        int int32 = byteArrayReader.getInt32(0);
        switch (int32) {
            case ICC_TAG_TYPE_XYZ_ARRAY /* 1482250784 */:
                StringBuilder sb = new StringBuilder();
                DecimalFormat decimalFormat = new DecimalFormat("0.####");
                int length = (byteArray.length - 8) / 12;
                while (i2 < length) {
                    int i3 = (i2 * 12) + 8;
                    float s15Fixed16 = byteArrayReader.getS15Fixed16(i3);
                    float s15Fixed162 = byteArrayReader.getS15Fixed16(i3 + 4);
                    float s15Fixed163 = byteArrayReader.getS15Fixed16(i3 + 8);
                    if (i2 > 0) {
                        sb.append(", ");
                    }
                    sb.append("(");
                    sb.append(decimalFormat.format(s15Fixed16));
                    sb.append(", ");
                    sb.append(decimalFormat.format(s15Fixed162));
                    sb.append(", ");
                    sb.append(decimalFormat.format(s15Fixed163));
                    sb.append(")");
                    i2++;
                }
                return sb.toString();
            case ICC_TAG_TYPE_CURV /* 1668641398 */:
                int int322 = byteArrayReader.getInt32(8);
                StringBuilder sb2 = new StringBuilder();
                for (int i4 = 0; i4 < int322; i4++) {
                    if (i4 != 0) {
                        sb2.append(", ");
                    }
                    sb2.append(formatDoubleAsString(byteArrayReader.getUInt16((i4 * 2) + 12) / 65535.0d, 7, false));
                }
                return sb2.toString();
            case 1684370275:
                return new String(byteArray, 12, byteArrayReader.getInt32(8) - 1);
            case 1835360627:
                int int323 = byteArrayReader.getInt32(8);
                float s15Fixed164 = byteArrayReader.getS15Fixed16(12);
                float s15Fixed165 = byteArrayReader.getS15Fixed16(16);
                float s15Fixed166 = byteArrayReader.getS15Fixed16(20);
                int int324 = byteArrayReader.getInt32(24);
                float s15Fixed167 = byteArrayReader.getS15Fixed16(28);
                int int325 = byteArrayReader.getInt32(32);
                String str3 = "Unknown";
                String format = int323 != 0 ? int323 != 1 ? int323 != 2 ? String.format("Unknown %d", Integer.valueOf(int323)) : "1964 10°" : "1931 2°" : str3;
                if (int324 != 0) {
                    str3 = int324 != 1 ? int324 != 2 ? String.format("Unknown %d", Integer.valueOf(int323)) : "0/d or d/0" : "0/45 or 45/0";
                }
                switch (int325) {
                    case 0:
                        str = "unknown";
                        break;
                    case 1:
                        str = "D50";
                        break;
                    case 2:
                        str = "D65";
                        break;
                    case 3:
                        str = "D93";
                        break;
                    case 4:
                        str = "F2";
                        break;
                    case 5:
                        str = "D55";
                        break;
                    case 6:
                        str = ExifInterface.GPS_MEASUREMENT_IN_PROGRESS;
                        break;
                    case 7:
                        str = "Equi-Power (E)";
                        break;
                    case 8:
                        str = "F8";
                        break;
                    default:
                        str = String.format("Unknown %d", Integer.valueOf(int325));
                        break;
                }
                DecimalFormat decimalFormat2 = new DecimalFormat("0.###");
                return String.format("%s Observer, Backing (%s, %s, %s), Geometry %s, Flare %d%%, Illuminant %s", format, decimalFormat2.format(s15Fixed164), decimalFormat2.format(s15Fixed165), decimalFormat2.format(s15Fixed166), str3, Integer.valueOf(Math.round(s15Fixed167 * 100.0f)), str);
            case ICC_TAG_TYPE_MLUC /* 1835824483 */:
                int int326 = byteArrayReader.getInt32(8);
                StringBuilder sb3 = new StringBuilder();
                sb3.append(int326);
                while (i2 < int326) {
                    int i5 = (i2 * 12) + 16;
                    String stringFromInt32 = IccReader.getStringFromInt32(byteArrayReader.getInt32(i5));
                    int int327 = byteArrayReader.getInt32(i5 + 4);
                    int int328 = byteArrayReader.getInt32(i5 + 8);
                    try {
                        str2 = new String(byteArray, int328, int327, "UTF-16BE");
                    } catch (UnsupportedEncodingException unused2) {
                        str2 = new String(byteArray, int328, int327);
                    }
                    sb3.append(" ");
                    sb3.append(stringFromInt32);
                    sb3.append("(");
                    sb3.append(str2);
                    sb3.append(")");
                    i2++;
                }
                return sb3.toString();
            case ICC_TAG_TYPE_SIG /* 1936287520 */:
                return IccReader.getStringFromInt32(byteArrayReader.getInt32(8));
            case ICC_TAG_TYPE_TEXT /* 1952807028 */:
                try {
                    return new String(byteArray, 8, (byteArray.length - 8) - 1, "ASCII");
                } catch (UnsupportedEncodingException unused3) {
                    return new String(byteArray, 8, (byteArray.length - 8) - 1);
                }
            default:
                return String.format("%s (0x%08X): %d bytes", IccReader.getStringFromInt32(int32), Integer.valueOf(int32), Integer.valueOf(byteArray.length));
        }
        return null;
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        return i != 8 ? i != 12 ? i != 40 ? i != 64 ? (i <= 538976288 || i >= 2054847098) ? super.getDescription(i) : getTagDataString(i) : getRenderingIntentDescription() : getPlatformDescription() : getProfileClassDescription() : getProfileVersionDescription();
    }
}
