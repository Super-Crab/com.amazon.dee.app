package com.amazon.alexa.voice.handsfree.features;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.handsfree.protocols.features.FeatureChecker;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class UserIdentityFeatureChecker implements FeatureChecker {
    private static final String ALEXA_HANDS_FREE_FEATURE_CHECKER = "ALEXA_HANDS_FREE_FEATURE_GATING";
    private static final String TAG = "UserIdentityFeatureChecker";
    private boolean mFSV2ReadyToUse;
    private final FeatureServiceV2 mFeatureServiceV2;
    private final UserIdentity mUserIdentity;

    public UserIdentityFeatureChecker(@Nullable UserIdentity userIdentity) {
        this(userIdentity, (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class), "ALEXA_HANDS_FREE_FEATURE_GATING");
    }

    private void checkKnownWeblabToConfirmFSV2Ready(@NonNull String str) {
        this.mFeatureServiceV2.observeFeature(str, new FeatureServiceV2.FeatureUpdateListener() { // from class: com.amazon.alexa.voice.handsfree.features.UserIdentityFeatureChecker.1
            @Override // com.amazon.alexa.featureservice.api.FeatureServiceV2.FeatureUpdateListener
            public void onFeatureUpdate(@NonNull String str2) {
                UserIdentityFeatureChecker userIdentityFeatureChecker = UserIdentityFeatureChecker.this;
                userIdentityFeatureChecker.mFSV2ReadyToUse = userIdentityFeatureChecker.mFeatureServiceV2.hasAccess(str2, false);
                String str3 = UserIdentityFeatureChecker.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Feature service V2 access has been updated to: ");
                outline107.append(UserIdentityFeatureChecker.this.mFSV2ReadyToUse);
                Log.d(str3, outline107.toString());
                if (UserIdentityFeatureChecker.this.mFSV2ReadyToUse) {
                    UserIdentityFeatureChecker.this.mFeatureServiceV2.unsubscribe(this);
                }
            }
        });
    }

    @Override // com.amazon.alexa.handsfree.protocols.features.FeatureChecker
    public boolean isActive(@NonNull String str, boolean z) {
        return (this.mUserIdentity == null || !this.mFSV2ReadyToUse) ? z : this.mFeatureServiceV2.hasAccess(str, false);
    }

    UserIdentityFeatureChecker(@Nullable UserIdentity userIdentity, @NonNull FeatureServiceV2 featureServiceV2, @NonNull String str) {
        this.mUserIdentity = userIdentity;
        this.mFeatureServiceV2 = featureServiceV2;
        checkKnownWeblabToConfirmFSV2Ready(str);
    }
}
