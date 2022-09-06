package com.amazon.communication.directorservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.communication.WorkExecutor;
import com.amazon.communication.authentication.MapAccountManagerWrapper;
import com.amazon.communication.directorservice.GetEndpointParam;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.dp.logger.DPLogger;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dp.utils.FailFast;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.EnumSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class DirectorServiceClient {
    static final String DEFAULT_MARKETPLACE_ID = "ATVPDKIKX0DER";
    private static final int LONG_TTL_SECS = 3600;
    private static final String QUERY_PARAM_DELIMITER = "&";
    private static final String QUERY_PARAM_KEY_VALUE_DELIMITER = "=";
    private static final String QUERY_PARAM_PREFIX = "?";
    private static final String SERVICE_URL_PATH = "/service/";
    private static final int SHORT_TTL_SECS = 120;
    private static final DPLogger log = new DPLogger("TComm.DirectorServiceClient");
    private final MapAccountManagerWrapper mAccountManager;
    private final BroadcastReceiver mAccountsUpdatedReceiver;
    private AuthenticationMethodFactoryWrapper mAuthMethodFactory;
    private final AuthMethodFactoryWrapper mAuthMethodFactoryWrapper;
    private final AuthenticatedURLConnectionWrapper mAuthenticatedURLConnectionWrapper;
    private ResultCache mCache;
    private ExecutorService mCacheUpdateService;
    private final Context mContext;
    private final CustomerAttributeStoreWrapper mCustomerAttributeStore;
    private String mMarketplaceId;
    private final PfmFactory mPfmFactory;
    private final WorkExecutor mWorkExecutor;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class AccountsUpdatedReceiver extends BroadcastReceiver {
        private AccountsUpdatedReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DirectorServiceClient.log.verbose("onReceive", "accounts updated", MAPAccountManager.KEY_INTENT, intent);
            if (DirectorServiceClient.isPrimaryAccountAddedIntent(intent)) {
                if (DirectorServiceClient.this.mMarketplaceId != null || DirectorServiceClient.this.mAccountManager.getAccount() == null) {
                    return;
                }
                DirectorServiceClient.log.info("onReceive", "the Amazon account was registered and we haven't got a market place id yet. Updating market place id", new Object[0]);
                DirectorServiceClient.this.mWorkExecutor.doBackgroundWork(new Callable<Void>() { // from class: com.amazon.communication.directorservice.DirectorServiceClient.AccountsUpdatedReceiver.1
                    @Override // java.util.concurrent.Callable
                    public Void call() {
                        DirectorServiceClient.this.updateInfoFromMAP();
                        return null;
                    }
                });
            } else if (!DirectorServiceClient.isPrimaryAccountRemovedIntent(intent) || DirectorServiceClient.this.mAccountManager.getAccount() != null) {
            } else {
                DirectorServiceClient.log.info("onReceive", "the Amazon account was de-registered - clean up ", new Object[0]);
                DirectorServiceClient.this.cleanup();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class AuthMethodFactoryWrapper {
        AuthMethodFactoryWrapper() {
        }

        public AuthenticationMethodFactoryWrapper getAuthMethodFactory(Context context, String str) {
            if (str == null) {
                DirectorServiceClient.log.info("getAuthMethodFactory", "Primary account is null. Device is not registered. We will use anonymous credentials to talk to Director Service", new Object[0]);
            }
            return new AuthenticationMethodFactoryWrapper(new AuthenticationMethodFactory(context, str));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class AuthenticatedURLConnectionWrapper {
        AuthenticatedURLConnectionWrapper() {
        }

        public HttpURLConnection openConnection(URL url, AuthenticationMethod authenticationMethod) throws IOException {
            return AuthenticatedURLConnection.openConnection(url, authenticationMethod);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class AuthenticationMethodFactoryWrapper {
        private final AuthenticationMethodFactory mAuthMethodFactory;

        public AuthenticationMethodFactoryWrapper(AuthenticationMethodFactory authenticationMethodFactory) {
            this.mAuthMethodFactory = authenticationMethodFactory;
        }

        public AuthenticationMethod newAuthenticationMethod(AuthenticationType authenticationType) {
            return this.mAuthMethodFactory.newAuthenticationMethod(authenticationType);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class CustomerAttributeStoreWrapper {
        private final CustomerAttributeStore mCustomerAttributeStore;

        public CustomerAttributeStoreWrapper(Context context) {
            this.mCustomerAttributeStore = CustomerAttributeStore.getInstance(context);
        }

        public MAPFuture<Bundle> getAttribute(String str, String str2, Callback callback, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet) {
            return this.mCustomerAttributeStore.getAttribute(str, str2, callback, enumSet);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class GetMarketplaceIdFailedException extends Exception {
        private static final long serialVersionUID = -1268778014586829572L;

        public GetMarketplaceIdFailedException(String str) {
            super(str);
        }

        public GetMarketplaceIdFailedException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class PfmFactory {
        PfmFactory() {
        }

        public String getMarketplaceId(String str, CustomerAttributeStoreWrapper customerAttributeStoreWrapper) throws GetMarketplaceIdFailedException {
            if (str != null) {
                DirectorServiceClient.log.debug("getMarketplaceId", "got primary account - ", "primary account", str);
                try {
                    String valueOrAttributeDefault = CustomerAttributeStore.getValueOrAttributeDefault(customerAttributeStoreWrapper.getAttribute(str, CustomerAttributeKeys.KEY_PFM, null, EnumSet.noneOf(CustomerAttributeStore.GetAttributeOptions.class)).get());
                    if (valueOrAttributeDefault != null) {
                        DirectorServiceClient.log.debug("getMarketplaceId", "got pfm - ", WebConstants.WebviewConstants.MARKETPLACE_ID, valueOrAttributeDefault);
                        return valueOrAttributeDefault;
                    }
                    throw new GetMarketplaceIdFailedException("Could not resolve marketplaceId");
                } catch (MAPCallbackErrorException e) {
                    DirectorServiceClient.log.warn("getMarketplaceId", "could not resolve marketplace due to MAPCallbackErrorException", ADMRegistrationConstants.CALL_EXCEPTION, e, "error", e.getErrorBundle());
                    throw new GetMarketplaceIdFailedException("Could not resolve marketplace due to MAPCallbackErrorException", e);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    DirectorServiceClient.log.warn("getMarketplaceId", "could not resolve marketplace due to InterruptedException", ADMRegistrationConstants.CALL_EXCEPTION, e2);
                    throw new GetMarketplaceIdFailedException("could not resolve marketplace due to InterruptedException", e2);
                } catch (ExecutionException e3) {
                    DirectorServiceClient.log.warn("getMarketplaceId", "could not resolve marketplace due to ExecutionException", ADMRegistrationConstants.CALL_EXCEPTION, e3);
                    throw new GetMarketplaceIdFailedException("could not resolve marketplace due to ExecutionException", e3);
                }
            }
            throw new GetMarketplaceIdFailedException("Could not determine encrypted marketplace id as this device is not registered.");
        }
    }

    public DirectorServiceClient(MapAccountManagerWrapper mapAccountManagerWrapper, WorkExecutor workExecutor, Context context) {
        this(mapAccountManagerWrapper, workExecutor, context, new AuthMethodFactoryWrapper(), new PfmFactory(), new AuthenticatedURLConnectionWrapper(), new CustomerAttributeStoreWrapper(context));
    }

    private void backgroundCacheUpdate(String str, String str2, String str3, String str4) throws GetEndpointFailedException {
        this.mCacheUpdateService.submit(cacheUpdateCallable("backgroundCacheUpdate", str, str2, str3, str4));
    }

    private void bestEffortClose(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            log.error("bestEffortClose", "tried to close Closeable, but got exception", "closeable", closeable, "message", e.getMessage());
        }
    }

    private Callable<GetEndpointResponse> cacheUpdateCallable(final String str, final String str2, final String str3, final String str4, final String str5) {
        return new Callable<GetEndpointResponse>() { // from class: com.amazon.communication.directorservice.DirectorServiceClient.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public GetEndpointResponse mo3289call() throws GetEndpointFailedException {
                GetEndpointResponse getEndpointResponse = DirectorServiceClient.this.mCache.get(str3, str4, str5, 120);
                if (getEndpointResponse != null) {
                    DPLogger dPLogger = DirectorServiceClient.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("cacheUpdateCallable:");
                    outline107.append(str);
                    dPLogger.debug(outline107.toString(), "got exising entry, not calling director", NotificationCompat.CATEGORY_SERVICE, str4, "purpose", str5);
                    return getEndpointResponse;
                }
                GetEndpointResponse doGet = DirectorServiceClient.this.doGet(str2, str3, str4, str5);
                DirectorServiceClient.this.mCache.put(str3, str4, str5, doGet);
                return doGet;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanup() {
        this.mAuthMethodFactory = null;
        this.mMarketplaceId = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GetEndpointResponse doGet(String str, String str2, String str3, String str4) throws GetEndpointFailedException {
        log.debug("doGet", "actually calling director", "marketplace", str2, NotificationCompat.CATEGORY_SERVICE, str3, "purpose", str4);
        try {
            return getEndpoint(new GetEndpointParam.Builder().setDirectorUrl(str).setServiceName(str3).setMarketplace(str2).setPurpose(str4).build());
        } catch (IOException e) {
            throw new GetEndpointFailedException("Exception when making the request to director service", e);
        } catch (JSONException e2) {
            throw new GetEndpointFailedException("Exception when parsing the response from director service", e2);
        }
    }

    private static String generateParameterizedRequestUrl(GetEndpointParam getEndpointParam) {
        return getEndpointParam.getBaseUrl() + SERVICE_URL_PATH + getEndpointParam.getServiceName() + "?" + getEndpointParam.getLocationParamKey().toString() + "=" + getEndpointParam.getLocation() + "&" + GetEndpointParam.GetEndpointParamKey.PURPOSE.toString() + "=" + getEndpointParam.getPurpose();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPrimaryAccountAddedIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        return "com.amazon.dcp.sso.action.account.added".equals(intent.getAction());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isPrimaryAccountRemovedIntent(Intent intent) {
        if (intent == null) {
            return false;
        }
        return "com.amazon.dcp.sso.action.account.removed".equals(intent.getAction());
    }

    private GetEndpointResponse makeAuthenticatedDirectorServiceRequest(String str) throws IOException, JSONException {
        InputStream inputStream;
        log.verbose("makeAuthenticatedDirectorServiceRequest", "executing director service request", "url", str);
        HttpURLConnection openConnection = this.mAuthenticatedURLConnectionWrapper.openConnection(new URL(str), this.mAuthMethodFactory.newAuthenticationMethod(AuthenticationType.ADPAuthenticator));
        try {
            inputStream = openConnection.getInputStream();
            try {
                GetEndpointResponse parseDirectorServiceResponse = parseDirectorServiceResponse(inputStream);
                if (inputStream != null) {
                    bestEffortClose(inputStream);
                }
                openConnection.disconnect();
                return parseDirectorServiceResponse;
            } catch (Throwable th) {
                th = th;
                if (inputStream != null) {
                    bestEffortClose(inputStream);
                }
                openConnection.disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
    }

    private GetEndpointResponse parseDirectorServiceResponse(InputStream inputStream) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    String sb2 = sb.toString();
                    log.verbose("parseDirectorServiceResponse", "received director service response", "response", sb2);
                    return GetEndpointResponse.fromJson(new JSONObject(sb2));
                }
            }
        } finally {
            bestEffortClose(bufferedReader);
        }
    }

    private GetEndpointResponse syncCacheUpdateAndGet(String str, String str2, String str3, String str4) throws GetEndpointFailedException {
        try {
            return (GetEndpointResponse) this.mCacheUpdateService.submit(cacheUpdateCallable("syncCacheUpdate", str, str2, str3, str4)).get();
        } catch (InterruptedException e) {
            throw new GetEndpointFailedException("synchronous getEndpoint call interrupted", e);
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof GetEndpointFailedException) {
                throw ((GetEndpointFailedException) e2.getCause());
            }
            throw new GetEndpointFailedException("Synchronous getEndpoint call failed", e2.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInfoFromMAP() {
        String account = this.mAccountManager.getAccount();
        this.mAuthMethodFactory = this.mAuthMethodFactoryWrapper.getAuthMethodFactory(this.mContext, account);
        try {
            this.mMarketplaceId = this.mPfmFactory.getMarketplaceId(account, this.mCustomerAttributeStore);
        } catch (GetMarketplaceIdFailedException e) {
            log.warn("updateMaketPlaceId", "Could not get market place ID from MAP. We might have to use US Amazon market place id to talk to Director Service", "ex", e);
        }
    }

    public BroadcastReceiver getAccountsUpdatedReceiver() {
        return this.mAccountsUpdatedReceiver;
    }

    public GetEndpointResponse getEndpoint(String str, String str2, String str3) throws GetEndpointFailedException {
        FailFast.expectNotNull(str, "directorUrl");
        FailFast.expectNotNull(str2, "serviceName");
        FailFast.expectNotNull(str3, "purpose");
        FailFast.expectNotNull(this.mAuthMethodFactory, "Auth method factory is null. Did you invoke initialize()?");
        String str4 = this.mMarketplaceId;
        if (str4 == null) {
            log.warn("getEndpoint", "Market place id is null. Use US Amazon market place id", new Object[0]);
            str4 = "ATVPDKIKX0DER";
        }
        GetEndpointResponse getEndpointResponse = this.mCache.get(str4, str2, str3, 120);
        if (getEndpointResponse != null) {
            log.info("getEndpoint", "found recently cached entry, not calling director", "marketplace", str4, "serviceName", str2, "purpose", str3, "endpoint", getEndpointResponse.getEndpointUrl());
            return getEndpointResponse;
        }
        GetEndpointResponse getEndpointResponse2 = this.mCache.get(str4, str2, str3, 3600);
        if (getEndpointResponse2 != null) {
            log.info("getEndpoint", "Found old cached entry.  Using and queueing background update.", "marketplace", str4, "serviceName", str2, "purpose", str3, "endpoint", getEndpointResponse2.getEndpointUrl());
            backgroundCacheUpdate(str, str4, str2, str3);
            return getEndpointResponse2;
        }
        return syncCacheUpdateAndGet(str, str4, str2, str3);
    }

    public void initialize() {
        updateInfoFromMAP();
    }

    public void shutdown() {
        cleanup();
    }

    DirectorServiceClient(MapAccountManagerWrapper mapAccountManagerWrapper, WorkExecutor workExecutor, Context context, AuthMethodFactoryWrapper authMethodFactoryWrapper, PfmFactory pfmFactory, AuthenticatedURLConnectionWrapper authenticatedURLConnectionWrapper, CustomerAttributeStoreWrapper customerAttributeStoreWrapper) {
        this.mAccountsUpdatedReceiver = new AccountsUpdatedReceiver();
        this.mAccountManager = mapAccountManagerWrapper;
        this.mCustomerAttributeStore = customerAttributeStoreWrapper;
        this.mWorkExecutor = workExecutor;
        this.mContext = context;
        this.mAuthMethodFactoryWrapper = authMethodFactoryWrapper;
        this.mPfmFactory = pfmFactory;
        this.mAuthenticatedURLConnectionWrapper = authenticatedURLConnectionWrapper;
        this.mCache = ResultCache.newCache();
        this.mCacheUpdateService = Executors.newFixedThreadPool(1);
    }

    private GetEndpointResponse getEndpoint(GetEndpointParam getEndpointParam) throws IOException, JSONException {
        log.verbose("getEndpoint", "initiating request to director service", "baseUrl", getEndpointParam.getBaseUrl(), "serviceName", getEndpointParam.getServiceName(), "location", getEndpointParam.getLocation());
        return makeAuthenticatedDirectorServiceRequest(generateParameterizedRequestUrl(getEndpointParam));
    }
}
