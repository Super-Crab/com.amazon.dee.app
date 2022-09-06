package com.amazon.dee.app.services.coral;

import com.dee.app.http.HttpCoralService;
/* loaded from: classes12.dex */
public class AcceptHeaderRequestInterceptor implements HttpCoralService.RequestInterceptor {
    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        httpRequest.getHeaders().put("Accept", "application/json");
    }
}
