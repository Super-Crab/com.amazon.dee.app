package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.utils.LanguageUtils;
import com.dee.app.http.HttpCoralService;
import java.util.Locale;
/* loaded from: classes6.dex */
public class AcceptLanguageRequestInterceptor implements HttpCoralService.RequestInterceptor {
    @Override // com.dee.app.http.HttpCoralService.RequestInterceptor
    public void intercept(HttpCoralService.HttpRequest httpRequest) {
        httpRequest.withHeader("Accept-Language", LanguageUtils.getAcceptableLanguageTag(Locale.getDefault()).toLanguageTag());
    }
}
