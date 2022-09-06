package com.google.zxing.client.result;

import com.amazon.tarazed.core.logging.TarazedLogFormatter;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes3.dex */
public final class ExpandedProductResultParser extends ResultParser {
    private static String findAIvalue(int i, String str) {
        if (str.charAt(i) != '(') {
            return null;
        }
        String substring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == ')') {
                return sb.toString();
            }
            if (charAt < '0' || charAt > '9') {
                return null;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(i);
        for (int i2 = 0; i2 < substring.length(); i2++) {
            char charAt = substring.charAt(i2);
            if (charAt == '(') {
                if (findAIvalue(i2, substring) != null) {
                    break;
                }
                sb.append('(');
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse */
    public ExpandedProductParsedResult mo10122parse(Result result) {
        char c;
        String substring;
        String str;
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        HashMap hashMap = new HashMap();
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
        int i = 0;
        while (i < massagedText.length()) {
            String findAIvalue = findAIvalue(i, massagedText);
            if (findAIvalue == null) {
                return expandedProductParsedResult;
            }
            int length = findAIvalue.length() + 2 + i;
            String findValue = findValue(length, massagedText);
            int length2 = findValue.length() + length;
            int hashCode = findAIvalue.hashCode();
            String str15 = str12;
            String str16 = str11;
            if (hashCode == 1536) {
                if (findAIvalue.equals("00")) {
                    c = 0;
                }
                c = 65535;
            } else if (hashCode == 1537) {
                if (findAIvalue.equals("01")) {
                    c = 1;
                }
                c = 65535;
            } else if (hashCode == 1567) {
                if (findAIvalue.equals("10")) {
                    c = 2;
                }
                c = 65535;
            } else if (hashCode == 1568) {
                if (findAIvalue.equals("11")) {
                    c = 3;
                }
                c = 65535;
            } else if (hashCode == 1570) {
                if (findAIvalue.equals("13")) {
                    c = 4;
                }
                c = 65535;
            } else if (hashCode == 1572) {
                if (findAIvalue.equals("15")) {
                    c = 5;
                }
                c = 65535;
            } else if (hashCode != 1574) {
                switch (hashCode) {
                    case 1567966:
                        if (findAIvalue.equals("3100")) {
                            c = 7;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567967:
                        if (findAIvalue.equals("3101")) {
                            c = '\b';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567968:
                        if (findAIvalue.equals("3102")) {
                            c = '\t';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567969:
                        if (findAIvalue.equals("3103")) {
                            c = '\n';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567970:
                        if (findAIvalue.equals("3104")) {
                            c = 11;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567971:
                        if (findAIvalue.equals("3105")) {
                            c = '\f';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567972:
                        if (findAIvalue.equals("3106")) {
                            c = '\r';
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567973:
                        if (findAIvalue.equals("3107")) {
                            c = 14;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567974:
                        if (findAIvalue.equals("3108")) {
                            c = 15;
                            break;
                        }
                        c = 65535;
                        break;
                    case 1567975:
                        if (findAIvalue.equals("3109")) {
                            c = 16;
                            break;
                        }
                        c = 65535;
                        break;
                    default:
                        switch (hashCode) {
                            case 1568927:
                                if (findAIvalue.equals("3200")) {
                                    c = 17;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568928:
                                if (findAIvalue.equals("3201")) {
                                    c = 18;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568929:
                                if (findAIvalue.equals("3202")) {
                                    c = 19;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568930:
                                if (findAIvalue.equals("3203")) {
                                    c = 20;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568931:
                                if (findAIvalue.equals("3204")) {
                                    c = 21;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568932:
                                if (findAIvalue.equals("3205")) {
                                    c = 22;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568933:
                                if (findAIvalue.equals("3206")) {
                                    c = 23;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568934:
                                if (findAIvalue.equals("3207")) {
                                    c = 24;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568935:
                                if (findAIvalue.equals("3208")) {
                                    c = 25;
                                    break;
                                }
                                c = 65535;
                                break;
                            case 1568936:
                                if (findAIvalue.equals("3209")) {
                                    c = 26;
                                    break;
                                }
                                c = 65535;
                                break;
                            default:
                                switch (hashCode) {
                                    case 1575716:
                                        if (findAIvalue.equals("3920")) {
                                            c = 27;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1575717:
                                        if (findAIvalue.equals("3921")) {
                                            c = TarazedLogFormatter.FILE_SEPARATOR;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1575718:
                                        if (findAIvalue.equals("3922")) {
                                            c = 29;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    case 1575719:
                                        if (findAIvalue.equals("3923")) {
                                            c = 30;
                                            break;
                                        }
                                        c = 65535;
                                        break;
                                    default:
                                        switch (hashCode) {
                                            case 1575747:
                                                if (findAIvalue.equals("3930")) {
                                                    c = 31;
                                                    break;
                                                }
                                                c = 65535;
                                                break;
                                            case 1575748:
                                                if (findAIvalue.equals("3931")) {
                                                    c = Chars.SPACE;
                                                    break;
                                                }
                                                c = 65535;
                                                break;
                                            case 1575749:
                                                if (findAIvalue.equals("3932")) {
                                                    c = '!';
                                                    break;
                                                }
                                                c = 65535;
                                                break;
                                            case 1575750:
                                                if (findAIvalue.equals("3933")) {
                                                    c = '\"';
                                                    break;
                                                }
                                                c = 65535;
                                                break;
                                            default:
                                                c = 65535;
                                                break;
                                        }
                                }
                        }
                }
            } else {
                if (findAIvalue.equals("17")) {
                    c = 6;
                }
                c = 65535;
            }
            switch (c) {
                case 0:
                    str3 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 1:
                    str2 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 2:
                    str4 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 3:
                    str5 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 4:
                    str6 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 5:
                    str7 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 6:
                    str8 = findValue;
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 7:
                case '\b':
                case '\t':
                case '\n':
                case 11:
                case '\f':
                case '\r':
                case 14:
                case 15:
                case 16:
                    substring = findAIvalue.substring(3);
                    str = ExpandedProductParsedResult.KILOGRAM;
                    str11 = substring;
                    str10 = str;
                    str12 = str15;
                    str9 = findValue;
                    i = length2;
                    expandedProductParsedResult = null;
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                    substring = findAIvalue.substring(3);
                    str = ExpandedProductParsedResult.POUND;
                    str11 = substring;
                    str10 = str;
                    str12 = str15;
                    str9 = findValue;
                    i = length2;
                    expandedProductParsedResult = null;
                case 27:
                case 28:
                case 29:
                case 30:
                    str13 = findAIvalue.substring(3);
                    str12 = findValue;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                case 31:
                case ' ':
                case '!':
                case '\"':
                    if (findValue.length() < 4) {
                        return null;
                    }
                    str12 = findValue.substring(3);
                    String substring2 = findValue.substring(0, 3);
                    str13 = findAIvalue.substring(3);
                    str14 = substring2;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
                default:
                    hashMap.put(findAIvalue, findValue);
                    str12 = str15;
                    str11 = str16;
                    i = length2;
                    expandedProductParsedResult = null;
            }
        }
        return new ExpandedProductParsedResult(massagedText, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, hashMap);
    }
}
