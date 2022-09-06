package com.amazon.alexa.biloba.service;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
/* loaded from: classes6.dex */
public final class BilobaPersonIdProvider implements PersonIdProvider {
    private static final String DEFAULT_DIRECTED_ID = "0";
    static final String TAG = "BilobaPersonIdProvider";
    private final IdentityService identityService;
    private final BilobaMetricsService metricsService;

    public BilobaPersonIdProvider(IdentityService identityService, BilobaMetricsService bilobaMetricsService) {
        this.identityService = identityService;
        this.metricsService = bilobaMetricsService;
    }

    private boolean isNewUser(String str) {
        return TextUtils.isEmpty(str) || DEFAULT_DIRECTED_ID.equals(str);
    }

    @Override // com.amazon.alexa.identity.api.PersonIdProvider
    @Nullable
    public String getPersonId() {
        UserIdentity user = this.identityService.getUser(TAG);
        this.metricsService.recordOccurrence(MetricsConstants.OperationalMetrics.GET_USER_IDENTITY, user != null);
        if (user == null) {
            LogUtils.e(TAG, "UserIdentity is null");
            this.metricsService.recordOccurrence(MetricsConstants.OperationalMetrics.GET_PERSON_ID, false);
            return null;
        }
        UserProfile userProfile = user.getUserProfile();
        this.metricsService.recordOccurrence(MetricsConstants.OperationalMetrics.GET_USER_PROFILE, userProfile != null);
        if (userProfile == null) {
            LogUtils.e(TAG, "UserProfile is null");
            this.metricsService.recordOccurrence(MetricsConstants.OperationalMetrics.GET_PERSON_ID, false);
            return null;
        }
        String directedId = userProfile.getDirectedId();
        boolean isNewUser = isNewUser(directedId);
        if (isNewUser) {
            LogUtils.e(TAG, "directedId in UserProfile is empty, null or default value");
            directedId = null;
        }
        this.metricsService.recordOccurrence(MetricsConstants.OperationalMetrics.GET_PERSON_ID, true ^ isNewUser);
        return directedId;
    }
}
