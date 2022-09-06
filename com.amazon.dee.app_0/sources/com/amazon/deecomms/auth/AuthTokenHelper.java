package com.amazon.deecomms.auth;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.FetchAuthToken;
import com.amazon.deecomms.common.sip.SipAuthTokenResponse;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
/* loaded from: classes12.dex */
public final class AuthTokenHelper {
    private static final boolean DEBUG_FORCE_SHORT_EXPIRATION = false;
    private static final int DEBUG_SHORT_EXPIRATION_VALUE = 360000;
    private static final boolean DEBUG_USE_READABLE_TOKENS = false;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AuthTokenHelper.class);
    private static final Map<Integer, String> sDebugReadableTokens = null;

    /* loaded from: classes12.dex */
    public static final class AuthToken {
        private final String authToken;
        private final long expiry;

        public AuthToken(@NonNull String str, long j) {
            this.authToken = str;
            this.expiry = j;
        }

        public long getExpiry() {
            return this.expiry;
        }

        public int getExpiryInSecs() {
            return (int) (getExpiry() / 1000);
        }

        @NonNull
        public String getValue() {
            return this.authToken;
        }

        public String toString() {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("AuthToken: value=");
            outline1.append(AuthTokenHelper.sensitive(getValue()));
            outline1.append(", expiry=");
            outline1.append(getExpiry());
            return outline1.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class TokenInfo {
        SipAuthTokenResponse.AuthToken authToken;
        String requestId;

        private TokenInfo() {
        }

        public String toString() {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("TokenInfo: token=");
            outline1.append(this.authToken);
            outline1.append(", requestId=");
            outline1.append(this.requestId);
            return outline1.toString();
        }

        /* synthetic */ TokenInfo(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    public enum TokenType {
        SIP { // from class: com.amazon.deecomms.auth.AuthTokenHelper.TokenType.1
            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getAcmsType() {
                return Constants.ACMS_SIP_AUTH_TOKEN_TYPE;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getExpiryPreferenceKey() {
                return Constants.SHARED_PREF_SIP_AUTH_TOKEN_EXPIRY;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getHashPreferenceKey() {
                return Constants.SHARED_PREF_SIP_AUTH_TOKEN_HASH;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getTokenPreferenceKey() {
                return Constants.SHARED_PREF_SIP_AUTH_TOKEN;
            }
        },
        MEDIA_SERVICE { // from class: com.amazon.deecomms.auth.AuthTokenHelper.TokenType.2
            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getAcmsType() {
                return Constants.ACMS_MEDIA_AUTH_TOKEN_TYPE;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getExpiryPreferenceKey() {
                return Constants.SHARED_PREF_MEDIA_AUTH_TOKEN_EXPIRY;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getHashPreferenceKey() {
                return Constants.SHARED_PREF_MEDIA_AUTH_TOKEN_HASH;
            }

            @Override // com.amazon.deecomms.auth.AuthTokenHelper.TokenType
            @NonNull
            protected String getTokenPreferenceKey() {
                return Constants.SHARED_PREF_MEDIA_AUTH_TOKEN;
            }
        };

        @NonNull
        protected abstract String getAcmsType();

        @NonNull
        protected abstract String getExpiryPreferenceKey();

        @NonNull
        protected abstract String getHashPreferenceKey();

        @NonNull
        protected abstract String getTokenPreferenceKey();

        /* synthetic */ TokenType(AnonymousClass1 anonymousClass1) {
        }
    }

    private AuthTokenHelper() {
    }

    static String computeSHA256Hash(@NonNull String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes());
            return Base64.encodeToString(messageDigest.digest(), 0).trim();
        } catch (NoSuchAlgorithmException e) {
            LOG.e("SHA 256 algorithm is not found for computing hash", e);
            return null;
        }
    }

    public static synchronized AuthToken fetchAuthToken(@NonNull String str, @NonNull TokenType tokenType, boolean z) {
        AuthToken fetchAuthToken;
        synchronized (AuthTokenHelper.class) {
            fetchAuthToken = fetchAuthToken(str, tokenType, 300000L, z);
        }
        return fetchAuthToken;
    }

    @NonNull
    private static TokenInfo fetchToken(@NonNull String str, @NonNull TokenType tokenType) {
        TokenInfo tokenInfo = new TokenInfo(null);
        try {
            FetchAuthToken fetchAuthToken = new FetchAuthToken(tokenType.getAcmsType());
            SipAuthTokenResponse execute = fetchAuthToken.execute(str);
            tokenInfo.requestId = fetchAuthToken.getRequestId();
            if (execute != null) {
                tokenInfo.authToken = execute.getAuthToken();
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("fetched token: ");
                sb.append(execute.getAuthToken());
                sb.append(". TokenType=");
                sb.append(tokenType);
                commsLogger.d(sb.toString());
            } else {
                CommsLogger commsLogger2 = LOG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid response from FetchAuthToken. TokenType=");
                sb2.append(tokenType);
                commsLogger2.e(sb2.toString());
                recordDebugMetric(fetchAuthToken.getRequestId(), "Null response from API", null);
            }
        } catch (ServiceException e) {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.e("Error while FetchAuthToken. TokenType=" + tokenType, e);
        }
        SipAuthTokenResponse.AuthToken authToken = tokenInfo.authToken;
        if (authToken != null) {
            String tokenValue = authToken.getTokenValue();
            long epochTokenCreatedTimeInMillis = tokenInfo.authToken.getEpochTokenCreatedTimeInMillis();
            long epochTokenExpirationTimeInMillis = tokenInfo.authToken.getEpochTokenExpirationTimeInMillis();
            if (TextUtils.isEmpty(tokenValue)) {
                CommsLogger commsLogger4 = LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Fetched token has invalid value: %s");
                outline1.append(sensitive(tokenValue));
                outline1.append(". TokenType=");
                outline1.append(tokenType);
                commsLogger4.e(outline1.toString());
                tokenInfo.authToken = null;
                String str2 = tokenInfo.requestId;
                recordDebugMetric(str2, "Fetched token has invalid value. TokenType=" + tokenType, null);
            } else if (epochTokenExpirationTimeInMillis == 0) {
                CommsLogger commsLogger5 = LOG;
                StringBuilder outline12 = GeneratedOutlineSupport.outline1("Fetched token has invalid expiration");
                outline12.append(sensitive(tokenValue));
                outline12.append(". TokenType=");
                outline12.append(tokenType);
                commsLogger5.e(outline12.toString());
                tokenInfo.authToken = null;
                String str3 = tokenInfo.requestId;
                recordDebugMetric(str3, "Fetched token has invalid expiration. TokenType=" + tokenType, null);
            } else if (epochTokenExpirationTimeInMillis <= epochTokenCreatedTimeInMillis) {
                CommsLogger commsLogger6 = LOG;
                commsLogger6.e("Fetched token is expired. TokenType=" + tokenType);
                tokenInfo.authToken = null;
                String str4 = tokenInfo.requestId;
                recordDebugMetric(str4, "Fetched token is expired. TokenType=" + tokenType, null);
            }
        }
        return tokenInfo;
    }

    public static AuthToken getSavedAuthToken(@NonNull Context context, @NonNull TokenType tokenType, long j) {
        String string = new SecuredSharedPreference(context).getString(tokenType.getTokenPreferenceKey(), null);
        long longPreferenceFromSharedPrefs = Utils.getLongPreferenceFromSharedPrefs(context, tokenType.getExpiryPreferenceKey(), -1L);
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = longPreferenceFromSharedPrefs - currentTimeMillis;
        CommsLogger commsLogger = LOG;
        commsLogger.d(String.format("Cached token: %s, expiration=%d, type=%s", commsLogger.sensitive(string), Long.valueOf(longPreferenceFromSharedPrefs), tokenType));
        if (TextUtils.isEmpty(string)) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i("No cached token, fetching new token. TokenType=" + tokenType);
        } else if (longPreferenceFromSharedPrefs < currentTimeMillis) {
            CommsLogger commsLogger3 = LOG;
            commsLogger3.w("Cached token has expired, fetching new token. TokenType=" + tokenType);
        } else if (!validAuthTokenIntegrity(string, tokenType)) {
            CommsLogger commsLogger4 = LOG;
            commsLogger4.w("Cached token Integrity is not valid, fetching new token. TokenType=" + tokenType);
        } else if (j2 <= j) {
            LOG.w(String.format("Cached token expiring soon: %d ms (min=%d ms). TokenType=%s", Long.valueOf(j2), Long.valueOf(j), tokenType));
        } else {
            AuthToken authToken = new AuthToken(string, j2);
            LOG.i(String.format("Cached token is valid, using it without fetching: %s. TokenType=%s", authToken, tokenType));
            return authToken;
        }
        return null;
    }

    private static void recordDebugMetric(@Nullable String str, @NonNull String str2, @Nullable Integer num) {
        CounterMetric generateOperational = CounterMetric.generateOperational("comms.api.auth.token.create.debug");
        Map<String, Object> metadata = generateOperational.getMetadata();
        if (str != null) {
            metadata.put("requestId", str);
        }
        metadata.put("source", str2);
        if (num != null) {
            metadata.put("statusCode", num);
        }
        MetricsHelper.recordSingleOccurrence(generateOperational);
    }

    public static String sensitive(@Nullable String str) {
        return LOG.sensitive(str);
    }

    private static void storeAuthTokenInSharedPreference(@NonNull Context context, @NonNull SipAuthTokenResponse.AuthToken authToken, @NonNull TokenType tokenType) {
        String tokenValue = authToken.getTokenValue();
        long expiresIn = authToken.getExpiresIn() + System.currentTimeMillis();
        new SecuredSharedPreference(context).putString(tokenType.getTokenPreferenceKey(), tokenValue);
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putLong(tokenType.getExpiryPreferenceKey(), expiresIn).apply();
        context.getSharedPreferences("SHARED_PREFS", 0).edit().putString(tokenType.getHashPreferenceKey(), computeSHA256Hash(tokenValue)).apply();
    }

    static boolean validAuthTokenIntegrity(@NonNull String str, @NonNull TokenType tokenType) {
        String string = CommsDaggerWrapper.getComponent().getContext().getSharedPreferences("SHARED_PREFS", 0).getString(tokenType.getHashPreferenceKey(), null);
        String computeSHA256Hash = computeSHA256Hash(str);
        if (string != null && computeSHA256Hash != null && computeSHA256Hash.equals(string)) {
            CommsLogger commsLogger = LOG;
            commsLogger.d("Auth Token Hash is same before encrypting and after decrypting. TokenType=" + tokenType);
            return true;
        }
        CommsLogger commsLogger2 = LOG;
        commsLogger2.e("Auth Token Hash is not same before encrypting and after decrypting. TokenType=" + tokenType);
        return false;
    }

    public static synchronized AuthToken fetchAuthToken(@NonNull String str, @NonNull TokenType tokenType, long j, boolean z) {
        AuthToken savedAuthToken;
        synchronized (AuthTokenHelper.class) {
            Context context = CommsDaggerWrapper.getComponent().getContext();
            if (z || (savedAuthToken = getSavedAuthToken(context, tokenType, j)) == null || TextUtils.isEmpty(savedAuthToken.getValue())) {
                TokenInfo fetchToken = fetchToken(str, tokenType);
                SipAuthTokenResponse.AuthToken authToken = fetchToken.authToken;
                if (authToken != null && !TextUtils.isEmpty(authToken.getTokenValue())) {
                    storeAuthTokenInSharedPreference(context, fetchToken.authToken, tokenType);
                    AuthToken authToken2 = new AuthToken(fetchToken.authToken.getTokenValue(), fetchToken.authToken.getExpiresIn());
                    CommsLogger commsLogger = LOG;
                    commsLogger.i("Successfully fetched token: " + authToken2 + ". TokenType=" + tokenType);
                    return authToken2;
                }
                if (fetchToken.authToken != null) {
                    String str2 = fetchToken.requestId;
                    recordDebugMetric(str2, "Got token with empty or null token value. TokenType=" + tokenType, null);
                    CommsLogger commsLogger2 = LOG;
                    commsLogger2.e("Unable to retrieve SIP Auth Token. TokenType=" + tokenType);
                } else {
                    String str3 = fetchToken.requestId;
                    recordDebugMetric(str3, "Unable to retrieve SIP Auth Token. TokenType=" + tokenType, null);
                    CommsLogger commsLogger3 = LOG;
                    commsLogger3.e("Unable to retrieve SIP Auth Token. TokenType=" + tokenType);
                }
                return null;
            }
            return savedAuthToken;
        }
    }
}
