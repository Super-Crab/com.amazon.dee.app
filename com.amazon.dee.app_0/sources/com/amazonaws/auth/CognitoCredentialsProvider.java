package com.amazonaws.auth;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.SDKGlobalConfiguration;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.Credentials;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityRequest;
import com.amazonaws.services.cognitoidentity.model.GetCredentialsForIdentityResult;
import com.amazonaws.services.cognitoidentity.model.ResourceNotFoundException;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.securitytoken.model.AssumeRoleWithWebIdentityRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/* loaded from: classes13.dex */
public class CognitoCredentialsProvider implements AWSCredentialsProvider {
    public static final int DEFAULT_DURATION_SECONDS = 3600;
    public static final int DEFAULT_THRESHOLD_SECONDS = 500;
    private static final Log log = LogFactory.getLog(AWSCredentialsProviderChain.class);
    protected String authRoleArn;
    private AmazonCognitoIdentity cib;
    protected ReentrantReadWriteLock credentialsLock;
    protected String customRoleArn;
    private final AWSCognitoIdentityProvider identityProvider;
    protected int refreshThreshold;
    private final String region;
    protected AWSSecurityTokenService securityTokenService;
    protected AWSSessionCredentials sessionCredentials;
    protected Date sessionCredentialsExpiration;
    protected int sessionDuration;
    protected String token;
    protected String unauthRoleArn;
    protected boolean useEnhancedFlow;

    public CognitoCredentialsProvider(String str, String str2, String str3, String str4, Regions regions) {
        this(str, str2, str3, str4, regions, new ClientConfiguration());
    }

    private void appendUserAgent(AmazonWebServiceRequest amazonWebServiceRequest, String str) {
        amazonWebServiceRequest.getRequestClientOptions().appendUserAgent(str);
    }

    private static AmazonCognitoIdentityClient createIdentityClient(ClientConfiguration clientConfiguration, Regions regions) {
        AmazonCognitoIdentityClient amazonCognitoIdentityClient = new AmazonCognitoIdentityClient(new AnonymousAWSCredentials(), clientConfiguration);
        amazonCognitoIdentityClient.setRegion(Region.getRegion(regions));
        return amazonCognitoIdentityClient;
    }

    private static ClientConfiguration getClientConfiguration(AWSConfiguration aWSConfiguration) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setUserAgent(aWSConfiguration.getUserAgent());
        return clientConfiguration;
    }

    private static String getIdentityPoolId(AWSConfiguration aWSConfiguration) {
        try {
            return aWSConfiguration.optJsonObject("CredentialsProvider").optJSONObject("CognitoIdentity").getJSONObject(aWSConfiguration.getConfiguration()).getString("PoolId");
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read CognitoIdentity please check your setup or awsconfiguration.json file", e);
        }
    }

    private static Regions getRegions(AWSConfiguration aWSConfiguration) {
        try {
            return Regions.fromName(aWSConfiguration.optJsonObject("CredentialsProvider").optJSONObject("CognitoIdentity").getJSONObject(aWSConfiguration.getConfiguration()).getString("Region"));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read CognitoIdentity please check your setup or awsconfiguration.json file", e);
        }
    }

    private void populateCredentialsWithCognito(String str) {
        Map<String, String> logins;
        GetCredentialsForIdentityResult retryGetCredentialsForIdentity;
        if (str != null && !str.isEmpty()) {
            logins = new HashMap<>();
            logins.put(getLoginsKey(), str);
        } else {
            logins = getLogins();
        }
        try {
            retryGetCredentialsForIdentity = this.cib.getCredentialsForIdentity(new GetCredentialsForIdentityRequest().withIdentityId(getIdentityId()).withLogins(logins).withCustomRoleArn(this.customRoleArn));
        } catch (ResourceNotFoundException unused) {
            retryGetCredentialsForIdentity = retryGetCredentialsForIdentity();
        } catch (AmazonServiceException e) {
            if (e.getErrorCode().equals("ValidationException")) {
                retryGetCredentialsForIdentity = retryGetCredentialsForIdentity();
            } else {
                throw e;
            }
        }
        Credentials credentials = retryGetCredentialsForIdentity.getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretKey(), credentials.getSessionToken());
        setSessionCredentialsExpiration(credentials.getExpiration());
        if (!retryGetCredentialsForIdentity.getIdentityId().equals(getIdentityId())) {
            setIdentityId(retryGetCredentialsForIdentity.getIdentityId());
        }
    }

    private void populateCredentialsWithSts(String str) {
        AssumeRoleWithWebIdentityRequest withDurationSeconds = new AssumeRoleWithWebIdentityRequest().withWebIdentityToken(str).withRoleArn(this.identityProvider.isAuthenticated() ? this.authRoleArn : this.unauthRoleArn).withRoleSessionName("ProviderSession").withDurationSeconds(Integer.valueOf(this.sessionDuration));
        appendUserAgent(withDurationSeconds, getUserAgent());
        com.amazonaws.services.securitytoken.model.Credentials credentials = this.securityTokenService.assumeRoleWithWebIdentity(withDurationSeconds).getCredentials();
        this.sessionCredentials = new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretAccessKey(), credentials.getSessionToken());
        setSessionCredentialsExpiration(credentials.getExpiration());
    }

    private GetCredentialsForIdentityResult retryGetCredentialsForIdentity() {
        Map<String, String> logins;
        this.token = retryRefresh();
        String str = this.token;
        if (str != null && !str.isEmpty()) {
            logins = new HashMap<>();
            logins.put(getLoginsKey(), this.token);
        } else {
            logins = getLogins();
        }
        return this.cib.getCredentialsForIdentity(new GetCredentialsForIdentityRequest().withIdentityId(getIdentityId()).withLogins(logins).withCustomRoleArn(this.customRoleArn));
    }

    private String retryRefresh() {
        setIdentityId(null);
        this.token = this.identityProvider.refresh();
        return this.token;
    }

    public void clear() {
        this.credentialsLock.writeLock().lock();
        try {
            clearCredentials();
            setIdentityId(null);
            this.identityProvider.setLogins(new HashMap());
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public void clearCredentials() {
        this.credentialsLock.writeLock().lock();
        try {
            this.sessionCredentials = null;
            this.sessionCredentialsExpiration = null;
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public String getCustomRoleArn() {
        return this.customRoleArn;
    }

    public String getIdentityId() {
        return this.identityProvider.getIdentityId();
    }

    public AWSIdentityProvider getIdentityProvider() {
        return this.identityProvider;
    }

    public Map<String, String> getLogins() {
        return this.identityProvider.getLogins();
    }

    protected String getLoginsKey() {
        return Regions.CN_NORTH_1.getName().equals(this.region) ? "cognito-identity.cn-north-1.amazonaws.com.cn" : "cognito-identity.amazonaws.com";
    }

    public int getRefreshThreshold() {
        return this.refreshThreshold;
    }

    public Date getSessionCredentitalsExpiration() {
        this.credentialsLock.readLock().lock();
        try {
            return this.sessionCredentialsExpiration;
        } finally {
            this.credentialsLock.readLock().unlock();
        }
    }

    public int getSessionDuration() {
        return this.sessionDuration;
    }

    public String getToken() {
        return this.identityProvider.getToken();
    }

    protected String getUserAgent() {
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean needsNewSession() {
        if (this.sessionCredentials == null) {
            return true;
        }
        return this.sessionCredentialsExpiration.getTime() - (System.currentTimeMillis() - ((long) (SDKGlobalConfiguration.getGlobalTimeOffset() * 1000))) < ((long) (this.refreshThreshold * 1000));
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    public void refresh() {
        this.credentialsLock.writeLock().lock();
        try {
            startSession();
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public void registerIdentityChangedListener(IdentityChangedListener identityChangedListener) {
        this.identityProvider.registerIdentityChangedListener(identityChangedListener);
    }

    public void setCustomRoleArn(String str) {
        this.customRoleArn = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setIdentityId(String str) {
        this.identityProvider.identityChanged(str);
    }

    public void setLogins(Map<String, String> map) {
        this.credentialsLock.writeLock().lock();
        try {
            this.identityProvider.setLogins(map);
            clearCredentials();
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public void setRefreshThreshold(int i) {
        this.refreshThreshold = i;
    }

    public void setSessionCredentialsExpiration(Date date) {
        this.credentialsLock.writeLock().lock();
        try {
            this.sessionCredentialsExpiration = date;
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public void setSessionDuration(int i) {
        this.sessionDuration = i;
    }

    protected void startSession() {
        try {
            this.token = this.identityProvider.refresh();
        } catch (ResourceNotFoundException unused) {
            this.token = retryRefresh();
        } catch (AmazonServiceException e) {
            if (e.getErrorCode().equals("ValidationException")) {
                this.token = retryRefresh();
            } else {
                throw e;
            }
        }
        if (this.useEnhancedFlow) {
            populateCredentialsWithCognito(this.token);
        } else {
            populateCredentialsWithSts(this.token);
        }
    }

    public void unregisterIdentityChangedListener(IdentityChangedListener identityChangedListener) {
        this.identityProvider.unregisterIdentityChangedListener(identityChangedListener);
    }

    public AWSCredentialsProvider withLogins(Map<String, String> map) {
        setLogins(map);
        return this;
    }

    public CognitoCredentialsProvider withRefreshThreshold(int i) {
        setRefreshThreshold(i);
        return this;
    }

    public CognitoCredentialsProvider withSessionDuration(int i) {
        setSessionDuration(i);
        return this;
    }

    public CognitoCredentialsProvider(String str, String str2, String str3, String str4, Regions regions, ClientConfiguration clientConfiguration) {
        this(str, str2, str3, str4, createIdentityClient(clientConfiguration, regions), (str3 == null && str4 == null) ? null : new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), clientConfiguration));
    }

    @Override // com.amazonaws.auth.AWSCredentialsProvider
    /* renamed from: getCredentials  reason: collision with other method in class */
    public AWSSessionCredentials mo6648getCredentials() {
        this.credentialsLock.writeLock().lock();
        try {
            if (needsNewSession()) {
                startSession();
            }
            return this.sessionCredentials;
        } finally {
            this.credentialsLock.writeLock().unlock();
        }
    }

    public CognitoCredentialsProvider(AWSConfiguration aWSConfiguration) {
        this((String) null, getIdentityPoolId(aWSConfiguration), (String) null, (String) null, getRegions(aWSConfiguration), getClientConfiguration(aWSConfiguration));
    }

    public CognitoCredentialsProvider(String str, Regions regions) {
        this((String) null, str, (String) null, (String) null, regions, new ClientConfiguration());
    }

    public CognitoCredentialsProvider(String str, Regions regions, ClientConfiguration clientConfiguration) {
        this((String) null, str, (String) null, (String) null, regions, clientConfiguration);
    }

    public String getIdentityPoolId() {
        return this.identityProvider.getIdentityPoolId();
    }

    public CognitoCredentialsProvider(String str, String str2, String str3, String str4, AmazonCognitoIdentityClient amazonCognitoIdentityClient, AWSSecurityTokenService aWSSecurityTokenService) {
        this.cib = amazonCognitoIdentityClient;
        this.region = amazonCognitoIdentityClient.getRegions().getName();
        this.securityTokenService = aWSSecurityTokenService;
        this.unauthRoleArn = str3;
        this.authRoleArn = str4;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        this.useEnhancedFlow = str3 == null && str4 == null;
        if (this.useEnhancedFlow) {
            this.identityProvider = new AWSEnhancedCognitoIdentityProvider(str, str2, amazonCognitoIdentityClient);
        } else {
            this.identityProvider = new AWSBasicCognitoIdentityProvider(str, str2, amazonCognitoIdentityClient);
        }
        this.credentialsLock = new ReentrantReadWriteLock(true);
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider aWSCognitoIdentityProvider, String str, String str2) {
        this(aWSCognitoIdentityProvider, str, str2, new AWSSecurityTokenServiceClient(new AnonymousAWSCredentials(), new ClientConfiguration()));
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider aWSCognitoIdentityProvider, String str, String str2, AWSSecurityTokenService aWSSecurityTokenService) {
        this.identityProvider = aWSCognitoIdentityProvider;
        if (aWSCognitoIdentityProvider instanceof AWSAbstractCognitoIdentityProvider) {
            AWSAbstractCognitoIdentityProvider aWSAbstractCognitoIdentityProvider = (AWSAbstractCognitoIdentityProvider) aWSCognitoIdentityProvider;
            AmazonCognitoIdentity amazonCognitoIdentity = aWSAbstractCognitoIdentityProvider.cib;
            if ((amazonCognitoIdentity instanceof AmazonWebServiceClient) && ((AmazonWebServiceClient) amazonCognitoIdentity).getRegions() != null) {
                this.region = ((AmazonWebServiceClient) aWSAbstractCognitoIdentityProvider.cib).getRegions().getName();
                this.unauthRoleArn = str;
                this.authRoleArn = str2;
                this.securityTokenService = aWSSecurityTokenService;
                this.sessionDuration = 3600;
                this.refreshThreshold = 500;
                this.useEnhancedFlow = false;
                this.credentialsLock = new ReentrantReadWriteLock(true);
            }
        }
        log.warn("Could not determine region of the Cognito Identity client, using default us-east-1");
        this.region = Regions.US_EAST_1.getName();
        this.unauthRoleArn = str;
        this.authRoleArn = str2;
        this.securityTokenService = aWSSecurityTokenService;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        this.useEnhancedFlow = false;
        this.credentialsLock = new ReentrantReadWriteLock(true);
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider aWSCognitoIdentityProvider, Regions regions) {
        this(aWSCognitoIdentityProvider, regions, new ClientConfiguration());
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider aWSCognitoIdentityProvider, Regions regions, ClientConfiguration clientConfiguration) {
        this(aWSCognitoIdentityProvider, createIdentityClient(clientConfiguration, regions));
    }

    public CognitoCredentialsProvider(AWSCognitoIdentityProvider aWSCognitoIdentityProvider, AmazonCognitoIdentityClient amazonCognitoIdentityClient) {
        this.cib = amazonCognitoIdentityClient;
        this.region = amazonCognitoIdentityClient.getRegions().getName();
        this.identityProvider = aWSCognitoIdentityProvider;
        this.unauthRoleArn = null;
        this.authRoleArn = null;
        this.securityTokenService = null;
        this.sessionDuration = 3600;
        this.refreshThreshold = 500;
        this.useEnhancedFlow = true;
        this.credentialsLock = new ReentrantReadWriteLock(true);
    }
}
