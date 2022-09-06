package com.amazon.alexa.routing.api;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.rxjava3.core.Observable;
import java.util.ArrayList;
/* loaded from: classes10.dex */
public interface RoutingService {

    /* loaded from: classes10.dex */
    public interface RouteInterceptor {
        boolean onRouteChanging(RouteContext routeContext);
    }

    /* loaded from: classes10.dex */
    public interface RoutingBuilder {
        RoutingBuilder addToBackStack();

        RoutingBuilder clearBackStack();

        RouteContext create();

        void forceNavigate();

        RoutingBuilder fromMainMenu();

        void navigate();

        void navigateReplaceTop();

        void notifyNavigated();

        void popUntil();

        void popUntil(boolean z);

        RoutingBuilder with(@NonNull String str, int i);

        RoutingBuilder with(@NonNull String str, @Nullable Bundle bundle);

        RoutingBuilder with(@NonNull String str, @Nullable Parcelable parcelable);

        RoutingBuilder with(@NonNull String str, @Nullable String str2);

        RoutingBuilder with(@NonNull String str, boolean z);

        RoutingBuilder withAll(@NonNull Bundle bundle);

        RoutingBuilder withRequestId(@NonNull String str);
    }

    void addHomeToBackStackIfEmpty();

    boolean canNavigateBackward();

    void clearBackStack();

    void clearCurrentRoute();

    @Nullable
    RouteContext findRouteByRequestId(@NonNull String str);

    RouteContext[] getBackstack();

    @Nullable
    RouteContext getCurrentRoute();

    @Nullable
    RouteContext getRouteFromBackStack(@NonNull String str);

    boolean isMenuRoute(RouteContext routeContext);

    boolean isRegisteredRouteInterceptor(RouteInterceptor routeInterceptor);

    @Nullable
    RoutingBuilder match(@NonNull String str);

    void navigate(RouteContext routeContext);

    void navigate(String str);

    void navigate(String str, boolean z);

    void navigateBackward();

    void navigateReplaceTop(@NonNull RouteContext routeContext);

    void navigateToExit();

    void navigateUpward();

    void notifyRouteChanged(@NonNull RouteContext routeContext);

    @Nullable
    Observable<RoutePath> onRouteChanged();

    boolean popFromBackStack(String str);

    boolean popUntil(String str);

    boolean popUntil(String str, boolean z, Bundle bundle);

    void refresh();

    void registerObserver(@NonNull RoutingObserver routingObserver);

    void registerRouteInterceptor(RouteInterceptor routeInterceptor);

    ArrayList<RouteContext> removeRoutesFromBackStack(@NonNull Route route);

    void removeTopFromBackStack();

    @Nullable
    RouteContext restoreState(@Nullable Bundle bundle);

    @NonNull
    RoutingBuilder route(String str);

    void saveContentModeToRouteContext(int i);

    void saveRouteContextInBackStack(@NonNull RouteContext routeContext);

    @Nullable
    Bundle saveState();

    void setCurrentRouteContext(RouteContext routeContext, boolean z);

    void startLoadingTimeout(Route route);

    void stopLoadingTimeout();

    void unregisterObserver(@NonNull RoutingObserver routingObserver);

    void unregisterRouteInterceptor(RouteInterceptor routeInterceptor);
}
