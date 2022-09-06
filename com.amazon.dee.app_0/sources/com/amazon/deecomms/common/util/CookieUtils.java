package com.amazon.deecomms.common.util;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.auth.Stage;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsComponent;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.ExecutionException;
import rx.Observable;
/* loaded from: classes12.dex */
public final class CookieUtils {
    private static final String COOKIE_ASSIGNER = "=";
    private static final String COOKIE_SEPARATOR = ";";
    private static final String CSRF_COOKIE_NAME = "csrf";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, Utils.class);
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private CookieUtils() {
    }

    private static Bundle bundleForRegisteringAccount(String str, String str2) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11("com.amazon.dcp.sso.property.account.acctId", str);
        outline11.putString("com.amazon.dcp.sso.property.account.delegateeaccount", CommsDaggerWrapper.getComponent().getApplicationManager().getAccountManager().getAccount());
        outline11.putBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary", true);
        outline11.putString("device_name", CommsDaggerWrapper.getComponent().getCommsInternal().getDeviceNameForRegisteringWithMAP());
        outline11.putString("com.amazon.identity.ap.domain", str2);
        return outline11;
    }

    @VisibleForTesting
    public static String determineDomain() {
        Stage stage = CommsDaggerWrapper.getComponent().getStage();
        String preferredMarketplace = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getPreferredMarketplace(false);
        if (!Stage.ALPHA.equals(stage) && !Stage.BETA.equals(stage)) {
            return preferredMarketplace.equalsIgnoreCase("GB") ? Constants.AUTH_DOMAIN_UK : preferredMarketplace.equalsIgnoreCase("DE") ? Constants.AUTH_DOMAIN_DE : preferredMarketplace.equalsIgnoreCase("IN") ? Constants.AUTH_DOMAIN_IN : preferredMarketplace.equalsIgnoreCase("CA") ? Constants.AUTH_DOMAIN_CA : preferredMarketplace.equalsIgnoreCase("AU") ? Constants.AUTH_DOMAIN_AU : preferredMarketplace.equalsIgnoreCase("JP") ? Constants.AUTH_DOMAIN_JP : preferredMarketplace.equalsIgnoreCase("IT") ? Constants.AUTH_DOMAIN_IT : preferredMarketplace.equalsIgnoreCase("ES") ? Constants.AUTH_DOMAIN_ES : preferredMarketplace.equalsIgnoreCase("MX") ? Constants.AUTH_DOMAIN_MX : preferredMarketplace.equalsIgnoreCase("FR") ? Constants.AUTH_DOMAIN_FR : preferredMarketplace.equalsIgnoreCase("BR") ? Constants.AUTH_DOMAIN_BR : preferredMarketplace.equalsIgnoreCase("NL") ? Constants.AUTH_DOMAIN_NL : ".amazon.com";
        }
        LOG.i("Using Devo Domain");
        return Constants.AUTH_DOMAIN_DEVELOPMENT;
    }

    private static String determineDomainWithinUS() {
        Stage stage = CommsDaggerWrapper.getComponent().getStage();
        return (Stage.ALPHA.equals(stage) || Stage.BETA.equals(stage)) ? Constants.AUTH_DOMAIN_DEVELOPMENT : ".amazon.com";
    }

    @Nullable
    public static String extractSessionIdFromCookies(@NonNull String[] strArr) {
        for (String str : strArr) {
            if (str.startsWith("session-id=") && str.split("=").length >= 2) {
                return str.split(" ")[0].split("=")[1].replaceAll(COOKIE_SEPARATOR, "");
            }
        }
        return null;
    }

    @NonNull
    public static String[] getCookiesForDirectedId(@Nullable String str, boolean z) {
        CommsComponent component = CommsDaggerWrapper.getComponent();
        MAPAccountManager accountManager = component.getApplicationManager().getAccountManager();
        IdentityService identityService = component.getIdentityService();
        CapabilitiesManager capabilitiesManager = component.getCapabilitiesManager();
        if (str != null) {
            try {
                if (!registerAccountWithMap(str, accountManager)) {
                    LOG.e("Unable to register account with MAP.");
                    return EMPTY_STRING_ARRAY;
                }
            } catch (MAPCallbackErrorException | InterruptedException | ExecutionException e) {
                CommsLogger commsLogger = LOG;
                commsLogger.e("Exception. Not fetching cookies." + e);
                Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "getCookiesForDirectedId", e);
                MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.MAP_COOKIE_RETRIEVAL_FAILURE);
                return EMPTY_STRING_ARRAY;
            }
        }
        if (Utils.isNullOrEmpty(str)) {
            str = accountManager.getAccount();
        }
        String[] strArr = null;
        if (capabilitiesManager.getCookiesFromCore()) {
            LOG.w("Retrieving cookies via Core");
            Observable<String[]> cookiesFromDirectedId = identityService.getCookiesFromDirectedId(str, determineDomain());
            if (cookiesFromDirectedId != null) {
                strArr = cookiesFromDirectedId.toBlocking().toFuture().get();
            }
        } else {
            LOG.w("Retrieving cookies directly (incorrectly)");
            Bundle bundle = new Bundle();
            bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, z);
            TokenManagement tokenManagement = new TokenManagement(CommsDaggerWrapper.getComponent().getContext());
            String determineDomain = determineDomain();
            CommsLogger commsLogger2 = LOG;
            StringBuilder sb = new StringBuilder();
            sb.append("using domain: ");
            sb.append(determineDomain);
            commsLogger2.d(sb.toString());
            strArr = tokenManagement.getCookies(str, determineDomain, bundle, null).get().getStringArray(CookieKeys.KEY_COOKIES);
        }
        return strArr == null ? EMPTY_STRING_ARRAY : strArr;
    }

    public static String getCsrfCookieValue(String str) {
        for (String str2 : str.split(COOKIE_SEPARATOR)) {
            String[] split = str2.split("=");
            if (split.length >= 2) {
                String trim = split[0].trim();
                String trim2 = split[1].trim();
                if ("csrf".equals(trim)) {
                    LOG.d("Found csrf value in cookie.");
                    return trim2;
                }
            }
        }
        return null;
    }

    @NonNull
    public static String getSendableCookies(@Nullable String str, boolean z) {
        return toCookieString(getCookiesForDirectedId(str, z));
    }

    private static boolean registerAccountWithMap(String str, MAPAccountManager mAPAccountManager) {
        if (mAPAccountManager == null) {
            LOG.e("mapAccountManager was null, so cannot register with MAP.");
            return false;
        }
        if (!mAPAccountManager.isAccountRegistered(str)) {
            if (!TextUtils.equals(mAPAccountManager.getAccount(), str)) {
                Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_INFO, "registerAccountWithMap", "invalid directed id");
            }
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Need to register account for directedId ");
            outline1.append(LOG.sensitive(str));
            commsLogger.d(outline1.toString());
            try {
                Bundle bundle = mAPAccountManager.registerAccount(RegistrationType.REGISTER_DELEGATED_ACCOUNT, bundleForRegisteringAccount(str, determineDomainWithinUS()), null).get();
                if (bundle == null) {
                    LOG.e("Registration result from MAP was null!");
                    Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", "registrationBundle is null");
                    return false;
                } else if (bundle.containsKey("com.amazon.dcp.sso.ErrorCode")) {
                    String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                    if (!Utils.isNullOrEmpty(string)) {
                        CommsLogger commsLogger2 = LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Got error message ");
                        sb.append(string);
                        commsLogger2.e(sb.toString());
                    }
                    Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", "KEY_ERROR_CODE", string);
                    return false;
                }
            } catch (MAPCallbackErrorException e) {
                Bundle errorBundle = e.getErrorBundle();
                if (errorBundle != null && errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") == MAPAccountManager.RegistrationError.ACCOUNT_ALREADY_EXISTS.value()) {
                    LOG.d("MAP account was already registered on this device.");
                    Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", e);
                    return true;
                }
                if (errorBundle != null) {
                    String string2 = errorBundle.getString("com.amazon.dcp.sso.ErrorMessage");
                    CommsLogger commsLogger3 = LOG;
                    commsLogger3.e("MAP failed to register account: " + string2, e);
                    Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", "errorBundle: " + string2);
                } else {
                    LOG.e("MAP failed to register account", e);
                    Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", "errorBundle is null");
                }
                return false;
            } catch (Exception e2) {
                LOG.e("Failed to register account with MAP.", e2);
                Utils.recordMapMetric(MetricKeys.DEBUG_MAP_REGISTRATION_FAILED, "registerAccountWithMap", e2);
                return false;
            }
        }
        return true;
    }

    @NonNull
    public static String toCookieString(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            String[] split = TextUtils.split(str, COOKIE_SEPARATOR);
            if (split.length >= 1) {
                sb.append(split[0]);
                sb.append(COOKIE_SEPARATOR);
            } else {
                LOG.e("Cookie was malformed!");
            }
        }
        return sb.toString();
    }
}
