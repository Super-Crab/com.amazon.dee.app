package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.HttpRequestSignerType;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.identity.auth.device.api.AccountChangeEvent;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPInit;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes11.dex */
public class MAPMetricsTransport extends AbstractHTTPMetricsTransport {
    protected MetricsMAPAccountChangeObserver mAccountChangeObserver;
    protected MAPAccountManager mAccountManager;
    protected AuthenticationMethodFactory mAuthFactory;

    /* renamed from: com.amazon.client.metrics.thirdparty.transport.MAPMetricsTransport$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType = new int[HttpRequestSignerType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP_OAUTH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.DCP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$configuration$HttpRequestSignerType[HttpRequestSignerType.OAUTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes11.dex */
    protected class MetricsMAPAccountChangeObserver implements MAPAccountManager.MAPAccountChangeObserver {
        protected MetricsMAPAccountChangeObserver() {
        }

        @Override // com.amazon.identity.auth.device.api.MAPAccountManager.MAPAccountChangeObserver
        public void onAccountChange(AccountChangeEvent accountChangeEvent) {
            Log.i(MAPMetricsTransport.this.TAG, "Account changed, recreating AuthenticationMethodFactory with new credentials");
            MAPMetricsTransport mAPMetricsTransport = MAPMetricsTransport.this;
            mAPMetricsTransport.mAuthFactory = new AuthenticationMethodFactory(mAPMetricsTransport.mContext, accountChangeEvent.getCurrentAccount());
        }
    }

    public MAPMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        super(context, metricsConfiguration, deviceUtil, metricsTransport);
        MAPInit.getInstance(this.mContext).initialize();
        this.mAccountManager = new MAPAccountManager(this.mContext);
        this.mAuthFactory = new AuthenticationMethodFactory(this.mContext, this.mAccountManager.getAccount());
        this.mAccountChangeObserver = new MetricsMAPAccountChangeObserver();
        this.mAccountManager.registerAccountChangeObserver(this.mAccountChangeObserver);
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected HttpURLConnection openConnection(URL url) throws IOException {
        AuthenticationType authenticationType;
        HttpConfiguration httpConfiguration = this.mMetricsConfiguration.getHttpConfiguration();
        int ordinal = httpConfiguration.getHttpRequestSignerType().ordinal();
        if (ordinal == 0) {
            authenticationType = AuthenticationType.ADPAuthenticator;
        } else if (ordinal != 1) {
            if (ordinal != 2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unknown request signer type: ");
                outline107.append(httpConfiguration.getHttpRequestSignerType());
                throw new IllegalArgumentException(outline107.toString());
            }
            throw new IllegalArgumentException("OAUTH request signer type not supported without setting an OAuthHelper");
        } else {
            authenticationType = AuthenticationType.OAuth;
        }
        return AuthenticatedURLConnection.openConnection(url, this.mAuthFactory.newAuthenticationMethod(authenticationType));
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void shutdown() {
        this.mAccountManager.deregisterAccountChangeObserver(this.mAccountChangeObserver);
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport
    protected boolean signRequest(HttpURLConnection httpURLConnection) {
        return true;
    }
}
