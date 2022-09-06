package com.amazon.dee.app.metrics;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.client.metrics.common.transport.OAuthHelper;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import java.util.concurrent.ExecutionException;
/* loaded from: classes12.dex */
public class CommsMetricsOAuthHelper implements OAuthHelper {
    private final Context mContext;

    public CommsMetricsOAuthHelper(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.client.metrics.common.transport.OAuthHelper
    public String getAccessToken() throws MetricsOAuthException, InterruptedException, ExecutionException, MAPCallbackErrorException {
        String account = new MAPAccountManager(this.mContext).getAccount();
        if (!TextUtils.isEmpty(account)) {
            return new TokenManagement(this.mContext).getToken(account, TokenKeys.getAccessTokenKeyForPackage(this.mContext.getPackageName()), null, null).get().getString("value_key");
        }
        throw new MetricsOAuthException("DirectedId is empty; Cannot get access token");
    }
}
