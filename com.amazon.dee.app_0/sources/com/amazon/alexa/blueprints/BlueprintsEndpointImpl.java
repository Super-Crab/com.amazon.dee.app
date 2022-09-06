package com.amazon.alexa.blueprints;

import android.content.Context;
import com.amazon.alexa.blueprints.api.BlueprintsEndpoint;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.ComponentGetter;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class BlueprintsEndpointImpl implements BlueprintsEndpoint {
    private Provider<EnvironmentService> environmentService;

    public BlueprintsEndpointImpl(ComponentGetter componentGetter, Context context) {
        this.environmentService = componentGetter.get(EnvironmentService.class);
    }

    @Override // com.amazon.alexa.blueprints.api.BlueprintsEndpoint
    public String getBlueprintsHomePageURL() {
        return BlueprintsEndpointConfiguration.getEndpointbyStageandMarketplace(this.environmentService.mo10268get().getBuildStage(), this.environmentService.mo10268get().getMarketplace());
    }

    @Override // com.amazon.alexa.blueprints.api.BlueprintsEndpoint
    public String getURLRegexKey() {
        return BlueprintsEndpointConstants.BLUEPRINTS_REGEX;
    }
}
