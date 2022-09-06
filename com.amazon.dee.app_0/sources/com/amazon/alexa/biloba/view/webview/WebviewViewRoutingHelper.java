package com.amazon.alexa.biloba.view.webview;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.MarketplaceId;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import dagger.Lazy;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class WebviewViewRoutingHelper {
    private static final String TAG = "WebviewViewRoutingHelper";
    @Inject
    public Lazy<CareActorsStore> careActorsStore;
    @Inject
    public Lazy<IdentityService> identityService;
    private static final ImmutableMap<String, String> WEBVIEW_ROUTES = ImmutableMap.builder().mo7828put(Routes.BILOBA_CARE_PLUS_DISCOVERY, "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011").mo7828put(Routes.BILOBA_CARE_PLUS_UPSELL, "https://www.amazon.com/b/ref=ods_surl_ac?&node=21390531011").mo7828put(Routes.BILOBA_COMMS_PRIMER, WebConstants.WebviewPaths.COMMS_PRIMER_PATH).mo7828put("biloba/external/dashboard", "/groups/care/dashboard").mo7828put(Routes.BILOBA_FINISH_COMMS_SETUP, WebConstants.WebviewPaths.COMMS_PRIMER_PATH).mo7828put(Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP, WebConstants.WebviewPaths.EMERGENCY_ADDRESS_PATH).mo7828put(Routes.BILOBA_TEST_EMERGENCY_HELPLINE, WebConstants.WebviewPaths.TEST_EMERGENCY_HELPLINE_PATH).mo7828put(Routes.BILOBA_LONE_CR_CREATE_RELATIONSHIP, WebConstants.WebviewPaths.LONE_CR_CREATE_RELATIONSHIP_URL).mo7828put(Routes.BILOBA_CARE_PLUS_WELCOME, WebConstants.WebviewPaths.CARE_PLUS_WELCOME_PATH).mo7828put(Routes.BILOBA_INVITE_CARE_GIVER, WebConstants.WebviewPaths.INVITE_CG_MULTI_CG_URL).mo7826build();
    private static final Set<String> ROUTES_WITH_SUBSCRIPTION_PARAMS = ImmutableSet.of(Routes.BILOBA_FINISH_EMERGENCY_ADDRESS_SETUP, Routes.BILOBA_CARE_PLUS_WELCOME);

    @Inject
    public WebviewViewRoutingHelper() {
    }

    private String getMarketPlaceId(RouteContext routeContext) {
        String parameterValue = BilobaRouteUtil.getParameterValue(routeContext, WebConstants.WebviewConstants.MARKETPLACE_ID);
        if (!AndroidUtils.isEmpty(parameterValue)) {
            return parameterValue;
        }
        UserIdentity user = this.identityService.mo358get().getUser(TAG);
        if (user != null && user.getMarketplace() != null) {
            return user.getMarketplace().getObfuscatedId().toString();
        }
        return MarketplaceId.COM.toString();
    }

    private String getSubscriptionID(RouteContext routeContext) {
        String parameterValue = BilobaRouteUtil.getParameterValue(routeContext, WebConstants.WebviewConstants.SUBSCRIPTION_ID);
        return AndroidUtils.isEmpty(parameterValue) ? this.careActorsStore.mo358get().getSubscriptionId() : parameterValue;
    }

    private boolean needsSubscriptionParams(Route route) {
        return ROUTES_WITH_SUBSCRIPTION_PARAMS.contains(route.getName());
    }

    @VisibleForTesting
    String getWebviewUri(RouteContext routeContext) {
        Route route = routeContext.getRoute();
        String mo7740get = WEBVIEW_ROUTES.mo7740get(route.getName());
        if (needsSubscriptionParams(route)) {
            mo7740get = String.format(mo7740get, getSubscriptionID(routeContext), getMarketPlaceId(routeContext));
        }
        LogUtils.d(TAG, String.format("Retrieved webview URI %s using route %s", mo7740get, route.getName()));
        return mo7740get;
    }

    public ViewController getWebviewViewController(Context context, RouteContext routeContext) {
        RouteContext routeContext2 = (RouteContext) routeContext.getParcelable(DeferredRoutingHelper.DEFERRED_ROUTE_CONTEXT);
        if (routeContext2 != null) {
            routeContext = routeContext2;
        }
        String webviewUri = getWebviewUri(routeContext);
        if (!AndroidUtils.isEmpty(webviewUri)) {
            return new WebviewView(context, webviewUri);
        }
        return null;
    }

    public boolean isWebviewRoute(String str) {
        return WEBVIEW_ROUTES.containsKey(str);
    }

    @VisibleForTesting
    WebviewViewRoutingHelper(Lazy<CareActorsStore> lazy, Lazy<IdentityService> lazy2) {
        this.careActorsStore = lazy;
        this.identityService = lazy2;
    }
}
