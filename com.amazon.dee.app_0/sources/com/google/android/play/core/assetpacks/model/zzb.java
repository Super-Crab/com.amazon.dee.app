package com.google.android.play.core.assetpacks.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzb {
    public static String zza(String str, String str2) {
        return GeneratedOutlineSupport1.outline92(new StringBuilder(str.length() + 1 + String.valueOf(str2).length()), str, ":", str2);
    }

    public static String zzb(String str, String str2, String str3) {
        int length = String.valueOf(str2).length();
        StringBuilder sb = new StringBuilder(str.length() + 2 + length + String.valueOf(str3).length());
        GeneratedOutlineSupport1.outline181(sb, str, ":", str2, ":");
        sb.append(str3);
        return sb.toString();
    }
}
