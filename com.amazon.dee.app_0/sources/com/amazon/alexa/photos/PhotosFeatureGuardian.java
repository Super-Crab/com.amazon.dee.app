package com.amazon.alexa.photos;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.service.api.LazyComponent;
/* loaded from: classes9.dex */
public class PhotosFeatureGuardian {
    private static final String TAG = "PhotosFeatureGuardian";
    private final LazyComponent<FeatureServiceV2> featureServiceV2Lazy;

    public PhotosFeatureGuardian(LazyComponent<FeatureServiceV2> lazyComponent) {
        this.featureServiceV2Lazy = lazyComponent;
    }

    public boolean isAutosaveFeatureAvailable() {
        FeatureServiceV2 mo10268get = this.featureServiceV2Lazy.mo10268get();
        return mo10268get != null && mo10268get.hasAccess("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID", false);
    }

    public boolean isManualUploadFeatureAvailable() {
        FeatureServiceV2 mo10268get = this.featureServiceV2Lazy.mo10268get();
        if (mo10268get != null) {
            return mo10268get.hasAccess("AMAZON_PHOTOS_ALEXA_APP_MANUAL_UPLOAD_ANDROID", false) || mo10268get.hasAccess("AMAZON_PHOTOS_ALEXA_APP_AUTO_SAVE_ANDROID", false);
        }
        return false;
    }

    public boolean isUploaderV2Available() {
        return isManualUploadFeatureAvailable();
    }
}
