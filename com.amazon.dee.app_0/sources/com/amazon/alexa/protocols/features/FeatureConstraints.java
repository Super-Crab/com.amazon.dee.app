package com.amazon.alexa.protocols.features;

import com.amazon.alexa.identity.api.UserIdentity;
import java.util.Set;
/* loaded from: classes9.dex */
public interface FeatureConstraints {
    default Set<String> apply(UserIdentity userIdentity, String[] strArr) {
        return null;
    }

    default Set<String> apply(String[] strArr) {
        return null;
    }
}
