package com.amazon.alexa.routing.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public interface RoutingAdapter {

    /* loaded from: classes10.dex */
    public static class RouteConfiguration {
        boolean enter;
        boolean leave;
        boolean navigate;

        public static RouteConfiguration all() {
            return enter().andLeave().andNavigate();
        }

        public static RouteConfiguration enter() {
            return new RouteConfiguration().andEnter();
        }

        public static RouteConfiguration leave() {
            return new RouteConfiguration().andLeave();
        }

        public static RouteConfiguration navigate() {
            return new RouteConfiguration().andNavigate();
        }

        public RouteConfiguration andEnter() {
            this.enter = true;
            return this;
        }

        public RouteConfiguration andLeave() {
            this.leave = true;
            return this;
        }

        public RouteConfiguration andNavigate() {
            this.navigate = true;
            return this;
        }

        public boolean canEnter() {
            return this.enter;
        }

        public boolean canLeave() {
            return this.leave;
        }

        public boolean canNavigate() {
            return this.navigate;
        }
    }

    void enter(@NonNull Route route, Route route2);

    void exit();

    @Nullable
    RouteConfiguration getConfiguration(@NonNull Route route);

    int getId();

    void leave(@NonNull Route route, Route route2);

    void navigate(@NonNull RouteContext routeContext, Runnable runnable);

    void push(RouteContext routeContext);

    void reenter();

    void replace(@NonNull RouteContext routeContext);

    default void reset() {
    }
}
