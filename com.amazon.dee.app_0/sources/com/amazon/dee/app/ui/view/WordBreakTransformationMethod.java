package com.amazon.dee.app.ui.view;

import android.text.method.ReplacementTransformationMethod;
import kotlin.text.Typography;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes12.dex */
public final class WordBreakTransformationMethod extends ReplacementTransformationMethod {
    private static WordBreakTransformationMethod instance;
    private static char[] original;
    private static char[] replacement;
    private static char[] hyphenMinus = {'-', 8209};
    private static char[] hyphen = {8208, 8209};
    private static char[] figureDash = {8210, 8209};
    private static char[] enDash = {Typography.ndash, 8209};
    private static char[] emDash = {Typography.mdash, 8209};
    private static char[] horizontalBar = {8213, 8209};
    private static char[] space = {Chars.SPACE, Typography.nbsp};
    private static char[] enSpace = {8194, Typography.nbsp};
    private static char[] emSpace = {8195, Typography.nbsp};
    private static char[] slash = {'/', 8725};

    static {
        char[] cArr = hyphenMinus;
        char[] cArr2 = hyphen;
        char[] cArr3 = figureDash;
        char[] cArr4 = enDash;
        char[] cArr5 = emDash;
        char[] cArr6 = horizontalBar;
        char[] cArr7 = space;
        char[] cArr8 = enSpace;
        char[] cArr9 = emSpace;
        char[] cArr10 = slash;
        original = new char[]{cArr[0], cArr2[0], cArr3[0], cArr4[0], cArr5[0], cArr6[0], cArr7[0], cArr8[0], cArr9[0], cArr10[0]};
        replacement = new char[]{cArr[1], cArr2[1], cArr3[1], cArr4[1], cArr5[1], cArr6[1], cArr7[1], cArr8[1], cArr9[1], cArr10[1]};
    }

    private WordBreakTransformationMethod() {
    }

    public static WordBreakTransformationMethod getInstance() {
        if (instance == null) {
            instance = new WordBreakTransformationMethod();
        }
        return instance;
    }

    @Override // android.text.method.ReplacementTransformationMethod
    protected char[] getOriginal() {
        return original;
    }

    @Override // android.text.method.ReplacementTransformationMethod
    protected char[] getReplacement() {
        return replacement;
    }
}
