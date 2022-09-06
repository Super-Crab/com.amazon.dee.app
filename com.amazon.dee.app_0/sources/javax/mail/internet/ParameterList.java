package javax.mail.internet;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.PropUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public class ParameterList {
    private String lastName;
    private Map list;
    private Set multisegmentNames;
    private Map slist;
    private static final boolean encodeParameters = PropUtil.getBooleanSystemProperty("mail.mime.encodeparameters", true);
    private static final boolean decodeParameters = PropUtil.getBooleanSystemProperty("mail.mime.decodeparameters", true);
    private static final boolean decodeParametersStrict = PropUtil.getBooleanSystemProperty("mail.mime.decodeparameters.strict", false);
    private static final boolean applehack = PropUtil.getBooleanSystemProperty("mail.mime.applefilenames", false);
    private static final boolean windowshack = PropUtil.getBooleanSystemProperty("mail.mime.windowsfilenames", false);
    private static final boolean parametersStrict = PropUtil.getBooleanSystemProperty("mail.mime.parameters.strict", true);
    private static final boolean splitLongParameters = PropUtil.getBooleanSystemProperty("mail.mime.splitlongparameters", true);
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class MultiValue extends ArrayList {
        private static final long serialVersionUID = 699561094618751023L;
        String value;

        private MultiValue() {
        }
    }

    /* loaded from: classes3.dex */
    private static class ParamEnum implements Enumeration {

        /* renamed from: it  reason: collision with root package name */
        private Iterator f20it;

        ParamEnum(Iterator it2) {
            this.f20it = it2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f20it.hasNext();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return this.f20it.next();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class ToStringBuffer {
        private StringBuffer sb = new StringBuffer();
        private int used;

        public ToStringBuffer(int i) {
            this.used = i;
        }

        public void addNV(String str, String str2) {
            int lastIndexOf;
            this.sb.append("; ");
            this.used += 2;
            if (this.used + str2.length() + str.length() + 1 > 76) {
                this.sb.append("\r\n\t");
                this.used = 8;
            }
            StringBuffer stringBuffer = this.sb;
            stringBuffer.append(str);
            stringBuffer.append(Chars.EQ);
            this.used = str.length() + 1 + this.used;
            if (str2.length() + this.used > 76) {
                String fold = MimeUtility.fold(this.used, str2);
                this.sb.append(fold);
                if (fold.lastIndexOf(10) >= 0) {
                    this.used = ((fold.length() - lastIndexOf) - 1) + this.used;
                    return;
                }
                this.used = fold.length() + this.used;
                return;
            }
            this.sb.append(str2);
            this.used = str2.length() + this.used;
        }

        public String toString() {
            return this.sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Value {
        String charset;
        String encodedValue;
        String value;

        private Value() {
        }
    }

    public ParameterList() {
        this.list = new LinkedHashMap();
        this.lastName = null;
        if (decodeParameters) {
            this.multisegmentNames = new HashSet();
            this.slist = new HashMap();
        }
    }

    private void combineMultisegmentNames(boolean z) throws ParseException {
        try {
            for (String str : this.multisegmentNames) {
                String str2 = null;
                MultiValue multiValue = new MultiValue();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i = 0;
                while (true) {
                    String str3 = str + "*" + i;
                    Object obj = this.slist.get(str3);
                    if (obj == null) {
                        break;
                    }
                    multiValue.add(obj);
                    try {
                        if (obj instanceof Value) {
                            Value value = (Value) obj;
                            if (i == 0) {
                                str2 = value.charset;
                            } else if (str2 == null) {
                                this.multisegmentNames.remove(str);
                                break;
                            }
                            decodeBytes(value.value, byteArrayOutputStream);
                        } else {
                            byteArrayOutputStream.write(ASCIIUtility.getBytes((String) obj));
                        }
                    } catch (IOException unused) {
                    }
                    this.slist.remove(str3);
                    i++;
                }
                if (i == 0) {
                    this.list.remove(str);
                } else {
                    if (str2 != null) {
                        try {
                            str2 = MimeUtility.javaCharset(str2);
                        } catch (UnsupportedEncodingException e) {
                            if (!decodeParametersStrict) {
                                try {
                                    multiValue.value = byteArrayOutputStream.toString("iso-8859-1");
                                } catch (UnsupportedEncodingException unused2) {
                                }
                            } else {
                                throw new ParseException(e.toString());
                            }
                        }
                    }
                    if (str2 == null || str2.length() == 0) {
                        str2 = MimeUtility.getDefaultJavaCharset();
                    }
                    if (str2 != null) {
                        multiValue.value = byteArrayOutputStream.toString(str2);
                    } else {
                        multiValue.value = byteArrayOutputStream.toString();
                    }
                    this.list.put(str, multiValue);
                }
            }
            if (this.slist.size() > 0) {
                for (Object obj2 : this.slist.values()) {
                    if (obj2 instanceof Value) {
                        Value value2 = (Value) obj2;
                        try {
                            value2.value = decodeBytes(value2.value, value2.charset);
                        } catch (UnsupportedEncodingException e2) {
                            if (decodeParametersStrict) {
                                throw new ParseException(e2.toString());
                            }
                        }
                    }
                }
                this.list.putAll(this.slist);
            }
            this.multisegmentNames.clear();
            this.slist.clear();
        } catch (Throwable th) {
            if (z) {
                if (this.slist.size() > 0) {
                    for (Object obj3 : this.slist.values()) {
                        if (obj3 instanceof Value) {
                            Value value3 = (Value) obj3;
                            try {
                                value3.value = decodeBytes(value3.value, value3.charset);
                            } catch (UnsupportedEncodingException e3) {
                                if (decodeParametersStrict) {
                                    throw new ParseException(e3.toString());
                                }
                            }
                        }
                    }
                    this.list.putAll(this.slist);
                }
                this.multisegmentNames.clear();
                this.slist.clear();
            }
            throw th;
        }
    }

    private static String decodeBytes(String str, String str2) throws ParseException, UnsupportedEncodingException {
        byte[] bArr = new byte[str.length()];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                try {
                    charAt = (char) Integer.parseInt(str.substring(i + 1, i + 3), 16);
                    i += 2;
                } catch (NumberFormatException e) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e.toString());
                    }
                } catch (StringIndexOutOfBoundsException e2) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e2.toString());
                    }
                }
            }
            bArr[i2] = (byte) charAt;
            i++;
            i2++;
        }
        if (str2 != null) {
            str2 = MimeUtility.javaCharset(str2);
        }
        if (str2 == null || str2.length() == 0) {
            str2 = MimeUtility.getDefaultJavaCharset();
        }
        return new String(bArr, 0, i2, str2);
    }

    private static Value encodeValue(String str, String str2) {
        if (MimeUtility.checkAscii(str) == 1) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(MimeUtility.javaCharset(str2));
            StringBuffer stringBuffer = new StringBuffer(str2.length() + bytes.length + 2);
            stringBuffer.append(str2);
            stringBuffer.append("''");
            for (byte b : bytes) {
                char c = (char) (b & 255);
                if (c > ' ' && c < 127 && c != '*' && c != '\'' && c != '%' && HeaderTokenizer.MIME.indexOf(c) < 0) {
                    stringBuffer.append(c);
                } else {
                    stringBuffer.append('%');
                    stringBuffer.append(hex[c >> 4]);
                    stringBuffer.append(hex[c & 15]);
                }
            }
            Value value = new Value();
            value.charset = str2;
            value.value = str;
            value.encodedValue = stringBuffer.toString();
            return value;
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private static Value extractCharset(String str) throws ParseException {
        int indexOf;
        Value value = new Value();
        value.encodedValue = str;
        value.value = str;
        try {
            indexOf = str.indexOf(39);
        } catch (NumberFormatException e) {
            if (decodeParametersStrict) {
                throw new ParseException(e.toString());
            }
        } catch (StringIndexOutOfBoundsException e2) {
            if (decodeParametersStrict) {
                throw new ParseException(e2.toString());
            }
        }
        if (indexOf < 0) {
            if (!decodeParametersStrict) {
                return value;
            }
            throw new ParseException("Missing charset in encoded value: " + str);
        }
        String substring = str.substring(0, indexOf);
        int indexOf2 = str.indexOf(39, indexOf + 1);
        if (indexOf2 < 0) {
            if (!decodeParametersStrict) {
                return value;
            }
            throw new ParseException("Missing language in encoded value: " + str);
        }
        value.value = str.substring(indexOf2 + 1);
        value.charset = substring;
        return value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v8, types: [javax.mail.internet.ParameterList$Value] */
    /* JADX WARN: Type inference failed for: r6v4, types: [javax.mail.internet.ParameterList$Value] */
    private void putEncodedName(String str, String str2) throws ParseException {
        String str3;
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            this.list.put(str, str2);
        } else if (indexOf == str.length() - 1) {
            String substring = str.substring(0, indexOf);
            Value extractCharset = extractCharset(str2);
            try {
                extractCharset.value = decodeBytes(extractCharset.value, extractCharset.charset);
            } catch (UnsupportedEncodingException e) {
                if (decodeParametersStrict) {
                    throw new ParseException(e.toString());
                }
            }
            this.list.put(substring, extractCharset);
        } else {
            String substring2 = str.substring(0, indexOf);
            this.multisegmentNames.add(substring2);
            this.list.put(substring2, "");
            String str4 = str2;
            if (str.endsWith("*")) {
                if (str.endsWith("*0*")) {
                    str3 = extractCharset(str2);
                } else {
                    ?? value = new Value();
                    value.encodedValue = str2;
                    value.value = str2;
                    str3 = value;
                }
                str = GeneratedOutlineSupport1.outline50(str, -1, 0);
                str4 = str3;
            }
            this.slist.put(str, str4);
        }
    }

    private static String quote(String str) {
        return MimeUtility.quote(str, HeaderTokenizer.MIME);
    }

    public void combineSegments() {
        if (!decodeParameters || this.multisegmentNames.size() <= 0) {
            return;
        }
        try {
            combineMultisegmentNames(true);
        } catch (ParseException unused) {
        }
    }

    public String get(String str) {
        Object obj = this.list.get(str.trim().toLowerCase(Locale.ENGLISH));
        if (obj instanceof MultiValue) {
            return ((MultiValue) obj).value;
        }
        if (obj instanceof Value) {
            return ((Value) obj).value;
        }
        return (String) obj;
    }

    public Enumeration getNames() {
        return new ParamEnum(this.list.keySet().iterator());
    }

    public void remove(String str) {
        this.list.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public void set(String str, String str2) {
        String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
        if (decodeParameters) {
            try {
                putEncodedName(lowerCase, str2);
                return;
            } catch (ParseException unused) {
                this.list.put(lowerCase, str2);
                return;
            }
        }
        this.list.put(lowerCase, str2);
    }

    public int size() {
        return this.list.size();
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        String outline49;
        String str;
        ToStringBuffer toStringBuffer = new ToStringBuffer(i);
        for (Map.Entry entry : this.list.entrySet()) {
            String str2 = (String) entry.getKey();
            Object value = entry.getValue();
            if (value instanceof MultiValue) {
                MultiValue multiValue = (MultiValue) value;
                String outline72 = GeneratedOutlineSupport1.outline72(str2, "*");
                for (int i2 = 0; i2 < multiValue.size(); i2++) {
                    Object obj = multiValue.get(i2);
                    if (obj instanceof Value) {
                        outline49 = GeneratedOutlineSupport1.outline52(outline72, i2, "*");
                        str = ((Value) obj).encodedValue;
                    } else {
                        outline49 = GeneratedOutlineSupport1.outline49(outline72, i2);
                        str = (String) obj;
                    }
                    toStringBuffer.addNV(outline49, quote(str));
                }
            } else if (value instanceof Value) {
                toStringBuffer.addNV(GeneratedOutlineSupport1.outline72(str2, "*"), quote(((Value) value).encodedValue));
            } else {
                String str3 = (String) value;
                if (str3.length() > 60 && splitLongParameters && encodeParameters) {
                    String outline722 = GeneratedOutlineSupport1.outline72(str2, "*");
                    int i3 = 0;
                    while (str3.length() > 60) {
                        toStringBuffer.addNV(GeneratedOutlineSupport1.outline49(outline722, i3), quote(str3.substring(0, 60)));
                        str3 = str3.substring(60);
                        i3++;
                    }
                    if (str3.length() > 0) {
                        toStringBuffer.addNV(GeneratedOutlineSupport1.outline49(outline722, i3), quote(str3));
                    }
                } else {
                    toStringBuffer.addNV(str2, quote(str3));
                }
            }
        }
        return toStringBuffer.toString();
    }

    public void set(String str, String str2, String str3) {
        if (encodeParameters) {
            Value encodeValue = encodeValue(str2, str3);
            if (encodeValue != null) {
                this.list.put(str.trim().toLowerCase(Locale.ENGLISH), encodeValue);
                return;
            } else {
                set(str, str2);
                return;
            }
        }
        set(str, str2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
        if (javax.mail.internet.ParameterList.decodeParameters == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
        combineMultisegmentNames(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0036, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ParameterList(java.lang.String r12) throws javax.mail.internet.ParseException {
        /*
            Method dump skipped, instructions count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.ParameterList.<init>(java.lang.String):void");
    }

    private static void decodeBytes(String str, OutputStream outputStream) throws ParseException, IOException {
        int i = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt == '%') {
                try {
                    charAt = (char) Integer.parseInt(str.substring(i + 1, i + 3), 16);
                    i += 2;
                } catch (NumberFormatException e) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e.toString());
                    }
                } catch (StringIndexOutOfBoundsException e2) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e2.toString());
                    }
                }
            }
            outputStream.write((byte) charAt);
            i++;
        }
    }
}
