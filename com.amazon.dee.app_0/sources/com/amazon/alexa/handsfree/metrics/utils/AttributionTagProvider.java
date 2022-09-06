package com.amazon.alexa.handsfree.metrics.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.preload.attribution.AttributionTagListener;
import com.amazon.alexa.preload.attribution.DefaultPreloadAttributionManager;
import com.amazon.alexa.preload.attribution.FeatureQueryBridge;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class AttributionTagProvider implements AttributionTagListener {
    private static AttributionTagProvider sInstance;
    private boolean mComputedAttributionTag = false;
    private final List<AttributionTagListener> mListeners = new ArrayList();
    private PreloadAttributionManager mPreloadAttributionManager;

    private AttributionTagProvider() {
    }

    private void callListeners() {
        for (AttributionTagListener attributionTagListener : this.mListeners) {
            attributionTagListener.onAttributionTagComputed();
        }
        this.mListeners.clear();
    }

    public static synchronized AttributionTagProvider getInstance() {
        AttributionTagProvider attributionTagProvider;
        synchronized (AttributionTagProvider.class) {
            if (sInstance == null) {
                sInstance = new AttributionTagProvider();
            }
            attributionTagProvider = sInstance;
        }
        return attributionTagProvider;
    }

    public void addListener(@NonNull AttributionTagListener attributionTagListener) {
        if (this.mComputedAttributionTag) {
            attributionTagListener.onAttributionTagComputed();
        } else {
            this.mListeners.add(attributionTagListener);
        }
    }

    @Nullable
    public String getAttributionTag() {
        return this.mPreloadAttributionManager.getAttributionTag();
    }

    public boolean hasComputedAttributionTag() {
        return this.mComputedAttributionTag;
    }

    public void initialize(@NonNull Context context) {
        this.mPreloadAttributionManager = new DefaultPreloadAttributionManager(context, this, new FeatureQueryBridge() { // from class: com.amazon.alexa.handsfree.metrics.utils.AttributionTagProvider.1
            private final FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline21(FeatureServiceV2.class);

            @Override // com.amazon.alexa.preload.attribution.FeatureQueryBridge
            public boolean isActive(String str) {
                FeatureServiceV2 featureServiceV2 = this.featureServiceV2;
                if (featureServiceV2 == null) {
                    return false;
                }
                return featureServiceV2.hasAccess(str, false);
            }
        });
    }

    @Override // com.amazon.alexa.preload.attribution.AttributionTagListener
    public void onAttributionTagComputed() {
        this.mComputedAttributionTag = true;
        callListeners();
    }

    @VisibleForTesting
    AttributionTagProvider(@NonNull PreloadAttributionManager preloadAttributionManager) {
        this.mPreloadAttributionManager = preloadAttributionManager;
    }
}
