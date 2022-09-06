package com.amazon.alexa.cantilever.utils;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.cantilever.LogConfig;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class HelpFeatureChecker {
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpFeatureChecker.class);
    private final Provider<FeatureServiceV2> featureServiceV2 = ComponentRegistry.getInstance().getLazy(FeatureServiceV2.class);

    @VisibleForTesting
    public boolean checkFeatureAccess(@NonNull String str) {
        return hasFeatureAccess(str);
    }

    public boolean hasAmazonForumAccess() {
        return checkFeatureAccess("CANTILEVER_FORUMS_ANDROID");
    }

    @VisibleForTesting
    public boolean hasFeatureAccess(@NonNull String str) {
        Provider<FeatureServiceV2> provider = this.featureServiceV2;
        if (provider != null && provider.mo10268get() != null) {
            return this.featureServiceV2.mo10268get().hasAccess(str, false);
        }
        Lib.Log.i(TAG, "FeatureServiceV2 cannot be null");
        return false;
    }
}
