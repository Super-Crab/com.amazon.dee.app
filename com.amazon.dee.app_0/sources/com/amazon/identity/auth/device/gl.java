package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gl extends gd {
    private static final String TAG = "com.amazon.identity.auth.device.gl";
    private final gg w;

    public gl(Context context) {
        this(ed.M(context).dV());
    }

    static String I(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return String.format("%s.%s", AccountConstants.TOKEN_TYPE_COOKIES, str);
        }
        return String.format("%s.%s#%s", AccountConstants.TOKEN_TYPE_COOKIES, str, str2);
    }

    static String J(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return String.format("%s.json.%s", AccountConstants.TOKEN_TYPE_COOKIES, str);
        }
        return String.format("%s.json.%s#%s", AccountConstants.TOKEN_TYPE_COOKIES, str, str2);
    }

    static String K(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "json.".concat(String.valueOf(str));
        }
        return String.format("json.%s#%s", str, str2);
    }

    static String L(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : String.format("%s#%s", str, str2);
    }

    private static Map<String, String> b(String str, String str2, List<MAPCookie> list) {
        HashMap hashMap = new HashMap();
        hashMap.put(I(str, str2), ia.h(list));
        hashMap.put(J(str, str2), ia.j(list));
        return hashMap;
    }

    static String l(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return String.format("%s.json.%s.%s", AccountConstants.TOKEN_TYPE_ACTOR_COOKIES, str2, str);
        }
        return String.format("%s.json.%s.%s#%s", AccountConstants.TOKEN_TYPE_ACTOR_COOKIES, str2, str, str3);
    }

    @Override // com.amazon.identity.auth.device.gd
    public Map<String, String> a(String str, List<MAPCookie> list, String str2) {
        if (list.isEmpty()) {
            io.dm(TAG);
            return Collections.emptyMap();
        }
        return b(str2, null, list);
    }

    @Override // com.amazon.identity.auth.device.gd
    protected List<MAPCookie> c(String str, String str2, String str3, String str4) {
        return ia.aq(this.w.z(str, l(str2, str3, str4)), str);
    }

    @Override // com.amazon.identity.auth.device.gd
    protected List<MAPCookie> h(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            List<MAPCookie> ap = ia.ap(this.w.C("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", L(str2, str3)), null);
            if (!hz.isEmpty(ap)) {
                return ap;
            }
            List<MAPCookie> aq = ia.aq(this.w.C("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", K(str2, str3)), null);
            c(str, str2, str3, aq);
            return aq;
        }
        List<MAPCookie> ap2 = ia.ap(this.w.z(str, I(str2, str3)), str);
        if (!hz.isEmpty(ap2)) {
            return ap2;
        }
        List<MAPCookie> aq2 = ia.aq(this.w.z(str, J(str2, str3)), str);
        c(str, str2, str3, aq2);
        return aq2;
    }

    @Override // com.amazon.identity.auth.device.gd
    public boolean j(Context context, String str) {
        throw new IllegalStateException("Clear cookies is not supported");
    }

    public gl(gg ggVar) {
        this.w = ggVar;
    }

    private void c(final String str, final String str2, final String str3, List<MAPCookie> list) {
        if (!hz.isEmpty(list)) {
            final ArrayList arrayList = new ArrayList(list);
            ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.gl.1
                @Override // java.lang.Runnable
                public void run() {
                    gl.this.a(str, str2, str3, arrayList);
                }
            });
        }
    }

    @Override // com.amazon.identity.auth.device.gd
    protected void a(String str, String str2, String str3, List<MAPCookie> list) {
        if (TextUtils.isEmpty(str)) {
            HashMap hashMap = new HashMap();
            hashMap.put(L(str2, str3), ia.h(list));
            hashMap.put(K(str2, str3), ia.j(list));
            this.w.c("com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth", hashMap);
            return;
        }
        this.w.d(str, b(str2, str3, list));
    }

    @Override // com.amazon.identity.auth.device.gd
    protected void a(String str, String str2, String str3, String str4, List<MAPCookie> list) {
        gg ggVar = this.w;
        HashMap hashMap = new HashMap();
        hashMap.put(l(str2, str3, str4), ia.j(list));
        ggVar.d(str, hashMap);
    }
}
