package com.amazon.dee.app.elements;

import androidx.annotation.NonNull;
import com.facebook.react.ReactRootView;
/* loaded from: classes12.dex */
public interface ReactFeatureManager extends ReactHostLifecycle {
    void onClearReactData();

    void onFeatureBackgrounded(@NonNull ReactFeature reactFeature);

    void onFeatureDestroyed(@NonNull ReactFeature reactFeature);

    void onFeatureDestroyed(@NonNull String str);

    void onFeatureForegrounded(@NonNull ReactFeature reactFeature);

    void onFeatureRendered(@NonNull String str);

    ReactRootView onFeatureResumed(@NonNull ReactFeature reactFeature);

    void setOnFeatureRenderedListener(@NonNull ReactFeature reactFeature, @NonNull OnRenderedListener onRenderedListener);
}
