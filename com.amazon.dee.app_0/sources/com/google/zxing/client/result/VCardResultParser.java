package com.google.zxing.client.result;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public final class VCardResultParser extends ResultParser {
    private static final Pattern BEGIN_VCARD = Pattern.compile("BEGIN:VCARD", 2);
    private static final Pattern VCARD_LIKE_DATE = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");
    private static final Pattern CR_LF_SPACE_TAB = Pattern.compile("\r\n[ \t]");
    private static final Pattern NEWLINE_ESCAPE = Pattern.compile("\\\\[nN]");
    private static final Pattern VCARD_ESCAPES = Pattern.compile("\\\\([,;\\\\])");
    private static final Pattern EQUALS = Pattern.compile(Config.Compare.EQUAL_TO);
    private static final Pattern SEMICOLON = Pattern.compile(";");
    private static final Pattern UNESCAPED_SEMICOLONS = Pattern.compile("(?<!\\\\);+");
    private static final Pattern COMMA = Pattern.compile(",");
    private static final Pattern SEMICOLON_OR_COMMA = Pattern.compile("[;,]");

    private static String decodeQuotedPrintable(CharSequence charSequence, String str) {
        char charAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char charAt2 = charSequence.charAt(i);
            if (charAt2 != '\n' && charAt2 != '\r') {
                if (charAt2 != '=') {
                    maybeAppendFragment(byteArrayOutputStream, str, sb);
                    sb.append(charAt2);
                } else if (i < length - 2 && (charAt = charSequence.charAt(i + 1)) != '\r' && charAt != '\n') {
                    i += 2;
                    char charAt3 = charSequence.charAt(i);
                    int parseHexDigit = ResultParser.parseHexDigit(charAt);
                    int parseHexDigit2 = ResultParser.parseHexDigit(charAt3);
                    if (parseHexDigit >= 0 && parseHexDigit2 >= 0) {
                        byteArrayOutputStream.write((parseHexDigit << 4) + parseHexDigit2);
                    }
                }
            }
            i++;
        }
        maybeAppendFragment(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    private static void formatNames(Iterable<List<String>> iterable) {
        int indexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < strArr.length - 1 && (indexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, indexOf);
                    i++;
                    i2 = indexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                maybeAppendComponent(strArr, 3, sb);
                maybeAppendComponent(strArr, 1, sb);
                maybeAppendComponent(strArr, 2, sb);
                maybeAppendComponent(strArr, 0, sb);
                maybeAppendComponent(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    private static boolean isLikeVCardDate(CharSequence charSequence) {
        return charSequence == null || VCARD_LIKE_DATE.matcher(charSequence).matches();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<String> matchSingleVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField(charSequence, str, z, z2);
        if (matchVCardPrefixedField == null || matchVCardPrefixedField.isEmpty()) {
            return null;
        }
        return matchVCardPrefixedField.get(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<List<String>> matchVCardPrefixedField(CharSequence charSequence, String str, boolean z, boolean z2) {
        ArrayList arrayList;
        int i;
        String str2;
        String str3;
        int indexOf;
        int i2;
        int i3;
        String replaceAll;
        int length = str.length();
        int i4 = 0;
        int i5 = 0;
        ArrayList arrayList2 = null;
        while (i5 < length) {
            int i6 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i5 > 0) {
                i5--;
            }
            if (!matcher.find(i5)) {
                break;
            }
            int end = matcher.end(i4);
            String group = matcher.group(1);
            if (group != null) {
                String[] split = SEMICOLON.split(group);
                int length2 = split.length;
                int i7 = i4;
                i = i7;
                arrayList = null;
                str2 = null;
                str3 = null;
                while (i7 < length2) {
                    String str4 = split[i7];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str4);
                    String[] split2 = EQUALS.split(str4, i6);
                    if (split2.length > 1) {
                        String str5 = split2[0];
                        String str6 = split2[1];
                        if ("ENCODING".equalsIgnoreCase(str5) && "QUOTED-PRINTABLE".equalsIgnoreCase(str6)) {
                            i = 1;
                        } else if ("CHARSET".equalsIgnoreCase(str5)) {
                            str2 = str6;
                        } else if ("VALUE".equalsIgnoreCase(str5)) {
                            str3 = str6;
                        }
                    }
                    i7++;
                    i6 = 2;
                }
            } else {
                arrayList = null;
                i = 0;
                str2 = null;
                str3 = null;
            }
            int i8 = end;
            while (true) {
                indexOf = str.indexOf(10, i8);
                if (indexOf < 0) {
                    break;
                }
                if (indexOf < str.length() - 1) {
                    int i9 = indexOf + 1;
                    if (str.charAt(i9) == ' ' || str.charAt(i9) == '\t') {
                        i8 = indexOf + 2;
                    }
                }
                if (i == 0) {
                    break;
                }
                if (indexOf >= 1) {
                    if (str.charAt(indexOf - 1) == '=') {
                        i8 = indexOf + 1;
                    }
                }
                if (indexOf < 2) {
                    break;
                }
                if (str.charAt(indexOf - 2) != '=') {
                    break;
                }
                i8 = indexOf + 1;
            }
            if (indexOf < 0) {
                i3 = length;
                i2 = 0;
            } else {
                if (indexOf > end) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    if (indexOf >= 1 && str.charAt(indexOf - 1) == '\r') {
                        indexOf--;
                    }
                    String substring = str.substring(end, indexOf);
                    if (z) {
                        substring = substring.trim();
                    }
                    if (i != 0) {
                        replaceAll = decodeQuotedPrintable(substring, str2);
                        if (z2) {
                            replaceAll = UNESCAPED_SEMICOLONS.matcher(replaceAll).replaceAll("\n").trim();
                        }
                    } else {
                        if (z2) {
                            substring = UNESCAPED_SEMICOLONS.matcher(substring).replaceAll("\n").trim();
                        }
                        replaceAll = VCARD_ESCAPES.matcher(NEWLINE_ESCAPE.matcher(CR_LF_SPACE_TAB.matcher(substring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                    }
                    if ("uri".equals(str3)) {
                        try {
                            replaceAll = URI.create(replaceAll).getSchemeSpecificPart();
                        } catch (IllegalArgumentException unused) {
                        }
                    }
                    if (arrayList == null) {
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(replaceAll);
                        arrayList2.add(arrayList3);
                    } else {
                        i2 = 0;
                        arrayList.add(0, replaceAll);
                        arrayList2.add(arrayList);
                        i3 = indexOf + 1;
                    }
                }
                i2 = 0;
                i3 = indexOf + 1;
            }
            i4 = i2;
            i5 = i3;
        }
        return arrayList2;
    }

    private static void maybeAppendComponent(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] == null || strArr[i].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(Chars.SPACE);
        }
        sb.append(strArr[i]);
    }

    private static void maybeAppendFragment(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    private static String toPrimaryValue(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static String[] toPrimaryValues(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = list.get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private static String[] toTypes(Collection<List<String>> collection) {
        String str;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str2 = list.get(0);
            if (str2 != null && !str2.isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= list.size()) {
                        str = null;
                        break;
                    }
                    String str3 = list.get(i);
                    int indexOf = str3.indexOf(61);
                    if (indexOf < 0) {
                        str = str3;
                        break;
                    } else if ("TYPE".equalsIgnoreCase(str3.substring(0, indexOf))) {
                        str = str3.substring(indexOf + 1);
                        break;
                    } else {
                        i++;
                    }
                }
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse */
    public AddressBookParsedResult mo10122parse(Result result) {
        String massagedText = ResultParser.getMassagedText(result);
        Matcher matcher = BEGIN_VCARD.matcher(massagedText);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> matchVCardPrefixedField = matchVCardPrefixedField("FN", massagedText, true, false);
        if (matchVCardPrefixedField == null) {
            matchVCardPrefixedField = matchVCardPrefixedField("N", massagedText, true, false);
            formatNames(matchVCardPrefixedField);
        }
        List<String> matchSingleVCardPrefixedField = matchSingleVCardPrefixedField("NICKNAME", massagedText, true, false);
        String[] split = matchSingleVCardPrefixedField == null ? null : COMMA.split(matchSingleVCardPrefixedField.get(0));
        List<List<String>> matchVCardPrefixedField2 = matchVCardPrefixedField("TEL", massagedText, true, false);
        List<List<String>> matchVCardPrefixedField3 = matchVCardPrefixedField("EMAIL", massagedText, true, false);
        List<String> matchSingleVCardPrefixedField2 = matchSingleVCardPrefixedField("NOTE", massagedText, false, false);
        List<List<String>> matchVCardPrefixedField4 = matchVCardPrefixedField("ADR", massagedText, true, true);
        List<String> matchSingleVCardPrefixedField3 = matchSingleVCardPrefixedField("ORG", massagedText, true, true);
        List<String> matchSingleVCardPrefixedField4 = matchSingleVCardPrefixedField("BDAY", massagedText, true, false);
        List<String> list = (matchSingleVCardPrefixedField4 == null || isLikeVCardDate(matchSingleVCardPrefixedField4.get(0))) ? matchSingleVCardPrefixedField4 : null;
        List<String> matchSingleVCardPrefixedField5 = matchSingleVCardPrefixedField("TITLE", massagedText, true, false);
        List<List<String>> matchVCardPrefixedField5 = matchVCardPrefixedField("URL", massagedText, true, false);
        List<String> matchSingleVCardPrefixedField6 = matchSingleVCardPrefixedField("IMPP", massagedText, true, false);
        List<String> matchSingleVCardPrefixedField7 = matchSingleVCardPrefixedField("GEO", massagedText, true, false);
        String[] split2 = matchSingleVCardPrefixedField7 == null ? null : SEMICOLON_OR_COMMA.split(matchSingleVCardPrefixedField7.get(0));
        return new AddressBookParsedResult(toPrimaryValues(matchVCardPrefixedField), split, null, toPrimaryValues(matchVCardPrefixedField2), toTypes(matchVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField3), toTypes(matchVCardPrefixedField3), toPrimaryValue(matchSingleVCardPrefixedField6), toPrimaryValue(matchSingleVCardPrefixedField2), toPrimaryValues(matchVCardPrefixedField4), toTypes(matchVCardPrefixedField4), toPrimaryValue(matchSingleVCardPrefixedField3), toPrimaryValue(list), toPrimaryValue(matchSingleVCardPrefixedField5), toPrimaryValues(matchVCardPrefixedField5), (split2 == null || split2.length == 2) ? split2 : null);
    }
}
