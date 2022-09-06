package com.airbnb.lottie.parser.moshi;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;

    private JsonScope() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getPath(int i, int[] iArr, String[] strArr, int[] iArr2) {
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104('$');
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            if (i3 == 1 || i3 == 2) {
                outline104.append(JsonReaderKt.BEGIN_LIST);
                outline104.append(iArr2[i2]);
                outline104.append(JsonReaderKt.END_LIST);
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                outline104.append('.');
                if (strArr[i2] != null) {
                    outline104.append(strArr[i2]);
                }
            }
        }
        return outline104.toString();
    }
}
