package com.amazon.identity.auth.device;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hs {
    public static <T> Set<T> a(T... tArr) {
        HashSet hashSet = new HashSet();
        hashSet.addAll(Arrays.asList(tArr));
        return hashSet;
    }
}
