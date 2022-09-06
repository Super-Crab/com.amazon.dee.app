package com.amazon.identity.auth.device;

import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fy {
    public final String directedId;
    public final String displayName;
    public final Map<String, String> ns;

    /* JADX INFO: Access modifiers changed from: package-private */
    public fy(String str, String str2) {
        this(str, str2, new HashMap());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public fy(String str, String str2, Map<String, String> map) {
        this.directedId = str;
        this.displayName = str2;
        this.ns = map;
    }
}
