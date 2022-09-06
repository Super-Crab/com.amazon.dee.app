package com.amazon.alexa.drive.landing;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public class HomeViewControllerFactory implements ViewControllerFactory<ViewController> {
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        if (routeContext.getRoute().is("drive-mode/home/index")) {
            FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
            Preconditions.checkNotNull(featureServiceV2);
            if (featureServiceV2.hasAccess("ALEXA_AUTO_ANDROID_AUTOMODE_2_0_LAUNCH", false)) {
                return new LandingPageViewControllerV2();
            }
            return new LandingPageViewController();
        }
        return null;
    }
}
