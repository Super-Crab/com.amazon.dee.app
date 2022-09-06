package com.amazon.alexa.redesign.view.container;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.redesign.utils.LatencyInteractor;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.amazon.latencyinfra.LatencyInfra;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes10.dex */
public class HomeViewControllerFactory implements ViewControllerFactory<HomeViewController> {
    HomeViewController viewController;

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public HomeViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        LatencyInteractor.initProfileTimeline((LatencyInfra) GeneratedOutlineSupport1.outline20(LatencyInfra.class), this.viewController == null);
        if (this.viewController == null) {
            this.viewController = new HomeViewController();
        }
        return this.viewController;
    }
}
