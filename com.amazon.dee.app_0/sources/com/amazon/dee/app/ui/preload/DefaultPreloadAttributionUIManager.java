package com.amazon.dee.app.ui.preload;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
/* loaded from: classes12.dex */
public class DefaultPreloadAttributionUIManager implements PreloadAttributionUIManager {
    private PreloadAttributionManager attributionManager;
    private Context context;
    private int headerClickCount = 0;
    private RoutingService routingService;

    public DefaultPreloadAttributionUIManager(Context context, RoutingService routingService, PreloadAttributionManager preloadAttributionManager) {
        this.context = context;
        this.routingService = routingService;
        this.attributionManager = preloadAttributionManager;
    }

    @Nullable
    String getCurrentRoute() {
        RouteContext currentRoute = this.routingService.getCurrentRoute();
        if (currentRoute != null) {
            return currentRoute.getRoute().getName();
        }
        return null;
    }

    @Override // com.amazon.dee.app.ui.preload.PreloadAttributionUIManager
    public void onAppHeaderClicked() {
        if (RouteName.HELP.equals(getCurrentRoute())) {
            this.headerClickCount++;
            if (this.headerClickCount != 5) {
                return;
            }
            showAttributionTagToast();
            this.headerClickCount = 0;
            return;
        }
        this.headerClickCount = 0;
    }

    void showAttributionTagToast() {
        Toast.makeText(this.context, this.attributionManager.getAttributionTag(), 1).show();
    }
}
