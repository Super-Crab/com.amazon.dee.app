package com.amazon.dee.app.ui.external;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.framework.ViewModel;
import com.amazon.dee.app.services.useragent.UserAgentService;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ExternalUIViewModel implements ViewModel {
    @Inject
    EnvironmentService environmentService;
    @Inject
    UserAgentService userAgentService;

    @Override // com.amazon.dee.app.framework.ViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void destroy() {
    }

    public String getUserAgentString() {
        return this.userAgentService.build();
    }

    public boolean isWithinAlexaWebViewNonIndex(@NonNull String str) {
        return this.environmentService.isWithinAlexaWebViewNonIndex(str);
    }

    public boolean isWithinExternalUIWebView(String str, String str2) {
        return this.environmentService.isWithinExternalUIWebView(str, str2);
    }

    public boolean isWithinHostList(@NonNull String str, String[] strArr) {
        return this.environmentService.isWithinHostList(str, strArr);
    }

    public boolean isWithinWebAppUrl(@NonNull String str) {
        return this.environmentService.isWithinWebAppUrl(str);
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    @Nullable
    public Bundle saveState() {
        return null;
    }

    @Override // com.amazon.dee.app.framework.ViewModel
    public void updateContentMode(int i) {
    }
}
