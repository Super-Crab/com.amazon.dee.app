package org.apache.oro.text.regex;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.ThreadConfined;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes4.dex */
public final class Perl5Debug {
    private Perl5Debug() {
    }

    static void _printOperator(char[] cArr, int i, StringBuffer stringBuffer) {
        String str;
        String str2;
        String str3;
        stringBuffer.append(":");
        switch (cArr[i]) {
            case 0:
                str = "END";
                break;
            case 1:
                str = "BOL";
                break;
            case 2:
                str = "MBOL";
                break;
            case 3:
                str = "SBOL";
                break;
            case 4:
                str = "EOL";
                break;
            case 5:
                str = "MEOL";
                break;
            case 6:
            case '%':
            case '/':
            case '0':
            case '1':
            default:
                stringBuffer.append("Operator is unrecognized.  Faulty expression code!");
                str = null;
                break;
            case 7:
                str = ThreadConfined.ANY;
                break;
            case '\b':
                str = "SANY";
                break;
            case '\t':
                str = "ANYOF";
                break;
            case '\n':
                str2 = "CURLY {";
                stringBuffer.append(str2);
                stringBuffer.append((int) OpCode._getArg1(cArr, i));
                stringBuffer.append(JsonReaderKt.COMMA);
                stringBuffer.append((int) OpCode._getArg2(cArr, i));
                stringBuffer.append(JsonReaderKt.END_OBJ);
                str = null;
                break;
            case 11:
                str2 = "CURLYX {";
                stringBuffer.append(str2);
                stringBuffer.append((int) OpCode._getArg1(cArr, i));
                stringBuffer.append(JsonReaderKt.COMMA);
                stringBuffer.append((int) OpCode._getArg2(cArr, i));
                stringBuffer.append(JsonReaderKt.END_OBJ);
                str = null;
                break;
            case '\f':
                str = "BRANCH";
                break;
            case '\r':
                str = "BACK";
                break;
            case 14:
                str = "EXACTLY";
                break;
            case 15:
                str = "NOTHING";
                break;
            case 16:
                str = "STAR";
                break;
            case 17:
                str = "PLUS";
                break;
            case 18:
                str = "ALNUM";
                break;
            case 19:
                str = "NALNUM";
                break;
            case 20:
                str = "BOUND";
                break;
            case 21:
                str = "NBOUND";
                break;
            case 22:
                str = "SPACE";
                break;
            case 23:
                str = "NSPACE";
                break;
            case 24:
                str = "DIGIT";
                break;
            case 25:
                str = "NDIGIT";
                break;
            case 26:
                str3 = "REF";
                stringBuffer.append(str3);
                stringBuffer.append((int) OpCode._getArg1(cArr, i));
                str = null;
                break;
            case 27:
                str3 = "OPEN";
                stringBuffer.append(str3);
                stringBuffer.append((int) OpCode._getArg1(cArr, i));
                str = null;
                break;
            case 28:
                str3 = "CLOSE";
                stringBuffer.append(str3);
                stringBuffer.append((int) OpCode._getArg1(cArr, i));
                str = null;
                break;
            case 29:
                str = "MINMOD";
                break;
            case 30:
                str = "GBOL";
                break;
            case 31:
                str = "IFMATCH";
                break;
            case ' ':
                str = "UNLESSM";
                break;
            case '!':
                str = "SUCCEED";
                break;
            case '\"':
                str = "WHILEM";
                break;
            case '#':
                str = "ANYOFUN";
                break;
            case '$':
                str = "NANYOFUN";
                break;
            case '&':
                str = "ALPHA";
                break;
            case '\'':
                str = "BLANK";
                break;
            case '(':
                str = "CNTRL";
                break;
            case ')':
                str = "GRAPH";
                break;
            case '*':
                str = "LOWER";
                break;
            case '+':
                str = "PRINT";
                break;
            case ',':
                str = "PUNCT";
                break;
            case '-':
                str = "UPPER";
                break;
            case '.':
                str = "XDIGIT";
                break;
            case '2':
                str = "ALNUMC";
                break;
            case '3':
                str = "ASCII";
                break;
        }
        if (str != null) {
            stringBuffer.append(str);
        }
    }

    public static String printProgram(Perl5Pattern perl5Pattern) {
        char[] cArr = perl5Pattern._program;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        char c = 27;
        while (c != 0) {
            c = cArr[i];
            stringBuffer.append(i);
            _printOperator(cArr, i, stringBuffer);
            int _getNext = OpCode._getNext(cArr, i);
            int i2 = i + OpCode._operandLength[c];
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("(");
            stringBuffer2.append(_getNext);
            stringBuffer2.append(")");
            stringBuffer.append(stringBuffer2.toString());
            i = i2 + 2;
            if (c == '\t') {
                i += 16;
            } else {
                if (c == '#' || c == '$') {
                    while (cArr[i] != 0) {
                        i = cArr[i] == '%' ? i + 3 : i + 2;
                    }
                } else if (c == 14) {
                    i++;
                    stringBuffer.append(" <");
                    while (cArr[i] != 65535) {
                        stringBuffer.append(cArr[i]);
                        i++;
                    }
                    stringBuffer.append(Config.Compare.GREATER_THAN);
                }
                i++;
            }
            stringBuffer.append('\n');
        }
        if (perl5Pattern._startString != null) {
            StringBuffer outline103 = GeneratedOutlineSupport1.outline103("start `");
            outline103.append(new String(perl5Pattern._startString));
            outline103.append("' ");
            stringBuffer.append(outline103.toString());
        }
        if (perl5Pattern._startClassOffset != -1) {
            stringBuffer.append("stclass `");
            _printOperator(cArr, perl5Pattern._startClassOffset, stringBuffer);
            stringBuffer.append("' ");
        }
        if ((perl5Pattern._anchor & 3) != 0) {
            stringBuffer.append("anchored ");
        }
        if ((perl5Pattern._anchor & 4) != 0) {
            stringBuffer.append("plus ");
        }
        if ((perl5Pattern._anchor & 8) != 0) {
            stringBuffer.append("implicit ");
        }
        if (perl5Pattern._mustString != null) {
            StringBuffer outline1032 = GeneratedOutlineSupport1.outline103("must have \"");
            outline1032.append(new String(perl5Pattern._mustString));
            outline1032.append("\" back ");
            outline1032.append(perl5Pattern._back);
            outline1032.append(" ");
            stringBuffer.append(outline1032.toString());
        }
        StringBuffer outline1033 = GeneratedOutlineSupport1.outline103("minlen ");
        outline1033.append(perl5Pattern._minLength);
        outline1033.append('\n');
        stringBuffer.append(outline1033.toString());
        return stringBuffer.toString();
    }
}
