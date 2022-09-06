package com.amazon.alexa.accessory.frames.provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.identity.api.UserIdentity;
/* loaded from: classes.dex */
public final class FeatureChecker {
    private static final String TAG = "FeatureChecker";

    private FeatureChecker() {
    }

    public static boolean checkFeatureAccess(@NonNull String str) {
        return checkFeatureAccess(DependencyProvider.getIdentityService().getUser(TAG), str);
    }

    public static boolean checkFeatureAccess(@Nullable UserIdentity userIdentity, @NonNull String str) {
        return userIdentity != null && userIdentity.hasFeature(str);
    }
}
