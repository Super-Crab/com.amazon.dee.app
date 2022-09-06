package com.amazon.identity.auth.device;

import android.accounts.Account;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class gg {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public interface a {
        void onSuccess();
    }

    private String ck(String str) {
        return "actor/".concat(String.valueOf(str));
    }

    public abstract void B(String str, String str2);

    public abstract String C(String str, String str2);

    public boolean D(String str) {
        return getAccounts().contains(str);
    }

    public String E(String str, String str2) {
        return b(str, ck(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String F(String str, String str2) {
        return "actor_data/" + str + str2;
    }

    public abstract void G(String str);

    public Map<String, String> a(String str, List<String> list) {
        HashMap hashMap = new HashMap();
        for (String str2 : list) {
            hashMap.put(str2, C(str, str2));
        }
        return hashMap;
    }

    public abstract void a(fz fzVar);

    public abstract void a(String str, String str2, String str3);

    public abstract boolean a(String str, fz fzVar, a aVar);

    public abstract boolean a(String str, fz fzVar, a aVar, List<String> list);

    public abstract String b(String str, String str2);

    public void c(String str, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            g(str, entry.getKey(), entry.getValue());
        }
    }

    public abstract Set<String> cc(String str);

    public abstract Account ce(String str);

    public abstract Set<String> cf(String str);

    public void d(String str, Map<String, String> map) {
        a(new fz(str, null, map));
    }

    public void e(String str, String str2, String str3, String str4) {
        a(str, F(str2, str3), str4);
    }

    public abstract void eS();

    public abstract Set<String> eT();

    @Deprecated
    public abstract void f(String str, String str2, String str3);

    public abstract void g(String str, String str2, String str3);

    public abstract Set<String> getAccounts();

    public abstract String getDeviceSnapshot();

    public abstract void initialize();

    public void j(String str, String str2, String str3) {
        a(str, ck(str3), str2);
    }

    public String k(String str, String str2, String str3) {
        return b(str, F(str2, str3));
    }

    public abstract void setup();

    public abstract String z(String str, String str2);
}
