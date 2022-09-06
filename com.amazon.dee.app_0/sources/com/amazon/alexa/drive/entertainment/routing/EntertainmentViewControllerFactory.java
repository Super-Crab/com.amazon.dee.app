package com.amazon.alexa.drive.entertainment.routing;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewControllerV2;
import com.amazon.alexa.drive.entertainment.view.NowPlayingScreenViewController;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.permissions.api.PermissionsService;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.viewmanagement.api.ViewController;
import com.amazon.alexa.viewmanagement.api.ViewControllerFactory;
import com.amazon.alexa.viewmanagement.api.ViewManagerLoadingDelegate;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes7.dex */
public class EntertainmentViewControllerFactory implements ViewControllerFactory<ViewController> {
    @Override // com.amazon.alexa.viewmanagement.api.ViewControllerFactory
    /* renamed from: createView */
    public ViewController mo2381createView(@NonNull Context context, @NonNull PermissionsService permissionsService, @NonNull RouteContext routeContext, @NonNull ViewManagerLoadingDelegate viewManagerLoadingDelegate) {
        FeatureServiceV2 featureServiceV2 = (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
        if (routeContext.getRoute().is("drive-mode/entertainment/index")) {
            Preconditions.checkNotNull(featureServiceV2);
            if (featureServiceV2.hasAccess("ALEXA_AUTO_ANDROID_AUTOMODE_2_0_LAUNCH", false)) {
                return new EntertainmentLandingPageViewControllerV2();
            }
            return new EntertainmentLandingPageViewController();
        } else if (!routeContext.getRoute().is("drive-mode/entertainment/now-playing-screen")) {
            return null;
        } else {
            return new NowPlayingScreenViewController();
        }
    }
}
