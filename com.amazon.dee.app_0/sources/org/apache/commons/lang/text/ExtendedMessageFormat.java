package org.apache.commons.lang.text;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
/* loaded from: classes4.dex */
public class ExtendedMessageFormat extends MessageFormat {
    private static final String DUMMY_PATTERN = "";
    private static final char END_FE = '}';
    private static final String ESCAPED_QUOTE = "''";
    private static final int HASH_SEED = 31;
    private static final char QUOTE = '\'';
    private static final char START_FE = '{';
    private static final char START_FMT = ',';
    private static final long serialVersionUID = -2362048321261811743L;
    private final Map registry;
    private String toPattern;

    public ExtendedMessageFormat(String str) {
        this(str, Locale.getDefault());
    }

    private StrBuilder appendQuotedString(String str, ParsePosition parsePosition, StrBuilder strBuilder, boolean z) {
        int index = parsePosition.getIndex();
        char[] charArray = str.toCharArray();
        if (z && charArray[index] == '\'') {
            next(parsePosition);
            if (strBuilder != null) {
                return strBuilder.append('\'');
            }
            return null;
        }
        int i = index;
        for (int index2 = parsePosition.getIndex(); index2 < str.length(); index2++) {
            if (z && str.substring(index2).startsWith(ESCAPED_QUOTE)) {
                strBuilder.append(charArray, i, parsePosition.getIndex() - i).append('\'');
                parsePosition.setIndex(index2 + 2);
                i = parsePosition.getIndex();
            } else if (charArray[parsePosition.getIndex()] != '\'') {
                next(parsePosition);
            } else {
                next(parsePosition);
                if (strBuilder != null) {
                    return strBuilder.append(charArray, i, parsePosition.getIndex() - i);
                }
                return null;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unterminated quoted string at position ");
        stringBuffer.append(index);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    private boolean containsElements(Collection collection) {
        if (collection != null && collection.size() != 0) {
            for (Object obj : collection) {
                if (obj != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private Format getFormat(String str) {
        String str2;
        if (this.registry != null) {
            int indexOf = str.indexOf(44);
            if (indexOf > 0) {
                String trim = str.substring(0, indexOf).trim();
                str2 = str.substring(indexOf + 1).trim();
                str = trim;
            } else {
                str2 = null;
            }
            FormatFactory formatFactory = (FormatFactory) this.registry.get(str);
            if (formatFactory != null) {
                return formatFactory.getFormat(str, str2, getLocale());
            }
        }
        return null;
    }

    private void getQuotedString(String str, ParsePosition parsePosition, boolean z) {
        appendQuotedString(str, parsePosition, null, z);
    }

    private String insertFormats(String str, ArrayList arrayList) {
        if (!containsElements(arrayList)) {
            return str;
        }
        StrBuilder strBuilder = new StrBuilder(str.length() * 2);
        ParsePosition parsePosition = new ParsePosition(0);
        int i = -1;
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                appendQuotedString(str, parsePosition, strBuilder, false);
            } else if (charAt != '{') {
                if (charAt == '}') {
                    i2--;
                }
                strBuilder.append(charAt);
                next(parsePosition);
            } else {
                i2++;
                if (i2 == 1) {
                    i++;
                    strBuilder.append('{').append(readArgumentIndex(str, next(parsePosition)));
                    String str2 = (String) arrayList.get(i);
                    if (str2 != null) {
                        strBuilder.append(',').append(str2);
                    }
                }
            }
        }
        return strBuilder.toString();
    }

    private ParsePosition next(ParsePosition parsePosition) {
        parsePosition.setIndex(parsePosition.getIndex() + 1);
        return parsePosition;
    }

    private String parseFormatDescription(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        int index2 = parsePosition.getIndex();
        int i = 1;
        while (parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (charAt == '\'') {
                getQuotedString(str, parsePosition, false);
            } else if (charAt == '{') {
                i++;
            } else if (charAt == '}' && i - 1 == 0) {
                return str.substring(index2, parsePosition.getIndex());
            }
            next(parsePosition);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unterminated format element at position ");
        stringBuffer.append(index);
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    private int readArgumentIndex(String str, ParsePosition parsePosition) {
        int index = parsePosition.getIndex();
        seekNonWs(str, parsePosition);
        StrBuilder strBuilder = new StrBuilder();
        boolean z = false;
        while (!z && parsePosition.getIndex() < str.length()) {
            char charAt = str.charAt(parsePosition.getIndex());
            if (Character.isWhitespace(charAt)) {
                seekNonWs(str, parsePosition);
                charAt = str.charAt(parsePosition.getIndex());
                if (charAt != ',' && charAt != '}') {
                    z = true;
                    next(parsePosition);
                }
            }
            if ((charAt == ',' || charAt == '}') && strBuilder.length() > 0) {
                try {
                    return Integer.parseInt(strBuilder.toString());
                } catch (NumberFormatException unused) {
                }
            }
            strBuilder.append(charAt);
            z = !Character.isDigit(charAt);
            next(parsePosition);
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Invalid format argument index at position ");
            stringBuffer.append(index);
            stringBuffer.append(RealTimeTextConstants.COLON_SPACE);
            stringBuffer.append(str.substring(index, parsePosition.getIndex()));
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Unterminated format element at position ");
        stringBuffer2.append(index);
        throw new IllegalArgumentException(stringBuffer2.toString());
    }

    private void seekNonWs(String str, ParsePosition parsePosition) {
        char[] charArray = str.toCharArray();
        do {
            int isMatch = StrMatcher.splitMatcher().isMatch(charArray, parsePosition.getIndex());
            parsePosition.setIndex(parsePosition.getIndex() + isMatch);
            if (isMatch <= 0) {
                return;
            }
        } while (parsePosition.getIndex() < str.length());
    }

    @Override // java.text.MessageFormat
    public final void applyPattern(String str) {
        String str2;
        Format format;
        if (this.registry == null) {
            super.applyPattern(str);
            this.toPattern = super.toPattern();
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StrBuilder strBuilder = new StrBuilder(str.length());
        int i = 0;
        ParsePosition parsePosition = new ParsePosition(0);
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (parsePosition.getIndex() < str.length()) {
            char c = charArray[parsePosition.getIndex()];
            boolean z = true;
            if (c != '\'') {
                if (c == '{') {
                    i2++;
                    seekNonWs(str, parsePosition);
                    int index = parsePosition.getIndex();
                    strBuilder.append('{').append(readArgumentIndex(str, next(parsePosition)));
                    seekNonWs(str, parsePosition);
                    if (charArray[parsePosition.getIndex()] == ',') {
                        str2 = parseFormatDescription(str, next(parsePosition));
                        format = getFormat(str2);
                        if (format == null) {
                            strBuilder.append(',').append(str2);
                        }
                    } else {
                        str2 = null;
                        format = null;
                    }
                    arrayList.add(format);
                    if (format == null) {
                        str2 = null;
                    }
                    arrayList2.add(str2);
                    Validate.isTrue(arrayList.size() == i2);
                    if (arrayList2.size() != i2) {
                        z = false;
                    }
                    Validate.isTrue(z);
                    if (charArray[parsePosition.getIndex()] != '}') {
                        StringBuffer stringBuffer = new StringBuffer();
                        stringBuffer.append("Unreadable format element at position ");
                        stringBuffer.append(index);
                        throw new IllegalArgumentException(stringBuffer.toString());
                    }
                }
                strBuilder.append(charArray[parsePosition.getIndex()]);
                next(parsePosition);
            } else {
                appendQuotedString(str, parsePosition, strBuilder, true);
            }
        }
        super.applyPattern(strBuilder.toString());
        this.toPattern = insertFormats(super.toPattern(), arrayList2);
        if (!containsElements(arrayList)) {
            return;
        }
        Format[] formats = getFormats();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            Format format2 = (Format) it2.next();
            if (format2 != null) {
                formats[i] = format2;
            }
            i++;
        }
        super.setFormats(formats);
    }

    @Override // java.text.MessageFormat
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !super.equals(obj) || ObjectUtils.notEqual(ExtendedMessageFormat.class, obj.getClass())) {
            return false;
        }
        ExtendedMessageFormat extendedMessageFormat = (ExtendedMessageFormat) obj;
        return !ObjectUtils.notEqual(this.toPattern, extendedMessageFormat.toPattern) && !ObjectUtils.notEqual(this.registry, extendedMessageFormat.registry);
    }

    @Override // java.text.MessageFormat
    public int hashCode() {
        int hashCode = ObjectUtils.hashCode(this.registry);
        return ObjectUtils.hashCode(this.toPattern) + ((hashCode + (super.hashCode() * 31)) * 31);
    }

    @Override // java.text.MessageFormat
    public void setFormat(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatByArgumentIndex(int i, Format format) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormats(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public void setFormatsByArgumentIndex(Format[] formatArr) {
        throw new UnsupportedOperationException();
    }

    @Override // java.text.MessageFormat
    public String toPattern() {
        return this.toPattern;
    }

    public ExtendedMessageFormat(String str, Locale locale) {
        this(str, locale, null);
    }

    public ExtendedMessageFormat(String str, Map map) {
        this(str, Locale.getDefault(), map);
    }

    public ExtendedMessageFormat(String str, Locale locale, Map map) {
        super("");
        setLocale(locale);
        this.registry = map;
        applyPattern(str);
    }
}
