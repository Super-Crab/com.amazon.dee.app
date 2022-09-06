package com.amazon.commscore.api.featureflag;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface FeatureFlag {
    @NonNull
    String getName();

    boolean getValue();

    @NonNull
    FeaturePoolOperation withFeaturePool(@NonNull String str);
}
