package com.amazon.identity.auth.device;

import android.net.Uri;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface js {
    byte[] getBody();

    String getHeader(String str);

    Map<String, List<String>> getHeaders();

    String getHttpVerb();

    Uri getUri();

    void setHeader(String str, String str2);
}
