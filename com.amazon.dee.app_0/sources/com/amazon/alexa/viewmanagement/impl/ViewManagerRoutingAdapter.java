package com.amazon.alexa.viewmanagement.impl;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.routing.api.Route;
import com.amazon.alexa.routing.api.RouteContext;
import com.amazon.alexa.routing.api.RoutingAdapter;
/* loaded from: classes10.dex */
public class ViewManagerRoutingAdapter implements RoutingAdapter {
    private static final String TAG = "ViewManagerRoutingAdapter";
    private ViewPresenter viewPresenter;

    public ViewManagerRoutingAdapter(@NonNull ViewPresenter viewPresenter) {
        this.viewPresenter = viewPresenter;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void enter(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void exit() {
        this.viewPresenter.removeView();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    @Nullable
    public RoutingAdapter.RouteConfiguration getConfiguration(@NonNull Route route) {
        return RoutingAdapter.RouteConfiguration.all();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public int getId() {
        return 18;
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void leave(@NonNull Route route, Route route2) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void navigate(@NonNull RouteContext routeContext, Runnable runnable) {
        String viewControllerFactoryClassName = routeContext.getRoute().getViewControllerFactoryClassName();
        if (!TextUtils.isEmpty(viewControllerFactoryClassName)) {
            this.viewPresenter.pushView(viewControllerFactoryClassName, routeContext);
        } else {
            Log.w(TAG, "Route did not have a ViewFactory id");
        }
        runnable.run();
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void push(RouteContext routeContext) {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void reenter() {
    }

    @Override // com.amazon.alexa.routing.api.RoutingAdapter
    public void replace(@NonNull RouteContext routeContext) {
    }
}
