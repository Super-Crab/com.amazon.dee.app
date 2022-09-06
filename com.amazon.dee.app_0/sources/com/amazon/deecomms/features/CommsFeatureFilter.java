package com.amazon.deecomms.features;

import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.protocols.marketplace.MarketplaceName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsDynamicFeature;
import com.amazon.deecomms.api.MarketplaceUtils;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.conversation.CommsConversationService;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes12.dex */
public class CommsFeatureFilter implements FeatureFilter {
    private static final String HAS_NO_COMMS_FEATURE_FLAG = "hasNoCommsFeatureFlag";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsFeatureFilter.class);
    private static final String NOT_IN_SUPPORTED_MARKETPLACE = "notInSupportedMarketplace";
    private static final String TAG = "CommsFeatureFilter";
    private final CommsDeviceSupport commsDeviceSupport;
    private final DeviceInformation deviceInformation;

    public CommsFeatureFilter(@NonNull CommsDeviceSupport commsDeviceSupport, @NonNull DeviceInformation deviceInformation) {
        this.commsDeviceSupport = commsDeviceSupport;
        this.deviceInformation = deviceInformation;
    }

    private boolean hasTachyonFeature(@NonNull String str) {
        boolean equals = CommsDynamicFeature.COMMS_AVAILABILITY.getPrimaryFeatureName().equals(str);
        LOG.i(String.format("hasTachyonNaFeature? %b", Boolean.valueOf(equals)));
        return equals;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
        if (inSupportedMarketplace(r11) == false) goto L15;
     */
    @Override // com.amazon.alexa.protocols.features.FeatureFilter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean hasAccess(@androidx.annotation.NonNull com.amazon.alexa.identity.api.UserIdentity r11, @androidx.annotation.NonNull java.lang.String r12, @androidx.annotation.NonNull java.util.Set<java.lang.String> r13) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.features.CommsFeatureFilter.hasAccess(com.amazon.alexa.identity.api.UserIdentity, java.lang.String, java.util.Set):boolean");
    }

    boolean inSupportedMarketplace(@NonNull UserIdentity userIdentity) {
        boolean isCommsSupportedInMarketplace = MarketplaceUtils.isCommsSupportedInMarketplace(CommsConversationService.getCommsPfmById(userIdentity.getOriginalMarketplace() == null ? null : userIdentity.getOriginalMarketplace().getObfuscatedId().toString(), MarketplaceName.US.toString()));
        LOG.i(String.format("isInCommsSupportedMarketplace? %b", Boolean.valueOf(isCommsSupportedInMarketplace)));
        return isCommsSupportedInMarketplace;
    }

    @Override // com.amazon.alexa.protocols.features.FeatureFilter
    public Set<String> targetedFeatures() {
        return new HashSet(Arrays.asList(CommsDynamicFeature.COMMS_AVAILABILITY.getPrimaryFeatureName()));
    }
}
