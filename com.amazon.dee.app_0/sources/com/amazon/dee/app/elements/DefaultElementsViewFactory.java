package com.amazon.dee.app.elements;

import android.content.Context;
import androidx.annotation.Nullable;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.reactnative.api.ElementsViewFactory;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
/* loaded from: classes12.dex */
public class DefaultElementsViewFactory implements ElementsViewFactory {
    static final String TAG = "DefaultElementsViewFactory";
    @Nullable
    private IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);

    @Override // com.amazon.alexa.reactnative.api.ElementsViewFactory
    public ViewController makeViewControllerForRoute(RouteContext routeContext) {
        return makeViewControllerForRoute(null, routeContext);
    }

    @Override // com.amazon.alexa.reactnative.api.ElementsViewFactory
    public ViewController makeViewControllerForRoute(Context context, RouteContext routeContext) {
        IdentityService identityService;
        Route.Theme loggedOnUsersEffectiveTheme;
        Preconditions.checkNotNull(routeContext);
        ReactFeature reactFeatureFromRouteContext = ElementsUtils.getReactFeatureFromRouteContext(routeContext);
        if (reactFeatureFromRouteContext.getLaunchOptions() != null && (identityService = this.identityService) != null && (loggedOnUsersEffectiveTheme = ElementsUtils.getLoggedOnUsersEffectiveTheme(context, identityService.getUser(TAG), routeContext.getRoute())) != null) {
            reactFeatureFromRouteContext.getLaunchOptions().putString(ElementsRouteKeys.THEME, loggedOnUsersEffectiveTheme.toString());
        }
        return ReactFeatureViewController.create(reactFeatureFromRouteContext);
    }
}
