package com.amazon.alexa.accessory.transport;

import com.google.common.collect.Sets;
import java.util.Set;
/* loaded from: classes6.dex */
public enum TransportFeature {
    AUTHENTICATION,
    ENCRYPTION,
    REQUIRE_RESPONSE;

    public static boolean isSufficient(Set<TransportFeature> set, Set<TransportFeature> set2) {
        return Sets.difference(set, set2).isEmpty();
    }
}
