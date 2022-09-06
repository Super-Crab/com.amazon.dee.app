package com.amazon.alexa.protocols.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.UserIdentity;
import java.util.Set;
/* loaded from: classes9.dex */
public interface FeatureFilter {
    default boolean hasAccess(@Nullable UserIdentity userIdentity, @NonNull String str, @NonNull Set<String> set) {
        return false;
    }

    default boolean hasAccess(@NonNull String str, @NonNull Set<String> set) {
        return false;
    }

    @NonNull
    Set<String> targetedFeatures();
}
