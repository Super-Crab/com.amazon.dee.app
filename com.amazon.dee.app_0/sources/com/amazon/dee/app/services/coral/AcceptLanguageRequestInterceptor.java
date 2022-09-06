package com.amazon.dee.app.services.coral;

import com.dee.app.http.HttpCoralService;
import java.util.Locale;
/* loaded from: classes12.dex */
public class AcceptLanguageRequestInterceptor implements HttpCoralService.RequestInterceptor {
    private String getAcceptLanguage() {
        return Locale.getDefault().toString().replace('_', '-');
    }

    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        httpRequest.withHeader("Accept-Language", getAcceptLanguage());
    }
}
