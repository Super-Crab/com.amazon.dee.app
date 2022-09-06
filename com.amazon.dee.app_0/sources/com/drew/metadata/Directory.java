package com.drew.metadata;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.lang.annotations.SuppressWarnings;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public abstract class Directory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String _floatFormatPattern = "0.###";
    protected TagDescriptor _descriptor;
    @Nullable
    private Directory _parent;
    @NotNull
    protected final Map<Integer, Object> _tagMap = new HashMap();
    @NotNull
    protected final Collection<Tag> _definedTagList = new ArrayList();
    @NotNull
    private final Collection<String> _errorList = new ArrayList(4);

    public void addError(@NotNull String str) {
        this._errorList.add(str);
    }

    public boolean containsTag(int i) {
        return this._tagMap.containsKey(Integer.valueOf(i));
    }

    public boolean getBoolean(int i) throws MetadataException {
        Boolean booleanObject = getBooleanObject(i);
        if (booleanObject != null) {
            return booleanObject.booleanValue();
        }
        Object object = getObject(i);
        if (object == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag '");
            outline107.append(getTagName(i));
            outline107.append("' has not been set -- check using containsTag() first");
            throw new MetadataException(outline107.toString());
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Tag '", i, "' cannot be converted to a boolean.  It is of type '");
        outline109.append(object.getClass());
        outline109.append("'.");
        throw new MetadataException(outline109.toString());
    }

    @Nullable
    @SuppressWarnings(justification = "keep API interface consistent", value = "NP_BOOLEAN_RETURN_NULL")
    public Boolean getBooleanObject(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof Boolean) {
            return (Boolean) object;
        }
        if ((object instanceof String) || (object instanceof StringValue)) {
            try {
                return Boolean.valueOf(Boolean.getBoolean(object.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!(object instanceof Number)) {
            return null;
        } else {
            return Boolean.valueOf(((Number) object).doubleValue() != FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        }
    }

    @Nullable
    public byte[] getByteArray(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof StringValue) {
            return ((StringValue) object).getBytes();
        }
        int i2 = 0;
        if (object instanceof Rational[]) {
            Rational[] rationalArr = (Rational[]) object;
            byte[] bArr = new byte[rationalArr.length];
            while (i2 < bArr.length) {
                bArr[i2] = rationalArr[i2].byteValue();
                i2++;
            }
            return bArr;
        } else if (object instanceof byte[]) {
            return (byte[]) object;
        } else {
            if (object instanceof int[]) {
                int[] iArr = (int[]) object;
                byte[] bArr2 = new byte[iArr.length];
                while (i2 < iArr.length) {
                    bArr2[i2] = (byte) iArr[i2];
                    i2++;
                }
                return bArr2;
            } else if (object instanceof short[]) {
                short[] sArr = (short[]) object;
                byte[] bArr3 = new byte[sArr.length];
                while (i2 < sArr.length) {
                    bArr3[i2] = (byte) sArr[i2];
                    i2++;
                }
                return bArr3;
            } else if (!(object instanceof CharSequence)) {
                if (!(object instanceof Integer)) {
                    return null;
                }
                return new byte[]{((Integer) object).byteValue()};
            } else {
                CharSequence charSequence = (CharSequence) object;
                byte[] bArr4 = new byte[charSequence.length()];
                while (i2 < charSequence.length()) {
                    bArr4[i2] = (byte) charSequence.charAt(i2);
                    i2++;
                }
                return bArr4;
            }
        }
    }

    @Nullable
    public Date getDate(int i) {
        return getDate(i, null, null);
    }

    @Nullable
    public Date getDate(int i, @Nullable String str, @Nullable TimeZone timeZone) {
        String str2;
        String str3;
        TimeZone timeZone2;
        Date parse;
        Object object = getObject(i);
        if (object instanceof Date) {
            return (Date) object;
        }
        if ((object instanceof String) || (object instanceof StringValue)) {
            String[] strArr = {"yyyy:MM:dd HH:mm:ss", "yyyy:MM:dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HH:mm", "yyyy-MM-dd", "yyyy-MM", "yyyyMMdd", "yyyy"};
            String obj = object.toString();
            Matcher matcher = Pattern.compile("(\\d\\d:\\d\\d:\\d\\d)(\\.\\d+)").matcher(obj);
            if (matcher.find()) {
                String substring = matcher.group(2).substring(1);
                String replaceAll = matcher.replaceAll("$1");
                str2 = substring;
                obj = replaceAll;
            } else {
                str2 = str;
            }
            Matcher matcher2 = Pattern.compile("(Z|[+-]\\d\\d:\\d\\d)$").matcher(obj);
            if (matcher2.find()) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GMT");
                outline107.append(matcher2.group().replaceAll("Z", ""));
                timeZone2 = TimeZone.getTimeZone(outline107.toString());
                str3 = matcher2.replaceAll("");
            } else {
                str3 = obj;
                timeZone2 = timeZone;
            }
            for (String str4 : strArr) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str4);
                    if (timeZone2 != null) {
                        simpleDateFormat.setTimeZone(timeZone2);
                    } else {
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    }
                    parse = simpleDateFormat.parse(str3);
                    break;
                } catch (ParseException unused) {
                }
            }
        } else {
            str2 = str;
        }
        parse = null;
        if (parse == null) {
            return null;
        }
        if (str2 == null) {
            return parse;
        }
        try {
            int parseDouble = (int) (Double.parseDouble("." + str2) * 1000.0d);
            if (parseDouble < 0 || parseDouble >= 1000) {
                return parse;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(parse);
            calendar.set(14, parseDouble);
            return calendar.getTime();
        } catch (NumberFormatException unused2) {
            return parse;
        }
    }

    @Nullable
    public Date getDate(int i, @Nullable TimeZone timeZone) {
        return getDate(i, null, timeZone);
    }

    @Nullable
    public String getDescription(int i) {
        return this._descriptor.getDescription(i);
    }

    public double getDouble(int i) throws MetadataException {
        Double doubleObject = getDoubleObject(i);
        if (doubleObject != null) {
            return doubleObject.doubleValue();
        }
        Object object = getObject(i);
        if (object == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag '");
            outline107.append(getTagName(i));
            outline107.append("' has not been set -- check using containsTag() first");
            throw new MetadataException(outline107.toString());
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Tag '", i, "' cannot be converted to a double.  It is of type '");
        outline109.append(object.getClass());
        outline109.append("'.");
        throw new MetadataException(outline109.toString());
    }

    @Nullable
    public Double getDoubleObject(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if ((object instanceof String) || (object instanceof StringValue)) {
            try {
                return Double.valueOf(Double.parseDouble(object.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!(object instanceof Number)) {
            return null;
        } else {
            return Double.valueOf(((Number) object).doubleValue());
        }
    }

    public int getErrorCount() {
        return this._errorList.size();
    }

    @NotNull
    public Iterable<String> getErrors() {
        return Collections.unmodifiableCollection(this._errorList);
    }

    public float getFloat(int i) throws MetadataException {
        Float floatObject = getFloatObject(i);
        if (floatObject != null) {
            return floatObject.floatValue();
        }
        Object object = getObject(i);
        if (object == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag '");
            outline107.append(getTagName(i));
            outline107.append("' has not been set -- check using containsTag() first");
            throw new MetadataException(outline107.toString());
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Tag '", i, "' cannot be converted to a float.  It is of type '");
        outline109.append(object.getClass());
        outline109.append("'.");
        throw new MetadataException(outline109.toString());
    }

    @Nullable
    public Float getFloatObject(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if ((object instanceof String) || (object instanceof StringValue)) {
            try {
                return Float.valueOf(Float.parseFloat(object.toString()));
            } catch (NumberFormatException unused) {
                return null;
            }
        } else if (!(object instanceof Number)) {
            return null;
        } else {
            return Float.valueOf(((Number) object).floatValue());
        }
    }

    public int getInt(int i) throws MetadataException {
        Integer integer = getInteger(i);
        if (integer != null) {
            return integer.intValue();
        }
        Object object = getObject(i);
        if (object == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag '");
            outline107.append(getTagName(i));
            outline107.append("' has not been set -- check using containsTag() first");
            throw new MetadataException(outline107.toString());
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Tag '", i, "' cannot be converted to int.  It is of type '");
        outline109.append(object.getClass());
        outline109.append("'.");
        throw new MetadataException(outline109.toString());
    }

    @Nullable
    public int[] getIntArray(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof int[]) {
            return (int[]) object;
        }
        int i2 = 0;
        if (object instanceof Rational[]) {
            Rational[] rationalArr = (Rational[]) object;
            int[] iArr = new int[rationalArr.length];
            while (i2 < iArr.length) {
                iArr[i2] = rationalArr[i2].intValue();
                i2++;
            }
            return iArr;
        } else if (object instanceof short[]) {
            short[] sArr = (short[]) object;
            int[] iArr2 = new int[sArr.length];
            while (i2 < sArr.length) {
                iArr2[i2] = sArr[i2];
                i2++;
            }
            return iArr2;
        } else if (object instanceof byte[]) {
            byte[] bArr = (byte[]) object;
            int[] iArr3 = new int[bArr.length];
            while (i2 < bArr.length) {
                iArr3[i2] = bArr[i2];
                i2++;
            }
            return iArr3;
        } else if (!(object instanceof CharSequence)) {
            if (!(object instanceof Integer)) {
                return null;
            }
            return new int[]{((Integer) object).intValue()};
        } else {
            CharSequence charSequence = (CharSequence) object;
            int[] iArr4 = new int[charSequence.length()];
            while (i2 < charSequence.length()) {
                iArr4[i2] = charSequence.charAt(i2);
                i2++;
            }
            return iArr4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public Integer getInteger(int i) {
        int i2;
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof Number) {
            i2 = ((Number) object).intValue();
        } else {
            if (!(object instanceof String) && !(object instanceof StringValue)) {
                if (object instanceof Rational[]) {
                    Rational[] rationalArr = (Rational[]) object;
                    if (rationalArr.length == 1) {
                        i2 = rationalArr[0].intValue();
                    }
                } else if (object instanceof byte[]) {
                    byte[] bArr = (byte[]) object;
                    if (bArr.length == 1) {
                        i2 = bArr[0];
                    }
                } else if (object instanceof int[]) {
                    int[] iArr = (int[]) object;
                    if (iArr.length == 1) {
                        i2 = iArr[0];
                    }
                } else if (object instanceof short[]) {
                    short[] sArr = (short[]) object;
                    if (sArr.length == 1) {
                        i2 = sArr[0];
                    }
                }
                return null;
            }
            try {
                return Integer.valueOf(Integer.parseInt(object.toString()));
            } catch (NumberFormatException unused) {
                long j = 0;
                for (byte b : object.toString().getBytes()) {
                    j = (j << 8) + (b & 255);
                }
                i2 = (int) j;
            }
        }
        return Integer.valueOf(i2);
    }

    public long getLong(int i) throws MetadataException {
        Long longObject = getLongObject(i);
        if (longObject != null) {
            return longObject.longValue();
        }
        Object object = getObject(i);
        if (object == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tag '");
            outline107.append(getTagName(i));
            outline107.append("' has not been set -- check using containsTag() first");
            throw new MetadataException(outline107.toString());
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Tag '", i, "' cannot be converted to a long.  It is of type '");
        outline109.append(object.getClass());
        outline109.append("'.");
        throw new MetadataException(outline109.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public Long getLongObject(int i) {
        int i2;
        long j;
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (!(object instanceof Number)) {
            if ((object instanceof String) || (object instanceof StringValue)) {
                try {
                    return Long.valueOf(Long.parseLong(object.toString()));
                } catch (NumberFormatException unused) {
                    return null;
                }
            } else if (object instanceof Rational[]) {
                Rational[] rationalArr = (Rational[]) object;
                if (rationalArr.length == 1) {
                    j = rationalArr[0].longValue();
                }
                return null;
            } else if (object instanceof byte[]) {
                byte[] bArr = (byte[]) object;
                if (bArr.length == 1) {
                    i2 = bArr[0];
                    j = i2;
                }
                return null;
            } else if (object instanceof int[]) {
                int[] iArr = (int[]) object;
                if (iArr.length == 1) {
                    i2 = iArr[0];
                    j = i2;
                }
                return null;
            } else {
                if (object instanceof short[]) {
                    short[] sArr = (short[]) object;
                    if (sArr.length == 1) {
                        i2 = sArr[0];
                        j = i2;
                    }
                }
                return null;
            }
        }
        j = ((Number) object).longValue();
        return Long.valueOf(j);
    }

    @NotNull
    public abstract String getName();

    @Nullable
    public Object getObject(int i) {
        return this._tagMap.get(Integer.valueOf(i));
    }

    @Nullable
    public Directory getParent() {
        return this._parent;
    }

    @Nullable
    public Rational getRational(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof Rational) {
            return (Rational) object;
        }
        if (object instanceof Integer) {
            return new Rational(((Integer) object).intValue(), 1L);
        }
        if (!(object instanceof Long)) {
            return null;
        }
        return new Rational(((Long) object).longValue(), 1L);
    }

    @Nullable
    public Rational[] getRationalArray(int i) {
        Object object = getObject(i);
        if (object != null && (object instanceof Rational[])) {
            return (Rational[]) object;
        }
        return null;
    }

    @Nullable
    public String getString(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof Rational) {
            return ((Rational) object).toSimpleString(true);
        }
        if (!object.getClass().isArray()) {
            return object instanceof Double ? new DecimalFormat(_floatFormatPattern).format(((Double) object).doubleValue()) : object instanceof Float ? new DecimalFormat(_floatFormatPattern).format(((Float) object).floatValue()) : object.toString();
        }
        int length = Array.getLength(object);
        Class<?> componentType = object.getClass().getComponentType();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (Object.class.isAssignableFrom(componentType)) {
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append(Array.get(object, i2).toString());
                i2++;
            }
        } else if (componentType.getName().equals("int")) {
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append(Array.getInt(object, i2));
                i2++;
            }
        } else if (componentType.getName().equals("short")) {
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append((int) Array.getShort(object, i2));
                i2++;
            }
        } else if (componentType.getName().equals("long")) {
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append(Array.getLong(object, i2));
                i2++;
            }
        } else if (componentType.getName().equals("float")) {
            DecimalFormat decimalFormat = new DecimalFormat(_floatFormatPattern);
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                String format = decimalFormat.format(Array.getFloat(object, i2));
                if (format.equals("-0")) {
                    format = "0";
                }
                sb.append(format);
                i2++;
            }
        } else if (componentType.getName().equals("double")) {
            DecimalFormat decimalFormat2 = new DecimalFormat(_floatFormatPattern);
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                String format2 = decimalFormat2.format(Array.getDouble(object, i2));
                if (format2.equals("-0")) {
                    format2 = "0";
                }
                sb.append(format2);
                i2++;
            }
        } else if (componentType.getName().equals("byte")) {
            while (i2 < length) {
                if (i2 != 0) {
                    sb.append(Chars.SPACE);
                }
                sb.append(Array.getByte(object, i2) & 255);
                i2++;
            }
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected array component type: ");
            outline107.append(componentType.getName());
            addError(outline107.toString());
        }
        return sb.toString();
    }

    @Nullable
    public String getString(int i, String str) {
        byte[] byteArray = getByteArray(i);
        if (byteArray == null) {
            return null;
        }
        try {
            return new String(byteArray, str);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    @Nullable
    public String[] getStringArray(int i) {
        Object object = getObject(i);
        String[] strArr = null;
        if (object == null) {
            return null;
        }
        if (object instanceof String[]) {
            return (String[]) object;
        }
        int i2 = 0;
        if (object instanceof String) {
            return new String[]{(String) object};
        }
        if (object instanceof StringValue) {
            return new String[]{object.toString()};
        }
        if (object instanceof StringValue[]) {
            StringValue[] stringValueArr = (StringValue[]) object;
            String[] strArr2 = new String[stringValueArr.length];
            while (i2 < strArr2.length) {
                strArr2[i2] = stringValueArr[i2].toString();
                i2++;
            }
            return strArr2;
        } else if (object instanceof int[]) {
            int[] iArr = (int[]) object;
            String[] strArr3 = new String[iArr.length];
            while (i2 < strArr3.length) {
                strArr3[i2] = Integer.toString(iArr[i2]);
                i2++;
            }
            return strArr3;
        } else if (object instanceof byte[]) {
            byte[] bArr = (byte[]) object;
            String[] strArr4 = new String[bArr.length];
            while (i2 < strArr4.length) {
                strArr4[i2] = Byte.toString(bArr[i2]);
                i2++;
            }
            return strArr4;
        } else {
            if (object instanceof Rational[]) {
                Rational[] rationalArr = (Rational[]) object;
                strArr = new String[rationalArr.length];
                for (int i3 = 0; i3 < strArr.length; i3++) {
                    strArr[i3] = rationalArr[i3].toSimpleString(false);
                }
            }
            return strArr;
        }
    }

    @Nullable
    public StringValue getStringValue(int i) {
        Object object = getObject(i);
        if (object instanceof StringValue) {
            return (StringValue) object;
        }
        return null;
    }

    @Nullable
    public StringValue[] getStringValueArray(int i) {
        Object object = getObject(i);
        if (object == null) {
            return null;
        }
        if (object instanceof StringValue[]) {
            return (StringValue[]) object;
        }
        if (!(object instanceof StringValue)) {
            return null;
        }
        return new StringValue[]{(StringValue) object};
    }

    public int getTagCount() {
        return this._definedTagList.size();
    }

    @NotNull
    public String getTagName(int i) {
        HashMap<Integer, String> tagNameMap = getTagNameMap();
        if (!tagNameMap.containsKey(Integer.valueOf(i))) {
            String hexString = Integer.toHexString(i);
            while (hexString.length() < 4) {
                hexString = GeneratedOutlineSupport1.outline72("0", hexString);
            }
            return GeneratedOutlineSupport1.outline75("Unknown tag (0x", hexString, ")");
        }
        return tagNameMap.get(Integer.valueOf(i));
    }

    @NotNull
    protected abstract HashMap<Integer, String> getTagNameMap();

    @NotNull
    public Collection<Tag> getTags() {
        return Collections.unmodifiableCollection(this._definedTagList);
    }

    public boolean hasErrors() {
        return this._errorList.size() > 0;
    }

    public boolean hasTagName(int i) {
        return getTagNameMap().containsKey(Integer.valueOf(i));
    }

    public boolean isEmpty() {
        return this._errorList.isEmpty() && this._definedTagList.isEmpty();
    }

    public void setBoolean(int i, boolean z) {
        setObject(i, Boolean.valueOf(z));
    }

    public void setByteArray(int i, @NotNull byte[] bArr) {
        setObjectArray(i, bArr);
    }

    public void setDate(int i, @NotNull Date date) {
        setObject(i, date);
    }

    public void setDescriptor(@NotNull TagDescriptor tagDescriptor) {
        if (tagDescriptor != null) {
            this._descriptor = tagDescriptor;
            return;
        }
        throw new NullPointerException("cannot set a null descriptor");
    }

    public void setDouble(int i, double d) {
        setObject(i, Double.valueOf(d));
    }

    public void setDoubleArray(int i, @NotNull double[] dArr) {
        setObjectArray(i, dArr);
    }

    public void setFloat(int i, float f) {
        setObject(i, Float.valueOf(f));
    }

    public void setFloatArray(int i, @NotNull float[] fArr) {
        setObjectArray(i, fArr);
    }

    public void setInt(int i, int i2) {
        setObject(i, Integer.valueOf(i2));
    }

    public void setIntArray(int i, @NotNull int[] iArr) {
        setObjectArray(i, iArr);
    }

    public void setLong(int i, long j) {
        setObject(i, Long.valueOf(j));
    }

    public void setObject(int i, @NotNull Object obj) {
        if (obj != null) {
            if (!this._tagMap.containsKey(Integer.valueOf(i))) {
                this._definedTagList.add(new Tag(i, this));
            }
            this._tagMap.put(Integer.valueOf(i), obj);
            return;
        }
        throw new NullPointerException("cannot set a null object");
    }

    public void setObjectArray(int i, @NotNull Object obj) {
        setObject(i, obj);
    }

    public void setParent(@NotNull Directory directory) {
        this._parent = directory;
    }

    public void setRational(int i, @NotNull Rational rational) {
        setObject(i, rational);
    }

    public void setRationalArray(int i, @NotNull Rational[] rationalArr) {
        setObjectArray(i, rationalArr);
    }

    public void setString(int i, @NotNull String str) {
        if (str != null) {
            setObject(i, str);
            return;
        }
        throw new NullPointerException("cannot set a null String");
    }

    public void setStringArray(int i, @NotNull String[] strArr) {
        setObjectArray(i, strArr);
    }

    public void setStringValue(int i, @NotNull StringValue stringValue) {
        if (stringValue != null) {
            setObject(i, stringValue);
            return;
        }
        throw new NullPointerException("cannot set a null StringValue");
    }

    public void setStringValueArray(int i, @NotNull StringValue[] stringValueArr) {
        setObjectArray(i, stringValueArr);
    }

    public String toString() {
        Object[] objArr = new Object[3];
        objArr[0] = getName();
        objArr[1] = Integer.valueOf(this._tagMap.size());
        objArr[2] = this._tagMap.size() == 1 ? "tag" : "tags";
        return String.format("%s Directory (%d %s)", objArr);
    }
}
