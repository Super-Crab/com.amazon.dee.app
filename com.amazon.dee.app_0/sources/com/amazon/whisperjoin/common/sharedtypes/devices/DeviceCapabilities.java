package com.amazon.whisperjoin.common.sharedtypes.devices;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes13.dex */
public class DeviceCapabilities {
    private static final Set<String> sProductsUsingRTOS;
    private static final Set<String> sProductsWithTokenVendedFromDSS;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("89SM");
        hashSet.add("CbtN");
        sProductsWithTokenVendedFromDSS = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add("ebK3");
        hashSet2.add("Q9Lm");
        sProductsUsingRTOS = Collections.unmodifiableSet(hashSet2);
    }

    public static boolean ifRTOSSdk(String str) {
        return sProductsUsingRTOS.contains(str);
    }

    public static boolean isTokenVendedFromDSS(String str) {
        return sProductsWithTokenVendedFromDSS.contains(str);
    }
}
