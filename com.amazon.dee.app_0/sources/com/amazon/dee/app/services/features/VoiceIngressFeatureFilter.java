package com.amazon.dee.app.services.features;

import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.features.FeatureFilter;
import com.amazon.alexa.voice.model.VoiceService;
import dagger.Lazy;
import java.util.Collections;
import java.util.Set;
/* loaded from: classes12.dex */
public class VoiceIngressFeatureFilter implements FeatureFilter {
    private final Lazy<VoiceService> voiceService;

    public VoiceIngressFeatureFilter(Lazy<VoiceService> lazy) {
        this.voiceService = lazy;
    }

    @Override // com.amazon.alexa.protocols.features.FeatureFilter
    public boolean hasAccess(UserIdentity userIdentity, String str, Set<String> set) {
        if (!"TACHYON_FEATURE_MAGIK".equals(str)) {
            return false;
        }
        return this.voiceService.mo358get().isVoicePossible();
    }

    @Override // com.amazon.alexa.protocols.features.FeatureFilter
    public Set<String> targetedFeatures() {
        return Collections.singleton("TACHYON_FEATURE_MAGIK");
    }
}
