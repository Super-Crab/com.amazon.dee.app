package com.drew.metadata;

import androidx.exifinterface.media.ExifInterface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.StringUtil;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class TagDescriptor<T extends Directory> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    @NotNull
    protected final T _directory;

    public TagDescriptor(@NotNull T t) {
        this._directory = t;
    }

    @Nullable
    public static String convertBytesToVersionString(@Nullable int[] iArr, int i) {
        if (iArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 4 && i2 < iArr.length; i2++) {
            if (i2 == i) {
                sb.append('.');
            }
            char c = (char) iArr[i2];
            if (c < '0') {
                c = (char) (c + '0');
            }
            if (i2 != 0 || c != '0') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public static String getFStopDescription(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return "f/" + decimalFormat.format(d);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public static String getFocalLengthDescription(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(d) + " mm";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String get7BitStringFromBytes(int i) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        int length = byteArray.length;
        for (int i2 = 0; i2 < byteArray.length; i2++) {
            int i3 = byteArray[i2] & 255;
            if (i3 == 0 || i3 > 127) {
                length = i2;
                break;
            }
        }
        return new String(byteArray, 0, length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getBitFlagDescription(int i, @NotNull Object... objArr) {
        String str;
        Integer integer = this._directory.getInteger(i);
        if (integer == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Integer num = integer;
        for (int i2 = 0; objArr.length > i2; i2++) {
            Object obj = objArr[i2];
            if (obj != null) {
                char c = (num.intValue() & 1) == 1 ? (char) 1 : (char) 0;
                if (obj instanceof String[]) {
                    str = ((String[]) obj)[c];
                } else if (c != 0 && (obj instanceof String)) {
                    str = (String) obj;
                }
                arrayList.add(str);
            }
            num = Integer.valueOf(num.intValue() >> 1);
        }
        return StringUtil.join(arrayList, ", ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getByteLengthDescription(int i) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(byteArray.length);
        objArr[1] = byteArray.length == 1 ? "" : "s";
        return String.format("(%d byte%s)", objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getDecimalRational(int i, int i2) {
        Rational rational = this._directory.getRational(i);
        if (rational == null) {
            return null;
        }
        return String.format(GeneratedOutlineSupport1.outline52("%.", i2, "f"), Double.valueOf(rational.doubleValue()));
    }

    @Nullable
    public String getDescription(int i) {
        int length;
        Object object = this._directory.getObject(i);
        if (object == null) {
            return null;
        }
        return (!object.getClass().isArray() || (length = Array.getLength(object)) <= 16) ? object instanceof Date ? new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").format((Date) object).replaceAll("([0-9]{2} [^ ]+)$", ":$1") : this._directory.getString(i) : String.format("[%d values]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getEpochTimeDescription(int i) {
        Long longObject = this._directory.getLongObject(i);
        if (longObject == null) {
            return null;
        }
        return new Date(longObject.longValue()).toString();
    }

    @Nullable
    protected String getFormattedFloat(int i, @NotNull String str) {
        Float floatObject = this._directory.getFloatObject(i);
        if (floatObject == null) {
            return null;
        }
        return String.format(str, floatObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getFormattedInt(int i, @NotNull String str) {
        Integer integer = this._directory.getInteger(i);
        if (integer == null) {
            return null;
        }
        return String.format(str, integer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getFormattedString(int i, @NotNull String str) {
        String string = this._directory.getString(i);
        if (string == null) {
            return null;
        }
        return String.format(str, string);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getIndexedDescription(int i, int i2, @NotNull String... strArr) {
        String str;
        Long longObject = this._directory.getLongObject(i);
        if (longObject == null) {
            return null;
        }
        long longValue = longObject.longValue() - i2;
        if (longValue >= 0 && longValue < strArr.length && (str = strArr[(int) longValue]) != null) {
            return str;
        }
        return "Unknown (" + longObject + ")";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getIndexedDescription(int i, @NotNull String... strArr) {
        return getIndexedDescription(i, 0, strArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getLensSpecificationDescription(int i) {
        Rational rational;
        String format;
        Rational[] rationalArray = this._directory.getRationalArray(i);
        if (rationalArray == null || rationalArray.length != 4) {
            return null;
        }
        if (rationalArray[0].isZero() && rationalArray[2].isZero()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (rationalArray[0].equals(rationalArray[1])) {
            rational = rationalArray[0];
        } else {
            sb.append(rationalArray[0].toSimpleString(true));
            sb.append('-');
            rational = rationalArray[1];
        }
        sb.append(rational.toSimpleString(true));
        sb.append("mm");
        if (!rationalArray[2].isZero()) {
            sb.append(Chars.SPACE);
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
            if (rationalArray[2].equals(rationalArray[3])) {
                format = getFStopDescription(rationalArray[2].doubleValue());
            } else {
                sb.append("f/");
                sb.append(decimalFormat.format(rationalArray[2].doubleValue()));
                sb.append('-');
                format = decimalFormat.format(rationalArray[3].doubleValue());
            }
            sb.append(format);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getLightSourceDescription(short s) {
        if (s != 0) {
            if (s == 1) {
                return "Daylight";
            }
            if (s == 2) {
                return "Fluorescent";
            }
            if (s == 3) {
                return "Tungsten (Incandescent)";
            }
            if (s == 4) {
                return ExifInterface.TAG_FLASH;
            }
            if (s == 255) {
                return "Other";
            }
            switch (s) {
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
                    return "Standard Light A";
                case 18:
                    return "Standard Light B";
                case 19:
                    return "Standard Light C";
                case 20:
                    return "D55";
                case 21:
                    return "D65";
                case 22:
                    return "D75";
                case 23:
                    return "D50";
                case 24:
                    return "ISO Studio Tungsten";
                default:
                    return getDescription(s);
            }
        }
        return "Unknown";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getOrientationDescription(int i) {
        return getIndexedDescription(i, 1, "Top, left side (Horizontal / normal)", "Top, right side (Mirror horizontal)", "Bottom, right side (Rotate 180)", "Bottom, left side (Mirror vertical)", "Left side, top (Mirror horizontal and rotate 270 CW)", "Right side, top (Rotate 90 CW)", "Right side, bottom (Mirror horizontal and rotate 90 CW)", "Left side, bottom (Rotate 270 CW)");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getRationalOrDoubleString(int i) {
        Rational rational = this._directory.getRational(i);
        if (rational != null) {
            return rational.toSimpleString(true);
        }
        Double doubleObject = this._directory.getDoubleObject(i);
        if (doubleObject == null) {
            return null;
        }
        return new DecimalFormat("0.###").format(doubleObject);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getShutterSpeedDescription(int i) {
        Float floatObject = this._directory.getFloatObject(i);
        if (floatObject == null) {
            return null;
        }
        if (floatObject.floatValue() > 1.0f) {
            return GeneratedOutlineSupport1.outline52("1/", (int) Math.exp(Math.log(2.0d) * floatObject.floatValue()), " sec");
        }
        double log = Math.log(2.0d);
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(((float) Math.round(((float) (1.0d / Math.exp(log * floatObject.floatValue()))) * 10.0d)) / 10.0f) + " sec";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getSimpleRational(int i) {
        Rational rational = this._directory.getRational(i);
        if (rational == null) {
            return null;
        }
        return rational.toSimpleString(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getStringFromBytes(int i, Charset charset) {
        byte[] byteArray = this._directory.getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return new String(byteArray, charset.name()).trim();
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public String getVersionBytesDescription(int i, int i2) {
        int[] intArray = this._directory.getIntArray(i);
        if (intArray == null) {
            return null;
        }
        return convertBytesToVersionString(intArray, i2);
    }
}
