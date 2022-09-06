package com.amazon.alexa.biloba.utils;

import android.content.res.Configuration;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import dagger.Lazy;
import okhttp3.HttpUrl;
/* loaded from: classes6.dex */
public class UrlHelper {
    private static final ImmutableMap<String, String> STAGE_ENDPOINT_MAP = ImmutableMap.builder().mo7828put("beta", WebConstants.Endpoints.BETA_ENDPOINT).mo7828put("gamma", WebConstants.Endpoints.GAMMA_ENDPOINT).mo7828put("prod", "https://alexa.amazon.com").mo7828put("alpha", WebConstants.Endpoints.ALPHA_ENDPOINT).mo7826build();
    private static final String TAG = "UrlHelper";
    private final Lazy<CareActorsStore> careActorsStore;
    private final Lazy<EnvironmentService> environmentService;
    private final Lazy<FeatureServiceV2> featureServiceV2;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class QueryParameterAdder {
        private String url;

        QueryParameterAdder(String str) {
            this.url = str;
        }

        private boolean hasQueryParams(String str) {
            HttpUrl parse = HttpUrl.parse(str);
            return (parse == null || parse.querySize() == 0) ? false : true;
        }

        public QueryParameterAdder addQueryParam(String str, String str2) {
            this.url = GeneratedOutlineSupport1.outline93(new StringBuilder(), this.url, hasQueryParams(this.url) ? WebConstants.UriConstants.AMPERSAND_KEY : WebConstants.UriConstants.QUESTIONMARK_KEY, str, str2);
            return this;
        }

        public String getUrl() {
            return this.url;
        }
    }

    public UrlHelper(Lazy<EnvironmentService> lazy, Lazy<CareActorsStore> lazy2, Lazy<FeatureServiceV2> lazy3) {
        this.careActorsStore = lazy2;
        this.environmentService = lazy;
        this.featureServiceV2 = lazy3;
    }

    @WebConstants.UriConstants.Theme
    private String getSystemUIMode(Configuration configuration) {
        int i = configuration.uiMode & 48;
        return (i == 16 || i != 32) ? "light" : "dark";
    }

    public String getAdminProfileUrl(Configuration configuration) {
        CareActor value = this.careActorsStore.mo358get().getCareGiverAdmin().getValue();
        if (value == null) {
            LogUtils.e(TAG, "Admin profile URL requested but no admin. Returning care circle path.");
            return getUrl("/groups/care/dashboard", configuration);
        }
        return getUrl(String.format(WebConstants.WebviewPaths.MEMBER_PROFILE_PATH, value.getDirectedPersonIdV2()), configuration);
    }

    public String getEditEmergencyAddressUrl(Configuration configuration) {
        return getUrl(String.format(WebConstants.WebviewPaths.EMERGENCY_ADDRESS_CHANGE_PATH, this.careActorsStore.mo358get().getSubscriptionId(), this.environmentService.mo358get().getMarketplace().getObfuscatedId().toString()), configuration);
    }

    public String getUrl(@NonNull String str, Configuration configuration) {
        String mo7740get = STAGE_ENDPOINT_MAP.mo7740get(this.environmentService.mo358get().getBuildStage());
        if (AndroidUtils.isEmpty(mo7740get)) {
            mo7740get = STAGE_ENDPOINT_MAP.mo7740get("prod");
        }
        String outline72 = GeneratedOutlineSupport1.outline72(mo7740get, str);
        QueryParameterAdder addQueryParam = new QueryParameterAdder(outline72).addQueryParam(WebConstants.UriConstants.MOSAIC_THEMING_IN_APP_SETTINGS_QUERY_ARG, getSystemUIMode(configuration)).addQueryParam(WebConstants.UriConstants.HIDE_BANNER_QUERY_ARG, Boolean.toString(true));
        if (this.featureServiceV2.mo358get().hasAccess("ALEXA_AGING_ANDROID_BYPASS_PROFILE_SELECTION", false)) {
            addQueryParam.addQueryParam(WebConstants.UriConstants.CURRENT_ACTOR_PERSON_ID, this.careActorsStore.mo358get().getCurrentActor().getValue().getDirectedPersonIdV2());
        }
        String url = addQueryParam.getUrl();
        String str2 = TAG;
        LogUtils.d(str2, "ExternalWebView URL: " + url);
        return url;
    }
}
