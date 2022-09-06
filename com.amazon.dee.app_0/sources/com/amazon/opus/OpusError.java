package com.amazon.opus;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class OpusError extends RuntimeException {
    public OpusError() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int throwIfError(int i) {
        if (i >= 0) {
            return i;
        }
        throw new OpusError(GeneratedOutlineSupport1.outline49("Error from codec: ", i));
    }

    public OpusError(String str) {
        super(str);
    }
}
