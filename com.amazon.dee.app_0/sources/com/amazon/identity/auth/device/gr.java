package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gr extends gd {
    private static final String TAG = gl.class.getName();
    private final Context mContext;

    public gr(Context context) {
        this.mContext = context;
    }

    private static String cH(String str) {
        return String.format("%s#%s", "com.amazon.identity.auth.device.cookiekeys.namespace", str);
    }

    @Override // com.amazon.identity.auth.device.gd
    protected void a(String str, String str2, String str3, String str4, List<MAPCookie> list) {
        io.e(TAG, "Setting actor cookies is not supported in pre-merge devices, do nothing.");
    }

    @Override // com.amazon.identity.auth.device.gd
    protected List<MAPCookie> c(String str, String str2, String str3, String str4) {
        io.e(TAG, "Getting actor cookies is not supported in pre-merge devices, returning null");
        return null;
    }

    @Override // com.amazon.identity.auth.device.gd
    protected List<MAPCookie> h(final String str, final String str2, final String str3) {
        gp gpVar;
        if (TextUtils.isEmpty(str)) {
            gpVar = new gp(this.mContext, "com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth");
        } else {
            gpVar = new gp(this.mContext, cH(str));
        }
        String cs = gpVar.cs(str2);
        List<MAPCookie> ao = ia.ao(cs, str);
        if (!TextUtils.isEmpty(cs) && !cs.startsWith("[")) {
            final ArrayList arrayList = new ArrayList(ao);
            ji.b(new Runnable() { // from class: com.amazon.identity.auth.device.gr.1
                @Override // java.lang.Runnable
                public void run() {
                    gr.this.a(str, str2, str3, arrayList);
                }
            });
        }
        return ao;
    }

    @Override // com.amazon.identity.auth.device.gd
    public boolean j(Context context, String str) {
        return new gp(context, cH(str)).fE();
    }

    @Override // com.amazon.identity.auth.device.gd
    protected void a(String str, String str2, String str3, List<MAPCookie> list) {
        boolean isEmpty = TextUtils.isEmpty(str);
        String j = ia.j(list);
        if (isEmpty) {
            new gp(this.mContext, "com.amazon.identity.auth.device.cookiekeys.namespace.nonAuth").U(str2, j);
        } else {
            new gp(this.mContext, cH(str)).U(str2, j);
        }
    }

    @Override // com.amazon.identity.auth.device.gd
    public Map<String, String> a(String str, List<MAPCookie> list, String str2) {
        if (list.isEmpty()) {
            io.dm(TAG);
            return Collections.emptyMap();
        }
        a(str, str2, null, list);
        return Collections.emptyMap();
    }
}
