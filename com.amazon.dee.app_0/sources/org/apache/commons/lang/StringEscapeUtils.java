package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import org.apache.commons.lang.exception.NestableRuntimeException;
import org.apache.commons.lang.text.StrBuilder;
/* loaded from: classes4.dex */
public class StringEscapeUtils {
    private static final char CSV_DELIMITER = ',';
    private static final char CSV_QUOTE = '\"';
    private static final String CSV_QUOTE_STR = String.valueOf('\"');
    private static final char[] CSV_SEARCH_CHARS = {',', '\"', '\r', '\n'};

    public static String escapeCsv(String str) {
        if (StringUtils.containsNone(str, CSV_SEARCH_CHARS)) {
            return str;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            escapeCsv(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String escapeHtml(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter((int) (str.length() * 1.5d));
            escapeHtml(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String escapeJava(String str) {
        return escapeJavaStyleString(str, false, false);
    }

    public static String escapeJavaScript(String str) {
        return escapeJavaStyleString(str, true, true);
    }

    private static String escapeJavaStyleString(String str, boolean z, boolean z2) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(str.length() * 2);
            escapeJavaStyleString(stringWriter, str, z, z2);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String escapeSql(String str) {
        if (str == null) {
            return null;
        }
        return StringUtils.replace(str, "'", "''");
    }

    public static void escapeXml(Writer writer, String str) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            Entities.XML.escape(writer, str);
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null.");
    }

    private static String hex(char c) {
        return Integer.toHexString(c).toUpperCase(Locale.ENGLISH);
    }

    public static String unescapeCsv(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            unescapeCsv(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String unescapeHtml(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter((int) (str.length() * 1.5d));
            unescapeHtml(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String unescapeJava(String str) {
        if (str == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter(str.length());
            unescapeJava(stringWriter, str);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new UnhandledException(e);
        }
    }

    public static String unescapeJavaScript(String str) {
        return unescapeJava(str);
    }

    public static void unescapeXml(Writer writer, String str) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            Entities.XML.unescape(writer, str);
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null.");
    }

    public static void escapeJava(Writer writer, String str) throws IOException {
        escapeJavaStyleString(writer, str, false, false);
    }

    public static void escapeJavaScript(Writer writer, String str) throws IOException {
        escapeJavaStyleString(writer, str, true, true);
    }

    public static void unescapeJavaScript(Writer writer, String str) throws IOException {
        unescapeJava(writer, str);
    }

    public static String escapeXml(String str) {
        if (str == null) {
            return null;
        }
        return Entities.XML.escape(str);
    }

    public static String unescapeXml(String str) {
        if (str == null) {
            return null;
        }
        return Entities.XML.unescape(str);
    }

    public static void escapeHtml(Writer writer, String str) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            Entities.HTML40.escape(writer, str);
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null.");
    }

    private static void escapeJavaStyleString(Writer writer, String str, boolean z, boolean z2) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            int length = str.length();
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt > 4095) {
                    StringBuffer outline103 = GeneratedOutlineSupport1.outline103("\\u");
                    outline103.append(hex(charAt));
                    writer.write(outline103.toString());
                } else if (charAt > 255) {
                    StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("\\u0");
                    outline1032.append(hex(charAt));
                    writer.write(outline1032.toString());
                } else if (charAt > 127) {
                    StringBuffer outline1033 = GeneratedOutlineSupport1.outline103("\\u00");
                    outline1033.append(hex(charAt));
                    writer.write(outline1033.toString());
                } else if (charAt < ' ') {
                    switch (charAt) {
                        case '\b':
                            writer.write(92);
                            writer.write(98);
                            continue;
                        case '\t':
                            writer.write(92);
                            writer.write(116);
                            continue;
                        case '\n':
                            writer.write(92);
                            writer.write(110);
                            continue;
                        case 11:
                        default:
                            if (charAt > 15) {
                                StringBuffer outline1034 = GeneratedOutlineSupport1.outline103("\\u00");
                                outline1034.append(hex(charAt));
                                writer.write(outline1034.toString());
                                continue;
                            } else {
                                StringBuffer outline1035 = GeneratedOutlineSupport1.outline103("\\u000");
                                outline1035.append(hex(charAt));
                                writer.write(outline1035.toString());
                                break;
                            }
                        case '\f':
                            writer.write(92);
                            writer.write(102);
                            continue;
                        case '\r':
                            writer.write(92);
                            writer.write(114);
                            continue;
                    }
                } else if (charAt == '\"') {
                    writer.write(92);
                    writer.write(34);
                } else if (charAt == '\'') {
                    if (z) {
                        writer.write(92);
                    }
                    writer.write(39);
                } else if (charAt == '/') {
                    if (z2) {
                        writer.write(92);
                    }
                    writer.write(47);
                } else if (charAt != '\\') {
                    writer.write(charAt);
                } else {
                    writer.write(92);
                    writer.write(92);
                }
            }
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null");
    }

    public static void unescapeCsv(Writer writer, String str) throws IOException {
        if (str == null) {
            return;
        }
        if (str.length() < 2) {
            writer.write(str);
        } else if (str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') {
            String outline51 = GeneratedOutlineSupport1.outline51(str, 1, 1);
            if (StringUtils.containsAny(outline51, CSV_SEARCH_CHARS)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(CSV_QUOTE_STR);
                stringBuffer.append(CSV_QUOTE_STR);
                str = StringUtils.replace(outline51, stringBuffer.toString(), CSV_QUOTE_STR);
            }
            writer.write(str);
        } else {
            writer.write(str);
        }
    }

    public static void unescapeHtml(Writer writer, String str) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            Entities.HTML40.unescape(writer, str);
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null.");
    }

    public static void unescapeJava(Writer writer, String str) throws IOException {
        if (writer != null) {
            if (str == null) {
                return;
            }
            int length = str.length();
            StrBuilder strBuilder = new StrBuilder(4);
            boolean z = false;
            boolean z2 = false;
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (z2) {
                    strBuilder.append(charAt);
                    if (strBuilder.length() == 4) {
                        try {
                            writer.write((char) Integer.parseInt(strBuilder.toString(), 16));
                            strBuilder.setLength(0);
                            z = false;
                            z2 = false;
                        } catch (NumberFormatException e) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("Unable to parse unicode value: ");
                            stringBuffer.append(strBuilder);
                            throw new NestableRuntimeException(stringBuffer.toString(), e);
                        }
                    } else {
                        continue;
                    }
                } else if (z) {
                    if (charAt == '\"') {
                        writer.write(34);
                    } else if (charAt == '\'') {
                        writer.write(39);
                    } else if (charAt == '\\') {
                        writer.write(92);
                    } else if (charAt == 'b') {
                        writer.write(8);
                    } else if (charAt == 'f') {
                        writer.write(12);
                    } else if (charAt == 'n') {
                        writer.write(10);
                    } else if (charAt == 'r') {
                        writer.write(13);
                    } else if (charAt == 't') {
                        writer.write(9);
                    } else if (charAt != 'u') {
                        writer.write(charAt);
                    } else {
                        z = false;
                        z2 = true;
                    }
                    z = false;
                } else if (charAt == '\\') {
                    z = true;
                } else {
                    writer.write(charAt);
                }
            }
            if (!z) {
                return;
            }
            writer.write(92);
            return;
        }
        throw new IllegalArgumentException("The Writer must not be null");
    }

    public static void escapeCsv(Writer writer, String str) throws IOException {
        if (StringUtils.containsNone(str, CSV_SEARCH_CHARS)) {
            if (str == null) {
                return;
            }
            writer.write(str);
            return;
        }
        writer.write(34);
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                writer.write(34);
            }
            writer.write(charAt);
        }
        writer.write(34);
    }
}
