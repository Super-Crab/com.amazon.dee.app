package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class fz {
    private final String bP;
    private final Map<String, String> nt;
    private final Map<String, String> nu;
    private final gg w;

    public fz(String str, Map<String, String> map, Map<String, String> map2) {
        this(str, map, map2, null);
    }

    public String ca(String str) {
        gg ggVar;
        String str2 = this.nu.get(str);
        return (str2 != null || (ggVar = this.w) == null) ? str2 : ggVar.z(this.bP, str);
    }

    public String cb(String str) {
        gg ggVar;
        String str2 = this.nt.get(str);
        return (str2 != null || (ggVar = this.w) == null) ? str2 : ggVar.b(this.bP, str);
    }

    public Map<String, String> eP() {
        return this.nu;
    }

    public Map<String, String> eQ() {
        return this.nt;
    }

    public String getDirectedId() {
        return this.bP;
    }

    public void u(String str, String str2) {
        this.nu.put(str, str2);
    }

    public void v(String str, String str2) {
        this.nt.put(str, str2);
    }

    public fz(String str, Map<String, String> map, Map<String, String> map2, gg ggVar) {
        if (!TextUtils.isEmpty(str)) {
            this.bP = str;
            if (map == null) {
                this.nt = new HashMap();
            } else {
                this.nt = map;
            }
            if (map2 == null) {
                this.nu = new HashMap();
            } else {
                this.nu = map2;
            }
            this.w = ggVar;
            return;
        }
        throw new IllegalArgumentException("AccountData directedId cannot be null");
    }
}
