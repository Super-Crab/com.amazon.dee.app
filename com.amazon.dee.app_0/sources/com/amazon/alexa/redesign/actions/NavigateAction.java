package com.amazon.alexa.redesign.actions;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.routing.api.RoutingService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes10.dex */
public class NavigateAction extends Action {
    private static final String TAG = "NavigateAction";
    private final HomeOEInteractor homeOEInteractor;
    @Nullable
    private final String route;
    private final RoutingService routingService;

    public NavigateAction(@Nullable String str, @NonNull RoutingService routingService, @NonNull String str2, @NonNull String str3, @NonNull HomeOEInteractor homeOEInteractor) {
        super("NavigateAction", str2, str3);
        if (routingService != null) {
            if (str2 != null) {
                this.routingService = routingService;
                this.homeOEInteractor = homeOEInteractor;
                this.route = str;
                return;
            }
            throw new IllegalArgumentException("NavigateAction: metaActionType must not be null");
        }
        throw new IllegalArgumentException("NavigateAction: routingService must not be null");
    }

    private String addParamsToRoute(@Nullable String str, @NonNull Map<String, Object> map) {
        if (str == null) {
            return null;
        }
        if (map == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        if (map.size() > 0) {
            sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    sb.append(key);
                    sb.append(Config.Compare.EQUAL_TO);
                    sb.append(value);
                    sb.append(WebConstants.UriConstants.AMPERSAND_KEY);
                }
            }
            return sb.substring(0, sb.length() - 1);
        }
        return sb.toString();
    }

    private void navigateWithRoutingService(@Nullable String str) {
        try {
            if (str != null) {
                RoutingService.RoutingBuilder match = this.routingService.match(str);
                if (match != null) {
                    match.addToBackStack().navigate();
                } else {
                    String str2 = TAG;
                    Log.e(str2, "Route: " + str + " could not be matched");
                    this.homeOEInteractor.recordBadRouteOccurrence(str, HomeOEInteractor.ROUTE_NOT_MATCHED);
                }
            } else {
                Log.e(TAG, "Route was null");
                this.homeOEInteractor.recordBadRouteOccurrence(str, HomeOEInteractor.ROUTE_NULL);
            }
        } catch (Exception e) {
            GeneratedOutlineSupport1.outline148(e, GeneratedOutlineSupport1.outline115("Route: ", str, " is invalid "), TAG);
            this.homeOEInteractor.recordBadRouteOccurrence(str, HomeOEInteractor.ROUTE_INVALID);
        }
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        navigateWithRoutingService(this.route);
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
        navigateWithRoutingService(addParamsToRoute(this.route, map));
    }
}
