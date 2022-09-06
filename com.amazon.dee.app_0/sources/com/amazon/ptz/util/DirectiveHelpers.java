package com.amazon.ptz.util;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.UUID;
import lombok.Generated;
/* loaded from: classes13.dex */
public class DirectiveHelpers {
    private static final int CORRELATION_TOKEN_LENGTH = 49;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    private DirectiveHelpers() {
    }

    public static String generateCorrelationToken() {
        return RandomAlphanumericString.getString(49);
    }

    public static UUID generateUUID() {
        return UUID.randomUUID();
    }
}
