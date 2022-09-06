package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.token.MAPCookie;
import java.util.List;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class gd {
    public List<MAPCookie> D(String str, String str2) {
        return h(null, str, str2);
    }

    public abstract Map<String, String> a(String str, List<MAPCookie> list, String str2);

    protected abstract void a(String str, String str2, String str3, String str4, List<MAPCookie> list);

    protected abstract void a(String str, String str2, String str3, List<MAPCookie> list);

    public void a(String str, String str2, List<MAPCookie> list) {
        a(null, str, str2, list);
    }

    public void b(String str, String str2, String str3, List<MAPCookie> list) {
        a(str, str2, str3, list);
    }

    protected abstract List<MAPCookie> c(String str, String str2, String str3, String str4);

    public List<MAPCookie> d(String str, String str2, String str3, String str4) {
        return c(str, str2, str3, str4);
    }

    protected abstract List<MAPCookie> h(String str, String str2, String str3);

    public List<MAPCookie> i(String str, String str2, String str3) {
        return h(str, str2, str3);
    }

    public abstract boolean j(Context context, String str);

    public void b(String str, String str2, String str3, String str4, List<MAPCookie> list) {
        a(str, str2, str3, str4, list);
    }
}
