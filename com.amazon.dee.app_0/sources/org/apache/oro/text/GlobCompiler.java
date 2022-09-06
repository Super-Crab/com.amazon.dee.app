package org.apache.oro.text;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.Perl5Compiler;
/* loaded from: classes4.dex */
public final class GlobCompiler implements PatternCompiler {
    public static final int CASE_INSENSITIVE_MASK = 1;
    public static final int DEFAULT_MASK = 0;
    public static final int QUESTION_MATCHES_ZERO_OR_ONE_MASK = 4;
    public static final int READ_ONLY_MASK = 8;
    public static final int STAR_CANNOT_MATCH_NULL_MASK = 2;
    private Perl5Compiler __perl5Compiler = new Perl5Compiler();

    private static boolean __isGlobMetaCharacter(char c) {
        return c == '*' || c == '?' || c == '[' || c == ']';
    }

    private static boolean __isPerl5MetaCharacter(char c) {
        return c == '*' || c == '?' || c == '+' || c == '[' || c == ']' || c == '(' || c == ')' || c == '|' || c == '^' || c == '$' || c == '.' || c == '{' || c == '}' || c == '\\';
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0076, code lost:
        if (r6 != '^') goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String globToPerl5(char[] r9, int r10) {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            int r1 = r9.length
            int r1 = r1 * 2
            r0.<init>(r1)
            r1 = r10 & 4
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L10
            r1 = r3
            goto L11
        L10:
            r1 = r2
        L11:
            r10 = r10 & 2
            if (r10 == 0) goto L17
            r10 = r3
            goto L18
        L17:
            r10 = r2
        L18:
            r4 = r2
            r5 = r4
        L1a:
            int r6 = r9.length
            if (r4 >= r6) goto La1
            char r6 = r9[r4]
            r7 = 42
            if (r6 == r7) goto L8e
            r7 = 63
            if (r6 == r7) goto L83
            r7 = 92
            switch(r6) {
                case 91: goto L60;
                case 92: goto L47;
                case 93: goto L40;
                default: goto L2c;
            }
        L2c:
            if (r5 != 0) goto L39
            char r6 = r9[r4]
            boolean r6 = __isPerl5MetaCharacter(r6)
            if (r6 == 0) goto L39
            r0.append(r7)
        L39:
            char r6 = r9[r4]
        L3b:
            r0.append(r6)
            goto L9e
        L40:
            char r5 = r9[r4]
            r0.append(r5)
            r5 = r2
            goto L9e
        L47:
            r0.append(r7)
            int r6 = r9.length
            int r6 = r6 - r3
            if (r4 != r6) goto L4f
            goto L90
        L4f:
            int r6 = r4 + 1
            char r8 = r9[r6]
            boolean r8 = __isGlobMetaCharacter(r8)
            if (r8 == 0) goto L90
            char r4 = r9[r6]
            r0.append(r4)
            r4 = r6
            goto L9e
        L60:
            char r5 = r9[r4]
            r0.append(r5)
            int r5 = r4 + 1
            int r6 = r9.length
            if (r5 >= r6) goto L81
            char r6 = r9[r5]
            r7 = 33
            r8 = 94
            if (r6 == r7) goto L7d
            r7 = 93
            if (r6 == r7) goto L79
            if (r6 == r8) goto L7d
            goto L81
        L79:
            r0.append(r7)
            goto L80
        L7d:
            r0.append(r8)
        L80:
            r4 = r5
        L81:
            r5 = r3
            goto L9e
        L83:
            if (r5 == 0) goto L86
            goto L90
        L86:
            if (r1 == 0) goto L8b
            java.lang.String r6 = ".?"
            goto L9b
        L8b:
            r6 = 46
            goto L3b
        L8e:
            if (r5 == 0) goto L94
        L90:
            r0.append(r7)
            goto L9e
        L94:
            if (r10 == 0) goto L99
            java.lang.String r6 = ".+"
            goto L9b
        L99:
            java.lang.String r6 = ".*"
        L9b:
            r0.append(r6)
        L9e:
            int r4 = r4 + r3
            goto L1a
        La1:
            java.lang.String r9 = r0.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.oro.text.GlobCompiler.globToPerl5(char[], int):java.lang.String");
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str) throws MalformedPatternException {
        return compile(str.toCharArray(), 0);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(String str, int i) throws MalformedPatternException {
        return compile(str.toCharArray(), i);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(char[] cArr) throws MalformedPatternException {
        return compile(cArr, 0);
    }

    @Override // org.apache.oro.text.regex.PatternCompiler
    public Pattern compile(char[] cArr, int i) throws MalformedPatternException {
        int i2 = (i & 1) != 0 ? 1 : 0;
        if ((i & 8) != 0) {
            i2 |= 32768;
        }
        return this.__perl5Compiler.compile(globToPerl5(cArr, i), i2);
    }
}
