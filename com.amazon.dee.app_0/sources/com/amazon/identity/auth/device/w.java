package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class w {
    private static final String TAG = "com.amazon.identity.auth.device.w";
    private static final Map<String, String> bI = new HashMap();

    public String b(String str, gg ggVar) {
        String str2 = bI.get(str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String b = ggVar.b(str, "com.amazon.dcp.sso.property.account.delegateeaccount");
        if (!TextUtils.isEmpty(b)) {
            bI.put(str, b);
        }
        return b;
    }
}
