package com.amazon.alexa.cantilever;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.logging.Lib;
import com.amazon.alexa.logging.Tag;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.amazon.identity.auth.device.authorization.RegionUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class HelpURLService {
    static final String CANTILEVER_INTENT = "?cantileverIntent=%s";
    static final String CANTILEVER_NODE = "/csad/help/node/";
    static final String CANTILEVER_WORKFLOW = "/csad/workflow/";
    static final String CHAT_REDIRECT_SUFFIX = "?isChatRedirect=true";
    private static final Map<String, String> COUNTRY_CODE_TO_REGION;
    static final String DEFAULT_BASE_CANTILEVER_URL = "https://digprjsurvey.amazon.com";
    private static final String DEFAULT_COUNTRY_CODE = "US";
    private static final int NODE_ID_MAX_LENGTH = 512;
    static final String PLATFORM_WORKFLOW_ID = "84e99600-ed4b-4c47-8431-362876e79aa1";
    private final HelpConfigService helpConfigService;
    private final LazyComponent<IdentityService> identityService;
    private static final Tag TAG = LogConfig.TAGGER.tag(HelpURLService.class);
    private static final String CALLER = HelpURLService.class.getSimpleName();

    static {
        HashMap outline134 = GeneratedOutlineSupport1.outline134("BR", RegionUtil.REGION_STRING_NA, "CA", RegionUtil.REGION_STRING_NA);
        outline134.put("MX", RegionUtil.REGION_STRING_NA);
        outline134.put("US", RegionUtil.REGION_STRING_NA);
        outline134.put("DE", RegionUtil.REGION_STRING_EU);
        outline134.put("ES", RegionUtil.REGION_STRING_EU);
        outline134.put("FR", RegionUtil.REGION_STRING_EU);
        outline134.put("IN", RegionUtil.REGION_STRING_EU);
        outline134.put("IT", RegionUtil.REGION_STRING_EU);
        outline134.put("NL", RegionUtil.REGION_STRING_EU);
        outline134.put("GB", RegionUtil.REGION_STRING_EU);
        outline134.put("AU", "JP");
        outline134.put("JP", "JP");
        outline134.put("CN", "CN");
        COUNTRY_CODE_TO_REGION = Collections.unmodifiableMap(outline134);
    }

    public HelpURLService(Context context) {
        this(new HelpConfigService(context), ComponentRegistry.getInstance());
    }

    private String getCountryCode() {
        UserIdentity user = this.identityService.mo10268get().getUser(CALLER);
        if (user == null) {
            Lib.Log.w(TAG, "User identity is null.");
            return "US";
        }
        Marketplace marketplace = user.getMarketplace();
        if (marketplace == null) {
            Lib.Log.w(TAG, "Marketplace is null.");
            return "US";
        }
        return marketplace.getCountryCode().toString();
    }

    private String getDomainUrl() {
        Lib.Log.i(TAG, "Getting cantilever page url");
        String str = this.helpConfigService.getURLConfig().get(COUNTRY_CODE_TO_REGION.get(getCountryCode()));
        return TextUtils.isEmpty(str) ? DEFAULT_BASE_CANTILEVER_URL : str;
    }

    public String getCantileverIntentUrl(@Nullable String str) {
        Lib.Log.i(TAG, "Retrieving cantilever intent url");
        StringBuilder sb = new StringBuilder();
        sb.append(getDomainUrl());
        sb.append(CANTILEVER_WORKFLOW);
        sb.append(PLATFORM_WORKFLOW_ID);
        if (!TextUtils.isEmpty(str) && str.length() <= 512) {
            sb.append(String.format(CANTILEVER_INTENT, str));
            return sb.toString();
        }
        return sb.toString();
    }

    public String getChatPageURL() {
        return GeneratedOutlineSupport1.outline93(new StringBuilder(), getDomainUrl(), CANTILEVER_WORKFLOW, PLATFORM_WORKFLOW_ID, CHAT_REDIRECT_SUFFIX);
    }

    public String getPageURL(@Nullable String str) {
        String domainUrl = getDomainUrl();
        Lib.Log.i(TAG, "Retrieved cantilever page url");
        if (!TextUtils.isEmpty(str) && str.length() <= 512) {
            return GeneratedOutlineSupport1.outline75(domainUrl, CANTILEVER_NODE, str);
        }
        return GeneratedOutlineSupport1.outline75(domainUrl, CANTILEVER_WORKFLOW, PLATFORM_WORKFLOW_ID);
    }

    public HelpURLService(HelpConfigService helpConfigService, ComponentRegistry componentRegistry) {
        this.helpConfigService = helpConfigService;
        this.identityService = componentRegistry.getLazy(IdentityService.class);
    }
}
