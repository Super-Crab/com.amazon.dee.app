package com.amazon.alexa.identity;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.Metric;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.identity.api.UserIdentity;
import com.dee.app.metrics.MetricsService;
/* loaded from: classes9.dex */
public final class DefaultPersonIdProvider implements PersonIdProvider {
    static final String TAG = Utils.tag(DefaultPersonIdProvider.class);
    private final IdentityService identityService;
    private final MetricsService metricsService;

    public DefaultPersonIdProvider(IdentityService identityService, MetricsService metricsService) {
        this.identityService = identityService;
        this.metricsService = metricsService;
    }

    @Override // com.amazon.alexa.identity.api.PersonIdProvider
    @Nullable
    public String getPersonId() {
        UserIdentity user = this.identityService.getUser(TAG);
        if (user == null) {
            return null;
        }
        this.metricsService.recordEvent(Metric.Event.PERSONID_REQUESTED, Metric.Component.PERSONID, Utils.CUSTOM_ENTRIES);
        if (user.getUserProfile() != null) {
            String directedId = user.getUserProfile().getDirectedId();
            this.metricsService.recordPercentOccurrence(Metric.Event.PERSONID_USR_PRFL_DIRID_NULL, Metric.Component.PERSONID, TextUtils.isEmpty(directedId), Utils.CUSTOM_ENTRIES);
            return directedId;
        }
        String directedId2 = user.getDirectedId();
        this.metricsService.recordPercentOccurrence(Metric.Event.PERSONID_DIRID_NULL, Metric.Component.PERSONID, TextUtils.isEmpty(directedId2), Utils.CUSTOM_ENTRIES);
        return directedId2;
    }
}
