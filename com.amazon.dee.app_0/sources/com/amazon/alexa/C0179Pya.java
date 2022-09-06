package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.ExtendedClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* compiled from: outline */
/* renamed from: com.amazon.alexa.Pya  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0179Pya {
    public static String BIo(StringBuilder sb, Object obj, String str) {
        return GeneratedOutlineSupport1.outline88(sb, obj, str);
    }

    public static String zZm(String str, String str2) {
        return GeneratedOutlineSupport1.outline72(str, str2);
    }

    public static String zZm(StringBuilder sb, long j, String str) {
        return GeneratedOutlineSupport1.outline87(sb, j, str);
    }

    public static String zZm(StringBuilder sb, String str, String str2) {
        return GeneratedOutlineSupport1.outline91(sb, str, str2);
    }

    public static String zZm(StringBuilder sb, boolean z, String str) {
        return GeneratedOutlineSupport1.outline97(sb, z, str);
    }

    public static StringBuilder zZm(String str) {
        return GeneratedOutlineSupport1.outline107(str);
    }

    public static ArrayList zZm(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList zZm(Object obj, Object obj2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static void zZm(ExtendedClient extendedClient, StringBuilder sb, String str, String str2) {
        sb.append(extendedClient.getId());
        sb.append(str);
        Log.i(str2, sb.toString());
    }

    public static void zZm(String str, Object obj) {
        String str2 = str + obj;
    }

    public static void zZm(StringBuilder sb, Object obj, String str) {
        sb.append(obj);
        Log.i(str, sb.toString());
    }
}
