package com.amazon.dee.app.services.coral;

import com.amazon.dee.app.services.useragent.UserAgent;
import com.dee.app.http.HttpCoralService;
/* loaded from: classes12.dex */
public class UserAgentRequestInterceptor implements HttpCoralService.RequestInterceptor {
    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        httpRequest.getHeaders().put("User-Agent", UserAgent.get());
    }
}
