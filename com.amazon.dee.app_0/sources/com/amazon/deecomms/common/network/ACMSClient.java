package com.amazon.deecomms.common.network;

import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.auth.AuthHeaderProvider;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.amazon.deecomms.common.util.JacksonJSONConverter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ACMSClient {
    private final String mClientId;
    private final CommsLogger mCommsLogger;
    private final IHttpClient mHttpClient;
    private final String mUrlPrefix;

    public ACMSClient(@NonNull String str) {
        this(new OkHttpClientWrapper(new JacksonJSONConverter(), new AuthHeaderProvider(), "default"), CommsDaggerWrapper.getComponent().getAppUrl().getACMSServiceURL(), CommsLogger.getLogger(Constants.LOG_TAG, ACMSClient.class), str);
    }

    private String getFullUrl(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.mUrlPrefix, str);
    }

    public IHttpClient.Request request(@NonNull String str) {
        String userAgent = Utils.getUserAgent();
        String locale = CommsInternal.getInstance().getLocale();
        IHttpClient.Request addHeader = this.mHttpClient.request(getFullUrl(str)).addRequestIdToHeader().addHeader(Constants.ACMS_HEADER_CLIENT_ID, this.mClientId).addHeader("User-Agent", userAgent).addHeader("Accept-Language", locale).addHeader(Constants.ACCEPT_PFM, CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false));
        CommsLogger commsLogger = this.mCommsLogger;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(" ACMS Request ID ");
        outline1.append(addHeader.getRequestId());
        outline1.append(", ACMS Client ID ");
        GeneratedOutlineSupport1.outline177(outline1, this.mClientId, commsLogger);
        return addHeader;
    }

    public ACMSClient(OkHttpClientWrapper okHttpClientWrapper, String str, CommsLogger commsLogger, @NonNull String str2) {
        this.mHttpClient = okHttpClientWrapper;
        this.mUrlPrefix = str;
        this.mCommsLogger = commsLogger;
        this.mClientId = CommsInternal.getInstance().getClientID();
        this.mHttpClient.setOperationMetricNameRoot(str2);
    }
}
