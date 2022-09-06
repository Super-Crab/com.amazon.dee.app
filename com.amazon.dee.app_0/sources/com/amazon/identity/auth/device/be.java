package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.framework.IsolatedModeSwitcher;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class be {
    static void a(Context context, Set<String> set) {
        gp gpVar = new gp(context, "bootstrap.sso.authority.signature.store");
        gpVar.fE();
        gpVar.e("bootstrap.sso.authority.signature.array.size", set.size());
        int i = 0;
        for (String str : set) {
            gpVar.U("bootstrap.sso.authority.signature.key.".concat(String.valueOf(i)), str);
            i++;
        }
    }

    public static synchronized void k(Context context) {
        synchronized (be.class) {
            boolean z = new MAPAccountManager(context).getAccount() != null;
            if (IsolatedModeSwitcher.isAppInStaticIsolatedMode(context) && !z && bg.o(context)) {
                m(context);
            }
        }
    }

    public static synchronized Set<String> l(Context context) {
        synchronized (be.class) {
            if (bg.o(context)) {
                return m(context);
            }
            return n(context);
        }
    }

    private static Set<String> m(Context context) {
        ej by = ej.by("AuthoritySignature");
        Set<String> a = new bg(context).a(new bf(context, bj.c(context, context.getPackageName()).iterator().next()), by);
        a(context, a);
        return a;
    }

    static synchronized Set<String> n(Context context) {
        HashSet hashSet;
        synchronized (be.class) {
            hashSet = new HashSet();
            gp gpVar = new gp(context, "bootstrap.sso.authority.signature.store");
            int intValue = gpVar.getIntValue("bootstrap.sso.authority.signature.array.size");
            for (int i = 0; i < intValue; i++) {
                hashSet.add(gpVar.cs("bootstrap.sso.authority.signature.key.".concat(String.valueOf(i))));
            }
        }
        return hashSet;
    }
}
