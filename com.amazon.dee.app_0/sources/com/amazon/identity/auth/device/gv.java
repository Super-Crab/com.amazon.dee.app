package com.amazon.identity.auth.device;

import android.content.Context;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class gv {

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a {
        private final String bk;
        private final String pc;
        private final String pd;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(String str, String str2, String str3) {
            this.bk = str;
            this.pc = str2;
            this.pd = str3;
        }

        public String fL() {
            return this.pd;
        }

        public String getDeviceType() {
            return this.bk;
        }
    }

    public static String W(String str, String str2) {
        return str == null ? str2 : String.format("%s/%s", str, str2);
    }

    public static String X(String str, String str2) {
        return GeneratedOutlineSupport1.outline75(str, "/", str2);
    }

    public static String i(Context context, String str, String str2) {
        return ie.p(context, str) ? str2 : GeneratedOutlineSupport1.outline75(str, "/", str2);
    }
}
