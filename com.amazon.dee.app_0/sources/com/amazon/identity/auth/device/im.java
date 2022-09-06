package com.amazon.identity.auth.device;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class im {
    private final String bm;
    private final String mKey;
    private final String rm;

    public im(String str) {
        this.rm = gv.W(null, str);
        this.bm = null;
        this.mKey = str;
    }

    public static im dl(String str) {
        String substring;
        String str2;
        int indexOf = str.indexOf(47);
        if (indexOf == -1) {
            str2 = null;
            substring = str;
        } else {
            String substring2 = str.substring(0, indexOf);
            substring = str.substring(indexOf + 1);
            str2 = substring2;
        }
        return new im(str, str2, substring);
    }

    public String gA() {
        return this.rm;
    }

    public im gB() {
        return new im(getKey());
    }

    public String getKey() {
        return this.mKey;
    }

    public String getPackageName() {
        return this.bm;
    }

    private im(String str, String str2, String str3) {
        this.rm = str;
        this.bm = str2;
        this.mKey = str3;
    }
}
