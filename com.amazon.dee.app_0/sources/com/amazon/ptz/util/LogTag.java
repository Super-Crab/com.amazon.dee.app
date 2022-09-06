package com.amazon.ptz.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes13.dex */
public class LogTag {
    private static final String TAG_PREFIX = "PtzLibrary.";

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private LogTag() {
    }

    public static String forClass(Class<?> cls) {
        return GeneratedOutlineSupport1.outline39(cls, GeneratedOutlineSupport1.outline107(TAG_PREFIX));
    }
}
