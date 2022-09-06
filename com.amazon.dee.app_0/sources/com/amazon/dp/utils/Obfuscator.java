package com.amazon.dp.utils;
/* loaded from: classes12.dex */
public class Obfuscator {
    public static String obfuscate(String str) {
        return str == null ? "<NULL>" : String.valueOf(str.hashCode());
    }
}
