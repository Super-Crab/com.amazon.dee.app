package com.amazon.commscore.api.featureflag;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public interface FeaturePoolOperation {
    @NonNull
    FeaturePoolOperation and(@NonNull String str);

    boolean getValue();
}
