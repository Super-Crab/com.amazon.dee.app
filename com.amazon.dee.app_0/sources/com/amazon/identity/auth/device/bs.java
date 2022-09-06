package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bs {
    private static final br hB = new bt(null, null);
    private static br hC = null;
    private static Map<String, br> hD = new HashMap();

    public static synchronized void P() {
        synchronized (bs.class) {
            hD.clear();
        }
    }

    private static boolean a(br brVar) {
        return brVar != null && !brVar.z();
    }

    public static synchronized void aM(String str) {
        synchronized (bs.class) {
            ArrayList<String> arrayList = new ArrayList();
            for (String str2 : hD.keySet()) {
                if (str2.contains(str)) {
                    arrayList.add(str2);
                }
            }
            for (String str3 : arrayList) {
                hD.remove(str3);
            }
        }
    }

    public static synchronized br d(Context context, String str, String str2) {
        br brVar;
        synchronized (bs.class) {
            Context applicationContext = context.getApplicationContext();
            if (!TextUtils.isEmpty(str)) {
                brVar = hD.get(l(str, str2));
            } else {
                brVar = hC;
            }
            if (a(brVar)) {
                return brVar;
            }
            br g = bu.g(applicationContext, str, str2);
            if (g == null) {
                g = new ga(applicationContext).eR();
            }
            if (!TextUtils.isEmpty(str)) {
                hD.put(l(str, str2), g);
            } else {
                hC = g;
            }
            return g;
        }
    }

    public static synchronized br e(Context context, String str, String str2) {
        br f;
        synchronized (bs.class) {
            f = f(context, str, str2);
        }
        return f;
    }

    private static br f(Context context, String str, String str2) {
        AmazonAccountManager amazonAccountManager = new AmazonAccountManager(context);
        if (str != null && !amazonAccountManager.C(str)) {
            String l = l(str, str2);
            br brVar = hD.get(l);
            Context applicationContext = context.getApplicationContext();
            if (a(brVar)) {
                return brVar;
            }
            bu h = bu.h(applicationContext, str, str2);
            if (h == null) {
                return hB;
            }
            hD.put(l, h);
            return h;
        }
        return d(context, str, str2);
    }

    private static String l(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : String.format("%s/%s", str2, str);
    }

    public static br r(Context context) {
        return d(context, null, null);
    }

    public static synchronized br d(Context context, String str) {
        br f;
        synchronized (bs.class) {
            f = f(context, str, null);
        }
        return f;
    }
}
