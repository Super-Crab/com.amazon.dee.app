package com.amazon.client.metrics.common.transport.internal.android;

import com.amazon.client.metrics.thirdparty.transport.OAuthHelper;
/* loaded from: classes11.dex */
public class AndroidOAuthHelperImpl implements OAuthHelper {
    private final com.amazon.client.metrics.common.transport.OAuthHelper mCommonOAuthHelper;

    public AndroidOAuthHelperImpl(com.amazon.client.metrics.common.transport.OAuthHelper oAuthHelper) {
        if (oAuthHelper != null) {
            this.mCommonOAuthHelper = oAuthHelper;
            return;
        }
        throw new NullPointerException("OAuthHelper may not be null");
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.OAuthHelper
    public String getAccessToken() throws Exception {
        return this.mCommonOAuthHelper.getAccessToken();
    }
}
