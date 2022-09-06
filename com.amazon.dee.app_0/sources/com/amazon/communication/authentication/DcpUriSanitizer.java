package com.amazon.communication.authentication;

import android.net.Uri;
import com.amazon.dp.logger.DPLogger;
/* loaded from: classes12.dex */
class DcpUriSanitizer {
    private static final String DEVICE_EVENT_PROXY = "DeviceEventProxy";
    private static final DPLogger log = new DPLogger("TComm.DcpUriUtil");
    private static final Uri FAKE_URI = Uri.parse("https://www.amazon.com/workAroundDcpBlackList");

    DcpUriSanitizer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Uri sanitizeUriForDcp(Uri uri) {
        if (uri == null) {
            return null;
        }
        boolean z = false;
        if (uri.getPath().contains(DEVICE_EVENT_PROXY)) {
            log.info("sanitizeUriForDcp", "Using fake URI to work-around DCP blacklist.", new Object[0]);
            return FAKE_URI;
        }
        String path = uri.getPath();
        if (path == null || path.trim().length() == 0) {
            log.debug("signRequest", "No path or null path in URI, appending / as a workaround", new Object[0]);
            z = true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(uri.toString().replace("ws://", "http://").replace("wss://", "https://"));
        sb.append(z ? "/" : "");
        return Uri.parse(sb.toString());
    }
}
