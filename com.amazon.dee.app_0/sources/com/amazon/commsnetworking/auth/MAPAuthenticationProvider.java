package com.amazon.commsnetworking.auth;

import androidx.annotation.NonNull;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.identity.CommsCoreIdentity;
import com.amazon.commscore.api.identity.CookieProvider;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.remoteconfiguration.AlexaCommsCoreRemoteConfigurationService;
import com.amazon.commsnetworking.Constants;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.util.TextUtils;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import rx.Observable;
/* loaded from: classes12.dex */
public class MAPAuthenticationProvider implements AuthenticationProvider {
    private static final String COOKIE_AT_ACB = "at-acb";
    private static final String COOKIE_AT_MAIN = "at-main";
    private static final String COOKIE_AT_TACB = "at-tacb";
    private static final String COOKIE_UBID_ACB = "ubid-acb";
    private static final String COOKIE_UBID_MAIN = "ubid-main";
    private static final String COOKIE_UBID_TACB = "ubid-tacb";
    private static final String CSRF_IDENTIFIER = "csrf";
    private static final String DEFAULT_HOST = ".amazon.com";
    private static final String HOST_CONFIG_KEY = "ACMS.Auth.Host";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, MAPAuthenticationProvider.class);
    private static final String METRIC_PREFIX = "comms.net.auth.map";
    private AlexaCommsCoreIdentityService commsCoreIdentityService;
    private CommsCoreIdentity commsIdentity;
    private AlexaCommsCoreRemoteConfigurationService configurationService;
    private IdentityService identityService;
    private MAPAccountManager mapAccountManager;
    private AlexaCommsCoreMetricsService metricsService;
    private String source;

    @Deprecated
    public MAPAuthenticationProvider(@NonNull String str, @NonNull IdentityService identityService, @NonNull CommsCoreIdentity commsCoreIdentity, @NonNull MAPAccountManager mAPAccountManager, @NonNull AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.source = str;
        this.identityService = identityService;
        this.commsIdentity = commsCoreIdentity;
        this.mapAccountManager = mAPAccountManager;
        this.configurationService = alexaCommsCoreRemoteConfigurationService;
        this.metricsService = alexaCommsCoreMetricsService;
    }

    private String getCookie(String str) {
        Observable<String[]> cookies;
        String[] strArr = new String[0];
        String remoteConfigValue = this.configurationService.getRemoteConfiguration(HOST_CONFIG_KEY, ".amazon.com").toString();
        IdentityService identityService = this.identityService;
        if (identityService != null) {
            cookies = identityService.getCookiesFromDirectedId(str, remoteConfigValue);
        } else {
            AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.commsCoreIdentityService;
            cookies = (alexaCommsCoreIdentityService == null || !(alexaCommsCoreIdentityService instanceof CookieProvider)) ? null : ((CookieProvider) alexaCommsCoreIdentityService).getCookies(str, remoteConfigValue);
        }
        if (cookies != null) {
            try {
                strArr = cookies.toBlocking().toFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                CommsLogger commsLogger = LOG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
                outline107.append(this.source);
                outline107.append("] Failed to get cookie");
                commsLogger.e(outline107.toString(), e);
                HashMap hashMap = new HashMap();
                hashMap.put("errorSource", e);
                this.metricsService.recordOccurrence("comms.net.auth.map.fail", this.source, true, hashMap);
                return "";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            String[] split = TextUtils.split(str2, ";");
            if (split.length >= 1 && !TextUtils.isEmpty(split[0])) {
                sb.append(split[0]);
                sb.append(";");
            } else {
                CommsLogger commsLogger2 = LOG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[");
                outline1072.append(this.source);
                outline1072.append("] malformed cookie: ");
                outline1072.append(str2);
                commsLogger2.w(outline1072.toString());
            }
        }
        String sb2 = sb.toString();
        if (validateCookie(sb2)) {
            this.metricsService.recordOccurrence("comms.net.auth.map.success", this.source, true, new HashMap());
        } else {
            CommsLogger commsLogger3 = LOG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("[");
            outline1073.append(this.source);
            outline1073.append("] invalid cookie: ");
            outline1073.append(LOG.sensitive(sb2));
            commsLogger3.e(outline1073.toString());
            HashMap hashMap2 = new HashMap();
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("invalid cookie: ");
            outline1074.append(LOG.sensitive(sb2));
            hashMap2.put("errorSource", outline1074.toString());
            this.metricsService.recordOccurrence("comms.net.auth.map.fail", this.source, true, hashMap2);
        }
        return sb2;
    }

    private String getCsrfCookieValue(String str) {
        for (String str2 : str.split(";")) {
            String[] split = str2.split(Config.Compare.EQUAL_TO);
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if ("csrf".equals(trim)) {
                    return trim2;
                }
            }
        }
        return null;
    }

    private boolean validateCookie(@NonNull String str) {
        String[] split;
        boolean z = false;
        boolean z2 = false;
        for (String str2 : str.split(";")) {
            if (str2.startsWith(COOKIE_UBID_MAIN) || str2.startsWith(COOKIE_UBID_ACB) || str2.startsWith(COOKIE_UBID_TACB)) {
                z = true;
            }
            if (str2.startsWith(COOKIE_AT_MAIN) || str2.startsWith(COOKIE_AT_ACB) || str2.startsWith(COOKIE_AT_TACB)) {
                z2 = true;
            }
        }
        return z && z2;
    }

    @Override // com.amazon.commsnetworking.auth.AuthenticationProvider
    public void authenticateRequest(@NonNull INetworkRequest iNetworkRequest) {
        AlexaCommsCoreIdentityService alexaCommsCoreIdentityService = this.commsCoreIdentityService;
        if (alexaCommsCoreIdentityService != null) {
            this.commsIdentity = alexaCommsCoreIdentityService.getIdentity();
        }
        String cookie = getCookie(this.commsIdentity.getDirectedId());
        String csrfCookieValue = getCsrfCookieValue(cookie);
        if (csrfCookieValue == null) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[");
            outline107.append(this.source);
            outline107.append("] csrf token not found, regenerating token");
            commsLogger.d(outline107.toString());
            csrfCookieValue = Integer.toString(new SecureRandom().nextInt());
            cookie = GeneratedOutlineSupport1.outline75(cookie, "csrf=", csrfCookieValue);
        }
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("[");
        GeneratedOutlineSupport1.outline181(outline1072, this.source, "] cookie = ", cookie, ", csrf = ");
        outline1072.append(csrfCookieValue);
        commsLogger2.d(outline1072.toString());
        iNetworkRequest.withHeader("csrf", csrfCookieValue).withHeader("Cookie", cookie);
    }

    public MAPAuthenticationProvider(@NonNull String str, @NonNull AlexaCommsCoreIdentityService alexaCommsCoreIdentityService, @NonNull AlexaCommsCoreRemoteConfigurationService alexaCommsCoreRemoteConfigurationService, @NonNull AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.source = str;
        this.commsCoreIdentityService = alexaCommsCoreIdentityService;
        this.configurationService = alexaCommsCoreRemoteConfigurationService;
        this.metricsService = alexaCommsCoreMetricsService;
    }
}
