package com.amazon.deecomms.RTT;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.nio.charset.StandardCharsets;
/* loaded from: classes12.dex */
public final class T140Encoding {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentityManager.class);

    private T140Encoding() {
    }

    public static String decode(byte[] bArr) {
        String str = new String(bArr, StandardCharsets.UTF_8);
        GeneratedOutlineSupport1.outline159("Deocoded string", str, LOG);
        return str;
    }

    public static byte[] encode(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        sb.append(charSequence);
        return sb.toString().getBytes(StandardCharsets.UTF_8);
    }
}
