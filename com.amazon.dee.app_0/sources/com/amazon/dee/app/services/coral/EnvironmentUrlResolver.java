package com.amazon.dee.app.services.coral;

import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.UrlResolver;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
/* loaded from: classes12.dex */
public class EnvironmentUrlResolver implements UrlResolver {
    private EnvironmentService environmentService;

    public EnvironmentUrlResolver(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    @Override // com.dee.app.http.UrlResolver
    @SuppressFBWarnings({"NP_NULL_ON_SOME_PATH_FROM_RETURN_VALUE"})
    public String resolve(String str) {
        if (str.startsWith("http")) {
            return str;
        }
        String coralEndpoint = this.environmentService.getCoralEndpoint();
        if (!coralEndpoint.endsWith("/")) {
            coralEndpoint = GeneratedOutlineSupport1.outline72(coralEndpoint, "/");
        }
        if (str.startsWith("/")) {
            return GeneratedOutlineSupport1.outline55(str, 1, GeneratedOutlineSupport1.outline107(coralEndpoint));
        }
        return GeneratedOutlineSupport1.outline72(coralEndpoint, str);
    }
}
