package com.amazon.alexa.sharing.util;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import javax.inject.Inject;
/* loaded from: classes10.dex */
public class FeatureServiceUtil {
    public static final String EXTERNAL_SHARE_TO_SHARE_SHEET = "MOBILE_SOCIAL_SHARE_TO_NON_ALEXA";
    public static final String SHARING_COMO_21693_EXTERNAL_LINK_ANDROID = "SHARING_COMO_21693_EXTERNAL_LINK_ANDROID";
    public static final String SHARING_COMO_22247_ANDROID_SHEET = "SHARING_COMO_22247_ANDROID_SHEET";
    public static final String SHARING_DECOUPLING_ANDROID_COMO_33387 = "SHARING_DECOUPLING_ANDROID_COMO_33387";
    FeatureServiceV2 featureServiceV2;

    @Inject
    public FeatureServiceUtil(FeatureServiceV2 featureServiceV2) {
        this.featureServiceV2 = featureServiceV2;
    }

    public boolean hasSharingDecouplingAccess() {
        return this.featureServiceV2.hasAccess("SHARING_DECOUPLING_ANDROID_COMO_33387", false);
    }

    public boolean isExternalShareToShareSheetEnabled() {
        return this.featureServiceV2.hasAccess("MOBILE_SOCIAL_SHARE_TO_NON_ALEXA", false);
    }

    public boolean isLinkSharingEnabled() {
        return this.featureServiceV2.hasAccess("SHARING_COMO_21693_EXTERNAL_LINK_ANDROID", false);
    }

    public boolean isShareSheetEnabled() {
        return this.featureServiceV2.hasAccess("SHARING_COMO_22247_ANDROID_SHEET", false);
    }
}
