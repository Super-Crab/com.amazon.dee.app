package amazon.speech.util;

import android.text.TextUtils;
/* loaded from: classes.dex */
public class TextUtilsWrapper {
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || str == null;
    }
}
