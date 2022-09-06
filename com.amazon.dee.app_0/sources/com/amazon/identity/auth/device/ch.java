package com.amazon.identity.auth.device;

import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ch {
    private static final String TAG = "com.amazon.identity.auth.device.ch";

    /* renamed from: if  reason: not valid java name */
    private static volatile ch f1if;
    private final ConcurrentHashMap<String, String> ig = new ConcurrentHashMap<>();

    private ch() {
    }

    public static ch bP() {
        if (f1if == null) {
            f1if = new ch();
        }
        return f1if;
    }

    public void P() {
        io.dm(TAG);
        this.ig.clear();
    }

    public boolean containsKey(String str) {
        return this.ig.containsKey(str);
    }

    public String get(String str) {
        return this.ig.get(str);
    }

    public void put(String str, String str2) {
        this.ig.put(str, str2);
    }
}
