package androidx.core.view.inputmethod;

import android.os.Build;
import android.view.inputmethod.EditorInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class EditorInfoCompat {
    private static final String CONTENT_MIME_TYPES_INTEROP_KEY = "android.support.v13.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String CONTENT_MIME_TYPES_KEY = "androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_MIME_TYPES";
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int IME_FLAG_FORCE_ASCII = Integer.MIN_VALUE;
    public static final int IME_FLAG_NO_PERSONALIZED_LEARNING = 16777216;

    @NonNull
    public static String[] getContentMimeTypes(EditorInfo editorInfo) {
        int i = Build.VERSION.SDK_INT;
        String[] strArr = editorInfo.contentMimeTypes;
        return strArr != null ? strArr : EMPTY_STRING_ARRAY;
    }

    static int getProtocol(EditorInfo editorInfo) {
        int i = Build.VERSION.SDK_INT;
        return 1;
    }

    public static void setContentMimeTypes(@NonNull EditorInfo editorInfo, @Nullable String[] strArr) {
        int i = Build.VERSION.SDK_INT;
        editorInfo.contentMimeTypes = strArr;
    }
}
