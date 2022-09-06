package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.env.EnvironmentUtils;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ew {
    public static URLConnection c(URL url) throws IOException {
        if (url != null) {
            URLConnection openConnection = url.openConnection();
            EnvironmentUtils.cf();
            ey.a(openConnection);
            return openConnection;
        }
        throw new IllegalArgumentException("Null url is not allowed");
    }
}
