package com.amazon.deecomms.identity;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.marketplace.Marketplace;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.auth.SecuredSharedPreference;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.ThreadUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import java.text.MessageFormat;
import org.apache.commons.lang3.StringUtils;
/* loaded from: classes12.dex */
public final class CommsIdentityUtils {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentityUtils.class);
    private static final long USER_INFO_EXPIRY_MS = 300000;

    private CommsIdentityUtils() {
    }

    @VisibleForTesting
    static void cacheUserInfo(@NonNull Context context, @NonNull UserInfo userInfo) {
        new SecuredSharedPreference(context).putString(Constants.LAST_USER_INFO_PREF, new Gson().toJson(userInfo));
        Utils.writeLongPreferenceToSharedPrefs(context, Constants.LAST_USER_INFO_SYNC_TIME, System.currentTimeMillis());
    }

    @Nullable
    public static String fetchAndStoreUserAOR(@NonNull Context context) throws ServiceException {
        ThreadUtils.checkNotMainThread();
        String commsId = CommsDaggerWrapper.getComponent().getCommsIdentityManager().getCommsId("fetchAndStoreUserAOR", false);
        if (StringUtils.isEmpty(commsId)) {
            LOG.w("[fetchUserInfo] Unable to fetch and store user AOR as comms identity has an empty commsId");
            return null;
        }
        UserInfo fetchLatestUserInfo = fetchLatestUserInfo(context, commsId, "fetchAndStoreUserAOR");
        if (fetchLatestUserInfo == null) {
            LOG.w("[fetchUserInfo] Unable to fetch and store user AOR as we were unable to fetch the user's info");
            return null;
        }
        String aor = fetchLatestUserInfo.getAor();
        if (aor == null) {
            LOG.w("[fetchUserInfo] Unable to fetch and store user AOR as the data fetched did not include the aor");
        } else if (!aor.equals(CommsDaggerWrapper.getComponent().getCommsIdentityManager().getAor())) {
            CommsDaggerWrapper.getComponent().getCommsIdentityManager().setAor(aor, true);
            CommsLogger commsLogger = LOG;
            commsLogger.i(String.format("[fetchUserInfo] Persisted user's aor (%s) successfully", commsLogger.sensitive(aor)));
        } else {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.i(String.format("[fetchUserInfo] Did not need to persist the user's aor (%s), it remains unchanged", commsLogger2.sensitive(aor)));
        }
        return aor;
    }

    @Nullable
    public static UserInfo fetchLatestUserInfo(@NonNull Context context, @NonNull String str, @NonNull String str2) throws ServiceException {
        ThreadUtils.checkNotMainThread();
        LOG.i("[fetchUserInfo] Querying ACMS for latest UserInfo.");
        UserInfo internalFetchUserInfo = internalFetchUserInfo(str, str2);
        if (internalFetchUserInfo != null) {
            cacheUserInfo(context, internalFetchUserInfo);
        }
        return internalFetchUserInfo;
    }

    @Nullable
    @VisibleForTesting
    static UserInfo fetchLocalUserInfo(@NonNull Context context, long j, @NonNull String str) {
        if (isUserInfoExpired(context, j)) {
            return null;
        }
        UserInfo internalFetchUserInfoFromCache = internalFetchUserInfoFromCache(context, null);
        if (internalFetchUserInfoFromCache != null) {
            LOG.i("[fetchUserInfo] returned cached UserInfo.");
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.CD_API_GET_IDENTITY_V2_CACHED, str + ", fetchUserInfo() in CommsIdentityUtils");
        }
        return internalFetchUserInfoFromCache;
    }

    @Nullable
    public static UserInfo fetchUserInfo(@NonNull Context context, @NonNull String str, @NonNull String str2) throws ServiceException {
        UserInfo fetchLocalUserInfo;
        ThreadUtils.checkNotMainThread();
        return (CommsDaggerWrapper.getComponent().getCapabilitiesManager().disableNMinuteIdentityCache() || (fetchLocalUserInfo = fetchLocalUserInfo(context, System.currentTimeMillis(), str2)) == null) ? fetchLatestUserInfo(context, str, str2) : fetchLocalUserInfo;
    }

    @NonNull
    public static String getPfmCodeFromMarketplace(String str, String str2) {
        Marketplace findMarketplaceById = Marketplace.findMarketplaceById(str, null);
        return findMarketplaceById != null ? findMarketplaceById.getMarketplaceName().toString() : str2;
    }

    @Nullable
    @VisibleForTesting
    static UserInfo internalFetchUserInfo(@NonNull String str, @NonNull String str2) throws ServiceException {
        ThreadUtils.checkNotMainThread();
        ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_GET_IDENTITY_V2);
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(", internalFetchUserInfo() in CommsIdentityUtils");
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.OP_GET_IDENTITY_V2, sb.toString());
            UserInfo userInfo = (UserInfo) aCMSClient.request(MessageFormat.format("/users/{0}/identities", str)).authenticatedAsCurrentCommsUser().get().mo3640execute().convert(UserInfo.class);
            if (userInfo != null) {
                return userInfo;
            }
            throw new ServiceException("Service returned a null UserInfo response");
        } catch (ServiceException e) {
            LOG.e("[fetchUserInfo] Service Error occurred while fetching User Info.", e);
            throw e;
        }
    }

    @VisibleForTesting
    static UserInfo internalFetchUserInfoFromCache(@NonNull Context context, @Nullable SecuredSharedPreference securedSharedPreference) {
        if (securedSharedPreference == null) {
            securedSharedPreference = new SecuredSharedPreference(context);
        }
        String string = securedSharedPreference.getString(Constants.LAST_USER_INFO_PREF, null);
        if (string == null) {
            LOG.w("[fetchUserInfo] Cached UserInfo is empty");
            return null;
        }
        try {
            UserInfo userInfo = (UserInfo) new Gson().fromJson(string, (Class<Object>) UserInfo.class);
            if (userInfo != null && !StringUtils.isEmpty(userInfo.getCommsId()) && !StringUtils.isEmpty(userInfo.getHomeGroupId()) && !StringUtils.isEmpty(userInfo.getHashedCommsId()) && !StringUtils.isEmpty(userInfo.getAor())) {
                return userInfo;
            }
            LOG.w("[fetchUserInfo] Cached UserInfo is malformed");
            return null;
        } catch (JsonParseException e) {
            LOG.e("[fetchUserInfo] Cached UserInfo resulted in JSON Parsing error", e);
            return null;
        }
    }

    @VisibleForTesting
    static boolean isUserInfoExpired(@NonNull Context context, long j) {
        CommsLogger commsLogger = LOG;
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("[fetchUserInfo] UserInfo cache expires in ");
        long longPreferenceFromSharedPrefs = (Utils.getLongPreferenceFromSharedPrefs(context, Constants.LAST_USER_INFO_SYNC_TIME, -1L) + 300000) - j;
        outline1.append(longPreferenceFromSharedPrefs / 1000.0d);
        outline1.append(" seconds");
        commsLogger.i(outline1.toString());
        return longPreferenceFromSharedPrefs <= 0;
    }

    @NonNull
    public static String safeNameFormat(@Nullable String str, @Nullable String str2) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        if (str == null || TextUtils.isEmpty(str)) {
            z = false;
        } else {
            sb.append(str);
            z = true;
        }
        if (str2 != null && !TextUtils.isEmpty(str2)) {
            if (z) {
                sb.append(" ");
            }
            sb.append(str2);
        }
        return sb.toString();
    }
}
