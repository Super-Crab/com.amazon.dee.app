package com.amazon.deviceevents.com.google.gson.internal;
/* renamed from: com.amazon.deviceevents.com.google.gson.internal.$Gson$Preconditions  reason: invalid class name */
/* loaded from: classes12.dex */
public final class C$Gson$Preconditions {
    public static void checkArgument(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException();
    }

    public static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }
}
